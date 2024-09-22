package com.github.alex31n.bitpermission;

import java.util.ArrayList;

public interface PermissionListener {


    void onPermissionGranted(ArrayList<String> grantedPermissions);
    void onPermissionDenied(ArrayList<String> deniedPermissions);
}
