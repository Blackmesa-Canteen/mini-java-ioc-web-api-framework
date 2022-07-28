package io.swen90007sm2.framework.core.config;

import io.swen90007sm2.framework.common.constant.ConfigFileConstant;
import io.swen90007sm2.framework.common.util.ConfigFileUtil;

import java.util.Properties;

/**
 * a helper to get configs from the file
 *
 * @author xiaotian
 */
public class ConfigFileReader {
    
    /*
     * props read from the prop config file
     */
    private static final Properties CONFIG_FILE_PROPS = ConfigFileUtil.loadProps(ConfigFileConstant.CONFIG_FILE_NAME);
    
    /*
     * jdbc
     */
    public static String getJdbcDriver() {
        return ConfigFileUtil.getString(CONFIG_FILE_PROPS, ConfigFileConstant.JDBC_DRIVER);
    }

    public static String getJdbcUrl() {
        return ConfigFileUtil.getString(CONFIG_FILE_PROPS, ConfigFileConstant.JDBC_URL);
    }

    public static String getJdbcUsername() {
        return ConfigFileUtil.getString(CONFIG_FILE_PROPS, ConfigFileConstant.JDBC_USERNAME);
    }

    public static String getJdbcPassword() {
        return ConfigFileUtil.getString(CONFIG_FILE_PROPS, ConfigFileConstant.JDBC_PASSWORD);
    }

    /**
     * get application package name
     */
    public static String getBasePackage() {
        return ConfigFileUtil.getString(CONFIG_FILE_PROPS, ConfigFileConstant.APP_BASE_PACKAGE_NAME);
    }

    /**
     * get customized k-v prop
     */
    public static String getString(String key) {
        return ConfigFileUtil.getString(CONFIG_FILE_PROPS, key);
    }

    public static int getInt(String key) {
        return ConfigFileUtil.getInt(CONFIG_FILE_PROPS, key);
    }

    public static boolean getBoolean(String key) {
        return ConfigFileUtil.getBoolean(CONFIG_FILE_PROPS, key);
    }
}
