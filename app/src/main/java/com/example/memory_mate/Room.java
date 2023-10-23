package com.example.memory_mate;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "rooms")
public class Room {
    @PrimaryKey(autoGenerate = false)
    private long id;  // Unique ID for the room in the database
    private String name;  // Name of the room


    public Room() {
        // Empty constructor for Android framework
    }

    public Room(long id, String name) {
        this.id=id;
        this.name = name;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

