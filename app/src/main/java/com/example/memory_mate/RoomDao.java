package com.example.memory_mate;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RoomDao {
    @Query("SELECT * FROM rooms")
    List<Room> getAllRooms();

    @Query("SELECT name FROM rooms WHERE id = :roomId")
    String getRoomNameById(long roomId);

    @Query("SELECT * FROM rooms WHERE id = :roomId")
    Room getRoomById(long roomId);

    // Other CRUD operations...
    @Insert
    void insertRoom(Room room);

    @Update
    void updateRoomName(Room room);



}
