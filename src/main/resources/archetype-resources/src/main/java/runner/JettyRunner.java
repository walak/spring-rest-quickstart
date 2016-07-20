package ${groupId}.runner;

import org.eclipse.jetty.deploy.DeploymentManager;
import org.eclipse.jetty.deploy.providers.WebAppProvider;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;

public class JettyRunner {
    private static final int DEFAULT_PORT = 8080;
    private static final String DEFAULT_RESOURCE_PATH = "src/main/webapp";
    private static final String DEFAULT_DESCRIPTOR_PATH = "src/main/webapp/WEB-INF/web.xml";
    private static final String DEFAULT_CONTEXT_PATH = "/";
    private static final File TEMP_DIR = new File("target");
    private static final int SCAN_INTERVAL = 1;

    private final Server server;

    public JettyRunner(int port, String resourcePath, String descriptorPath, String contextPath) {
        this.server = new Server(port);

        WebAppContext appContext = createWebContext(resourcePath, descriptorPath, contextPath);

        this.server.setHandler(appContext);
        this.server.addBean(createDeploymentManager(appContext));

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

    private DeploymentManager createDeploymentManager(WebAppContext context) {
        DeploymentManager deploymentManager = new DeploymentManager();
        deploymentManager.setContexts(createContextHandlerCollection(context));
        deploymentManager.addAppProvider(getWebAppProvider(context));

        return deploymentManager;
    }

    private WebAppProvider getWebAppProvider(WebAppContext context) {
        WebAppProvider webAppProvider = new WebAppProvider();
        webAppProvider.setMonitoredDirName(context.getResourceBase());
        webAppProvider.setTempDir(TEMP_DIR);
        webAppProvider.setScanInterval(SCAN_INTERVAL);
        webAppProvider.setExtractWars(true);
        return webAppProvider;
    }

    private ContextHandlerCollection createContextHandlerCollection(WebAppContext context) {
        ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
        contextHandlerCollection.setServer(server);
        contextHandlerCollection.addHandler(context);
        return contextHandlerCollection;
    }

    public static JettyRunner withDefaults() {
        return new JettyRunner(DEFAULT_PORT,
                DEFAULT_RESOURCE_PATH,
                DEFAULT_DESCRIPTOR_PATH,
                DEFAULT_CONTEXT_PATH);
    }
}
