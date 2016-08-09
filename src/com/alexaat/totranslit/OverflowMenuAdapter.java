package com.alexaat.totranslit;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class OverflowMenuAdapter extends ArrayAdapter<OverflowHolder>{

	  Context context;
	  int resource;
	  List<OverflowHolder> objects;
	  
	  
	public OverflowMenuAdapter(Context context, int resource, List<OverflowHolder> objects) {
		super(context, resource, objects);
		this.context = context;
		this.resource = resource;
		this.objects = objects;
		
	}

	 

	    @Override
	    public int getCount() {
	   
	        return objects.size();
	    }
	 

	    @Override
		public OverflowHolder getItem(int position) {
			
	    	return objects.get(position);
	    	
		}


		@Override
	    public long getItemId(int position) {
	           return position;
	    }

	   

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			if(convertView == null){
				LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(resource, parent, false);
			}
			
			TextView textViewOverflowItem = (TextView) convertView.findViewById(R.id.textViewOverflowItem);
			ImageView imageViewOverflowItem = (ImageView) convertView.findViewById(R.id.imageViewOverflowItem);
			
		
			textViewOverflowItem.setText(getItem(position).getText());			
			imageViewOverflowItem.setImageResource(getItem(position).getId());
			
		
	  

		return convertView;
		
		
		
		
		}
	    
	  
	
}

