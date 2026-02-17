
package com.antoiovi.unicig.fluidi;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
 
/**
 *
 * @author Antonello iovino antoiovi@antoiovi.com
 */
public   class AzotoN2 extends Fluido{

// massa molare 28.013 u.m.a 
// 28.013 g/mole
// 28.013 Kg/Kmole	
private final static double massamolare=28.013;// u.m.a.
// Ru=8314.472 J/(K kmol)
// R= 8314.472/28.013= J/(K Kg)
 private static final double R=Ru/massamolare; //


/***
 * 12/05/2015 Punto critico
 **/
double temper_c=126.192;
double press_c=3.3958e6;
 
    public AzotoN2() {
 
    	/**
    	* vISCOSIT DINAMICA : FORMULA DI SUTHERLAND
    	**/
    	mu0=17.81e-6;
    	T0=300.55;
    	C=111.0;
       //init("azoto");
    }

         @Override
	 public void setTemperatura(double temperatura){
			this.temperatura=temperatura;
			}
 
    @Override
	public void setPressione(double pressione){
			this.pressione=pressione;
			}
 
   
    @Override
	public double MassaMolare() {
    return massamolare;
    }
   
 
/**
		 * Restituisce la conducibilita termica del gas; dipende solo dalla temperatura.
	     * Polinomio tratto da S.C. SAXENA : University of Illinois, 1970
		 * Unita di misura W /(m K)
		 * @return conducibilta termica in W/(m K)
	     */
@Override
public double CondicTermica( ){
 
			double a1=0.05681;
		     double b1=0.7189*1e-3;
		     double c1=0.1259*1e-6;
		     double d1=-0.01199*1e-9;
 				double x = a1 + b1 * temperatura -c1 * Math.pow(temperatura, 2.0)
						+d1*Math.pow(temperatura, 3.0);
 
	  		return x/10 ;

}

  /*****
		* Restituisce la capacita termica a pressione costante per il CO2;
		* Viene usato il  NASA polynomials con la forma :
		* Cp/R = a1 + a2 T + a3 T^2 + a4 T^3 + a5 T^4.
		* con i valori tratti da http://combustion.berkeley.edu/gri_mech/data/nasa_plnm.html
		* Poiche la costante dei gas e espressa in J/(K kmole) =8314.472 il valre resituito e
		* la costante del gas Ru/massamolare=J/(K kg)il calore specifico in J/(K kg)
		********* */
 @Override
public double CapTerm() {
 
 double cp;
		if(temperatura >1000){
		double a1= 0.02926640E+02;
		double a2=0.14879768E-02;
		 double a3=-0.05684760E-05;
		double a4=0.10097038E-09;
		double a5=-0.06753351E-13; 
			cp = a1 + a2 * temperatura + a3 * Math.pow(temperatura, 2) + a4 * Math.pow(temperatura, 3)
					+ a5 * Math.pow(temperatura, 4);
		}else{
			    
		double a1=0.03298677E+02 ;
		double a2=0.14082404E-02;
		 double a3=-0.03963222E-04;
		double a4=0.05641515E-07;
		double a5=-0.02444854E-10; 
			cp = a1 + a2 * temperatura + a3 * Math.pow(temperatura, 2) + a4 * Math.pow(temperatura, 3)
					+ a5 * Math.pow(temperatura, 4);
		}
			/**
			 * cp/R R[J/(kg K)]      cp =cp/R x R  [J/(kg K)]
			 */
			return cp*R;

 
 /**

 * cp [J/K kg]
 */

 
}
 

    @Override
    public String toString() {
        return "N2"; //To change body of generated methods, choose Tools | Templates.
    }



	@Override
	public boolean equals(Object other) {
		BigDecimal bd1 = new BigDecimal(temperatura);
		 bd1.setScale(0, BigDecimal.ROUND_HALF_EVEN);
		BigDecimal bd2 = new BigDecimal(  ((AzotoN2)other).getTemperatura() );
		bd2.setScale(0, BigDecimal.ROUND_HALF_EVEN);
		BigDecimal bd3 = new BigDecimal(pressione);
		bd3.setScale(0, BigDecimal.ROUND_HALF_EVEN);
		BigDecimal bd4 = new BigDecimal(  ((AzotoN2)other).getPressione() );
		bd4.setScale(0, BigDecimal.ROUND_HALF_EVEN);
		
		int comp_temp=bd1.compareTo(bd2);  // returns (-1 if a < b), (0 if a == b), (1 if a > b)
		int comp_press=bd3.compareTo(bd4);
		
		//log(String.format("this.temperatura %f other.temperatura %f ",temperatura,((AzotoN2)other).getTemperatura() ));
	         return (other != null && getClass() == other.getClass() )
	             ? (comp_temp==0 && comp_press==0 )
	             : (other == this);
	     }
	
		@Override
	   public int hashCode() {
		   return this.toString().hashCode();
	       
	     }

	

	/*
     *Costante dei gas J/(K kmol)
     */
     @Override
     public double CostElasticita() {
      return R;
     }  

    

    
}
