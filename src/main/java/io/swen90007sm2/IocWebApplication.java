package io.swen90007sm2;

import io.swen90007sm2.core.AppContextLoader;
import io.swen90007sm2.core.util.ReflectionUtil;
import io.swen90007sm2.core.web.TomcatServer;
import org.apache.catalina.LifecycleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * embed tomcat server that runs the whole framework
 */
public class IocWebApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(IocWebApplication.class);

    /**
     * Entrance to startup the IoC web application
     */
    public static void run(int portNumber) {

        LOGGER.info("Start to run the IoC Web Application.");

        // load core modules
        AppContextLoader.initAppContext();

        LOGGER.info("Startup the web server.");
        // start server
        TomcatServer server = new TomcatServer(portNumber);
        try {
            server.run();
        } catch (LifecycleException e) {
            LOGGER.error("tomcat server exception: ", e);
            throw new RuntimeException(e);
        }

        LOGGER.info("Application is running.");
    }
}
