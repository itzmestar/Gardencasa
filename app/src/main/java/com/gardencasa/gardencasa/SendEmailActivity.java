package com.gardencasa.gardencasa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by TARIQUE on 29-04-2017.
 */

public class SendEmailActivity extends AppCompatActivity {

        Button buttonSend;
        EditText textTo;
        EditText textSubject;
        EditText textMessage;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_email);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            buttonSend = (Button) findViewById(R.id.buttonSend);
            textTo = (EditText) findViewById(R.id.editTextTo);
            textTo.setEnabled(false);
            textSubject = (EditText) findViewById(R.id.editTextSubject);
            textMessage = (EditText) findViewById(R.id.editTextMessage);

            buttonSend.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    String to = textTo.getText().toString();
                    String subject = textSubject.getText().toString();
                    String message = textMessage.getText().toString();

                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
                    //email.putExtra(Intent.EXTRA_CC, new String[]{ to});
                    //email.putExtra(Intent.EXTRA_BCC, new String[]{to});
                    email.putExtra(Intent.EXTRA_SUBJECT, subject);
                    email.putExtra(Intent.EXTRA_TEXT, message);

                    //need this to prompts email client only
                    email.setType("message/rfc822");

                    startActivity(Intent.createChooser(email, "Choose an Email client :"));
                    finish();
                }
            });
        }
}
