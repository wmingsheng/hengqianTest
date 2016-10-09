package com.example.administrator.myapplication.TestWork.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.androidUtils.CommonAdapter;
import com.example.administrator.myapplication.androidUtils.CustomCommonViewHolder;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/18.
 */

public class TimeLineAdapter extends CommonAdapter<Map<String,Object>> {
    private List<Map<String,Object>> mList;

    public TimeLineAdapter(Context context, List<Map<String, Object>> lists) {
        super(context, lists);
        mList = lists;
    }

    public TimeLineAdapter(Context context, int layoutId, List<Map<String, Object>> lists) {
        super(context, layoutId, lists);
        this.mList = lists;
    }

    @Override
    public void convert(CustomCommonViewHolder holder, Map<String, Object> contactbean, int position) {
//        Map<String,Object> map = (Map<String,Object>)mList.get(position);
        ImageView acount_pic = holder.getImageView(R.id.acount_pic);
        TextView content_tv = holder.getTextView(R.id.content_tv);
        acount_pic.setImageResource((Integer) contactbean.get("image"));
        content_tv.setText((String)contactbean.get("content"));
    }
}
