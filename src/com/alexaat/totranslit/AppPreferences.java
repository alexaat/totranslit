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
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AppPreferences extends ActionBarActivity {


GridView Language_Table;
Spinner Spinner_languages;
Button Button_New;
Button Button_Delete;
Button Button_Copy;

	

	
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
		Button_Copy = (Button)  findViewById(R.id.Button_Copy);
		
  	
  	
		String SELECTED_LANGUAGE = "";
			
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    SELECTED_LANGUAGE = extras.getString(MainActivity.SELECTED_LANGUAGE_TAG);
		}
  		
		Set_Spinner(SELECTED_LANGUAGE);
		Set_Listeners();
  	
  	}
	
  	private void Set_Spinner(String table_name){
  		
  		
  		if(table_name == null){
  		    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.simple_list_item, new ArrayList<String>());
  	        Spinner_languages.setAdapter(adapter);
  			return;
  		}
  	
  		
  		
  		 	table_name = table_name.replace(MainActivity.TO_database, MainActivity.TO_dispaly);
  		
  		 
 	       // Get only language table in format Source_To_Result
 	       List<String> tableNames = new ArrayList<String>();
 	       DatabaseHelper db = new DatabaseHelper(this);
 	       tableNames = db.getListOfTables();
 	       db.close();
 	        
          // Display in format Source > > > Result
 	        for(int i = 0; i<tableNames.size();i++){
 	        	
 	        	String s = tableNames.get(i);
 	       
 	        	
 	        	s = s.replace(MainActivity.TO_database, MainActivity.TO_dispaly);
 	        	tableNames.set(i, s);
 	        	
 	        }
 	               
 	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.simple_list_item, tableNames);
 	        
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
			

			Button_Delete.setEnabled(false);
			Button_Copy.setEnabled(false);

			
			break;
		
		case 1:
			

			Button_Delete.setEnabled(true);
			Button_Copy.setEnabled(true);

			
			break;
		
		default:
			

			Button_Delete.setEnabled(true);
			Button_Copy.setEnabled(true);

			
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

				table_name = table_name.replace(MainActivity.TO_dispaly, MainActivity.TO_database);
				
				List<String> items = new ArrayList<String>();
				Map<String,String> itemsMap = new TreeMap<String,String>();
				DatabaseHelper db = new DatabaseHelper(AppPreferences.this);
				itemsMap = db.Get(table_name);
				db.close();
				
				
				
				
				for(Map.Entry<String,String> entry : itemsMap.entrySet()) {
					String key = entry.getKey();
					String value = entry.getValue();
					items.add(key + MainActivity.TO_dispaly + value);
					
				}
				
				
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(AppPreferences.this,
						R.layout.simple_list_item,
						items);

				
				
				Language_Table.setAdapter(adapter);
					
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			
				
			}});
    	
    	Spinner_languages.setOnLongClickListener(new OnLongClickListener(){

			@Override
			public boolean onLongClick(View arg0) {
				
				
				final Dialog dialog_add_symbol = new Dialog(context);
				
				dialog_add_symbol.setContentView(R.layout.add_symbol_dialog);
					
				dialog_add_symbol.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
								
				
				String title = "Add new character to:";
				dialog_add_symbol.setTitle(title);
										
			
				
				TextView TextView_Language_Table = (TextView) dialog_add_symbol.findViewById(R.id.TextView_Language_Table);
				
				
				final EditText EditText_add_key = (EditText) dialog_add_symbol.findViewById(R.id.EditText_add_key);
								
				final EditText EditText_add_value = (EditText) dialog_add_symbol.findViewById(R.id.EditText_add_value);
				
				Button Button_add_ok = (Button) dialog_add_symbol.findViewById(R.id.Button_add_ok);
				Button Button_add_cancel = (Button) dialog_add_symbol.findViewById(R.id.Button_add_cancel);
				final CheckBox CheckBox_add_Case = (CheckBox) dialog_add_symbol.findViewById(R.id.CheckBox_add_Case);
				
				
				EditText_add_key.addTextChangedListener(new TextWatcher() {

					@Override
					public void afterTextChanged(Editable arg0) {
						
						
						String first_char_in_text = "";
						String text = arg0.toString();
						if(!text.equals("")){
						first_char_in_text = text.substring(0,1);
						}
						//Toast.makeText(context, text, 50).show();
						
						if(!text.equals("")){
							
							String texttoUpper = text.toUpperCase();
							String first_char_in_texttoUpper = texttoUpper.substring(0,1);
							
							
							if(first_char_in_text.equals(first_char_in_texttoUpper)){
							
								CheckBox_add_Case.setChecked(false);
								CheckBox_add_Case.setEnabled(false);
								
							}
							else
							{
								CheckBox_add_Case.setEnabled(true);
							}
							
							
						}
						else
						{
							CheckBox_add_Case.setEnabled(true);
						}
						
						
						
						
					}

					@Override
					public void beforeTextChanged(CharSequence arg0, int arg1,
							int arg2, int arg3) {
						
						
					}

					@Override
					public void onTextChanged(CharSequence arg0, int arg1,
							int arg2, int arg3) {
						
						
						
						
					}});
				
				
	
				
				final String current_language = Spinner_languages.getSelectedItem().toString().replace(MainActivity.TO_dispaly, MainActivity.TO_database);
				TextView_Language_Table.setText(Spinner_languages.getSelectedItem().toString());
				///////////////////////////////////////////////////
							
				Button_add_ok.setOnClickListener(new OnClickListener(){
			
					
					@Override
					public void onClick(View v) {
						

						final String new_key = EditText_add_key.getText().toString();
						final String new_value = EditText_add_value.getText().toString();

						if(new_key.equals("") || new_value.equals("")){
							
							Toast.makeText(getApplicationContext(), "Please enter key and value . . .  ", Toast.LENGTH_SHORT).show();
						}
						else
						{
							
							if(CheckBox_add_Case.isChecked()){
							
								dialog_add_symbol.dismiss();
								
				    			
				    			DatabaseHelper db = new DatabaseHelper(AppPreferences.this);
			    				db.Insert(current_language,new_key.toLowerCase(), new_value.toLowerCase());
			    				db.close();
			    				SwapInsert(current_language,new_key.toLowerCase(), new_value.toLowerCase());
			    				
				    			
				    			if(new_key.length()==1){
				    			
				    			if(new_value.length()==1){
				    			
				    				db = new DatabaseHelper(AppPreferences.this);
				    				db.Insert(current_language, new_key.toUpperCase(), new_value.toUpperCase());
				    				db.close();
				    				SwapInsert(current_language, new_key.toUpperCase(), new_value.toUpperCase());
				    				
				    			
				    		    }
				    			else if(new_value.length()>1){
				    								    			
				    				db = new DatabaseHelper(AppPreferences.this);
				    				db.Insert(current_language,
				    						new_key.toUpperCase(),
				    						new_value.toLowerCase().substring(0,1).toUpperCase()+new_value.toLowerCase().substring(1));
				    				db.close();
				    				SwapInsert(current_language,
				    						new_key.toUpperCase(),
				    						new_value.toLowerCase().substring(0,1).toUpperCase()+new_value.toLowerCase().substring(1));
				    			
				    			
				    			
				    			}
				    			
				    		}
				    		else if(new_key.length()>1){
				    			
				    			
				    			
				    			if(new_value.length()==1){
						    		   
				    			
					    			db = new DatabaseHelper(AppPreferences.this);
				    				db.Insert(current_language,
				    						new_key.toUpperCase(),
				    						new_value.toUpperCase());
				    				
				    				db.Insert(current_language,
				    						new_key.toLowerCase().substring(0,1).toUpperCase()+new_key.toLowerCase().substring(1),
				    						new_value.toUpperCase());
				    				db.close();
				    				
				    				SwapInsert(current_language,
				    						new_key.toUpperCase(),
				    						new_value.toUpperCase());
				    				SwapInsert(current_language,
				    						new_key.toLowerCase().substring(0,1).toUpperCase()+new_key.toLowerCase().substring(1),
				    						new_value.toUpperCase());
				    			
				    			
				    			
				    			}
					    			else if(new_value.length()>1){
					    				 
						    			db = new DatabaseHelper(AppPreferences.this);
					    				db.Insert(current_language,
					    						new_key.toUpperCase(),
					    						new_value.toUpperCase());
					    				
					    				db.Insert(current_language,
					    						new_key.toLowerCase().substring(0,1).toUpperCase()+new_key.toLowerCase().substring(1),
					    						new_value.toLowerCase().substring(0,1).toUpperCase()+new_value.toLowerCase().substring(1));
					    				db.close();
					    							
					    				SwapInsert(current_language,
					    						new_key.toUpperCase(),
					    						new_value.toUpperCase());
					    				SwapInsert(current_language,
					    						new_key.toLowerCase().substring(0,1).toUpperCase()+new_key.toLowerCase().substring(1),
					    						new_value.toLowerCase().substring(0,1).toUpperCase()+new_value.toLowerCase().substring(1));
					    				
					    				
					    			
					    			}
				    			}
																						
								
							}
							else  // if(!CheckBox_add_Case.isChecked()){
							{
									
								dialog_add_symbol.dismiss();
					
									DatabaseHelper db = new DatabaseHelper(AppPreferences.this);
				    				db.Insert(current_language,
				    						new_key,
				    						new_value);
				    				db.close();
				    				SwapInsert(current_language,
				    						new_key,
				    						new_value);
				    							
							}
							
							Set_Spinner(Spinner_languages.getSelectedItem().toString());
							
						}
						
					}
								
				});
				
				
				
				Button_add_cancel.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						
						dialog_add_symbol.dismiss();
					}});
				
				
				dialog_add_symbol.show();
				
				
				
				return true;
			}});
    	    	
    	Language_Table.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
			
				// custom dialog
				final Dialog dialog_edit = new Dialog(context);
				dialog_edit.setContentView(R.layout.edit_layout);
				dialog_edit.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				dialog_edit.setTitle("Edit");
				
				// Set controls
				Button edit_layout_Save_Button = (Button) dialog_edit.findViewById(R.id.edit_layout_Save_Button);
				Button edit_layout_Cancel_Button = (Button) dialog_edit.findViewById(R.id.edit_layout_Cancel_Button);
				Button edit_layout_Delete_Button = (Button) dialog_edit.findViewById(R.id.edit_layout_Delete_Button);
				final EditText edit_layout_key_EditText = (EditText)  dialog_edit.findViewById(R.id.edit_layout_key_EditText);
				final EditText edit_layout_value_EditText = (EditText)  dialog_edit.findViewById(R.id.edit_layout_value_EditText);
								
				// fill editText
				String text_raw = ((TextView) view).getText().toString();
				String[] text_raw_parts = text_raw.split(MainActivity.TO_dispaly);
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

							
						final String table_name = Spinner_languages.getSelectedItem().toString().replace(MainActivity.TO_dispaly, MainActivity.TO_database);
		
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
							
						final String table_name = Spinner_languages.getSelectedItem().toString().replace(MainActivity.TO_dispaly, MainActivity.TO_database);			
						
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				 
							// set title
							alertDialogBuilder.setTitle("Delete . . .");
				 
					
							// set dialog message
							alertDialogBuilder.setMessage("Delete "+text_key+MainActivity.TO_dispaly+text_value+" ?")
								.setCancelable(false)
								.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
																			
										DatabaseHelper db = new DatabaseHelper(AppPreferences.this);
										db.Delete(table_name, text_key);
																												
										dialog_edit.dismiss();
										
										
										String text = text_key + MainActivity.TO_dispaly+text_value+ " deleted . . .";
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
    	
    	Button_New.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				
				// custom dialog
				final Dialog dialog = new Dialog(context);
								
				 
				dialog.setContentView(R.layout.new_language_dialog);
				dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				
				dialog.setTitle("Create new table");
	 				
				TextView TextView_dialog_Source = (TextView) dialog.findViewById(R.id.TextView_dialog_Source);
				TextView_dialog_Source.setText(MainActivity.TO_dispaly);

				Button dialogButton_Cancel = (Button) dialog.findViewById(R.id.Button_dialog_cancel);
				// if Cancel button is clicked, close the custom dialog
				dialogButton_Cancel.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
	 
				
				Button Button_dialog_Ok = (Button) dialog.findViewById(R.id.Button_dialog_Ok);
			    final EditText EditText_dialog_Source = (EditText) dialog.findViewById(R.id.EditText_dialog_Source);
				final EditText EditText_dialog_Target = (EditText) dialog.findViewById(R.id.EditText_dialog_Target);
							
													
				// if OK button is clicked, close the custom dialog
				Button_dialog_Ok.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View v) {
					
						final String source_text = EditText_dialog_Source.getText().toString();
						final String target_text =  EditText_dialog_Target.getText().toString();
						
						
						
						if(source_text.contains(MainActivity.TO_database) || target_text.contains(MainActivity.TO_database))
						{
							
							
							Toast.makeText(getApplicationContext(), "table cannot contain " + MainActivity.TO_database + " expression", Toast.LENGTH_SHORT).show();
						}
						else
						{
						final String table_name = source_text+MainActivity.TO_database+target_text;
						final String table_name_mirror = target_text +MainActivity.TO_database+ source_text;
						
						String table_name_raw = source_text.trim() +MainActivity.TO_dispaly+target_text.trim();
						
						
						if(!source_text.equals("") && !target_text.equals(""))
						{		
							DatabaseHelper db = new DatabaseHelper(AppPreferences.this);
							db.CreateTable(table_name);
							db.CreateTable(table_name_mirror);
							db.close();
							dialog.dismiss();
							Set_Spinner(table_name_raw);
							
							Toast.makeText(getApplicationContext(), "new table was created", Toast.LENGTH_SHORT).show();
												
						}
						else
						{
							Toast.makeText(getApplicationContext(), "table name is invalid . . .", Toast.LENGTH_SHORT).show();
						}
						}							
						
					}});
				
				dialog.show();
				
			}});
    	
    	Button_Delete.setOnClickListener(new OnClickListener(){

    	
    		
    		
			@Override
			public void onClick(View arg0) {
				final String table_name = Spinner_languages.getSelectedItem().toString().replace(MainActivity.TO_dispaly, MainActivity.TO_database);
				
				String[] language_parts = table_name.split(MainActivity.TO_database);
				String language_raw_parts_first = language_parts[0].trim();
				String language_raw_parts_last = language_parts[language_parts.length-1].trim();
				final String table_name_mirror = language_raw_parts_last+MainActivity.TO_database+language_raw_parts_first;
											
				
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
     			// set title
				alertDialogBuilder.setTitle("Delete . . .");
				// set dialog message
				alertDialogBuilder.setMessage("Delete "+table_name.replace(MainActivity.TO_database, MainActivity.TO_dispaly)+" ?")
					.setCancelable(false)
					.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							
							DatabaseHelper db = new DatabaseHelper(AppPreferences.this);
							
							if(db.getListOfTables().size()==2){
								Toast.makeText(getApplicationContext(), "Cannot delete the last table.\nCreate another table first ...", Toast.LENGTH_LONG).show();
								db.close();
							
							}
							else{
								
							db.DeleteTable(table_name);
							db.DeleteTable(table_name_mirror);
							Set_Spinner(db.getListOfTables().size()>0 ? db.getListOfTables().get(0).replace(MainActivity.TO_database, MainActivity.TO_dispaly):null);
							db.close();
							dialog.dismiss();
							String text = table_name.replace(MainActivity.TO_database, MainActivity.TO_dispaly)+ " deleted . . .";
							Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
							text = table_name_mirror.replace(MainActivity.TO_database, MainActivity.TO_dispaly)+ " deleted . . .";
							Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
							}
																					
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
        	
    	Button_Delete.setOnLongClickListener(new OnLongClickListener(){

			@Override
			public boolean onLongClick(View arg0) {
				
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
     			// set title
				alertDialogBuilder.setTitle("Reset");
				// set dialog message
				alertDialogBuilder.setMessage("Would you like to reset initial tables?")
					.setCancelable(false)
					.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							  
							SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
							SharedPreferences.Editor editor = sharedpreferences.edit();
							editor.putBoolean(MainActivity.SET_INITIAL_TABLES_KEY, true);
							editor.commit();
							Toast.makeText(getApplicationContext(), "Restart application to finish reset", Toast.LENGTH_SHORT).show();
							
							
																					
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
				
				
				
				
				return true;
			}});
    	
    	Button_Copy.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				
				// custom dialog
				final Dialog dialog_copy = new Dialog(context);
				
							
				dialog_copy.setContentView(R.layout.copy_layout);
				dialog_copy.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				dialog_copy.setTitle("Copy Table");
							
				
								
				final Spinner Spinner_copy_layout = (Spinner) dialog_copy.findViewById(R.id.Spinner_copy_layout);
				final EditText EditText_copy_layout_source_language = (EditText) dialog_copy.findViewById(R.id.EditText_copy_layout_source_language);
				final EditText EditText_copy_layout_target_language = (EditText) dialog_copy.findViewById(R.id.EditText_copy_layout_target_language);
				TextView TextView_copy_layout_To = (TextView) dialog_copy.findViewById(R.id.TextView_copy_layout_To);
				TextView_copy_layout_To.setText(MainActivity.TO_dispaly);
				Button Button_copy_layout_Ok = (Button) dialog_copy.findViewById(R.id.Button_copy_layout_Ok);
				Button Button_copy_layout_Cancel = (Button) dialog_copy.findViewById(R.id.Button_copy_layout_Cancel);
							
				/// Set Spinner
		        ArrayList<String> languages_array = new ArrayList<String>();
		        
		        /// Get list of tables in database

		        List<String> Table_Names_New = new ArrayList<String>();
		        DatabaseHelper db = new DatabaseHelper(AppPreferences.this);
		        Table_Names_New = db.getListOfTables();
		        db.close();
		        
		       
		        ///////////////////////////////
		        
		        // Display in format Source > > > Result
		        for(int i = 0; i<Table_Names_New.size();i++){
		        	
		        	
		        	String Table_Name_Parts[] = Table_Names_New.get(i).split(MainActivity.TO_database);
		        	String Table_Name_To_Display = Table_Name_Parts[0]+MainActivity.TO_dispaly+ Table_Name_Parts[Table_Name_Parts.length-1];
		        	languages_array.add(Table_Name_To_Display);
		        	
		        }
		         
		    

			   
		        
		        Spinner_copy_layout.setAdapter(new ArrayAdapter<String>(context, R.layout.simple_list_item, languages_array));
				/// End of set spinner
		        	
       
		        
		        Button_copy_layout_Ok.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						
						String _copy_layout_source_language = EditText_copy_layout_source_language.getText().toString().trim();
						String _copy_layout_target_language = EditText_copy_layout_target_language.getText().toString().trim();
						
							
						if(_copy_layout_source_language.toLowerCase().contains(MainActivity.TO_database))
						{
						
							Toast.makeText(getApplicationContext(), "table name cannot contain " + MainActivity.TO_database, Toast.LENGTH_SHORT).show();
							
												
						}
						else
						{
						
						
							
						    String table_name_copy = _copy_layout_source_language+MainActivity.TO_database+ _copy_layout_target_language;
							String new_table_name =  table_name_copy.replace(MainActivity.TO_database, MainActivity.TO_dispaly);
							String table_name_raw = Spinner_copy_layout.getSelectedItem().toString();
							String spinner_language = table_name_raw.replace(MainActivity.TO_dispaly, MainActivity.TO_database);
														
							String sql = "INSERT INTO [" + table_name_copy +"] SELECT * FROM [" + spinner_language+"]";
							
							DatabaseHelper db = new DatabaseHelper(AppPreferences.this);
						
							if(!db.TableExists(table_name_copy)){
								db.CreateTable(table_name_copy);
								db.getWritableDatabase().execSQL(sql);
								 Toast.makeText(getApplicationContext(), "table is copied . . . ", Toast.LENGTH_SHORT).show();
							     Set_Spinner(new_table_name);
																
							}
							else{
								Toast.makeText(getApplicationContext(), "table "+ table_name_copy+ " exists . . . ", Toast.LENGTH_SHORT).show();	
							}
											
							
							
							
							 String table_name_copy_copy =  _copy_layout_target_language +MainActivity.TO_database + _copy_layout_source_language;
							 String spinner_language_copy = "";
							 String[] spinner_language_parts =  spinner_language.split(MainActivity.TO_database);
							 spinner_language_copy = spinner_language_parts[spinner_language_parts.length-1]
									 				+ MainActivity.TO_database
									 				+spinner_language_parts[0];
										 
							 sql = "INSERT INTO [" + table_name_copy_copy +"] SELECT * FROM [" + spinner_language_copy+"]";
							
							 if(!db.TableExists(table_name_copy_copy)){
						 		 db.CreateTable(table_name_copy_copy);
						 		 db.getWritableDatabase().execSQL(sql);
						 		 Toast.makeText(getApplicationContext(), "table is copied . . . ", Toast.LENGTH_SHORT).show();
							     Set_Spinner(table_name_copy_copy.replace(MainActivity.TO_database, MainActivity.TO_dispaly));
							 }else{
								 Toast.makeText(getApplicationContext(), "table "+ table_name_copy_copy+ " exists . . . ", Toast.LENGTH_SHORT).show();	
							 }
							
							
							dialog_copy.dismiss();
							db.close();	
							
						}
						
						
						
					}});
		        
		        Button_copy_layout_Cancel.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						
						dialog_copy.dismiss();
					}});
		        
		        
		       
		        
			
				dialog_copy.show();
				
				
			}});
    	
    }
    
    private void SwapInsert(String language, String key, String value){
    	
    	String[] language_parts = language.split( MainActivity.TO_database);
    	String part1 = language_parts[0];
    	String part2 = language_parts[language_parts.length-1];
    	String new_language = part2+ MainActivity.TO_database+part1;
    	
    	DatabaseHelper db = new DatabaseHelper(this);
    	if(db.TableExists(new_language)){
    		db.Insert(new_language, value, key);    		
    	}
    	db.close();
    	
    	
    }
    
    @Override
	protected void onResume(){
		Toast.makeText(getApplicationContext(), "To add symbol press and hold languages title", Toast.LENGTH_SHORT).show();	
    	
		super.onResume();
	}



}
