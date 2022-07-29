package io.swen90007sm2.framework.core.aop.processor;

import io.swen90007sm2.framework.core.aop.interceptor.AbstractInterceptor;
import io.swen90007sm2.framework.core.aop.proxy.CglibProxy;

/**
 * cglib enhancer
 *
 * @author xiaotian
 */
public class CgLibAopBeanProcessor extends AbstractAopBeanProcessor {
    @Override
    public Object enhanceBean(Object bean, AbstractInterceptor interceptor) {
        return CglibProxy.enhanceWithProxy(bean, interceptor);
    }
}
