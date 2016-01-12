package com.xxyevolve.callingmusic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "MyReceiver";
	
	@Override
	public void onReceive(Context context, Intent intent) {
//		String msg = intent.getStringExtra("msg");
//		Log.i(TAG, msg);
//		playingwithService(context);
//		String ac = intent.getAction();
//		if (ac.equals(Intent.ACTION_NEW_OUTGOING_CALL) || ac.equals(Intent.ACTION_)) {
//			playingwithService(context);
//	    }
		String ps = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
		if(ps.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
			
			playingwithService(context);
		}
	
	}
	public void playingwithService(Context context){
        Intent intent = new Intent(context,SoundService.class);
        intent.putExtra("playing", true);
        context.startService(intent);
    }
}
