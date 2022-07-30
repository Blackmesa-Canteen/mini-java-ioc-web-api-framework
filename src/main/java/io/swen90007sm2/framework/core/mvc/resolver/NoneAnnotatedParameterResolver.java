package io.swen90007sm2.framework.core.mvc.resolver;

import io.swen90007sm2.framework.bean.RequestSessionBean;

import java.lang.reflect.Parameter;

/**
 * none annotated param
 *
 * @author xiaotain
 */
@Deprecated
public class NoneAnnotatedParameterResolver implements IParameterResolver {
    @Override
    public Object resolve(RequestSessionBean requestSessionBean, Parameter parameter) throws Exception {
        return null;
    }
}
