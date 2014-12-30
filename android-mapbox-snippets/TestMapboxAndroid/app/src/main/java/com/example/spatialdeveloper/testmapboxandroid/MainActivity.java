package com.example.spatialdeveloper.testmapboxandroid;

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
