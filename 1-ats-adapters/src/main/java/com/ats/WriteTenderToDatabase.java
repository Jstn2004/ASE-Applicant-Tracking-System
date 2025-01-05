package com.ats;

import com.ats.entities.Tender;

import java.io.*;

public class WriteTenderToDatabase {

    private static final String FILE_PATH = "ausschreibungen.txt";

    public void speichereAusschreibung(Tender ausschreibung) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(ausschreibung.toString());
            writer.write("\n\n");
            System.out.println("Ausschreibung wurde gespeichert.");
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern der Ausschreibung: " + e.getMessage());
        }
    }

    public void ladeAusschreibungen() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Laden der Ausschreibungen: " + e.getMessage());
        }
    }
}
