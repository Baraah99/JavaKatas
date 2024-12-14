package katas.exercises;

public class WordCounter {

    /**
     * Counts the number of words in a given sentence.
     *
     * @param sentence the input string (a sentence)
     * @return the number of words in the sentence
     */
    public static int countWords(String sentence) {

        if (sentence == null || sentence.trim().isEmpty()) {
            return 0;  // Return 0 for null or empty sentences
        }

        // Use regex to find sequences of alphanumeric characters as words
        String[] words = sentence.trim().split("\\s+|(?<!\\w)\\W+");
        return (int) java.util.Arrays.stream(words).filter(word -> !word.isEmpty()).count();

    }

    public static void main(String[] args) {
        String sentence = "This is a sample sentence for counting words.";
        int wordCount = countWords(sentence);
        System.out.println(wordCount);
    }
}

