package io.swen90007sm2.framework.core;

import io.swen90007sm2.framework.core.helper.*;
import io.swen90007sm2.framework.core.util.ClassLoadUtil;

/**
 * @author xiaotain
 */
public class AppContextLoader {

    /**
     * Load 5 helper class, run static blocks in them.
     */
    public static void initAppContext() {
        Class<?>[] classLoadList = {
                ConfigFileReader.class,
                ClassManager.class,
                BeanManager.class,
                InjectionHelper.class,
                HandlerManager.class
        };

        for (Class<?> clazz : classLoadList) {
            ClassLoadUtil.loadClass(clazz.getName());
        }
    }
}