package io.swen90007sm2.framework.core.mvc.resolver;

import com.alibaba.fastjson.JSON;
import io.swen90007sm2.framework.annotation.mvc.RequestJsonBody;
import io.swen90007sm2.framework.bean.RequestSessionBean;
import io.swen90007sm2.framework.exception.RequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Parameter;

/**
 * convert incoming request json body into a specific entity as the same as parameter type
 *
 * @author xiaotian
 */
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
                throw new RequestException("JSON body not recognized");
            }
        }

        return res;
    }
}
