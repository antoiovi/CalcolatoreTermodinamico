package com.antoiovi.unicig.fluidi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Le classi che estendono ed implementano questa classe devo inizzializzare nel
 * loro costruttore l'array propieta[][];
 * Le propieta 
 *  -densita
 *  -viscosita cinematica
 * - numero di prandtl
 * non vengono recuperate dalla tabella ma vengono calcolate in funzione delle altre
 *- la densita dipende dalla pressione e dalla massa molare
 * - la viscosita cinematica è = alla visdinamica/densita
 * -il numero di prandtl=viscoscinematica/diffusivitatermica
 * dove serve la densità c'e come parametro anche la Pressione.
 * @author antoiovi
 */
 public abstract class Fluido implements FluidoI{

 /* Costante universale dei gas J/(K kmol) Ru=8314.472*/ 
public static final double Ru=8314.472;
 
 
 
HashMap<Fluido, Double> map_p;// map ponderali elemneti base
HashMap<Fluido, Double> map_m;// map_m molarielementi base 
 
protected double temperatura;
protected double pressione;


public  Fluido(){
		temperatura=20+273;
		pressione=101000.0;
        this.map_p = new HashMap<Fluido, Double>();
		this.map_m = new HashMap<Fluido, Double>();
		map_p.put(this,new Double(1.0));
		map_m.put(this,new Double(1.0));
	}

 public void setTemperatura(double temperatura){
	this.temperatura=temperatura;
		for (Map.Entry<Fluido, Double> entry : this.map_p.entrySet()) {
            Fluido fluidobase=entry.getKey();
				fluidobase.setTemperatura(temperatura);
			}
		for (Map.Entry<Fluido, Double> entry : this.map_m.entrySet()) {
            Fluido fluidobase=entry.getKey();
				fluidobase.setTemperatura(temperatura);
			}
}
 public void setPressione(double pressione){
	this.pressione=pressione;
	for (Map.Entry<Fluido, Double> entry : this.map_p.entrySet()) {
            Fluido fluidobase=entry.getKey();
				fluidobase.setPressione(pressione);
			}
		for (Map.Entry<Fluido, Double> entry : this.map_m.entrySet()) {
            Fluido fluidobase=entry.getKey();
				fluidobase.setPressione(pressione);
			}
}

 public double getTemperatura(){
	return temperatura;
}
  

 public double getPressione(){
	return pressione;
}
 	
 	 
  public HashMap<Fluido, Double> getMap_p(){
	 return map_p;
	 
 }
   public HashMap<Fluido, Double> getMap_m(){
	  return map_m;
  }
	
	

   /*
    * Costante universale dei gas J/(K kmol) Ru=8314.472;

            J/(K kmol) x 1/(kg/kmole)= J/(kg K)
	 Ru=8314.472 J/(K kmol)
	 R= 8314.472/28.013= J/(K Kg)
	
	 @return Costante dei gas J/(kg K) 
    */
    @Override
    public double CostElasticita() {
	//  Poiche Ru = J/(K Kmole) massaMolare e considerata in Kg/Kmole
	//   ed R = J/(K Kg)
	//  ( il valore e tuttaivia in u.m.a.)  	
        return Ru/MassaMolare();
    }

/**
 * 1- Massa Volumica (Densita)
 * p*v=R*T    v=p/(R*T)=
 *    (N/m2)     (N/m2) Kg   Kg
 * ----------- =---------- = ---
 * J/(K Kg) K       N m      m3
 *
 * @param Pressione
 * @param Temperatura
 * @return Massa volumica (densita) in Kg/m3 
 */
    @Override
    public double MassaVolumica() {
        return pressione/(this.CostElasticita()*temperatura);
    }

    
// 2- Calore specifico(Dipende solo dalla temperatura)
	@Override
    public  abstract double CapTerm();
	
	@Override
	public String toString(){
		return "Fluido";
	};

	/**
     *  Formula di Sutherland per viscositࡤinamica per elementi base
     * 12/06/2015 
     * 
     * */
    protected double mu0;
    protected double T0;
    protected double C;

	/**
	* Calcola la viscosta dinamoca con la formula di Sutherland.
	* @return Viscosità dinamica del gas in Pa*s
	**/
	@Override
    public double ViscositaDinamica( ) {
      
      double T0_3_2=Math.pow(T0,3.0/2.0);
      double lambda=mu0*(T0+C)/T0_3_2;
     return lambda*Math.pow(temperatura,3.0/2.0)/ (temperatura+C);
    
    }
    
	
	
	
// 3 - cONDUCIBILITA TERMICA
    @Override
    public  abstract double CondicTermica( );
    

	//4- diffusività termica
    @Override
    public double DiffusivitaTermica( ){
		// m2/s
		// [W/(m K)]/([kg/m3][J/(K kg)]
		return (CondicTermica()/(MassaVolumica()*CapTerm()));
	}
    
    
     
    
// 6 -viscosita cinematica
//      m2/s
@Override
    public double ViscCin( ) {
        return ViscositaDinamica()/MassaVolumica();
    }

// Rapporto tra diffusdivita cinematica e diffudsivita termica (adimensionale)
@Override
    public double NumeroPrandtl( ) {
        return ViscCin( )/DiffusivitaTermica( );
    }

 
     
 protected double Interpolation(double T1,double T2,double P1,double P2,double T){
//Calculate slope from p1 to p2 
double m = (T2-T1)/(P2-P1); 
double a=P1*(T-T2)/(T1-T2);
double b=P2*(T-T1)/(T1-T2);
return a-b;        
    }
    
    
}
