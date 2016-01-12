package com.xxyevolve.callingmusic;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


public class MyService extends Service {
	private static final String TAG = "MyService";
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "onCreate called.");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG, "onStartCommand called.");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

}
