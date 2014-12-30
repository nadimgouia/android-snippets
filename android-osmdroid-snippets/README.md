android-osmdroid-snippets
================

####Description

This repo simply contains notes for setting up an Android Studio Project using the [OSMDroid](https://github.com/osmdroid/osmdroid) mapping library.

####Android Studio Project Setup

1) Create a new Android project using Android Studio.

2) Open the App module's __build.gradle__ (Module: app) and add dependencies for osmdroid and slf4j ...

````
dependencies {
    .... other dependencies
    compile 'org.osmdroid:osmdroid-android:4.2'
    compile 'org.slf4j:slf4j-simple:1.6.1'
}
````

Sync changes to your build.gradle file.

See [Prerequisites](https://code.google.com/p/osmdroid/wiki/Prerequisites) to understand why __slf4j__ is needed.

3) Add the required permissions to your AndroidManifest.xml file ...

````
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/> 
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
````

4) Test that you can programmatically add an OSMDroid map to your target Activity.


````
…
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import org.osmdroid.tileprovider.MapTileProviderBasic;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.TilesOverlay;
 
public class MainActivity extends ActionBarActivity {
 
    private MapView mOsmv;
    private TilesOverlay mTilesOverlay;
    private MapTileProviderBasic mProvider;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        // Setup base map
        final RelativeLayout rl = new RelativeLayout(this);

        this.mOsmv = new MapView(this, 256);
        rl.addView(this.mOsmv, new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        this.mOsmv.setBuiltInZoomControls(true);

        // zoom to the Seattle
        this.mOsmv.getController().setZoom(15);
        this.mOsmv.getController().setCenter(new GeoPoint(47.606209, -122.332071));

        // Add tiles layer
        mProvider = new MapTileProviderBasic(getApplicationContext());
        mProvider.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        this.mTilesOverlay = new TilesOverlay(mProvider, this.getBaseContext());
        this.mOsmv.getOverlays().add(this.mTilesOverlay);

        this.setContentView(rl);
    }
````

####Connect
* Twitter: [@clintcabanero](http://twitter.com/clintcabanero)
* GitHub: [ccabanero](http:///github.com/ccabanero)

