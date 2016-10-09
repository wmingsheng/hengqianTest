package com.example.administrator.myapplication.TestDome.fragment;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2016/9/22.
 */

public class MainFragment extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_layout);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

//        initView();
        initDate();

    }


    private void initDate() {
        DpxSpSwitch px = new DpxSpSwitch();
        int px2 = px.px2Dp(this,2);
        int px22 = px.px22Dp(this,2);

        Log.e("info"," -------px2--------- " + px2);
        Log.e("info"," -------px22--------- " + px22);
    }

    private void initView() {
        ActionBar bar = getActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab = bar.newTab().
                setText("主页").
                setTabListener(new TestTabListener<Fragment1>(this,"film1",Fragment1.class));
        bar.addTab(tab);

        tab = bar.newTab().
                setText("电影").
                setTabListener(new TestTabListener<Fragement2>(this,"film2",Fragement2.class));
        bar.addTab(tab);

        tab = bar.newTab().
                setText("联系方式").
                setTabListener(new TestTabListener<Fragment3>(this,"film3",Fragment3.class));
        bar.addTab(tab);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this,upIntent)) {
                    TaskStackBuilder.create(this).addNextIntentWithParentStack(upIntent).
                            startActivities();
                } else {
                    upIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    NavUtils.navigateUpTo(this,upIntent);
                }
                return true;
            default:
            return super.onOptionsItemSelected(item);
        }
    }
}

class DpxSpSwitch {
    public int dp2Px(Context context,int dp) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);

        return (int)(dp * displayMetrics.density + 0.5f);
    }

    public int px2Dp(Context context,int px) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);

        return (int)(px/displayMetrics.density + 0.5f);
    }

    public int px22Dp(Context context,int px) {
        float f = context.getResources().getDisplayMetrics().density;
        return (int)(px / f + 0.5f);
    }

    public int sp2px(Context context,int sp) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(sp * scaledDensity + 0.5f);
    }

    public int px2sp(Context context,int px) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(px / scaledDensity + 0.5f);
    }
}
