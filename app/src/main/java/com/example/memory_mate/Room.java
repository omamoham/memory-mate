package com.example.memory_mate;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "rooms")
public class Room {
    @PrimaryKey(autoGenerate = true)
    private long id;  // Unique ID for the room in the database
    private String name;  // Name of the room

    @Ignore
    private List<Item> itemList;  // List of items in the room

    public Room() {
        // Empty constructor for Android framework
    }

    public Room(String name) {
        this.name = name;
        this.itemList = new ArrayList<>();
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

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    // Methods to manipulate items within the room

    public void addItem(Item item) {
        itemList.add(item);
    }

    public void removeItem(Item item) {
        itemList.remove(item);
    }

    public void updateItem(int index, Item newItem) {
        if (index >= 0 && index < itemList.size()) {
            itemList.set(index, newItem);
        }
    }

    public List<Item> getItems() {
        return itemList;
    }
}

