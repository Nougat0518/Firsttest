package com.Nougat.mxep.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.Nougat.mxep.R;
import com.Nougat.mxep.activity.OrderDetailActivity;
import com.Nougat.mxep.adapter.OrderListAdapter;
import com.Nougat.mxep.dao.OrderDao;
import com.Nougat.mxep.model.Order;

import java.util.ArrayList;


public class UserWaitingFragment extends Fragment {

    ListView listView;
    SharedPreferences sp=null;



    public UserWaitingFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_user_waiting, container, false);
        sp=getActivity().getSharedPreferences("userdata", Context.MODE_MULTI_PROCESS);
        listView = view.findViewById(R.id.waitinglistview);
        OrderDao order_dao=new OrderDao(getContext());
        final ArrayList<Order> arrayList= order_dao.oneUserallList(sp.getString("userId",""));
        OrderListAdapter phAdapter=new OrderListAdapter(getContext(),arrayList);
        listView.setAdapter(phAdapter);
        return view;
    }

}
