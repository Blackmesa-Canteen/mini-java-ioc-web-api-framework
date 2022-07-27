package io.swen90007sm2.core.helper;

import io.swen90007sm2.annotation.Handler;
import io.swen90007sm2.annotation.Service;
import io.swen90007sm2.core.util.ClassLoadUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * a helper to hold all class objects
 *
 * @author xiaotian
 */
public class ClassManager {

    /**
     * a set holds all class object of this project
     */
    private static final Set<Class<?>> CLASS_SET;

    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    static {
        String basePackage = ConfigFileReader.getBasePackage();
        CLASS_SET = ClassLoadUtil.getClassSetUnderPackageName(basePackage);
    }

    /**
     * get set of class object whose class file annotated with @Handler
     * @return Set of Handler class objects
     */
    public static Set<Class<?>> getHandlerClassSet() {
        Set<Class<?>> set = new HashSet<>();
        for (Class<?> clazz : CLASS_SET) {
            if (clazz.isAnnotationPresent(Handler.class)) {
                set.add(clazz);
            }
        }

        return set;
    }

    public static Set<Class<?>> getServiceClassSet() {
        Set<Class<?>> set = new HashSet<>();
        for (Class<?> clazz : CLASS_SET) {
            if (clazz.isAnnotationPresent(Service.class)) {
                set.add(clazz);
            }
        }

        return set;
    }

    /**
     * Component: a bean combination of Service and Handler.
     */
    public static Set<Class<?>> getComponentClassSet() {
        Set<Class<?>> set = new HashSet<>();
        set.addAll(getServiceClassSet());
        set.addAll(getHandlerClassSet());
        return set;
    }

    /**
     * Bean: is a super set of the Component, in our project, component is enough to use.
     */
    public static Set<Class<?>> getBeanClassSet() {
        return getComponentClassSet();
    }

    /**
     * get class set with a specific Annotation class
     */
    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass) {
        Set<Class<?>> set = new HashSet<>();
        for (Class<?> clazz : CLASS_SET) {
            if (clazz.isAnnotationPresent(annotationClass)) {
                set.add(clazz);
            }
        }

        return set;
    }

    /**
     * get class from his super class, used to hold class objects that are inhereted from a abstact class or interface
     */
    public static Set<Class<?>> getClassSetBySuperClass(Class<?> superClass) {
        Set<Class<?>> set = new HashSet<>();
        for (Class<?> clazz : CLASS_SET) {
            // Determines if the class or interface represented by this Class object is either the same as,
            // or is a superclass or superinterface of, the class or interface represented
            // by the specified Class parameter.
            if (superClass.isAssignableFrom(clazz) && !superClass.equals(clazz)) {
                set.add(clazz);
            }
        }

        return set;
    }


}