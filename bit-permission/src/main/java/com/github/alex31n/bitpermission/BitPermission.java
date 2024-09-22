package com.github.alex31n.bitpermission;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;

public class BitPermission {

    private Builder builder;

    private BitPermission(Builder builder) {
        this.builder = builder;
    }

    public static Builder with(Activity activity) {
        return new Builder(activity);
    }

    public void request(){
        if (this.builder.listener == null) {
            throw new IllegalArgumentException("PermissionListener is missing. You must add PermissionListener");
        } else if (this.builder.permissionList== null || this.builder.permissionList.isEmpty()) {
            throw new IllegalArgumentException("No Permissions found. You must add at lest a permissions");
        }


        for (String per : builder.permissionList){
            if (!hasPermission(builder.activity, per)){
                throw new NullPointerException(per+" Permission is not found in Manifest.");
            }
        }


        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            this.builder.listener.onPermissionGranted(builder.permissionList);
            return;
        }

        Intent intent = new Intent(builder.activity, PermissionActivity.class);
        intent.putStringArrayListExtra("LIST", builder.permissionList);
        //builder.activity.startActivity(intent);
        PermissionActivity.startActivity(builder.activity, intent,builder.listener);
    }

    public static class Builder{
        ArrayList<String> permissionList= new ArrayList<>();
        PermissionListener listener=null;
        Activity activity;

        public Builder(Activity activity){
            this.activity = activity;
        }

        public Builder setPermissionListener(@Nullable PermissionListener l){
            this.listener = l;
            return this;
        }

        public Builder setPermissions(ArrayList<String> permissionList){
            this.permissionList = permissionList;
            return this;
        }

        public Builder setPermissions(String... permissions ){
            this.permissionList = new ArrayList<>(Arrays.asList(permissions));
            return this;
        }

        public BitPermission build(){
            //Log.e("TAG", "permissionList: "+permissionList.size());
            return new BitPermission(this);
        }

    }


    //for example, permission can be "android.permission.WRITE_EXTERNAL_STORAGE"
    private boolean hasPermission(Activity activity, String permission) {
        try {
            PackageInfo info = activity.getPackageManager().getPackageInfo(activity.getPackageName(), PackageManager.GET_PERMISSIONS);
            if (info.requestedPermissions != null) {
                for (String p : info.requestedPermissions) {
                    if (p.equals(permission)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
