package io.swen90007sm2.framework.core.ioc;

import io.swen90007sm2.framework.common.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A core helper, which is a container to hold all objects.
 * By default, the bean scope is singleton only...
 *
 * @author xiaotian
 */
public class BeanManager {

    /*
     * the map contains all bean objects
     */
    private static final Map<Class<?>, Object> BEAN_MAP;

    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    static {
        BEAN_MAP = new HashMap<>();

        // Handler + Blo + Dao
        Set<Class<?>> beanClassSet = ClassManager.getBeanClassSet();

        // instantiate objects from class object, then put in the map
        for (Class<?> clazz :beanClassSet) {
            Object object = ReflectionUtil.newInstance(clazz);
            BEAN_MAP.put(clazz, object);
        }
    }

    /**
     * get bean object from container by class object
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean (Class<T> clazz) {
        if (!BEAN_MAP.containsKey(clazz)) {
            throw new RuntimeException("Can not get bean by class object: " + clazz);
        }

        return (T) BEAN_MAP.get(clazz);
    }

    /**
     * put new bean in map
     */
    public static void putBean(Class<?> clazz, Object object) {
        BEAN_MAP.put(clazz, object);
    }
}
