package com.xxyevolve.callingmusic;

import java.io.IOException;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

/**
 * @author save
 *处理从其他地方发来的状态信息，主要功能是播放音乐
 */
public class CallMusicService extends Service {
	private MediaPlayer mMediaPlay;
	private Context  mContext;
	private AudioManager mAudioManager;
	private final String  TAG= "soundserver";
	private int maxVolume;
	private boolean isServerOpen = true; //是否开启服务的开关
	
	@Override
	public void onCreate() {
		super.onCreate();
		mContext = this;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		stopServer();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Bundle bundle = intent.getExtras();
		int msg = bundle.getInt("msg");
		Log.i("TAG","play music :"+msg);
		switch (msg) {
		case Config.PLAY_MUSIC:
			startMusic();
			break;
		case Config.STOP_MUSIC:
			stopMusic();
			break;
		case Config.START_MUSIC_SERVER:
			startServer();
			break;
		case Config.STOP_MUSIC_SERVER:
			stopServer();
			break;
		case Config.SET_VOLUME_ADD:
			setVoice(1);
			break;
		case Config.SET_VOLUME_REDUCE:
			setVoice(-1);
		default:
			break;
		}
		return super.onStartCommand(intent, flags, startId);
	}

	
	
	/** 
	* @Description: 初始化MediaPlay 
	* @param   
	* @return void 
	*/
	private void prepareMusic() {
		if(mMediaPlay == null){
			mMediaPlay = MediaPlayer.create(this, R.raw.chuanqi);
			mMediaPlay.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mMediaPlay.setLooping(true); 
	        mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
	        maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	        Log.i("TAG","I am  prepareMusic");
		}else{
			try {
				mMediaPlay.prepare();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	/** 
	* @Description: 开启服务
	* @param   
	* @return void 
	*/
	private void startServer(){
		isServerOpen = true;
	}
	
	
	/** 
	* @Description: 关闭服务
	* @param   
	* @return void 
	*/
	private void stopServer(){
		if (mMediaPlay != null ) {
			mMediaPlay.release();
			Log.i("TAG","I am  stopServer");
		}
		isServerOpen = false;
		stopSelf();
	}
	
	/** 
	* @Description: TODO 开始播放音乐
	* @param   
	* @return void 
	*/
	private void startMusic() {
		if(isServerOpen){
			prepareMusic();
			if (!mMediaPlay.isPlaying()) {
				mMediaPlay.start();
				Log.i("TAG","I am  startMusic");
			}
		}
		
	}

	
	/** 
	* @Description: TODO 停止播放音乐
	* @param   
	* @return void 
	*/
	private void stopMusic() {
		if (mMediaPlay != null && mMediaPlay.isPlaying()) {
			mMediaPlay.stop();
			Log.i("TAG","I am  stopMusic");
		}
		
	}
	
	
	/** 
	* @Description: TODO 设置音量大小
	* @param @param volume  
	* @return void 
	*/
	private void setVoice(int volume ){
		Log.i("TAG","maxVolume :"+(float)(maxVolume));
		maxVolume = maxVolume+volume;
		if (mMediaPlay != null ) {
			mMediaPlay.setVolume((float)(maxVolume),(float) (maxVolume));
			Log.i("TAG","I am  setVoice");
		}
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}