package com.example.administrator.myapplication.androidUtils;

import android.support.v4.util.ArrayMap;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/8/12 0012.
 * <p>
 * 处理点击事件，屏蔽连续点击事件多次触发的可能性，保证在连续点击过程中，只会触发第一次点击事件
 */

public class ClickControlUtil {
    private long clickDefaultDuration;//两次点击事件都有效的最小间隔
    private boolean isLocked;//是否被锁（true：被锁；false：未被锁）
    private Timer timer;//定时器对象
    private ArrayMap<Integer, Boolean> rippleMap;//存储所有点击的rippleView对应的事件是否能够激活的Map

    public ClickControlUtil() {
        clickDefaultDuration = 500;
        isLocked = false;
        timer = new Timer();
        rippleMap = new ArrayMap<Integer, Boolean>();
    }

    /**
     * OnClick中检查是否被锁
     *
     * @return 是否被锁
     * true：被锁    false：未被锁
     */
    public boolean checkClickLock() {
        if (!isLocked) {
            isLocked = true;
            timer.schedule(new MyTimerTask(), clickDefaultDuration);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 检查RippleView的点击事件是否被锁（在RippleView的Onclick监听中调用）
     *
     * @param key 被点击的RippleView的标识
     * @return 是否被锁
     * true：被锁；false：未被锁
     */
    public void checkRippleLock(int key) {
        if (!isLocked) {
            rippleMap.put(key, !isLocked);
            isLocked = true;
            timer.schedule(new MyTimerTask(), clickDefaultDuration);
        } else {
            if (!rippleMap.keySet().contains(key)) {
                rippleMap.put(key, !isLocked);
            }
        }
    }

    /**
     * 检查RippleVIew的OnComplete事件是否有效（在RippleView的OnOnComplete监听中调用）
     *
     * @param key 被点击rippleVIew的唯一标识
     *
     * @return OnComplete事件是否有效
     * true：有效；false：无效
     */
    public boolean isCompleteEventActive(int key) {
        if (rippleMap.size() > 0) {
            boolean isActive = rippleMap.get(key);
            rippleMap.remove(key);
            return isActive;
        } else {
            return false;
        }
    }

    private class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            isLocked = false;
        }
    }
}
