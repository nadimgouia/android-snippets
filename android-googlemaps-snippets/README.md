android-googlemaps-snippets
===========================

This repo simply contains notes for setting up an Android project to use [Google Play Services](https://developers.google.com/maps/documentation/android/), creating a Google Maps Android API key, and updating the AndroidManifest.xml using __Android Studio__ Release 1.

####Set up Google Play Services in Android Studio

1) Using the Android Studio SDK Manager (Tools-Android-SDK Manager), confirm that Extras-Google Play Services is installed.

2) Open your build.grade (Module: app) and update the dependencies to include an entry for Google Play Services:

````
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'com.android.support:appcompat-v7:21.0.3'
    compile 'com.google.android.gms:play-services:6.5.+'
}
````

####Getting a Google Maps API Key
1) Each Android app that you make using Google Maps Android V2, requires its own API key. [See documentation here](https://developers.google.com/maps/documentation/android/start#obtain_a_google_maps_api_key)

2) Create a debug key by performing the following in Terminal,

````
cd ~
````

Then generate a debug key ...

````
keytool -genkey -v -keystore ~/.android/debug.keystore -alias androiddebugkey -validity 10000 -storepass android -keypass android
````

Then view the generated key ...

````
keytool -list -v -keystore ~/.android/debug.keystore -alias androiddebugkey -storepass android -keypass android
````

Note the SHA1 fingerprint ... you will use this to create your Google Maps API Key.

3) Use the debug key to create a Google Maps API Key

a) Go to your [Google APIs Console](https://code.google.com/apis/console) and go to the 'API Access' tab.

b) At the bottom of the __Simple API Access__ section, choose 'Create new Android Key'

c) In the text box, enter [SHA1 Fingerprint];com.yourpackage

For Example:
D1:23:4C:56:7B:C8:09:1B:77:FE:3E:22:56:8B:81:2C:F7:09:8C:1A;com.bigleafmobile.googlemapssnippets

Note, if you are not sure what the package name is for your Android app, see your AndroidManifest.xml file.

d) After submitting, your App's Google Maps API key will be generated.

####Adding the Google Maps API Key and Permissions to the AndroidManifest.xml

1) In Android Studio, open your application's AndroidManifest.xml file, and add the following <meta-data> element as a child of the <application> element ...

````
<meta-data
            android:name="com.google.android.maps.v2.API_KEY"
            android:value="ADD_YOUR_API_KEY_HERE"/>
````

2) In your application's AndroidManifest.xml file, add the following <users-permission> elements as a child of the <manifest> element ...

	<uses-permission android:name="android.permission.INTERNET"/>
	<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
	<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
	<!-- The following two permissions are not required to use
     Google Maps Android API v2, but are recommended. -->
	<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
	<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>

3) Finally, in your application's AndroidManifest.xml file, add the following <uses-feature> element as a child of the <manifest> element.

	<!--Open GL ES version 2-->
    <uses-feature
        android:glEsVersion="0x00020000"
        android:required="true"/>

####Add a Map Fragment to an Activity's Layout       
````
<fragment xmlns:android="http://schemas.android.com/apk/res/android"
        android:id="@+id/map"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:name="com.google.android.gms.maps.MapFragment"/>
````

####Using a Map in an Activity

````
....
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

public class MainActivity extends ActionBarActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        // set default map center and zoom level
        LatLng seattleCoordinate = new LatLng(47.606209, -122.332071);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(seattleCoordinate, 2));

        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }
    ....
````

####References

[Google Maps Android V2 - Overview](https://developer.android.com/google/play-services/maps.html)

[Google Maps Android V2 - Getting Started](https://developers.google.com/maps/documentation/android/start#installing_the_google_maps_android_v2_api)

[Google Maps Android - Documentation](https://developers.google.com/maps/documentation/android/)