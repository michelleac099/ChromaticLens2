package edu.monmouth.cs250.s1230292.chromaticlens2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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
        imageView.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(new   float[] {
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
        imageView.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(new   float[] {
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
        imageView.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(new   float[] {
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

}



