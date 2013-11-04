package com.dk.cardashboard;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.dk.cardashboard.objects.Car;
import com.dk.cardashboard.objects.FuelEvent;
import com.verivo.akula.AK;
import com.verivo.akula.persistence.AKPersistenceManager;
import com.verivo.akula.persistence.schema.AKSchema;
import com.verivo.akula.persistence.schema.AKSchemaBuilder;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;

public class CarDashboard extends Application {

	private static Context mContext;
	
	@Override
	public void onCreate() {
		mContext = this;
		
		AK.init(getContext(), getContext().getResources().getString(R.string.akula_base_url), getContext().getResources().getString(R.string.akula_application_identifier));
		
		LoadAkulaSchema();
	}
	
	public static Context getContext() {
		return mContext;
	}
	
	public static void LoadAkulaSchema() {
		AssetManager assetManager = getContext().getAssets();
		
		InputStream CarXML;
		InputStream FuelEventXML;
		
		String CarURL = getContext().getResources().getString(R.string.akula_object_car);
		String FuelEventURL = getContext().getResources().getString(R.string.akula_object_fuel_event);
		
		try {
			CarXML = assetManager.open("akula-object/CarSchema.xml");
			AKSchemaBuilder CarSchemaBuilder = new AKSchemaBuilder(CarXML);
			AKSchema CarSchema = CarSchemaBuilder.toSchema();
			AKPersistenceManager.initSchema(Car.class, CarSchema, CarURL);
			
			FuelEventXML = assetManager.open("akula-object/FuelEventSchema.xml");
			AKSchemaBuilder FuelEventSchemaBuilder = new AKSchemaBuilder(FuelEventXML);
			AKSchema FuelEventSchema = FuelEventSchemaBuilder.toSchema();
			AKPersistenceManager.initSchema(FuelEvent.class, FuelEventSchema, FuelEventURL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
