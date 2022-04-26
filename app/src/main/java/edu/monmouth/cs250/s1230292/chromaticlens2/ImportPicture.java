package edu.monmouth.cs250.s1230292.chromaticlens2;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//import com.google.android.material.navigation.NavigationBarView;

public class ImportPicture extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.import_screen);
        Button gallery = findViewById(R.id.gallery);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            ImageView imageView = findViewById(R.id.imageView);
            imageView.setImageURI(selectedImage);

        }
    }

    // monochromatic button click, turns color black and white
    public void onMonoClick(View view) {
        ImageView imageView = findViewById(R.id.imageView);
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        imageView.setColorFilter(filter);
    }

    // no colorblind click
    public void onNoneClick(View view) {
        ImageView imageView = findViewById(R.id.imageView);
        ColorMatrix matrix = new ColorMatrix();
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        imageView.setColorFilter(filter);
    }

    //tritanopia click,
    // NO BLUE
    public void onTriClick(View view) {
        ImageView imageView = findViewById(R.id.imageView);
        ColorMatrix matrix2 = new ColorMatrix();
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix2);
        imageView.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(new float[]{
                1, 1, 0, 0, 1,
                0, 0, 1, 0, 1,
                0, 1, 1, 0, 0,
                1, 1, 0, 0, 0
                // R’ = a*R + b*G + c*B + d*A + e;
                //  G’ = f*R + g*G + h*B + i*A + j;
                //  B’ = k*R + l*G + m*B + n*A + o;
                //  A’= p*R + q*G + r*B + s*A + t;
        })));
    }

    //on deutranopia click
    // Deuteran = protan + more yellow
    // NO GREEN
    public void onDueClick(View view) {
        ImageView imageView = findViewById(R.id.imageView);
        ColorMatrix matrix3 = new ColorMatrix();
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix3);
        imageView.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(new float[]{
                1, 1, 0, 0, 1,
                1, 1, 0, 0, 0,
                0, 1, 1, 0, 0,
                1, 0, 0, 1, 0
        })));
    }

    // on protanopia click
    // NO RED
    public void onProClick(View view) {
        ImageView imageView = findViewById(R.id.imageView);
        ColorMatrix matrix4 = new ColorMatrix();
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix4);
        imageView.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(new float[]{
                1, 1, 0, 0, 1,
                1, 1, 0, 0, 0,
                0, 1, 1, 0, 0,
                1, 0, 0, 1, 0
                // R’ = a*R + b*G + c*B + d*A + e;
                //  G’ = f*R + g*G + h*B + i*A + j;
                //  B’ = k*R + l*G + m*B + n*A + o;
                //  A’= p*R + q*G + r*B + s*A + t;
        })));
    }

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    public void imageToRoll(View view) {
        Intent photo = new Intent(Intent.ACTION_PICK);


    }


    /** Create a file Uri for saving an image or video */
    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

}