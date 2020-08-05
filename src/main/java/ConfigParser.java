import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ConfigParser {
    String configFile;
    FileReader fileReader = null;
    BufferedReader bufferedReader = null;
    HashMap<String, String> map = new LinkedHashMap<>();

    public String getConfigFile() {
        return configFile;
    }

    public HashMap<String, String> getMap() {
        return map;
    }

    public ConfigParser() throws IOException {
        this("src/main/config-files/config.txt");
    }

    /**
     * On start of the application, the constructor below loads and stores key/values from the file on a map based on the environment
     * specified. Where there are multiple values for a key, only the first key/value pair is added to the map.
     * @param configFile The config file for the current environment
     * @throws IOException
     */
    public ConfigParser(String configFile) throws IOException {
        this.configFile = configFile;
        try {
            fileReader = new FileReader(configFile);
            bufferedReader = new BufferedReader(fileReader);

            String thisLine = bufferedReader.readLine();
            String prefix = "";
            String key = null;
            String value;
            int index;

            /**
             *  Loops until the end of the file.
             */
            while (thisLine != null) {
//                System.out.println(thisLine);

                /**
                 *  Checks if the current line being read is a sub heading or tag, for example "[application]" in order to extract the name and
                 *  concatenate it to the following keys under it until another blank line or tag is met.
                 */
                if ((!thisLine.contains("=")) && (!thisLine.isBlank())) {
                    prefix = thisLine.substring(1, thisLine.length()-1) + ".";  /* extracting the subheading/tag and concatenating with a dot "." */
                    thisLine = bufferedReader.readLine(); /* Continues to read next line after meeting the condition for a subheading/tag and
                    extracting the required portion */
//                    System.out.println(thisLine);

                    /**
                     *  The while loop below reads subsequent lines and processes their keys by concatenating the prefix with their respective keys
                     *  until another subheading/tag or blank line is met.
                     */
                    try {
                        while ((!thisLine.isBlank()) && (!thisLine.contains("["))) {
                            if (thisLine.contains("=")) {
                                index = thisLine.indexOf("=");
                                key = prefix + thisLine.substring(0, index); /* Extracts the name portion from the current line being processed and
                                concatenates the prefix to it to form the key */
                                value = thisLine.substring(index+1); /* Extracts the value portion from the current line being processed to form
                                the value */
                                thisLine = bufferedReader.readLine();
//                                System.out.println(thisLine);
                                if (!map.containsKey(key)) { /* Checks if the map does not contain the current key being processed, and if true it
                                adds the key and value to the map */
                                    map.put(key,value);
                                }
                            }
                        }
                    } catch (Exception e) {
//                        System.out.println("End of File");
                    }
                }

                /**
                 *  Continues processing each line that is not part of a subheading/tag section and adding it to the map if it DOESN'T already
                 *  exist in the map
                 */
                else if (thisLine.contains("=")) {
                    prefix = "";
                    index = thisLine.indexOf("=");
                    key = prefix + thisLine.substring(0, index);
                    value = thisLine.substring(index+1);

                    if (!map.containsKey(key)) {
                        map.put(key,value);
                    }
                }
                thisLine = bufferedReader.readLine(); // Continues to next line
            }

        } catch (Exception e) {
            System.err.println("The filename \"" + configFile + "\" is not present on the file system! ");
        } finally {
            if (fileReader != null) {
                fileReader.close();
                bufferedReader.close();
            }
        }
    }


    /**
     * @param key The key in which the value to be returned is assigned
     * @return Returns the value from the map
     */
    public String get(String key) {
        if (!map.containsKey(key)) {
            System.err.println("Invalid key \"" + key + "\"");
            System.exit(0);
        }
        return map.get(key);
    }
}

/**
 *   USING PROPERTIES CLASS, ASSUMING NO DUPLICATE KEY.
 *
 * if (inputStream != null) {
 *      properties.load(inputStream);
 *  } else {
 *      throw new FileNotFoundException("Property file '" + configFile + "' not found in the classpath");
 *    }
 */