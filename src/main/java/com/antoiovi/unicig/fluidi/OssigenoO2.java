package com.antoiovi.unicig.fluidi;

 
import java.util.List;
import java.util.Locale;
import java.util.Map;
 import java.math.BigDecimal;
/**
 *
 * @author Antonello iovino antoiovi@antoiovi.com
 */
public class OssigenoO2 extends Fluido{
private final double massamolare=31.999;
private   final double R =  Ru / massamolare;

//Coefficienti calcolo capacita termica per T>1000 K
 static final double a1= 3.28253784E+00 ;
static final double a2= 1.48308754E-03;
 static final double a3=-7.57966669E-07;
 static final double a4=2.09470555E-10;
 static final double a5=-2.16717794E-14;    
//Coefficienti calcolo capacita termica per T<1000 K 
static final double b1=3.78245636E+00;
static final double b2=-2.99673416E-03;
 static final double b3=9.84730201E-06 ;
 static final double b4=-9.68129509E-09 ;
 static final double b5=3.24372837E-12;

 
 
    public OssigenoO2() {
		super();
        
    
   	/**
   	 * VALORI PER CALCOLO VISCOSITA CON FORMULA DI SUTHERLAND
   	 */
   	mu0=20.18e-6;
	T0=292.25;
	C=127.0;
    }
    

     
    @Override
	public double MassaMolare() {
		return massamolare;
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
		* Poiche la costante dei gas è espressa in J/(K kmole) =8314.472 il valre resituito e
		* la costante del gas Ru/massamolare=J/(K kg)il calore specifico in J/(K kg)
		********* */
 @Override
public double CapTerm() {
 
 double cp;
		if(temperatura >1000){
			cp = a1 + a2 * temperatura + a3 * Math.pow(temperatura, 2) + a4 * Math.pow(temperatura, 3)
					+ a5 * Math.pow(temperatura, 4);
		}else{
			    
			cp = b1 + b2 * temperatura + b3 * Math.pow(temperatura, 2) + b4 * Math.pow(temperatura, 3)
					+ b5 * Math.pow(temperatura, 4);
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
    public double CondicTermica() {
   	 double a1=0.09156;
     double b1=0.6162*1e-3;
     double c1=-0.01062*1e-6;
     double d1=0.02535*1e-9;
		double x = a1 + b1 * temperatura +c1 * Math.pow(temperatura, 2.0)
				+d1*Math.pow(temperatura, 3.0);
		return x/10  ;
    }

   
    /*
     *Costante dei gas J/(K kmol)
     */
     @Override
     public double CostElasticita() {
      return R;
     }
    
    @Override
    public String toString() {
        return "O2"; 
    }
	
		@Override
	public boolean equals(Object other) {
		BigDecimal bd1 = new BigDecimal(temperatura);
		 bd1.setScale(0, BigDecimal.ROUND_HALF_EVEN);
		BigDecimal bd2 = new BigDecimal(  ((OssigenoO2)other).getTemperatura() );
		bd2.setScale(0, BigDecimal.ROUND_HALF_EVEN);
		BigDecimal bd3 = new BigDecimal(pressione);
		bd3.setScale(0, BigDecimal.ROUND_HALF_EVEN);
		BigDecimal bd4 = new BigDecimal(  ((OssigenoO2)other).getPressione() );
		bd4.setScale(0, BigDecimal.ROUND_HALF_EVEN);
		
		int comp_temp=bd1.compareTo(bd2);  // returns (-1 if a < b), (0 if a == b), (1 if a > b)
		int comp_press=bd3.compareTo(bd4);
		
		//log(String.format("this.temperatura %f other.temperatura %f ",temperatura,((OssigenoO2)other).getTemperatura() ));
	         return (other != null && getClass() == other.getClass() )
	             ? (comp_temp==0 && comp_press==0 )
	             : (other == this);
	     }
	
		@Override
	   public int hashCode() {
		   return this.toString().hashCode();
	     }
		 
		 void log(String  str){
			 System.out.println(str);
		 }

}
