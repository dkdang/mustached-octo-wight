package com.dk.cardashboard.adapter;

import java.text.SimpleDateFormat;
import java.util.List;

import com.dk.cardashboard.R;
import com.dk.cardashboard.objects.Car;
import com.dk.cardashboard.objects.FuelEvent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FuelEventListAdapter extends ArrayAdapter<FuelEvent> {
	
	private List<FuelEvent> items;
	
	public FuelEventListAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}
	
	public FuelEventListAdapter(Context context, int resource, List<FuelEvent> items) {
		super(context, resource, items);
		
		this.items = items;
	}
	
	static class FuelEventHolder {
		TextView Date;
		TextView GasPrice;
		TextView GasAmount;
		TextView Odometer;
		TextView CarId;
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		final FuelEventHolder holder;
		
		if(view == null) {
			LayoutInflater vi = LayoutInflater.from(getContext());
			
			view = vi.inflate(R.layout.activity_fuel_event_list_item, null);
			
			holder = new FuelEventHolder();
			holder.Date = (TextView) view.findViewById(R.id.text_date);
			holder.GasPrice = (TextView) view.findViewById(R.id.text_gas_price);
			holder.GasAmount = (TextView) view.findViewById(R.id.text_gas_amount);
			holder.Odometer = (TextView) view.findViewById(R.id.text_odometer);
			holder.CarId = (TextView) view.findViewById(R.id.text_carid);
			
			view.setTag(holder);
		} else 
			holder = (FuelEventHolder) view.getTag();
		
		FuelEvent fuelevent = items.get(position);
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		
		holder.Date.setText(sdf.format(fuelevent.getDate()));
		holder.GasPrice.setText(fuelevent.getGasprice().toString());
		holder.GasAmount.setText(fuelevent.getGasAmount().toString());
		holder.Odometer.setText(fuelevent.getOdometer().toString());
		holder.CarId.setText(fuelevent.getCarId().toString());
		
		return view;
	}
	
}
