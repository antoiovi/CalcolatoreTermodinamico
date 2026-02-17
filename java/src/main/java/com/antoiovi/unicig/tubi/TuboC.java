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
public class TuboC extends Tubo{
    
    double diam_est;
	double diam_int;

    public TuboC(double diam_int,double diam_est,double lunghezza, double restermica, double rugosita) {
		super(lunghezza, restermica, rugosita);
		this.diam_est=diam_est;
		this.diam_int=diam_int;
		spessore= (diam_est-diam_int)/2;
	}

	
	public void setDiametri(double diamI,double diamE){
		double temp=diamI;
		diamI=diamI<diamE?diamI:diamE;
		diamE=temp<diamE? diamE:temp;
		diam_est=diamE;
		diam_int=diamI;
		spessore=(diam_est-diam_int)/2;
	}
	public void setDi_Spess(double diamI,double spess){
		this.spessore=spess;		
		this.diam_int=diamI;
		diam_est=diam_int+2*spessore;
	}
	
	public void setDe_Spess(double diamE,double spess){
			this.spessore=spess;		
			this.diam_est=diamE;
			diam_int=diam_est-2*spessore;
	}
	
	public void setDiam_int(double diam_int) {
        this.diam_int = diam_int;
		diam_est=diam_int+2*spessore;
		
    }
	
	public void setDiam_est(double diam_est) {
        this.diam_est = diam_est;
		diam_int=diam_est-2*spessore;
    }
	
	
	public double getDiam_int() {
        return diam_int;
    }

    public double getDiam_est() {
        return diam_est;
    }

 
	
	@Override
    public double Area_int() {
        return Math.PI*(diam_int/2)*(diam_int/2);
    }

    @Override
    public double Area_est() {
        return Math.PI*(diam_est/2)*(diam_est/2);
    }

    @Override
    public double Per_est() {
        return Math.PI*diam_est;
    }

    @Override
    public double Per_int() {
        return Math.PI*diam_int;
    }


	@Override
	public void setSpessore(double spess) {
	this.spessore=spess;
	diam_est=diam_int+2*spessore;
	}

}
