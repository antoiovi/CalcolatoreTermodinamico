package com.antoiovi.unicig;
import com.antoiovi.unicig.condotti.MoodyDiagram;

/**************
 * 	Formule
 *	@author antoiovi
 * 
 *	Prima creazione 5/06/2018
 *
 ***********/
public class Formule {
	
	 
	public static final double g=9.81; // m s-2
	 /* Costante universale dei gas J/(K kmol) Ru=8314.472*/ 
	public static final double Ru=8314.472; 
	public static final double R_L=288.0; // Costante dell'aria J(kgxK)
	
	
	public static final double H2O_Mvol_4=1000.0;// kg/m3
	public static final double H2O_visc_cin_20=834.167e-6;// m2s-1
	
	public static final double Aria_Mvol_0=1.293;// kg/m3
	public static final double Aria_Mvol_10=1.247;// kg/m3
	public static final double Aria_Mvol_20=1.204;// kg/m3
	public static final double Aria_visc_cin_20=18e-6;// Pa s;   0.018 cP -> 1cP=1 mPa=0.001 Paxs
	public static final double Aria_visc_din_20=1.485e-5;// m2s-1
	
	
	
	
	public Formule(){
	}
	
	/**
	*@param visc_din in Pa x s
	* @param massaVol in kg/m3
	* @return viscosita dinamica in m2s-1
	**/
	public static double viscositaCinematica(double visc_din,double massaVol){
		return visc_din/massaVol;
	}
	
	public static double pressioneAtmosferica(double altitudine,double temperatura){
		double k=(-1*g*altitudine)/(R_L*temperatura);
		return 97000.0*Math.exp(k);
	}
	
	public static double pressioneAtmosferica(double altitudine){
		double k=(-1*g*altitudine)/(R_L*293.0);
		return 97000.0*Math.exp(k);
	}
	
	public static double coeffLiminEsterno(double frazioneEsterna){
		return frazioneEsterna*23+(1-frazioneEsterna)*8;
	}
	
 /**
  * Calcola fattore attrto liscio
  */  

	public static double FattAttrLiscio(double numeroReynolds,double scabrRel){
		if(  numeroReynolds <3000){
            return 64/numeroReynolds;
		}else{
             MoodyDiagram moodydiagr=new MoodyDiagram(numeroReynolds,scabrRel);
             moodydiagr.setEsd(0);// fattore attrito liscio con scabrezza=0
            return moodydiagr.zbrent();
           }
}   


	public static double FattAttrRuvido(double numeroReynolds,double scabrRel){
    	if(numeroReynolds<3000){
              return 64/numeroReynolds;
		}else{
			MoodyDiagram moodydiagr=new MoodyDiagram(numeroReynolds,scabrRel);
			return moodydiagr.zbrent();
          }
		}
 
 public static double coeffGlobaleScambioTermico(double coeffLiminareInetrno,double coeffLimEsterno,double Dh_int,double Dh_est,double ResistenzaTermica)
	{
        double k;
		k = (1 / coeffLiminareInetrno) + (((1 / coeffLimEsterno) * Dh_int / Dh_est) + ResistenzaTermica);
		return ( 1 / k);
     }
 
 
 public static  double fattoreRaffreddamento
		 (double coeffGlobaleScambioTermico, double perimetro,double lunghezza, double portataMassica,double capacitaTermicaFluido){
		double k=coeffGlobaleScambioTermico;
		 double U=perimetro;
		 double L=lunghezza;
		 double M=portataMassica;
		 double cp=capacitaTermicaFluido;
		 return ( U * k * L )/ (M * cp );
	 }
 

 public static double NumeroNusselt(double fattattrLiscio,double fattattrRuvido,double numeroReynolds){
        double psipsi0 = fattattrRuvido / fattattrLiscio;
		double a = Math.pow(psipsi0, 0.67);
        double b = Math.pow( numeroReynolds , 0.75);
        return ( a * 0.0354 * (b - 180));
	}
 
	
		/**
		*  Formula di Darcy
		**/
	public static double perditediCaricoLin(double massaVol,double velMedia,double fattattrRuvido, double lunghezza,double diamIdr){
		return  0.5 * massaVol * Math.pow(velMedia, 2)*(fattattrRuvido*lunghezza/diamIdr);
		}
	
	public static double perditediCaricoConc(double massaVol,double velMedia,double csi){
		return 0.5 * massaVol* Math.pow(velMedia, 2) * csi;
		}
	
	public static double pressDin(double massaVol,double velMedia){
		return  0.5 * massaVol * Math.pow(velMedia, 2);
		}
		
	/*********
	* Se Altezza è negativa; il carico statico sarà negativo (ovvero in discesa)
	************/
	public static double caricoStatico(double massaVol,double altezza){
		return 9.81*altezza*massaVol;
	}	
	  
    

	}//fine classe
