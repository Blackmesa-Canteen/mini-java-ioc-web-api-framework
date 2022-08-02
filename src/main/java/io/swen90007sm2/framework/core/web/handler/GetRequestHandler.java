package io.swen90007sm2.framework.core.web.handler;

import io.swen90007sm2.framework.annotation.filter.AppliesRequestFilter;
import io.swen90007sm2.framework.bean.R;
import io.swen90007sm2.framework.bean.RequestSessionBean;
import io.swen90007sm2.framework.bean.Worker;
import io.swen90007sm2.framework.common.util.ReflectionUtil;
import io.swen90007sm2.framework.common.util.UrlUtil;
import io.swen90007sm2.framework.core.ioc.BeanManager;
import io.swen90007sm2.framework.core.mvc.factory.ParameterResolverFactory;
import io.swen90007sm2.framework.core.mvc.resolver.IParameterResolver;
import io.swen90007sm2.framework.core.web.filter.IRequestFilter;
import io.swen90007sm2.framework.core.web.filter.RequestFilterManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * handling Get request, returns json
 *
 * @author xiatian
 */
public class GetRequestHandler implements IRequestHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetRequestHandler.class);

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp, RequestSessionBean requestSessionBean) throws Exception {

        // get query parameters
        Enumeration<String> parameterNames = req.getParameterNames();
        Map<String, String> queryParameterMap = new HashMap<>();
        while (parameterNames.hasMoreElements()) {
            String queryParamName = parameterNames.nextElement();
            queryParameterMap.put(queryParamName, req.getParameter(queryParamName));
        }
        // init a query parameter map to hold incoming qurey parameter strings
        requestSessionBean.setQueryParameterMap(queryParameterMap);

        Worker worker = requestSessionBean.getWorkerNeeded();

        if (worker != null) {
            Method targetMethod = worker.getHandlerMethod();

            // perform request filter logic
            if (targetMethod.isAnnotationPresent(AppliesRequestFilter.class)) {
                AppliesRequestFilter annotation = targetMethod.getAnnotation(AppliesRequestFilter.class);
                String[] filterNames = annotation.filterNames();
                List<IRequestFilter> filterList = new LinkedList<>();
                for (String filterName : filterNames) {
                    IRequestFilter filterObj = RequestFilterManager.getRequestFilterByName(filterName);
                    if (filterObj != null) {
                        filterList.add(filterObj);
                    } else {
                        LOGGER.error("Filter [{}] not found on method [{}]",
                                filterName, targetMethod.getName());
                    }

                }

                for(IRequestFilter filter : filterList) {
                    boolean passed = filter.doFilter(req, resp);

                    if (!passed) {
                        LOGGER.info("Didn't pass request filter: [{}]", filter.getClass().getName());
                        IRequestHandler.closeRequestConnection(resp);
                        return;
                    }
                    LOGGER.info("passed request filter: [{}]", filter.getClass().getName());
                }
            }

            Parameter[] targetMethodParameters = targetMethod.getParameters();

            List<Object> paramObjList = new ArrayList<>();

            // traverse params in handler method, then generate correct param object for method calling
            for (Parameter param : targetMethodParameters) {
                // get correct resolver type for the param
                IParameterResolver parameterResolver = ParameterResolverFactory.getResolverForParameter(param);
                if (parameterResolver != null) {
                    Object paramObj = parameterResolver.resolve(requestSessionBean, param);
                    paramObjList.add(paramObj);

                }
            }

            Object handlerBean = BeanManager.getBeanFromBeanMapByClass(worker.getHandlerClazz());

            try {
                if (targetMethod.getReturnType().equals(void.class)) {
                    ReflectionUtil.invokeMethodWithoutResult(handlerBean, targetMethod, paramObjList.toArray());
                    IRequestHandler.closeRequestConnection(resp);
                } else {
                    Object methodCallingResult = ReflectionUtil.invokeMethod(handlerBean, targetMethod, paramObjList.toArray());
                    IRequestHandler.respondRequestWithJson((R) methodCallingResult, resp);
                }
            } catch (IOException e) {
                LOGGER.info("handleRestfulResponse IO err: ", e);
                throw new RuntimeException(e);
            }

        } else {
            LOGGER.info("Worker miss matched in GetRequestHandler");
        }

    }

    /**
     * get the query parameters. e.g. /user?name=233, get Map {'name} -> '233'}
     */
    private Map<String, String> parseQueryParamFromUrlToMap(String url) {
        return UrlUtil.getQueryParamMap(url);
    }
}
