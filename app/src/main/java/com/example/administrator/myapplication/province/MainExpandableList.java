package com.example.administrator.myapplication.province;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.SimpleExpandableListAdapter;

import com.example.administrator.myapplication.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/26.
 */

public class MainExpandableList extends ExpandableListActivity {
    private static final String DISTRICTION = "districtList";
    private static final String CITY = "cityList";
    private static final String PROVINCE = "provinceList";
    private Context mContext;
    private List<Map<String,Object>> province;
    private List<Map<String,Object>> city;
    private List<Map<String,Object>> distriction;
    private List<PCDBean> provinceList;
    private List<PCDBean> cityList;
    private List<PCDBean> districtionList;
    List<Map<String,Object>> groupList;
    private List<List<Map<String,Object>>> childList;
    private Handler myHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_province_expandable_layout);

        mContext = this;
        initView();
        new Thread(new InitData()).start();

        myHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    setChild();
                    SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(mContext,groupList,R.layout.province_group_layout,
                            new String[]{"p_c_d","length"},new int[]{R.id.data_group_name,R.id.data_group_link},childList,R.layout.province_item_layout,
                            new String[]{"p_id","p_name","p_parentId"},new int[]{R.id.data_item_id,R.id.data_item_name,R.id.data_item_parent_id});
                    setListAdapter(adapter);
                }
            }
        };
    }

    public void initView() {
        province = new ArrayList<Map<String,Object>>();
        city = new ArrayList<Map<String,Object>>();
        distriction = new ArrayList<Map<String,Object>>();
        provinceList = new ArrayList<PCDBean>();
        cityList = new ArrayList<PCDBean>();
        districtionList = new ArrayList<PCDBean>();
        childList = new ArrayList<List<Map<String,Object>>>();
        groupList = new ArrayList<Map<String,Object>>();


    }

    class InitData implements Runnable {
        String dataStr = null;
        InputStream is = null;

        @Override
        public void run() {
            try {
                is = mContext.getResources().getAssets().open("provincial_city_data_file.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            dataStr = ProvinceUtils.getFileData(is);
            provinceList = ProvinceUtils.getJSONData(dataStr,PROVINCE);
            cityList = ProvinceUtils.getJSONData(dataStr,CITY);
            districtionList = ProvinceUtils.getJSONData(dataStr,DISTRICTION);

            Message msg = myHandler.obtainMessage();
            msg.what = 1;
            myHandler.sendMessage(msg);
        }
    }

    /**
     * 子项的设置
     */
    public void setChild() {
        Map<String,Object> group1 = new HashMap<String,Object>();
        group1.put("p_c_d","省");
        group1.put("length",String.valueOf(provinceList.size()));
        Map<String,Object> group2 = new HashMap<String,Object>();
        group2.put("p_c_d","市");
        group2.put("length",String.valueOf(cityList.size()));
        Map<String,Object> group3 = new HashMap<String,Object>();
        group3.put("p_c_d","区、县");
        group3.put("length",String.valueOf(districtionList.size()));
        groupList.add(group1);
        groupList.add(group2);
        groupList.add(group3);
        addListMapFromList(province,provinceList);
        addListMapFromList(city,cityList);
        addListMapFromList(distriction,districtionList);
        childList.add(province);
        childList.add(city);
        childList.add(distriction);
    }

    /**
     *将List中的数据存入到ListMap中
     * */
    public void addListMapFromList(List<Map<String,Object>> listMap,List<PCDBean> list) {
        PCDBean bean = null;
        for (Iterator<PCDBean> iterator = list.iterator();iterator.hasNext();) {
            Map<String,Object> map = new HashMap<String,Object>();
            bean = iterator.next();
            map.put("p_id",bean.getID());
            map.put("p_name",bean.getName());
            map.put("p_parentId",bean.getParentId());
            listMap.add(map);
        }
    }
}
