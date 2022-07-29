package io.swen90007sm2.framework.annotation.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Aspect for AOP.
 *
 * (Not Supported right now, can only get Component beans that marked with @Aspect.)
 * @author xiaotian
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Aspect {
    String value() default "";
}
