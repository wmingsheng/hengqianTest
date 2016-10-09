package com.example.administrator.myapplication.androidUtils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/30.
 */

public abstract class CommonAdapter<T> extends BaseAdapter {
    private Context mContext;
    private List<T> mList;
    private int mLayoutId;

    public CommonAdapter(Context context,List<T> lists) {
        this(context,0,lists);
    }

    public CommonAdapter(Context context, int layoutId, List<T> lists) {
        mContext = context;
        if (null == lists) {
            mList = new ArrayList<T>();
        } else {
            mList = lists;
        }
        mLayoutId = layoutId;
    }

    /**
     * 获取数据
     * */
    public void getSourceData(List<T> lists) {
        if (null != lists) {
            mList = lists;
        }

    }

    /**
     * 添加数据
     * @param  lists
     * */
    public void addData(List<T> lists) {
        if (null != mList) {
            mList.addAll(lists);
        }
        notifyDataSetChanged();
    }

    /**
     * 重置数据
     * @param  lists
     * */
    public void removeDato (List<T> lists) {
        mList.clear();
        mList.addAll(lists);
        notifyDataSetChanged();
    }

    /**
     * 删除指定处的数据
     * @param  position
     * */
    public void removeData(int position) {
        if (null != mList) {
            mList.remove(position);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomCommonViewHolder holder = ViewHolderFactory.getCustomCommonViewHolder(mContext,parent,
                convertView,position,mLayoutId);
        convert(holder, getItem(position), position);
        return holder.getmConvertView();
    }

    public abstract void convert(CustomCommonViewHolder holder, T contactbean, int position);
}
