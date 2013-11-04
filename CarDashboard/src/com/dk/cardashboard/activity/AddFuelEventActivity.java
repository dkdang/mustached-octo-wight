package com.dk.cardashboard.activity;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.dk.cardashboard.R;
import com.dk.cardashboard.objects.Car;
import com.dk.cardashboard.objects.FuelEvent;
import com.verivo.akula.persistence.AKPersistenceManager;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

public class AddFuelEventActivity extends Activity {
	
	private static ArrayAdapter<String> adapter;
	private static List<String> key = new ArrayList<String>();
	private static Calendar FuelEventDate = Calendar.getInstance();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_add_fuel_event);
		
		TextView spinner_date = (TextView) findViewById(R.id.spinner_date);
		Button button_save = (Button) findViewById(R.id.button_save);
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new ArrayList<String>());
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		new GetCars().execute();
		
		spinner_date.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDatePickerDialog(v);
			}
		});
	
		button_save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Save().execute();
			}
		});
	}
	
	public class GetCars extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			AKPersistenceManager PM = AKPersistenceManager.getInstance();
			
			List<Car> Cars = PM.getObjects(Car.class);
			
			for(int i=0; i < Cars.size(); i++) {
				String CarId = Cars.get(i).getId();
				String Year = Cars.get(i).getYear();
				String Make = Cars.get(i).getMake();
				String Model = Cars.get(i).getModel();
				String CarName = Year + " " + Make + " " + Model;
				
				adapter.add(CarName);
				key.add(CarId);
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void results) {
			Spinner spinner_car = (Spinner) findViewById(R.id.spinner_car);
			
			spinner_car.setAdapter(adapter);
		}
		
	}
	
	public void showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getFragmentManager(), "timePicker");
	}
	
	public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);
			
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			TextView spinner_date = (TextView) getActivity().findViewById(R.id.spinner_date);
			
			spinner_date.setText(monthOfYear + "/" + dayOfMonth + "/" + year);
			
			FuelEventDate.set(year, monthOfYear, dayOfMonth);
		}
		
	}
	
	public class Save extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			
			Spinner spinner_car = (Spinner) findViewById(R.id.spinner_car);
			EditText edit_gas_price = (EditText) findViewById(R.id.edit_gas_price);
			EditText edit_gas_amount = (EditText) findViewById(R.id.edit_gas_amount);
			EditText edit_odometer = (EditText) findViewById(R.id.edit_odometer);
			
			AKPersistenceManager PM = AKPersistenceManager.getInstance();
			
			FuelEvent newFuelEvent = new FuelEvent();
			
			newFuelEvent.setCarId(key.get(spinner_car.getSelectedItemPosition()).toString());
			newFuelEvent.setDate(FuelEventDate.getTime());
			newFuelEvent.setGasPrice(Double.parseDouble(edit_gas_price.getText().toString()));
			newFuelEvent.setGasAmount(Double.parseDouble(edit_gas_amount.getText().toString()));
			newFuelEvent.setOdometer(Integer.parseInt(edit_odometer.getText().toString()));
			
			PM.add(newFuelEvent);
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			finish();
		}
		
	}
	
}
