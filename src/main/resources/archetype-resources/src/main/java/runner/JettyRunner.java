package ${groupId}.runner;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyRunner {
    private static final int DEFAULT_PORT = 8080;
    private static final String DEFAULT_RESOURCE_PATH = "src/main/webapp";
    private static final String DEFAULT_DESCRIPTOP_PATH = "src/main/webapp/WEB-INF/web.xml";
    private static final String DEFAULT_CONTEXT_PATH = "/";

    private final Server server;
    private final WebAppContext appContext;

    public JettyRunner(int port, String resourcePath, String descriptorPath, String contextPath) {
        this.server = new Server(port);
        this.appContext = createWebContext(resourcePath, descriptorPath, contextPath);

        this.server.setHandler(appContext);
    }


    public void start() {
        try {
            server.start();
        } catch (Exception e) {
            throw new RuntimeException("Error running Jetty: " + e.getMessage(), e);
        }
    }

    public void join() {
        try {
            server.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            throw new RuntimeException("Error stopping Jetty: " + e.getMessage(), e);
        }
    }

    private WebAppContext createWebContext(String resourcePath, String descriptorPath, String contextPath) {
        WebAppContext webAppContext = new WebAppContext();

        webAppContext.setContextPath(contextPath);
        webAppContext.setResourceBase(resourcePath);
        webAppContext.setDescriptor(descriptorPath);

        return webAppContext;
    }

    public static JettyRunner withDefaults() {
        return new JettyRunner(DEFAULT_PORT,
                DEFAULT_RESOURCE_PATH,
                DEFAULT_DESCRIPTOP_PATH,
                DEFAULT_CONTEXT_PATH);
    }
}
