package io.swen90007sm2.framework.core.web.servlet;

import com.alibaba.fastjson.JSON;
import io.swen90007sm2.framework.bean.R;
import io.swen90007sm2.framework.bean.RequestSessionBean;
import io.swen90007sm2.framework.bean.Worker;
import io.swen90007sm2.framework.core.ioc.BeanManager;
import io.swen90007sm2.framework.core.mvc.HandlerManager;
import io.swen90007sm2.framework.core.mvc.RequestHelper;
import io.swen90007sm2.framework.common.util.ReflectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * servlet need to be run at the start of the application
 * This is a RESTful JSON servlet
 * @author xiaotian
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class MyDispatcherServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyDispatcherServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestMethod = req.getMethod().toUpperCase();
        String requestPath = req.getServletPath();
        req.setCharacterEncoding("utf-8");

        LOGGER.info("incoming request: " + requestMethod + " , path: " + requestPath);

        // handle the case: "/contextXXX/userList"
//        String[] splits = requestPath.split("/");
//        if (splits.length > 2) {
//            requestPath = "/" + splits[2];
//        }

        // generate request-response session bean for this new serving session
        RequestSessionBean sessionBean = genRequestSessionBean(requestMethod, requestPath);

        if (sessionBean.getWorkerNeeded() != null) {
            Class<?> handlerClass = worker.getHandlerClazz();
            Object handlerBean = BeanManager.getBean(handlerClass);

            RequestSessionBean requestSessionBean = RequestHelper.genParamWithServletRequest(req);
            Method handlerMethod = worker.getHandlerMethod();

            Object methodCallingResult;
            // call the specific method to handle the request
            if (requestSessionBean != null && !requestSessionBean.isEmptyParam()) {
                methodCallingResult = ReflectionUtil.invokeMethod(handlerBean, handlerMethod, params);
            } else {
                methodCallingResult = ReflectionUtil.invokeMethod(handlerBean, handlerMethod);
            }

            // response to the client
            handleRestfulResponse((R) methodCallingResult, resp);
        } else {
            LOGGER.info("mismatched a request: [" + requestMethod + "] " + requestPath);
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

    public static RequestSessionBean genRequestSessionBean(String requestMethodText, String requestPath) {
        RequestSessionBean requestSessionBean = new RequestSessionBean();

        HandlerManager.getRequestMap().forEach((requestBean, workerBean) -> {
            Pattern pattern = Pattern.compile(requestBean.getRequestPathPattern());
            String urlDefinedInHandler = requestBean.getRequestPath();

            boolean isUrlMatched = pattern.matcher(requestPath).find();
            boolean isRequestMethodMatched = requestBean.getRequestMethod().equals(requestMethodText)

            if (isRequestMethodMatched && isUrlMatched) {
                requestSessionBean.setPathVariableParameterMap(
                        parseIncomingPath2pathVariableMap(requestPath, urlDefinedInHandler)
                );

                requestSessionBean.setWorkerNeeded(workerBean);
            }
        });

        return requestSessionBean;
    }

    private static Map<String, String> parseIncomingPath2pathVariableMap(String requestPath, String url) {
        String[] requestParams = requestPath.split("/");
        String[] urlParams = url.split("/");
        Map<String, String> res = new HashMap<>();
        for (int i = 1; i < urlParams.length; i++) {
            res.put(urlParams[i].replace("{", "").replace("}", ""), requestParams[i]);
        }
        return res;
    }
}
