package org.fnktech;

import org.fnktech.model.TxtFile;
import org.fnktech.services.FileReader;
import org.fnktech.services.impl.FileReaderImpl;

import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Path testDirectory = Path.of("/Users/filipkucherenkov/Desktop/test_files");
        FileReader fileReader = new FileReaderImpl();
        List<TxtFile> lst = fileReader.readFiles(testDirectory);

        lst.forEach(file -> {
            file.content().forEach(System.out::println);
            System.out.println();
        });

        //TODO: 1. Process each file's content in a different thread
        //      2. Calculate character frequency in each file
        //      3. Combine the results and output the character with the maximum frequency
    }
}