package com.abc.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


/******
 * socket 服务端和客户端 通信的实现
 */
public class SocketDemo {

    public static void main(String[] args) {

        new Thread(()->{
            SocketServer server = new SocketDemo().new SocketServer();
            server.startServerScoket();
        }).start();

        new Thread(()->{
            SocketClient client = new SocketDemo().new SocketClient();
            client.startClint();
        }).start();
    }


    /******
     * SOCKET 服务端
     */
     class SocketServer {

        private  int count = 0;

        //开始服务端监听
        public  void startServerScoket() {
            ServerSocket server = null;
            Socket clint = null;
            try {
                server = new ServerSocket(8080);
                System.out.println("server准备好了------");

                while (true) {
                    clint = server.accept();
                    System.out.println("接待亲朋一位");

                    count++;

                    InputStream inputStream = clint.getInputStream();

                    byte[] buff = new byte[1024];
                    int len = inputStream.read(buff);
                    if(len > 0){
                        String msg = new String(buff,0,len);
                        System.out.println("server收到信息："+msg);
                        OutputStream outputStream = clint.getOutputStream();
                        String res = "服务端返回包---哈哈哈---";
                        outputStream.write(res.getBytes());
                        outputStream.flush();
                        outputStream.close();
                    }
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    /******
     * socket 客户端
     */
     class SocketClient {

         //开启客户端访问
        public  void startClint() {

            try {
                Socket client = new Socket("localhost", 8080);

                OutputStream outputStream = client.getOutputStream();
                outputStream.write("发送数据包---你好-".getBytes());
                outputStream.flush();

                InputStream inputStream = client.getInputStream();

                byte[] buff = new byte[1024];
                int len = inputStream.read(buff);
                if(len > 0){
                    String msg = new String(buff,0,len);

                    System.out.println("收到的包:"+msg);
                }
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
