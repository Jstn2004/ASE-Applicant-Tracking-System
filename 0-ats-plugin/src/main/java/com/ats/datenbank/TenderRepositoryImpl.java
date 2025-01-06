package com.ats.datenbank;

import com.ats.entities.Tender;
import com.ats.interfaces.TenderRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TenderRepositoryImpl implements TenderRepository {

    private static final String FILE_PATH;

    static {
        String classPath = TenderRepositoryImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        File currentDirectory = new File(classPath).getParentFile();
        if (currentDirectory.getAbsolutePath().contains("target")) {
            currentDirectory = currentDirectory.getParentFile();
        }
        FILE_PATH = new File(currentDirectory, "TenderDatabase.txt").getAbsolutePath();
    }
    public void saveTender(Tender tender) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(tender.toString());
            writer.write("\n\n");
            System.out.println("Ausschreibung wurde gespeichert.");
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern der Ausschreibung: " + e.getMessage());
        }
    }

    public List<String> loadTender() {
        List<String> tenders = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
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

}
