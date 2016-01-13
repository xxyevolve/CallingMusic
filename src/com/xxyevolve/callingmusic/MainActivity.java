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
	private Button mStartServer, mStopServer, mSetVolumeAdd, mSetVolumeRdeuce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();
    }
    
    
    /** 
    * @Description: TODO 初始化view
    * @param   
    * @return void 
    */
    private void initView(){
    	  mStartServer = (Button)findViewById(R.id.start);
          mStopServer = (Button)findViewById(R.id.stop);
          mSetVolumeAdd = (Button)findViewById(R.id.setvolumeadd);
          mSetVolumeRdeuce = (Button)findViewById(R.id.setvolumereduce);
     	
         mStartServer.setOnClickListener(new OnClickListener(){
         	@Override
         	public void onClick(View v){
         		infoToast("开始服务");
         		sendOrderForService(Config.START_MUSIC_SERVER);
         		
         	}
         });
         
         mStopServer.setOnClickListener(new OnClickListener(){
         	@Override
         	public void onClick(View v){
         		infoToast("停止服務");
         		sendOrderForService(Config.STOP_MUSIC_SERVER);
         	}
         });
         
         mSetVolumeAdd.setOnClickListener(new OnClickListener() {
 			
 			@Override
 			public void onClick(View v) {
 				sendOrderForService(Config.SET_VOLUME_ADD);
 			}
 		});
         
         mSetVolumeRdeuce.setOnClickListener(new OnClickListener() {
 			
 			@Override
 			public void onClick(View v) {
 				sendOrderForService(Config.SET_VOLUME_REDUCE);
 			}
 		});
    }
    
    /** 
    * @Description: TODO 发送状态给CallMusicService 
    * @param @param order  
    * @return void 
    */
    private void sendOrderForService(int order){
        Intent intent = new Intent(mContext,CallMusicService.class);
        Bundle bundle = new Bundle();
        bundle.putInt("msg", order);
        intent.putExtras(bundle);
        mContext.startService(intent);
    }
    
    
    /** 
    * @Description: TODO 弹窗显示 
    * @param @param str  所显示的内容
    * @return void 
    */
    private void infoToast(String str){
    	Toast toast = Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT);
    	toast.show();
    }
}
