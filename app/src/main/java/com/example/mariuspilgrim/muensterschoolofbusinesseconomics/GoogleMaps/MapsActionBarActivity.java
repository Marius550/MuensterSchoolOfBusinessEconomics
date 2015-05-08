package com.example.mariuspilgrim.muensterschoolofbusinesseconomics.GoogleMaps;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mariuspilgrim.muensterschoolofbusinesseconomics.MainActivity;
import com.example.mariuspilgrim.muensterschoolofbusinesseconomics.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActionBarActivity extends FragmentActivity {

    public static String EXTRA_ADDRESS_TITLE = "Münster School of Business and Economics";
    public static String EXTRA_ADDRESS = "Universitätsstraße 14, 48143 Münster";
    public static final double EXTRA_LATITUDE = 51.962168;
    public static final double EXTRA_LONGITUDE = 7.620359;

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_maps, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action buttons
        switch(item.getItemId()) {
            case R.id.action_websearch:
                // create intent to perform web search for this planet
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
                // catch event that there's no activity to handle intent
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, R.string.browser_not_available, Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.action_help:
                openHelp();
                return true;
            case R.id.action_wwu_locations_list:
                openExpandableListView();
                return true;
            case android.R.id.home:
                openMainActivity();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Open In-App help
     */
    public void openHelp() {
        messageBox(getResources().getString(R.string.map_help_title),getResources().getString(R.string.map_help_text));
    }

    /**
     * open expandable list view
     */
    public void openExpandableListView() {
        Intent intent = new Intent(this, MapLocationSelectionList.class);
        startActivity(intent);
    }

    /**
     * open main activity
     */
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link com.google.android.gms.maps.SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(android.os.Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {

        Location campus  = new Location(LocationManager.NETWORK_PROVIDER);

        campus.setLatitude(EXTRA_LATITUDE);
        campus.setLongitude(EXTRA_LONGITUDE);

        Marker marker = mMap.addMarker(
                new MarkerOptions()
                .position(new LatLng(EXTRA_LATITUDE, EXTRA_LONGITUDE))
                .title(EXTRA_ADDRESS_TITLE)
                .snippet(EXTRA_ADDRESS)
        );
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(campus.getLatitude(), campus.getLongitude()), 17));//Determines the initial zoom level

    }

    /**
     * Creating notification box
     * @param method
     * @param message
     */
    public void messageBox(String method, String message) {
        AlertDialog.Builder messageBox = new AlertDialog.Builder(this);
        messageBox.setTitle(method);
        messageBox.setMessage(message);
        messageBox.setCancelable(false);
        messageBox.setNeutralButton(getResources().getString(R.string.ok), null);
        messageBox.show();
    }

}