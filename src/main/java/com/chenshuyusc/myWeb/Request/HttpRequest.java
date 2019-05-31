package com.chenshuyusc.myWeb.Request;

/**
 * http 请求报文对应的实体
 */
public class HttpRequest {
    private String method;
    private String url;

    public HttpRequest(){

    }

    public HttpRequest(String method, String url) {
        this.method = method;
        this.url = url;
    }

    /**
     * 根据的 http 请求报文的头部获得请求方法，和请求地址
     * @param head
     */
    public HttpRequest(String head){
        this.method = head.split(" ")[0];
        // 从 1 开始是为了去掉/
        this.url = head.split(" ")[1].substring(1);
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }
}
