package io.swen90007sm2.framework.annotation.mvc;

import io.swen90007sm2.framework.common.constant.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * an annotation used in methods in Handler classes.
 *
 * @author Xiaotian
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HandlesRequest {
    /**
     * Request URL/Path
     * @return String
     */
    String path() default "";

    /**
     * Request method, such as GET, POST and so on
     * @return RequestMethod Enum
     */
    RequestMethod method() default RequestMethod.GET;
}
