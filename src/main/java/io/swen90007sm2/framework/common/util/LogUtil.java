package io.swen90007sm2.framework.common.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.logging.Level;

/**
 * log util for disable redundant warning
 *
 * @author xiaotian
 * @author StackOverflow
 */
public class LogUtil {

    public static void disableIllegalReflectiveWarning() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe u = (Unsafe) theUnsafe.get(null);

            Class cls = Class.forName("jdk.internal.module.IllegalAccessLogger");
            Field logger = cls.getDeclaredField("logger");
            u.putObjectVolatile(cls, u.staticFieldOffset(logger), null);
        } catch (Exception e) {
            // ignore
        }
    }

    /**
     * hide tomcat-embed log
     */
    public static void disableTomcatInitWarning() {
        System.setProperty("java.util.logging.SimpleFormatter.format", "");
    }
}
