package katas.exercises;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WordCounterTest {

    @Test
    public void testCountWords() {
        // Normal case: a sentence with multiple words
        String sentence1 = "This is a sample sentence for counting words.";
        assertEquals(8, WordCounter.countWords(sentence1), "The word count for the first sentence is incorrect");

        // Edge case: empty string
        String sentence2 = "";
        assertEquals(0, WordCounter.countWords(sentence2), "The word count for an empty string should be 0");

        // Edge case: string with only spaces
        String sentence3 = "     ";
        assertEquals(0, WordCounter.countWords(sentence3), "The word count for a string with only spaces should be 0");

        // Edge case: null string
        String sentence4 = null;
        assertEquals(0, WordCounter.countWords(sentence4), "The word count for a null string should be 0");

        // Edge case: sentence with multiple spaces between words
        String sentence5 = "This    is   a   sentence  with    multiple    spaces.";
        assertEquals(7, WordCounter.countWords(sentence5), "The word count for a sentence with multiple spaces should be correct");

        // Edge case: sentence with leading and trailing spaces
        String sentence6 = "   Leading and trailing spaces   ";
        assertEquals(4, WordCounter.countWords(sentence6), "The word count for a sentence with leading and trailing spaces should be correct");

        // Edge case: single word
        String sentence7 = "Hello";
        assertEquals(1, WordCounter.countWords(sentence7), "The word count for a single word should be 1");

        // Edge case: sentence with multiple spaces between words and punctuation
        String sentence9 = "   This   is   a    test sentence  .   ";
        assertEquals(5, WordCounter.countWords(sentence9), "The word count for a sentence with spaces and punctuation should be correct");
    }
}
