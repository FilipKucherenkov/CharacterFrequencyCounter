package org.fnktech.services.impl;

import org.fnktech.model.TxtFile;
import org.fnktech.services.FrequencyCounter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class CharacterFrequencyCounter implements FrequencyCounter<Character> {
    private static final Logger LOGGER =  Logger.getLogger(CharacterFrequencyCounter.class.getName());

    private List<TxtFile> fileList;
    public CharacterFrequencyCounter(List<TxtFile> fileList){
        this.fileList = fileList;
    }
    @Override
    public Map<Character, Integer> count(){
        // Create an unlimited thread pool using virtual threads.
        ThreadFactory factory = Thread.ofVirtual().factory();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(0, factory);

        // Count the char frequency in every file in a different virtual thread
        List<CompletableFuture<Map<Character, Integer>>> futures = fileList.stream()
                .map(file -> CompletableFuture.supplyAsync(() -> {
                    CharCounter charCounter = new CharCounter(file.content());
                    try {
                        return charCounter.call();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }, scheduledExecutorService))
                .toList();

        // Combine all CompletableFuture objects into a single CompletableFuture
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        // Wait for all CompletableFuture objects to complete
        allFutures.join();

        // Merge results into a single map
        Map<Character, Integer> result = mergeResults(futures);

        scheduledExecutorService.shutdown();
        return result;
    }

    @Override
    public Map<Character, Integer> findTheMostFrequentElements() {
        Map<Character, Integer> result = new HashMap<>();
        Map<Character, Integer> elementFrequencies = count();
        int maxFrequency = Integer.MIN_VALUE;
        for(Map.Entry<Character, Integer> entry: elementFrequencies.entrySet()){
            maxFrequency = Math.max(maxFrequency, entry.getValue());
        }
        for(Map.Entry<Character, Integer> entry: elementFrequencies.entrySet()){
            if(entry.getValue() == maxFrequency) result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    private Map<Character, Integer> mergeResults(List<CompletableFuture<Map<Character, Integer>>> futures){

        Map<Character, Integer> result = new HashMap<>();
        futures.forEach(future -> {
            try {
                for(Map.Entry<Character, Integer> entry: future.get().entrySet()){
                    result.put(entry.getKey(), result.getOrDefault(entry.getKey(), 0) + entry.getValue());
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        return result;
    }
}
