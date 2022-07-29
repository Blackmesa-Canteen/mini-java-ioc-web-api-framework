package io.swen90007sm2.framework.core.aop.processor;

/**
 * An interface to do post process to existing bean, such as aop enhance
 *
 * @author xiaotian
 */
public interface IBeanPostProcessor {

    // default doing nothing
    default Object postProcessToBean(Object bean) {
        return bean;
    }
}
