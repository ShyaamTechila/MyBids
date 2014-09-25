package com.techila.mybids.Webservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.techila.mybids.Params.RegFieldParams;

public class AppWebService {
	
	private static AppWebService appWebService;
	JSONObject response;
	
	public static AppWebService getInstance() {
		if (appWebService == null) {
			appWebService = new AppWebService();
		}
		return appWebService;
	}

		public String dataUpload(RegFieldParams fieldParams) {
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		
		parameters.add(new BasicNameValuePair("email", fieldParams.email));
		parameters.add(new BasicNameValuePair("userType", fieldParams.utype));
		parameters.add(new BasicNameValuePair("fname", fieldParams.fname));
		parameters.add(new BasicNameValuePair("lname", fieldParams.lname));
		parameters.add(new BasicNameValuePair("city", fieldParams.city));
		parameters.add(new BasicNameValuePair("state", fieldParams.state));
		parameters.add(new BasicNameValuePair("country", fieldParams.country));
		parameters.add(new BasicNameValuePair("zipCode", fieldParams.zipcode));
		parameters.add(new BasicNameValuePair("mobile", fieldParams.phnum));
		
		
		String paramString = URLEncodedUtils.format(parameters, "utf-8");
	
			Log.e("paramString: " , paramString);
			
		String url ="http://phbjharkhand.in/Reverseauction/web/app_dev.php/Registration"
				+ paramString;
		
		Log.e("url:----- " , url);
	
		// Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://phbjharkhand.in/Reverseauction/web/app_dev.php/Registration");

	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(9);
	        nameValuePairs.add(new BasicNameValuePair("fName", fieldParams.fname));
            nameValuePairs.add(new BasicNameValuePair("lName", fieldParams.lname));
            nameValuePairs.add(new BasicNameValuePair("email", fieldParams.email));
            nameValuePairs.add(new BasicNameValuePair("state", fieldParams.state));
            nameValuePairs.add(new BasicNameValuePair("country", fieldParams.country));
            nameValuePairs.add(new BasicNameValuePair("city", fieldParams.city));
            nameValuePairs.add(new BasicNameValuePair("mobile", fieldParams.phnum));
            nameValuePairs.add(new BasicNameValuePair("userType", fieldParams.utype));
            nameValuePairs.add(new BasicNameValuePair("zipCode", fieldParams.zipcode));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        // Execute HTTP Post Request
	        //HttpResponse response = httpclient.execute(httppost);

	        //JSONObject respBody = new JSONObject(response);
	        
	        ResponseHandler<String> responseHandler=new BasicResponseHandler();
	        String responseBody = httpclient.execute(httppost, responseHandler);
	        try {
				 response=new JSONObject(responseBody);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        Log.e("respose in part", response.toString());
	        return responseBody;
	        
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return null;
	}
	
}