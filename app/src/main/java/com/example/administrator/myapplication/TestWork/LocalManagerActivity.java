package com.example.administrator.myapplication.TestWork;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2016/9/18.
 */

public class LocalManagerActivity extends Activity implements View.OnClickListener{
    private Button start;
    private Button stop;
    private WindowManager mManager;
    private WindowManager.LayoutParams mParams;
    private LinearLayout mLinearLayout;
    private TextView word;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_main_layout);
        start = (Button) findViewById(R.id.start_btn);
        stop = (Button) findViewById(R.id.stop_btn);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_btn:
                creatFloatView();
                break;
            case R.id.stop_btn:
                if (null != mLinearLayout) {
                    mManager.removeView(mLinearLayout);
                }
                break;
            default:
                break;
        }
    }

    private void creatFloatView() {
//        mManager = getWindow().getWindowManager();
        mManager = this.getWindowManager();
//        mManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        mParams = new WindowManager.LayoutParams();

        mParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        mParams.format = PixelFormat.RGBA_8888;
        mParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mParams.gravity = Gravity.TOP | Gravity.RIGHT;

        mParams.x = 50;
        mParams.y = 80;

        mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        LayoutInflater inflater = this.getLayoutInflater();
        mLinearLayout = (LinearLayout) inflater.inflate(R.layout.text_layout,null);
        mLinearLayout.setPadding(10,10,10,10);
        mLinearLayout.setBackgroundColor(Color.LTGRAY);
        mManager.addView(mLinearLayout,mParams);

        word = (TextView) mLinearLayout.findViewById(R.id.textview_tv);
        word.setBackgroundColor(Color.YELLOW);

        word.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mParams.x = (int) event.getRawX() - word.getMeasuredWidth()/2;
                mParams.y = (int) event.getRawY() - word.getMeasuredHeight()/2 - 25;

                mManager.updateViewLayout(mLinearLayout,mParams);
                return true;
            }
        });

        word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LocalManagerActivity.this,"onclick",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mLinearLayout) {
            mManager.removeView(mLinearLayout);
        }
    }
}
