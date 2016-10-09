package com.example.administrator.myapplication.TestWork;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2016/9/21.
 */

public class QuitSettingPopupWindow extends PopupWindow implements View.OnClickListener{
    private PopupWindow mPopup;
    private Context mContext;
    private AppManagerActivity mApp;

    public QuitSettingPopupWindow(Context context,View view,AppManagerActivity app) {
        mContext = context;
        mApp = app;
        View contentView = LayoutInflater.from(context).inflate(R.layout.two_textview_layout,null);
        mPopup = new PopupWindow(contentView);
        mPopup.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView setting = (TextView) contentView.findViewById(R.id.popup_setting);
        TextView quit = (TextView) contentView.findViewById(R.id.popup_quit);

        setting.setOnClickListener(this);
        quit.setOnClickListener(this);


        mPopup.setFocusable(true);
        mPopup.setOutsideTouchable(true);
        ColorDrawable color = new ColorDrawable(0xd000aaaa);
        mPopup.setBackgroundDrawable(color);
        mPopup.setAnimationStyle(R.style.contentAnim);

        mPopup.showAsDropDown(view,-120,0);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.popup_setting:
                Toast.makeText(mContext,"setting.OnClickListener",Toast.LENGTH_SHORT).show();
                dismissPopupWindow();
                break;
            case R.id.popup_quit:
                mApp.stopView();
                break;
            default:
                break;
        }
    }

    /**
     * 取消弹出框
     * */
    public void dismissPopupWindow() {
        if (mPopup.isShowing()) {
            mPopup.dismiss();
        }
    }
}
