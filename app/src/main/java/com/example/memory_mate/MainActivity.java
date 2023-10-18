package com.example.memory_mate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if the username is already saved
        String username = SharedPreferencesManager.getString(this, "username", null);

        String button11 = SharedPreferencesManager.getString(this, "button1", null);

// If not saved, show a toast to ask for the username
        if (username == null) {
            // Display a toast to ask for the username
            Toast.makeText(this, "Please enter your username", Toast.LENGTH_SHORT).show();
        }

        // If not saved, show a toast to ask for the button1
        if (button11 == null) {
            // Display a toast to ask for the username
            Toast.makeText(this, "Please enter your name for first location", Toast.LENGTH_SHORT).show();
        }

// Display the username (or "Username" if not set) in the TextView
        TextView usernameTextView = findViewById(R.id.usernameTextView);
        usernameTextView.setText(username != null ? username : "Username");

        // Display the button11 (or "button11" if not set) in the TextView
        Button button1button = findViewById(R.id.button1);
        button1button.setText(button11 != null ? button11 : "Rename");

// Display the current date in the Date TextView
        TextView dateTextView = findViewById(R.id.dateTextView);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        Date currentDate = new Date();
        dateTextView.setText(dateFormat.format(currentDate));


        EditText usernameEditText = findViewById(R.id.usernameEditText);
        boolean isUsernameSet = SharedPreferencesManager.getBoolean(this, "isUsernameSet", false);
        if (!isUsernameSet) {
            // If the username is not set, show the EditText.
            usernameEditText.setVisibility(View.VISIBLE);
            usernameEditText.setOnEditorActionListener((v, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Hide the keyboard
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(usernameEditText.getWindowToken(), 0);

                    // Handle saving the username here or any other actions.
                    String newUsername = usernameEditText.getText().toString();
                    if (!newUsername.isEmpty()) {
                        // Save the username to SharedPreferences using SharedPreferencesManager.
                        SharedPreferencesManager.saveString(MainActivity.this, "username", newUsername);

                        // Set the flag to indicate that the username has been set.
                        SharedPreferencesManager.setBoolean(MainActivity.this, "isUsernameSet", true);

                        // Update the TextView
                        usernameTextView.setText(newUsername);
                    }

                // Hide the EditText
                usernameEditText.setVisibility(View.GONE);

                return true; // Return true to indicate that the event has been handled.
            }
            return false; // Return false to indicate that the event has not been handled.
        });

        } else {
            // If the username is set, hide the EditText.
            usernameEditText.setVisibility(View.GONE);
        }

    }
}