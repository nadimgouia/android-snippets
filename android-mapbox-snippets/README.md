android-mapbox-snippets
========================

####Description

This repo simply contains notes for setting up an Android Studio project using the [Mapbox Android SDK](https://www.mapbox.com/mapbox-android-sdk/).

####Android Studio Project Setup

1) In the app module's build.gradle file (Module: app), add dependencies for the Mapbox Android SDK ...

````
repositories {
    mavenCentral()
}
 
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'com.android.support:appcompat-v7:21.0.2'
    compile ('com.mapbox.mapboxsdk:mapbox-android-sdk:0.5.0@aar'){
        transitive=true
    }
    compile ('com.cocoahero.android:geojson:1.0.0@aar'){
        transitive=true
    }
}

````

2) Add permission to the AndroidManifest.xml file ...

````
	<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />

````

3) Add a Mapbox SDK MapView to an Activity's layout ...

````
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    android:paddingBottom="@dimen/activity_vertical_margin"
    tools:context=".MainActivity">
 
    <TextView android:text="@string/hello_world" android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
 
    <com.mapbox.mapboxsdk.views.MapView
        android:id="@+id/mapview"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent" />
 
</RelativeLayout>

````

4) Use the MapView in your Activity.  Note, update the MapboxTileLayer() to reference your own Mapbox map id.

````
package com.spatialdev.prototype_mapboxandroidsdk;
 
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.mapbox.mapboxsdk.views.MapView;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.overlay.Icon;
import com.mapbox.mapboxsdk.tileprovider.tilesource.*;
import com.mapbox.mapboxsdk.overlay.*;
 
public class MainActivity extends ActionBarActivity {
 
    private MapView mapView;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
 
        super.onCreate(savedInstanceState);
 
        setContentView(R.layout.activity_main);
 
        //instantiate map
        this.mapView = (MapView)findViewById(R.id.mapview);
 
        //set the map tile
        this.mapView.setTileSource(new MapboxTileLayer("YOUR-MAP-ID-GOES-HERE.map-4o51gab2"));
 
        //add marker of Seattle
        LatLng seattleCoordinate = new LatLng(47.606209, -122.332071);
        Marker m = new Marker(this.mapView, "Seattle", "yo", seattleCoordinate);
        m.setIcon(new Icon(this, Icon.Size.SMALL, "marker-stroked", "FF0000"));
        this.mapView.addMarker(m);

        //set default map extent
        mapView.setCenter(seattleCoordinate);
        mapView.setZoom(12);
    }
 
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
 
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
 
        return super.onOptionsItemSelected(item);
    }
}
````

####Connect
* Twitter: [@clintcabanero](http://twitter.com/clintcabanero)
* GitHub: [ccabanero](http:///github.com/ccabanero)

####References

[Mapbox instructions](https://www.mapbox.com/mapbox-android-sdk/#gradle)