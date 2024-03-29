package io.swen90007sm2.framework.core.aop.processor;


import io.swen90007sm2.framework.core.aop.interceptor.AbstractInterceptor;
import io.swen90007sm2.framework.core.aop.proxy.ByteBuddyProxy;


/**
 * processor of bytebuddy proxy
 *
 * @author xiaotian
 */
public class ByteBuddyAopBeanProcessor extends AbstractAopBeanProcessor{

    @Override
    public Object enhanceBean(Object bean, AbstractInterceptor interceptor) {
        return ByteBuddyProxy.enhanceWithProxy(bean, interceptor);
    }
}
