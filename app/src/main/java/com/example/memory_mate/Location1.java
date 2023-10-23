package com.example.memory_mate;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Location1 extends AppCompatActivity {
    String Room1string;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location1);


        Button buttonRoom1 = findViewById(R.id.button);
        Button buttonRoom2 = findViewById(R.id.button3);
        Button buttonRoom3 = findViewById(R.id.button4);
        Button buttonRoom4 = findViewById(R.id.button5);
        Button buttonRoom5 = findViewById(R.id.button6);

        // Create a Room object
        Room room1 = new Room(1, "Room1");
        Room room2 = new Room(2, "Room2");
        Room room3 = new Room(3, "Room3");
        Room room4 = new Room(4, "Room4");
        Room room5 = new Room(5, "Room5");

        new CheckRoomExistsTask(room1).execute();
        new CheckRoomExistsTask(room2).execute();
        new CheckRoomExistsTask(room3).execute();
        new CheckRoomExistsTask(room4).execute();
        new CheckRoomExistsTask(room5).execute();
        // Using an AsyncTask to insert the room in the background


        // Add a long click listener to the button
        buttonRoom1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                // Show a dialog to enter a new name for Room1
                showRenameDialog(room1,buttonRoom1);
                return true; // Consume the long click event
            }
        });

        buttonRoom2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                // Show a dialog to enter a new name for Room1
                showRenameDialog(room2,buttonRoom2);
                return true; // Consume the long click event
            }
        });

        buttonRoom3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                // Show a dialog to enter a new name for Room1
                showRenameDialog(room3,buttonRoom3);
                return true; // Consume the long click event
            }
        });

        buttonRoom4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                // Show a dialog to enter a new name for Room1
                showRenameDialog(room4,buttonRoom4);
                return true; // Consume the long click event
            }
        });

        buttonRoom5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                // Show a dialog to enter a new name for Room1
                showRenameDialog(room5,buttonRoom5);
                return true; // Consume the long click event
            }
        });


        new GetRoomNameTask(room1, buttonRoom1).execute();
        new GetRoomNameTask(room2, buttonRoom2).execute();
        new GetRoomNameTask(room3, buttonRoom3).execute();
        new GetRoomNameTask(room4, buttonRoom4).execute();
        new GetRoomNameTask(room5, buttonRoom5).execute();

        //buttonRoom1.setText(Room1string);





    }
    private class InsertRoomTask extends AsyncTask<Room, Void, Void> {
        @Override
        protected Void doInBackground(Room... rooms) {
            if (rooms != null && rooms.length > 0) {
                // Insert the provided room into the database on a background thread
                AppDatabase db = ((MyApplication) getApplication()).getDatabase();
                RoomDao roomDao = db.roomDao();
                roomDao.insertRoom(rooms[0]); // Insert the first room
            }
            return null;
        }


    }

    private void showRenameDialog(Room room, Button button) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Rename Room");

        // Create an input field for the new room name
        final EditText input = new EditText(this);
        input.setText(""); // Set the current name as the default text
        builder.setView(input);

        // Set up the buttons for the dialog
        builder.setPositiveButton("Rename", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newName = input.getText().toString();
                // Update the button's text with the new name
                room.setName(newName);
                new UpdateRoomNameTask().execute(room);
                new GetRoomNameTask(room, button).execute();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing, simply close the dialog
            }
        });

        builder.show();
    }

    private class UpdateRoomNameTask extends AsyncTask<Room, Void, Void> {
        @Override
        protected Void doInBackground(Room... rooms) {
            if (rooms != null && rooms.length > 0) {
                AppDatabase db = ((MyApplication) getApplication()).getDatabase();
                RoomDao roomDao = db.roomDao();
                roomDao.updateRoomName(rooms[0]);
            }
            return null;
        }
    }


    private class GetRoomNameTask extends AsyncTask<Void, Void, String> {
        private Room room; // The Room object you want to retrieve the name for
        private Button button; // The Button to update with the room name

        public GetRoomNameTask(Room room, Button button) {
            this.room = room;
            this.button = button;
        }

        @Override
        protected String doInBackground(Void... voids) {
            String roomName = "hello"; // Default value

            if (room != null) {
                AppDatabase db = ((MyApplication) getApplication()).getDatabase();
                RoomDao roomDao = db.roomDao();
                roomName = roomDao.getRoomNameById(room.getId());
            }

            return roomName;
        }

        @Override
        protected void onPostExecute(String roomName) {
            // Update the text of the specified button with the new room name
            if (button != null) {
                button.setText(roomName);
            }
        }
    }


    private class CheckRoomExistsTask extends AsyncTask<Room, Void, Boolean> {
        private Room room;

        public CheckRoomExistsTask(Room room) {
            this.room = room;
        }

        @Override
        protected Boolean doInBackground(Room... rooms) {
            if (room != null) {
                AppDatabase db = ((MyApplication) getApplication()).getDatabase();
                RoomDao roomDao = db.roomDao();
                Room existingRoom = roomDao.getRoomById(room.getId());
                return existingRoom != null;
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean roomExists) {
            // Handle the result here, for example:
            if (!roomExists) {
                // Room doesn't exist, insert it
                new InsertRoomTask().execute(room);
            }
        }
    }

}