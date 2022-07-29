package io.swen90007sm2.framework.annotation.mvc;

import io.swen90007sm2.framework.annotation.ioc.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * an annotation used in data access level.
 *
 * @author xiaotian
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface Dao {
}
