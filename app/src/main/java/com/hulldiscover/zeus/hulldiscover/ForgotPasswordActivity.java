package com.hulldiscover.zeus.hulldiscover;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

public class ForgotPasswordActivity extends AppCompatActivity {

    protected EditText mEmail;
    protected Button mForgotPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // grab email address
        mEmail = (EditText)findViewById(R.id.emailField);
        mForgotPasswordButton = (Button)findViewById(R.id.retrievePasswordButton);

        mForgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // interactivity - when user taps on sign-up check values, if values are not okay send message to user
                String email = mEmail.getText().toString();

                // trim whitespace in-case the user accidently hits any spaces
                email = email.trim();

                // check each value is empty
                if (email.isEmpty()) {
                    // inform user
                    // dialog require user action before they disappear
                    AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPasswordActivity.this);
                    // set message title and text for the button
                    builder.setMessage(R.string.signup_error_message)
                            .setTitle(R.string.signup_error_title)
                            .setPositiveButton(android.R.string.ok,null); // button to dismiss dialog - passing 'null' lets the dialog be dissmissed when the button is tapped
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else {
                    // reset password
                    ParseUser.requestPasswordResetInBackground(email, new RequestPasswordResetCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                // An email was successfully sent with reset instructions.
                                AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPasswordActivity.this);
                                // set message title and text for the button
                                builder.setMessage("done")
                                        .setTitle("success")
                                        .setPositiveButton(android.R.string.ok,null); // button to dismiss dialog - passing 'null' lets the dialog be dissmissed when the button is tapped
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            } else {
                                // Something went wrong. Look at the ParseException to see what's up.
                                // An email was successfully sent with reset instructions.
                                AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPasswordActivity.this);
                                // set message title and text for the button
                                builder.setMessage("bad")
                                        .setTitle("failure")
                                        .setPositiveButton(android.R.string.ok,null); // button to dismiss dialog - passing 'null' lets the dialog be dissmissed when the button is tapped
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    });

                }
            }
        });


    }
}
