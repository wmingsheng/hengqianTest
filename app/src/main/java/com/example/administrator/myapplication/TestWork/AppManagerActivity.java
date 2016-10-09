package com.example.administrator.myapplication.TestWork;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import static android.view.MotionEvent.ACTION_DOWN;

/**
 * Created by Administrator on 2016/9/20.
 */

public class AppManagerActivity extends Activity implements View.OnClickListener{
    private static final String TAG = AppManagerActivity.class.getSimpleName();
    private Button start_btn;
    private Button stop_btn;
    private WindowManager mManager;
    private WindowManager.LayoutParams mParams;
    private LinearLayout mLayout;
    private TextView mTView;
    private float startX,startY,lastX,lastY;
    private Context mContext;
    private PopupWindow mPopup;
    private int createFV = 0;                                            //判断两次点击事件的不同
    private QuitSettingPopupWindow quitsetting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_main_layout);
        mContext = this;
//        initView();

        ViewGroup group = (ViewGroup) findViewById(R.id.manager_layout);
        for (int i = 0; i < group.getChildCount(); i++) {
            Log.e(TAG, " -------------- " + group.getChildAt(i).getClass().getDeclaredClasses());
        }

    }


    private void initView() {
        start_btn = (Button) findViewById(R.id.start_btn);
        stop_btn = (Button) findViewById(R.id.stop_btn);
        start_btn.setOnClickListener(this);
        stop_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_btn:
                Log.e(TAG,"start_btn");
                if (mLayout == null) {
                    createFloatView();
                }
                break;
            case R.id.stop_btn:
                Log.e(TAG,"stop_btn");
                    stopView();
                break;
            default:
                break;
        }
    }

    /**
     * 创建悬浮窗
     * */
    public void createFloatView() {

        mParams = new WindowManager.LayoutParams();
        mManager = (WindowManager) getApplicationContext().getSystemService(getApplicationContext().WINDOW_SERVICE);

        mParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        mParams.format = PixelFormat.RGBA_8888;
        mParams.flags =  WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mParams.gravity = Gravity.TOP | Gravity.RIGHT;
//        mParams.gravity = Gravity.CENTER;

        mParams.x = 0;
        mParams.y = 0;

        mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        mLayout = (LinearLayout) LayoutInflater.from(getApplication()).inflate(R.layout.text_layout,null);
        mLayout.setBackgroundColor(Color.LTGRAY);
        mLayout.setPadding(15,15,15,15);
        mManager.addView(mLayout,mParams);

        mTView = (TextView) mLayout.findViewById(R.id.textview_tv);
        mTView.setBackgroundColor(Color.YELLOW);

        mLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.e(TAG,"mTView.OnLongClickListener");

                //弹出AlertDialog对话框询问是否退出
                /*AlertDialog.Builder dialog = new AlertDialog.Builder(AppManagerActivity.this);
                dialog.setIcon(R.mipmap.ic_launcher).setTitle("提示框").setMessage("确定退出悬浮窗 ....").
                        setCancelable(false).
                        setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                stopView();
                            }
                        }).
                        setNegativeButton("Cannel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).create().show();*/


                createView(v);
                return true;
            }
        });

        mTView.setOnTouchListener(new View.OnTouchListener() {
            float x = 0,y = 0;
            int dx = 0,dy = 0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case ACTION_DOWN:
                        startX = lastX = event.getRawX();
                        startY = lastY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        x = event.getRawX() - lastX;
                        y = event.getRawY() - lastY;
                        mParams.x = (int) (mParams.x - x);
                        mParams.y = (int) (mParams.y + y);
                        mManager.updateViewLayout(mLayout,mParams);
                        lastX = event.getRawX();
                        lastY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        dx = (int) (event.getRawX() - startX);
                        dy = (int) (event.getRawY() + startY);
                        if (Math.abs(dx) > 2 || Math.abs(dy) > 2){
                            return true;
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    /**
     * 移除悬浮窗
     * */
    public void stopView() {
        if (mLayout != null) {
            mManager.removeView(mLayout);
            mLayout = null;
        }
        mParams = null;
        startX = 0;
        startY = 0;
        lastY = 0;
        lastX = 0;
    }

    /**
     * 创建弹出窗口（PopupWindow）
     * */
    public void createPopupView(View view) {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.two_textview_layout,null);
        mPopup = new PopupWindow(contentView);
        mPopup.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView setting = (TextView) contentView.findViewById(R.id.popup_setting);
        TextView quit = (TextView) contentView.findViewById(R.id.popup_quit);

        setting.setOnClickListener(this);
        quit.setOnClickListener(this);

        mPopup.setBackgroundDrawable(getResources().getDrawable(R.drawable.background));
        mPopup.setAnimationStyle(R.style.contentAnim);
        mPopup.setFocusable(true);
        mPopup.setOutsideTouchable(true);

        mPopup.showAsDropDown(view);
    }

    /**
     * 创建弹出框
     * */
    public void createView(View v) {
        if (quitsetting == null) {
            quitsetting = new QuitSettingPopupWindow(mContext,v,AppManagerActivity.this);
        } else {
            if (quitsetting.isShowing()) {
                quitsetting.dismiss();
            } else {
                quitsetting = new QuitSettingPopupWindow(mContext,v,AppManagerActivity.this);
            }
        }
    }
}
