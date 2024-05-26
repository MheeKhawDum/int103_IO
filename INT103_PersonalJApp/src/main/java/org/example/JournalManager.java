package org.example;

import java.io.*;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.nio.file.*;

public class JournalManager {

    private File journalFile;

    // Constructor that accepts a file path as a String
    public JournalManager(String filePath) {
        this.journalFile = new File(filePath);
    }

    public void writeEntry(JournalEntry entry) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(journalFile, true))) {
            writer.write(entry.getTimeStamp().format(formatter) + " - " + entry.getTextEntry() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<JournalEntry> readEntries() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        List<JournalEntry> entries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(journalFile))) {
            String textRead;
            while ((textRead = reader.readLine()) != null) {
                String[] parts = textRead.split(" - ", 2);
                LocalDateTime timestamp = LocalDateTime.parse(parts[0], formatter);
                String textEntry = parts[1];
                entries.add(new JournalEntry(timestamp, textEntry));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }

    public void deleteEntries(LocalDateTime timestamp) {
        List<JournalEntry> entries = readEntries();
        File temporaryFile = new File("temp.txt");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(temporaryFile))) {
            for (JournalEntry entry : entries) {
                if (!entry.getTimeStamp().equals(timestamp)) {
                    writer.write(entry.getTimeStamp().format(formatter) + " - " + entry.getTextEntry() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.move(temporaryFile.toPath(), journalFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(journalFile))) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


