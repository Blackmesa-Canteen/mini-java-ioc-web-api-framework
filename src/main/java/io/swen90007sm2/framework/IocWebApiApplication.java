package io.swen90007sm2.framework;

import io.swen90007sm2.framework.common.util.BannerUtil;
import io.swen90007sm2.framework.common.util.LogUtil;
import io.swen90007sm2.framework.core.AppContextLoader;
import io.swen90007sm2.framework.core.web.TomcatServer;
import io.swen90007sm2.framework.db.DbConnectionHelper;
import org.apache.catalina.LifecycleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * embed tomcat server that runs the whole framework
 *
 * @author xiaotian
 */
public class IocWebApiApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(IocWebApiApplication.class);

    /**
     * Entrance to startup the IoC web application
     */
    public static void run(String hostName, int portNumber) {

        BannerUtil.printBanner();

        LOGGER.info("SWEN90007 mini Java Ioc web Api app framework");
        LOGGER.info("Start to run the IoC Web Api Application.");

        // mute warning message caused by CgLib
        LogUtil.disableIllegalReflectiveWarning();

        // load core modules
        AppContextLoader.initAppContext();

        // load db connection
        DbConnectionHelper.getDbConnection();

        LOGGER.info("Startup the web server.");
        // start server
        TomcatServer server = new TomcatServer(hostName, portNumber);
        try {
            server.run();
        } catch (LifecycleException e) {
            LOGGER.error("tomcat server exception: ", e);
            throw new RuntimeException(e);
        }

        LOGGER.info("Application is running. Good luck no bugs mate :)");
    }
}
