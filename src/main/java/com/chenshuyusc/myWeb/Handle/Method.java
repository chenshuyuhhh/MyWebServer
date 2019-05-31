package com.chenshuyusc.myWeb.Handle;

public enum Method {
    GET("GET"),HEAD("HEAD");

    private String method;

    Method(String method){
        this.method = method;
    }

    /**
     * 只有 get 方法，不可修改，常量
     * @return
     */
    public String getMethod() {
        return method;
    }
}
