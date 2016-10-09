package com.example.administrator.myapplication.TestDome;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/9/21.
 */

public class MyView extends TextView {
    private static final String TAG = MyView.class.getSimpleName();
    private Context mContext;

    public MyView(Context context) {
        super(context);
        mContext = context;
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG,"dispatchTouchEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG,"dispatchTouchEvent.ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG,"dispatchTouchEvent.ACTION_UP");
                break;
            default:
                break;
        }
        return false;
//        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG,"onTouchEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG,"onTouchEvent.ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG,"onTouchEvent.ACTION_UP");
                break;
            default:
                break;
        }
        return true;
//        return super.onTouchEvent(event);
    }
}
