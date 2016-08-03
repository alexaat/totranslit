package com.alexaat.totranslit;

import android.support.v7.app.ActionBarActivity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;


public class Help extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		 android.support.v7.app.ActionBar mActionBar =this.getSupportActionBar();
	     mActionBar.setBackgroundDrawable(new ColorDrawable(getResources()
	     .getColor(R.color.platinum)));
	}

	
	
}
