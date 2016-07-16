package com.alexaat.totranslit;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
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
	
	DatabaseHelper db;
	
	
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
       
       
        SetInitialTables();
        SetSpinner();    
        
        
    } 

    private void SetInitialTables(){
    db = new DatabaseHelper(getApplicationContext());
    Map<String, String> map = new  HashMap<String, String>();
    map = db.Get(DatabaseHelper.ENGLISH_TO_RUSSIAN);
    if(map.size()==0){
       
    map = new  HashMap<String, String>();
    map.put("A", "А");
	map.put("B", "Б");
	map.put("V", "В");
	map.put("G", "Г");
	map.put("D", "Д");
	map.put("E", "Е");
	map.put("JO", "Ё");
	map.put("Jo", "Ё");
	map.put("YO", "Ё");
	map.put("Yo", "Ё");
	map.put("ZH", "Ж");
	map.put("Zh", "Ж");
	map.put("Z", "З");
	map.put("I", "И");
	map.put("J", "Й");
	map.put("K", "К");
	map.put("L", "Л");
	map.put("M", "М");
	map.put("N", "Н");
	map.put("O", "О");
	map.put("P", "П");
	map.put("R", "Р");
	map.put("S", "С");
	map.put("T", "Т");
	map.put("U", "У");
	map.put("F", "Ф");
	map.put("H", "Х");
	map.put("X", "Х");
	map.put("C", "Ц");
	map.put("CH", "Ч");
	map.put("Ch", "Ч");
	map.put("SH", "Ш");
	map.put("Sh", "Ш");
	map.put("SHH", "Щ");
	map.put("Shh", "Щ");
	map.put("W", "Щ");
	map.put("Y", "Ы");
	map.put("JE", "Э");
	map.put("Je", "Э");
	map.put("JU", "Ю");
	map.put("Ju", "Ю");
	map.put("YU", "Ю");
	map.put("Yu", "Ю");
	map.put("JA", "Я");
	map.put("Ja", "Я");
	map.put("YA", "Я");
	map.put("Ya", "Я");
	
	
	map.put("a", "а");
	map.put("b", "б");
	map.put("v", "в");
	map.put("g", "г");
	map.put("d", "д");
	map.put("e", "е");
	map.put("jo", "ё");
	map.put("yo", "ё");
	map.put("zh", "ж");
	map.put("z", "з");
	map.put("i", "и");
	map.put("j", "й");
	map.put("k", "к");
	map.put("l", "л");
	map.put("m", "м");
	map.put("n", "н");
	map.put("o", "о");
	map.put("p", "п");
	map.put("r", "р");
	map.put("s", "с");
	map.put("t", "т");
	map.put("u", "у");
	map.put("f", "ф");
	map.put("h", "х");
	map.put("x", "х");
	map.put("c", "ц");
	map.put("ch", "ч");
	map.put("sh", "ш");
	map.put("shh", "щ");
	map.put("w", "щ");
	map.put("#", "ъ");
	map.put("y", "ы");
	map.put("'", "ь");
	map.put("je", "э");
	map.put("ju", "ю");
  	map.put("yu", "ю");
   	map.put("ja", "я");
   	map.put("ya", "я");
   	map.put("q", "я");
    
    for (Map.Entry<String, String> entry : map.entrySet()) {
    	 String key = entry.getKey();
         String value = entry.getValue();
         db.Insert(DatabaseHelper.ENGLISH_TO_RUSSIAN, key, value);
    }
    }
    
    map = new  HashMap<String, String>();
    map = db.Get(DatabaseHelper.RUSSIAN_TO_ENGLISH);
    if(map.size()==0){ 
    map = new  HashMap<String, String>();
	
	map.put("А", "A");
	map.put("Б", "B");
	map.put("В", "V");
	map.put("Г", "G");
	map.put("Д", "D");
	map.put("Е", "E");
	map.put("Ё", "Jo");
	map.put("Ж", "Zh");
	map.put("З", "Z");
	map.put("И", "I");
	map.put("Й", "J");
	map.put("K", "K");
	map.put("Л", "L");
	map.put("М", "M");
	map.put("Н", "N");
	map.put("О", "O");
	map.put("П", "P");
	map.put("Р", "R");
	map.put("С", "S");
	map.put("Т", "T");
	map.put("У", "U");
	map.put("Ф", "F");
	map.put("Х", "H");
	map.put("Ц", "C");
	map.put("Ч", "Ch");
	map.put("Ш", "Sh");
	map.put("Щ", "Shh");
	map.put("Ы", "Y");
	map.put("Э", "Je");
	map.put("Ю", "Ju");
	map.put("Я", "Ja");
	
	
	map.put("а", "a");
	map.put("б", "b");
	map.put("в", "v");
	map.put("г", "g");
	map.put("д", "d");
	map.put("e", "e");
	map.put("ё", "jo");
	map.put("ж", "zh");
	map.put("з", "z");
	map.put("и", "i");
	map.put("й", "j");
	map.put("к", "k");
	map.put("л", "l");
	map.put("м", "m");
	map.put("н", "n");
	map.put("о", "o");
	map.put("п", "p");
	map.put("р", "r");
	map.put("с", "s");
	map.put("т", "t");
	map.put("у", "u");
	map.put("ф", "f");
	map.put("х", "h");
	map.put("ц", "c");
	map.put("ч", "ch");
	map.put("ш", "sh");
	map.put("щ", "shh");
	map.put("ъ", "#");
	map.put("ы", "y");
	map.put("ь", "'");
	map.put("э", "je");
	map.put("ю", "ju");
	map.put("я", "ja");
    
    for (Map.Entry<String, String> entry : map.entrySet()) {
   	    String key = entry.getKey();
        String value = entry.getValue();
        db.Insert(DatabaseHelper.RUSSIAN_TO_ENGLISH, key, value);
    }
        
    }
    db.close();
    }
  
    private void SetSpinner(){
    	
    	List<String> tables = new ArrayList<String>();
    	db = new DatabaseHelper(getApplicationContext());
    	tables = db.getListOfTables();
    	db.close();
    	
    	
    	List<String> tablesTemp = new ArrayList<String>();
    	for(String t:tables){
    		t = t.toLowerCase(Locale.UK);
    		if(t.contains("_to_")){
    			tablesTemp.add(t);
    		}
    		
    	}
    	tables = tablesTemp;
    	
    	
    	 ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tables);
         
         // Drop down layout style - list view with radio button
         dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         
         // attaching data adapter to spinner
         spinner_language.setAdapter(dataAdapter);
    	
    	
    	
    	
    }
    
}
