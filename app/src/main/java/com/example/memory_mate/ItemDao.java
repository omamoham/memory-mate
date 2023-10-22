package com.example.memory_mate;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDao {
    @Query("SELECT * FROM items WHERE room_id = :roomId")
    List<Item> getItemsForRoom(long roomId);
    // Other CRUD operations...
}
