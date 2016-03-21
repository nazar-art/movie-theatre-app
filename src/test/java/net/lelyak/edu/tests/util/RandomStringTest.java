package net.lelyak.edu.tests.util;


import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.assertFalse;


public class RandomStringTest {

    @Test
    public void testGetWordBoolean() {
        Random rnd = new Random();
        int minWordLength = rnd.nextInt(5),
                maxWordLength = minWordLength + rnd.nextInt(31),
                minWords = rnd.nextInt(3),
                maxWords = minWords + rnd.nextInt(101);

        RandomString rs = new RandomString(minWordLength, maxWordLength, minWords, maxWords);
        System.out.format("===== testGetWordBoolean(%d, %d, %d, %d) ===================\r\n", minWordLength, maxWordLength, minWords, maxWords);
        for (int i = 0; i < rnd.nextInt(100); i++) {
            boolean wordCase = rnd.nextBoolean();
            String word = rs.getWord(wordCase);
            System.out.println(word);
            assertFalse(Character.isUpperCase(word.charAt(0)) != wordCase);
        }
    }

    @Test
    public void testGetSentence() {
        Random rnd = new Random();
        int minWordLength = rnd.nextInt(5),
                maxWordLength = minWordLength + rnd.nextInt(31),
                minWords = rnd.nextInt(3),
                maxWords = minWords + rnd.nextInt(101);

        RandomString rs = new RandomString(1, maxWordLength, 1, maxWords);
        System.out.format("===== testGetSentence(%d, %d, %d, %d) ===================\r\n", minWordLength, maxWordLength, minWords, maxWords);
        for (int i = 0; i < rnd.nextInt(100); i++) {
            String setence = rs.getSentence();
            System.out.println(setence);
            assertFalse(setence.length() < minWordLength * minWords);
        }
    }

}
