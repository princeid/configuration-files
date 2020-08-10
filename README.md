# configuration-files

This application reads key/value pairs from different config files (one file at a time) and stores the result on a map, based on the environment specified by the user.

## Usage

Specify the environment from the command line in args[0]. If no environment is specified, the default environment is "production".
```java
        ConfigParser config = new ConfigParser(configFile);
        config.getMap();
        config.get("application.name");
```


The below values will be returned from the map based on "development" environment. For duplicate keys, only the first one is added to the map:

```
{dbname=sq04_db-development, host=127.0.0.1, application.name=fintek-development, 
application.port=8082, application.context-url=/api/v1, mode=development, theme=yellow, 
pipeline=fast-development}

```

dbname returns ```sq04_db-development```,   and  application.name returns ```fintek-development```

## Classes and API's Used

*  java.io.BufferedReader
*  java.io.FileReader
*  java.util.HashMap
*  junit.jupiter.api
