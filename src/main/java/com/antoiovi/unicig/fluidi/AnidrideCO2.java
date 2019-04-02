package com.antoiovi.unicig.fluidi;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Antonello iovino antoiovi@antoiovi.com
 */
public class AnidrideCO2 extends Fluido {

	private final double massamolare=44.010;
	
	/**
     * Costante universale dei gas J/(K kmol) Ru=8314.472;
     * Costante del gas J/(kg K)
     */
	private    final double R= Ru/massamolare;
 
	    public  AnidrideCO2 () {
 	     	 
	   
	    	/**
	    	* vISCOSIT DINAMICA : FORMULA DI SUTHERLAND
	    	**/
	    	mu0=14.8e-6;//Pa s
	    	T0=293.15;
	    	C=240.0;
	       
	    }

         @Override
	 public void setTemperatura(double temperatura){
			this.temperatura=temperatura;
			}
 
    @Override
	public void setPressione(double pressione){
			this.pressione=pressione;
			}
 
	   /*****
		* Restituisce la capacita termica a pressione costante per il CO2;
		* Viene usato il  NASA polynomials con la forma :
		* Cp/R = a1 + a2 T + a3 T^2 + a4 T^3 + a5 T^4.
		* con i valori tratti da http://combustion.berkeley.edu/gri_mech/data/nasa_plnm.html
		* Poiche la costante dei gas e  espressa in J/(K kmole) =8314.472 il valre resituito e
		* la costante del gas Ru/massamolare=J/(K kg)il calore specifico in J/(K kg)
		********* */
	   @Override
		public double CapTerm( ) {
		
		double cp;
		if(temperatura >1000){
		double a1=3.85746029E+00;
		double a2=4.41437026E-03;
		 double a3=-2.21481404E-06;
		double a4=5.23490188E-10;
		double a5=-4.72084164E-14; 
			cp = a1 + a2 * temperatura + a3 * Math.pow(temperatura, 2) + a4 * Math.pow(temperatura, 3)
					+ a5 * Math.pow(temperatura, 4);
		}else{
			    
		double a1=2.35677352E+00;
		double a2=8.98459677E-03;
		 double a3=-7.12356269E-06;
		double a4=2.45919022E-09;
		double a5=-1.43699548E-13; 
			cp = a1 + a2 * temperatura + a3 * Math.pow(temperatura, 2) + a4 * Math.pow(temperatura, 3)
					+ a5 * Math.pow(temperatura, 4);
		}
			/**
			 * cp/R R[J/(kg K)]      cp =cp/R x R  [J/(kg K)]
			 */
			return cp*R;

		}
	    
		/**
		 * Restituisce la conducibilita termica del gas; dipende solo dalla temperatura.
	     * Polinomio tratto da S.C. SAXENA : University of Illinois, 1970
		 * Unita di misura W /(m K)
		 * @return conducibilta termica in W/(m K)
	     */
	    @Override
	    public double CondicTermica( ) {
	    	 
	    	 double a1=-0.05413;
		     double b1=0.7123*1e-3;
		     double c1=-0.09456*1e-6;
		     double d1=-0.08449*1e-9;
			double x = a1 + b1 * temperatura -c1 * Math.pow(temperatura, 2.0)
						+d1*Math.pow(temperatura, 3.0);
	  		return x/10 ;
	    }
		
		 
		
	    @Override
		public double MassaMolare() {
	    return massamolare;
	    }
	   

	    @Override
	    public String toString() {
	        return "CO2"; //To change body of generated methods, choose Tools | Templates.
	    }
	    
		@Override
	public boolean equals(Object other) {
		BigDecimal bd1 = new BigDecimal(temperatura);
		 bd1.setScale(0, BigDecimal.ROUND_HALF_EVEN);
		BigDecimal bd2 = new BigDecimal(  ((AnidrideCO2)other).getTemperatura() );
		bd2.setScale(0, BigDecimal.ROUND_HALF_EVEN);
		BigDecimal bd3 = new BigDecimal(pressione);
		bd3.setScale(0, BigDecimal.ROUND_HALF_EVEN);
		BigDecimal bd4 = new BigDecimal(  ((AnidrideCO2)other).getPressione() );
		bd4.setScale(0, BigDecimal.ROUND_HALF_EVEN);
		
		int comp_temp=bd1.compareTo(bd2);  // returns (-1 if a < b), (0 if a == b), (1 if a > b)
		int comp_press=bd3.compareTo(bd4);
		
		//log(String.format("this.temperatura %f other.temperatura %f ",temperatura,((AnidrideCO2)other).getTemperatura() ));
	         return (other != null && getClass() == other.getClass() )
	             ? (comp_temp==0 && comp_press==0 )
	             : (other == this);
	     }
	
		@Override
	   public int hashCode() {
		   return this.toString().hashCode();
	     }
		
		
	    /*
	     *@return Costante dei gas J/(K kmol)
	     */
	     @Override
	     public double CostElasticita() {
	      return R;
	     }
	    
	     
		 

}
