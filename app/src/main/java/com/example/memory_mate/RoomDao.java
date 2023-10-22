package com.example.memory_mate;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RoomDao {
    @Query("SELECT * FROM rooms")
    List<Room> getAllRooms();
    // Other CRUD operations...
}
