package com.abc.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/*****
 * 手写Jedis,理解 RESP 协议
 *
 * 例如 set mads shuaige
 * 转义成RESP的写法后
 * *3
 * $3
 * set
 * $4
 * mads
 * $7
 * shuaige
 *
 * ps:注意每个回车代表了结束符  \r\n
 * @author  mads
 */
public class JedisDemo {
    private Socket jedis = null;

    private String CTRL = "\r\n";
    private String API_SET = "SET";
    private String FUNCTION_XING = "*";
    private String FUNCTION_DAOLE = "$";


    public static void main(String[] args) throws IOException {
        JedisDemo jedis = new JedisDemo();
        jedis.inintJedis();
        jedis.setString("ceshimads", "huichegngognma");
    }

    public void inintJedis() {
        try {
            jedis = new Socket("redis.catpaw.local", 6379);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*******
     * 模拟 Redis 的SET 命令
     * @param key
     * @param value
     */
    public void setString(String key,String value) throws IOException {

        //这里使用了 阿里开发规范里的提到的  try-with-resource的流处理方式，优雅的关闭流
        try (OutputStream out = jedis.getOutputStream()){
            sendCommand(out, API_SET, key.getBytes(),value.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

//        byte[] xing_byte = "*".getBytes();
//        byte[] daole_byte = "$".getBytes();
//
//        //*3
//        out.write(xing_byte);
//        out.write(mingling.length + 1);
//
//        //$3
//        out.write(daole_byte);
//        out.write(API_SET.getBytes().length);
//        out.write(API_SET.getBytes());
//        out.write(CTRL.getBytes());
//
//        //$4mads$7shuaige
//        for (String v:mingling) {
//            out.write(daole_byte);
//            out.write(v.getBytes().length);
//            out.write(v.getBytes());
//            out.write(CTRL.getBytes());
//        }getBytes

    }

    /******
     *  转义成resp 协议 并执行
     * @param out
     * @param command redis 的api set,hget...  ，这里可以优化成枚举
     * @param args
     * @throws IOException
     */
    private  void sendCommand(OutputStream out,String command ,byte[] ... args) throws IOException {
        //比如  key ==mads   value== shuaige

        //在确定的字符串个数的情况下传入大小。避免了后续的扩容操作，提升效率
        StringBuilder ss = new StringBuilder(20);

        // *3
        ss.append(FUNCTION_XING).append(args.length + 1).append(CTRL);
        //$3
        ss.append(FUNCTION_DAOLE).append(command.length()).append(CTRL);
        //SET
        ss.append(command).append(CTRL);

        //$4mads$7shuaige
        for (byte[] arg:args) {
            ss.append(FUNCTION_DAOLE).append(arg.length).append(CTRL);

            ss.append(new String(arg)).append(CTRL);
        }
        System.out.println("拼装成的RESP协议串->:"+ss);
        out.write(ss.toString().getBytes());
        out.flush();

        try (InputStream inputStream = jedis.getInputStream()){

            byte[] buff = new byte[1024];
            int len = inputStream.read(buff);
            if(len > 0){
                String msg = new String(buff,0,len);

                System.out.println("jedis客户端收到Redis服务端返回的数据:"+msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
