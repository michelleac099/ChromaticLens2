package edu.monmouth.cs250.s1230292.chromaticlens2;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

public class Screen2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen2);
    }

    public void onImportClick(View view) {
        importPic();
    }

    public void onLearnClick(View view) {
        learn();
    }

    public void onCaptureClick(View view) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        camera();
    }

    //opens the learn page
    public void learn() {
        Intent intent = new Intent(this, Learn.class);
    }

    //opens the camera page
    public void camera() {
        Intent intent = new Intent(this, Camera.class);

    }

    //opens the import pic page
    public void importPic() {
        //FILL IN
    }
}