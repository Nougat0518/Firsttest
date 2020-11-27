package com.Nougat.mxep.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.Nougat.mxep.R;
import com.Nougat.mxep.activity.PersonInfoActivity;
import com.Nougat.mxep.dao.DistributorDao;
import com.Nougat.mxep.model.Distributor;

import java.util.ArrayList;
import java.util.List;

public class RankFragment extends Fragment  {

    ListView listView;
    ArrayList<Distributor> list;
    public RankFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//    @Override
//    public void onClick(View v) {
//
//    }

    //获取排行数据并在ListView中显示
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rank, container, false);
        listView = view.findViewById(R.id.paihanglist);//实例化listview
        DistributorDao distributor_dao = new DistributorDao(getContext());

        list = (ArrayList<Distributor>) distributor_dao.distributorDesc();

        MyListAdapter myListAdapter = new MyListAdapter(getContext(),list);//实例化适配器
        listView.setAdapter(myListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Distributor distributor=list.get(position);
                Intent intent=new Intent();
                intent.putExtra("ispsy","2");
                intent.putExtra("name",distributor.getDistributor_name());
                intent.putExtra("phone",distributor.getDistributor_tel()+"");
                intent.putExtra("idcar",distributor.getDistributor_idcar()+"");
                intent.putExtra("jdcs",distributor.getDistributor_singularnum()+"");
                Log.i("data",distributor.getDistributor_idcar()+distributor.getDistributor_tel()+"");
                intent.setClass(getContext(), PersonInfoActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    //创建ListView适配器的内部类  MyListAdapter
    class MyListAdapter extends BaseAdapter {
        LayoutInflater layoutInflater;
        ArrayList<Distributor> datalist;
        MyListAdapter(Context context, ArrayList<Distributor>templist){
            layoutInflater=LayoutInflater.from(context);
            datalist=templist;
            //this.list=list;
        }


        @Override
        public int getCount() {

            return datalist.size();
            //return datalist.size();
        }

        @Override
        public Object getItem(int position) {
            return datalist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
        HoldView holdView = new HoldView();
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.list_item,parent,false);
            holdView.paimingtv=convertView.findViewById(R.id.mingci);
            holdView.touxiangtv=convertView.findViewById(R.id.psy_image);
            holdView.nametv=convertView.findViewById(R.id.psy_name);
            holdView.jiediantv=convertView.findViewById(R.id.psy_jdcs);
            convertView.setTag(holdView);

        }else {
            holdView=(HoldView)convertView.getTag();
        }
            //int mingci = position+1;
            holdView.paimingtv.setText(position+1+"");
            Bitmap bitmap = BitmapFactory.decodeFile(datalist.get(position).getDistributor_picPath());
            holdView.touxiangtv.setImageBitmap(bitmap);
            holdView.nametv.setText(datalist.get(position).getDistributor_name());
            holdView.jiediantv.setText(datalist.get(position).getDistributor_singularnum()+"");
            return convertView;
        }

    }
    class HoldView{
        TextView paimingtv;
        ImageView touxiangtv;
        TextView nametv;
        TextView jiediantv;
    }


//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ViewHolder viewHolder = null;
//            if (convertView==null) {
//
//                convertView = layoutInflater.inflate(R.layout.list_item,parent);
//
//                viewHolder=new ViewHolder();
//
//                viewHolder.imageViews=(ImageView)convertView.findViewById(R.id.psy_image);
//                viewHolder.name=(TextView) convertView.findViewById(R.id.psy_name);
//                viewHolder.phcs=(TextView) convertView.findViewById(R.id.psy_jdcs);
//                viewHolder.ph=(TextView) convertView.findViewById(R.id.mingci);
//
//                convertView.setTag(viewHolder);
//
//            }else {
//                viewHolder= (ViewHolder) convertView.getTag();
//            }
//            Distributor distributor=list.get(position);
//
//            viewHolder.ph.setText(position+1+"");
//
//            viewHolder.name.setText(distributor.getDistributor_name());
//            viewHolder.phcs.setText(distributor.getDistributor_singularnum()+"");
//            return convertView;
//        }
//    }
//    //创建与ListView填充界面组件对应的内部组件实体类
//    class ViewHolder{
//        public ImageView imageViews;
//        public  TextView ph;
//        public TextView name;
//        public TextView phcs;
//    }

}