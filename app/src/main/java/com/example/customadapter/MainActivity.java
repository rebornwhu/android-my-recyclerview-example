package com.example.customadapter;

import android.os.Bundle;
import android.app.ListActivity;

public class MainActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		CustomAdapter customAdapter = new CustomAdapter(this);
		setListAdapter(customAdapter);
	}
}