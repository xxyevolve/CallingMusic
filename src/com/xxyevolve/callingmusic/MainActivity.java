package com.xxyevolve.callingmusic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {
	private AudioManager audioManager;
	private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = (Button)findViewById(R.id.button1);
        Button btn2 = (Button)findViewById(R.id.button2);
        
    	AudioManager audioManager = (AudioManager)MainActivity.this.getSystemService(Context.AUDIO_SERVICE); 
    	audioManager.setMode(AudioManager.MODE_IN_CALL);
    	audioManager.setSpeakerphoneOn(false);
    	
        btn1.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		Toast tst = Toast.makeText(MainActivity.this, "开始播放", Toast.LENGTH_SHORT);
        		tst.show();
        		playMusic();
        		//sendStart();
        		
        	}
        });
        
        btn2.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		Toast tst = Toast.makeText(MainActivity.this, "停止", Toast.LENGTH_SHORT);
        		tst.show();
        		//playMusic();
        		//sendStop();
        		stopMusic();
        	}
        });
        
        mp = MediaPlayer.create(MainActivity.this, R.raw.chuanqi);
    }
    
    public void sendStop(){
    	Intent intent = new Intent("android.intent.action.MY_BROADCAST");
    	intent.putExtra("msg", "hello receiver.");
    	sendBroadcast(intent);
    }
    
    public void sendStart(){
    	Intent intent = new Intent("android.intent.action.MY_BROADCAST");
    	intent.putExtra("msg", "hello receiver.");
    	sendBroadcast(intent);
    }
    public void playMusic(){
    	//MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.ireland);
    	mp.start();
    }
    public void stopMusic(){
    	//MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.ireland);
    	mp.pause();
//    	mp.stop();
    }
    public void playingwithService(){
        Intent intent = new Intent(MainActivity.this,SoundService.class);
        intent.putExtra("playing", true);
        startService(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
