package io.swen90007sm2.core.bean;

/**
 * a bean used to seal up incoming request
 *
 * @author xiaotian
 */
public class Request {

    /*
        request method : GET, POST...
     */
    private String requestMethod;

    /*
        request path: "/xxx"
     */
    private String requestPath;

    public Request() {
    }

    public Request(String requestMethod, String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }
}
