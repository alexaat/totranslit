package com.alexaat.totranslit;


import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;


public class MainActivity extends Activity {

	Spinner spinner_language;
	EditText EditText_dialog_Source;
	ImageButton imageButton_Paste;
	EditText EditText_Result;
	ImageButton imageButton_Share;
	ImageButton imageButton_Copy;
	ImageButton imageButton_Settings;
	ImageButton imageButton_Delete;
	ImageButton imageButton_Info;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    } 

     
  
}
