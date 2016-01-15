package com.xxyevolve.callingmusic;

import java.io.FileDescriptor;
import java.io.IOException;

import com.xxyevolve.callingmusic.dao.BelmotPlayer;
import com.xxyevolve.callingmusic.utils.CallMusicConfig;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
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
	private BelmotPlayer belmotPlayer;
	private Context  mContext;
	private AudioManager mAudioManager;
	private final String  TAG= "soundserver";
	private int maxVolume;
	private static boolean isServerOpen = true; //是否开启服务的开关
	
	@Override
	public void onCreate() {
		super.onCreate();
		mContext = this;
		initApplication();
	}

	private void initApplication() {
		if (null == belmotPlayer) {
			belmotPlayer = BelmotPlayer.getInstance();
		}
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
		case CallMusicConfig.PLAY_MUSIC:
			startMusic();
			break;
		case CallMusicConfig.STOP_MUSIC:
			stopMusic();
			break;
		case CallMusicConfig.START_MUSIC_SERVER:
			startServer();
			break;
		case CallMusicConfig.STOP_MUSIC_SERVER:
			stopServer();
			break;
		case CallMusicConfig.SET_VOLUME_ADD:
			setVoice(1);
			break;
		case CallMusicConfig.SET_VOLUME_REDUCE:
			setVoice(-1);
		default:
			break;
		}
		return super.onStartCommand(intent, flags, startId);
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
		if (belmotPlayer != null ) {
			belmotPlayer.getPlayerEngine().stop();
			Log.i("TAG","I am  stopServer");
		}
		isServerOpen = false;
		stopSelf();
	}
	
	private void play(String path) {
		if (belmotPlayer.getPlayerEngine().isPlaying()
				&& belmotPlayer.getPlayerEngine().getPlayingPath().equals(path)) {
			belmotPlayer.getPlayerEngine().pause();
		} else if (belmotPlayer.getPlayerEngine().isPause()
				&& belmotPlayer.getPlayerEngine().getPlayingPath().equals(path)) {
			belmotPlayer.getPlayerEngine().reset();
			belmotPlayer.getPlayerEngine().setPlayingPath(path);
			belmotPlayer.getPlayerEngine().play();
		} else {
			if (belmotPlayer.getPlayerEngine().isPlaying()
					|| belmotPlayer.getPlayerEngine().isPause()) {
				belmotPlayer.getPlayerEngine().reset();
			}
			belmotPlayer.getPlayerEngine().setPlayingPath(path);
			belmotPlayer.getPlayerEngine().play();
		}

	}
	private void play(FileDescriptor path) {
		if (belmotPlayer.getPlayerEngine().isPlaying()
				&& belmotPlayer.getPlayerEngine().getPlayingPath().equals(path)) {
			belmotPlayer.getPlayerEngine().pause();
		} else if (belmotPlayer.getPlayerEngine().isPause()
				&& belmotPlayer.getPlayerEngine().getPlayingPath().equals(path)) {
			belmotPlayer.getPlayerEngine().reset();
			belmotPlayer.getPlayerEngine().setPlayingPath(path);
			belmotPlayer.getPlayerEngine().play(path);
		} else {
			if (belmotPlayer.getPlayerEngine().isPlaying()
					|| belmotPlayer.getPlayerEngine().isPause()) {
				belmotPlayer.getPlayerEngine().reset();
			}
			belmotPlayer.getPlayerEngine().setPlayingPath(path);
			belmotPlayer.getPlayerEngine().play(path);
		}

	}
	
	/** 
	* @Description: TODO 开始播放音乐
	* @param   
	* @return void 
	*/
	private void startMusic() {
		if(isServerOpen){
			if(null != CallMusicConfig.MUSIC_PATH)
					play(CallMusicConfig.MUSIC_PATH);
			else{
				try {
					String[] str = getResources().getAssets().list("");
					Log.i(TAG,"path --->"+str[0]);
					 AssetFileDescriptor afd = getAssets().openFd(str[0]);
					play(afd.getFileDescriptor());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
//			prepareMusic();
//			if (!mMediaPlay.isPlaying()) {
//				mMediaPlay.start();
//				Log.i("TAG","I am  startMusic");
//			}
		}
		
	}

	
	/** 
	* @Description: TODO 停止播放音乐
	* @param   
	* @return void 
	*/
	private void stopMusic() {
		if(belmotPlayer != null && belmotPlayer.getPlayerEngine().isPlaying()){
			belmotPlayer.getPlayerEngine().pause();
		}
//		if (mMediaPlay != null && mMediaPlay.isPlaying()) {
//			mMediaPlay.stop();
//			Log.i("TAG","I am  stopMusic");
//		}
		
	}
	
	
	/** 
	* @Description: TODO 设置音量大小
	* @param @param volume  
	* @return void 
	*/
	private void setVoice(int volume ){
		Log.i("TAG","maxVolume :"+(float)(maxVolume));
		maxVolume = maxVolume+volume;
		if (belmotPlayer != null ) {
//			belmotPlayer.setVolume((float)(maxVolume),(float) (maxVolume));
			Log.i("TAG","I am  setVoice");
		}
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}