package com.example.memory_mate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemDetailsActivity extends AppCompatActivity {
    //= intent.getLongExtra("roomId", -1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        // Retrieve the item_id from the intent

        //long itemId = getIntent().getLongExtra("item_id", -1);

        // Initialize UI elements

        // Fetch the item details based on itemId and display them
       /* Item item = fetchItemDetails(itemId);

        // Update the UI with the item details
        if (item != null) {
            TextView itemfromroomid=findViewById(R.id.textView2);
            TextView itemNameTextView = findViewById(R.id.textView3);
            TextView itemDescriptionTextView = findViewById(R.id.textView4);
            ImageView imageforitem=findViewById(R.id.imageView2);

            itemfromroomid.setText((int) itemId);
            itemNameTextView.setText(item.getName());
            itemDescriptionTextView.setText(item.getDescription());
            // Check if item has an image and set it to the ImageView
            if (item.getImage() != null) {
                byte[] imageData = item.getImage();
                imageforitem.setImageBitmap(BitmapFactory.decodeByteArray(imageData, 0, imageData.length));
            }
        }*/
        Intent intent = getIntent();
        long itemId = intent.getLongExtra("item_id", -1);
        long roomId = intent.getLongExtra("roomId", -1);

        GetItemTask getItemTask = new GetItemTask(this);
        getItemTask.execute(itemId);

        Button gobacktoroom=findViewById(R.id.button10);
        gobacktoroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the room ID for Room 1
                //Intent intent1 = getIntent();
                //long roomId = intent1.getLongExtra("roomId",-1); // -1 is a default value in case the extra is not found

                // Create an intent to start the RoomActivity
                Intent intent = new Intent(ItemDetailsActivity.this, RoomActivity.class);

                // Pass the room ID as an extra to the intent
                intent.putExtra("roomId", roomId);

                // Start the RoomActivity
                startActivity(intent);
            }
        });


    }

    public void updateUI(Item item) {
        if (item != null) {
            // Update the UI with the loaded item's details
            TextView itemfromroomid=findViewById(R.id.textView2);
            TextView itemNameTextView = findViewById(R.id.textView3);
            TextView itemDescriptionTextView = findViewById(R.id.textView4);
            ImageView imageforitem=findViewById(R.id.imageView2);

            itemfromroomid.setText(String.valueOf(item.getId()));
            itemNameTextView.setText(item.getName());
            itemDescriptionTextView.setText(item.getDescription());
            // Check if item has an image and set it to the ImageView
            if (item.getImage() != null) {
                byte[] imageData = item.getImage();
                imageforitem.setImageBitmap(BitmapFactory.decodeByteArray(imageData, 0, imageData.length));
            }

            // You can also load and display the image here if needed
        }
    }

    // Implement a method to fetch item details from your database
    public class GetItemTask extends AsyncTask<Long, Void, Item> {
        private final ItemDetailsActivity activity;

        public GetItemTask(ItemDetailsActivity activity) {
            this.activity = activity;
        }

        @Override
        protected Item doInBackground(Long... params) {
            if (params.length > 0) {
                long itemId = params[0];
                AppDatabase db = ((MyApplication) getApplication()).getDatabase();
                ItemDao itemDao = db.itemDao();
                return itemDao.getItemById(itemId);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Item item) {
            if (activity != null && item != null) {
                // Update the UI with the loaded item's details
                activity.updateUI(item);
            }
        }
    }
}