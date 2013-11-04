package com.dk.cardashboard.activity;

import java.util.List;

import com.dk.cardashboard.R;
import com.dk.cardashboard.adapter.CarListAdapter;
import com.dk.cardashboard.adapter.FuelEventListAdapter;
import com.dk.cardashboard.objects.Car;
import com.dk.cardashboard.objects.FuelEvent;
import com.verivo.akula.persistence.AKPersistenceManager;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

public class FuelEventListActivity extends Activity{
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_fuel_event_list);
		
		new GetFuelEvent().execute();
	}
	
	private class GetFuelEvent extends AsyncTask<Void, Void, List<FuelEvent>> {

		@Override
		protected List<FuelEvent> doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			AKPersistenceManager PM = AKPersistenceManager.getInstance();
			
			List<FuelEvent> FuelEvents = PM.getObjects(FuelEvent.class);
			
			return FuelEvents;
		}
		
		@Override
		protected void onPostExecute(List<FuelEvent> FuelEvents) {
			ListView listview_fuel_events = (ListView) findViewById(R.id.listview_fuel_event);
			
			FuelEventListAdapter adapter;
			
			adapter = new FuelEventListAdapter(getBaseContext(), R.layout.activity_fuel_event_list_item, FuelEvents);
			
			listview_fuel_events.setAdapter(adapter);
		}
		
	}

}
