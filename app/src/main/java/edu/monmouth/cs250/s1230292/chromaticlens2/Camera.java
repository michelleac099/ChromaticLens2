package edu.monmouth.cs250.s1230292.chromaticlens2;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.view.View;


public class Camera extends Object{



    //checks for camera in device
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            return true;
        } else {
            return false;
        }
    }

    //instance of the Camera obj
    public static android.hardware.Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return null; // returns null if camera is unavailable
    }

    public static Camera open() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


        return null;
    }


    public interface PictureCallback {
         //?? from phtohandler
    }
}
