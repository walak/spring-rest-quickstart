package ${groupId}.runner;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;

public class TomcatRunner implements ContainerRunner {

    private static final String ADDITIONAL_WEBINF_CLASSES_PATH = new File("target/classes").getAbsolutePath();
    private static final String WEB_INF_CLASSES = "/WEB-INF/classes";
    private static final String INTERNAL_PATH = "/";

    private final Tomcat tomcat;

    public TomcatRunner(String webappDir, String contextPath, int port) {
        this.tomcat = new Tomcat();
        this.tomcat.setPort(port);

        StandardContext context = createContext(webappDir, contextPath);

    }

    private StandardContext createContext(String webappDir, String contextPath) {
        try {
            StandardContext context = (StandardContext) tomcat.addWebapp(contextPath, new File(webappDir).getAbsolutePath());
            context.setResources(createWebResourceRoot(context));

            return context;
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    private WebResourceRoot createWebResourceRoot(StandardContext context) {
        WebResourceRoot resourceRoot = new StandardRoot(context);

        resourceRoot.addPreResources(new DirResourceSet(resourceRoot, WEB_INF_CLASSES, ADDITIONAL_WEBINF_CLASSES_PATH, INTERNAL_PATH));
        return resourceRoot;
    }

    public static TomcatRunner withDefaults() {
        return new TomcatRunner("src/main/webapp", "/", 8080);
    }

    @Override
    public void start() {
        try {
            tomcat.start();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void join() {
        tomcat.getServer().await();
    }

    @Override
    public void stop() {
        try {
            tomcat.stop();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
    }
}