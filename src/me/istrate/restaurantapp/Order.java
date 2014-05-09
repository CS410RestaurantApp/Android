package me.istrate.restaurantapp;

public class Order {        //order objects
	
	private int m_iTableNumber; //table holder
	private String m_sOrder;   //order holder
	
	public Order() {}
	
	//builder pattern
	public Order TableNumber(int i) {
		m_iTableNumber = i;                     //returns table number info
		return this;
	}
	public Order OrderContent(String s) {
		m_sOrder = s;                          //returns order info
		return this;
	}
	
	public void setTableNumber(int i) {
		m_iTableNumber = i;                     //returns table num var
	}
	
	public void setOrder(String s) {         
		m_sOrder = s;
	}                                               //returns order string
	
	public String getOrder() {
		return m_sOrder;
	}
	public int getTableNumber() {
		return m_iTableNumber;
	}
	
	@Override
	public String toString() {
		return "Table #: " + getTableNumber() + " Order is: " + getOrder();
	}
}
