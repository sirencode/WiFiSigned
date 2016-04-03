package com.syh.yongheshen.wifisigned;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener
{

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inintViews();
    }

    private void inintViews()
    {
        textView = (TextView) findViewById(R.id.tv_signed);
        Button wifi = (Button) findViewById(R.id.wifi_btn);
        Button gps = (Button) findViewById(R.id.gps_btn);
        wifi.setOnClickListener(this);
        gps.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.wifi_btn:
                wifiSigned();
                break;
            case R.id.gps_btn:
                gpsSigned();
                break;
            default:
                break;
        }
    }

    private void wifiSigned()
    {
        SignInByWifi signInByWifi = new SignInByWifi(this);
        if (signInByWifi.isNearby())
        {
            String wifiList = "";
            for (int i = 0; i < Config.WIFILIST.length; i++)
            {
                wifiList += Config.WIFILIST[i];
            }
            textView.setText("签到成功\n签到方式：WIFI签到\n成功扫描到您在指定WIFI附近\n指定WiFi：" + wifiList);
        } else
        {
            String wifiList = "";
            for (int i = 0; i < Config.WIFILIST.length; i++)
            {
                wifiList += Config.WIFILIST[i];
            }
            textView.setText("签到失败\n签到方式：WIFI签到\n扫描到您不在指定WIFI附近\n指定WiFi：" + wifiList);
        }
    }

    private void gpsSigned()
    {
        SignInByGPS signInByGPS = new SignInByGPS(getApplicationContext());
        signInByGPS.setMyLocationListener(new SignInByGPS.MyLocationListener()
        {

            @Override
            public void onFinished(int dist)
            {
                if (dist <= Config.DIS_VALUE)
                {
                    textView.setText("您距离签到的距离为：" + dist+"\nGPS签到成功");
                }
            }
        });
        signInByGPS.initLocation();

    }

}
