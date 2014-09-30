package com.example.mybids;

import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.techila.mybids.Params.RegFieldParams;
import com.techila.mybids.Utils.Constant;
import com.techila.mybids.Utils.MyPreferences;
import com.techila.mybids.Webservice.AppWebService;

public class Login extends Activity {
	
	 Button login;
	 TextView signup, forgot;
	 String emailid, passw;
	 EditText email1,pass1;
	 int logn = 0;
	 
		private static final Pattern EMAIL_PATTERN = Pattern
				.compile("[a-zA-Z0-9+._%-+]{1,100}" + "@"
						+ "[a-zA-Z0-9][a-zA-Z0-9-]{0,10}" + "(" + "."
						+ "[a-zA-Z0-9][a-zA-Z0-9-]{0,20}"+
			              ")+");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		signup = (TextView) findViewById(R.id.reg);
		forgot = (TextView) findViewById(R.id.forgot);
		login = (Button) findViewById(R.id.login);
		
		email1 = (EditText) findViewById(R.id.username);
		pass1 = (EditText) findViewById(R.id.password);
		
		emailid = MyPreferences.getInstance(Login.this)
				.getUsrEmail();
		passw = MyPreferences.getInstance(Login.this).getpass();
		
		Log.e("Email", emailid);
		Log.e("Password", passw);
		
		if(emailid != null)
		{
			//send to home page//
			/*Intent homeintent = new Intent(Login.this,forgot.class);
			startActivity(homeintent);
			finish();*/
			email1.setText(emailid); 	// = emailid;
			pass1.setText(passw);
			
		}
		else{
			//do nothing- Let user use credentials//
		}
		
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RegFieldParams fieldParams = new RegFieldParams();
				fieldParams.ID = MyPreferences.getInstance(Login.this)
						.getUsrId();
				fieldParams.email = email1.getText().toString();
				fieldParams.passw = pass1.getText().toString();
				
				if(fieldParams.email == "")
				{
					email1.setError("Email is required!");
					logn = 1;
				}
				else {
					logn = 0;
					if (!CheckEmail(fieldParams.email)) {
						email1.setError( "Invalid Email!" );
						logn = 1;
					}
				}
				
				
				if(logn == 0){
				LoginTask loginTask = new LoginTask();
				loginTask.execute(fieldParams);
				}
				else {
					
				}
			}
			
			private boolean CheckEmail(String email) {

				return EMAIL_PATTERN.matcher(email).matches();
			}
			
			
		});
		
		signup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), Registration.class);
				startActivity(i);
			}
		});
		
		
		forgot.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Dialog dialog = new Dialog(Login.this);
				dialog.setContentView(R.layout.forgot_login_dialogue);
				dialog.setTitle("Get password by email.");
				
				final RegFieldParams fieldParams = new RegFieldParams();
				((Button) dialog.findViewById(R.id.btnMailLoginDetail))
						.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								
								
								
								EditText editEmail = (EditText) dialog
										.findViewById(R.id.editForgEmail);
								fieldParams.email = editEmail.getText()
										.toString().trim();
								
								if(fieldParams.email == "")
								{
									editEmail.setError("Email is required!");
								}
								else {
									if (!CheckEmail(fieldParams.email)) {
										editEmail.setError( "Invalid Email!" );
										
									}
								}
								
								Log.e("email", fieldParams.email);
								MailLoginDetailsTask loginDetailsTask = new MailLoginDetailsTask();
								loginDetailsTask.execute(fieldParams);
								
								dialog.dismiss();
							}
							
							private boolean CheckEmail(String email) {

								return EMAIL_PATTERN.matcher(email).matches();
							}
							
						});
				dialog.show();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return false;
	}

	private class MailLoginDetailsTask extends
	AsyncTask<RegFieldParams, Void, String> {

@Override
protected String doInBackground(RegFieldParams... params) {
	return AppWebService.getInstance().mailLoginDetails(params[0]);
}

@Override
protected void onPostExecute(String result) {
	super.onPostExecute(result);

	//Log.e("result", result);
	
	RegFieldParams fieldParams = new RegFieldParams();
	try {
		JSONObject jsonObj = new JSONObject(result);
		Log.e("jsonObject in part", jsonObj.toString());
		
		 for(int i=0; i<jsonObj.length();i++) {
			 
		        JSONObject jsonData = jsonObj.getJSONObject("data");
		       
		        String errorMessage = jsonData.getString("errorMessage");
		        Log.e("errorMessage", errorMessage);
		        
		        int errorCode = jsonData.getInt("errorCode");
		        Log.e("errorCode", "" + errorCode);
		    
		        if (errorCode == 0){
		        	Toast.makeText(Login.this, "Password Change Successfully, Please check for our mail.",Toast.LENGTH_LONG).show();
		        }
		 }
	
	} catch (JSONException e) {
		e.printStackTrace();
	}

}

}
	
	private class LoginTask extends AsyncTask<RegFieldParams, Void, String> {

		@Override
		protected String doInBackground(RegFieldParams... params) {
			return AppWebService.getInstance().verifyLogin(params[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			JSONObject jsonObject;
			RegFieldParams fieldParams = new RegFieldParams();
			try {
				jsonObject = new JSONObject(result);
				Log.e("jsonObject in part", jsonObject.toString());
				
				 for(int i=0; i<jsonObject.length();i++) {
					 
				        JSONObject jsonData = jsonObject.getJSONObject("data");
				       
				        String errorMessage = jsonData.getString("errorMessage");
				        Log.e("errorMessage", errorMessage);
				        
				        int errorCode = jsonData.getInt("errorCode");
				        Log.e("errorCode", "" + errorCode);
				     
				        if (errorCode == 0)
				        {
				        
				        		for(int j=0; j<jsonObject.length();j++) {
							 
				        			JSONObject results = jsonData.getJSONObject("result");
					       
				        			//String errorMess = results.getString("errorMessage");
				        			Log.e("userId", "" + results.getInt("userId"));
				        			MyPreferences.getInstance(Login.this)
									.storeUsrId(results.getInt("userId"));
				        			
				        			Log.e("email", results.getString("email"));
				        			MyPreferences.getInstance(Login.this)
											.storeUsrEmail(results.getString("email"));
				        			
				        			Log.e("fName", results.getString("fName"));
				        			MyPreferences.getInstance(Login.this)
									.storeUsrfName(results.getString("fName"));
				        			
				        			Log.e("lName", results.getString("lName"));
				        			MyPreferences.getInstance(Login.this)
									.storeUsrlName(results.getString("lName"));
				        			
				        			Log.e("userType", results.getString("userType"));
				        			MyPreferences.getInstance(Login.this)
									.storeUsrType(results.getString("userType"));
				        			
				        			
				        			
				        			Intent in1 = new Intent(Login.this,BidderHome.class);
				        			startActivity(in1);
				        			finish();
				        			
				        		}
				        }
				        
				        if (errorCode == 1) {
				        	
				        }
				        
				 }
			
			} catch (JSONException e) {
				e.printStackTrace();
			}
		
		}
	}
	
	
	
}
