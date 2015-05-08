package com.example.mariuspilgrim.muensterschoolofbusinesseconomics.Athletics;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mariuspilgrim.muensterschoolofbusinesseconomics.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AthleticsContactActivity extends Activity {

    public final static String EXTRA_MESSAGE_FIRST_NAME = "com.example.mariuspilgrim.muensterschoolofbusinesseconomics.MESSAGE_FIRST_NAME";
    public final static String EXTRA_MESSAGE_LAST_NAME  = "com.example.mariuspilgrim.muensterschoolofbusinesseconomics.MESSAGE_LAST_NAME";
    public final static String EXTRA_MESSAGE_EMAIL      = "com.example.mariuspilgrim.muensterschoolofbusinesseconomics.MESSAGE_EMAIL";
    public final static String EXTRA_MESSAGE_SUBJECT    = "com.example.mariuspilgrim.muensterschoolofbusinesseconomics.MESSAGE_SUBJECT";
    public final static String EXTRA_MESSAGE_MESSAGE    = "com.example.mariuspilgrim.muensterschoolofbusinesseconomics.MESSAGE_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.athletics_activity_contact);

        TextView textView_welcome = (TextView) findViewById(R.id.welcome_info);
        textView_welcome.setText(getResources().getText(R.string.athletics_contact_welcome_info));

        Button button_setDefaultInputValues = (Button) findViewById(R.id.btn_setDefaultInputValues);
        button_setDefaultInputValues.setOnClickListener(setDefaultInputValues);

        Button button_sendMessage = (Button) findViewById(R.id.btn_sendMessage);
        button_sendMessage.setOnClickListener(sendMessage);
    }

    View.OnClickListener setDefaultInputValues = new View.OnClickListener() {
        public void onClick(View v) {
            setDefaultInputValues();
        }
    };

    View.OnClickListener sendMessage = new View.OnClickListener() {
        public void onClick(View v) {
            sendMessage();
        }
    };

    /**
     * Called when the user clicks the onClick="sendMessage" button
     */
    public Boolean sendMessage() {
        //Do something in response to button
        try {
            Intent intent = new Intent(this, AthleticsContactResultActivity.class);

            EditText editTextFirstName = (EditText) findViewById(R.id.edit_first_name);
            EditText editTextLastName = (EditText) findViewById(R.id.edit_last_name);
            EditText editTextEmail = (EditText) findViewById(R.id.edit_email);
            EditText editTextMessageSubject = (EditText) findViewById(R.id.edit_message_subject);
            EditText editTextMessage = (EditText) findViewById(R.id.edit_message);

            String messageFirstName = editTextFirstName.getText().toString();
            String messageLastName = editTextLastName.getText().toString();
            String messageEmail = editTextEmail.getText().toString();
            String messageSubject = editTextMessageSubject.getText().toString();
            String messageMessage = editTextMessage.getText().toString();

            if(isEmailValid(messageEmail)) {
            } else {
                messageBox( getResources().getString(R.string.edit_email_incorrect_title),
                            getResources().getString(R.string.edit_email_incorrect));
                return false;
            }

            intent.putExtra(EXTRA_MESSAGE_FIRST_NAME, messageFirstName);
            intent.putExtra(EXTRA_MESSAGE_LAST_NAME, messageLastName);
            intent.putExtra(EXTRA_MESSAGE_EMAIL, messageEmail);
            intent.putExtra(EXTRA_MESSAGE_SUBJECT, messageSubject);
            intent.putExtra(EXTRA_MESSAGE_MESSAGE, messageMessage);

            startActivity(intent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  true;
    }

    public boolean isEmailValid(String email)
    {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }

    /**
     * Called when the user clicks the Demo button
     */
    public void setDefaultInputValues() {
        EditText editTextFirstName = (EditText) findViewById(R.id.edit_first_name);
        EditText editTextLastName = (EditText) findViewById(R.id.edit_last_name);
        EditText editTextEmail = (EditText) findViewById(R.id.edit_email);
        EditText editTextMessageSubject = (EditText) findViewById(R.id.edit_message_subject);
        EditText editTextMessage = (EditText) findViewById(R.id.edit_message);

        editTextFirstName.setText(getResources().getString(R.string.default_first_name));
        editTextLastName.setText(getResources().getString(R.string.default_last_name));
        editTextEmail.setText(getResources().getString(R.string.default_email));
        editTextMessageSubject.setText(getResources().getString(R.string.default_subject));
        editTextMessage.setText(getResources().getString(R.string.default_message));
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
        messageBox.setNeutralButton("OK", null);
        messageBox.show();
    }

}