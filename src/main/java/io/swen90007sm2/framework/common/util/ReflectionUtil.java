package io.swen90007sm2.framework.common.util;

import io.swen90007sm2.framework.exception.RequestException;
import io.swen90007sm2.framework.exception.ValidationException;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Reflection Utils, including instantiate new object, invoke a method and so on, based on class object loaded by ClassLoadUtil.
 *
 * @author Xiaotian
 */
public class ReflectionUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * instantiate an object based a class object
     */
    public static Object newInstance(Class<?> clazz) {
        Object instance;
        try {
            instance = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            LOGGER.error("new instance failure", e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * instantiate an object with the className, and initialize it (set default value, run static code blocks)
     */
    public static Object newInstance(String className) {
        Class<?> clazz = ClassLoadUtil.loadClass(className);
        return newInstance(clazz);
    }

    /**
     * invoke a method in an object
     */
    public static Object invokeMethod(Object object, Method method, Object... args) {
        Object result;
        try {
            method.setAccessible(true);
            result = method.invoke(object, args);
        } catch (Throwable e) {
            if (e.getCause() != null && e.getCause() instanceof ConstraintViolationException) {
                LOGGER.error("invoke method param validation, exception: " + e.toString());
                throw (ConstraintViolationException) e.getCause();
            } else if (e.getCause() instanceof RequestException) {
                throw (RequestException) e.getCause();
            }
            LOGGER.error("invoke method failure, exception: ", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * brutally set a field in an object with reflection
     */
    public static void setField(Object obj, Field field, Object value) {
        try {
            field.setAccessible(true); // overcome private bug
            field.set(obj, value);
        } catch (Exception e) {
            LOGGER.error("set field failure, exception: ", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * get all methods from this class
     */
    public static Method[] getMethods(Class<?> clazz) {
        return clazz.getDeclaredMethods();
    }

    /**
     * Get the implementation classes of the interface
     */
    public static <T> Set<Class<? extends T>> getSubClass(String packageName, Class<T> interfaceClass) {
        Reflections reflections = new Reflections(packageName);
        return reflections.getSubTypesOf(interfaceClass);

    }
}
