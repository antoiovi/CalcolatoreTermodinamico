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
public class Gener  {


int combustibile;
double Q;
double rend;
double co2;
double portatamassica;
double tm;
double Pw ; // tiraggio minimo
boolean aria_naturale;
boolean aria_soffiata;

public boolean _LOG=false;

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

 public Gener(double potenzautile,int _combustibile,double tm) {
		this.Q		=potenzautile;
		 this.combustibile=_combustibile;
		this.tm=tm;
		aria_naturale=false;
		aria_soffiata=true;
		// Inizzializza con valori di default : Pw (tiraggio minimo),Rendimento, sigma(CO2)
		this.InitCombustibile();
    portatamassica= Comb_1.portataMassicaFumi(combustibile, Q,rend,co2);
		double PL=101000.0;

		 }

     public Gener(double potenzautile,int _combustibile,double tm, boolean _aria_naturale) {
    		this.Q		=potenzautile;
    		 this.combustibile=_combustibile;
    		this.tm=tm;
    		aria_naturale=_aria_naturale;
    		aria_soffiata=!aria_naturale;
    		// Inizzializza con valori di default : Pw (tiraggio minimo),Rendimento, sigma(CO2)
    		this.InitCombustibile();
        portatamassica= Comb_1.portataMassicaFumi(combustibile, Q,rend,co2);
    		double PL=101000.0;
    		 }
/**
* Prospetto B.2
*  Inizzializza  in base al prospetto B.2 della Norama UNI EN 13384-1 :
*       Il tiraggio minimo Pw
*       Il rendimento  ()
*       Il co2 %
*
*/
void  InitCombustibile(){
	double logQ=Math.log10(Q);
  log(String.format("Input %d",combustibile));

	switch(combustibile){
		case Comb_1.COKE:
		case Comb_1.ANTRACITE:
		case Comb_1.LIGNITE:
    log("coke antracite lignite");
				if(Q<= 100.0){
					Pw=15*logQ;    // Tiraggio minimo
				}else if(Q <= 1000){
					Pw= -70+50*logQ;  // Tiraggio minimo
				}else{
					Pw=80;			// Tiraggio minimo
				}

			rend=68.65+4.35*logQ; //Rendimento  valido per potenze fino a 2000 kW
			if(Q<100){
				co2=9.5;            //CO2
			}else{
				co2=4.1+2.7*logQ;   //CO2
			}
			break;
		case Comb_1.LEGNO_23:
		case Comb_1.LEGNO_33:
		case Comb_1.PALLET :
log("legno pallet");
			if(Q<= 50.0){
					Pw=15*logQ; // Tiraggio minimo
				}else{
					Pw=27+13*logQ;  // Tiraggio minimo Valido fino a 350 kW
				}

			rend=51.6+8.4*logQ; // Rendimento valido per potenze fino a 1000 kW
			if(Q<10){
				co2=8.0;            //CO2
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
					if(combustibile==Comb_1.GAS_NAT_H || combustibile==Comb_1.GAS_NAT_L){
						fx1=8.6;
						fx2=0.078;
						fx3=10.2;
            log(String.format("Aria soffiata- GAS; fx1=%5.3f \t fx2=%5.3f\tfx3=%5.3f ",fx1,fx2,fx3));
					}else if(combustibile == Comb_1.GAS_LIQUIDO){
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
					if(combustibile==Comb_1.GAS_NAT_H || combustibile==Comb_1.GAS_NAT_L){
						fx1=5.1;
						fx2=0.075;
						fx3=6.0;
            log(String.format("Aria naturale - GAS NATURALE; fx1=%5.3f \t fx2=%5.3f\tfx3=%5.3f ",fx1,fx2,fx3));
					}else if(combustibile == Comb_1.GAS_LIQUIDO){
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
* Imposta temperatura fumi
*/
public void setTm(double _Tm){
	this.tm=_Tm;
}
public void setCo2(double _Co2){
	this.co2=_Co2;
}

public void setRend(double _rend){
	this.rend=_rend;
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

public double getCo2(){
  return this.co2;
}

public double getTm(){
  return this.tm;
}

public double getRend(){
  return this.rend;
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


void log(String s){
  if(!_LOG)
    return;
 System.out.println(s);
}
void Log(String s){
  System.out.println(s);
}

public void print(){
printHeader();
printValue();

}

public String getStringHeader(){
  return String.format("%30s|%10s|%10s|%10s|%10s|%10s|%10s|%10s|%10s|",
              "Combustibile","Q [kW]","Rend","CO2[%]","Pmass[g/s]","Tfumo °C","Tirmin[Pa]",
              "Aria soffiata","Aria naturale" );
}
public String getStringValue(){
  return String.format("%30s|%10.1f|%10.1f|%10.1f|%10.4f|%10.3f|%10.3f|%10s|%10s|",
                  Comb_1.combustibile[combustibile], Q,rend,co2,
                  portatamassica, tm,Pw,
                  (aria_soffiata?"SI":"NO"),
                  (aria_naturale?"SI":"NO")
                  );
}

public   void printHeader(){
    Log(  this.getStringHeader());
  }

public void printValue(){
      Log(this.getStringValue());
  }

}
