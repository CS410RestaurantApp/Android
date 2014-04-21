package me.istrate.restaurantapp;

import java.util.ArrayList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class OrderListActivity extends Activity {

	private ArrayList < Order > m_Orders = new ArrayList< Order >();
	int convert = 0;
	
	SharedPreferences prefs;
	JSONArray jsonArray = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_list);

		prefs = PreferenceManager.getDefaultSharedPreferences(this);

		String json = prefs.getString("orders", "[]");
		//get json array
		try {
			jsonArray = new JSONArray(json);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < jsonArray.length(); i++) {
			int tableNr = 0;
			String sTableNr = "";
			String orderContent = "";
			try {
				sTableNr = jsonArray.getJSONObject(i).get("Table").toString();
				orderContent = jsonArray.getJSONObject(i).get("Order").toString();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			//mothafucka
			tableNr = Integer.parseInt(sTableNr+0) / 10;
			m_Orders.add(new Order().TableNumber(tableNr).OrderContent(orderContent));
		}		
		
		ListView orderList = (ListView)findViewById(R.id.order_list);
		ArrayAdapter<Order> adapter = new ArrayAdapter<Order>(this, android.R.layout.simple_list_item_1, m_Orders);
		orderList.setAdapter(adapter);
		
		orderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Toast toast = Toast.makeText(getApplicationContext(), "Item at position " + position, Toast.LENGTH_LONG);
				toast.show();
			}
			
		});
	
		Button addOrder = (Button) findViewById(R.id.order_button);
		addOrder.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(OrderListActivity.this, OrderActivity.class);
				startActivity(intent);
			}
		});			
		
	}
}
