package com.example.memory_mate;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDao {
    @Query("SELECT * FROM items WHERE room_id = :roomId")
    List<Item> getItemsForRoom(long roomId);

    @Insert
    long insert(Item item);

    @Query("SELECT * FROM items WHERE id = :itemId")
    Item getItemById(long itemId);
    // Other CRUD operations...

}
