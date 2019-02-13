package com.example.dinna.miniproject.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dinna.miniproject.Constant;
import com.example.dinna.miniproject.R;
import com.example.dinna.miniproject.presenter.Presenter;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

public class LoginActivity extends Activity {

    private static String TAG = "LoginActivity";
    private static final Pattern EMAIL_REGEX = Pattern.compile(" /^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$/");
    private static final Pattern PASSWORD_REGEX = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})");


    private EditText emailField;
    private EditText passwordField;
    private TextView emailInlineError;
    private TextView passwordInlineError;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);

        emailInlineError = findViewById(R.id.email_inline_error);
        passwordInlineError = findViewById(R.id.password_inline_error);

        presenter = new Presenter();

        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
    }

    private void doLogin(){
        String email = String.valueOf(emailField.getText());
        String password = String.valueOf(passwordField.getText());

        boolean isValidEmail = true;
        boolean isValidPassword = true;

        emailInlineError.setVisibility(View.GONE);
        passwordInlineError.setVisibility(View.GONE);

        if ("".equalsIgnoreCase(email)){
            isValidEmail = false;
            emailInlineError.setVisibility(View.VISIBLE);
            emailInlineError.setText("Please enter a valid email");
        }

        if ("".equalsIgnoreCase(password)){
            isValidPassword = false;
            passwordInlineError.setVisibility(View.VISIBLE);
            passwordInlineError.setText("Please enter a valid password");
        }

        if (isValidEmail && isValidPassword){
            presenter.updateUserInfo(getDummySuccessLogin(), getApplicationContext());

            Intent intent = new Intent(this, TabActivity.class);
            startActivity(intent);
        }
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = EMAIL_REGEX;
        return pattern.matcher(email).matches();
    }

    private boolean isValidPassword(String password){
        Pattern pattern = PASSWORD_REGEX;
        return pattern.matcher(password).matches();
    }

    private String getDummySuccessLogin(){
        String json = null;
        try {
            InputStream is = getResources().getAssets().open("mock_login_response_success.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
