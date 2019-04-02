

package com.antoiovi.unicig.tubi;

/**
*  Autore Antonell Iovino
 * Non viene definita la forma 
 * @author antoiovi
 */
public abstract class Tubo {
  
  protected double lunghezza;
  protected double altezza;
  protected double resistenzaTermica;
  protected double rugosita;
  protected double spessore;
  
  // Valori calcolati tipici della forma del tubo , vengono calcolate nelle 
  // implementazioni tipiche della forma
  public abstract double Per_est();  
  public abstract double Per_int();  
  public abstract double Area_int();
  public abstract double Area_est();
  
  
    public double Dh_int() {
        return 4*Area_int()/Per_int();
    }

    public double Dh_est() {
        return 4*Area_est()/Per_est();
    }
  
  
  
  public Tubo(double lunghezza,double restermica,double rugosita){
	  this.lunghezza=lunghezza;
	  this.altezza=lunghezza;
	  this.resistenzaTermica=restermica;
	  this.rugosita=rugosita;
  }
  
  public double getLunghezza(){
	  return lunghezza;
  }
  public double getAltezza(){
	  return altezza;
  }
  public double getResistenzaTermica(){
	  return resistenzaTermica;
	  
  }
  public double getRugosita(){
	  return rugosita;
  }
  public double getSpessore(){
	  return spessore;
  }
  
  public void setLunghezza(double lunghezza){
	  this.lunghezza=lunghezza;
	  this.altezza=(this.lunghezza<this.altezza)?this.lunghezza:this.altezza;
  }
  public void setAltezza(double altezza){
	  this.altezza=altezza;
	  this.lunghezza=(this.altezza<this.lunghezza)?this.lunghezza:altezza;
  }
  
  public void setResistenzaTermica(double resistenzaTermica){
	  this.resistenzaTermica=resistenzaTermica;
  }
  public void setRugosita(double rugosita){
	  this.rugosita=rugosita;
  }
  
  public abstract void setSpessore(double spessore);
  
  
}
