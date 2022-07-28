package io.swen90007sm2.framework.core.mvc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swen90007sm2.framework.bean.RequestSessionBean;
import io.swen90007sm2.framework.common.constant.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * helper for handling HttpServletRequest
 * @author xiaotian
 */
public class RequestHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestHelper.class);

    public static RequestSessionBean genParamWithServletRequest(HttpServletRequest request) {
        Map<String, Object> paramMap;

        if (request.getMethod().toUpperCase().equals(RequestMethod.GET.name())) {
            // get GET parameter from Servlet Request
            // params in url
            Enumeration<String> parameterNames = request.getParameterNames();
            // if there is no param in this request
            if (!parameterNames.hasMoreElements()) return null;

            paramMap = doGet(request, parameterNames);

        } else {
            // get POST, PUT, DELETE from request
            // params in body as JSON
            paramMap = doPost(request);
        }

        return new RequestSessionBean(paramMap);
    }

    /**
     * doPost will read JSON from request body to get param
     */
    private static Map<String, Object> doPost(HttpServletRequest request) {
        Map<String, Object> paramMap = new HashMap<>();

        StringBuilder sb = new StringBuilder();
        String line = null;

        try {
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            LOGGER.error("doPost exception: ", e);
            throw new RuntimeException(e);
        }

        try {
            JSONObject jsonObject = JSON.parseObject(sb.toString());
            for (String key : jsonObject.keySet()) {
                paramMap.put(key, jsonObject.get(key));
            }
        } catch (Exception e) {
            LOGGER.error("doPost exception: ", e);
            throw new RuntimeException(e);
        }

        return paramMap;
    }

    /**
     * doGet will read URL to get param
     */
    private static Map<String, Object> doGet(HttpServletRequest request, Enumeration<String> parameterNames) {
        Map<String, Object> paramMap = new HashMap<>();

        while (parameterNames.hasMoreElements()) {
            String paramKey = parameterNames.nextElement();
            String paramValue = request.getParameter(paramKey);

            paramMap.put(paramKey, paramValue);
        }
        return paramMap;
    }
}
