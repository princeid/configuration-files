import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));

        /**
         *  Set environment argument from the command line. "Goto -> Edit Configurations -> Program arguments"
         *  The default environment is "production"
         */

        String relativePath = "src/main/config-files/";
        String productionFile = "config.txt";
        String stagingFile = "config-staging.txt";
        String developmentFile = "config-development.txt";
        String configFile = relativePath + productionFile;
        String environment = "production";

        if (args[0].equalsIgnoreCase("staging")) {
            environment = "staging";
            configFile = relativePath + stagingFile;
        }

        if (args[0].equalsIgnoreCase("development")) {
            environment = "development";
            configFile = relativePath + developmentFile;
        }

        ConfigParser config = new ConfigParser(configFile);
        System.out.println(config.getMap());
        System.out.println(config.get("application.name"));
    }
}