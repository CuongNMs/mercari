package com.cuongnm.mercari.utils;

import java.io.Serializable;
import java.util.Map;

public class BuyRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6555946663441365914L;
	private int payType;
	private String address;
	private String city;
	private int status;
	private Map<Long, Integer> setProducts;

	public BuyRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BuyRequest(int payType, String address, String city, int status, Map<Long, Integer> setProducts) {
		super();
		this.payType = payType;
		this.address = address;
		this.city = city;
		this.status = status;
		this.setSetProducts(setProducts);
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Map<Long, Integer> getSetProducts() {
		return setProducts;
	}

	public void setSetProducts(Map<Long, Integer> setProducts) {
		this.setProducts = setProducts;
	}

}
