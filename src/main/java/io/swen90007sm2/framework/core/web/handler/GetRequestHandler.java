package io.swen90007sm2.framework.core.web.handler;

import com.alibaba.fastjson.JSON;
import io.swen90007sm2.framework.bean.R;
import io.swen90007sm2.framework.bean.RequestSessionBean;
import io.swen90007sm2.framework.bean.Worker;
import io.swen90007sm2.framework.common.util.ReflectionUtil;
import io.swen90007sm2.framework.common.util.UrlUtil;
import io.swen90007sm2.framework.core.ioc.BeanManager;
import io.swen90007sm2.framework.core.mvc.factory.ParameterResolverFactory;
import io.swen90007sm2.framework.core.mvc.resolver.IParameterResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class GetRequestHandler implements IRequestHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetRequestHandler.class);

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp, RequestSessionBean requestSessionBean) {
        String requestPath = req.getServletPath();

//        Map<String, String> queryParamMap = getQueryParams(requestPath);

        Enumeration<String> parameterNames = req.getParameterNames();
        Map<String, String> queryParameterMap = new HashMap<>();
        while (parameterNames.hasMoreElements()) {
            String queryParamName = parameterNames.nextElement();
            queryParameterMap.put(queryParamName, req.getParameter(queryParamName));
        }

        requestSessionBean.setQueryParameterMap(queryParameterMap);

        Worker worker = requestSessionBean.getWorkerNeeded();

        if (worker != null) {
            Method targetMethod = worker.getHandlerMethod();
            Parameter[] targetMethodParameters = targetMethod.getParameters();

            List<Object> paramObjList = new ArrayList<>();
            for (Parameter param : targetMethodParameters) {
                IParameterResolver parameterResolver = ParameterResolverFactory.get(param);
                if (parameterResolver != null) {
                    Object paramObj = parameterResolver.resolve(requestSessionBean, param);
                    paramObjList.add(paramObj);
                }
            }

            Object handlerBean = BeanManager.getBean(worker.getHandlerClazz());
            Object methodCallingResult = ReflectionUtil.invokeMethod(handlerBean, targetMethod, paramObjList.toArray());

            try {
                handleRestfulResponse((R) methodCallingResult, resp);
            } catch (IOException e) {
                LOGGER.info("handleRestfulResponse IO err: ", e);
                throw new RuntimeException(e);
            }

        } else {
            LOGGER.info("Worker miss matched in GetRequestHandler");
        }

    }

    /**
     * returns JSON
     */
    private void handleRestfulResponse(R responseObj, HttpServletResponse response) throws IOException {
        if (responseObj != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            String json = JSON.toJSON(responseObj).toString();
            writer.write(json);
            writer.flush();
            writer.close();
        }
    }


    /**
     * get the query parameters. e.g. /user?name=233, get Map {'name} -> '233'}
     */
    private Map<String, String> getQueryParams(String url) {
        return UrlUtil.getQueryParamMap(url);
    }
}
