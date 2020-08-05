# configuration-files

This application reads key/value pairs from different config files (one file at a time) and stores the result on a map, based on the environment specified by the user.

## Usage

Specify the environment from the command line in args[0]. If no environment is specified, the default environment is "production".
```java
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
        config.getMap();
        config.get("application.name");
```

## Classes and API's Used

*  java.io.BufferedReader
*  java.io.FileReader
*  java.util.HashMap
*  junit.jupiter.api
