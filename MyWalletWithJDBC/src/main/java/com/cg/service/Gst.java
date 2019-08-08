package com.cg.service;

/**
 * Calculate the GST on the amount
 * 
 * @author tanmpath
 * */
public interface Gst {
	// Different tax rate
	double PCT_5=0.05;
	double PCT_12=0.12;
	double PCT_18=0.18;
	
	public double calculateTax (double PCT,double amount);
}