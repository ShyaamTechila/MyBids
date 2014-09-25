package com.techila.mybids.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class MyPreferences {
	private static Context context;
	private static MyPreferences myPreferences;

	private MyPreferences() {
	}

	
	public static MyPreferences getInstance(Context context) {
		MyPreferences.context = context;
		if (myPreferences == null) {
			myPreferences = new MyPreferences();
		}
		return myPreferences;
	}

	
	//Storing email in local storage - shared preference//
	public void storeUsrEmail(String email) {
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.PreferenceVar.PREF_NAME, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(Constant.PreferenceVar.EMAIL, email);
		editor.commit();
	}

	//Retrieving email from local storage - shared preference//
	public String getUsrEmail() {
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.PreferenceVar.PREF_NAME, Context.MODE_PRIVATE);
		return preferences.getString(Constant.PreferenceVar.EMAIL, "");
	}
	
	public void storepass(String pass) {
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.PreferenceVar.PREF_NAME, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(Constant.PreferenceVar.PASS, pass);
		editor.commit();
	}
	
	public String getpass() {
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.PreferenceVar.PREF_NAME, Context.MODE_PRIVATE);
		return preferences.getString(Constant.PreferenceVar.PASS, "");
	}
	
	public void storeUsrUniqueCode(String code) {
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.PreferenceVar.PREF_NAME, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(Constant.PreferenceVar.UNIQUE_CODE, code);
		editor.commit();
	}

	public String getUniqueCode() {
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.PreferenceVar.PREF_NAME, Context.MODE_PRIVATE);
		return preferences.getString(Constant.PreferenceVar.UNIQUE_CODE, "");
	}

	public void storeUsrPhoneNum(String phnum) {
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.PreferenceVar.PREF_NAME, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(Constant.PreferenceVar.PHONE_NUM, phnum);
		editor.commit();
	}

	public String getUsrPhoneNum() {
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.PreferenceVar.PREF_NAME, Context.MODE_PRIVATE);
		return preferences.getString(Constant.PreferenceVar.PHONE_NUM, "");
	}

	public void storeCountry(String country) {
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.PreferenceVar.PREF_NAME, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(Constant.PreferenceVar.COUNTRY, country);
		editor.commit();
	}

	public String getUsrCountry() {
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.PreferenceVar.PREF_NAME, Context.MODE_PRIVATE);
		return preferences.getString(Constant.PreferenceVar.COUNTRY, "");
	}

	public void storeCity(String city) {
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.PreferenceVar.PREF_NAME, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(Constant.PreferenceVar.CITY, city);
		editor.commit();
	}
	
	public String getUsrCity() {
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.PreferenceVar.PREF_NAME, Context.MODE_PRIVATE);
		return preferences.getString(Constant.PreferenceVar.CITY, "");
	}

	
	public void storeState(String state) {
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.PreferenceVar.PREF_NAME, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(Constant.PreferenceVar.STATE, state);
		editor.commit();
	}
	
	public String getUsrState() {
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.PreferenceVar.PREF_NAME, Context.MODE_PRIVATE);
		return preferences.getString(Constant.PreferenceVar.STATE, "");
	}
	
	
	public void storeZip(String zip) {
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.PreferenceVar.PREF_NAME, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(Constant.PreferenceVar.ZIP, zip);
		editor.commit();
	}
	
	public String getUsrZip() {
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.PreferenceVar.PREF_NAME, Context.MODE_PRIVATE);
		return preferences.getString(Constant.PreferenceVar.ZIP, "");
	}
	
	
	public void storeUsrId(int Id) {
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.PreferenceVar.PREF_NAME, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putInt(Constant.PreferenceVar.USER_ID, Id);
		editor.commit();
	}

	public int getUsrId() {
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.PreferenceVar.PREF_NAME, Context.MODE_PRIVATE);
		return preferences.getInt(Constant.PreferenceVar.USER_ID, -1);
	}

	public void storeUsrfName(String name) {
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.PreferenceVar.PREF_NAME, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(Constant.PreferenceVar.USER_FNAME, name);
		editor.commit();
	}

	public String getUsrfName() {
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.PreferenceVar.PREF_NAME, Context.MODE_PRIVATE);
		return preferences.getString(Constant.PreferenceVar.USER_FNAME, "");
	}
	
	public void storeUsrlName(String name) {
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.PreferenceVar.PREF_NAME, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(Constant.PreferenceVar.USER_LNAME, name);
		editor.commit();
	}

	public String getUsrlName() {
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.PreferenceVar.PREF_NAME, Context.MODE_PRIVATE);
		return preferences.getString(Constant.PreferenceVar.USER_LNAME, "");
	}

	public void storeUsrType(String type) {
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.PreferenceVar.PREF_NAME, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(Constant.PreferenceVar.USER_TYPE, type);
		editor.commit();
	}

	public String getUsrType() {
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.PreferenceVar.PREF_NAME, Context.MODE_PRIVATE);
		return preferences.getString(Constant.PreferenceVar.USER_TYPE, "");
	}


	public void storeUsrPicName(String picName) {
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.PreferenceVar.PREF_NAME, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(Constant.PreferenceVar.USER_PIC, picName);
		editor.commit();
	}

	public String getUsrPicName() {
		SharedPreferences preferences = context.getSharedPreferences(
				Constant.PreferenceVar.PREF_NAME, Context.MODE_PRIVATE);
		return preferences.getString(Constant.PreferenceVar.USER_PIC, "");
	}

	
}