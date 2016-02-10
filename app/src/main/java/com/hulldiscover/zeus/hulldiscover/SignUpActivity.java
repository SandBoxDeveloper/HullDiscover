package com.hulldiscover.zeus.hulldiscover;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    protected EditText mUsername;
    protected EditText mPassword;
    protected EditText mEmail;
    protected Button mSignUpButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS); // used to request a view extend window features, MUST be called before contentView
        setContentView(R.layout.activity_sign_up);

        mUsername = (EditText)findViewById(R.id.usernameField);
        mPassword = (EditText)findViewById(R.id.passwordField);
        mEmail = (EditText)findViewById(R.id.emailField);
        mSignUpButton = (Button)findViewById(R.id.signupButton);

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // interactivity - when user taps on sign-up check values, if values are not okay send message to user
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                String email = mEmail.getText().toString();

                // trim whitespace in-case the user accidently hits any spaces
                username = username.trim();
                password = password.trim();
                email = email.trim();

                // check each value is empty
                if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                    // inform user
                    // dialog require user action before they disappear
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                    // set message title and text for the button
                    builder.setMessage(R.string.signup_error_message)
                        .setTitle(R.string.signup_error_title)
                        .setPositiveButton(android.R.string.ok,null); // button to dismiss dialog - passing 'null' lets the dialog be dissmissed when the button is tapped
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else {
                    // create new user
                    ParseUser newUser = new ParseUser();
                    newUser.setUsername(username);
                    newUser.setPassword(password);
                    newUser.setEmail(email);
                    newUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                // success!
                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                // clear navigation history
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                            else {
                                // display message to user on what went wrong
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                // set message title and text for the button
                                builder.setMessage(e.getMessage())
                                        .setTitle(R.string.signup_error_title)
                                        .setPositiveButton(android.R.string.ok,null); // button to dismiss dialog - passing 'null' lets the dialog be dissmissed when the button is tapped
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    }); // sign-up user in background thread
                }
            }
        });
    }
}
