package katas.exercises;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class URLShortenerTest {

    private URLShortener urlShortener;

    @BeforeEach
    void setUp() {
        urlShortener = new URLShortener();
    }

    @Test
    void testShortenAndRetrieve() {
        String longUrl = "https://www.example.com/some/really/long/url";
        String shortUrl = urlShortener.shorten(longUrl);
        String retrievedUrl = urlShortener.retrieve(shortUrl);

        assertNotNull(shortUrl, "Shortened URL should not be null.");
        assertEquals(longUrl, retrievedUrl, "Retrieved URL should match the original long URL.");
    }

    @Test
    void testShortenSameUrlMultipleTimes() {
        String longUrl = "https://www.example.com/some/really/long/url";

        String shortUrl1 = urlShortener.shorten(longUrl);
        String shortUrl2 = urlShortener.shorten(longUrl);

        assertEquals(shortUrl1, shortUrl2, "Shortened URL for the same long URL should be the same.");
    }

    @Test
    void testRetrieveNonExistentShortUrl() {
        String nonExistentShortUrl = "http://short.ly/nonexistent";
        String retrievedUrl = urlShortener.retrieve(nonExistentShortUrl);

        assertNull(retrievedUrl, "Retrieving a non-existent short URL should return null.");
    }

    @Test
    void testShortenDifferentUrls() {
        String longUrl1 = "https://www.example.com/some/really/long/url1";
        String longUrl2 = "https://www.example.com/some/really/long/url2";

        String shortUrl1 = urlShortener.shorten(longUrl1);
        String shortUrl2 = urlShortener.shorten(longUrl2);

        assertNotEquals(shortUrl1, shortUrl2, "Shortened URLs for different long URLs should be different.");
        assertEquals(longUrl1, urlShortener.retrieve(shortUrl1), "Retrieved URL should match the first original long URL.");
        assertEquals(longUrl2, urlShortener.retrieve(shortUrl2), "Retrieved URL should match the second original long URL.");
    }

    @Test
    void testEmptyUrl() {
        String emptyUrl = "";
        String shortUrl = urlShortener.shorten(emptyUrl);
        String retrievedUrl = urlShortener.retrieve(shortUrl);

        assertNotNull(shortUrl, "Shortened URL should not be null for an empty string.");
        assertEquals(emptyUrl, retrievedUrl, "Retrieved URL should match the empty string.");
    }

    @Test
    void testSpecialCharactersInUrl() {
        String longUrl = "https://www.example.com/some/url?query=param&another=param2";

        String shortUrl = urlShortener.shorten(longUrl);
        String retrievedUrl = urlShortener.retrieve(shortUrl);

        assertNotNull(shortUrl, "Shortened URL should not be null.");
        assertEquals(longUrl, retrievedUrl, "Retrieved URL should match the original URL with special characters.");
    }
}
