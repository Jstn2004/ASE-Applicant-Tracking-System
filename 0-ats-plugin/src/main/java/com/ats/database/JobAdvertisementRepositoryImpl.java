package com.ats.database;

import com.ats.entities.JobAdvertisement;
import com.ats.repositories.JobAdvertisementRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class JobAdvertisementRepositoryImpl implements JobAdvertisementRepository {

    private final String filePath;

    public JobAdvertisementRepositoryImpl(String filePath) {
        this.filePath = filePath;
    }

    public void saveJobAdvertisement(JobAdvertisement jobAdvertisement) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(jobAdvertisement.toString());
            writer.write("\n\n");
            System.out.println("Ausschreibung wurde gespeichert.");
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern der Ausschreibung: " + e.getMessage());
        }
    }

    public List<String> loadTender() {
        List<String> tenders = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    tenders.add(line);
                }
            }
            return tenders;
        } catch (IOException e) {
            System.err.println("Fehler beim Laden der Ausschreibungen: " + e.getMessage());
            return null;
        }
    }

    public void deleteTenderById(String tenderId) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            List<String> updatedLines = lines.stream()
                    .filter(line -> !line.contains("id=" + tenderId))
                    .collect(Collectors.toList());
            Files.write(Paths.get(filePath), updatedLines);
        } catch (IOException e) {
            throw new RuntimeException("Fehler bei löschen eines Tenders", e);
        }
    }

}
