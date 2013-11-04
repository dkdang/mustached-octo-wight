package com.dk.cardashboard.objects;

import java.util.Date;

import com.verivo.akula.persistence.AKBasicModel;

public class FuelEvent extends AKBasicModel {

	public void setId(String id) {
		set("id", id);
	}
	
	public String getId() {
		return get("id").toString();
	}
	
	public void setCarId(String carid) {
		set("carid", carid);
	}
	
	public String getCarId() {
		return get("carid").toString();
	}
	
	public void setDate(Date date) {
		set("date", date);
	}
	
	public Date getDate() {
		return (Date) get("date");
	}
	
	public void setGasPrice(Double gasprice) {
		set("gasprice", gasprice);
	}
	
	public Double getGasprice() {
		return (Double) get("gasprice");
	}
	
	public void setGasAmount(Double gasamount) {
		set("gasamount", gasamount);
	}
	
	public Double getGasAmount() {
		return (Double) get("gasamount");
	}
	
	public void setOdometer(Integer odometer) {
		set("odometer", odometer);
	}
	
	public Integer getOdometer() {
		return (Integer) get("odometer");
	}
	
}
