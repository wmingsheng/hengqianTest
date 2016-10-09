package com.example.administrator.myapplication.androidUtils;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/8/30.
 */

public class CustomCommonViewHolder {
    private int mPos;
    private View mConvertView;
    private SparseArray<View> mItemViewList;

    public CustomCommonViewHolder(Context context,ViewGroup parent,View convertView,
                                  int positon,int layoutId) {
        this(context,parent,convertView,positon,layoutId,null);
    }

    public CustomCommonViewHolder(Context context, ViewGroup parent, View convertView,
                                  int position, int layoutId, ArraibuteListener listener) {
        mItemViewList = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId,null);
        setmPos(position);
        mConvertView.setTag(this);
        if (listener != null) {
            listener.setArraibuteForView(mConvertView);
        }
    }

    //异常的定义
    class ViewHolderException extends Exception {
        public ViewHolderException(String msg) {
            super(msg);
        }
    }

    public TextView getTextView(int viewId) {
        return getItemView(viewId);
    }

    public Button getButton(int viewId) {
        return getItemView(viewId);
    }

    public ImageView getImageView(int viewId) {
        return getItemView(viewId);
    }

    public View getView(int viewId) {
        return getItemView(viewId);
    }

    /**
     * 获取一个子View
     * @param itemViewId      子View的ID（R.id.xxxxxxx）
     * @return <T extends View> T    返回一个集成View的T
     * */
    public <T extends View> T getItemView(int itemViewId) {
        View itemView = mItemViewList.get(itemViewId);
        if (null == itemView) {
            itemView = mConvertView.findViewById(itemViewId);
            //使用itemViewId作为Key
            mItemViewList.put(itemViewId,itemView);
        }
        return (T) itemView;
    }

    public int getmPos() {
        return mPos;
    }

    public void setmPos(int mPos) {
        this.mPos = mPos;
    }

    public View getmConvertView() {
        return mConvertView;
    }

    public void setmConvertView(View mConvertView) {
        this.mConvertView = mConvertView;
    }

    interface ArraibuteListener {
        public void setArraibuteForView(View itemView);
    }
}
