package io.swen90007sm2.framework.core.aop;

import io.swen90007sm2.framework.common.util.ReflectionUtil;
import io.swen90007sm2.framework.core.aop.interceptor.AbstractInterceptor;
import io.swen90007sm2.framework.core.aop.interceptor.validation.JSR303ValidationInterceptor;
import io.swen90007sm2.framework.core.config.ConfigFileManager;
import io.swen90007sm2.framework.exception.InternalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * helper to load all interceptors.
 *
 * @author xiaotian
 * @author shuang.kou:https://github.com/Snailclimb/jsoncat
 */
public class InterceptorManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterceptorManager.class);

    private static List<AbstractInterceptor> INTERCEPTOR_LIST;

    static {
        INTERCEPTOR_LIST = new ArrayList<>();
        String basePackage = ConfigFileManager.getBasePackageName();

        // get subclasses that implmented Interceptor abstract class
        Set<Class<? extends AbstractInterceptor>> interceptorClasses = ReflectionUtil.getSubClass(basePackage, AbstractInterceptor.class);

//        // get @Aspect class
//        Set<Class<?>> aspectClasses = ClassManager.getAspectClassSet();

        // traverse all intercepter classes and instantiate them
        interceptorClasses.forEach(interceptorClass -> {
            try {
                INTERCEPTOR_LIST.add(interceptorClass.getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                LOGGER.error("init constructor for interceptor exception: " + interceptorClass.getName());
                LOGGER.error("exception ",e);
                throw new InternalException("Server error, try again later");
            }
        });

        // add jsr303 interceptor
        INTERCEPTOR_LIST.add(new JSR303ValidationInterceptor());

        // sort by order
        INTERCEPTOR_LIST = INTERCEPTOR_LIST.stream().sorted(
                Comparator.comparing(AbstractInterceptor::getOrder)
        ).collect(Collectors.toList());
    }

    public static List<AbstractInterceptor> getInterceptorList() {
        return INTERCEPTOR_LIST;
    }
}
