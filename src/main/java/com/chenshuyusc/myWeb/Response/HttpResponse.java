package com.chenshuyusc.myWeb.Response;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * HTTP/1.1 200 OK
 * Date: Sat, 31 Dec 2005 23:59:59 GMT
 * Content-Type: text/html;charset=UTF-8
 * Content-Length: 122
 */

/**
 * Get 方法
 * 返回 http 响应报文
 */
public abstract class HttpResponse {
    // private String path;


    // private StatusCode statusCode;

    /**
     * 根据情况返回 http 响应报文
     *
     * @param cases 在浏览器中输入的网址从端口号后面开始
     * @return
     */
    public abstract byte[] getResponse(String cases) throws IOException;

    /**
     * 根据文件名获得返回给客户端的字节流
     *
     * @param file
     * @param statusCode
     * @return
     * @throws IOException
     */
    static byte[] getHttp(File file, StatusCode statusCode) throws IOException {
        InputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int n = 0;
        while ((n = fis.read(buffer)) != -1) {
            bos.write(buffer, 0, n);
        }
        byte[] content = bos.toByteArray();
        // 将 http 头部和内容合并
        return merge(getHead(file.length(), statusCode), content);
    }

    /**
     * 为了解决打包成 jar 不能直接读取文件的问题
     * @param fis
     * @param statusCode
     * @return
     * @throws IOException
     */
    static byte[] getHttp(InputStream fis, StatusCode statusCode) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        StringBuffer s = new StringBuffer();
        String temp;
        while ((temp = br.readLine()) != null) {
            s.append(temp);
        }
        byte[] content = s.toString().getBytes();
        // 将 http 头部和内容合并
        return merge(getHead(content.length, statusCode), content);
    }

    /**
     * 获得 http 响应报文的头部
     *
     * @param length 文件长度
     * @param statusCode 状态🐎
     * @return
     */
    static byte[] getHead(long length, StatusCode statusCode) {
        // date 格式的字符串
        SimpleDateFormat greenwichDate = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        String date = greenwichDate.format(Calendar.getInstance().getTime());
        String result = "HTTP/1.1 " + statusCode.getDescribe() + "\nDate: " + date + "\nContent-Type: text/html;charset=UTF-8\n" + "Content-Length: " + length+ "\n\n";
        System.out.println("[ Response ] HTTP响应报文的头部:");
        System.out.println(result);
        return result.getBytes();
    }

    /**
     * 合并 byte 数组
     *
     * @param head
     * @param content
     * @return
     */
    private static byte[] merge(byte[] head, byte[] content) {
        byte[] result = new byte[head.length + content.length];

        for (int i = 0; i < head.length; i++) {
            result[i] = head[i];
        }

        for (int i = head.length; i < head.length + content.length; i++) {
            result[i] = content[i - head.length];
        }
        return result;
    }
}

