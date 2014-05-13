package me.istrate.restaurantapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;

public class ListenServer implements Runnable {
	
	Context mContext;
	SharedPreferences prefs;
	Editor editor;
	JSONArray jsonArray = null;
	JSONObject jsonObject = new JSONObject();
	
	public ListenServer(Context context){
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		editor = prefs.edit();
		mContext = context;
	}
	
	@Override
	public void run() {
		
		//this is where you put your json string kind sir
		String json = prefs.getString("orders", "[]");
		try {
			jsonArray = new JSONArray(json);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
		}
		
		while(!Thread.currentThread().isInterrupted()){
			ServerSocket socket =null;
			try {
				 socket = new ServerSocket(GlobalV.listeningPortAlerts);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Socket alertSocket = null;
			try {
				Log.i("Server ","Waiting for alerts");
				alertSocket = socket.accept();
				Log.i("Server", "connectin recieved from the server");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedReader input=null;
			try {
				input = new BufferedReader(new InputStreamReader(alertSocket.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				try {
					String read = input.readLine();
					try {
						jsonObject.put("Alert", read);
						jsonArray.put(jsonObject);
						editor.putString("alerts", jsonArray.toString());
						editor.commit();
						
					} catch (JSONException e) {
						e.printStackTrace();
					}
					Log.i("Alert", read);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		
	}

}
