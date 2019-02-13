package com.example.dinna.miniproject.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.dinna.miniproject.Constant;
import com.example.dinna.miniproject.model.HomeData;
import com.example.dinna.miniproject.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bpn on 11/30/17.
 *
 * 0. In MVP the presenter assumes the functionality of the "middle-man". All presentation logic is pushed to the presenter.
 * 1. Listens to user action and model updates
 * 2. Updates model and view
 */

public class Presenter {

    private User user;
    private String profileString;

    public Presenter() {
        this.user = new User();
    }

    public void updateUserInfo(String data, Context context) {
        profileString = data;
        SharedPreferences sp = context.getSharedPreferences(Constant.LOGIN_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Constant.USER_PROFILE_KEY, data);
        editor.commit();
    }

    public List<HomeData> getHomeData(String data){
        Gson gson = new Gson();
        TypeToken typeToken = new TypeToken<ArrayList<HomeData>>(){};
        Type type = typeToken.getType();
        List<HomeData> dataList = gson.fromJson(data, type);

        return dataList;
    }
}
