package net.lelyak.edu.tests.util;

import java.util.Random;

public class RandomString {
    private final Random rnd = new Random(47);

    private static final char vowels[] = {'a', 'e', 'i', 'o', 'u'};
    private static final char consonants[] = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y'};
    private static final char spaces[] = {' ', ' ', ' ', '\t'};
    private static final char separators[] = {',', ';', '.', '!', '?', '\'', '"', ':', '-', '\r', '\n'};


    private int minWords, maxWords;
    private int minWordLength, maxWordLength;

    public RandomString(int minWords, int maxWords, int minWordLength, int maxWordLength) {
        super();
        this.minWords = minWords;
        this.maxWords = maxWords;
        this.minWordLength = minWordLength;
        this.maxWordLength = maxWordLength;
    }

    public String getWord(boolean firstLetterUpperCase) {
        StringBuilder sb = new StringBuilder();

        if (firstLetterUpperCase) {
            sb.append(Character.toUpperCase(consonants[rnd.nextInt(consonants.length)]));
        } else {
            sb.append(consonants[rnd.nextInt(consonants.length)]);
        }

        while (sb.length() <= minWordLength + rnd.nextInt(maxWordLength - minWordLength)) {
            if (rnd.nextBoolean() && rnd.nextBoolean()) {
                sb.append(consonants[rnd.nextInt(consonants.length)]);
            }
            sb.append(vowels[rnd.nextInt(vowels.length)]);
            if (rnd.nextBoolean() && rnd.nextBoolean()) {
                sb.append(vowels[rnd.nextInt(vowels.length)]);
            }
            if (rnd.nextBoolean()) {
                sb.append(consonants[rnd.nextInt(consonants.length)]);
            }
            if (rnd.nextBoolean()) {
                sb.append(consonants[rnd.nextInt(consonants.length)]);
            }
        }
        return sb.toString();
    }

    public String getUCWord() {
        return getWord(true);
    }

    public String getLCWord() {
        return getWord(false);
    }

    public String getWord() {
        return getWord(rnd.nextBoolean());
    }

    public String getSentence() {
        StringBuilder sb = new StringBuilder();

        sb.append(getUCWord());

        for (int i = 1; i < minWords + rnd.nextInt(maxWords - minWords); i++) {
            sb.append(spaces[rnd.nextInt(spaces.length)]);
            if (rnd.nextBoolean()) {
                sb.append(spaces[rnd.nextInt(spaces.length)]);
            }

            sb.append(getWord());

            if (rnd.nextBoolean()) {
                sb.append(separators[rnd.nextInt(separators.length)]);
            }
        }
        return sb.toString();
    }

    public int getMinWords() {
        return minWords;
    }

    public int getMaxWords() {
        return maxWords;
    }

    public int getMinWordLength() {
        return minWordLength;
    }

    public int getMaxWordLength() {
        return maxWordLength;
    }


}
