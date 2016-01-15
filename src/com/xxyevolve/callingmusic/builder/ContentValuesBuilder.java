package com.xxyevolve.callingmusic.builder;

import java.lang.reflect.Field;

import com.xxyevolve.callingmusic.utils.BaseDomain;
import com.xxyevolve.callingmusic.utils.Column;

import android.content.ContentValues;


public class ContentValuesBuilder {
	private static ContentValuesBuilder instance;
	public static ContentValues mContentValues;

	// private static String TABLE_NAME = "tableName";

	public static ContentValuesBuilder getInstance() {
		if (null == instance) {
			instance = new ContentValuesBuilder();
		}
		mContentValues = new ContentValues();
		return instance;
	}

	public <T extends BaseDomain> ContentValues bulid(T domain)
			throws IllegalArgumentException, IllegalAccessException {
		// Table table = domain.getClass().getAnnotation(Table.class);
		// mContentValues.put(TABLE_NAME, table.name());

		for (Field f : domain.getClass().getDeclaredFields()) {
			if (f.getAnnotations().length != 0) {
				f.setAccessible(true);
				f.getType().getName();
				// Class.forName(f.getType().getName()).newInstance();

				mContentValues.put(f.getAnnotation(Column.class).name(), f.get(
						domain).toString());
			}
		}

		// domain.getClass().isAnnotationPresent(Table.class);
		return mContentValues;

	}
}
