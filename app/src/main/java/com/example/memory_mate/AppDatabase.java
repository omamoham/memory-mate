package com.example.memory_mate;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Room.class, Item.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RoomDao roomDao();
    public abstract ItemDao itemDao();


}
