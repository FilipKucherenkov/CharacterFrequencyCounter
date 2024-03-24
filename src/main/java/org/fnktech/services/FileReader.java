package org.fnktech.services;

import org.fnktech.model.TxtFile;

import java.nio.file.Path;
import java.util.List;

/**
 * Defines the contract for interacting with files.
 */
public interface FileReader {

    /**
     * Reads all files in the specified directory and extracts the content.
     * @return List of String objects with the content from each file.
     */
    List<TxtFile> readFiles(Path path) throws Exception;

}
