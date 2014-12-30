package com.example.spatialdeveloper.testosmdroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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

        //setContentView(R.layout.activity_main);

        // Setup base map
        final RelativeLayout rl = new RelativeLayout(this);

        this.mOsmv = new MapView(this, 256);
        rl.addView(this.mOsmv, new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        this.mOsmv.setBuiltInZoomControls(true);

        // zoom to the netherlands
        this.mOsmv.getController().setZoom(15);
        this.mOsmv.getController().setCenter(new GeoPoint(47.606209, -122.332071));

        // Add tiles layer
        mProvider = new MapTileProviderBasic(getApplicationContext());
        mProvider.setTileSource(TileSourceFactory.FIETS_OVERLAY_NL);
        this.mTilesOverlay = new TilesOverlay(mProvider, this.getBaseContext());
        this.mOsmv.getOverlays().add(this.mTilesOverlay);

        this.setContentView(rl);
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
