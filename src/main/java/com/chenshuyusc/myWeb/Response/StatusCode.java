package com.chenshuyusc.myWeb.Response;

/**
 *  状态🐎
 */
public enum StatusCode {
    ok("200 OK"), error("400 Bad Request"), notfound("404 Not Found"), servererror("500 Internal Server Error");

    private String describe;

    StatusCode(String describe){
        this.describe = describe;
    }

    String getDescribe(){
        return  describe;
    }
}
