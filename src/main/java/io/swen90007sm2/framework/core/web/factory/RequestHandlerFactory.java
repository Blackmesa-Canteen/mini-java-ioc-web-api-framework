package io.swen90007sm2.framework.core.web.factory;

import io.swen90007sm2.framework.common.constant.RequestMethod;
import io.swen90007sm2.framework.core.web.handler.IRequestHandler;
import io.swen90007sm2.framework.core.web.handler.PostRequestHandler;
import io.swen90007sm2.framework.core.web.handler.GetRequestHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * factor for instantiate request handler to different request method
 * @author xiaotian
 */
public class RequestHandlerFactory {
    private static final Map<String, IRequestHandler> REQUEST_MAP = new HashMap<>();

    static {
        REQUEST_MAP.put(RequestMethod.GET.name(), new GetRequestHandler());
        REQUEST_MAP.put(RequestMethod.POST.name(), new PostRequestHandler());
        REQUEST_MAP.put(RequestMethod.PUT.name(), new PostRequestHandler());
        REQUEST_MAP.put(RequestMethod.DELETE.name(), new PostRequestHandler());
    }

    public static IRequestHandler get(String requestMethodText) {
        return REQUEST_MAP.get(requestMethodText);
    }


}
