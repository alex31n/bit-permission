package com.ornach.bitpermission;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class PermissionActivity extends AppCompatActivity {
    private static final String TAG = "bit-permission";

    private static PermissionListener permissionListener;

    private ArrayList<String> list;

    private final int PERMISSIONS_REQUEST_CODE = 1245;

    public static void startActivity(Context context, Intent intent, PermissionListener listener) {
        PermissionActivity.permissionListener = listener;
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_permission);
        overridePendingTransition(0, 0);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        if (getIntent() != null) {
            if (getIntent().hasExtra("LIST")) {
                list = getIntent().getStringArrayListExtra("LIST");
//                Log.e(TAG, "list: " + list.size());
            }
        }

        checkPermission();
    }


    private void checkPermission() {
        ArrayList<String> permissionList = new ArrayList<>();

        for (String per : list) {
            if (ContextCompat.checkSelfPermission(this, per) != PackageManager.PERMISSION_GRANTED) {
//                Log.d(TAG, "permission need: " + per);
                permissionList.add(per);
            }
        }

//        Log.d(TAG, "permissionList.size(): " + permissionList.size());
        if (permissionList.size() > 0) {
            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[0]), PERMISSIONS_REQUEST_CODE);

        } else {
//            Log.d(TAG, "No permission is need");
            if (permissionListener != null) permissionListener.onPermissionGranted(list);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE: {
                permissionResult(permissions);

            }

        }
    }

    private void permissionResult(String[] permissions) {

        ArrayList<String> grantedPermissions = new ArrayList<>();
        ArrayList<String> deniedPermissions = new ArrayList<>();

        for (String permission : permissions) {
            if (PermissionUtils.isGranted(this, permission)) {
                grantedPermissions.add(permission);
            } else {
                deniedPermissions.add(permission);
            }
        }

        if (permissionListener != null) {
            if (grantedPermissions.size() > 0)
                permissionListener.onPermissionGranted(grantedPermissions);
            if (deniedPermissions.size() > 0)
                permissionListener.onPermissionDenied(deniedPermissions);
        }

        PermissionActivity.permissionListener = null;

        finish();
    }



    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
