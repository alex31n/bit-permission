# Bit Permission
 A simple library that will help you to request permission for Android M and newer version
 &nbsp;
 &nbsp;

### Installation
&nbsp;
Step 1. Add the JitPack repository to your build file  
Add it in your root build.gradle at the end of repositories:
```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency
```gradle
dependencies {
    implementation 'com.github.alex31n:bit-permission:2.0.1'
}
```

&nbsp;
#### Usages

Easy step you need to follow
- Create a Permission builder
- Declare a PermissionListener
- Add all permission to a ArrayList<String>
- Request for permission


&nbsp;
&nbsp;
&nbsp;
#### Code Example

Declare a PermissionListener
```
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
		
```
&nbsp;

Make a request for multiple permission
```
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

```

&nbsp;
For single permission
```
BitPermission bitPermission = BitPermission.with(this)
			  .setPermissionListener(listener)
			  .setPermissions(Manifest.permission.CALL_PHONE)
			  .build();
bitPermission.request();
```


&nbsp;
## Change log
- Migrate to androidx
- change grant listener with granted permission
- added new permission for Android 10

&nbsp;
&nbsp;
## License
    Copyright 2017 Alex Beshine
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and limitations under the License.
