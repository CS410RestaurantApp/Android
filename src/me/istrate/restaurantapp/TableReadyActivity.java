package me.istrate.restaurantapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TableReadyActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {   //shows table ready view
		super.onCreate(savedInstanceState);
		setContentView(R.layout.table_ready);
		                                                  
		Button sendtablenumber = (Button) findViewById(R.id.sendtable);  //button to send table
		sendtablenumber.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(TableReadyActivity.this, RestaurantActivity.class);
				startActivity(intent);
			}
		});	
		
	}
	
}

