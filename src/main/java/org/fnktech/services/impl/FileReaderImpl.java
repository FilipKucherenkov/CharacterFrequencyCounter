package org.fnktech.services.impl;

import org.fnktech.model.TxtFile;
import org.fnktech.services.FileReader;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

public class FileReaderImpl implements FileReader {
    private static final Logger LOGGER =  Logger.getLogger(FileReaderImpl.class.getName());
    @Override
    public List<TxtFile> readFiles(Path path) throws Exception {
        validatePath(path);
        try {
            return Files.walk(path)
                    .filter(Files::isRegularFile)
                    .filter(filePath -> !filePath.endsWith(".txt"))
                    .map(this::buildTxtFile)
                    .toList();
        }catch (Exception e){
            LOGGER.warning("Could not read files in directory");
            return List.of();
        }
    }
    private TxtFile buildTxtFile(Path path){
        LOGGER.info(String.format("Attempting to read file [path=%s]", path));
        try {
            return new TxtFile(Files.readAllLines(path, StandardCharsets.UTF_8));
        }catch (Exception e){
            LOGGER.warning(String.format("Could not read file [path=%s]", path));
            return new TxtFile(List.of());
        }
    }
    private void validatePath(Path path) throws Exception {
        if(!Files.exists(path)){
            throw new Exception(String.format("The provided path is invalid [path=%s]", path));
        }else if(!Files.isDirectory(path)){
            throw new Exception(String.format("The provided path is not a directory [path=%s]", path));
        }
    }
}
