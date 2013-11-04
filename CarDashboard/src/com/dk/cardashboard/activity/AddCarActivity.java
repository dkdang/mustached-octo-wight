package com.dk.cardashboard.activity;

import java.util.UUID;

import com.dk.cardashboard.R;
import com.dk.cardashboard.objects.Car;
import com.verivo.akula.persistence.AKPersistenceManager;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class AddCarActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_add_car);
		
		Button button_save = (Button) findViewById(R.id.button_save);
		
		button_save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Save().execute();
			}
		});
	}
	
	public class Save extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			
			EditText edit_year = (EditText) findViewById(R.id.edit_year);
			EditText edit_make = (EditText) findViewById(R.id.edit_make);
			EditText edit_model = (EditText) findViewById(R.id.edit_model);
			CheckBox checkbox_primary_vehicle = (CheckBox) findViewById(R.id.checkbox_primary_vehicle);
			
			String isPrimary;
			
			if(checkbox_primary_vehicle.isChecked()) 
				isPrimary = "true";
			else
				isPrimary = "false";
			
			AKPersistenceManager PM = AKPersistenceManager.getInstance();
			
			Car newCar = new Car();
			
			newCar.setId(UUID.randomUUID().toString());
			newCar.setYear(edit_year.getText().toString());
			newCar.setMake(edit_make.getText().toString());
			newCar.setModel(edit_model.getText().toString());
			newCar.setIsPrimary(isPrimary);
			
			PM.add(newCar);
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			finish();
		}
		
	}
	
}
