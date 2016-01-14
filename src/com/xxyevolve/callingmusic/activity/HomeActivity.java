package com.xxyevolve.callingmusic.activity;

import com.xxyevolve.callingmusic.CallMusicService;
import com.xxyevolve.callingmusic.R;
import com.xxyevolve.callingmusic.R.id;
import com.xxyevolve.callingmusic.R.layout;
import com.xxyevolve.callingmusic.utils.CallMusicConfig;
import com.xxyevolve.callingmusic.utils.Constants;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class HomeActivity extends Activity {
	private Context mContext;
	private Button mStartServer, mStopServer, mSetVolumeAdd, mSetVolumeRdeuce;
	private Button mGoto;
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
          mGoto = (Button)findViewById(R.id.button1);
          
          mGoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent toLocalMusicListActivity = new Intent(HomeActivity.this,LocalMusicListActivity.class);
				startActivity(toLocalMusicListActivity);
			}
		});
          
         mStartServer.setOnClickListener(new OnClickListener(){
         	@Override
         	public void onClick(View v){
         		infoToast("开始服务");
         		sendOrderForService(CallMusicConfig.START_MUSIC_SERVER);
         		
         	}
         });
         
         mStopServer.setOnClickListener(new OnClickListener(){
         	@Override
         	public void onClick(View v){
         		infoToast("停止服務");
         		sendOrderForService(CallMusicConfig.STOP_MUSIC_SERVER);
         	}
         });
         
         mSetVolumeAdd.setOnClickListener(new OnClickListener() {
 			
 			@Override
 			public void onClick(View v) {
 				sendOrderForService(CallMusicConfig.SET_VOLUME_ADD);
 			}
 		});
         
         mSetVolumeRdeuce.setOnClickListener(new OnClickListener() {
 			
 			@Override
 			public void onClick(View v) {
 				sendOrderForService(CallMusicConfig.SET_VOLUME_REDUCE);
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
    	Toast toast = Toast.makeText(HomeActivity.this, str, Toast.LENGTH_SHORT);
    	toast.show();
    }
}
