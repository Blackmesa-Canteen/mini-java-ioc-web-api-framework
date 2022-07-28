package io.swen90007sm2.framework.core;

import io.swen90007sm2.framework.core.config.ConfigFileReader;
import io.swen90007sm2.framework.core.ioc.BeanManager;
import io.swen90007sm2.framework.core.ioc.ClassManager;
import io.swen90007sm2.framework.core.ioc.InjectionHelper;
import io.swen90007sm2.framework.core.mvc.HandlerManager;
import io.swen90007sm2.framework.common.util.ClassLoadUtil;

/**
 *
 * Application context loader
 * init the framework, perform class scan, and dependency injection
 *
 * @author xiaotain
 */
public class AppContextLoader {

    /**
     * Load 5 helper class, run static blocks in them.
     */
    public static void initAppContext() {

        // the order should not be changed!
        Class<?>[] classLoadList = {
                ConfigFileReader.class,
                ClassManager.class,
                BeanManager.class,
                InjectionHelper.class,
                HandlerManager.class
        };

        // run static blocks one by one
        for (Class<?> clazz : classLoadList) {
            ClassLoadUtil.loadClass(clazz.getName());
        }
    }
}
