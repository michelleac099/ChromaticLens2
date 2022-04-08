package edu.monmouth.cs250.s1230292.chromaticlens2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Learn  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learn);
    }
    public void onClick(View view) {
        Intent intent = new Intent(this, Learn.class);
        startActivity(intent);
    }




}