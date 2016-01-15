package com.xxyevolve.callingmusic.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.xxyevolve.callingmusic.builder.ContentValuesBuilder;
import com.xxyevolve.callingmusic.utils.BaseDomain;

import android.app.ListActivity;
import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public abstract class BaseListActivity extends ListActivity {
	LinearLayout pop_menu_layout;
	Menu buttomMenu;
	ImageButton tab_menu;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}


	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("Ingore");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		// Toast.makeText(getBaseContext(),
		// "CurrentContext:" + this.getClass().getName(),
		// Toast.LENGTH_SHORT).show();
		return false;// show system should return true.
	}
	@Override
	public void onOptionsMenuClosed(Menu menu) {
	}


	protected <T extends BaseDomain> ContentValues bulid(T domain) {
		ContentValues cv = null;
		try {
			cv = ContentValuesBuilder.getInstance().bulid(domain);
		} catch (IllegalArgumentException e) {
			Log.e("BaseListActivity", e.getMessage());
		} catch (IllegalAccessException e) {
			Log.e("BaseListActivity", e.getMessage());
		}
		return cv;
	}
}
