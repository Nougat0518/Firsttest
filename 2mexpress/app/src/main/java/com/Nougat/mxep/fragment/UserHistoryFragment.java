package com.Nougat.mxep.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.Nougat.mxep.R;
import com.Nougat.mxep.adapter.OrderListAdapter;
import com.Nougat.mxep.dao.OrderDao;
import com.Nougat.mxep.model.Order;

import java.util.ArrayList;

public class UserHistoryFragment extends Fragment {


    ListView listView;
    SharedPreferences sp=null;
    public UserHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_history, container, false);
        sp = getActivity().getSharedPreferences("userdata", Context.MODE_MULTI_PROCESS);
        listView = view.findViewById(R.id.Historylist_view);
        OrderDao order_dao = new OrderDao(getContext());
        final ArrayList<Order> orderList = order_dao.getAllOrder(sp.getString("userId", ""));
        OrderListAdapter phAdapter = new OrderListAdapter(getContext(), orderList);
        listView.setAdapter(phAdapter);
        return view;
    }
}





























