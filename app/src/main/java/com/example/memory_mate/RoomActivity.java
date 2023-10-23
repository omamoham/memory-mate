package com.example.memory_mate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class RoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        TextView RoomName= findViewById(R.id.textView);
        Button AddItem=findViewById(R.id.button7);

        Intent intent = getIntent();
        long roomId = intent.getLongExtra("roomId",-1); // -1 is a default value in case the extra is not found

        new FetchRoomNameTask(roomId,RoomName).execute();



    }

    private class FetchRoomNameTask extends AsyncTask<Void, Void, String> {
        private long roomId;
        private TextView roomNameTextView;

        FetchRoomNameTask(long roomId, TextView roomNameTextView) {
            this.roomId = roomId;
            this.roomNameTextView = roomNameTextView;
        }

        @Override
        protected String doInBackground(Void... params) {
            String roomName = "Room Not Found"; // Default value

            // Replace this section with your actual database retrieval logic
            // For example, use your RoomDao and database connection
            // to fetch the room name based on the room ID.
            if (roomId != -1) {
                // Assuming you have a RoomDao and an AppDatabase
                AppDatabase db = ((MyApplication) getApplication()).getDatabase();
                RoomDao roomDao = db.roomDao();
                roomName = roomDao.getRoomNameById(roomId);
            }

            return roomName;
        }

        @Override
        protected void onPostExecute(String roomName) {
            // Display the room name in the TextView
            roomNameTextView.setText(roomName);
        }
    }

}