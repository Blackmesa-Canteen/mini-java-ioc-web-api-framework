package io.swen90007sm2.framework.core.mvc.factory;

import io.swen90007sm2.framework.annotation.mvc.PathVariable;
import io.swen90007sm2.framework.annotation.mvc.RequestJsonBody;
import io.swen90007sm2.framework.annotation.mvc.QueryParam;
import io.swen90007sm2.framework.core.mvc.resolver.*;

import java.lang.reflect.Parameter;

/**
 * Factory returns correct param resolver based on method parameter's type
 *
 * @author xiaotian
 */
public class ParameterResolverFactory {
    public static IParameterResolver getResolverForParameter(Parameter parameter) {
        if (parameter.isAnnotationPresent(QueryParam.class)) {
            return new QueryParamParameterResolver();
        }
        if (parameter.isAnnotationPresent(PathVariable.class)) {
            return new PathVariableParameterResolver();
        }
        if (parameter.isAnnotationPresent(RequestJsonBody.class)) {
            return new RequestJsonParamResolver();
        }

        return new NoneAnnotatedParameterResolver();
    }
}
