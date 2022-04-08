package edu.monmouth.cs250.s1230292.chromaticlens2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //called when the user taps the "get started" button
    public void onClick(View view) {
        Intent intent = new Intent(this, Screen2.class);
        startActivity(intent);
    }

}