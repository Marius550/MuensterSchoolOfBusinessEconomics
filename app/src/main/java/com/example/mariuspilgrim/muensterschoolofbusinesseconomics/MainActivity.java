
package com.example.mariuspilgrim.muensterschoolofbusinesseconomics;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.mariuspilgrim.muensterschoolofbusinesseconomics.About.AboutSelectionList;
import com.example.mariuspilgrim.muensterschoolofbusinesseconomics.Athletics.AthleticsSelectionList;
import com.example.mariuspilgrim.muensterschoolofbusinesseconomics.GoogleMaps.MapsFragmentActivity;
import com.example.mariuspilgrim.muensterschoolofbusinesseconomics.Library.LibrarySelectionList;

public class MainActivity extends Activity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mMenuItemTitles;

    public static String PACKAGE_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        PACKAGE_NAME = getApplicationContext().getPackageName();

        //Necessary to execute search function
        handleIntent(getIntent());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();
        mMenuItemTitles = getResources().getStringArray(R.array.menu_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mMenuItemTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItemWelcome(0);//Sets welcome item as default start item
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_websearch).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        //before: return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        System.out.println("What is this for?");
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            //query = keyboard input + Uni Münster
            String query = intent.getStringExtra(SearchManager.QUERY) + getResources().getString(R.string.search_added_string_uni_muenster);
            //use the query to search your data somehow

            String googleSearchURL = getResources().getString(R.string.main_menu_search_google_url_q) + query;
            WebViewMainMenuSearch.EXTRA_SEARCH_QUERY_INPUT = query;
            WebViewMainMenuSearch.EXTRA_TARGET_URL = googleSearchURL;
            Intent intentMenuSearch = new Intent(this, WebViewMainMenuSearch.class);
            startActivity(intentMenuSearch);

            /*
            Intent intentWebSearch = new Intent(Intent.ACTION_WEB_SEARCH);
            intentWebSearch.putExtra(SearchManager.QUERY, query);
            if (intentWebSearch.resolveActivity(getPackageManager()) != null) {
                startActivity(intentWebSearch);
            } else {
                Toast.makeText(this, R.string.browser_not_available, Toast.LENGTH_LONG).show();
            }
            */
        }
    }

    /**
     * Creating exception handling box
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

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         // The action bar home/up action should open or close the drawer.
         // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch(item.getItemId()) {
        case R.id.action_settings:
             openAndroidSettings();
                return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Opens Android device settings
     */
    public void openAndroidSettings() {
        //To go to the general settings
        //startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClassName(getResources().getString(R.string.android_settings_path),
                            getResources().getString(R.string.android_settings_language_path));
        startActivity(intent);
    }

    /**
     * Opens Android device browser
     */
    public void openAndroidBrowser() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.browser_default_address)));
        startActivity(browserIntent);
    }

    /**
     * Opens google maps fragment activity
     */
    public void goToGoogleMapsFragmentActivity() {
        Intent intent = new Intent(this, MapsFragmentActivity.class);

        MapsFragmentActivity.EXTRA_IMAGE_ID = "juridicum";
        MapsFragmentActivity.EXTRA_TITLE = "Juridicum";
        MapsFragmentActivity.EXTRA_DESCRIPTION = "JUR 1, JUR 2, JUR 3, JUR 4";
        MapsFragmentActivity.EXTRA_ADDRESS = "Universitätsstraße 14, 48143 Münster";
        MapsFragmentActivity.EXTRA_LATITUDE = 51.961965;
        MapsFragmentActivity.EXTRA_LONGITUDE = 7.619804;

        startActivity(intent);
    }

    /**
     * The click listener for ListView in the navigation drawer
     */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            switch(position) {
                case 0:
                    selectItemWelcome(position);        //Welcome
                    break;
                case 1:
                    goToGoogleMapsFragmentActivity();   //Campus Map
                    break;
                case 2:
                    goToLibraryActivity();              //Library
                    break;
                case 3:
                    goToAboutActivity();                //About
                    break;
                case 4:
                    goToAthleticsActivity();            //Athletics
                    break;
                case 5:
                    selectItemWelcome(position);        //Student Administration
                    break;
                case 6:
                    selectItemWelcome(position);        //News
                    break;
                case 7:
                    selectItemWelcome(position);        //Calendar
                    break;
                case 8:
                    selectItemWelcome(position);        //Directory
                    break;
                case 9:
                    selectItemWelcome(position);        //Dining
                    break;
                case 10:
                    selectItemWelcome(position);        //Settings
                    break;
                case 11:
                    selectItemWelcome(position);        //Admissions
                    break;
                case 12:
                    selectItemWelcome(position);        //Public Transport
                    break;
                case 13:
                    selectItemWelcome(position);        //Social
                    break;
                case 14:
                    selectItemWelcome(position);        //Emergency Info
                    break;
                case 15:
                    selectItemWelcome(position);        //Campus Tour
                    break;
                default:
                    selectItemWelcome(position);
                    break;
            }
        }
    }

    /**
     * Opens Library Selection List activity
     */
    public void goToLibraryActivity() {
        Intent intent = new Intent(this, LibrarySelectionList.class);
        startActivity(intent);
    }

    /**
     * Opens About Selection List activity
     */
    public void goToAboutActivity() {
        Intent intent = new Intent(this, AboutSelectionList.class);
        startActivity(intent);
    }

    /**
     * Opens Athletics Selection List activity
     */
    public void goToAthleticsActivity() {
        Intent intent = new Intent(this, AthleticsSelectionList.class);
        startActivity(intent);
    }

    private void selectItemWelcome(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new WelcomeFragment();
        Bundle args = new Bundle();
        args.putInt(WelcomeFragment.ARG_WELCOME_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        setMenuDrawer(position);
    }

    /**
     * Closes the navigation list after having selected the menu item
     */
    public void setMenuDrawer(int position) {
        mDrawerList.setItemChecked(position, true);
        setTitle(mMenuItemTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Fragment that appears in the "content_frame", shows a welcome fragment
     */
    public static class WelcomeFragment extends Fragment {
        public static final String ARG_WELCOME_NUMBER = "WELCOME_number";

        public WelcomeFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_welcome, container, false);

            //Get menu id / Name to finally set the title it is connected to
            int i = getArguments().getInt(ARG_WELCOME_NUMBER);
            String menuItem = getResources().getStringArray(R.array.menu_items_array)[i];
            getActivity().setTitle(menuItem);

            return rootView;
        }
    }

}