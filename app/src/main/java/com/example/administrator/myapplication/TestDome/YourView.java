package com.example.administrator.myapplication.TestDome;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2016/10/8.
 */

public class YourView extends View {
    private Paint mPaint;
    private Context mContext;
    private static final String TEXT = "Welcome to Xi'an City Chang'an ....";

    public YourView(Context context) {
        super(context);
        mPaint = new Paint();
    }

    public YourView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        TypedArray array = context.obtainStyledAttributes(R.styleable.YourView);
        int color = array.getColor(R.styleable.YourView_textcolor,0xff235342);
        int size = (int) array.getDimension(R.styleable.YourView_textsize,25);
        mPaint.setColor(color);
        mPaint.setTextSize(size);
        array.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(new Rect(5,5,260,260),mPaint);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(100);
        canvas.drawText(TEXT,0,400,mPaint);

    }
}
