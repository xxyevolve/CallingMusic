package com.xxyevolve.callingmusic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {
	private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        Button startServer = (Button)findViewById(R.id.start);
        Button stopServer = (Button)findViewById(R.id.stop);
        Button setVolume = (Button)findViewById(R.id.setvolume);
    	
        startServer.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		infoToast("开始服务");
        		sendOrderForService(Config.START_MUSIC_SERVER);
        		
        	}
        });
        
        stopServer.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		infoToast("停止服務");
        		sendOrderForService(Config.STOP_MUSIC_SERVER);
        	}
        });
        
        setVolume.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				sendOrderForService(Config.SET_VOLUME);
			}
		});
        
    }
    
    private void sendOrderForService(int order){
        Intent intent = new Intent(mContext,CallMusicService.class);
        Bundle bundle = new Bundle();
        bundle.putInt("msg", order);
        intent.putExtras(bundle);
        mContext.startService(intent);
    }
    
    private void infoToast(String str){
    	Toast toast = Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT);
    	toast.show();
    }
}
