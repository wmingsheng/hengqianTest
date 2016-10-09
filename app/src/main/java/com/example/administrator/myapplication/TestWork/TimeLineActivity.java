package com.example.administrator.myapplication.TestWork;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.TestWork.adapter.TimeLineAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/18.
 */

public class TimeLineActivity extends Activity {
    private ListView mListView;
    private List<Map<String,Object>> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_layout);
        initData();
        mListView = (ListView) findViewById(R.id.test_aty_list_view);
        TimeLineAdapter adapter = new TimeLineAdapter(this,R.layout.time_line_layout,mList);
        mListView.setAdapter(adapter);

    }

    public void initData() {
        String str = "在此特别感谢大家，谢谢你们，在这里特地分享RecycleView的实现方式。";
        mList = new ArrayList<Map<String,Object>>();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("image",R.mipmap.ic_launcher);
        map.put("content",str);
        mList.add(map);
        Map<String,Object> map2 = new HashMap<String,Object>();
        map2.put("image",R.mipmap.ic_launcher);
        map2.put("content",str);
        mList.add(map2);
        Map<String,Object> map3= new HashMap<String,Object>();
        map3.put("image",R.mipmap.ic_launcher);
        map3.put("content",str);
        mList.add(map3);
        Map<String,Object> map4 = new HashMap<String,Object>();
        map4.put("image",R.mipmap.ic_launcher);
        map4.put("content",str);
        mList.add(map4);
        Map<String,Object> map5 = new HashMap<String,Object>();
        map5.put("image",R.mipmap.ic_launcher);
        map5.put("content",str);
        mList.add(map5);
        Map<String,Object> map6 = new HashMap<String,Object>();
        map6.put("image",R.mipmap.ic_launcher);
        map6.put("content",str);
        mList.add(map6);
        Map<String,Object> map7 = new HashMap<String,Object>();
        map7.put("image",R.mipmap.ic_launcher);
        map7.put("content",str);
        mList.add(map7);
        Map<String,Object> map8 = new HashMap<String,Object>();
        map8.put("image",R.mipmap.ic_launcher);
        map8.put("content",str);
        mList.add(map8);
        Map<String,Object> map9 = new HashMap<String,Object>();
        map9.put("image",R.mipmap.ic_launcher);
        map9.put("content",str);
        mList.add(map9);
    }
}
