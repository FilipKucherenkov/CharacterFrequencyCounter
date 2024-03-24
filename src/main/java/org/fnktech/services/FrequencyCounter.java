package org.fnktech.services;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Defines the contract for counting element frequency
 * @param <T> element to count
 */
public interface FrequencyCounter<T> {
    Map<T, Integer> count() throws ExecutionException, InterruptedException;

    Map<T, Integer> findTheMostFrequentElements();

}
