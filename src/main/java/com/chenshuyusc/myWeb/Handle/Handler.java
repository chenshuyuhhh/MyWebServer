package com.chenshuyusc.myWeb.Handle;

import com.chenshuyusc.myWeb.Request.HttpRequest;
import com.chenshuyusc.myWeb.Response.GetHttpResponse;
import com.chenshuyusc.myWeb.Response.HeadHttpResponse;
import com.chenshuyusc.myWeb.Response.HttpResponse;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Handler implements Handle {
    private ServerSocket serverSocket;
    private HttpRequest httpRequest;
    private HttpResponse httpResponse;
    private String path;

    public Handler(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void handle() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();

            // 接收客户端的信息
            InputStream in = socket.getInputStream();

            // 将客户端的请求信息打印出来
            int count = 0;
            while (count == 0) {
                count = in.available();
            }
            byte[] b = new byte[count];
            in.read(b);
            String str = new String(b);

            System.out.println("[ Request ] 服务器收到 http 请求：\n"+str);
            OutputStream outer = socket.getOutputStream();

            // 获得 http 请求报文对应的实体
            httpRequest = new HttpRequest(str);

            // 向客户端发送响应消息
            if (httpRequest.getMethod().equals(Method.GET.getMethod())){
                httpResponse = new GetHttpResponse();
                doGet(outer);
            }
            if (httpRequest.getMethod().equals(Method.HEAD.getMethod())){
                httpResponse = new HeadHttpResponse();
                doHead(outer);
            }

            // 关闭socket
            socket.close();
        }
    }

    @Override
    public void doGet(OutputStream outputStream) throws IOException {
//        File file = new File("web/Test");
//        InputStream fis = new FileInputStream(file);
//        byte[] buffer = new byte[(int) file.length()];
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        int n = 0;
//        while ((n = fis.read(buffer)) != -1) {
//            bos.write(buffer, 0, n);
//        }
        byte[] reponse = httpResponse.getResponse(httpRequest.getUrl());
        outputStream.write(reponse);
        outputStream.flush();
    }

    @Override
    public void doHead(OutputStream outputStream) throws IOException {
        byte[] reponse = httpResponse.getResponse(httpRequest.getUrl());
        outputStream.write(reponse);
        outputStream.flush();
    }
}
