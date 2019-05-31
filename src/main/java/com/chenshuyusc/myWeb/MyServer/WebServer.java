package com.chenshuyusc.myWeb.MyServer;

import com.chenshuyusc.myWeb.Handle.Handler;

import java.io.*;
import java.net.ServerSocket;

public class WebServer {
    private int port;
    private String path;

    public WebServer(int port){
        this.port = port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void start() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println(" 🌟 本地服务器开始了它的表演\n");

            // 全部交给 handler 来处理
            Handler handler = new Handler(serverSocket);
            handler.setPath(path);
            handler.handle();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("☠️ 接收端出现异常");
        } finally {
            if (serverSocket != null)
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("☠️ 接收端 finally 出现异常");
                }
        }
    }
}
