package com.sonchaba.csv.processor.csv;

import com.sonchaba.csv.processor.utils.CsvVariables;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CSVParser {

    private final static char COMMA;
    private final static char DOUBLE_QUOTE = '\"';

    static {
        COMMA = System.getProperty(CsvVariables.CSV_FILE_SEPARATOR).charAt(0);
    }

    private CSVParser() {
    }

    public static String[] CSVSplit(String input) {
        List<String> split = new ArrayList<>();
        Deque<Character> queue = new ArrayDeque<>();
        State current = State.ACCEPT;

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            switch (current) {
                case ACCEPT:

                    if (currentChar == COMMA) {
                        split.add(queueToString(queue));
                    } else if (currentChar == DOUBLE_QUOTE) {
                        queue = new ArrayDeque<>();
                        current = State.QUOTED;
                    } else {
                        queue = new ArrayDeque<>();
                        queue.addLast(currentChar);
                        current = State.READ;
                    }
                    break;
                case QUOTED:
                    if (currentChar == DOUBLE_QUOTE) {
                        if (i < input.length() - 1 && input.charAt(i + 1) == COMMA) {
                            current = State.ACCEPT;
                        } else {
                            if (i < input.length() - 1) {
                                queue.addLast(currentChar);
                            }
                        }
                    } else {
                        queue.addLast(currentChar);
                    }
                    break;
                case READ:
                    if (currentChar == COMMA) {
                        current = State.ACCEPT;
                        split.add(queueToString(queue));
                    } else {
                        queue.addLast(currentChar);
                    }
            }
        }
        // dump any remaining chars in the queue
        split.add(queueToString(queue));

        // make new array to pass to 'toArray'
        String[] a = new String[split.size()];
        return split.toArray(a);
    }

    private static String queueToString(Deque<Character> queue) {
        String result = "";
        while (queue.size() > 0) {
            result += queue.pop();
        }
        return result;
    }

    enum State {QUOTED, READ, ACCEPT}
}
