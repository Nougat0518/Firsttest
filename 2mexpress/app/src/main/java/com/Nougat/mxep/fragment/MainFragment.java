package com.Nougat.mxep.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.Nougat.mxep.R;
import com.Nougat.mxep.activity.PickExpActivity;
import com.Nougat.mxep.activity.SentExpActivity;
import com.Nougat.mxep.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

    public class MainFragment extends Fragment {

        Banner banner;//banner组件
        List mlist;//图片资源
        List<String> mlist1;//轮播标题
        private Toolbar toolbar;
        public MainFragment() {
            // Required empty public constructor
        }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        toolbar=view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        view.findViewById(R.id.sendExpress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SentExpActivity.class));
            }
        });

        view.findViewById(R.id.pickExpress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), PickExpActivity.class));
                //由于当前类不属于Activity 因此先get
                //getActivity().finish();
            }
        });

        mlist = new ArrayList<>();
        mlist.add(R.mipmap.lunbo1);
        mlist.add(R.mipmap.lunbo2);
        mlist.add(R.mipmap.lunbo3);
        mlist1 = new ArrayList<>();
        mlist1.add("特色快速业务");
        mlist1.add("送货到家服务");
        mlist1.add("一键查询服务");
        banner = view.findViewById(R.id.main_banner);
        banner.setImageLoader(new GlideImageLoader());   //设置图片加载器
        banner.setImages(mlist);//设置图片源
        banner.setBannerTitles(mlist1);//设置标题源
        banner.setDelayTime(2000);//设置轮播事件，单位毫秒
        banner.setBannerAnimation(Transformer.ZoomOut);
        banner.setOnBannerListener(new OnBannerListener() {
            public void OnBannerClick(int position) {
                Toast.makeText(getActivity(), "点击了轮播第"+position+"个图片" + position, Toast.LENGTH_SHORT).show();
            }
        });
        banner.setIndicatorGravity(BannerConfig.CENTER);//设置指示器的位置
        banner.start();//开始轮播，一定要调用此方法。
        return view;
    }
}