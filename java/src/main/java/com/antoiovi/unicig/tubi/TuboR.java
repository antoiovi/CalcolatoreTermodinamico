/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.antoiovi.unicig.tubi;

/**
 *
 * @author antoiovi
 */
public class TuboR extends Tubo{
	double lato_lung_i;
	double lato_cort_i;
	double lato_lung_e;
	double lato_cort_e;
	
	public TuboR(double lato_cort_i,double lato_lung_i,double spessore, double lunghezza, double restermica, double rugosita) {
		
		super(lunghezza, restermica, rugosita);
		
		this.spessore=spessore;
		this.lato_cort_i=lato_cort_i;
		this.lato_lung_i=lato_lung_i;
		lato_cort_e=lato_cort_i+spessore*2;
		lato_lung_e=lato_lung_i+spessore*2;
		
	}

 
	
    
    @Override
    public double Per_est() {
    	       return 2*lato_lung_e+2*lato_cort_e;
    }

    @Override
    public double Per_int() {
        return 2*lato_lung_i+2*lato_cort_i;
    }

    @Override
    public double Area_int() {
        return lato_lung_i*lato_cort_i;
    }

    @Override
    public double Area_est() {
    	return lato_lung_e*lato_cort_e;
    }
    
    
 
 	@Override
	public void setSpessore(double spess) {
	this.spessore=spess;
	lato_lung_e=lato_lung_i+2*this.spessore;
	lato_cort_e=lato_cort_i+2*this.spessore;
	}


    
}
