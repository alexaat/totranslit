package com.alexaat.totranslit;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.Toolbar;
import android.text.ClipboardManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;





public class MainActivity extends  ActionBarActivity {

	
	EditText EditText_Source;
	EditText EditText_Result;
	ListPopupWindow listPopupWindow;
	ListPopupWindow listPopupWindow2;
	ListPopupWindow listPopupWindowOverflow;
	TextView tv_language1;
	TextView tv_language2;

	
	String LANG1 = "com.alexaat.tottanslit.lang1_setting";
	String LANG2 = "com.alexaat.tottanslit.lang2_setting";
	
	
	SharedPreferences sharedpreferences;
	public static final String MyPREFERENCES = "com.alexaat.totranslit.languages_settings" ;
	
	DatabaseHelper db;
	
	Map<String,String> Language;
	
	private ClipboardManager myClipboard;
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        myClipboard=(ClipboardManager)getSystemService(CLIPBOARD_SERVICE); 
        
        EditText_Source = (EditText) findViewById(R.id.EditText_dialog_Source);
        EditText_Result = (EditText) findViewById(R.id.EditText_Result);
               
        SetInitialTables();
       
        SetPopMenu();
       
        SetListeners();
        
      
       
       
        
        
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
  
    private void SetPopMenu(){
	   
    	
    	// 1. Get tables
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
    	
    	final Map<String,String> tablesMap = new HashMap<String,String>();
    	for(String t:tables){
    		
    		String[] tables_parts = t.split("_to_");
    		tablesMap.put(tables_parts[0], tables_parts[1]);
    		    		
    	}
    	
    	
    	
        // 2. Set PopUp menus
        android.support.v7.app.ActionBar mActionBar =this.getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
               
       // mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#E5E4E2")));
        mActionBar.setBackgroundDrawable(new ColorDrawable(getResources()
       	.getColor(R.color.platinum)));
        
        
        mActionBar.setCustomView(R.layout.abs_layout);
        View view = this.getSupportActionBar().getCustomView();
            
        final TextView tv1 = (TextView) view.findViewById(R.id.lang1);
        tv1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				listPopupWindow.show();				
							
			}});
        
        
        final TextView tv2 = (TextView) view.findViewById(R.id.lang2);
        tv2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				listPopupWindow2.show();	
			}});
        
        tv_language1 = tv1;
        tv_language2 = tv2;
        
       
        
        
        mActionBar.setDisplayShowCustomEnabled(true);
        Toolbar parent =(Toolbar) view.getParent();
        parent.setContentInsetsAbsolute(0,0);
        
        List<String> keySet = new ArrayList<String>(tablesMap.keySet());
       
        
        
        listPopupWindow = new ListPopupWindow(this);
        listPopupWindow.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, keySet));
        listPopupWindow.setAnchorView(tv1);
        listPopupWindow.setModal(true);
        
        listPopupWindow.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				String value =  (String) parent.getItemAtPosition(position);
				tv1.setText(value);
				listPopupWindow.dismiss();
				
				tv2.setText(tablesMap.get(value));
			}});
         	
    	
        
        listPopupWindow2 = new ListPopupWindow(this);
        listPopupWindow2.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, keySet));
        listPopupWindow2.setAnchorView(tv2);
        listPopupWindow2.setModal(true);
        
        listPopupWindow2.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String value =  (String) parent.getItemAtPosition(position);
				tv2.setText(value);
				listPopupWindow2.dismiss();
				tv1.setText(tablesMap.get(value));
			}});
      
        
        
        //2.1 SetPopupMenu for overflow
        final ImageButton imageButtonMenu = (ImageButton) view.findViewById(R.id.imageButtonMenu);
        
        imageButtonMenu.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				listPopupWindowOverflow.show();				
			}});
        
        listPopupWindowOverflow = new ListPopupWindow(this);
        
        List<OverflowHolder> list = new  ArrayList<OverflowHolder>();
        OverflowHolder item = new OverflowHolder();
        item.setId(R.drawable.ic_menu_copy);
        item.setText("Copy");
        list.add(item);
        
        item = new OverflowHolder();
        item.setId(R.drawable.ic_menu_paste);
        item.setText("Paste");
        list.add(item);
        
        item = new OverflowHolder();
        item.setId(R.drawable.ic_menu_delete);
        item.setText("Delete");
        list.add(item);
        
        item = new OverflowHolder();
        item.setId(R.drawable.ic_menu_share);
        item.setText("Share");
        list.add(item);
        
        item = new OverflowHolder();
        item.setId(R.drawable.ic_menu_preferences);
        item.setText("Preferences");
        list.add(item);
        
        item = new OverflowHolder();
        item.setId(R.drawable.ic_menu_help);
        item.setText("Help");
        list.add(item);
        
        
        OverflowMenuAdapter  overflowMenuAdapter = new OverflowMenuAdapter(getApplicationContext(),R.layout.overflow_item_layout,list);
        listPopupWindowOverflow.setAdapter(overflowMenuAdapter);
        listPopupWindowOverflow.setAnchorView(imageButtonMenu);
        listPopupWindowOverflow.setModal(true);
     
        //2.2 Set overflow menu width //////////////////////////////////////////////////////
        int maxWidth = 100;
        for(int i = 0; i<overflowMenuAdapter.getCount(); i++){
        	 View menuView = overflowMenuAdapter.getView(i, null, null);
        	menuView.measure(0,0);
        	int measureWidth = menuView.getMeasuredWidth();
        	if(measureWidth>maxWidth){
        		maxWidth = measureWidth;
        	}
        }
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        listPopupWindowOverflow.setContentWidth((int)(maxWidth+4*metrics.scaledDensity));
        /////////////////////////////////////////////////////////////////////////////////////
        
        listPopupWindowOverflow.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
		
				switch(position){
				case 0:
				
					String result = EditText_Result.getText().toString();
					myClipboard.setText(result);
					Toast.makeText(getApplicationContext(), "Text has been copied into clipboard", Toast.LENGTH_SHORT).show();
										
					break;
					
				case 1:

					String text = (String) myClipboard.getText();	
					EditText_Source.setText(text);	
									
					break;
					
				case 2:
					
					EditText_Source.setText("");
					
					break;
					
				case 3:
					
					String SMS_text = EditText_Result.getText().toString();
					
					
					if(!SMS_text.equals(""))
					{
					try {
						 
						Intent sendIntent = new Intent();
						sendIntent.setAction(Intent.ACTION_SEND);
						sendIntent.putExtra(Intent.EXTRA_TEXT, SMS_text);
						sendIntent.setType("text/plain");
						startActivity(sendIntent);
						

					} catch (Exception e) {
						Toast.makeText(getApplicationContext(),
							"Sending failed, please try again later!",
							Toast.LENGTH_LONG).show();
						e.printStackTrace();
					}
					
					}
					else
					{
						Toast.makeText(getApplicationContext(), "No text found . . .", Toast.LENGTH_SHORT).show();
					}
								
					
					break;
					
				case 4:
					
					Intent intendPrefs = new Intent(MainActivity.this, Preferences.class);
					startActivity(intendPrefs);	
					
					
					break;
					
				case 5:
					
					Intent intendHelp = new Intent(MainActivity.this, Help.class);
					startActivity(intendHelp);
					
					
					break;
							
				}
				
				
				
				
				listPopupWindowOverflow.dismiss();
				
			}});
        
        
        
        //3. Set SharedPreferences 
        String lang1_def = "";
        for (String s : tablesMap.keySet()) {
            lang1_def = s;
            break;
        }
        
        
      	 sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
      	 String lang1 =  sharedpreferences.getString(LANG1, lang1_def);
   	     String lang2 =  sharedpreferences.getString(LANG2, tablesMap.get(lang1));
   	
   	     tv1.setText(lang1);
   	     tv2.setText(lang2);
   	 
   	     
   	     //4.Set Backgrounds and swap listener
   	     imageButtonMenu.setBackgroundResource(R.drawable.lable_bg);   
   	     ImageButton imageButtonSwap = (ImageButton) view.findViewById(R.id.imageButtonSwap);
   	     imageButtonSwap.setBackgroundResource(R.drawable.lable_bg);
   	     imageButtonSwap.setOnClickListener(new OnClickListener(){

   	    	 @Override
   	    	 public void onClick(View v) {
   	    				String lang1 = (String) tv1.getText();
   	    				String lang2 = (String) tv2.getText();
   	    				tv1.setText(lang2);
   	    				tv2.setText(lang1);
   	    				Language = SetLanguageMap();
   	    				EditText_Result.setText(Translit(EditText_Source.getText().toString(),Language));
   	    				   	    				
   	    	 }});
   	     
   	   
   	     
   	     
   	     
   }
    
    @Override
  	protected void onPause() {
        	
   	 sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
   	 SharedPreferences.Editor editor = sharedpreferences.edit();
   	 editor.putString(LANG1, (String) tv_language1.getText());
     editor.putString(LANG2, (String) tv_language2.getText());
     editor.commit();
     
    	super.onPause();
    }
    
    private void SetListeners(){
    	   	
    	
    	EditText_Source.addTextChangedListener(new TextWatcher(){

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				EditText_Result.setText(Translit(s.toString(), Language));
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}});
    	   	
    	
    	
    }
    
    private Map<String,String> SetLanguageMap(){
    	  
    	Map<String,String> LanguageMap = new HashMap<String,String>();
    	db = new DatabaseHelper(getApplicationContext());
    	String source = tv_language1.getText().toString();
    	String target = tv_language2.getText().toString();
    	String table = 	source + "_to_" +	target;
    	LanguageMap = db.Get(table);
    	db.close();
    	
    	// sort language map
    	LanguageMap =  SortMap(LanguageMap);
    	   	
    	return LanguageMap;
    }
    
    private String Translit(String source, Map<String,String> Language){
    	
    	
    	if(Language == null){
    		Language = SetLanguageMap();
    	}
    	if(Language.size()==0){
    		Language = SetLanguageMap();
    	}
    	
    	
    	 for (Map.Entry<String,String> entry : Language.entrySet()) {
    	        String value = entry.getValue();
    	        String key = entry.getKey();
    	        source = source.replace(key, value);
    	        
    	        
    	   }
    	
    	
    	
    	//result = source.replace("bo", "б0");
    	
    	
    	
    	
    	
    	return source;
    }

    private Map<String,String> SortMap(Map<String,String> map){
    	
    	if(map == null){
    		return null;
    	}
  	
    	
    	Map<String, String> treeMap = new TreeMap<String, String>(
    		    new Comparator<String>() {
    		        @Override
    		        public int compare(String s1, String s2) {
    		            if (s1.length() > s2.length()) {
    		                return -1;
    		            } else if (s1.length() < s2.length()) {
    		                return 1;
    		            } else {
    		                return s1.compareTo(s2);
    		            }
    		        }
    		});
    	
    	treeMap.putAll(map);
    	

    	
    	return treeMap;
    }
        
    private void PrintMap(Map<String,String> map){
    	
    	Log.d("TAG", "Printing map");
    	for (Map.Entry<String, String> entry : map.entrySet()) {
    	    String key = entry.getKey();
    	    Object value = entry.getValue();
    	    Log.d("TAG", "key = " + key + "; value = " + value);
    	}
    }
    	    
	@Override
	protected void onResume(){
		
		Language = SetLanguageMap();
		super.onResume();
	}
}
