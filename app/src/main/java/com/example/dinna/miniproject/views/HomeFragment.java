package com.example.dinna.miniproject.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dinna.miniproject.R;
import com.example.dinna.miniproject.adapter.HomeAdapter;
import com.example.dinna.miniproject.model.HomeData;
import com.example.dinna.miniproject.presenter.Presenter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    ArrayList<HomeData> imageList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        Presenter presenter = new Presenter();
        List<HomeData> dataList = presenter.getHomeData(getHomeDummyData());
        imageList = new ArrayList(dataList);

        HomeAdapter adapter = new HomeAdapter(getContext(), imageList);
        recyclerView.setAdapter(adapter);
        return view;
    }

    public String getHomeDummyData(){
        String data = null;
        try {
            InputStream is = getResources().getAssets().open("mock_home_response.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            data = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return data;
    }
}
