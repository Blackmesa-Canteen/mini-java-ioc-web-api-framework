package io.swen90007sm2.core.helper;

import io.swen90007sm2.bean.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * helper for handling HttpServletRequest
 * @author xiaotian
 */
public class RequestHelper {

    public static RequestParam createParamWithServletRequest(HttpServletRequest request) throws IOException {
        Enumeration<String> parameterNames = request.getParameterNames();
        // if there is no param in this request
        if (!parameterNames.hasMoreElements()) return null;

        // get parameter from Servlet Request
        Map<String, Object> paramMap = new HashMap<>();
        while (parameterNames.hasMoreElements()) {
            String paramKey = parameterNames.nextElement();
            String paramValue = request.getParameter(paramKey);

            paramMap.put(paramKey, paramValue);
        }

        return new RequestParam(paramMap);
    }
}
