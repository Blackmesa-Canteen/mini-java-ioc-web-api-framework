package io.swen90007sm2.framework.core.web.filter;

import io.swen90007sm2.framework.annotation.filter.RequestFilter;
import io.swen90007sm2.framework.common.util.ReflectionUtil;
import io.swen90007sm2.framework.core.ioc.BeanManager;
import io.swen90007sm2.framework.core.ioc.ClassManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 996Worker
 * @description a manager holds all filter
 * @create 2022-08-02 22:46
 */
public class RequestFilterManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanManager.class);

    private static Map<String, IRequestFilter> FILTER_MAP;

    static {
        FILTER_MAP = new HashMap<>();
        Set<Class<?>> filterAnnotatedClassSet = ClassManager.getFilterAnnotatedClassSet();
        for (Class<?> filterClass : filterAnnotatedClassSet) {
            IRequestFilter filterObj = (IRequestFilter) ReflectionUtil.genNewInstanceByClass(filterClass);
            RequestFilter annotation = filterClass.getAnnotation(RequestFilter.class);
            String filterName = annotation.name();

            if (FILTER_MAP.containsKey(filterName)) {
                LOGGER.error("Duplicated request filter name!!! Ignored");
            } else {
                FILTER_MAP.put(filterName, filterObj);
            }
        }
    }

    public static IRequestFilter getRequestFilterByName(String filterName) {
        return FILTER_MAP.get(filterName);
    }
}