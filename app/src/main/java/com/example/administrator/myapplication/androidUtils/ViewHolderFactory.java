package com.example.administrator.myapplication.androidUtils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * 定义一个ViewHolder工厂
 * Created by Administrator on 2016/8/30.
 */

public class ViewHolderFactory {

    public static CustomCommonViewHolder getCustomCommonViewHolder(Context context,ViewGroup parent,
                                                                   View convertView, int position, int layoutId) {
        return getCustomCommonViewHolder(context,parent,convertView,position,layoutId,null);
    }

    public static CustomCommonViewHolder getCustomCommonViewHolder(Context context, ViewGroup parent,
        View convertView, int position, int layoutId, CustomCommonViewHolder.ArraibuteListener listener) {
        CustomCommonViewHolder holder = null;

        if (null == holder) {
            holder = new CustomCommonViewHolder(context,parent,convertView,position,layoutId,listener);
        } else {
            holder = (CustomCommonViewHolder) convertView.getTag();
            holder.setmPos(position);
        }
        return holder;
    }
}
