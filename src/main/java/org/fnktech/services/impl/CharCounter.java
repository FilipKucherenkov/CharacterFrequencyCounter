package org.fnktech.services.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

public class CharCounter implements Callable<Map<Character, Integer>>{
    private static final Logger LOGGER =  Logger.getLogger(FileReaderImpl.class.getName());

    private List<String> content;

    public CharCounter(List<String> content) {
        this.content = content;
    }

    @Override
    public Map<Character, Integer> call() {
        LOGGER.info(STR."Running in \{Thread.currentThread().toString()}");
        // Use content and additionalParam here as needed
        Map<Character, Integer> count = new HashMap<>();
        for(String line: content){
            for (char c : line.toCharArray()) {
                count.put(c, count.getOrDefault(c, 0) + 1);
            }
        }
        return count;
    }
}
