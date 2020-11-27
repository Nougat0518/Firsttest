package com.Nougat.mxep.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Nougat.mxep.R;
import com.Nougat.mxep.activity.AboutUsActivity;
import com.Nougat.mxep.activity.DetectUpdateActivity;
import com.Nougat.mxep.activity.FeedbackActivity;
import com.Nougat.mxep.activity.MyAddressActivity;
import com.Nougat.mxep.activity.MyMessageActivity;
import com.Nougat.mxep.activity.OrderTypeActivity;
import com.Nougat.mxep.activity.PersonInfoActivity;
import com.Nougat.mxep.activity.RechargeActivity;
import com.Nougat.mxep.dao.UserDao;
import com.Nougat.mxep.model.User;


public class MineFragment extends Fragment {
    private ImageView headImage;
    private Toolbar toolbar;
    private TextView showuser;
    private LinearLayout lin_ddxq;

    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        lin_ddxq = view.findViewById(R.id.ddxq);
        headImage = view.findViewById(R.id.myicon);
        showuser = view.findViewById(R.id.showuser);
        SharedPreferences sp = getActivity().getSharedPreferences("userdata", Context.MODE_MULTI_PROCESS);
        String user_id = sp.getString("userId", "");
        UserDao user_dao  = new UserDao(getContext());
        User user = user_dao.findUser(user_id);
        showuser.setText(user_id);

        String picPath=user.getUser_picPath();
        Toast.makeText(getContext(),picPath,Toast.LENGTH_LONG).show();
        if (null!=picPath&&!picPath.equals("null")&&!picPath.equals("")){
            headImage.setImageURI(Uri.parse(picPath));
        }

//        String picPath = user.getUser_picPath();
//        Toast.makeText(getContext(), "图片路径"+picPath, Toast.LENGTH_LONG).show();
//        if (null != picPath  && !picPath.equals("")) {
//            Bitmap bitmap= BitmapFactory.decodeFile(user.getUser_picPath());
//            headImage.setImageBitmap(bitmap);
//            //headImage.setImageURI(Uri.parse(picPath));
//        }

        view.findViewById(R.id.linheared).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), PersonInfoActivity.class);
                Bundle b = new Bundle();
                b.putString("ispsy", "0");
                intent.putExtras(b);
                getActivity().startActivity(intent);
            }
        });

        lin_ddxq.setOnClickListener(new View.OnClickListener() {//订单详情
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), OrderTypeActivity.class);
                Bundle b = new Bundle();
                b.putString("ispsy", "0");
                intent.putExtras(b);
                getActivity().startActivity(intent);
            }
        });

        view.findViewById(R.id.cz).setOnClickListener(new View.OnClickListener() {//充值
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), RechargeActivity.class));
            }
        });
        view.findViewById(R.id.yjfh).setOnClickListener(new View.OnClickListener() {//意见反馈
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), FeedbackActivity.class));
            }
        });
        view.findViewById(R.id.myaddress).setOnClickListener(new View.OnClickListener() {//我的地址
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MyAddressActivity.class));
            }
        });
        view.findViewById(R.id.wdxx).setOnClickListener(new View.OnClickListener() {//我的消息
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), MyMessageActivity.class);
                getActivity().startActivity(intent);
            }
        });
        view.findViewById(R.id.gywm).setOnClickListener(new View.OnClickListener() {//关于我们
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AboutUsActivity.class));
            }
        });
        view.findViewById(R.id.jcgx).setOnClickListener(new View.OnClickListener() {//检测更新
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), DetectUpdateActivity.class));
            }
        });
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        return view;

    }
}