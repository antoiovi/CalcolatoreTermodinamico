package com.antoiovi.unicig.fluidi;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
 import java.math.BigDecimal;



/**
 * Aria base : implementazione di default dell'interfaccia Fluido, 
 * con valori costatntiti tipici dell'aria a composozione tipica
 * @author antoiovi
 */
public class Aria_base extends Fluido {
Miscela aria;
 public static final double massamolare=28.96;
public static final double R = Ru/massamolare;
public static  final double Tcritica=132.52;
public   static final double Pcritica=3.78502e-6;
public   static final double ro_critica=313.0;//kg/m3


public   Aria_base(){
	
	PairFluidoDouble ossigeno=new PairFluidoDouble(new OssigenoO2(),21.0 );
	PairFluidoDouble azoto=new PairFluidoDouble(new AzotoN2(),79.0);
	
	List<PairFluidoDouble> listmoli=new ArrayList<PairFluidoDouble>();
	listmoli.add(azoto);
	listmoli.add(ossigeno);
	aria=new Miscela(listmoli,Miscela.FLUIDO_MOLI);
	    	/**
	    	* vISCOSITA DINAMICA fORMULA DI sUTHERLKAN
	    	***/

	    	mu0=18.27e-6;
	    	T0=291.15;
	    	C=120.0;
}


/**
* cp [J/K kg]
*/
@Override
public double CapTerm() {
	/*	 * 
	 * Formula Termodinamica eTrasmissione del calore
	 ***/
	    double	a=2.811;
	    double	b=-0.19671e-3;;
		double	c=0.48021e-6;
		double 	d=-1.9661e-10;
//            return aria.CapTerm();
 	double x=a+b*temperatura+c*Math.pow(temperatura,2)+d*Math.pow(temperatura, 3);
 		return x*CostElasticita();
 
}
 

/****
* cONDUCIBILITA TERMICA ARIA . fORMULA sTEPHAN AND lAESECKE
* oTTIMO!!
* 15/06/2015
* 
**/
 @Override
public double CondicTermica( ) {
	double coeff[]={33.97299025,-164.702679,262.108546,-21.5346955,-443.455815,607.339582,-368.790121,111.296674,-13.4122465};
	double espon[]={   -1.0   ,-2.0/3.0, -1.0/3.0, 0.0, 1.0/3.0,2.0/3.0,1.0, 4.0/3.0,5.0/3.0};

	double LAMBDA=4.358e-3;
double Tr=temperatura/Tcritica;
double T_exp[]=new double[espon.length];
for(int x=0;x<espon.length;x++){
  
  T_exp[x]=Math.pow(Tr,espon[x]);
}
double res=0;
for(int i=0;i<coeff.length;i++){
  res+=(coeff[i]*T_exp[i]);
		}
/**
 * W/(m K)
 **/
return res*LAMBDA;
}

 
	@Override
	public void setTemperatura(double temperatura){
		this.temperatura=temperatura;
		aria.setTemperatura(temperatura);
	}
	
	@Override
	public void setPressione(double pressione){
	this.pressione=pressione;
	aria.setPressione(pressione);
	}


@Override
public String toString() {
	return "Aria_base";
}


@Override
	public boolean equals(Object other) {
		BigDecimal bd1 = new BigDecimal(temperatura);
		 bd1.setScale(0, BigDecimal.ROUND_HALF_EVEN);
		BigDecimal bd2 = new BigDecimal(  ((Aria_base)other).getTemperatura() );
		bd2.setScale(0, BigDecimal.ROUND_HALF_EVEN);
		BigDecimal bd3 = new BigDecimal(pressione);
		bd3.setScale(0, BigDecimal.ROUND_HALF_EVEN);
		BigDecimal bd4 = new BigDecimal(  ((Aria_base)other).getPressione() );
		bd4.setScale(0, BigDecimal.ROUND_HALF_EVEN);
		
		int comp_temp=bd1.compareTo(bd2);  // returns (-1 if a < b), (0 if a == b), (1 if a > b)
		int comp_press=bd3.compareTo(bd4);
		
		//log(String.format("this.temperatura %f other.temperatura %f ",temperatura,((Aria_base)other).getTemperatura() ));
	         return (other != null && getClass() == other.getClass() )
	             ? (comp_temp==0 && comp_press==0 )
	             : (other == this);
	     }
	
		@Override
	   public int hashCode() {
		   return this.toString().hashCode();
	       
	     }




@Override
 public HashMap<Fluido, Double> getMap_p(){
	 return aria.getMap_p();
	 
 }
  @Override
  public HashMap<Fluido, Double> getMap_m(){
	  return aria.getMap_m();
  }

 
 @Override
    public double CostElasticita() {
        return aria.CostElasticita();
    }
    
    @Override
    public double MassaMolare() {
       return aria.MassaMolare();
    }

    
     

     
 
    @Override
    public double ViscositaDinamica() {
	return aria.ViscositaDinamica();
    }

    @Override
    public double ViscCin() {
		return aria.ViscCin();
    }

    // Modeia ponderale frazioni ponderali
    @Override
    public double DiffusivitaTermica() {
		return aria.DiffusivitaTermica();
    }

    
}
