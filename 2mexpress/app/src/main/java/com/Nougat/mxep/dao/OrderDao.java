package com.Nougat.mxep.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.Nougat.mxep.db.DBOpenHelper;
import com.Nougat.mxep.model.Distributor;
import com.Nougat.mxep.model.Order;

import java.util.ArrayList;

public class OrderDao {
    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;

    public OrderDao(Context context) {
        helper = new DBOpenHelper(context);
    }

    //    public Boolean newOrder (Order order){
//        db=helper.getReadableDatabase();
//                Cursor cursor=db.rawQuery("INSERT INTO tb_order VALUES('"+order.getOr_dis_type()+"','"+order.getOr_re_name()+"','"+order.getOr_re_tel()+"','"+order.getOr_re_address()+"','"+order.getOr_notes()+"');" ,null);
//        if (cursor.moveToNext()){
//            cursor.close();
//            db.close();
//            return true;
//        }
//        cursor.close();
//        db.close();
//        return false;
//    }
    //新建订单
    public long newOrder(Order order) {
        db = helper.getWritableDatabase();
        long insernumb = 0;
        ContentValues cv = new ContentValues();
        cv.put("user_id", order.getUser_id());
        cv.put("order_distribut_type", order.getOrder_distribut_type());
        cv.put("order_receiver_name", order.getOrder_receiver_name());
        cv.put("order_notes", order.getOrder_notes());
        cv.put("order_status", order.getOrder_status());
        cv.put("order_picPath", order.getOrder_picpath());
        cv.put("distributor_id", order.getDistributor_id());
        cv.put("order_price", order.getOrder_price());
        cv.put("order_receiver_tel", order.getOrder_receiver_tel());
        cv.put("order_receiver_address", order.getOrder_receiver_address());
        cv.put("order_time", order.getOrder_time());
        cv.put("order_delivery_time", order.getOrder_delivery_time());
        insernumb = db.insert("tb_order", null, cv);
        return insernumb;
    }

//History
    public ArrayList<Order> getAllOrder(String userId) {
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_order where user_id='"+ userId +"'", null);
        ArrayList<Order> orderList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Order order = new Order();
            order.setOrder_id(cursor.getString(cursor.getColumnIndex("order_id")));
            order.setUser_id(cursor.getString(cursor.getColumnIndex("user_id")));
            order.setDistributor_id(cursor.getString(cursor.getColumnIndex("distributor_id")));
            order.setOrder_distribut_type(cursor.getString(cursor.getColumnIndex("order_distribut_type")));
            order.setOrder_price(cursor.getDouble(cursor.getColumnIndex("order_price")));
            order.setOrder_receiver_name(cursor.getString(cursor.getColumnIndex("order_receiver_name")));
            order.setOrder_receiver_tel(cursor.getLong(cursor.getColumnIndex("order_receiver_tel")));
            order.setOrder_receiver_address(cursor.getString(cursor.getColumnIndex("order_receiver_address")));
            order.setOrder_time(cursor.getString(cursor.getColumnIndex("order_time")));
            order.setOrder_delivery_time(cursor.getString(cursor.getColumnIndex("order_delivery_time")));
            order.setOrder_status(cursor.getInt(cursor.getColumnIndex("order_status")));
            order.setOrder_picpath(cursor.getString(cursor.getColumnIndex("order_picpath")));
            order.setOrder_notes(cursor.getString(cursor.getColumnIndex("order_notes")));
            orderList.add(order);
        }
        cursor.close();
        return orderList;
    }

//Sending
    public ArrayList<Order> userOrderpsz(String user_id) {
        ArrayList<Order> arrayList = new ArrayList<Order>();
        db = helper.getReadableDatabase();
        String sql = "select * from tb_order where user_id='" + user_id + "'and order_status=1";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Order order = new Order();
            order.setOrder_id(cursor.getString(cursor.getColumnIndex("order_id")));
            order.setUser_id(cursor.getString(cursor.getColumnIndex("user_id")));
            order.setDistributor_id(cursor.getString(cursor.getColumnIndex("distributor_id")));
            order.setOrder_distribut_type(cursor.getString(cursor.getColumnIndex("order_distribut_type")));
            order.setOrder_price(cursor.getDouble(cursor.getColumnIndex("order_price")));
            order.setOrder_receiver_name(cursor.getString(cursor.getColumnIndex("order_receiver_name")));
            order.setOrder_receiver_address(cursor.getString(cursor.getColumnIndex("order_receiver_address")));
            order.setOrder_receiver_tel(cursor.getLong(cursor.getColumnIndex("order_receiver_tel")));
            order.setOrder_time(cursor.getString(cursor.getColumnIndex("order_time")));
            order.setOrder_delivery_time(cursor.getString(cursor.getColumnIndex("order_delivery_time")));
            order.setOrder_status(cursor.getInt(cursor.getColumnIndex("order_status")));
            order.setOrder_picpath(cursor.getString(cursor.getColumnIndex("order_picpath")));
            order.setOrder_notes(cursor.getString(cursor.getColumnIndex("order_notes")));
            arrayList.add(order);
        }
        cursor.close();
      ///  db.close();
        return arrayList;
    }

//Waiting
    public ArrayList<Order> oneUserallList(String userId) {
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_order where user_id='"+ userId +"' and order_status=0", null);
        ArrayList<Order> orderList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Order order = new Order();
            order.setOrder_id(cursor.getString(cursor.getColumnIndex("order_id")));
            order.setUser_id(cursor.getString(cursor.getColumnIndex("user_id")));
            order.setDistributor_id(cursor.getString(cursor.getColumnIndex("distributor_id")));
            order.setOrder_distribut_type(cursor.getString(cursor.getColumnIndex("order_distribut_type")));
            order.setOrder_price(cursor.getDouble(cursor.getColumnIndex("order_price")));
            order.setOrder_receiver_name(cursor.getString(cursor.getColumnIndex("order_receiver_name")));
            order.setOrder_receiver_tel(cursor.getLong(cursor.getColumnIndex("order_receiver_tel")));
            order.setOrder_receiver_address(cursor.getString(cursor.getColumnIndex("order_receiver_address")));
            order.setOrder_time(cursor.getString(cursor.getColumnIndex("order_time")));
            order.setOrder_delivery_time(cursor.getString(cursor.getColumnIndex("order_delivery_time")));
            order.setOrder_status(cursor.getInt(cursor.getColumnIndex("order_status")));
            order.setOrder_picpath(cursor.getString(cursor.getColumnIndex("order_picpath")));
            order.setOrder_notes(cursor.getString(cursor.getColumnIndex("order_notes")));
            orderList.add(order);
        }
        cursor.close();
        //db.close();
        return orderList;
    }

//接单
    public ArrayList<Order> allOrder() {
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_order where order_status=0", null);
        ArrayList<Order> orderList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Order order = new Order();
            order.setOrder_id(cursor.getString(cursor.getColumnIndex("order_id")));
            order.setUser_id(cursor.getString(cursor.getColumnIndex("user_id")));
            order.setDistributor_id(cursor.getString(cursor.getColumnIndex("distributor_id")));
            order.setOrder_distribut_type(cursor.getString(cursor.getColumnIndex("order_distribut_type")));
            order.setOrder_price(cursor.getDouble(cursor.getColumnIndex("order_price")));
            order.setOrder_receiver_name(cursor.getString(cursor.getColumnIndex("order_receiver_name")));
            order.setOrder_receiver_tel(cursor.getLong(cursor.getColumnIndex("order_receiver_tel")));
            order.setOrder_receiver_address(cursor.getString(cursor.getColumnIndex("order_receiver_address")));
            order.setOrder_time(cursor.getString(cursor.getColumnIndex("order_time")));
            order.setOrder_delivery_time(cursor.getString(cursor.getColumnIndex("order_delivery_time")));
            order.setOrder_status(cursor.getInt(cursor.getColumnIndex("order_status")));
            order.setOrder_picpath(cursor.getString(cursor.getColumnIndex("order_picpath")));
            order.setOrder_notes(cursor.getString(cursor.getColumnIndex("order_notes")));
            orderList.add(order);
        }
        cursor.close();
        return orderList;
    }
//接单数
    public void Jdorder(String id, String order_id) {
        db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("order_status", 1);
        cv.put("distributor_id", id);
        db.update("tb_order", cv, "order_id=?", new String[] {order_id});
    }
}





