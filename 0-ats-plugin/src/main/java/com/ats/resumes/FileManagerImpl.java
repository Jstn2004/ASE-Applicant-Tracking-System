package com.ats.resumes;

import com.ats.interfaces.FileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


public class FileManagerImpl implements FileManager {

    private final String inputPath;
    private final String outputPath;

    public FileManagerImpl(String inputPath, String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    @Override
    public List<String> loadResumeFiles() {
        List<String> fileContents = new ArrayList<>();
        File folder = new File(inputPath);

        if (!folder.exists() || !folder.isDirectory()) {
            throw new IllegalArgumentException("Der angegebene Pfad ist kein gültiges Verzeichnis: " + inputPath);
        }

        File[] files = folder.listFiles((_, name) -> name.endsWith(".txt"));
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
    public void saveLeaderboardFile(String jobTitle, List<String> lines) {
        String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new java.util.Date());
        String filename = "Leaderboard_" + jobTitle  + "_" + timestamp + ".txt";

        File file = new File(outputPath, filename);

        try {
            Files.write(file.toPath(), lines);
            System.out.println("\u001B[37mDatei gespeichert als: " + file.getAbsolutePath() + "\u001B[0m");
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern der Datei: " + e.getMessage());
        }
    }

}
