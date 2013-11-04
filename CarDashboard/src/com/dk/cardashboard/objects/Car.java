package com.dk.cardashboard.objects;

import com.verivo.akula.persistence.AKBasicModel;

public class Car extends AKBasicModel {
	
	public void setId(String id) {
		set("id", id);
	}
	
	public String getId() {
		return get("id").toString();
	}
	
	public void setYear(String year) {
		set("year", year);
	}

	public String getYear() {
		return get("year").toString();
	}
	
	public void setMake(String make) {
		set("make", make);
	}
	
	public String getMake() {
		return get("make").toString();
	}
	
	public void setModel(String model) {
		set("model", model);
	}
	
	public String getModel() {
		return get("model").toString();
	}
	
	public String getIsPrimary() {
		return get("IsPrimary").toString();
	}
	
	public void setIsPrimary(String name) {
		set("IsPrimary", name);
	}
	
}
