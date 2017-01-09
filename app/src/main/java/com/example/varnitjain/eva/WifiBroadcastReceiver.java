package com.example.varnitjain.eva;

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
                    if(!current.equals(bssid)){
                        count++;
                        //numberofchange = (TextView)numberofchange.findViewById(R.id.midText);
                        //numberofchange.setText(Integer.toString(count));

                        current=bssid;
                    }
                }


//            Intent mainIntent = getIntent();
//
            MainActivity mainObj = new MainActivity();
            mainObj.updateText();
//
//            context.startActivity(mainIntent);



            //connected = desiredMacAddress.equals(bssid);



        }

        //return connected;
    }



}
