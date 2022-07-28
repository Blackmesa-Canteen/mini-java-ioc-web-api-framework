package io.swen90007sm2.framework.core.web;

import io.swen90007sm2.framework.core.web.servlet.MyDispatcherServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

/**
 * embedded Tomcat server
 *
 * @author xiaotian
 */
public class TomcatServer {
    private Tomcat tomcat;
    private int portNumber = 8088;

    public TomcatServer() {
    }

    public TomcatServer(int portNumber) {
        this.portNumber = portNumber;
    }

    /**
     * startup the framework
     * @throws LifecycleException tomcat embed exception
     */
    public void run() throws LifecycleException {

        // init tomcat
        tomcat = new Tomcat();
        tomcat.setPort(portNumber);
        tomcat.start();

        Context context = new StandardContext();
        context.setPath("");
        context.addLifecycleListener(new Tomcat.FixContextListener());
        MyDispatcherServlet myDispatcherServlet = new MyDispatcherServlet();
        Tomcat.addServlet(context,"myDispatcherServlet", myDispatcherServlet).setAsyncSupported(true);

        // add path interceptor
        context.addServletMappingDecoded("/","myDispatcherServlet");
        tomcat.getHost().addChild(context);

        // setup thread for running tomcat server
        Thread awaitThread = new Thread("tomcat_await_thread."){
            @Override
            public void run() {
                TomcatServer.this.tomcat.getServer().await();
            }
        };
        awaitThread.setDaemon(false);
        awaitThread.start();
    }
}
