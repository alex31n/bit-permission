# Bit Permission
 A simple library that will help you to request permission for Android M and newer version
 &nbsp;
 &nbsp;

### Installation

#### Gradle 

```
dependencies {
        compile 'com.ornach.bitpermission:bit-permission:1.1'
}
```

&nbsp;
#### Maven

```
<dependency>
    <groupId>com.ornach.bitpermission</groupId>    
    <artifactId>bit-permission</artifactId>
    <version>1.1</version>
    <type>pom</type> 
</dependency>
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
			public void onPermissionGranted() {
				// permission was granted
				// do something what you want
				
			}

			@Override
			public void onPermissionDenied(ArrayList<String> deniedPermissions) {
				// permission denied by user
				// you get all denied permissions as parameter
			}
		};
		
```
&nbsp;

Make a request for multiple permission
```
		
		ArrayList<String> list = new ArrayList<>();
		list.add(Manifest.permission.CALL_PHONE);
		list.add(Manifest.permission.CAMERA);

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
