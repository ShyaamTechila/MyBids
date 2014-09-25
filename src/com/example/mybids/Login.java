package com.example.mybids;

import com.techila.mybids.Utils.MyPreferences;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity {
	
	 Button btn1, btn2;
	 TextView signup, forgot;
	 String emailid, passw;
	 EditText email1,pass1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		signup = (TextView) findViewById(R.id.reg);
		forgot = (TextView) findViewById(R.id.forgot);
		
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
				Intent i1 = new Intent(getApplicationContext(), forgot.class);
				startActivity(i1);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return false;
	}

}
