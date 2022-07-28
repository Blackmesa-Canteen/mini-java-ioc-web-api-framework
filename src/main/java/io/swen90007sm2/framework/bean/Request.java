package io.swen90007sm2.framework.bean;

import java.util.Objects;

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

    /**
     * need to override the equals logic, because it will be used in HandlerManager
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return requestMethod.equals(request.requestMethod) && requestPath.equals(request.requestPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestMethod, requestPath);
    }
}
