package io.swen90007sm2.framework.core.aop.processor;

import io.swen90007sm2.framework.core.aop.interceptor.AbstractInterceptor;
import io.swen90007sm2.framework.core.aop.proxy.JdkProxy;

public class JdkAopBeanProcessor extends AbstractAopBeanProcessor{

    @Override
    public Object enhanceBean(Object bean, AbstractInterceptor interceptor) {
        return JdkProxy.enhanceWithProxy(bean, interceptor);
    }
}
