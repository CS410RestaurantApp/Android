package me.istrate.restaurantapp;

public class Alert {
	
	private String m_sAlert;
	
	public Alert() {}
	
	//builder pattern
	public Alert AlertContent(String s) {  //returns a string of arrays for alerts
		m_sAlert = s;
		return this;
	}
	
	public void setAlert(String s) {  //sets alerts
		m_sAlert = s;
	}
	
	public String getAlert() {      //gets alerts
		return m_sAlert;
	}
	@Override
	public String toString() {
		return "Alert: " + getAlert();  //string that goes before alert
	}
}
