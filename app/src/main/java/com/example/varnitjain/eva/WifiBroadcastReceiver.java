package com.example.varnitjain.eva;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.TextView;

public class WifiBroadcastReceiver extends BroadcastReceiver {

    public TextView numberofchange;
    public int count=0;
    public String current="wow";
    public Activity activity;
    protected TextView Text1;
    protected TextView Text2;
    protected String macAddr;
    protected String bssid;


    public WifiBroadcastReceiver(Activity _activity){
        this.activity = _activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("poop","hey");

        String action = intent.getAction();
        if (WifiManager.SUPPLICANT_STATE_CHANGED_ACTION .equals(action)) {
            SupplicantState state = intent.getParcelableExtra(WifiManager.EXTRA_NEW_STATE);
            if (SupplicantState.isValidState(state) && state == SupplicantState.COMPLETED) {

                checkConnectedToDesiredWifi(context);


            }
        }

//        MainActivity mainObj = new MainActivity();
//        mainObj.updateText();

    }

    /** Detect you are connected to a specific network. */
    private void checkConnectedToDesiredWifi(Context context) {

        //boolean connected = false;
        Log.d("hel","hey");

        WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);

        WifiInfo wifi = wifiManager.getConnectionInfo();

        if (wifi != null) {

            String bssid = wifi.getBSSID();

            if(count==0){
                count++;
                current=bssid;
            }else{
                if(!current.equals(bssid)) {
                    count++;

                    numberofchange = (TextView)this.activity.findViewById(R.id.midText);
                    numberofchange.setText(Integer.toString(count));

                    setIDs();

                    current=bssid;
                }
            }


//            Intent mainIntent = getIntent();
//
//            MainActivity mainObj = new MainActivity();
//            mainObj.updateText();
//
//            context.startActivity(mainIntent);



            //connected = desiredMacAddress.equals(bssid);



        }

        //return connected;
    }

    public void setIDs(){

        WifiManager wifiMan = (WifiManager)this.activity.getSystemService(Context.WIFI_SERVICE);

        WifiInfo wifiInfo = wifiMan.getConnectionInfo();

        macAddr = wifiInfo.getMacAddress();
        bssid = wifiInfo.getBSSID();

        updateText();

    }

    public void updateText(){

        //setIDs();

        Text1 = (TextView) this.activity.findViewById(R.id.macAdd);
        Text1.setText(macAddr);

        Text2 = (TextView) this.activity.findViewById(R.id.ssid);
        Text2.setText(bssid);

    }



}
