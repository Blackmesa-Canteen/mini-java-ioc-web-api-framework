package io.swen90007sm2.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation used in a class in the handler level.
 *
 *  level structure:  Handler -> Service -> database level
 *
 * @author Xiaotian
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Handler {

    /**
     * Request URL/Path
     * @return String
     */
    String path() default "";
}