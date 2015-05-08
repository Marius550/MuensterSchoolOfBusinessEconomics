package com.example.mariuspilgrim.muensterschoolofbusinesseconomics.Athletics;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.mariuspilgrim.muensterschoolofbusinesseconomics.R;

public class AthleticsContactResultActivity extends Activity {

    public AthleticsContactResultActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Intent intent = getIntent();
            String messageFirstName = intent.getStringExtra(AthleticsContactActivity.EXTRA_MESSAGE_FIRST_NAME);
            String messageLastName = intent.getStringExtra(AthleticsContactActivity.EXTRA_MESSAGE_LAST_NAME);
            String messageEmail = intent.getStringExtra(AthleticsContactActivity.EXTRA_MESSAGE_EMAIL);
            String messageSubject = intent.getStringExtra(AthleticsContactActivity.EXTRA_MESSAGE_SUBJECT);
            String messageMessage= intent.getStringExtra(AthleticsContactActivity.EXTRA_MESSAGE_MESSAGE);

            setContentView(R.layout.athletics_activity_contact_results);

            TextView textViewFirstName = (TextView) findViewById(R.id.result_first_name);
            TextView textViewLastName = (TextView) findViewById(R.id.result_last_name);
            TextView textViewEmail = (TextView) findViewById(R.id.result_email);
            TextView textViewMessageSubject = (TextView) findViewById(R.id.result_message_subject);
            TextView textViewMessage = (TextView) findViewById(R.id.result_message);

            String textViewFirstNameHtml = "<b>" + getResources().getString(R.string.result_first_name) + "</b>" + messageFirstName;
            String textViewLastNameHtml = "<b>" + getResources().getString(R.string.result_last_name) + "</b>" + messageLastName;
            String textViewEmailHtml = "<b>" + getResources().getString(R.string.result_email) + "</b>" + messageEmail;
            String textViewMessageSubjectHtml = "<b>" + getResources().getString(R.string.result_subject) + "</b>" + messageSubject;
            String textViewMessageHtml = "<b>" + getResources().getString(R.string.result_message) + "</b>" + messageMessage;

            textViewFirstName.append(Html.fromHtml(textViewFirstNameHtml));
            textViewLastName.append(Html.fromHtml(textViewLastNameHtml));
            textViewEmail.append(Html.fromHtml(textViewEmailHtml));
            textViewMessageSubject.append(Html.fromHtml(textViewMessageSubjectHtml));
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
        String messageFirstName = intent.getStringExtra(AthleticsContactActivity.EXTRA_MESSAGE_FIRST_NAME);
        String messageLastName = intent.getStringExtra(AthleticsContactActivity.EXTRA_MESSAGE_LAST_NAME);
        String messageEmail = intent.getStringExtra(AthleticsContactActivity.EXTRA_MESSAGE_EMAIL);
        String messageSubject = intent.getStringExtra(AthleticsContactActivity.EXTRA_MESSAGE_SUBJECT);
        String messageMessage= intent.getStringExtra(AthleticsContactActivity.EXTRA_MESSAGE_MESSAGE);

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", getResources().getString(R.string.target_contact_email_address_athletics), null)); //This should be the WWU email address

        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.contact_email_subject));

        String completeEmailMessage = "<b>" + messageFirstName + "<br/>" + messageLastName
                                            + "<br/>" + messageEmail + "<br/>" + messageSubject
                                            + "</b><br/>" + messageMessage;

            emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(completeEmailMessage));
        startActivity(Intent.createChooser(emailIntent, getResources().getString(R.string.email_chooser)));
    }

    public void backToSendMessage(View view) {
        setContentView(R.layout.athletics_activity_contact);
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
        messageBox.setNeutralButton(getResources().getString(R.string.ok), null);
        messageBox.show();
    }

}