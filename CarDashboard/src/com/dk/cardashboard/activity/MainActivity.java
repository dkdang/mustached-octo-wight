package com.dk.cardashboard.activity;

import java.util.List;

import com.dk.cardashboard.R;
import com.dk.cardashboard.objects.Car;
import com.verivo.akula.persistence.AKPersistenceManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        new GetData().execute();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	switch(item.getItemId()) {
    		case R.id.menu_add_car:
    			startActivity(new Intent(MainActivity.this, AddCarActivity.class));
    			
    			return true;
    			
    		case R.id.menu_add_fuel_event:
    			startActivity(new Intent(MainActivity.this, AddFuelEventActivity.class));
    			
    			return true;
    			
    		case R.id.menu_car_list:
    			startActivity(new Intent(MainActivity.this, CarListActivity.class));
    			
    			return true;
    			
    		case R.id.menu_fuel_event_list:
    			startActivity(new Intent(MainActivity.this, FuelEventListActivity.class));
    			
    			return true;
    	
    		default:
    			return super.onOptionsItemSelected(item);
    	}

    }
    
    private class GetData extends AsyncTask<Void, Void, Void> {
    	
    	List<Car> Cars;

		@Override
		protected Void doInBackground(Void... params) {
			AKPersistenceManager PM = AKPersistenceManager.getInstance();
			
			Cars = PM.getObjects(Car.class);
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void Result) {
			if(Cars.size() > 0) {
				TextView label_car_name = (TextView) findViewById(R.id.label_car_name);
				
				String CarName = Cars.get(0).getYear() + " " + Cars.get(0).getMake() + " " + Cars.get(0).getModel() + " " + Cars.get(0).getIsPrimary();
				
				label_car_name.setText(CarName);
			}
		}
    	
    }
    
}
