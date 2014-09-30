package com.example.mybids;


import info.android.customlistviewvolley.adater.CustomListAdapter;
import info.android.customlistviewvolley.app.AppController;

import com.example.mybids.Product;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

public class BidderHome extends Activity {
	
	String tag_json_obj = "json_obj_req";
	private String TAG = BidderHome.class.getSimpleName();
	 
	private List<Product> productList = new ArrayList<Product>();
	private ListView listView;
	private CustomListAdapter adapter;
	String url = "http://phbjharkhand.in/Reverseauction/web/app_dev.php/ProductListing";
		
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bidderhome);
		
		listView = (ListView) findViewById(R.id.prodlist);
		adapter = new CustomListAdapter(this, productList);
		listView.setAdapter(adapter);
		
		// Creating volley request obj
		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
 
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        
                        for(int i=0;i<response.length();i++){
                        	
                        	try {
                        		JSONObject obj = response.getJSONObject("data");
                        		 Log.d(TAG, obj.toString());
                        		 
                        		 JSONArray resultArry = obj.getJSONArray("result");
                                 ArrayList<String> result = new ArrayList<String>();
                                 Log.d("result", resultArry.toString());
                                 
                                 for (int j = 0; j < resultArry.length(); j++) {
                                	 JSONObject resultObj = (JSONObject) resultArry.get(j);
                                	 
                                	 Product product = new Product();
                                	 product.setTitle(resultObj.getString("pName"));
                                	 product.setThumbnailUrl("http://phbjharkhand.in/Reverseauction/web/images/" + resultObj.getString("pImage"));
                                	 product.setRating(resultObj.getString("pBrandName"));
                                	 product.setYear(resultObj.getString("pRetailPrize"));
                                	 
                                	// adding movie to movies array
     								productList.add(product);
     								 
                                	 Log.d("result","------------------" + j +"-------------------");
                                	 
                                	 Log.d("Image",resultObj.getString("pImage"));
                                	 
                                	 Log.d("ID","" + resultObj.getInt("productId"));
                                	 Log.d("NAME",resultObj.getString("pName"));
                                	 Log.d("Brand",resultObj.getString("pBrandName"));
                                	 Log.d("Type",resultObj.getString("pType"));
                                	 Log.d("RetailPrize",resultObj.getString("pRetailPrize"));
                                	 Log.d("Description",resultObj.getString("pDescription"));
                                	 
                                	 Log.d("result","-------------------------------------");
                                	 
                                 }
                                 
                                 adapter.notifyDataSetChanged();
                        		
							} catch (Exception e) {
								// TODO: handle exception
							}
                        	
                        }
                        
                        
                    }
                }, new Response.ErrorListener() {
 
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        // hide the progress dialog
                        
                    }
                });
		
		
		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return false;
	}
	

	
	
	

	
	
}//end of main class
	
	

