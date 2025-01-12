package katas.exercises;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NginxLogsParserTest {

    @Test
    void testParseLog_validLog() {
        // Given
        String logEntry = "122.176.223.47 - - [05/Feb/2024:08:29:40 +0000] " +
                          "\"GET /web-enabled/Enhanced-portal/bifurcated-forecast.js HTTP/1.1\" 200 1684 " +
                          "\"-\" \"Opera/9.58 (X11; Linux i686; en-US) Presto/2.12.344 Version/13.00\"";

        // When
        Map<String, String> result = NginxLogsParser.parseLog(logEntry);

        // Then
        assertEquals("122.176.223.47", result.get("client_ip"));
        assertEquals("05/Feb/2024:08:29:40 +0000", result.get("date"));
        assertEquals("GET", result.get("http_method"));
        assertEquals("/web-enabled/Enhanced-portal/bifurcated-forecast.js", result.get("path"));
        assertEquals("1.1", result.get("http_version"));
        assertEquals("200", result.get("status"));
        assertEquals("1684", result.get("response_bytes"));
        assertEquals("Opera/9.58 (X11; Linux i686; en-US) Presto/2.12.344 Version/13.00", result.get("user_agent"));
    }

    @Test
    void testParseLog_invalidLog() {
        // Given
        String invalidLog = "Invalid log format";

        // When & Then
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                NginxLogsParser.parseLog(invalidLog)
        );

        assertEquals("Invalid log format", exception.getMessage());
    }

    @Test
    void testParseLog_partialLog() {
        // Given
        String partialLog = "122.176.223.47 - - [05/Feb/2024:08:29:40 +0000] \"GET /missing-part\"";

        // When & Then
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                NginxLogsParser.parseLog(partialLog)
        );

        assertEquals("Invalid log format", exception.getMessage());
    }
}
