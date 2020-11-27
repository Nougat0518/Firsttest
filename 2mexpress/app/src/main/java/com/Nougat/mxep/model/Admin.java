package com.Nougat.mxep.model;

public class Admin {
    //id
    private char admin_id;
    //密码
    private char admin_password;
    //用户id
    private char user_id;
    //配送员id
    private  char dis_id;

    public char getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(char admin_id) {
        this.admin_id = admin_id;
    }

    public char getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(char admin_password) {
        this.admin_password = admin_password;
    }

    public char getUser_id() {
        return user_id;
    }

    public void setUser_id(char user_id) {
        this.user_id = user_id;
    }

    public char getDis_id() {
        return dis_id;
    }

    public void setDis_id(char dis_id) {
        this.dis_id = dis_id;
    }
}
