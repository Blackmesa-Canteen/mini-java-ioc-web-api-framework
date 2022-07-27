package io.swen90007sm2.core.helper;

import io.swen90007sm2.annotation.AutoInjected;
import io.swen90007sm2.core.util.ReflectionUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * core helper for dependency injection
 *
 * @author xiaotian
 */
public class InjectionHelper {

    static {
        Map<Class<?>, Object> beanMap = BeanManager.getBeanMap();
        performInjection(beanMap);
    }

    private static void performInjection(Map<Class<?>, Object> beanMap) {
        if(!beanMap.isEmpty()) {
            // traverse this bean map
            // Using iterator has the best performance
            Iterator<Map.Entry<Class<?>, Object>> iterator = beanMap.entrySet().iterator();
            while (iterator.hasNext()) {
                // traverse each bean map entry
                Map.Entry<Class<?>, Object> next = iterator.next();
                Class<?> clazz = next.getKey();
                Object beanInstance = next.getValue();

                // get fields with reflection, then ready to inject
                Field[] fields = clazz.getFields();
                if (ArrayUtils.isNotEmpty(fields)) {
                    injectBeanToFields(beanMap, beanInstance, fields);
                }
            }
        }
    }

    private static void injectBeanToFields(Map<Class<?>, Object> beanMap, Object beanInstance, Field[] fields) {
        for (Field field : fields) {
            // judge whether the field has @AutoInjected or not
            if (field.isAnnotationPresent(AutoInjected.class)) {
                // inject the required bean to this field of this bean
                // find bean by Class/type
                Class<?> fieldClass = field.getType();

                // usually, we use interface or super class as type,
                // so need to find the implementation class
                Class<?> fieldImplClass = findImplementation(fieldClass);

                // get object instance from bean map
                Object fieldInstance = beanMap.get(fieldImplClass);
                if (fieldInstance != null) {
                    // if beanInstance exists, do injection
                    ReflectionUtil.setField(beanInstance, field, fieldInstance);
                }
            }
        }
    }

    private static Class<?> findImplementation(Class<?> fieldClass) {
        Class<?> res = fieldClass;
        // find impl/son class based on super
        Set<Class<?>> set = ClassManager.getClassSetBySuperClass(fieldClass);
        if (!set.isEmpty()) {
            // get the first impl class
            res = set.iterator().next();
        }
        return res;
    }
}
