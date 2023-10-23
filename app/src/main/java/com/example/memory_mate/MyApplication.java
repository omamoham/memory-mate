package com.example.memory_mate;

import androidx.room.Room;
import android.app.Application;

public class MyApplication extends Application {
    private AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize your database here
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "my-database").build();

        // Other application-level initialization if needed
    }

    public AppDatabase getDatabase() {
        return appDatabase;
    }
}
