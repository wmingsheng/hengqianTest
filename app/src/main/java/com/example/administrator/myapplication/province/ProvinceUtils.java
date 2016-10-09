package com.example.administrator.myapplication.province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/26.
 */

public class ProvinceUtils {

    /**
     *读取文件，并返回字符串
     * */
    public static String getFileData(InputStream is) {
        StringBuffer buffer = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    /**
     * 解析JSON数据，并添加进List中
     * */
    public static List<PCDBean> getJSONData(String JsonData,String jsonKey) {
        List<PCDBean> list = new ArrayList<PCDBean>();
        JSONObject object = null;
        try {
            JSONObject jObject = new JSONObject(JsonData);
            JSONArray  jArray = jObject.getJSONArray(jsonKey);
            for (int i = 0; i < jArray.length(); i++) {
                object = jArray.getJSONObject(i);
                PCDBean bean = new PCDBean();
                bean.setID(object.getString("ID"));
                bean.setName(object.getString("Name"));
                bean.setParentId(object.getString("ParentID"));

                list.add(bean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
