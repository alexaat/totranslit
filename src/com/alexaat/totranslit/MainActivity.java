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
        
        spinner_language = (Spinner) findViewById(R.id.spinner_language);
        EditText_dialog_Source = (EditText) findViewById(R.id.EditText_dialog_Source);
        EditText_Result = (EditText) findViewById(R.id.EditText_Result);
        imageButton_Paste = (ImageButton) findViewById(R.id.imageButton_Paste);
        imageButton_Share = (ImageButton) findViewById(R.id.imageButton_Share);
        imageButton_Copy = (ImageButton) findViewById(R.id.imageButton_Copy);
        imageButton_Settings = (ImageButton) findViewById(R.id.imageButton_Settings);
        imageButton_Delete = (ImageButton) findViewById(R.id.imageButton_Delete);
        imageButton_Info = (ImageButton) findViewById(R.id.imageButton_Info);
        
        
    } 

     
  
}
