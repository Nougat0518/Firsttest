package com.Nougat.mxep.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Nougat.mxep.R;
import com.Nougat.mxep.dao.DistributorDao;
import com.Nougat.mxep.dao.UserDao;
import com.Nougat.mxep.model.Distributor;
import com.Nougat.mxep.model.User;

public class LoginActivity extends AppCompatActivity {
    private EditText username_et,password_et;
    Button login_bt;
    TextView register_bt;
    SharedPreferences sp= null;//保存登录后的用户名
    SharedPreferences.Editor editor=null;
    UserDao userDao=new UserDao(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username_et=findViewById(R.id.username);
        password_et=findViewById(R.id.password);
        register_bt=findViewById(R.id.register);
        login_bt=findViewById(R.id.login);
        Intent intent=getIntent();

        String userid=intent.getStringExtra("userId");

        username_et.setText(userid);
        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=username_et.getText().toString();
                String password=password_et.getText().toString();
                UserDao userdao=new UserDao(LoginActivity.this);//
                User user = userDao.findUser(username);
                if (username.startsWith("u"))
                {
                    if (user == null)
                    {
                        Toast.makeText( LoginActivity.this,"用户不存在，请注册!",Toast.LENGTH_LONG).show();
                    }
                    else if (user.getUser_statue() == 0)
                    {
                        if(userdao.login(username,password))
                        {
                            sp = getSharedPreferences("userdata", Context.MODE_PRIVATE);//创建sp
                            editor = sp.edit(); //实例化edit
                            editor.putString("userId",username);//3.通过edit存放值
                            editor.commit();//4.确认提交
                            Toast.makeText(LoginActivity.this,"登录成功!",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(LoginActivity.this, UserMainActivity.class);
                            startActivity(intent);
                        }
                        else
                            {
                            Toast.makeText( LoginActivity.this,"账号或密码错误，登录失败!",Toast.LENGTH_LONG).show();
                            }
                }
                    else if (user.getUser_statue() == 1)
                    {
                        Toast.makeText( LoginActivity.this,"账号正在审核中暂不能登录!",Toast.LENGTH_LONG).show();
                    }
                    else if (user.getUser_statue()== 2)
                    {
                        Toast.makeText( LoginActivity.this,"账号被封，请联系管理员!",Toast.LENGTH_LONG).show();
                    }
                }


                //配送员
                if(username.startsWith("d")) {
                    DistributorDao distributorDao = new DistributorDao(LoginActivity.this);
                    Distributor distributor=distributorDao.findDistributorById(username);
                    if(distributor==null){
                        Toast.makeText(LoginActivity.this, "用户不存在！", Toast.LENGTH_LONG).show();
                    }

                    else if(distributor.getDistributor_status()==0) {
                        if (distributorDao.login(username, password)) {
                            sp=getSharedPreferences("distribdata", Context.MODE_PRIVATE);//创建sp
                            editor=sp.edit();//实列化
                            editor.putString("distributorId",username);//存放edit值
                            editor.commit();
                            Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginActivity.this, DistribMainActivity.class);//deng lu jie mian
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "登录失败！", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if (distributor.getDistributor_status()==1){
                        Toast.makeText(LoginActivity.this, "账号正审核中，暂不能登录", Toast.LENGTH_LONG).show();
                    }
                    else if (distributor.getDistributor_status()==2){
                        Toast.makeText(LoginActivity.this, "账号被封，请联系管理员！", Toast.LENGTH_LONG).show();
                    }
                }


            }
        });
        register_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type[]=new String[2];
                type[0]="普通用户";
                type[1]="配送员用户";
                AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("选择注册类型");
                builder.setItems(type, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which==0){
                            Intent intent=new Intent();
                            intent.setClass(LoginActivity.this,UserRegisterActivity.class);
                            startActivity(intent);
                        }
                        else if(which==1){
                            Intent intent=new Intent();
                            intent.setClass(LoginActivity.this,DistribRegisterActivity.class);
                            startActivity(intent);
                        }
                    }
                });
                builder.show();
            }
        });
    }
}

