package com.ats.resumes;

import com.ats.interfaces.FileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileManagerImpl implements FileManager {

    private final String directoryPath;

    public FileManagerImpl(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    @Override
    public List<String> loadResumeFiles() {
        List<String> fileContents = new ArrayList<>();
        File folder = new File(directoryPath);

        if (!folder.exists() || !folder.isDirectory()) {
            throw new IllegalArgumentException("Der angegebene Pfad ist kein gültiges Verzeichnis: " + directoryPath);
        }

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files == null) {
            return fileContents;
        }

        for (File file : files) {
            try {
                String content = new String(Files.readAllBytes(file.toPath()));
                fileContents.add(content);
            } catch (IOException e) {
                System.err.println("Fehler beim Lesen der Datei: " + file.getName());
            }
        }
        return fileContents;
    }

    @Override
    public void saveLeaderboardFile(String path, String content) {

    }
}
