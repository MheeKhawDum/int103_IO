package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        JournalManager journalManager = new JournalManager("journal.txt");
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        while (true) {
            System.out.println("1. Write an entry");
            System.out.println("2. View entries");
            System.out.println("3. Delete an entry");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Write your journal entry: ");
                    String text = scanner.nextLine();
                    journalManager.writeEntry(new JournalEntry(LocalDateTime.now(), text));
                    break;
                case 2:
                    List<JournalEntry> entries = journalManager.readEntries();
                    for (JournalEntry entry : entries) {
                        System.out.println(entry.getTimeStamp().format(formatter) + " - " + entry.getTextEntry());
                    }
                    break;
                case 3:
                    System.out.print("Enter the timestamp of the entry you want to delete (yyyy/MM/dd HH:mm:ss): ");
                    LocalDateTime deleteTimestamp = LocalDateTime.parse(scanner.nextLine(), formatter);
                    journalManager.deleteEntries(deleteTimestamp);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
