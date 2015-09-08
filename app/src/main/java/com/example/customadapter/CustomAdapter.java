package com.example.customadapter;

import java.util.ArrayList;

import com.example.chinesechairmen.R;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

	private Context context;
	private Resources res;
	
	private ArrayList<Leader> leaders;
	private String names[];
	/**The only way of displaying images stored in drawable is via "TypedArray" method*/
	private TypedArray images;
	private String comments[];
	
	//---constructor---
	public CustomAdapter(Context context) {
		this.context = context;
		res = context.getResources();
		
		leaders = new ArrayList<Leader>();
		names = res.getStringArray(R.array.names);
		images = res.obtainTypedArray(R.array.images); 
		comments = res.getStringArray(R.array.comments);
		for(int i=0; i<5; i++) {
			leaders.add(new Leader(names[i], images.getResourceId(i, -1), comments[i]) );
			/**"-1" is defValue - Value to return if the attribute is not defined or not a 
			resource. Possibly it will set the imageview blank if it has to use the default -1*/
			
			/**The following codes won't work
			images[i] = imagesArray.getResourceId(i, -1);
			leaders.add(new Leader(names[i], images[i], comments[i]) );*/
		}
	}
	
	
	@Override
	public int getCount() {
		if(leaders != null)
			return leaders.size();
		else
			return 0;
	}

	@Override
	public Object getItem(int position) {
		return leaders.get(position);
	}

	
	@Override
	public long getItemId(int position) {
		return position;
	}

	static class ViewHolder {
		TextView tv1;
		ImageView img;
		TextView tv2;
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if(convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			
			/**similar to newView() in custom cursor adapter*/
			convertView = inflater.inflate(R.layout.custom_view, parent, false);
			viewHolder = new ViewHolder();
			
			/**Similar to bindView() in custom cursor adapter, the only difference
			 * is that: in cursor adapter we query DB to get data; here we traverse
			 * an array list to get data;*/
			viewHolder.tv1 = (TextView) convertView.findViewById(R.id.textView1);
			viewHolder.img = (ImageView) convertView.findViewById(R.id.imageView1);
			viewHolder.tv2 = (TextView) convertView.findViewById(R.id.textView2);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		Leader leader = leaders.get(position);
		viewHolder.tv1.setText(leader.getName());
		viewHolder.img.setImageResource(leader.getImage());
		viewHolder.tv2.setText(leader.getComment());
			
		return convertView;
	}
	
	
	
	/**Old method without view holder
	 * Compare to custom cursor adapter, getView() kinda combines newView() 
	 * and bindView()*/
	/*@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		
		*//**similar to newView() in custom cursor adapter*//*
		convertView = inflater.inflate(R.layout.custom_view, parent, false);
		
		
		*//**Similar to bindView() in custom cursor adapter, the only difference
		 * is that: in cursor adapter we query DB to get data; here we traverse
		 * an array list to get data;*//*
		Leader leader = leaders.get(position);
		
		TextView tx1 = (TextView) convertView.findViewById(R.id.textView1);
		tx1.setText(leader.getName() );
		
		ImageView img = (ImageView) convertView.findViewById(R.id.imageView1);
		img.setImageResource(leader.getImage() );
		
		TextView tx2 = (TextView) convertView.findViewById(R.id.textView2);
		tx2.setText(leader.getComment() );
		
		return convertView;
	}*/

}
