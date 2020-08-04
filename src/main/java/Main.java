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

        String environment = args[0];
        String configFile = relativePath + productionFile;

        if (environment.equalsIgnoreCase("staging")) {
            configFile = relativePath + stagingFile;
        }
        else if (environment.equalsIgnoreCase("development")) {
            configFile = relativePath + developmentFile;
        }

        ConfigParser config = new ConfigParser();
        System.out.println(config.loadConfiguration());
        System.out.println(config.get("dbname"));
    }
}