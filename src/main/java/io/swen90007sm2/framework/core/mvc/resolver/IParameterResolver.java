package io.swen90007sm2.framework.core.mvc.resolver;

import io.swen90007sm2.framework.bean.RequestSessionBean;

import java.lang.reflect.Parameter;

public interface IParameterResolver {

    Object resolve(RequestSessionBean requestSessionBean, Parameter parameter) throws Exception;
}
