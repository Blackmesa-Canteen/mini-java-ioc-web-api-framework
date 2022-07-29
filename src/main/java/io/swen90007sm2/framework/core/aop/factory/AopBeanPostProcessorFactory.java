package io.swen90007sm2.framework.core.aop.factory;

import io.swen90007sm2.framework.core.aop.processor.CgLibAopBeanProcessor;
import io.swen90007sm2.framework.core.aop.processor.IBeanPostProcessor;
import io.swen90007sm2.framework.core.aop.processor.JdkAopBeanProcessor;

/**
 * factor to generate correct proxy post processor for the target bean
 *
 * @author xiaotian
 */
public class AopBeanPostProcessorFactory {

    /**
     * get the bean post processor, if the bean implements interface, use JDK, else use CgLib
     */
    public static IBeanPostProcessor getCorrectBeanPostProcessor(Class<?> targetBean) {
        if (targetBean.isInterface() || targetBean.getInterfaces().length > 0) {
            return new JdkAopBeanProcessor();
        } else {
            return new CgLibAopBeanProcessor();
        }
    }
}
