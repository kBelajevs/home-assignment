package com.kb.application.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.walk;
import static java.util.Collections.emptyList;

public class FileSearcher {

    private static final Set<String> fileNames = new HashSet<>();

    private String directory;

    public FileSearcher(String directory){
        this.directory = directory;
    }

    public List<File> getApplicableFiles() {
        try (Stream<Path> paths = walk(Paths.get(directory))) {
            return paths
                    .filter(Files::isRegularFile)
                    .filter(this::isFileXML)
                    .filter(this::isNewFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e){
            e.printStackTrace();
            return emptyList();
        }
    }

    private boolean isFileXML(Path path) {
        return path.getFileName().toString().toLowerCase().endsWith(".xml");
    }

    private boolean isNewFile(Path path) {
        return fileNames.add(path.getFileName().toString().toLowerCase());
    }
}
