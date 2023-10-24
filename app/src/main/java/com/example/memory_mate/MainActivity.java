package com.example.memory_mate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

        String button22 = SharedPreferencesManager.getString(this, "button2", null);

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

        if (button22 == null) {
            // Display a toast to ask for the username
            Toast.makeText(this, "Please enter your name for second location", Toast.LENGTH_SHORT).show();
        }

// Display the username (or "Username" if not set) in the TextView
        TextView usernameTextView = findViewById(R.id.usernameTextView);
        usernameTextView.setText(username != null ? username : "Username");

        // Display the button11 (or "button11" if not set) in the TextView
        Button button1button = findViewById(R.id.button1);
        button1button.setText(button11 != null ? button11 : "Rename");

        Button button2button = findViewById(R.id.button2);
        button2button.setText(button22 != null ? button22 : "Rename");

// Display the current date in the Date TextView
        TextView dateTextView = findViewById(R.id.dateTextView);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        Date currentDate = new Date();
        dateTextView.setText(dateFormat.format(currentDate));

        EditText button1EditText = findViewById(R.id.button1EditText);
        EditText button2EditText = findViewById(R.id.button2EditText);
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

                        button1EditText.setVisibility(View.VISIBLE);
                    }

                    // Hide the EditText
                    usernameEditText.setVisibility(View.GONE);

                    return true; // Return true to indicate that the event has been handled.
                }
                return false; // Return false to indicate that the event has not been handled.
            });

        }
        else {
            // If the username is set, hide the EditText.
            usernameEditText.setVisibility(View.GONE);
        }


            button1EditText.setVisibility(View.GONE);
            boolean isbutton1Set = SharedPreferencesManager.getBoolean(this, "isbutton1Set", false);
            if (!isbutton1Set) {
                // If the button1 is not set, show the EditText.
                if(isUsernameSet) {
                    button1EditText.setVisibility(View.VISIBLE);
                }
                button1EditText.setOnEditorActionListener((v, actionId, event) -> {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        // Hide the keyboard
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(button1EditText.getWindowToken(), 0);

                        // Handle saving the username here or any other actions.
                        String newbuttonname = button1EditText.getText().toString();
                        if (!newbuttonname.isEmpty()) {
                            // Save the username to SharedPreferences using SharedPreferencesManager.
                            SharedPreferencesManager.saveString(MainActivity.this, "button1", newbuttonname);

                            // Set the flag to indicate that the username has been set.
                            SharedPreferencesManager.setBoolean(MainActivity.this, "isbutton1Set", true);

                            // Update the TextView
                            button1button.setText(newbuttonname);
                            button2EditText.setVisibility(View.VISIBLE);
                        }

                        // Hide the EditText
                        button1EditText.setVisibility(View.GONE);

                        return true; // Return true to indicate that the event has been handled.
                    }
                    return false; // Return false to indicate that the event has not been handled.
                });

            } else {
                // If the username is set, hide the EditText.
                button1EditText.setVisibility(View.GONE);
            }

        button2EditText.setVisibility(View.GONE);
        boolean isbutton2Set = SharedPreferencesManager.getBoolean(this, "isbutton2Set", false);
        if (!isbutton2Set) {
            // If the button1 is not set, show the EditText.
            if(isUsernameSet&&isbutton1Set) {
                button2EditText.setVisibility(View.VISIBLE);
            }
            button2EditText.setOnEditorActionListener((v, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Hide the keyboard
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(button2EditText.getWindowToken(), 0);

                    // Handle saving the username here or any other actions.
                    String newbuttonname2 = button2EditText.getText().toString();
                    if (!newbuttonname2.isEmpty()) {
                        // Save the username to SharedPreferences using SharedPreferencesManager.
                        SharedPreferencesManager.saveString(MainActivity.this, "button2", newbuttonname2);

                        // Set the flag to indicate that the username has been set.
                        SharedPreferencesManager.setBoolean(MainActivity.this, "isbutton2Set", true);

                        // Update the TextView
                        button2button.setText(newbuttonname2);
                    }

                    // Hide the EditText
                    button2EditText.setVisibility(View.GONE);

                    return true; // Return true to indicate that the event has been handled.
                }
                return false; // Return false to indicate that the event has not been handled.
            });

        } else {
            // If the username is set, hide the EditText.
            button2EditText.setVisibility(View.GONE);
        }

        button1button.setOnClickListener( new View.OnClickListener() {
            @Override
             public void onClick(View v) {
                 openlocation1();
             }
        });

        button2button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openlocation2();
            }
        });

    }

    public void openlocation1(){
        Intent intent=new Intent(this, Location1.class);
    startActivity(intent);
    }

    public void openlocation2(){
        Intent intent=new Intent(this, Location2.class);
        startActivity(intent);
    }

}