package com.dk.cardashboard.activity;

import java.util.List;

import com.dk.cardashboard.R;
import com.dk.cardashboard.adapter.CarListAdapter;
import com.dk.cardashboard.objects.Car;
import com.verivo.akula.persistence.AKPersistenceManager;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

public class CarListActivity extends Activity{
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_car_list);
		
		new GetCars().execute();
	}
	
	private class GetCars extends AsyncTask<Void, Void, List<Car>> {

		@Override
		protected List<Car> doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			AKPersistenceManager PM = AKPersistenceManager.getInstance();
			
			List<Car> Cars = PM.getObjects(Car.class);
			
			return Cars;
		}
		
		@Override
		protected void onPostExecute(List<Car> Cars) {
			ListView listview_cars = (ListView) findViewById(R.id.listview_cars);
			
			CarListAdapter adapter;
			
			adapter = new CarListAdapter(getBaseContext(), R.layout.activity_car_list_item, Cars);
			
			listview_cars.setAdapter(adapter);
		}
		
	}

}
