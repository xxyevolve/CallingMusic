package com.xxyevolve.callingmusic.activity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.xxyevolve.callingmusic.R;
import com.xxyevolve.callingmusic.dao.Audio;
import com.xxyevolve.callingmusic.dao.AudioDao;
import com.xxyevolve.callingmusic.dao.AudioDaoImpl;
import com.xxyevolve.callingmusic.dao.MusicListAdapter;
import com.xxyevolve.callingmusic.utils.Constants;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TextView;


public class LocalMusicListActivity extends BaseListActivity {
	private AudioDao audioDao = new AudioDaoImpl(this);
	private ImageButton back_btn;
	private List<Audio> musicList;
	private String[] choices;
	private MusicListAdapter adapter;

	// @Override
	// protected void onPrepareDialog(int id, Dialog dialog) {
	// AlertDialog alertDialog = (AlertDialog) dialog;
	// ListView lv = alertDialog.getListView();
	// for (int i = 0; i < choices.length; i++) {
	// lv.setItemChecked(i, false);
	// }
	// super.onPrepareDialog(id, dialog);
	// }



	private void initListAdapter() {
		musicList = audioDao.getLocalAudioList();
		adapter = new MusicListAdapter(this, musicList, null);
		setListAdapter(adapter);
		// this.getListView().setOnItemLongClickListener(
		// new OnItemLongClickListener() {
		// @Override
		// public boolean onItemLongClick(AdapterView<?> parent,
		// View view, int position, long id) {
		// showItemLongClickDialog(id);
		// return false;
		// }
		// });
	}

	// private void showItemLongClickDialog(final long id) {
	// AlertDialog.Builder builder = CommonAlertDialogBuilder
	// .getInstance(this);
	// final CharSequence[] items = { "重命名", "删除" };
	// // TODO setMessage is something different with kugou's
	// builder.setItems(items, new DialogInterface.OnClickListener() {
	// @Override
	// public void onClick(DialogInterface dialog, int which) {
	// // TODO Auto-generated method stub
	// switch (which) {
	// // TODO 0,1 to constant
	// case 0:
	// break;
	// case 1:
	// // playlistDao.removePlaylist(String.valueOf(id));
	// initListAdapter();
	// break;
	// default:
	// break;
	// }
	//
	// }
	// }).setTitle("id:" + id);
	// AlertDialog alert = builder.create();
	// alert.show();
	// }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.local_audio_list);
		initListAdapter();
		initTopButton();
	}

	 private void initTopButton() {
			final Intent toPlayerActivity = new Intent(this, PlayerActivity.class);
			ImageButton player_button = (ImageButton) findViewById(R.id.player);
			player_button.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						startActivityForResult(toPlayerActivity,
								Constants.MENU_TO_PLAYER_REQUEST_CODE);
					}
					return false;
				}

			});
		}
	
	@Override
	protected void onResume() {
		super.onResume();
		adapter.notifyDataSetChanged();
	}


}
