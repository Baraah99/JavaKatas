package katas.exercises;

import java.util.HashMap;
import java.util.Map;

public class URLShortener {
    /**
     * A URL Shortener is a service that converts a long URL into a shorter, more manageable URL.
     */

    private Map<String, String> urlMap;
    private Map<String, String> reverseMap; // To prevent duplicate short URLs for the same long URL
    private static final String BASE_URL = "http://short.ly/";
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = CHARACTERS.length();
    private long counter;

    /**
     * Constructor to initialize the URL shortener system.
     */
    public URLShortener() {
        this.urlMap = new HashMap<>();
        this.reverseMap = new HashMap<>();
        this.counter = 1; // Start counter from 1 to avoid generating empty strings
    }

    /**
     * Shortens the provided long URL.
     *
     * @param longUrl the long URL to shorten
     * @return the shortened URL
     */
    public String shorten(String longUrl) {
        if (reverseMap.containsKey(longUrl)) {
            return reverseMap.get(longUrl); // Return existing short URL if already mapped
        }

        String shortKey = generateShortKey(counter++);
        String shortUrl = BASE_URL + shortKey;

        urlMap.put(shortUrl, longUrl);
        reverseMap.put(longUrl, shortUrl);

        return shortUrl;
    }

    /**
     * Retrieves the original long URL from the shortened URL.
     *
     * @param shortUrl the shortened URL
     * @return the original long URL, or null if the short URL is not found
     */
    public String retrieve(String shortUrl) {
        return urlMap.get(shortUrl);
    }

    /**
     * Generates a short key based on the given counter using Base62 encoding.
     *
     * @param id the counter value
     * @return a Base62 encoded string
     */
    private String generateShortKey(long id) {
        StringBuilder shortKey = new StringBuilder();
        while (id > 0) {
            shortKey.append(CHARACTERS.charAt((int) (id % BASE)));
            id /= BASE;
        }
        return shortKey.reverse().toString();
    }

    public static void main(String[] args) {
        URLShortener shortener = new URLShortener();

        String longUrl1 = "https://www.example.com/some/really/long/url";
        String shortUrl1 = shortener.shorten(longUrl1);
        System.out.println("Shortened URL: " + shortUrl1);
        System.out.println("Original URL: " + shortener.retrieve(shortUrl1));

        String longUrl2 = "https://www.different.com/another/path";
        String shortUrl2 = shortener.shorten(longUrl2);
        System.out.println("Shortened URL: " + shortUrl2);
        System.out.println("Original URL: " + shortener.retrieve(shortUrl2));

        // Testing duplicate URL shortening
        String shortUrl1Again = shortener.shorten(longUrl1);
        System.out.println("Shortened URL for the same long URL: " + shortUrl1Again);
    }
}
