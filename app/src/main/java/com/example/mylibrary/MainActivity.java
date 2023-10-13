package com.example.mylibrary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    // Declare buttons and a floating action button
    private Button btnAllBooks, btnAlreadyRead, btnWantToRead, btnCurrentlyReading, btnFavorite, btnAbout;
    private FloatingActionButton btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the views (buttons)
        initViews();

        // Set click listeners for different buttons to handle their actions
        btnAllBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When btnAllBooks is clicked, navigate to the AllBooksActivity
                Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
                startActivity(intent);
            }
        });

        btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When btnAlreadyRead is clicked, navigate to the AlreadyReadBookActivity
                Intent intent = new Intent(MainActivity.this, AlreadyReadBookActivity.class);
                startActivity(intent);
            }
        });

        btnCurrentlyReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When btnCurrentlyReading is clicked, navigate to the CurrentlyReadingActivity
                Intent intent = new Intent(MainActivity.this, CurrentlyReadingActivity.class);
                startActivity(intent);
            }
        });

        btnWantToRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When btnWantToRead is clicked, navigate to the WantToReadActivity
                Intent intent = new Intent(MainActivity.this, WantToReadActivity.class);
                startActivity(intent);
            }
        });

        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When btnFavorite is clicked, navigate to the FavoriteActivity
                Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
                startActivity(intent);
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When btnAbout is clicked, show an AlertDialog with a message and options
                // to visit a YouTube video link or dismiss the dialog.
                // If "Visit" is clicked, it opens the YouTube link in an external browser.
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getString(R.string.app_name));
                builder.setMessage("This is my first project on my path to learn to develop Android Applications.\n"+
                        "Check out this video of my first web application!");
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("Visit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Abre o link do YouTube num navegador externo
                        String youtubeVideoUrl = "https://www.youtube.com/watch?v=wJwwFaCzZd4";
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeVideoUrl));
                        startActivity(intent);
                    }
                });
                builder.create().show();
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When btnAdd is clicked, navigate to the AddBookActivity
                Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
                startActivity(intent);
            }
        });


        // Initialize and set up data structures (lists) using a Utils class
        //calling the constructor of the Utils class and initializing the lists
        Utils.getInstance(this);

    }

    // Initialize the view elements (buttons) by finding them in the layout XML

    private void initViews(){
        btnAllBooks = findViewById(R.id.btnAllBooks);
        btnAlreadyRead = findViewById(R.id.btnAlreadyRead);
        btnWantToRead = findViewById(R.id.btnWantToRead);
        btnCurrentlyReading = findViewById(R.id.btnCurrentlyReading);
        btnFavorite = findViewById(R.id.btnFavorite);
        btnAbout = findViewById(R.id.btnAbout);
        btnAdd = findViewById(R.id.btnAdd);
    }

}

// The code is for an Android app's main activity, which sets up click listeners for various buttons to perform different actions, such as navigating to different activities or showing an AlertDialog.
//It also initializes view elements by finding them in the layout XML. Additionally, it initializes data structures through a Utils class.