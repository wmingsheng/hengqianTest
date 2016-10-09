package com.example.administrator.myapplication.TestDome;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2016/9/21.
 */

public class OutsideLayout extends LinearLayout {
    private static final String TAG = OutsideLayout.class.getSimpleName();
    private Context mContext;

    public OutsideLayout(Context context) {
        super(context);
        mContext = context;
    }

    public OutsideLayout(Context context, AttributeSet attrs) {
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
//        return true;
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG,"onInterceptHoverEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG,"onInterceptHoverEvent.ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG,"onInterceptHoverEvent.ACTION_UP");
                break;
            default:
                break;
        }
        return true;
//        return super.onInterceptHoverEvent(event);
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
//        return true;
        return super.onTouchEvent(event);
    }
}
