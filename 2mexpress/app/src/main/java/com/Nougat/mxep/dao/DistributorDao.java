package com.Nougat.mxep.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.Nougat.mxep.db.DBOpenHelper;
import com.Nougat.mxep.model.Distributor;

import java.util.ArrayList;
import java.util.List;


public class DistributorDao {
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    public DistributorDao(Context context){
        helper=new DBOpenHelper(context);
    }
    public Distributor findDistributor(String distributor_id){
        db=helper.getReadableDatabase();//初始化SQLiteDatabase
        Cursor cursor = db.rawQuery("select * from tb_distributor where distributor_id='" + distributor_id + "'", null);
        if (cursor.moveToNext()){
            Distributor distributor = new Distributor();
            distributor.setDistributor_id(cursor.getString(cursor.getColumnIndex("distributor_id")));
            distributor.setDistributor_idcar(cursor.getLong(cursor.getColumnIndex("distributor_idcar")));
            distributor.setDistributor_password(cursor.getString(cursor.getColumnIndex("distributor_password")));
            distributor.setDistributor_name(cursor.getString(cursor.getColumnIndex("distributor_name")));
            distributor.setDistributor_tel(cursor.getLong(cursor.getColumnIndex("distributor_tel")));
            distributor.setDistributor_money(cursor.getDouble(cursor.getColumnIndex("distributor_money")));
            distributor.setDistributor_status(cursor.getInt(cursor.getColumnIndex("distributor_status")));
            distributor.setDistributor_picPath(cursor.getString(cursor.getColumnIndex("distributor_picPath")));
            distributor.setDistributor_singularnum(cursor.getInt(cursor.getColumnIndex("distributor_singularnum")));
            return distributor;
        }
        return null;
    }

    //配送员登录
    public Boolean login(String distributor_id,String password){
        db=helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_distributor where distributor_id= '"+ distributor_id +"' and distributor_password= '"+ password +"'and distributor_picPath ='';", null);
        if (cursor.moveToNext()){
            cursor.close();
            db.close();
            return  true;
        }
        cursor.close();
        db.close();
        return  false;
    }
    //配送员注册
    public Boolean  addDistributor(Distributor distributor){
        db=helper.getReadableDatabase();
        Cursor cursor=db.rawQuery( "INSERT INTO tb_distributor  VALUES('"+distributor.getDistributor_id()+"','" +distributor.getDistributor_password()+"','"+ distributor.getDistributor_name()+"','"+ distributor.getDistributor_idcar()+"','"+ distributor.getDistributor_tel()+"','null','0','0','"+distributor.getDistributor_picPath()+"');",null);        if (cursor.moveToNext()){
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }


    public Distributor findDistributorById(String id){
        db=helper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from tb_distributor where distributor_id='"+id+"';",null);
        if (cursor.moveToNext()){
            Distributor distributor=new Distributor();
            distributor.setDistributor_id(cursor.getString(cursor.getColumnIndex("distributor_id")));
            distributor.setDistributor_password(cursor.getString(cursor.getColumnIndex("distributor_password")));
            distributor.setDistributor_name(cursor.getString(cursor.getColumnIndex("distributor_name")));
            distributor.setDistributor_idcar(cursor.getLong(cursor.getColumnIndex("distributor_idcar")));
            distributor.setDistributor_tel(cursor.getLong(cursor.getColumnIndex("distributor_tel")));
            distributor.setDistributor_money(cursor.getDouble(cursor.getColumnIndex("distributor_money")));
            distributor.setDistributor_singularnum(cursor.getInt(cursor.getColumnIndex("distributor_singularnum")));
            distributor.setDistributor_status(cursor.getInt(cursor.getColumnIndex("distributor_status")));
            distributor.setDistributor_picPath(cursor.getString(cursor.getColumnIndex("distributor_picPath")));
            cursor.close();
            db.close();
            return distributor;
        }
        cursor.close();
        db.close();
        return  null;
    }





    //配送员排行


    public List<Distributor> distributorDesc() {
        db=helper.getReadableDatabase();//初始化SQLiteDatabase
        Cursor cursor = db.rawQuery("select * from tb_distributor order by distributor_singularnum desc", null);
        List<Distributor> distributorList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Distributor distributor = new Distributor();

            distributor.setDistributor_id(cursor.getString(cursor.getColumnIndex("distributor_id")));
            distributor.setDistributor_idcar(cursor.getLong(cursor.getColumnIndex("distributor_idcar")));
            distributor.setDistributor_password(cursor.getString(cursor.getColumnIndex("distributor_password")));
            distributor.setDistributor_name(cursor.getString(cursor.getColumnIndex("distributor_name")));

            distributor.setDistributor_tel(cursor.getLong(cursor.getColumnIndex("distributor_tel")));
        distributor.setDistributor_money(cursor.getDouble(cursor.getColumnIndex("distributor_money")));

//            distributor.setDistributor_tel(cursor.get(cursor.getColumnIndex("distributor_tel")));
//            distributor.setDistributor_money(cursor.getFloat(cursor.getColumnIndex("distributor_money")));

            distributor.setDistributor_status(cursor.getInt(cursor.getColumnIndex("distributor_status")));
            distributor.setDistributor_picPath(cursor.getString(cursor.getColumnIndex("distributor_picPath")));
            distributor.setDistributor_singularnum(cursor.getInt(cursor.getColumnIndex("distributor_singularnum")));
            distributorList.add(distributor);
        }
        cursor.close();
        return distributorList;
    }

//    public ArrayList<Distributor> distributorDesc()
//    {
//        ArrayList<Distributor> list= new ArrayList<>();
//        db=helper.getReadableDatabase();
//
//        Cursor cursor = db.rawQuery("select * from tb_distributor order by distributor_singularnum desc", null);
//        //String sql="select * from tb_distributor order by distributor_singularnum desc";
//        //Cursor cursor =db.rawQuery(sql,null);
//
//        while (cursor.moveToNext()){
//        Distributor distributor=new Distributor();
//        distributor.setDistributor_id(cursor.getString(cursor.getColumnIndex("distributor_id")));
//        distributor.setDistributor_password(cursor.getString(cursor.getColumnIndex("distributor_password")));
//        distributor.setDistributor_name(cursor.getString(cursor.getColumnIndex("distributor_name")));
//        distributor.setDistributor_idcar(cursor.getLong(cursor.getColumnIndex("distributor_idcar")));
//        distributor.setDistributor_tel(cursor.getLong(cursor.getColumnIndex("distributor_tel")));
//        distributor.setDistributor_money(cursor.getDouble(cursor.getColumnIndex("distributor_money")));
//        distributor.setDistributor_singularnum(cursor.getInt(cursor.getColumnIndex("distributor_singularnum")));
//        distributor.setDistributor_status(cursor.getInt(cursor.getColumnIndex("distributor_status")));
//        distributor.setDistributor_picPath(cursor.getString(cursor.getColumnIndex("distributor_picPath")));
//    }
//        cursor.close();
//        db.close();
//        return null;
//    }

    public void updateSingularnum(String id) {
        db = helper.getWritableDatabase();
        Distributor distributor = findDistributor(id);
        int num = distributor.getDistributor_singularnum() + 1;
        ContentValues cv = new ContentValues();
        cv.put("distributor_singularnum", num);
        db.update("tb_distributor", cv, "distributor_id=?", new String[] {id});
    }
}
