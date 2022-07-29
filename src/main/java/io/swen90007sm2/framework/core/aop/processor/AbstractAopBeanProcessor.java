package io.swen90007sm2.framework.core.aop.processor;


import io.swen90007sm2.framework.core.aop.InterceptorManager;
import io.swen90007sm2.framework.core.aop.interceptor.AbstractInterceptor;

import java.util.List;

/**
 * Aop bean post processor, decorate beam with interceptors
 * @author xiaotian
 */
public abstract class AbstractAopBeanProcessor implements IBeanPostProcessor{

    // decorate the target bean level by level with interceptor
    @Override
    public Object postProcessToBean(Object bean) {
        Object enhancedBean = bean;
        List<AbstractInterceptor> interceptorList = InterceptorManager.getInterceptorList();
        for (AbstractInterceptor interceptor : interceptorList) {
            if (interceptor.supports(bean)) {
                enhancedBean = enhanceBean(enhancedBean, interceptor);
            }
        }
        return enhancedBean;
    }

    // please design own enhance logic, now has 2 proxy, JDK and CgLib
    public abstract Object enhanceBean(Object bean, AbstractInterceptor interceptor);
}
