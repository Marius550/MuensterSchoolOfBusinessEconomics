package com.example.mariuspilgrim.muensterschoolofbusinesseconomics;

/**
 * Created by mariuspilgrim on 19/03/15.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class MapLocationSelectionList extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_audit_list);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub

                switch(groupPosition) {
                    case 0:
                        if        (id == 0) {
                            startNewAuditCreationActivity(groupPosition, childPosition, id, "Security Training");

                            Intent intent = new Intent(getApplicationContext(), MapsFragmentActivity.class);
                            startActivity(intent);

                            System.out.println("Security Training");
                        } else if (id == 1) {
                            startNewAuditCreationActivity(groupPosition, childPosition, id, "Work Experience");
                            System.out.println("Work Experience");
                        } else if (id == 2) {
                            startNewAuditCreationActivity(groupPosition, childPosition, id, "Educational Level");
                            System.out.println("Educational Level");
                        } else if (id == 3) {
                            startNewAuditCreationActivity(groupPosition, childPosition, id, "Career Path");
                            System.out.println("Career Path");
                        } else if (id == 4) {
                            startNewAuditCreationActivity(groupPosition, childPosition, id, "Customs");
                            System.out.println("Customs");
                        } else {
                            startNewAuditCreationActivity(groupPosition, childPosition, id, "Other...");
                            System.out.println("Other...");
                        }
                        break;
                    case 1:
                        if        (id == 0) {
                            startNewAuditCreationActivity(groupPosition, childPosition, id, "Security Data");
                            System.out.println("Security Data");
                        } else if (id == 1) {
                            startNewAuditCreationActivity(groupPosition, childPosition, id, "Emergency Installations");
                            System.out.println("Emergency Installations");
                        } else {
                            startNewAuditCreationActivity(groupPosition, childPosition, id, "Other...");
                            System.out.println("Other...");
                        }
                        break;
                    case 2:
                        if        (id == 0) {
                            startNewAuditCreationActivity(groupPosition, childPosition, id, "Maintenance");
                            System.out.println("Maintenance");
                        } else if (id == 1) {
                            startNewAuditCreationActivity(groupPosition, childPosition, id, "Emergency Installations");
                            System.out.println("Emergency Installations");
                        } else {
                            startNewAuditCreationActivity(groupPosition, childPosition, id, "Other...");
                            System.out.println("Other...");
                        }
                        break;
                    case 3:
                        if        (id == 0) {
                            startNewAuditCreationActivity(groupPosition, childPosition, id, "Effectiveness");
                            System.out.println("Effectiveness");
                        } else if (id == 1) {
                            startNewAuditCreationActivity(groupPosition, childPosition, id, "Interruptions");
                            System.out.println("Interruptions");
                        } else {
                            startNewAuditCreationActivity(groupPosition, childPosition, id, "Other...");
                            System.out.println("Other...");
                        }
                        break;
                    case 4:
                        if        (id == 0) {
                            startNewAuditCreationActivity(groupPosition, childPosition, id, "Equipment");
                            System.out.println("Equipment");
                        } else if (id == 1) {
                            startNewAuditCreationActivity(groupPosition, childPosition, id, "Training");
                            System.out.println("Training");
                        } else {
                            startNewAuditCreationActivity(groupPosition, childPosition, id, "Other...");
                            System.out.println("Other...");
                        }
                        break;
                    case 5:
                        if        (id == 0) {
                            startNewAuditCreationActivity(groupPosition, childPosition, id, "Installations");
                            System.out.println("Installations");
                        } else if (id == 1) {
                            startNewAuditCreationActivity(groupPosition, childPosition, id, "Training");
                            System.out.println("Training");
                        } else if (id == 2) {
                            startNewAuditCreationActivity(groupPosition, childPosition, id, "Update Intervals");
                            System.out.println("Update Intervals");
                        } else {
                            startNewAuditCreationActivity(groupPosition, childPosition, id, "Other...");
                            System.out.println("Other...");
                        }
                        break;
                    default:
                        startNewAuditCreationActivity(groupPosition, childPosition, id, "Default");
                        System.out.println("Default");
                        break;
                }
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
    }

    /**
     * Starts NewAuditCreationActivity and sets the corresponding category title
     */
    public void startNewAuditCreationActivity(int groupPosition, int childPosition, long id, String child) {
        /*
        Intent intent = new Intent(getApplicationContext(), NewAuditCreationActivity.class);

        List<String> group_list_items_array = Arrays.asList(getResources().getStringArray(R.array.group_list_items_array));
        NewAuditCreationActivity.GROUP_TITLE_NAME = group_list_items_array.get(groupPosition);
        NewAuditCreationActivity.CHILD_TITLE_NAME = child;

        startActivity(intent);
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_maps_list_view, menu);
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
                    Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.action_help:
                openHelp();
                return true;
            case R.id.action_map:
                goToGoogleMapsActionBar();
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
        messageBox(getResources().getString(R.string.map_list_help_title),getResources().getString(R.string.map_list_help_text));
    }

    /**
     * open main activity
     */
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Opens google maps fragment activity
     */
    public void goToGoogleMapsActionBar() {
        Intent intent = new Intent(this, MapsFragmentActivity.class);
        startActivity(intent);
    }

    /**
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        List<String> group_list_items_array = Arrays.asList(getResources().getStringArray(R.array.group_list_items_array));
        List<String> group_list_child_item_array_1 = Arrays.asList(getResources().getStringArray(R.array.group_list_child_item_array_1));
        List<String> group_list_child_item_array_2 = Arrays.asList(getResources().getStringArray(R.array.group_list_child_item_array_2));
        List<String> group_list_child_item_array_3 = Arrays.asList(getResources().getStringArray(R.array.group_list_child_item_array_3));
        List<String> group_list_child_item_array_4 = Arrays.asList(getResources().getStringArray(R.array.group_list_child_item_array_4));
        List<String> group_list_child_item_array_5 = Arrays.asList(getResources().getStringArray(R.array.group_list_child_item_array_5));
        List<String> group_list_child_item_array_6 = Arrays.asList(getResources().getStringArray(R.array.group_list_child_item_array_6));

        for(int i = 0; i < group_list_items_array.size(); i++) {
            listDataHeader.add(group_list_items_array.get(i));
        }

        // Adding child data
        List<String> Employees = new ArrayList<String>();
        for(int i = 0; i < group_list_child_item_array_1.size(); i++) {
            Employees.add(group_list_child_item_array_1.get(i));
            listDataChild.put(listDataHeader.get(0), Employees);
        }

        List<String> Building = new ArrayList<String>();
        for(int i = 0; i < group_list_child_item_array_2.size(); i++) {
            Building.add(group_list_child_item_array_2.get(i));
            listDataChild.put(listDataHeader.get(1), Building);
        }

        List<String> Machinery = new ArrayList<String>();
        for(int i = 0; i < group_list_child_item_array_3.size(); i++) {
            Machinery.add(group_list_child_item_array_3.get(i));
            listDataChild.put(listDataHeader.get(2), Machinery);
        }

        List<String> ProcessFlow = new ArrayList<String>();
        for(int i = 0; i < group_list_child_item_array_4.size(); i++) {
            ProcessFlow.add(group_list_child_item_array_4.get(i));
            listDataChild.put(listDataHeader.get(3), ProcessFlow);
        }

        List<String> Customs = new ArrayList<String>();
        for(int i = 0; i < group_list_child_item_array_5.size(); i++) {
            Customs.add(group_list_child_item_array_5.get(i));
            listDataChild.put(listDataHeader.get(4), Customs);
        }

        List<String> EmergencyMeasures = new ArrayList<String>();
        for(int i = 0; i < group_list_child_item_array_6.size(); i++) {
            EmergencyMeasures.add(group_list_child_item_array_6.get(i));
            listDataChild.put(listDataHeader.get(5), EmergencyMeasures);
        }

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
        messageBox.setNeutralButton("OK", null);
        messageBox.show();
    }

}