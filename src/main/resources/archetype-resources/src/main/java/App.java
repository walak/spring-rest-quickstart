package ${groupId};

import ${groupId}.runner.JettyRunner;

public class App {

    public static void main(String[] args) throws Exception {
        JettyRunner jettyRunner = JettyRunner.withDefaults();

        jettyRunner.start();
        jettyRunner.join();
    }
}
