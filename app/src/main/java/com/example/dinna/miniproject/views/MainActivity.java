package com.example.dinna.miniproject.views;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.dinna.miniproject.Constant;
import com.example.dinna.miniproject.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent activityIntent;

        SharedPreferences settings = getSharedPreferences(Constant.LOGIN_KEY,
                MODE_PRIVATE);
        String userProfile = settings.getString(Constant.USER_PROFILE_KEY, "");
        // go straight to main if a token is stored

        if (userProfile.equalsIgnoreCase("")){
            activityIntent = new Intent(this, LoginActivity.class);
        } else {
            activityIntent = new Intent(this, TabActivity.class);
        }

        startActivity(activityIntent);
        finish();
    }

}
