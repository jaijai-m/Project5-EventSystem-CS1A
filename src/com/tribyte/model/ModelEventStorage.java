package com.tribyte.model;

import java.util.ArrayList;
import java.util.List;

public class ModelEventStorage {
    public static final List<ModelEvents> eventList = new ArrayList<>();

    //Temporary sstorage for FormEvents
    
    static {
        // Event owned by someone else (ID 101)
        eventList.add(new ModelEvents(1, 101, "CCS Week: Day 1", "April 26, 2026", "Main Lobby", 15, 100, "Open", "9:52 AM", "3:37 PM", "Dr. Smith", "Public", "NONE"));

        // Event owned by YOU (ID 102)
        eventList.add(new ModelEvents(2, 102, "CCS Week: Day 2", "April 27, 2026", "Gymnasium", 100, 100, "Open", "---", "---", "Kenneth Bautista", "Private", "CCS123"));
    }
}
