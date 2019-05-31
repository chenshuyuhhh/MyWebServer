package com.chenshuyusc.myWeb;

import com.chenshuyusc.myWeb.MyServer.WebServer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true){
            System.out.println("你是否想启动本服务器 😜 [Y/N]");
            Scanner scanner = new Scanner(System.in);
            if (!scanner.next().equals("Y")){
                System.exit(0);
            }
            int port;
            System.out.println("请输入你想要🔗的端口号:( 建议输入比8000更大的端口号 )");
            port = scanner.nextInt();
            System.out.println();

            WebServer webServer = new WebServer(port);
            webServer.start();
        }
    }
}
