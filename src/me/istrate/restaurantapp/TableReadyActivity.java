package me.istrate.restaurantapp;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TableReadyActivity extends Activity {
//	JSONArray m_tables = null;
//	JSONObject m_table = new JSONObject();
//	SharedPreferences prefs;
//	Editor editor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.table_ready);
		
//		prefs = PreferenceManager.getDefaultSharedPreferences(this);
//		editor = prefs.edit();
//		String json = prefs.getString("alerts", "[]");
		
		 final EditText number= (EditText) findViewById(R.id.editText1);
		Button tableready = (Button) findViewById(R.id.sendtable);
		tableready.setOnClickListener( new OnClickListener() {			
			@Override
			public void onClick(View v) {
				String t = number.getText().toString();
				String ready = new String("Table "+ t);
				SendReady send = new SendReady(ready);
				new Thread(send).start();
			}
		});
	}
	public class SendReady implements Runnable {
		public String order ;
		public SendReady(String str) {
			order = str;
		}
        public void run() {
        	Log.i("ready","trying to send");
        	InetAddress serverAddr = null;
        	try {
				serverAddr = InetAddress.getByName("10.1.240.1");
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
                Socket socket = null;
				try {
					socket = new Socket(serverAddr,7080);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
                PrintWriter out = null;
				try {
					out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					out.println(order);
					Log.i("ready","Sent the ready table");
					
        }
        }
}

