package io.swen90007sm2.framework.core.web.factory;

import io.swen90007sm2.framework.bean.R;
import org.apache.http.HttpStatus;

/**
 * factory for generate R object
 *
 * @author xiaotian
 */
public class ResponseFactory {

    /**
     * returns response with 200 status code
     */
    public static R getSuccessResponseBean(Object data) {
        return R.ok().setData(data);
    }

    /**
     * returns response R with 400 status code
     */
    public static R getRequestErrorResponseBean(String msg) {
        return R.error(HttpStatus.SC_BAD_REQUEST, msg);
    }

    /**
     * returns response R with 404 status code
     */
    public static R getResourceNotFoundResponseBean(String msg) {
        return R.error(HttpStatus.SC_NOT_FOUND, msg);
    }

    /**
     * returns response R with 500 status code
     */
    public static R getServerInternalErrorResponseBean(String msg) {
        return R.error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static R getErrorResponseBean(int code, String msg) {
        return R.error(code, msg);
    }
}
