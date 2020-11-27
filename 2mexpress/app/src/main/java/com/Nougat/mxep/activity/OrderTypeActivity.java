package com.Nougat.mxep.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.Nougat.mxep.R;
import com.Nougat.mxep.fragment.DistribHistoryFragment;
import com.Nougat.mxep.fragment.DistribSendingFragment;
import com.Nougat.mxep.fragment.MineFragment;
import com.Nougat.mxep.fragment.UserHistoryFragment;
import com.Nougat.mxep.fragment.UserSendingFragment;
import com.Nougat.mxep.fragment.UserWaitingFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class OrderTypeActivity extends AppCompatActivity  {
    TabLayout tabLayout;
    ViewPager viewPager;
    MyPagerAdapter adapter;
    Boolean ispsy = true;
    Toolbar toolbar;
    ImageView fanhui;
    String[] title = new String[]{"历史订单", "配送中", "未配送"};
    int[] images = new int[]{R.drawable.selector_my_lsdd, R.drawable.selector_my_dsh, R.drawable.selector_my_wjd};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_type);
        Intent intent = getIntent();
        if (intent.getExtras().getString("ispsy").equals("0")) {
            ispsy = false;
        } else {
            ispsy = true;
        }
        Log.i("data", intent.getExtras().getString("ispsy"));
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablayouts);
        toolbar = findViewById(R.id.toolbar);
        adapter=new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(adapter);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(adapter.getTabView(i));
        }

        setSupportActionBar(toolbar);
        fanhui = findViewById(R.id.fanhui);

//        findViewById(R.id.fanhui).setOnClickListener((v) → {
//            if (ispsy){
//                startActivity(new Intent(OrderTypeActivity.this,DistribMainActivity.class));
//            }else {
//                startActivity(new Intent(OrderTypeActivity.this,UserMainActivity.class));
//            }
//            finish();
//        });

    }

    class MyPagerAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> fragments = new ArrayList<>();
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            if (ispsy) {
                fragments.add(new DistribHistoryFragment());
                fragments.add(new DistribSendingFragment());
            } else {
                fragments.add(new UserHistoryFragment());
                fragments.add(new UserSendingFragment());
                fragments.add(new UserWaitingFragment());
            }
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

//        @Override
//        public long  getItemId(int position) {
//            return position;
//        }

        public View getTabView(int position){
            //View view = View.inflate(getApplicationContext(), R.layout.tablayout, null);
            View view = LayoutInflater.from(OrderTypeActivity.this).inflate(R.layout.tablayoutitem,null);
            TextView textView=view.findViewById(R.id.selector_time_textview);
            textView.setTextColor(Color.parseColor("#000000"));
            textView.setText(title[position]);
            ImageView imageView=view.findViewById(R.id.selector_time_imageview);
            imageView.setImageResource(images[position]);
            return view;
        }
    }

//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.fanhui:
//                startActivity(new Intent(this, UserMainActivity.class));
//                finish();
//                break;
//        }
//    }
}