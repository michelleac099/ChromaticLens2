package edu.monmouth.cs250.s1230292.chromaticlens2;

import android.Manifest;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ImportPicture extends Activity {

    Button saveImage;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.import_screen);
        imageView = (ImageView)findViewById(R.id.imageView);

        saveImage = findViewById(R.id.btn_save);

        //IMPORT BTN
        Button gallery = findViewById(R.id.gallery);

        ActivityCompat.requestPermissions(ImportPicture.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        ActivityCompat.requestPermissions(ImportPicture.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);

            }
        });

        saveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToGallery();
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
                (float) 0.950,  (float) 0.050, 0, 0, 0,
                0,  (float) 0.433,  (float) 0.567, 0, 0,
                0,  (float) 0.475,  (float) 0.525, 0, 0,
                0, 0, 0, 1, 0

                // R’ = a*R + b*G + c*B + d*A + e;
                //  G’ = f*R + g*G + h*B + i*A + j;
                //  B’ = k*R + l*G + m*B + n*A + o;
                //  A’= p*R + q*G + r*B + s*A + t;
        })));
    }


    // Deuteran = protan + more yellow
    // NO GREEN
    public void onDueClick(View view) {
        ImageView imageView = findViewById(R.id.imageView);
        ColorMatrix matrix3 = new ColorMatrix();
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix3);
        imageView.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(new float[]{
                (float)0.625, (float)0.375, 0, 0, 0,
                (float)0.7, (float)0.3, 0, 0, 0,
                0, (float)0.3, (float)0.7, 0, 0,
                0, 0, 0, 1, 0
        })));
    }

    // on protanopia click
    // NO RED
    public void onProClick(View view) {

        ImageView imageView = findViewById(R.id.imageView);
        ColorMatrix matrix4 = new ColorMatrix();
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix4);
        imageView.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(new float[]{
                (float) 0.567, (float) 0.433, 0, 0, 0,
                (float) 0.558, (float) 0.442, 0, 0, 0,
                0, (float) 0.242, (float) 0.758, 0, 0,
                0, 0, 0, 1, 0

        // R’ = a*R + b*G + c*B + d*A + e;
                //  G’ = f*R + g*G + h*B + i*A + j;
                //  B’ = k*R + l*G + m*B + n*A + o;
                //  A’= p*R + q*G + r*B + s*A + t;
        })));
    }

    public void saveToGallery() {

        ActivityCompat.requestPermissions(ImportPicture.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();

        FileOutputStream outputStream = null;
        File file = Environment.getExternalStorageDirectory();
        File dir = new File(file.getAbsolutePath() + "/MyPics");
        dir.mkdirs();

        String filename = String.format("%d.png",System.currentTimeMillis());
        File outFile = new File(dir,filename);
        try{
            outputStream = new FileOutputStream(outFile);
        }catch (Exception e){
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        try{
            outputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            outputStream.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}

