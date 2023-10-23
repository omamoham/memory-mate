package com.example.memory_mate;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class AddItemActivity extends AppCompatActivity {

    // Define your views and variables
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private byte[] itemImage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        // Initialize your views and set click listeners
        EditText itemNameEditText = findViewById(R.id.itemNameEditText);
        EditText itemDescriptionEditText = findViewById(R.id.itemDescriptionEditText);
        Button addPictureButton = findViewById(R.id.button8);
        Button saveItemButton = findViewById(R.id.button9);

        addPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement logic to capture or pick an image
                dispatchTakePictureIntent();
            }
        });

        saveItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the item details
                String itemName = itemNameEditText.getText().toString();
                String itemDescription = itemDescriptionEditText.getText().toString();

                // Check if an image has been captured
                if (itemImage != null) {
                    long roomId = getIntent().getLongExtra("roomId", -1);
                    // Save the item to the database
                    saveItemToDatabase(itemName, itemDescription, itemImage);
                    Intent intent = new Intent(AddItemActivity.this, RoomActivity.class);
                    intent.putExtra("roomId", roomId);
                    // Start the RoomActivity
                    startActivity(intent);

                } else {
                    // Handle the case where no image has been captured
                    Toast.makeText(AddItemActivity.this, "Please capture an image", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Get the captured image
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            // Set the image to the ImageView for preview
            ImageView imagePreview = findViewById(R.id.imageView);
            imagePreview.setImageBitmap(imageBitmap);

            // Convert the image to a byte array (you can use other methods for conversion)
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            itemImage = stream.toByteArray();
        }
    }

    private void saveItemToDatabase(String name, String description, byte[] image) {
        // Get the room ID passed through the intent
        long roomId = getIntent().getLongExtra("roomId", -1); // -1 is the default value if the key is not found

        // Create an Item object
        final Item item = new Item(name, description, image, roomId);

        // Perform the database insertion asynchronously
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                AppDatabase db = ((MyApplication) getApplication()).getDatabase();
                ItemDao itemDao = db.itemDao();
                return itemDao.insert(item);
            }

            @Override
            protected void onPostExecute(Long itemId) {
                // You can handle the result here, e.g., show a success message, set a result, or finish the activity.
                setResult(RESULT_OK);
                finish();
            }
        }.execute();
    }



}