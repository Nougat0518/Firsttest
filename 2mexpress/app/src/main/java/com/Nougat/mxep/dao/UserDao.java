package com.Nougat.mxep.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.Nougat.mxep.db.DBOpenHelper;
import com.Nougat.mxep.model.User;


public class UserDao {
    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;
    public UserDao(Context context){
        helper=new DBOpenHelper(context); }
    /**
     *
     * 用户登录
     * */
    public Boolean login(String user_id,String password){
        db=helper.getReadableDatabase();//初始化SQLiteDatabase
        Cursor cursor = db.rawQuery("select * from tb_user where user_id='" + user_id + "' and user_password='" + password + "'", null);
        if (cursor.moveToNext()){
            cursor.close();
            db.close();
            return  true;
        }
        cursor.close();
        db.close();
        return  false;
    }
    /**
     * 根据id查找用户信息
     *
     * */
    public User findUser(String user_id){
        db=helper.getReadableDatabase();//初始化SQLiteDatabase
        User user=new User();
        final Cursor cursor = db.rawQuery("select * from tb_user where user_id='" + user_id + "'", null);
        if (cursor.moveToNext()){
            user.setUser_id(cursor.getString(cursor.getColumnIndex("user_id")));
            user.setUser_password(cursor.getString(cursor.getColumnIndex("user_password")));
            user.setUser_name(cursor.getString(cursor.getColumnIndex("user_name")));
            user.setUser_tel(cursor.getLong(cursor.getColumnIndex("user_tel")));
            user.setUser_money(cursor.getDouble(cursor.getColumnIndex("user_money")));
            user.setUser_address(cursor.getString(cursor.getColumnIndex("user_address")));
            user.setUser_statue(cursor.getInt(cursor.getColumnIndex("user_status")));

            user.setUser_picPath(cursor.getString(cursor.getColumnIndex("user_picPath")));

            return user;
        }
        return null;
    }
    /**
     * 新建用户
     *
     * @return*/
//    public Boolean  addUser(User user){
//        db=helper.getReadableDatabase();
//
//        Cursor cursor = db.rawQuery("insert into tb_user values ('" + user.getUser_id() + "','"
//                + user.getUser_password() + "','" + user.getUser_name() +
//                "','" + user.getUser_tel() + "','','" + user.getUser_address() + "','0','"+user.getUser_picPath()+"');", null);
//
//        //Cursor cursor=db.rawQuery("insert into tb_user values ('"+ user.getUser_id()+"','"+ user.getUser_password()+"','"+ user.getUser_name()+"','"+ user.getUser_tel()+"','','"+ user.getUser_address()+"','0','')",null);
//        if (cursor.moveToNext()){
//            cursor.close();
//            db.close();
//            return true;
//        }
//        cursor.close();
//        db.close();
//        return false;
//    }


    public long addUser(User user){
        db=helper.getWritableDatabase();
        long inserflag = 0;
        ContentValues contentValues=new ContentValues();
        contentValues.put("user_id",user.getUser_id());
        contentValues.put("user_password",user.getUser_password());
        contentValues.put("user_tel",user.getUser_tel());
        contentValues.put("user_name",user.getUser_name());
        contentValues.put("user_address",user.getUser_address());
        contentValues.put("user_money",100);
        contentValues.put("user_status",0);
        contentValues.put("user_picPath",user.getUser_picPath());
        inserflag=db.insert("tb_user",null,contentValues);
        return inserflag;
    }



    /**
     *修改头像路径
     *
     * */
    public void  updateUserImg(String id,String picPath){
        db=helper.getReadableDatabase();
        Cursor cursor=db.rawQuery("update tb_user set user_picPath='"+picPath+"'where user_id='"+id+"'",null);
        if (cursor.moveToNext()){
            cursor.close();
            db.close();
        }
        cursor.close();
        db.close();
    }
    /**
     *修改money
     *
     * */
    public void  updateMoney(String id, Double Money){
        db=helper.getReadableDatabase();
        Cursor cursor=db.rawQuery("update tb_user set user_money='"+Money+"'where user_id='"+id+"'",null);
        if (cursor.moveToNext()){
            cursor.close();
            db.close();
        }

        cursor.close();
        db.close();
    }
    /**
     *修改money
     *
     * */
    public void  updateAddress(String id, String Address){
        db=helper.getReadableDatabase();
        Cursor cursor=db.rawQuery("update tb_user set user_address='"+Address+"'where user_id='"+id+"'",null);
        if (cursor.moveToNext()){
            cursor.close();
            db.close();
        }

        cursor.close();
        db.close();
    }
}
