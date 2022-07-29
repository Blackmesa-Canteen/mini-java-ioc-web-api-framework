package io.swen90007sm2.framework.core;

import io.swen90007sm2.framework.IocWebApiApplication;
import io.swen90007sm2.framework.core.aop.InterceptorManager;
import io.swen90007sm2.framework.core.config.ConfigFileManager;
import io.swen90007sm2.framework.core.ioc.BeanManager;
import io.swen90007sm2.framework.core.ioc.ClassManager;
import io.swen90007sm2.framework.core.ioc.InjectionHelper;
import io.swen90007sm2.framework.core.mvc.HandlerManager;
import io.swen90007sm2.framework.common.util.ClassLoadUtil;
import io.swen90007sm2.framework.db.DbConnectionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Application context loader
 * init the framework, perform class scan, and dependency injection
 *
 * @author xiaotain
 * @author tyshawnlee https://github.com/tyshawnlee/handwritten-mvc
 */
public class  AppContextLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppContextLoader.class);

    /**
     * Load 5 helper class, run static blocks in them.
     */
    public static void initAppContext() {

        // the order should not be changed!
        Class<?>[] classLoadList = {
                ConfigFileManager.class,
                ClassManager.class,
                BeanManager.class,
                InterceptorManager.class,
                InjectionHelper.class,
                HandlerManager.class,
                DbConnectionHelper.class
        };

        // run static blocks one by one
        for (Class<?> clazz : classLoadList) {
            ClassLoadUtil.loadClass(clazz.getName());
            LOGGER.info("context " + clazz.getName() + " loaded.");
        }

        // perform AOP in BEAN_MAP. bean map includes Component (Handler + Blo + Dao)
        BeanManager.performBeanPostProcessorsToBeanMap();

        LOGGER.info("IoC Container is ready to go.");
    }
}
