package com.example.administrator.myapplication.TestDome;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.administrator.myapplication.MainActivity;
import com.example.administrator.myapplication.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/9/21.
 */

public class EventDistribution extends Activity {
    private static final String TAG = EventDistribution.class.getSimpleName();
    private OutsideLayout outlayout;
    private InsideLayout inLayout;
    private MyView myView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_distribution_layout);
        ActionBar bar = getActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.test_menu,menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible",Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu,true);

                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_title:
                Toast.makeText(this,"title1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.memu_tt2:
                Intent mainIntent = new Intent(EventDistribution.this, MainActivity.class);
                startActivity(mainIntent);
                Toast.makeText(this,"title2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.memu_tt3:
                Toast.makeText(this,"title3",Toast.LENGTH_SHORT).show();
                break;
            case R.id.memu_tt4:
                Toast.makeText(this,"title4",Toast.LENGTH_SHORT).show();
                break;
            case R.id.memu_tt5:
                Toast.makeText(this,"title5",Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                Intent intent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, intent)) {
                    TaskStackBuilder.create(this)
                            .addNextIntentWithParentStack(intent)
                            .startActivities();
                } else {
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    NavUtils.navigateUpTo(this, intent);
                }
                return true;
            case R.id.menu_search:
                Toast.makeText(this,"menu_search",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;

        }
        return super.onMenuItemSelected(featureId, item);
    }

    public void initView() {
        outlayout = (OutsideLayout) findViewById(R.id.outside_layout);
        inLayout = (InsideLayout) findViewById(R.id.inside_layout);
        myView = (MyView) findViewById(R.id.myView);

        outlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"outLayout.onclicklistener");
            }
        });

        inLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"inLayout.onclicklistener");
            }
        });

        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"myView.onclicklistener");
            }
        });

        outlayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.e(TAG,"outLayout.onlongclicklistener");
                return true;
            }
        });

        inLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.e(TAG,"inLayout.onlongclicklistener");
                return true;
            }
        });

        myView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.e(TAG,"myView.onlongclicklistener");
                return true;
            }
        });
    }
}
