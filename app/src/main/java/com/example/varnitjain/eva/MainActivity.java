package com.example.varnitjain.eva;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    protected TextView newText;
    protected TextView Text1;
    protected TextView Text2;
    protected String macAddr;
    protected String bssid;
    public BroadcastReceiver broadcastReceiver;
    public IntentFilter intentFilter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newText = (TextView)findViewById(R.id.newtext);
        newText.setText("hello");


        broadcastReceiver = new WifiBroadcastReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
        registerReceiver(broadcastReceiver, intentFilter);


        updateText();
    }



    private void setIDs(){

        WifiManager wifiMan = (WifiManager)getSystemService(Context.WIFI_SERVICE);

        WifiInfo wifiInfo = wifiMan.getConnectionInfo();

        macAddr = wifiInfo.getMacAddress();
        bssid = wifiInfo.getBSSID();

    }

    public void updateText(){

        setIDs();

        Text1 = (TextView) findViewById(R.id.macAdd);
        Text1.setText(macAddr);

        Text2 = (TextView) findViewById(R.id.ssid);
        Text2.setText(bssid);

    }


}


