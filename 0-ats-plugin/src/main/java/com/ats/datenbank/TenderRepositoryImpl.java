package com.ats.datenbank;

import com.ats.entities.Tender;
import com.ats.interfaces.TenderRepository;

import java.io.*;

public class TenderRepositoryImpl implements TenderRepository {

    private static final String FILE_PATH = "ausschreibungen.txt";

    public void saveTender(Tender tender) {
        System.out.println("Bin in der Datenbank");
        System.out.println(tender.toString());
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
//            writer.write(tender.toString());
//            writer.write("\n\n");
//            System.out.println("Ausschreibung wurde gespeichert.");
//        } catch (IOException e) {
//            System.err.println("Fehler beim Speichern der Ausschreibung: " + e.getMessage());
//        }
    }

    public String loadTender() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            return line;
        } catch (IOException e) {
            System.err.println("Fehler beim Laden der Ausschreibungen: " + e.getMessage());
            return null;
        }
    }

}
