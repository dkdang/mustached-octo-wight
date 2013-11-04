package com.dk.cardashboard.adapter;

import java.util.List;

import com.dk.cardashboard.R;
import com.dk.cardashboard.objects.Car;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CarListAdapter extends ArrayAdapter<Car> {
	
	private List<Car> items;
	
	public CarListAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}
	
	public CarListAdapter(Context context, int resource, List<Car> items) {
		super(context, resource, items);
		
		this.items = items;
	}
	
	static class CarHolder {
		TextView Year;
		TextView Make;
		TextView Model;
		TextView Id;
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		final CarHolder holder;
		
		if(view == null) {
			LayoutInflater vi = LayoutInflater.from(getContext());
			
			view = vi.inflate(R.layout.activity_car_list_item, null);
			
			holder = new CarHolder();
			holder.Year = (TextView) view.findViewById(R.id.text_year);
			holder.Make = (TextView) view.findViewById(R.id.text_make);
			holder.Model = (TextView) view.findViewById(R.id.text_model);
			holder.Id = (TextView) view.findViewById(R.id.text_id);
			
			view.setTag(holder);
		} else 
			holder = (CarHolder) view.getTag();
		
		Car car = items.get(position);
		
		holder.Year.setText(car.getYear());
		holder.Make.setText(car.getMake());
		holder.Model.setText(car.getModel());
		holder.Id.setText(car.getId());
		
		return view;
	}
	
}
