package com.example.dinna.miniproject.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dinna.miniproject.Constant;
import com.example.dinna.miniproject.R;
import com.example.dinna.miniproject.model.User;
import com.example.dinna.miniproject.presenter.Presenter;
import com.google.gson.Gson;

public class ProfileFragment extends Fragment {

    private ImageView profileImage;
    private TextView sex;
    private TextView email;
    private TextView fullName;
    private User profileInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        profileImage = view.findViewById(R.id.profile_image);
        sex = view.findViewById(R.id.sex);
        email = view.findViewById(R.id.email);
        fullName = view.findViewById(R.id.name);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getUserProfile();
        if (profileInfo != null){
            setUserProfile();
        }
    }

    public void getUserProfile(){
        SharedPreferences settings = getContext().getSharedPreferences(Constant.LOGIN_KEY,
                Context.MODE_PRIVATE);
        String userProfile = settings.getString(Constant.USER_PROFILE_KEY, "");

        Gson gson = new Gson();
        profileInfo = gson.fromJson(userProfile, User.class);
    }

    public void setUserProfile(){
        String name = profileInfo.getFirstName() + profileInfo.getLastName();
        fullName.setText(name);
        sex.setText(profileInfo.getSex());
        email.setText(profileInfo.getEmail());
        Drawable imageResources = profileInfo.getSex().equalsIgnoreCase(Constant.FEMALE) ? getResources().getDrawable(R.drawable.girl) : getResources().getDrawable(R.drawable.boy);
        profileImage.setImageDrawable(imageResources);
    }
}
