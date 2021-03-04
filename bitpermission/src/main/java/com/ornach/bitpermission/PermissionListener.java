package com.ornach.bitpermission;

import java.util.ArrayList;


public interface PermissionListener {


    void onPermissionGranted(ArrayList<String> grantedPermissions);
    void onPermissionDenied(ArrayList<String> deniedPermissions);
}
