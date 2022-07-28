package io.swen90007sm2.framework.core.mvc.resolver;

import io.swen90007sm2.framework.annotation.mvc.RequestParam;
import io.swen90007sm2.framework.bean.RequestSessionBean;
import io.swen90007sm2.framework.common.util.ObjectUtil;

import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * convert incoming query string to annotation's declared type
 *
 * @author xiaotian
 */
public class RequestParamParameterResolver implements IParameterResolver {
    @Override
    public Object resolve(RequestSessionBean requestSessionBean, Parameter parameter) {
        RequestParam requestParamAnno = parameter.getDeclaredAnnotation(RequestParam.class);
        String targetParamName = requestParamAnno.value();

        // TODO map 没初始化
        Map<String, String> queryParameterMap = requestSessionBean.getQueryParameterMap();
        String targetParamValue = queryParameterMap.get(targetParamName);

        if (targetParamValue == null) {
            if (requestParamAnno.require() && requestParamAnno.defaultValue().isEmpty()) {
                throw new IllegalArgumentException("The specified parameter " + targetParamName + " can not be null!");
            } else {
                targetParamValue = requestParamAnno.defaultValue();
            }
        }

        return ObjectUtil.convertString2Object(targetParamValue, parameter.getType());
    }
}
