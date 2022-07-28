package io.swen90007sm2.framework.core.web.servlet;

import com.alibaba.fastjson.JSON;
import io.swen90007sm2.framework.bean.R;
import io.swen90007sm2.framework.bean.RequestParam;
import io.swen90007sm2.framework.bean.Worker;
import io.swen90007sm2.framework.core.helper.BeanManager;
import io.swen90007sm2.framework.core.helper.HandlerManager;
import io.swen90007sm2.framework.core.helper.RequestHelper;
import io.swen90007sm2.framework.core.util.ReflectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

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

        // map worker based on request
        Worker worker = HandlerManager.getWorker(requestMethod, requestPath);
        if (worker != null) {
            Class<?> handlerClass = worker.getHandlerClazz();
            Object handlerBean = BeanManager.getBean(handlerClass);

            RequestParam requestParam = RequestHelper.genParamWithServletRequest(req);
            Method handlerMethod = worker.getHandlerMethod();

            Object methodCallingResult;
            // call the specific method to handle the request
            if (requestParam != null && !requestParam.isEmptyParam()) {
                methodCallingResult = ReflectionUtil.invokeMethod(handlerBean, handlerMethod, requestParam);
            } else {
                methodCallingResult = ReflectionUtil.invokeMethod(handlerBean, handlerMethod);
            }

            // response to the client
            handleRestfulResponse((R) methodCallingResult, resp);
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
}
