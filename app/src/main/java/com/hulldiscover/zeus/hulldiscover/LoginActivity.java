package com.hulldiscover.zeus.hulldiscover;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    protected EditText mUsername;
    protected EditText mPassword;
    protected Button mLoginButton;
    protected TextView mForgotPasswordTextView;

    // m prefix means its a member of a member var
    protected TextView mSignUpTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS); // used to request a view extend window features, MUST be called before contentView
        setContentView(R.layout.activity_login);

        mSignUpTextView = (TextView)findViewById(R.id.signUpText);
        mSignUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // switch to sign-up activity
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        mForgotPasswordTextView = (TextView)findViewById(R.id.forgotPasswordText);
        mForgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // switch to forgot-password activity
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
        /*// forgot password ? field
        mForgotPasswordTextView = (TextView)findViewById(R.id.forgotPasswordField);
        mForgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // switch to forgot_password activity
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });*/

        mUsername = (EditText)findViewById(R.id.usernameField);
        mPassword = (EditText)findViewById(R.id.passwordField);
        mLoginButton = (Button)findViewById(R.id.loginButton);




        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // interactivity - when user taps on sign-up check values, if values are not okay send message to user
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();

                // trim whitespace in-case the user accidentally hits any spaces
                username = username.trim();
                password = password.trim();

                // check each value is empty
                if (username.isEmpty() || password.isEmpty()) {
                    // inform user
                    // dialog require user action before they disappear
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    // set message title and text for the button
                    builder.setMessage(R.string.login_error_message)
                            .setTitle(R.string.login_error_title)
                            .setPositiveButton(android.R.string.ok,null); // button to dismiss dialog - passing 'null' lets the dialog be dissmissed when the button is tapped
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else { // Login
                    //setProgressBarIndeterminateVisibility(true); // show progress indicator

                    ParseUser.logInInBackground(username, password, new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {

                            //setProgressBarIndeterminateVisibility(false);

                            if (e == null) {
                                // success
                                // take user to home
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                // clear navigation history
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                            else {
                                // display message to user on what went wrong
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                // set message title and text for the button
                                builder.setMessage(e.getMessage())
                                        .setTitle(R.string.login_error_title)
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
