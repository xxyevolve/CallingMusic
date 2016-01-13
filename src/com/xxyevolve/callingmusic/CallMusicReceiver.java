package com.xxyevolve.callingmusic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @author save
 *主要为了监听通话中的各种事件
 */
public class CallMusicReceiver extends BroadcastReceiver {
	private static final String TAG = "CallMusicReceiver";
	private Context mContext;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		mContext = context;
		String phoneState = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
		if(phoneState.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
			Log.i("TAG","接通");
			playingwithService(Config.PLAY_MUSIC);
		}
		if(phoneState.equals(TelephonyManager.EXTRA_STATE_IDLE)){
			Log.i("TAG","挂断");
			playingwithService(Config.STOP_MUSIC);
		}
	
	}
	
	
	/** 
	* @Description: TODO 发送不同的状态给CallMusicService 
	* @param @param order  (int)
	* @return void 
	*/
	private void playingwithService(int order){
        Intent intent = new Intent(mContext,CallMusicService.class);
        Bundle bundle = new Bundle();
        bundle.putInt("msg", order);
        intent.putExtras(bundle);
        mContext.startService(intent);
    }
}
