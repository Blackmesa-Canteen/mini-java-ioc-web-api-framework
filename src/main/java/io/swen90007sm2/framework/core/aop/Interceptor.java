package io.swen90007sm2.framework.core.aop;


import io.swen90007sm2.framework.bean.MethodCalling;

/**
 * interceptor super class
 *
 * @author xiaotian
 */
public abstract class Interceptor {

    private int order = -1;

    /**
     * see whether this interceptor supports this bean
     * @param bean targetBean
     * @return true is interceptor supports the bean
     */
    public boolean supports(Object bean) {
        return false;
    }

    /**
     * override this method to enhance the Methodcalling.
     */
    public abstract Object intercept(MethodCalling methodCalling);

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
