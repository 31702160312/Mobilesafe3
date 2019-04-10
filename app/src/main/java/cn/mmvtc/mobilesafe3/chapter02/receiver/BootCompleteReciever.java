package cn.mmvtc.mobilesafe3.chapter02.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.mmvtc.mobilesafe3.App;

/**
 * Created by Administrator on 2019/4/3.
 * 监听开机启动的广播接收者，用于检查SIM卡是否被更换
 * 如果被更换则发短信给安全号码
 */

public class BootCompleteReciever extends BroadcastReceiver {
    private static final String TAG = BootCompleteReciever.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        ((App) context.getApplicationContext()).correctSIM();//初始化
    }
}
