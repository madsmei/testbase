package com.abc.demo;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

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
 * @author mads
 */
public class JedisDemo {
    private Socket jedis = null;

    private String CTRL = "\r\n";//结束语
    private String FUNCTION_XING = "*";//数组
    private String FUNCTION_DAOLE = "$";//字符 Bulk Strings 用于表示长度最大为512MB的单个二进制安全字符串。
    private String FUNCTION_NUM = ":";//数字
    private String FUNCTION_ERROR = "-";//错误回复
    private String FUNCTION_STATUS = "+";//状态回复

    private String API_SET = "SET";
    private String API_GET = "GET";
    private String API_INCRBY = "INCRBY";//


    public static void main(String[] args) throws IOException {
        JedisDemo jedis = new JedisDemo();
        jedis.inintJedis();
//        jedis.setString("mads", "googboy");
//        jedis.getString("mads");
        jedis.incrby("madsnum", "1");
    }

    public void inintJedis() {
        try {
            jedis = new Socket("localhost", 6379);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*******
     * 模拟 Redis 的GET 命令
     * @param key
     */
    public void incrby(String key, String inc) throws IOException {

        //这里使用了 阿里开发规范里的提到的  try-with-resource的流处理方式，优雅的关闭流
        try (OutputStream out = jedis.getOutputStream()) {
            sendCommand(out, API_INCRBY, key.getBytes(), inc.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*******
     * 模拟 Redis 的GET 命令
     * @param key
     */
    public void getString(String key) throws IOException {

        //这里使用了 阿里开发规范里的提到的  try-with-resource的流处理方式，优雅的关闭流
        try (OutputStream out = jedis.getOutputStream()) {
            sendCommand(out, API_GET, key.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*******
     * 模拟 Redis 的SET 命令
     * @param key
     * @param value
     */
    public void setString(String key, String value) throws IOException {

        //这里使用了 阿里开发规范里的提到的  try-with-resource的流处理方式，优雅的关闭流
        try (OutputStream out = jedis.getOutputStream()) {
            sendCommand(out, API_SET, key.getBytes(), value.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /******
     *  转义成resp 协议 并执行
     * @param out
     * @param command redis 的api set,hget...  ，这里可以优化成枚举
     * @param args
     * @throws IOException
     */
    private void sendCommand(OutputStream out, String command, byte[]... args) throws IOException {
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
        for (byte[] arg : args) {
            ss.append(FUNCTION_DAOLE).append(arg.length).append(CTRL);

            ss.append(new String(arg)).append(CTRL);
        }
        System.out.println("自己客户端拼装成的RESP协议串->:" + ss);
        out.write(ss.toString().getBytes());
        out.flush();

        try (InputStream inputStream = jedis.getInputStream()) {

            byte[] buff = new byte[1024];
            int len = inputStream.read(buff);
            if (len > 0) {
                String msg = new String(buff, 0, len);

                System.out.println("自己客户端收到Redis服务端返回的数据:" + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
