
package com.example.mariuspilgrim.muensterschoolofbusinesseconomics;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
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
import android.widget.Toast;

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
        return super.onCreateOptionsMenu(menu);
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
        case R.id.action_websearch:
            // create intent to perform web search for this title
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
            // catch event that there's no activity to handle intent
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
            }
            return true;
        case R.id.action_settings:
             openAndroidSettings();
                return true;
        case R.id.action_browser:
             openAndroidBrowser();
                return true;
        case R.id.action_map:
            goToGoogleMapsFragmentActivity();
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
        intent.setClassName("com.android.settings", "com.android.settings.LanguageSettings");
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
                    selectItemWelcome(position);//Welcome
                    break;
                case 1:
                    selectItemDepartment(position);//Student Administration
                    break;
                case 2:
                    selectItemAffairs(position);//News
                    break;
                case 3:
                    selectItemProspective(position);//Calendar
                    break;
                case 4:
                    selectItemResearch(position);//Directory
                    break;
                case 5:
                    goToGoogleMapsFragmentActivity();//WWU Map
                    break;
                case 6:
                    selectItemCampusFragment(position);//Dining
                    break;
                case 7:
                    selectItemContact(position);//Library
                    break;
                case 8:
                    selectItemWelcome(position);//Settings
                    break;
                case 9:
                    selectItemWelcome(position);//About
                    break;
                default:
                    selectItemWelcome(position);
                    break;
            }
        }
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

    private void selectItemWWUMap(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new WelcomeFragment();
        Bundle args = new Bundle();
        args.putInt(WelcomeFragment.ARG_WELCOME_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        setMenuDrawer(position);
    }

    private void selectItemDepartment(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new DepartmentFragment();
        Bundle args = new Bundle();
        args.putInt(DepartmentFragment.ARG_DEPARTMENT_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        setMenuDrawer(position);
    }

    private void selectItemAffairs(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new AffairsFragment();
        Bundle args = new Bundle();
        args.putInt(AffairsFragment.ARG_AFFAIRS_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        setMenuDrawer(position);
    }

    private void selectItemProspective(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new ProspectiveFragment();
        Bundle args = new Bundle();
        args.putInt(ProspectiveFragment.ARG_PROSPECTIVE_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        setMenuDrawer(position);
    }

    private void selectItemResearch(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new ResearchFragment();
        Bundle args = new Bundle();
        args.putInt(ResearchFragment.ARG_RESEARCH_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        setMenuDrawer(position);
    }

    private void selectItemNews(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putInt(NewsFragment.ARG_NEWS_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        setMenuDrawer(position);
    }

    private void selectItemCampusFragment(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new CampusFragment();
        Bundle args = new Bundle();
        args.putInt(CampusFragment.ARG_CAMPUS_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        setMenuDrawer(position);
    }

    private void selectItemContact(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new ContactFragment(); //Does not use bottom fragment class but own ContactFragment
        Bundle args = new Bundle();
        args.putInt(ContactFragment.ARG_CONTACT_NUMBER, position); //Also adjust to ContactFragment
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

    /**
     * Fragment that appears in the "content_frame", shows an affairs fragment
     */
    public static class AffairsFragment extends Fragment {
        public static final String ARG_AFFAIRS_NUMBER = "AFFAIRS_number";

        public AffairsFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_affairs, container, false);
            int i = getArguments().getInt(ARG_AFFAIRS_NUMBER);
            String menuItem = getResources().getStringArray(R.array.menu_items_array)[i];
            getActivity().setTitle(menuItem);

            return rootView;
        }
    }

    /**
     * Fragment that appears in the "content_frame", shows a prospective fragment
     */
    public static class ProspectiveFragment extends Fragment {
        public static final String ARG_PROSPECTIVE_NUMBER = "PROSPECTIVE_number";

        public ProspectiveFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_prospective, container, false);
            int i = getArguments().getInt(ARG_PROSPECTIVE_NUMBER);
            String menuItem = getResources().getStringArray(R.array.menu_items_array)[i];
            getActivity().setTitle(menuItem);

            return rootView;
        }
    }

    /**
     * Fragment that appears in the "content_frame", shows a research fragment fragment
     */
    public static class ResearchFragment extends Fragment {
        public static final String ARG_RESEARCH_NUMBER = "RESEARCH_number";

        public ResearchFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_research, container, false);
            int i = getArguments().getInt(ARG_RESEARCH_NUMBER);
            String menuItem = getResources().getStringArray(R.array.menu_items_array)[i];
            getActivity().setTitle(menuItem);

            return rootView;
        }
    }

    /**
     * Fragment that appears in the "content_frame", shows a news fragment
     */
    public static class NewsFragment extends Fragment {
        public static final String ARG_NEWS_NUMBER = "NEWS_number";

        public NewsFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_news, container, false);
            int i = getArguments().getInt(ARG_NEWS_NUMBER);
            String menuItem = getResources().getStringArray(R.array.menu_items_array)[i];
            getActivity().setTitle(menuItem);

            return rootView;
        }
    }

}