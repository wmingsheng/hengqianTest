package com.example.administrator.myapplication.TestDome;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2016/9/29.
 */

public class AppManager extends Activity implements View.OnClickListener{
    private Context mContext;
    private TextView start;
    private TextView stop;
    private WindowManager wm;
    private WindowManager.LayoutParams mParams;
    private LinearLayout mLayout;
    private float startX,startY,lastX,lastY;
    private PopupWindow mPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.your_view_test);
//        initView();
    }

    private void initView() {
        start = (TextView) findViewById(R.id.start_btn);
        stop = (TextView) findViewById(R.id.stop_btn);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_btn:
                if (mLayout == null) {
                    createSespensionWindow();
                }
                break;
            case R.id.stop_btn:
                quitSespensionWindow();
                break;
            case R.id.popup_setting:
                Toast.makeText(mContext,"this is setting ....",Toast.LENGTH_SHORT).show();
                mPopup.dismiss();
                break;
            case R.id.popup_quit:
                quitSespensionWindow();
                break;
            default:
                break;
        }
    }

    /**
     * 创建弹出框
     * */
    public void createPopup(View view) {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.two_textview_layout,null);
        mPopup = new PopupWindow(layout);

        TextView setting = (TextView) layout.findViewById(R.id.popup_setting);
        TextView quit = (TextView) layout.findViewById(R.id.popup_quit);

        setting.setOnClickListener(this);
        quit.setOnClickListener(this);

        mPopup.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        mPopup.setFocusable(true);
        mPopup.setOutsideTouchable(true);
        ColorDrawable color = new ColorDrawable(0xd0000000);
        mPopup.setBackgroundDrawable(color);

        mPopup.showAsDropDown(view,-70,0);
    }

    /**
     * 创建悬浮框
     * */
    public void createSespensionWindow() {
        wm = (WindowManager) getApplicationContext().getSystemService(getApplicationContext().WINDOW_SERVICE);
        mParams = new WindowManager.LayoutParams();
        mLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.text_layout,null);
//        mLayout.setPadding(10,10,10,10);
        mParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        mParams.format = PixelFormat.RGBA_8888;
        mParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        mParams.gravity = Gravity.LEFT |Gravity.TOP;

        mParams.x = 0;
        mParams.y = 0;
        mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wm.addView(mLayout,mParams);

        TextView view = (TextView) mLayout.findViewById(R.id.textview_tv);
        view.setPadding(10,10,10,10);

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                createPopup(v);
                return false;
            }
        });

        mLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = lastX = event.getRawX();
                        startY = lastY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float dx = event.getRawX() - lastX;
                        float dy = event.getRawY() - lastY;
                        mParams.x = (int) (mParams.x + dx);
                        mParams.y = (int) (mParams.y + dy);
                        wm.updateViewLayout(mLayout,mParams);
                        lastX = event.getRawX();
                        lastY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        float x = event.getRawX() - startX;
                        float y = event.getRawY() - startY;
                        if (Math.abs(x) > 5 || Math.abs(y) > 5) {
                            return true;
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    /**
     * 退出悬浮窗
     * */
    public void quitSespensionWindow() {
        if (mLayout != null) {
            wm.removeView(mLayout);
            mLayout = null;
        }
        mParams = null;
        startX = startY = lastX = lastY = 0;
    }
}
