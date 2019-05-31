package com.chenshuyusc.myWeb.Handle;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 可以处理 GET 请求和 HEAD 请求
 */
public interface Handle {
    void doGet(OutputStream out) throws IOException;
    void doHead(OutputStream out) throws IOException;
}
