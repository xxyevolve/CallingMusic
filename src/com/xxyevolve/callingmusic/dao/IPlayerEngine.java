package com.xxyevolve.callingmusic.dao;

import java.util.List;

import com.xxyevolve.callingmusic.dao.PlayerEngineImpl.PlaybackMode;


import android.media.MediaPlayer.OnCompletionListener;

public interface IPlayerEngine {

	void play();

	void reset();

	void stop();

	void pause();

	void previous();

	void next();

	void skipTo(int index);

	void forward(int time);

	void rewind(int time);

	boolean isPlaying();

	boolean isPause();

	String getPlayingPath();

	void setPlayingPath(String path);

	void setMediaPathList(List<String> pathList);

	void start();

	void setOnCompletionListener(OnCompletionListener onCompletionListener);

	void setPlaybackMode(PlaybackMode playbackMode);

	PlaybackMode getPlayMode();

	String getCurrentTime();

	String getDurationTime();

	int getDuration();

	int getCurrentPosition();

}
