package com.antoiovi.unicig.impianti;

import  com.antoiovi.unicig.fluidi.comb.*;

import java.util.List;
import java.util.Locale;
import java.util.Map;
 import java.math.BigDecimal;
/**
 *
 * @author Antonello iovino antoiovi@antoiovi.com
 * @version 1.0
 * Utilizzo :

 *   Combustibile comb=CombustibiliFactory.getInstance().getNewCombustibile(int combustibile);
 *    Caldaia cald=new Caldaia(double Potenzza_kW,combustibile,double TemperaturaFumi_K);
 *  Modificare eventuali parametri di default :
        valori di default
            aria_naturale=false;
            aria_soffiata=true;
            PL=101000.0;  pRESSONE ATMOSFERICA
Recuperare dati calcolati :


*
*/
public class Caldaia  {

 // Proprietà combustibili tabella B1

 static final int HU       =0; //Tenore di energia del combustibile
 static final int V_ATR_MIN=1; // relazione tra volumegas minimom
 static final int VL_MIN   =2;
 static final int V_H2O    =3;
 static final int CO2_MAX  =4;
 static final int SO2_MAX  =5;

// Combustibili
 static final int COKE       =0;
 static final int ANTRACITE  =1;
 static final int LIGNITE    =2;
 static final int RFO_4		 =3;
 static final int RFO_2		 =4;
  static final int RFO_1     =5;
 static final int RISC_DOM   =6;
 static final int CHEROSENE  =7;
 static final int GAS_NAT_H  =8;
 static final int GAS_NAT_L  =9;
 static final int GAS_LIQUIDO=10;
 static final int LEGNO_23   =11;
 static final int LEGNO_33   =12;
 static final int PALLET     =13;

public boolean _LOG=false;
 Combustibile comb;
 int combustibile;
double Q;
double rend;
double co2;
double portatamassica;
double tm;
double PL; // Pressione aria esterna
double Kf; // Kf fattore di conversione da SO2 a SO3 in % ???

double Pw ; // tiraggio minimo

boolean aria_naturale;
boolean aria_soffiata;

 /***
*  Costruttore di default per bruciatore aria soffiata
*
* @param double tm temperatura media prodotti combustione °C
* @param double potenza utile in kW
* @param int combustibile : valore da 0 a 13 ; NON VIENE FATTO IL CHECK
*  			Caldaia.COKE   Caldaia.ANTRACITE  Caldaia.LIGNITE Caldaia.RFO_4   Caldaia.RFO_2 Caldaia.RFO_1 Caldaia.RISC_DOM
*			Caldaia.CHEROSENE  Caldaia.GAS_NAT_H  Caldaia.GAS_NAT_L  Caldaia.GAS_LIQUIDO  Caldaia.LEGNO_23 Caldaia.LEGNO_33
*  			Caldaia.PALLET
 ****/

 public Caldaia(double potenzautile,int combustibile,double tm) {
		this.Q		=potenzautile;
		this.comb	=CombustibiliFactory.getInstance().getNewCombustibile(combustibile);
    this.combustibile=combustibile;
		this.tm=tm;
		aria_naturale=false;
		aria_soffiata=true;
		// Inizzializza con valori di default : Pw (tiraggio minimo),Rendimento, sigma(CO2)
		this.InitCombustibile();
    portatamassica= comb.portataMassicaFumi( Q,rend,co2);

		PL=101000.0;
		Kf=1;
		 }
/***
 *  Costruttore in cui è possibile inserire aria_natura (true) o soffiata (aria_naturale=false)
 ****/
 public Caldaia(double potenzautile,int combustibile,double tm, boolean aria_naturale) {
		this.Q		=potenzautile;
		this.comb	=CombustibiliFactory.getInstance().getNewCombustibile(combustibile);
		this.tm=tm;
		this.aria_naturale=aria_naturale;
		aria_soffiata=aria_naturale?false:true;
		// Inizzializza con valori di default : Pw (tiraggio minimo),Rendimento, sigma(CO2)
		this.InitCombustibile();
		PL=101000.0;
		Kf=1;
		 }

public Combustibile getComb(){
  return this.comb;
}
/**
* Prospetto B.2
*  Inizzializza il combustibile in base al prospetto B.2 della Norama UNI EN 13384-1
*
*/
void  InitCombustibile(){
	double logQ=Math.log10(Q);
  log(String.format("Input %d",combustibile));

	switch(combustibile){
		case COKE:
		case ANTRACITE:
		case LIGNITE:
    log("coke antracite lignite");
				if(Q<= 100.0){
					Pw=15*logQ;    // Tiraggio minimo
				}else if(Q <= 1000){
					Pw= -70+50*logQ;  // Tiraggio minimo
				}else{
					Pw=80;			// Tiraggio minimo
				}

			rend=68.65+4.35*logQ; // valido per potenze fino a 2000 kW
			if(Q<100){
				co2=9.5;
			}else{
				co2=4.1+2.7*logQ;
			}
			break;
		case LEGNO_23:
		case LEGNO_33:
		case PALLET :
log("legno pallet");
			if(Q<= 50.0){
					Pw=15*logQ; // Tiraggio minimo
				}else{
					Pw=27+13*logQ;  // Tiraggio minimo Valido fino a 350 kW
				}

			rend=51.6+8.4*logQ; // valido per potenze fino a 1000 kW
			if(Q<10){
				co2=8.0;
			}else{
				co2=6.0+2.0*logQ; // valido per potenze fino a 1000 kW
			}

			break;
		default :   // Combustibili Liquidi e gassosi
    log("Defaul gas e liquidi:");
			if(Q<= 100.0){
					Pw=15*logQ; 		// Tiraggio minimo
				}else{
					Pw= -47+38.5*logQ;  // Tiraggio minimo
				}
			if(Q<= 1000.0){
					rend= 85+logQ;		// Rendimento
				}else{
					rend=88.0;  				// Rendimento
				}
				// CO2 per combustibili liquidi utilizzo dati prospetto B.3
				double fx1,fx2,fx3;			// DATI PROSPETTO B3
        fx1=11.2;
        fx2=0.076;
        fx3=13.2;

				if(aria_soffiata){
					if(combustibile==GAS_NAT_H || combustibile==GAS_NAT_L){
						fx1=8.6;
						fx2=0.078;
						fx3=10.2;
            log(String.format("Aria soffiata- GAS; fx1=%5.3f \t fx2=%5.3f\tfx3=%5.3f ",fx1,fx2,fx3));
					}else if(combustibile == GAS_LIQUIDO){
						fx1=10.0;
						fx2=0.080;
						fx3=11.9;
            log(String.format("Aria soffiata - GAS LIQUIDO; fx1=%5.3f \t fx2=%5.3f\tfx3=%5.3f ",fx1,fx2,fx3));
					}else{     // LIQUIDO
						fx1=11.2;
						fx2=0.076;
						fx3=13.2;
            log(String.format("Aria soffiata -COMB LIQUIDO; fx1=%5.3f \t fx2=%5.3f\tfx3=%5.3f ",fx1,fx2,fx3));
					}
				}else{  // ARIA NATURALE : SOLO gas; NON VIENE FATTO TEST SE LIQUIDO....
					if(combustibile==GAS_NAT_H || combustibile==GAS_NAT_L){
						fx1=5.1;
						fx2=0.075;
						fx3=6.0;
            log(String.format("Aria naturale - GAS NATURALE; fx1=%5.3f \t fx2=%5.3f\tfx3=%5.3f ",fx1,fx2,fx3));
					}else if(combustibile == GAS_LIQUIDO){
						fx1=5.9;
						fx2=0.079;
						fx3=7.0;
            log(String.format("Aria naturale - GAS LIQUIDO; fx1=%5.3f \t fx2=%5.3f\tfx3=%5.3f ",fx1,fx2,fx3));
					}
				}

				if(Q<= 100.0){
					co2=fx1/(1-fx2*logQ);						// sigma(CO2)
				}else{
					co2=fx3;  						// sigma(CO2)
				}

			break;
	}

}


	 /**
	 * Imposta pressione atmosferica
	 **/
public void setPL(double _PL){
	this.PL=_PL;
	}

	 /**
	 * Imposta temperatura fumi
	 **/
public void setTm(double _Tm){
	this.tm=_Tm;
}


public void setAria_naturale(){
	this.aria_naturale=true;
	this.aria_soffiata=false;
	this.InitCombustibile();
}

public void setAria_soffiata(){
	this.aria_soffiata=true;
	this.aria_naturale=false;
	this.InitCombustibile();
}

public double getPW_tiraggiominimo(){
  return this.Pw;
}

	 /**
	 * Imposta Kf fattore di conversione da SO2 a SO3 in % ???
	 **/
public void setKf(double _Kf){
	this.Kf=_Kf;
}

/***
* Poratata massica viene calcolata nel costruttore; posso reinizzializzarla
* dopo con setPortatmassica
**/
	 public double portataMassicaFumi( ){
		return portatamassica;
	}

  public void setPortatamassicaFumi(double _portatamassica){
    this.portatamassica=_portatamassica;
  }

	/**
	*  Formula B.3 ( R )
	* @param co2 tenore di co2% secco
	* @return R in J/(kgxK)
	**/

	public double CostElasticita_1(){
		return comb.CostElasticita_1(co2);
	}


	/**
	*  Formula B.4 ( cp )
	* @param tm temperatura media fumi
	* @param co2 tenore di co2% secco
	* @return cp in J/(kgxK)
	**/

	public double CapTermica(){
			return comb.CapTermica(tm, co2);
	}


	/**
	*  Formula B.5 ( sigma(H2O) )
	* @param co2 tenore di co2% secco
	* @return sigma(H2O) tenore del vapore acqueo nei prodotti combustione in %
	**/

	public double TenoreH2O(){
			return comb.TenoreH2O(co2);
	}

	/**
	*  Formula B.6 ( PD) )
	* @param PL pressione dell'aria esterna in Pa
	* @param h20 sigma(H2O) tenore del vapore acqueo nei prodotti combustione in % (Calcolare con B.5)
	* @return PD pressione parziale del vapore acqueo in Pa
	**/
	public double PparzialeH2o(){
		double h2o=this.TenoreH2O();
		return comb.PparzialeH2o( h2o,PL);
	}

	/**
	*  Formula B.7 ( tp )
	* @param PD pressione parziale del vapore acqueo in Pa
	*
	* @return tp temperatura punto di rugiada in °C
	**/
	public double tempPuntoRugiada(){
		double PD=PparzialeH2o();
		return comb.tempPuntoRugiada(PD);
	}

	/**
	*  Formula B.8 ( delta tsp )
	* @param Kf fattore di conversione da SO2 a SO3 in % ???
	*
	* @return aumento del punto di rugiada in Kelvin
	**/
	public double deltaTsp(){
 		return  comb.deltaTsp(Kf);
	}

	/**
	*  Formula B.9 ( lambda A )
	* @param double tm temperatura media prodotti combustione
	*
	* @return coefficiente di conducibilità termica in W/(m x K)
	**/
	public double lambdaA(){
 		return  comb.lambdaA( tm);
	}

	/**
	*  Formula B.10 ( eta A viscosità dinamica )
	* @param double tm temperatura media prodotti combustione
	*
	* @return viscosità dinamica dei prodotti della combustione  in N s/m2
	**/
	public double viscDin(){

		return  comb.viscDin(tm);
	}

	/**
	*  Formula B.11 ( R )
	* @param co2 tenore di co2% secco
	* @param h2o tenore di vapore acqua
	*
	* @return R in J/(kgxK)
	**/

	public double CostElasticita_2(){
		double h2o=this.TenoreH2O();
		return  comb.CostElasticita_2( co2, h2o);
	}


	/**
	*  Formula B.12 ( sigma(H2O) )
	* @param PD pressione parziale del vapore acqueo in Pa
	* @param PL pressione dell'aria esterna in Pa
	* @return sigma(H2O) tenore del vapore acqueo nei prodotti combustione in %
	**/
	public double TenoreH2OPD(){
		double PD=PparzialeH2o();
		return  comb.TenoreH2O( PD, PL);
	}


	/**
	*  Formula B.13 ( PD )
	* @param  tp temperatura punto di rugiada in °C
	*
	* @return PD pressione parziale del vapore acqueo in Pa
	**/
	public double PparzialeH2oTp(){
		 double tp=this.tempPuntoRugiada();
		return  comb.PparzialeH2o(tp);
	}


	public double getRendimento(){
    return this.rend;
  }

  public double getCO2(){
    return this.co2;
  }

void log(String s){
  if(!_LOG)
    return;
 System.out.println(s);
}
void Log(String s){
  System.out.println(s);
}

public void print(){

Log(String.format("%10s %f","Pressione atmosferica",PL));

  Log(String.format("%8s|%7s|%7s|%8s|%3s|"+
                     "%10s|%10s|%10s|%12s|%10s|"+
                     "%10s|%10s|%10s|%10s",
                    "Q [kW]","CO2[%]","Rend","Tfumo °C","---",
                    "PMass[g/s]","R[J/(kgK)]","Vd[N*s/m2]","Cp[J/(kg*K)]","l[W/(m*K]",
                    "H20[%]","P H20[Pa]","Trug[°C]","Tirmin[Pa]" ));
  Log(String.format("%8.1f|%7.2f|%7.1f|%8.1f|%3s|"+
                    "%10.3f|%10.1f|%10.3e|%12.1f|%10.3e|"+
                    "%10.1f|%10.1f|%10.1f|%10.1f|",
                    Q,co2,rend,tm,"---",
                  portatamassica,CostElasticita_1() ,viscDin(),CapTermica(),lambdaA(),
                  TenoreH2O(),PparzialeH2o(),tempPuntoRugiada(),this.Pw
                    ));
}

public static void printHeader(){
  System.out.println(
  String.format("%10s|%8s|%7s|%7s|%8s|%3s|"+
                     "%10s|%10s|%10s|%12s|%10s|"+
                     "%10s|%10s|%10s|%10s",
                    "Patm[Pa]","Q [kW]","CO2[%]","Rend","Tfumo °C","---",
                    "PMass[g/s]","R[J/(kgK)]","Vd[N*s/m2]","Cp[J/(kg*K)]","l[W/(m*K]",
                    "H20[%]","P H20[Pa]","Trug[°C]","Tirmin[Pa]" )
                    );

}
public void printValue(){
  Log(String.format("%10.1f|%8.1f|%7.2f|%7.1f|%8.1f|%3s|"+
                    "%10.3f|%10.1f|%10.3e|%12.1f|%10.3e|"+
                    "%10.1f|%10.1f|%10.1f|%10.1f|",
                    PL,Q,co2,rend,tm,"---",
                  portatamassica,CostElasticita_1() ,viscDin(),CapTermica(),lambdaA(),
                  TenoreH2O(),PparzialeH2o(),tempPuntoRugiada(),this.Pw
                    ));
}

}
