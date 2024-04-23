package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        StringJoiner allDelimitersJoiner = new StringJoiner("");
        for (String delimiter : delimiters) {
            allDelimitersJoiner.add(delimiter);
        }
        StringTokenizer tokenizer = new StringTokenizer(source, allDelimitersJoiner.toString());
        List<String> splittedStrings = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            splittedStrings.add(tokenizer.nextToken());
        }
        return splittedStrings;
    }
}
