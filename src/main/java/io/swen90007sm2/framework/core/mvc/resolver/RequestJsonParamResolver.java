package io.swen90007sm2.framework.core.mvc.resolver;

import com.alibaba.fastjson.JSON;
import io.swen90007sm2.framework.annotation.mvc.RequestJsonBody;
import io.swen90007sm2.framework.bean.RequestSessionBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Parameter;

public class RequestJsonParamResolver implements IParameterResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestJsonParamResolver.class);

    @Override
    public Object resolve(RequestSessionBean requestSessionBean, Parameter parameter) {
        Object res = null;

        RequestJsonBody anno = parameter.getDeclaredAnnotation(RequestJsonBody.class);
        if (anno != null) {
            try {
                res = JSON.parseObject(requestSessionBean.getJsonBodyString(), parameter.getType());
            } catch (Exception e) {
                LOGGER.error("parse JSON body exception: ", e);
            }
        }

        return res;
    }
}
