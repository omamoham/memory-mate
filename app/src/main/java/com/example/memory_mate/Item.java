package com.example.memory_mate;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items")
public class Item {

    @PrimaryKey(autoGenerate = true)
    private long id;  // Unique ID for the item in the database
    private String name;  // Name of the item
    private String description;  // Description of the item
    private byte[] image;  // Byte array to store the image (you may use a path or URI as well)

    @ColumnInfo(name = "room_id")
    private long roomId;  // This represents the foreign key reference to the Room entity.


    public Item(long id, String name, String description, byte[] image, long roomId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.roomId = roomId;
    }

    public long getId() {
        return id;
    }

    public long getRoomId() {
        return roomId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getImage() {
        return image;
    }
}
