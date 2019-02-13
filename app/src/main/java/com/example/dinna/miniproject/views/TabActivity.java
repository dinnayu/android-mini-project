 package com.example.dinna.miniproject.views;

 import android.os.Bundle;
 import android.support.design.widget.TabLayout;
 import android.support.v4.view.ViewPager;
 import android.support.v7.app.AppCompatActivity;

 import com.example.dinna.miniproject.R;
 import com.example.dinna.miniproject.adapter.PageAdapter;


 public class TabActivity extends AppCompatActivity {

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.tab_activity);

         ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
         PageAdapter adapter = new PageAdapter(getSupportFragmentManager());

         // Add Fragments to adapter one by one
         adapter.addFragment(new HomeFragment(), "HOME");
         adapter.addFragment(new ProfileFragment(), "PROFILE");
         viewPager.setAdapter(adapter);

         TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
         tabLayout.setupWithViewPager(viewPager);


     }
 }