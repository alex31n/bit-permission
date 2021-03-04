package com.ornach.bitpermissionproject;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ornach.bitpermission.BitPermission;
import com.ornach.bitpermission.PermissionListener;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        PermissionListener listener = new PermissionListener() {

            @Override
            public void onPermissionGranted(ArrayList<String> grantedPermissions) {
                // Permission granted
                // your code here
                Log.d(TAG, "onPermissionGranted: " + Arrays.toString(grantedPermissions.toArray()));
                Toast.makeText(MainActivity.this, "Permission granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                // Permission denied
                Log.e(TAG, "onPermissionDenied: " + Arrays.toString(deniedPermissions.toArray()));

            }
        };

        ArrayList<String> list = new ArrayList<String>();
        list.add(Manifest.permission.CALL_PHONE);
        list.add(Manifest.permission.CAMERA);

        // for android 10 or up
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            list.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION);
        }

        BitPermission bitPermission = BitPermission.with(this)
                .setPermissionListener(listener)
                .setPermissions(list)
                .build();
        bitPermission.request();
    }


}