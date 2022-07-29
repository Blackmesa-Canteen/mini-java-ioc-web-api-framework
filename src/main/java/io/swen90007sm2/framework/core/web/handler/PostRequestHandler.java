package io.swen90007sm2.framework.core.web.handler;

import io.swen90007sm2.framework.bean.R;
import io.swen90007sm2.framework.bean.RequestSessionBean;
import io.swen90007sm2.framework.bean.Worker;
import io.swen90007sm2.framework.common.util.ReflectionUtil;
import io.swen90007sm2.framework.core.ioc.BeanManager;
import io.swen90007sm2.framework.core.mvc.factory.ParameterResolverFactory;
import io.swen90007sm2.framework.core.mvc.resolver.IParameterResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * handling POST, PUT, DELETE request, returns json
 *
 * @author xiatian
 */
public class PostRequestHandler implements IRequestHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostRequestHandler.class);
    public static final String APPLICATION_JSON = "application/json";

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp, RequestSessionBean requestSessionBean) {
        String requestPath = req.getServletPath();

        if (!req.getContentType().equals(APPLICATION_JSON)) {
            LOGGER.error("only receive application/json type data in POST/PUT/DELETE, err: " + requestPath);
            throw new IllegalArgumentException("only receive application/json type data in POST/PUT/DELETE");
        }

        Worker worker = requestSessionBean.getWorkerNeeded();
        if (worker != null) {
            String jsonStr = parseJsonString(req);
            requestSessionBean.setJsonBodyString(jsonStr);
            Method targetMethod = worker.getHandlerMethod();

            Parameter[] targetMethodParameters = targetMethod.getParameters();

            List<Object> paramObjList = new ArrayList<>();
            for (Parameter parameter : targetMethodParameters) {
                IParameterResolver parameterResolver = ParameterResolverFactory.get(parameter);
                if (parameterResolver != null) {
                    Object param = parameterResolver.resolve(requestSessionBean, parameter);
                    paramObjList.add(param);
                }
            }

            Object handlerBean = BeanManager.getBean(worker.getHandlerClazz());
            Object methodCallingResult = ReflectionUtil.invokeMethod(handlerBean, targetMethod, paramObjList.toArray());

            try {
                IRequestHandler.respondRequestWithJson((R) methodCallingResult, resp);
            } catch (IOException e) {
                LOGGER.info("handleRestfulResponse IO err: ", e);
                throw new RuntimeException(e);
            }


        } else {
            LOGGER.info("Worker miss matched in GetRequestHandler");
        }
    }

    /**
     * doPost will read JSON from request body to get param
     */
    private static String parseJsonString(HttpServletRequest request) {

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

        return sb.toString();
    }
}
