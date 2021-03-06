package edu.monmouth.cs250.s1230292.chromaticlens2;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Screen2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen2);
    }

    public void onLearnClick(View view) {
        learn(view);
    }

    public void onImportClick(View view) {
        importPic(view);
    }

    public void onCaptureClick(View view) {
       // Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        camera(view);
    }

    //opens the learn page
    public void learn(View view) {
        Intent intent = new Intent(this, Learn.class);
        startActivity(intent);
    }

    //opens the camera page
    public void camera(View view) {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }
    public void importPic(View view) {
        Intent intent = new Intent(this, ImportPicture.class);
        startActivity(intent);
    }
}