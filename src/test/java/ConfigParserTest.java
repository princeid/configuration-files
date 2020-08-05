import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test results for production values")
class ConfigParserTest {

    /**
     * Testing for all values in the production file
     * @throws IOException
     */
    @Test
    @DisplayName("Test results for production values")
    void getProduction() throws IOException {
        ConfigParser config = new ConfigParser();
        assertAll(
                () -> assertEquals("sq04_db", config.get("dbname")),
                () -> assertEquals("127.0.0.1", config.get("host")),
                () -> assertEquals("fintek", config.get("application.name")),
                () -> assertEquals("8080", config.get("application.port")),
                () -> assertEquals("/api/v1", config.get("application.context-url")),
                () -> assertEquals("production", config.get("mode")),
                () -> assertEquals("green", config.get("theme")),
                () -> assertEquals("fast", config.get("pipeline"))
        );
    }

    /**
     * Testing for all values in the staging file
     * @throws IOException
     */
    @Test
    @DisplayName("Test results for staging values")
    void getStaging() throws IOException {
        ConfigParser config = new ConfigParser("src/main/config-files/config-staging.txt");
        assertAll(
                () -> assertEquals("sq04_db", config.get("dbname")),
                () -> assertEquals("127.0.0.1", config.get("host")),
                () -> assertEquals("fintek-staging", config.get("application.name")),
                () -> assertEquals("8081", config.get("application.port")),
                () -> assertEquals("/api/v1", config.get("application.context-url")),
                () -> assertEquals("staging", config.get("mode")),
                () -> assertEquals("blue", config.get("theme")),
                () -> assertEquals("fast-staging", config.get("pipeline"))
        );
    }

    /**
     * Testing for all values in the development file
     * @throws IOException
     */
    @Test
    @DisplayName("Test results for development values")
    void getDevelopment() throws IOException {
        ConfigParser config = new ConfigParser("src/main/config-files/config-development.txt");
        assertAll(
                () -> assertEquals("sq04_db-development", config.get("dbname")),
                () -> assertEquals("127.0.0.1", config.get("host")),
                () -> assertEquals("fintek-development", config.get("application.name")),
                () -> assertEquals("8082", config.get("application.port")),
                () -> assertEquals("/api/v1", config.get("application.context-url")),
                () -> assertEquals("development", config.get("mode")),
                () -> assertEquals("yellow", config.get("theme")),
                () -> assertEquals("fast-development", config.get("pipeline"))
        );
    }
}