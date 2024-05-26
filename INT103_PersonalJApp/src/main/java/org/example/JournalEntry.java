package org.example;
import java.time.LocalDateTime;

public class JournalEntry {
    private LocalDateTime timeStamp;
    private String textEntry;

    public JournalEntry(LocalDateTime timeStamp, String textEntry) {
        this.timeStamp = timeStamp;
        this.textEntry = textEntry;
    }
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
    public String getTextEntry() {
        return textEntry;
    }




}
