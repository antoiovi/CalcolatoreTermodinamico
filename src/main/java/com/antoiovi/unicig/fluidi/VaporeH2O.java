package com.antoiovi.unicig.fluidi;


import java.util.List;
 import java.math.BigDecimal;


public class VaporeH2O extends Fluido {
	private final static double massamolare=18.015;
	private static final double R=Ru/massamolare;


     
  

//Coefficienti calcolo capacita termica per T>1000 K
 static final double a1=  4.01721090E+00 ;
static final double a2= 2.23982013E-03;
 static final double a3=-6.33658150E-07 ;
 static final double a4=1.14246370E-10;
 static final double a5=-1.07908535E-14;
//Coefficienti calcolo capacita termica per T<1000 K 
static final double b1= 4.30179801E+00;
static final double b2=-4.74912051E-03;
 static final double b3=2.11582891E-05;
 static final double b4=-2.42763894E-08;
 static final double b5=9.29225124E-12;



	
	public VaporeH2O(){

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
24/06/2015
*	Conducibilta termica : SENGERS ET AL. (nist.gov)
**/
@Override
public double CondicTermica(){
	double k=0;
	double a_i[]={	1.02811*1e-2,2.99621*1e-2,1.56146*1e-2,	-0.00422464	};
	double t_ref=647.3;
	double T=temperatura/t_ref;
	for(int x=0;x<4;x++){
		k+=(a_i[x]*Math.pow(T,x));
	}
	return k*Math.sqrt(T);
}

/**
*  Formula 10 del documento IAPWS R12-08
*  The International Association for the Properties of Water and Steam
*  @return  viscosita dinamica im Pa*s
**/
@Override
public double ViscositaDinamica(){
	
	
	double H[]={1.67752,2.20462,0.6366564,-0.241605};
	double M0=0;
	double D=0;
	double T=temperatura/647.096;
	for(int i=0;i<4;i++){
		D+=(H[i]/Math.pow(T,i));
		log(String.format("D=%f H[%d]: %f",D,i,H[i]));
	}
	
	M0=100.0*Math.pow(T,0.5)/D;
	log(String.format("T : %f Math.pow(T,0.5)=%f \t M0=%f",T ,Math.pow(T,0.5),M0));
	
	double K[][]=new double[][] {
				{5.20094e-1,  2.22531e-1,  -2.81378e-1,   1.61913e-1,  -3.25372e-2,           0.0, 		0.0},
				{8.50895e-2,  9.99115e-1,  -9.06851e-1,   2.57040e-1,         	0.0,           0.0, 		0.0},
				{-1.08374  ,  1.88797   ,  -7.72479e-1,          0.0,         	0.0,           0.0, 		0.0},
				{-2.89555e-1, 1.26613   ,   -4.8937e-1,          0.0,   6.98452e-2 ,           0.0, -4.35673e-3},
				{		 0.0,	     0.0, 	-2.57040e-1,          0.0,   		0.0,    8.72102e-3, 		0.0},
				{		 0.0, 1.20573e-1, 		0.0,          0.0,   		0.0,           0.0,     -5.93264e-4}
		};
	

double ro=this.MassaVolumica();
ro=ro/322.0;
	double a=(1/T)-1;
	double A=0;
	double B=0;
	double r1=ro-1;
	for (int i=0;i<6;i++){
		 A+=Math.pow(a,i);
		for(int j=0;j<7;j++){
			B+=K[i][j]*Math.pow(r1,j);
		}
		A*=B;
		B=0;
	}
	A*=ro;
	double M1=Math.exp(A);
	log(String.format("M1 %f",M1));
	return 1.0e-6*M0*M1;
}


@Override
public String toString() {
    return "VaporeH2O"; 
}


@Override
	public boolean equals(Object other) {
		BigDecimal bd1 = new BigDecimal(temperatura);
		 bd1.setScale(0, BigDecimal.ROUND_HALF_EVEN);
		BigDecimal bd2 = new BigDecimal(  ((VaporeH2O)other).getTemperatura() );
		bd2.setScale(0, BigDecimal.ROUND_HALF_EVEN);
		BigDecimal bd3 = new BigDecimal(pressione);
		bd3.setScale(0, BigDecimal.ROUND_HALF_EVEN);
		BigDecimal bd4 = new BigDecimal(  ((VaporeH2O)other).getPressione() );
		bd4.setScale(0, BigDecimal.ROUND_HALF_EVEN);
		
		int comp_temp=bd1.compareTo(bd2);  // returns (-1 if a < b), (0 if a == b), (1 if a > b)
		int comp_press=bd3.compareTo(bd4);
		//log(String.format("this.temperatura %f other.temperatura %f ",temperatura,((VaporeH2O)other).getTemperatura() ));
	         return (other != null && getClass() == other.getClass() )
	             ? (comp_temp==0 && comp_press==0 )
	             : (other == this);
	     }
	
		@Override
	   public int hashCode() {
		   return this.toString().hashCode();
	     }

@Override
public double MassaMolare() {
	return massamolare;
}
 

/*
 *Costante dei gas J/(K kmol)
 */
 @Override
 public double CostElasticita() {
  return R;
 }
 
 void log(String s){
	// System.out.println(s);
 }
  
 
 
}
