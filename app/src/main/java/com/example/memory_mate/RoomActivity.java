package com.example.memory_mate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class RoomActivity extends AppCompatActivity {

    //private RecyclerView recyclerView=findViewById(R.id.recyclerView);
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        TextView RoomName= findViewById(R.id.textView);
        Button AddItem=findViewById(R.id.button7);
        Button gotohome=findViewById(R.id.button13);

        Intent intent = getIntent();
        long roomId = intent.getLongExtra("roomId",-1); // -1 is a default value in case the extra is not found

        new FetchRoomNameTask(roomId,RoomName).execute();

        // Create an instance of FetchRoomItemsTask and execute it
        FetchRoomItemsTask fetchRoomItemsTask = new FetchRoomItemsTask();
        fetchRoomItemsTask.execute(roomId);

        AddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the room ID for Room 1
                Intent intent1 = getIntent();
                long roomId = intent1.getLongExtra("roomId",-1); // -1 is a default value in case the extra is not found

                // Create an intent to start the RoomActivity
                Intent intent = new Intent(RoomActivity.this, AddItemActivity.class);

                // Pass the room ID as an extra to the intent
                intent.putExtra("roomId", roomId);

                // Start the RoomActivity
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter = new ItemAdapter(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Item item) {
                Intent intent2 = getIntent();
                long roomId = intent2.getLongExtra("roomId",-1);
                Intent intent = new Intent(RoomActivity.this, ItemDetailsActivity.class);
                intent.putExtra("item_id", item.getId());
                intent.putExtra("roomId", roomId);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(itemAdapter);

        gotohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to start the RoomActivity
                Intent intent = new Intent(RoomActivity.this, MainActivity.class);
                // Start the RoomActivity
                startActivity(intent);
            }
        });





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
            // Display the room name in the TextView with the "RoomName: " prefix
            String roomNameWithPrefix = "RoomName: " + roomName;
            roomNameTextView.setText(roomNameWithPrefix);
        }
    }

    private class FetchRoomItemsTask extends AsyncTask<Long, Void, List<Item>> {
        @Override
        protected List<Item> doInBackground(Long... params) {
            if (params.length > 0) {
                long roomId = params[0];
                return ((MyApplication) getApplication()).getDatabase().itemDao().getItemsForRoom(roomId);
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Item> items) {
            if (items != null) {
                itemAdapter.setItems(items);
                //for (Item item : items) {
                   // Log.d("ItemAdapter", "Item Name: " + item.getName());
                }
            }
        }


}