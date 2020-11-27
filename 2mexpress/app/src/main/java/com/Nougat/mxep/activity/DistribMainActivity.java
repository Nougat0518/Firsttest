package com.Nougat.mxep.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.Nougat.mxep.R;
import com.Nougat.mxep.fragment.ReceiveFragment;
import com.Nougat.mxep.fragment.DistriMineFragment;
import com.Nougat.mxep.fragment.RankFragment;

public class DistribMainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView mTabHome, mTabRanking, mTabMine;
    private Fragment mreceiveFragment, mrankFragment, mdsmineFragment;
    private int mFragmentId = 0;

    // 标记三个Fragment
    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_RANK = 1;
    public static final int FRAGMENT_MINE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distrib_main);

        mTabHome = findViewById(R.id.tvd_home);
        mTabRanking = findViewById(R.id.tvd_ranking);
        mTabMine = findViewById(R.id.tvd_mine);

        //根据传入的Bundle对象判断Activity是正常启动还是销毁重建
        if (savedInstanceState == null) {
            //设置第一个Fragment默认选中
            setFragment(mFragmentId);
        }
        mTabHome.setOnClickListener(this);
        mTabRanking.setOnClickListener(this);
        mTabMine.setOnClickListener(this);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        FragmentManager mFragmentManager = getSupportFragmentManager();
        //通过FragmentManager获取保存在FragmentTransaction中的Fragment实例
        mreceiveFragment = mFragmentManager.findFragmentByTag("receive_fragment");
        mrankFragment = mFragmentManager.findFragmentByTag("rank_fragment");
        mdsmineFragment = mFragmentManager.findFragmentByTag("dsmine_fragment");
        //恢复销毁前显示的Fragment
        setFragment(savedInstanceState.getInt("fragment_id"));
    }

    private void hideFragments(FragmentTransaction transaction){
        if(mreceiveFragment != null){
            //隐藏Fragment
            transaction.hide(mreceiveFragment);
            //将对应菜单栏设置为默认状态
            mTabHome.setTextColor(getResources().getColor(R.color.colorTextNormal));
            mTabHome.setCompoundDrawablesWithIntrinsicBounds(0,
                    R.mipmap.tab_home_normal,0,0);
        }
        if(mrankFragment != null){
            transaction.hide(mrankFragment);
            mTabRanking.setTextColor(getResources().getColor(R.color.colorTextNormal));
            mTabRanking.setCompoundDrawablesWithIntrinsicBounds(0,
                    R.mipmap.tab_square_normal,0,0);
        }
        if(mdsmineFragment != null){
            transaction.hide(mdsmineFragment);
            mTabMine.setTextColor(getResources().getColor(R.color.colorTextNormal));
            mTabMine.setCompoundDrawablesWithIntrinsicBounds(0,
                    R.mipmap.tab_mine_normal,0,0);
        }
    }

    private void setFragment(int fragment_id) {
        //获取Fragment管理器
        FragmentManager mFragmentManager = getSupportFragmentManager();
        ////开启事务
        FragmentTransaction mTransaction = mFragmentManager.beginTransaction();
        ////隐藏所有Fragment
        hideFragments(mTransaction);
        switch (fragment_id){
            default:
                break;
            case FRAGMENT_HOME:
                mFragmentId = FRAGMENT_HOME;
                //设置菜单栏为选中状态（修改文字和图片颜色）
                mTabHome.setTextColor(getResources().getColor(R.color.colorTextPressed));
                mTabHome.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.mipmap.tab_home_selector,0,0);
                //显示对应Fragment
                if(mreceiveFragment == null){

                    mreceiveFragment = new ReceiveFragment();
                    mTransaction.add(R.id.container, mreceiveFragment, "receive_fragment");
                }else {
                    mTransaction.show(mreceiveFragment);
                }
                break;
            case FRAGMENT_RANK:
                mFragmentId = FRAGMENT_RANK;
                mTabRanking.setTextColor(getResources().getColor(R.color.colorTextPressed));
                mTabRanking.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.mipmap.tab_square_selteror,0,0);
                if(mrankFragment == null){
                    mrankFragment = new RankFragment();
                    mTransaction.add(R.id.container, mrankFragment, "rank_fragment");
                }else {
                    mTransaction.show(mrankFragment);
                }
                break;
            case FRAGMENT_MINE:
                mFragmentId = FRAGMENT_MINE;
                mTabMine.setTextColor(getResources().getColor(R.color.colorTextPressed));
                mTabMine.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.mipmap.tab_mine_selected,0,0);
                if(mdsmineFragment == null){
                    mdsmineFragment = new DistriMineFragment();
                    mTransaction.add(R.id.container, mdsmineFragment, "dsmine_fragment");
                }else {
                    mTransaction.show(mdsmineFragment);
                }
                break;
        }
        //提交事务
        mTransaction.commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("fragment_id", mFragmentId);
        super.onSaveInstanceState(outState);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tvd_home:
                setFragment(FRAGMENT_HOME);
                break;
            case R.id.tvd_ranking:
                setFragment(FRAGMENT_RANK);
                break;
            case R.id.tvd_mine:
                setFragment(FRAGMENT_MINE);
                break;
        }
    }
}