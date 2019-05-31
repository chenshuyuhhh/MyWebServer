package com.chenshuyusc.myWeb.Response;

import java.io.*;

/**
 * 获得 Head 方法对应的 http 响应报文
 */
public class HeadHttpResponse extends HttpResponse {
    @Override
    public byte[] getResponse(String cases) throws IOException {
        switch (cases) {
            case "": {
                // 获取 html 文件的字节流
                System.out.println("获取默认文件 🤓 \n");
                InputStream is = this.getClass().getResourceAsStream("/index.html");
                byte[] bytes = getHead(getLength(is), StatusCode.ok);
                return bytes;
            }
            case "index.html": {
                System.out.println("获取系统中存在的文件 😄 \n");
                InputStream is = this.getClass().getResourceAsStream("/index.html");
                byte[] bytes = getHead(getLength(is), StatusCode.ok);
                return bytes;
            }
            case "happy.html": {
                System.out.println("获取系统中存在的文件 😄 \n");
                InputStream is = this.getClass().getResourceAsStream("/happy.html");
                byte[] bytes = getHead(getLength(is), StatusCode.ok);
                return bytes;
            }
            default: {
                System.out.println("获取系统中不存在的文件 😱 \n");
                InputStream is = this.getClass().getResourceAsStream("/index.html");
                byte[] bytes = getHead(getLength(is), StatusCode.notfound);
                return bytes;
            }
        }
    }

    private long getLength(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuffer s = new StringBuffer();
        String temp;
        while ((temp = br.readLine()) != null) {
            s.append(temp);
        }
        return s.toString().getBytes().length;
    }
}
