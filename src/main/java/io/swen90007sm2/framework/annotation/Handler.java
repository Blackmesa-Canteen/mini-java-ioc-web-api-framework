package io.swen90007sm2.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation used in a class in the handler level.
 *
 *  level structure:  Handler -> Blo -> Dao
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
