package io.swen90007sm2.framework.annotation.mvc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * annotation for post request, it can be used to upload files via post
 * e.g. public R upload(@RequestParam(value="img")MultipartFile img, HttpServletRequest request)
 *
 * @author Xiaotian
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParam {
    String value();
}
