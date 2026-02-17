package com.antoiovi.unicig.condotti;
import com.antoiovi.unicig.tubi.*;
import com.antoiovi.unicig.fluidi.*;
import com.antoiovi.unicig.Formule;

/**
 * @author antoiovi
*   La classe deve essere inizzializzata:
*  -  Con una classe Tubo che Contiene    le dimensioni
*  -  Con un Fluido per avere le proprieta in funzione della temperatura
*  -  Con la portata massica del fluido
*  - con il coefficiente liminare esterno
* si possono poi cambiare alcuni valori, quali:
*     - csi perdite concentarte (defalt 0.5)
*     -altezza
*    -SH
*   -SE
*  Una volta chiamato il costruttore ed inizzializzata la classe si può chiamare
*  il metodo Calcolatemperature(double Tinput,double Testerna,double pressione_)
*  tramite il quale vengono calcolate le temperature medie ,iniziale e finale
*
*/
public class Condotto {

	Tubo tubo;
	Fluido fluido;

	double tempInput;
	double portataMassica;

	double csi=0.0; // Perdote di carico concentrate

	double coeffLimEsterno;

	double temperaturaMedia;
	double temperaturaUscita;

	double velMedia;
	double numeroReynolds;
	double fattattrLiscio;
	double fattattrRuvido;
 	double numeroNusselt;
	double coeffLiminInterno;
	double coeffglobalescambterm;
	double fattoreraffreddamento;



	double SH=0.5;// Usato per coefficiente globale di scambio termico
	double SE=1.2; // Usato per le perdite di carico
	double epsilon=0.000001; // Precisione minima usato in calcola temperatura

	double altezza;

	public Condotto(Tubo _tubo,Fluido _fluido, double _portatamassica,double _coeffLimEsterno){
		this.tubo=_tubo;
		this.altezza=tubo.getLunghezza();
		this.fluido=_fluido;
		this.portataMassica=_portatamassica;
		coeffLimEsterno=_coeffLimEsterno;

 	}



/**
* @param Pressione : pressone del fluido interno; Serve per calcolare i parametri del fluido
*                    che dipendono dalla pressione
*                   se calcolo tiraggio di un camino uso pressione atmosferica;
*
**/
	public void Calcolatemperature(double Tinput,double Testerna,double pressione_){
		double diamIdr=tubo.Dh_int();
		double diamIdrE=tubo.Dh_est();
		double perimetro=tubo.Per_int();
		double lunghezza=tubo.getLunghezza();
		double area=tubo.Area_int();
		double scabrRel=tubo.getRugosita()/tubo.Dh_int();
		double resTerm=tubo.getResistenzaTermica();
		double capTerm;
		double condTerm;
		double massaVol;
		double viscCin;

		double tempEsterna=Testerna;
		tempInput=Tinput;

		temperaturaMedia=0.9*Tinput;
		fluido.setPressione(pressione_);
		double temperaturaMedia_1=temperaturaMedia;
		fluido.setTemperatura(temperaturaMedia);
        do{

			 capTerm=fluido.CapTerm();
			 condTerm=fluido.CondicTermica();
			 massaVol=fluido.MassaVolumica();
			 viscCin=fluido.ViscCin();

			velMedia= portataMassica /(massaVol* area);
			log(String.format("velmedia %f\tportata massica %f\tmassavolumica %f\tarea [m2] %f",velMedia,
			portataMassica,massaVol,area));
			numeroReynolds=diamIdr* velMedia /viscCin;
			fattattrLiscio=Formule.FattAttrLiscio(numeroReynolds, scabrRel);
			fattattrRuvido=Formule.FattAttrRuvido(numeroReynolds, scabrRel);
			numeroNusselt=Formule.NumeroNusselt(fattattrLiscio,fattattrRuvido,numeroReynolds);

			coeffLiminInterno= (condTerm*numeroNusselt/diamIdr)<5?5:(condTerm*numeroNusselt/diamIdr);

			coeffglobalescambterm=SH*Formule.coeffGlobaleScambioTermico(coeffLiminInterno,coeffLimEsterno, diamIdr,diamIdrE,resTerm);
			//Fattore di raffreddamento formula 24
			double	KR =Formule.fattoreRaffreddamento( coeffglobalescambterm,  perimetro, lunghezza,  portataMassica,capTerm);
			// Temperatura uscita caso non coassiale, formula 26
			temperaturaUscita = tempEsterna + (tempInput - tempEsterna) * Math.exp(-1 * KR);
			// Temperatura media nel condotto caso non coassiale formula 29
			temperaturaMedia  =    tempEsterna +( tempInput- tempEsterna)*(1-Math.exp(-1 * KR))/KR;
			fluido.setTemperatura(temperaturaMedia);
			double deltaT= temperaturaMedia_1-temperaturaMedia;
				if (deltaT<epsilon)
					break;
				temperaturaMedia_1=temperaturaMedia;
		}while(true);
}




	public double perditediCarico(){
		return SE*(perditediCaricoLin()+perditediCaricoConc());
	}

	/**
	*  Formula di Darcy
	*/
	public double perditediCaricoLin(){
		return  0.5 * fluido.MassaVolumica() * Math.pow(velMedia, 2)*(fattattrRuvido*tubo.getLunghezza()/tubo.Dh_int());
	}

/**
* Calcola le perdite di carico concentarte
*/
  public double perditediCaricoConc(){
		return 0.5 * fluido.MassaVolumica() * Math.pow(velMedia, 2) * csi;
	}
	/*********
	* Se Altezza è negativa; il carico statico sarà negativo (ovvero in discesa)
	************/
	public double caricoStatico(){
		return 9.81*altezza*fluido.MassaVolumica();
	}

 /***********************************************************************
 *		SETTERS e GETEERS PROPRIETA
 ***********************************************************************/
  public void setFluido(Fluido fluido){  this.fluido=fluido;  }
  public Fluido getFluido(){return this.fluido;}
  public void setTubo(Tubo _tubo){this.tubo=_tubo;}
  public Tubo getTubo(){return this.tubo;}
  public void setPortatamassica(double _portatamassica){
	  if(_portatamassica<=0.0)
		  return;
	  this.portataMassica=_portatamassica;}
public double getPortatamassica(){return this.portataMassica;}
   public void setCsi(double _csi){
	   if(_csi<0.0)
		   return;
	   this.csi=_csi;
   };
public double getCsi(){return this.csi;}
   public void setCoeffLimEsterno(double _coeffLimEsterno){
	   if(_coeffLimEsterno<1.0)
		   return;
	   this.coeffLimEsterno=_coeffLimEsterno;
   };
 public double getCoeffLiminEsterno(){return this.coeffLimEsterno;}
	/**
	* Altezza può essere negativa; il carico statico sarà negativo (ovvero in discesa)
	**/
	public void setAltezza(double _altezza){
		double x=_altezza<0?-1*_altezza:_altezza;
		if(x>tubo.getLunghezza())
			return;
		this.altezza=_altezza;
	}

	public void set_SE(double _se){
		if(_se<=0.0)
			return;
		this.SE=_se;
	}
	public void set_SH(double _sh){
		if(_sh<=0.0)
			return;
		this.SH=_sh;
	}


		void log(String s){
			System.out.println(s);
		}
 /***********************************************************************
 *		GETTERS VALORI CALCOLATI
 ***********************************************************************/


	public double getTemperaturaInput(){return tempInput;}

	public double getTemperaturaMedia(){
	return temperaturaMedia;
	}

	public double getTemperaturaUscita(){
		return temperaturaUscita;
	}


	public double getNumeroReynolds(){
		 return numeroReynolds ;
	 }

	public double getFattattrLiscio(){
		 return  fattattrLiscio;
	 }

	 public double getFattattrRuvido(){
		 return  fattattrRuvido;
	 }
	 public double getNumeroNusselt(){
		 return numeroNusselt ;
	 }
	public double getVelMedia()	  {
		return velMedia;
	}

	public double getCoeffLiminInterno(){return	coeffLiminInterno;}
	public double getCoeffglobalescambterm(){return coeffglobalescambterm;}
	public double getFattoreraffreddamento(){return fattoreraffreddamento;}



}
