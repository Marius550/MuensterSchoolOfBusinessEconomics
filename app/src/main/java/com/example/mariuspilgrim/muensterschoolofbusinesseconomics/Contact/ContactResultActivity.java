package com.example.mariuspilgrim.muensterschoolofbusinesseconomics.Contact;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.mariuspilgrim.muensterschoolofbusinesseconomics.R;

public class ContactResultActivity extends Activity {

    public ContactResultActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Intent intent = getIntent();
            String messageFirstName = intent.getStringExtra(ContactActivity.EXTRA_MESSAGE_FIRST_NAME);
            String messageLastName = intent.getStringExtra(ContactActivity.EXTRA_MESSAGE_LAST_NAME);
            String messageEmail = intent.getStringExtra(ContactActivity.EXTRA_MESSAGE_EMAIL);
            String messageMessage= intent.getStringExtra(ContactActivity.EXTRA_MESSAGE_MESSAGE);

            setContentView(R.layout.activity_contact_results);

            TextView textViewFirstName = (TextView) findViewById(R.id.result_first_name);
            TextView textViewLastName = (TextView) findViewById(R.id.result_last_name);
            TextView textViewEmail = (TextView) findViewById(R.id.result_email);
            TextView textViewMessage = (TextView) findViewById(R.id.result_message);

            String textViewFirstNameHtml = "<b>" + getResources().getString(R.string.result_first_name) + "</b>" + messageFirstName;
            String textViewLastNameHtml = "<b>" + getResources().getString(R.string.result_last_name) + "</b>" + messageLastName;
            String textViewEmailHtml = "<b>" + getResources().getString(R.string.result_email) + "</b>" + messageEmail;
            String textViewMessageHtml = "<b>" + getResources().getString(R.string.result_message) + "</b>" + messageMessage;

            textViewFirstName.append(Html.fromHtml(textViewFirstNameHtml));
            textViewLastName.append(Html.fromHtml(textViewLastNameHtml));
            textViewEmail.append(Html.fromHtml(textViewEmailHtml));
            textViewMessage.append(Html.fromHtml(textViewMessageHtml));

            //throw new RuntimeException();
        } catch (Exception ex) {
            messageBox(getResources().getString(R.string.error_oncreate_ContactResultFragmentActivity), ex.getMessage());
            ex.printStackTrace();
        }
        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
    }

    public void sendAsEmail(View view) {
        Intent intent = getIntent();
        String messageFirstName = intent.getStringExtra(ContactActivity.EXTRA_MESSAGE_FIRST_NAME);
        String messageLastName = intent.getStringExtra(ContactActivity.EXTRA_MESSAGE_LAST_NAME);
        String messageEmail = intent.getStringExtra(ContactActivity.EXTRA_MESSAGE_EMAIL);
        String messageMessage= intent.getStringExtra(ContactActivity.EXTRA_MESSAGE_MESSAGE);

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","mariuspilgrim@icloud.com", null)); //This should be the WWU email address

        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.contact_email_subject));

        String completeEmailMessage = "<b>" + messageFirstName + "</b><br/><b>" + messageLastName + "</b><br/><b>" + messageEmail + "</b><br/>" + messageMessage;

            emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(completeEmailMessage));
        startActivity(Intent.createChooser(emailIntent, getResources().getString(R.string.email_chooser)));
    }

    public void backToSendMessage(View view) {
        setContentView(R.layout.activity_contact);
        finish();
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
        messageBox.setNeutralButton("OK", null);
        messageBox.show();
    }

}
    //Necessary to execute search function
    //handleIntent(getIntent());

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
            String query = intent.getStringExtra(SearchManager.QUERY) + getResources().getString(R.string.search_added_string_ulb_muenster);;
            //use the query to search your data somehow

            Intent intentWebSearch = new Intent(Intent.ACTION_WEB_SEARCH);
            intentWebSearch.putExtra(SearchManager.QUERY, query);
            if (intentWebSearch.resolveActivity(getPackageManager()) != null) {
                startActivity(intentWebSearch);
            } else {
                Toast.makeText(this, R.string.browser_not_available, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action buttons
        switch(item.getItemId()) {

            case R.id.action_settings:
                openAndroidSettings();
                return true;
            case R.id.action_browser:
                openAndroidBrowser();
                return true;
            case R.id.action_map:
                goToGoogleMapsActionBar();
                return true;
            case android.R.id.home:
                backToSendMessageActionBar(findViewById(R.id.home));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    */

    /*
    public void openAndroidSettings() {
        startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
    }

    public void openAndroidBrowser() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.browser_default_address)));
        startActivity(browserIntent);
    }

    public void goToGoogleMapsActionBar() {
        Intent intent = new Intent(this, MapsFragmentActivity.class);
        startActivity(intent);
    }
    */