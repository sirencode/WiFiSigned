package com.syh.yongheshen.wifisigned;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.widget.Toast;

import java.util.List;

/**
 * 作者： shenyonghe689 on 16/4/1.
 */
public class SignInByWifi
{
    private Context mContext;

    private boolean mNearby;

    public SignInByWifi(Context context)
    {
        this.mContext = context;
    }

    public boolean isNearby()
    {
        mNearby = false;
        if (!wifiOpen())
        {
            Toast.makeText(mContext, "wifi没有打开，请打开WiFi", Toast.LENGTH_LONG).show();
        } else
        {
            WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
            List<ScanResult> scanResults = wifiManager.getScanResults();//搜索到的设备列表
            for (ScanResult scanResult : scanResults)
            {
                String wifiName = scanResult.SSID;
                for (int j = 0; j < Config.WIFILIST.length; j++)
                    if (Config.WIFILIST[j].equals(wifiName))
                    {
                        System.out.println("\n设备名：" + scanResult.SSID);
                        return true;
                    }
            }

        }
        return false;
    }

    private boolean wifiOpen()
    {
        WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        //判断wifi已连接的条件
        return (wifiManager.isWifiEnabled());
    }
}
