package com.example.mybids;


import java.text.BreakIterator;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.techila.mybids.Utils.*;
import com.techila.mybids.Params.RegFieldParams;
import com.techila.mybids.Webservice.AppWebService;

public class Registration extends Activity {
	
	 Button btn1, btn2;
	 	private EditText edit_email;
		private EditText edit_cpassw;
		private EditText edit_fname;
		private EditText edit_lname;
		private EditText edit_zipcode;
		private EditText edit_city;
		private EditText edit_state;
		private EditText edit_country;
		private EditText edit_phnum;
		private EditText edit_usertype;
		
		int vali = 0; 
		
		private static final Pattern EMAIL_PATTERN = Pattern
				.compile("[a-zA-Z0-9+._%-+]{1,100}" + "@"
						+ "[a-zA-Z0-9][a-zA-Z0-9-]{0,10}" + "(" + "."
						+ "[a-zA-Z0-9][a-zA-Z0-9-]{0,20}"+
			              ")+");
		private static final Pattern USERNAME_PATTERN = Pattern
				.compile("[a-zA-Z0-9]{1,250}");
		private static final Pattern PASSWORD_PATTERN = Pattern
				.compile("[a-zA-Z0-9+_.]{4,16}");
		private static final Pattern ALPHANUMERIC = Pattern
				.compile("^[a-zA-Z0-9]*$");
		
		

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reg);
		
		btn1 = (Button) findViewById(R.id.login);
		
		edit_fname = (EditText) findViewById(R.id.fname);
		edit_lname = (EditText) findViewById(R.id.lname);
		edit_email = (EditText) findViewById(R.id.editemail);
		
		edit_usertype = (EditText) findViewById(R.id.editutype);
		
		edit_phnum = (EditText) findViewById(R.id.editphnum);
		edit_city = (EditText) findViewById(R.id.editcity);
		edit_state = (EditText) findViewById(R.id.editstate);
		edit_country = (EditText) findViewById(R.id.editcountry);
		edit_zipcode = (EditText) findViewById(R.id.editzip);
		//edit_cpassw = (EditText) findViewById(R.id.editcpassw);
		
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub//
				
				//Validation check//
				
				
				//go ahed to upload registration data//
				final RegFieldParams fieldParams = new RegFieldParams();
				
				fieldParams.fname = edit_fname.getText().toString().trim();
				fieldParams.lname = edit_lname.getText().toString().trim();
				fieldParams.email = edit_email.getText().toString().trim();
				
				fieldParams.utype = edit_usertype.getText().toString().trim();
				
				fieldParams.phnum = edit_phnum.getText().toString().trim();
				fieldParams.city = edit_city.getText().toString().trim();
				fieldParams.state = edit_state.getText().toString().trim();
				fieldParams.country = edit_country.getText().toString().trim();
				fieldParams.zipcode = edit_zipcode.getText().toString().trim();
				
				if (fieldParams.fname.equals("") || fieldParams.lname.equals("")
						|| fieldParams.email.equals("") || fieldParams.utype.equals("")
						|| fieldParams.phnum.equals("") || fieldParams.city.equals("")
						|| fieldParams.state.equals("") || fieldParams.country.equals("")
						|| fieldParams.zipcode.equals("")) 
				{
					vali = 1;
					
					if (fieldParams.fname.equals("")) {
						//Toast.makeText(Registration.this, "ENTER First",Toast.LENGTH_LONG).show();
						edit_fname.setError( "First name is required!" );

					}
					else if (fieldParams.lname.equals("")) {
						//Toast.makeText(Registration.this, "ENTER USERNAME",	Toast.LENGTH_LONG).show();
						edit_lname.setError( "Last name is required!" );
					}
					else if (fieldParams.email.equals("")) {
						//Toast.makeText(Registration.this, "ENTER EMAIL ID",Toast.LENGTH_LONG).show();
						edit_email.setError( "Email is required!" );

					}
					else if (fieldParams.utype.equals("")) {
						edit_usertype.setError( "Select usertype!" );
					}
					else if (fieldParams.phnum.equals("")) {
						edit_phnum.setError( "Mobile number required!" );
					}
					else if (fieldParams.city.equals("")) {
						edit_city.setError( "City required!" );
					}
					else if (fieldParams.state.equals("")) {
						edit_state.setError( "State required!" );
					}
					else if (fieldParams.country.equals("")) {
						edit_country.setError( "Country required!" );
					}
					else if (fieldParams.zipcode.equals("")) {
						edit_zipcode.setError( "Zipcode required!" );
					}
					else{ }
					
				} else {
					
					vali = 0;
					
					if (!CheckUsername(fieldParams.fname)) {
						edit_fname.setError( "Invalid First name!" );
						vali = 1;
					}
					if (!CheckUsername(fieldParams.lname)) {
						edit_lname.setError( "Invalid Last name!" );
						vali = 1;
					}
					if (!CheckEmail(fieldParams.email)) {
						edit_email.setError( "Invalid Email!" );
						vali = 1;
					}
					if (!Checkalphanumeric(fieldParams.utype)) {
						edit_phnum.setError( "Invalid Usertype!" );
						vali = 1;
					}
					if (!Checkalphanumeric(fieldParams.phnum)) {
						edit_phnum.setError( "Invalid Mobile Number!" );
						vali = 1;
					}
					if (!Checkalphanumeric(fieldParams.city)) {
						edit_city.setError( "Invalid City!" );
						vali = 1;
					}
					if (!Checkalphanumeric(fieldParams.state)) {
						edit_state.setError( "Invalid State!" );
						vali = 1;
					}
					if (!Checkalphanumeric(fieldParams.country)) {
						edit_country.setError( "Invalid Country!" );
						vali = 1;
					}
					if (!Checkalphanumeric(fieldParams.zipcode)) {
						edit_zipcode.setError( "Invalid Zipcode!" );
						vali = 1;
					}
				}
				
				if (vali == 0) {
					RegDataUploadTask regDataUpTask = new RegDataUploadTask();	//call to upload data
					regDataUpTask.execute(fieldParams);
				}
				else {
					
				}
				
				
			}
			
			private boolean CheckEmail(String email) {

				return EMAIL_PATTERN.matcher(email).matches();
			}

			private boolean CheckPassword(String password) {

				return PASSWORD_PATTERN.matcher(password).matches();
			}

			private boolean CheckUsername(String username) {

				return USERNAME_PATTERN.matcher(username).matches();
			}
			private boolean Checkalphanumeric(String aphanum) {

				return ALPHANUMERIC.matcher(aphanum).matches();
			}
		});
		
		
		
	}

    public void showDialog(View v)
    {
    	final CharSequence[] items={"Bidder","Recharger"};
    	AlertDialog.Builder builder=new AlertDialog.Builder(this);
    	builder.setTitle("User Type");
    	
     	builder.setSingleChoiceItems(items,-1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				LinearLayout ll=(LinearLayout)findViewById(R.id.linear);
				
				if("Bidder".equals(items[which]))
				{
				edit_usertype.setText("Bidder");
				dialog.dismiss();
				}
				else if("Recharger".equals(items[which]))
				{
					edit_usertype.setText("Recharger");
					dialog.dismiss();
				}
			}
		});
    	builder.show();
    
    }
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return false;
	}
	

	
private class RegDataUploadTask extends
	AsyncTask<RegFieldParams, Void, String> {

private int TASK;

public RegDataUploadTask() {
	//this.TASK = task;
}

@Override
protected String doInBackground(RegFieldParams... params) {
		return AppWebService.getInstance().dataUpload(params[0]);
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
			        			MyPreferences.getInstance(Registration.this)
								.storeUsrId(results.getInt("userId"));
			        			
			        			Log.e("email", results.getString("email"));
			        			MyPreferences.getInstance(Registration.this)
										.storeUsrEmail(results.getString("email"));
			        			
			        			Log.e("fName", results.getString("fName"));
			        			MyPreferences.getInstance(Registration.this)
								.storeUsrfName(results.getString("fName"));
			        			
			        			Log.e("lName", results.getString("lName"));
			        			MyPreferences.getInstance(Registration.this)
								.storeUsrlName(results.getString("lName"));
			        			
			        			Log.e("userType", results.getString("userType"));
			        			MyPreferences.getInstance(Registration.this)
								.storeUsrType(results.getString("userType"));
			        			
			        			Log.e("password", results.getString("password"));
			        			MyPreferences.getInstance(Registration.this)
								.storepass( results.getString("password"));
			        			
			        			Log.e("Mobile", results.getString("mobile"));
			        			MyPreferences.getInstance(Registration.this)
								.storeUsrPhoneNum( results.getString("mobile"));
			        			
			        			Log.e("city", results.getString("city"));
			        			MyPreferences.getInstance(Registration.this)
								.storeCity( results.getString("city"));
			        			
			        			Log.e("state", results.getString("state"));
			        			MyPreferences.getInstance(Registration.this)
								.storeState( results.getString("state"));
			        			
			        			Log.e("country", results.getString("country"));
			        			MyPreferences.getInstance(Registration.this)
								.storeCountry( results.getString("country"));
				       
			        			
			        			Log.e("zip_code", results.getString("zipCode"));
			        			MyPreferences.getInstance(Registration.this)
								.storeCountry( results.getString("zipCode"));
			        			
			        			Intent in1 = new Intent(Registration.this,Login.class);
			        			startActivity(in1);
			        			finish();
			        			
			        		}
			        }
			        
			        if (errorCode == 4) {
			        	edit_email.setError( "Email already used!" );
						vali = 1;
			        }
			        
			 }
		
		} catch (JSONException e) {
			e.printStackTrace();
		}
	
}


}//end of class
	
	
}//end of registration class
	
	

