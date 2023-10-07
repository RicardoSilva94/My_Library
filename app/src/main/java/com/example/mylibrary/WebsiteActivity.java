package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class WebsiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String youtubeVideoUrl = "https://www.youtube.com/watch?v=wJwwFaCzZd4";


        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeVideoUrl));
        startActivity(intent);


        finish();
    }
}
