package io.swen90007sm2.framework.core.mvc.factory;

import io.swen90007sm2.framework.annotation.mvc.PathVariable;
import io.swen90007sm2.framework.annotation.mvc.RequestJsonParam;
import io.swen90007sm2.framework.annotation.mvc.RequestParam;
import io.swen90007sm2.framework.core.mvc.resolver.IParameterResolver;
import io.swen90007sm2.framework.core.mvc.resolver.PathVariableParameterResolver;
import io.swen90007sm2.framework.core.mvc.resolver.RequestJsonParamResolver;
import io.swen90007sm2.framework.core.mvc.resolver.RequestParamParameterResolver;

import java.lang.reflect.Parameter;

public class ParameterResolverFactory {
    public static IParameterResolver get(Parameter parameter) {
        if (parameter.isAnnotationPresent(RequestParam.class)) {
            return new RequestParamParameterResolver();
        }
        if (parameter.isAnnotationPresent(PathVariable.class)) {
            return new PathVariableParameterResolver();
        }
        if (parameter.isAnnotationPresent(RequestJsonParam.class)) {
            return new RequestJsonParamResolver();
        }
        return null;
    }
}
