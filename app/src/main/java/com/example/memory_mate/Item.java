package com.example.memory_mate;

public class Item {
    private long id;  // Unique ID for the item in the database
    private String name;  // Name of the item
    private String description;  // Description of the item
    private byte[] image;  // Byte array to store the image (you may use a path or URI as well)

    public Item(long id, String name, String description, byte[] image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public long getId() {
        return id;
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
