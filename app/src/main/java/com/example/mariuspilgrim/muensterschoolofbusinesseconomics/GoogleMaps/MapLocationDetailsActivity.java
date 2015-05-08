package com.example.mariuspilgrim.muensterschoolofbusinesseconomics.GoogleMaps;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mariuspilgrim.muensterschoolofbusinesseconomics.R;

public class MapLocationDetailsActivity extends Activity {

    public static String EXTRA_IMAGE_ID;
    public static String EXTRA_TITLE;
    public static String EXTRA_ADDRESS;
    public static String EXTRA_DESCRIPTION;
    public static double EXTRA_LATITUDE;
    public static double EXTRA_LONGITUDE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.activity_map_location_details);

            ImageView imageView = (ImageView) findViewById(R.id.wwu_map_location_details_image);
            int resID = getResources().getIdentifier(EXTRA_IMAGE_ID , "drawable", getPackageName());
            imageView.setImageResource(resID);

            TextView textViewTitle = (TextView) findViewById(R.id.wwu_map_location_details_title);
            String textViewTitleHtml = "<b>" + getResources().getString(R.string.map_location_details_view_title) + "</b>" + EXTRA_TITLE;
            textViewTitle.append(Html.fromHtml(textViewTitleHtml));

            TextView textViewAddress = (TextView) findViewById(R.id.wwu_map_location_details_address);
            String textViewAddressHtml = "<b>" + getResources().getString(R.string.map_location_details_view_address) + "</b>" + EXTRA_ADDRESS;
            textViewAddress.append(Html.fromHtml(textViewAddressHtml));

            TextView textViewDescription = (TextView) findViewById(R.id.wwu_map_location_details_description);
            String textViewDescriptionHtml = "<b>" + getResources().getString(R.string.map_location_details_view_description) + "</b>" + EXTRA_DESCRIPTION;
            textViewDescription.append(Html.fromHtml(textViewDescriptionHtml));

            //throw new RuntimeException();
        } catch (Exception ex) {
            messageBox(getResources().getString(R.string.error_oncreate_map_location_details_activity), ex.getMessage());
            ex.printStackTrace();
        }

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_maps_details, menu);
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
            case R.id.action_wwu_locations_list_details:
                openExpandableListView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * open expandable list view
     */
    public void openExpandableListView() {
        Intent intent = new Intent(this, MapLocationSelectionList.class);
        startActivity(intent);
    }

    /**
     * Creating exception handling box
     * @param method
     * @param message
     */
    private void messageBox(String method, String message) {
        AlertDialog.Builder messageBox = new AlertDialog.Builder(this);
        messageBox.setTitle(method);
        messageBox.setMessage(message);
        messageBox.setCancelable(false);
        messageBox.setNeutralButton(getResources().getString(R.string.ok), null);
        messageBox.show();
    }

}