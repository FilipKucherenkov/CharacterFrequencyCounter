package org.fnktech;

import org.fnktech.model.TxtFile;
import org.fnktech.services.FileReader;
import org.fnktech.services.FrequencyCounter;
import org.fnktech.services.impl.CharacterFrequencyCounter;
import org.fnktech.services.impl.FileReaderImpl;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        Path testDirectory = Path.of(args[0]);
        FileReader fileReader = new FileReaderImpl();
        List<TxtFile> files = fileReader.readFiles(testDirectory);

        FrequencyCounter<Character> characterFrequencyCounter = new CharacterFrequencyCounter(files);
        for(Map.Entry<Character, Integer> entry : characterFrequencyCounter.findTheMostFrequentElements().entrySet()){
            System.out.println(STR."\{entry.getKey()}: \{entry.getValue()}");
        };
    }
}