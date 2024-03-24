package org.fnktech;

import org.fnktech.model.TxtFile;
import org.fnktech.services.FileReader;
import org.fnktech.services.impl.FileReaderImpl;

import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Path testDirecotory = Path.of("/Users/filipkucherenkov/Desktop/test_files");
        FileReader fileReader = new FileReaderImpl();
        List<TxtFile> lst = fileReader.readFiles(testDirecotory);

        lst.forEach(file -> {
            file.getContent().forEach(System.out::println);
            System.out.println();
        });
    }
}