package com.alexaat.totranslit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;






public class AppPreferences extends ActionBarActivity {


GridView Language_Table;
Spinner Spinner_languages;
Button Button_New;
Button Button_Delete;
Button Button_Add;
Button Button_Copy;
Button Button_Merge;
Button Button_Back;
	

	
	/*
	Spinner preferencesSpinner; 
	TextView preferencesTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app_preferences);
		
		android.support.v7.app.ActionBar mActionBar =this.getSupportActionBar();
	    mActionBar.setBackgroundDrawable(new ColorDrawable(getResources()
	    .getColor(R.color.platinum)));
		
	    //getActionBar().setIcon(R.drawable.ic_menu_preferences);
	    mActionBar.setIcon(R.drawable.ic_menu_preferences);
		
	    preferencesSpinner = (Spinner) findViewById(R.id.preferencesSpinner);
	    
	    DatabaseHelper db = new DatabaseHelper(this);
	    List<String> tables = db.getListOfTables();
	    db.close();
	    
	    for(int i = 0; i<tables.size(); i++){
	    	String item = tables.get(i);
	    	item = item.replace("_to_", ">>>");
	    	tables.set(i, item);
	    }
	    
	    
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tables);
	    preferencesSpinner.setAdapter(adapter);
	    
	    
	    
	    preferencesTextView = (TextView) findViewById(R.id.preferencesTextView);
	    preferencesTextView.setText(tables.get(0));
	    
	    
	    
	}
*/
	
	
    Map<String,String> normalized_table = new HashMap<String,String>();	
    Context context = AppPreferences.this;

  	@Override
   	protected void onCreate(Bundle savedInstanceState) {
  		super.onCreate(savedInstanceState);
  		setContentView(R.layout.activity_app_preferences);
  		
		android.support.v7.app.ActionBar mActionBar =this.getSupportActionBar();
	    mActionBar.setBackgroundDrawable(new ColorDrawable(getResources()
	    .getColor(R.color.platinum)));
		
	    mActionBar.setIcon(R.drawable.ic_menu_preferences);
  		
  		
		Spinner_languages = (Spinner) findViewById(R.id.Spinner_languages);
		Language_Table = (GridView) findViewById(R.id.GridView_Language_Table);
		Button_New =  (Button) findViewById(R.id.Button_New);
		Button_Delete = (Button)  findViewById(R.id.Button_Delete);
		Button_Add = (Button)  findViewById(R.id.Button_Add);
		Button_Copy = (Button)  findViewById(R.id.Button_Copy);
		Button_Merge = (Button)  findViewById(R.id.Button_Merge);
		Button_Back = (Button)  findViewById(R.id.Button_Back);	
  	
  	
		String SELECTED_LANGUAGE = "";
			
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    SELECTED_LANGUAGE = extras.getString(MainActivity.SELECTED_LANGUAGE_TAG);
		}
  		
		Set_Spinner(SELECTED_LANGUAGE);
		Set_Listeners();
  	
  	}

	
  	private void Set_Spinner(String table_name){
  		
  		 	table_name = table_name.replace("_to_", " >>> ");
  		
  		 
 	       // Get only language table in format Source_To_Result
 	       List<String> tableNames = new ArrayList<String>();
 	       DatabaseHelper db = new DatabaseHelper(this);
 	       tableNames = db.getListOfTables();
 	       db.close();
 	        
          // Display in format Source > > > Result
 	        for(int i = 0; i<tableNames.size();i++){
 	        	
 	        	String s = tableNames.get(i);
 	        	s = s.replace("_to_", " >>> ");
 	        	tableNames.set(i, s);
 	        	
 	        }
 	               
 	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, tableNames);
 	        
 	   	    Spinner_languages.setAdapter(adapter);
 	        
 	        
 	        
 	       // Spinner_languages.setSelection(Set_Selecion_position);
 	        int position=0;
 	        for(int i = 0; i<Spinner_languages.getCount(); i++){
 	        	if(Spinner_languages.getItemAtPosition(i).toString().equals(table_name)){
 	        		position = i;
 	        	}
 	        	
 	        }
 	        
 	        if(Spinner_languages.getCount()>0){
 	        Spinner_languages.setSelection(position);
 	        }
 	    
 	        
 	        if(Spinner_languages.getCount() == 0){
 	            Language_Table.setAdapter(null);
 	        }
 	         	        
 	    	SetSettingsControlEnableDisable(Spinner_languages.getCount());	   
 	         	        
 	    	
 	    }
  	
    private void SetSettingsControlEnableDisable(int number_of_languages){
		

		if(number_of_languages>=0){
		
		switch (number_of_languages){
		
		case 0:
			
			Button_Add.setEnabled(false);
			Button_Delete.setEnabled(false);
			Button_Copy.setEnabled(false);
			Button_Merge.setEnabled(false);
			
			break;
		
		case 1:
			
			Button_Add.setEnabled(true);
			Button_Delete.setEnabled(true);
			Button_Copy.setEnabled(true);
			Button_Merge.setEnabled(false);
			
			break;
		
		default:
			
			Button_Add.setEnabled(true);
			Button_Delete.setEnabled(true);
			Button_Copy.setEnabled(true);
			Button_Merge.setEnabled(true);
			
			break;
		}
		}
		else
		{
			
		 Toast.makeText(getApplicationContext(), "Number of items in spinner less than 0", Toast.LENGTH_SHORT).show();
		}
	}  
	
    private void Set_Listeners(){
    	
    	Spinner_languages.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				//Spinner_Selected_Position = position;
				
				String table_name;
				table_name = Spinner_languages.getSelectedItem().toString();
				table_name = table_name.replace(" >>> ", "_to_");
				
				List<String> items = new ArrayList<String>();
				Map<String,String> itemsMap = new TreeMap<String,String>();
				DatabaseHelper db = new DatabaseHelper(AppPreferences.this);
				itemsMap = db.Get(table_name);
				db.close();
				
				
				
				
				for(Map.Entry<String,String> entry : itemsMap.entrySet()) {
					String key = entry.getKey();
					String value = entry.getValue();
					items.add(key + " >>> " + value);
					
				}
				
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(AppPreferences.this,
						android.R.layout.simple_list_item_1,
						items);

				
				
				Language_Table.setAdapter(adapter);
					
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			
				
			}});
    	    	
    	Language_Table.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
			
				// custom dialog
				final Dialog dialog_edit = new Dialog(context);
				dialog_edit.setContentView(R.layout.edit_layout);
				dialog_edit.setTitle("Edit                        ");
				
				// Set controls
				Button edit_layout_Save_Button = (Button) dialog_edit.findViewById(R.id.edit_layout_Save_Button);
				Button edit_layout_Cancel_Button = (Button) dialog_edit.findViewById(R.id.edit_layout_Cancel_Button);
				Button edit_layout_Delete_Button = (Button) dialog_edit.findViewById(R.id.edit_layout_Delete_Button);
				final EditText edit_layout_key_EditText = (EditText)  dialog_edit.findViewById(R.id.edit_layout_key_EditText);
				final EditText edit_layout_value_EditText = (EditText)  dialog_edit.findViewById(R.id.edit_layout_value_EditText);
								
				// fill editText
				String text_raw = ((TextView) view).getText().toString();
				String[] text_raw_parts = text_raw.split(">");
				String text_key = text_raw_parts[0].trim();
				String text_value = text_raw_parts[text_raw_parts.length-1].trim();
				
				//final String key_ID = text_key;
				//final String text_key1 = text_key;
								
				edit_layout_key_EditText.setText(text_key);
				edit_layout_value_EditText.setText(text_value);
			
				
				// Set listeners
				edit_layout_Cancel_Button.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						dialog_edit.dismiss();
						
					}});
				
				edit_layout_Save_Button.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {

						final String text_key = edit_layout_key_EditText.getText().toString().trim();
						final String text_value = edit_layout_value_EditText.getText().toString().trim();
						
						if(text_key.equals("") || text_value.equals("")){
						
							Toast.makeText(getApplicationContext(), "Please enter key and value . . .  ", Toast.LENGTH_SHORT).show();
							
						}
						else
						{
												
						final String table_name = Spinner_languages.getSelectedItem().toString().replace(" >>> ", "_to_");
		
						DatabaseHelper db = new DatabaseHelper(AppPreferences.this);
						db.Insert(table_name, text_key, text_value);
						db.close();
										
						dialog_edit.dismiss();
			
						Set_Spinner(Spinner_languages.getSelectedItem().toString());
						}

						
					}});
				
				
				edit_layout_Delete_Button.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						
						final String text_key = edit_layout_key_EditText.getText().toString().trim();
						final String text_value = edit_layout_value_EditText.getText().toString().trim();
						final String table_name = Spinner_languages.getSelectedItem().toString().replace(" >>> ", "_to_");			
						
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				 
							// set title
							alertDialogBuilder.setTitle("Delete . . .");
				 
							// set dialog message
							alertDialogBuilder.setMessage("Delete "+text_key+" >>> "+text_value+" ?")
								.setCancelable(false)
								.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
																			
										DatabaseHelper db = new DatabaseHelper(AppPreferences.this);
										db.Delete(table_name, text_key);
																												
										dialog_edit.dismiss();
										
										String text = text_key + " >>> "+text_value+ " deleted . . .";
										Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
										Set_Spinner(Spinner_languages.getSelectedItem().toString());
										
									}
								  })
								.setNegativeButton("No",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
									
										dialog.dismiss();
										
									}
								});
				 
								// create alert dialog
								AlertDialog alertDialog = alertDialogBuilder.create();
				 
								// show it
								alertDialog.show();
						
						
						
					}});
				
				
				
				
				
				dialog_edit.show();
			
				
			}});
    	
    }



}
