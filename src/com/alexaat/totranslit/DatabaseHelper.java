package com.alexaat.totranslit;

import java.util.HashMap;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	   private static final String DATABASE_NAME = "com.alexaat.totranslit.database.languages";
       private static final int DATABASE_VERSION = 1;
       public static final String RUSSIAN_TO_ENGLISH = "russian_to_english";
       public static final String ENGLISH_TO_RUSSIAN = "english_to_russian";
       private static final String COLUMN_KEY = "key";
       private static final String COLUMN_VALUE = "value";
       private static final String CREATE_RUSSIAN_TO_ENGLISH = "create table " +
               RUSSIAN_TO_ENGLISH + "( " + COLUMN_KEY + " text, " +
    		   COLUMN_VALUE
               + " text);";
       
     private static final String CREATE_ENGLISH_TO_RUSSIAN = "create table " + 
               ENGLISH_TO_RUSSIAN + "( " + COLUMN_KEY + " text, " +
               COLUMN_VALUE + " text);";
	
	 public DatabaseHelper(Context context) {
          super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

    	@Override
	 public void onCreate(SQLiteDatabase db) {
		
		 db.execSQL(CREATE_RUSSIAN_TO_ENGLISH);
		 db.execSQL(CREATE_ENGLISH_TO_RUSSIAN);
	 }

	 @Override
	 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 db.execSQL("DROP TABLE IF EXISTS " + RUSSIAN_TO_ENGLISH);
		 db.execSQL("DROP TABLE IF EXISTS " + ENGLISH_TO_RUSSIAN);
         onCreate(db);
	}
	
	 public void Insert(String table, String key, String value){
		 
		 key = key.trim();
		 value = value.trim();
		 
		 boolean unique = true;
		 
		 String val = GetValue(table, key);
		 if(val!=null){
			 if(val.equals(value)){
				 unique = false;
			 }
		 }
          		 
		 if(unique){
		 ContentValues insertValues = new ContentValues();
		 insertValues.put(COLUMN_KEY, key);
		 insertValues.put(COLUMN_VALUE, value);
		 this.getWritableDatabase().insert(table, null, insertValues);
		
		 
		 }
	 }
	 
	 public Map<String,String> Get(String table){
		 Map<String,String> values = new HashMap<String,String>();
		 
		 Cursor c = this.getReadableDatabase().query(table, null, null, null, null, null, null);
		 c = this.getReadableDatabase().rawQuery("select * from "+ table, null);
		 
		 if(c.moveToFirst()){
			 do{
				 String key = c.getString(c.getColumnIndex(COLUMN_KEY));
				 String value = c.getString(c.getColumnIndex(COLUMN_VALUE));
				 values.put(key, value);				 
			 }while(c.moveToNext());
		 }		 
		 
		 c.close();
		 return values;
	 }
	 
	 public String GetValue(String table, String key){
		 
		 Cursor c = this.getReadableDatabase().query(table, null, null, null, null, null, null);
		 if(c.moveToFirst()){
			 do{
				 String _key = c.getString(c.getColumnIndex(COLUMN_KEY));
				 if(_key.equals(key)){
					 String value = c.getString(c.getColumnIndex(COLUMN_VALUE));
					 c.close();
					 return value;
				 }										 
			 }while(c.moveToNext());
		 }		
		 c.close();
		 return null;
		 
	 }
	 
}
