package com.antoiovi.unicig.condotti;
import com.antoiovi.unicig.tubi.*;
import com.antoiovi.unicig.fluidi.*;

/**
* @author antoiovi
* Aggiunge le proprieta
*
*		frazContatEsterno=1;
*  	tempEsterna;
*		frazioneEsterna;
*	pressione;
*	Aria_base aria=new Aria_base();
*		massaVolAria;
*/
public class CondottoCamino extends Condotto {
	/**
	* Usato per calcolo coefficiente liminare interno:
	* 'e il rapporto tra la porzione di perimetro esposta all'esterno ed il
	*	 perimetro totale;
	* vale 1 se il condotto è totalmente coeffLimEsterno
	* vale 0 se totalmente interno
	*/
	double frazContatEsterno;
/**
* Temperatura del locale interno ;
*/
double  tempInterna;

double tempEsterna;
double tempMediata;

/**
* Deve essere la pressione atmosferica
*/
	double pressione;
	Aria_base aria=new Aria_base();

	double massaVolAria;

	public CondottoCamino(Tubo _tubo,Fluido _fluido, double _portatamassica,double _frazContatEsterno ){
		super( _tubo, _fluido, _portatamassica,1);
		frazContatEsterno=_frazContatEsterno<0?0:_frazContatEsterno;
		frazContatEsterno=frazContatEsterno>1?1:frazContatEsterno;
		tempInterna=293.0;
		tempEsterna=293.0;
		this.setCoeffLimEsterno(coeffLiminEsterno(_frazContatEsterno));
		tempMediata=TempMeidata();
	}


	private double TempMeidata(){
		return tempInterna*(1-frazContatEsterno)+tempEsterna*frazContatEsterno;
	}

	public  double coeffLiminEsterno(double _frazContatEsterno){
		return frazContatEsterno*23+(1-frazContatEsterno)*8;
	}

	public void setFrazContatEsterno(double fraz){
		this.frazContatEsterno=fraz;
		coeffLimEsterno=coeffLiminEsterno(frazContatEsterno);
	}


/**
*   La temperatura del locale è di default 293 k (20°) ;
* come temperatura  esterna (per lo scambio termico)  si usa une temperatura
*				 mediata in base lall frazione di contatto esterno
* La temperatura esterna reale viene usata per il tiraggio impostandola
*		nel fluido ARIA;
* @param _Tinput : Temperatura del fluido all'imbocco
* @param _Testerna : Temperatura esterna
* @param _Pressione : pressone del fluido interno; se calcolo tiraggio uso pressione atmosferica;
*
*/
	@Override
	public void Calcolatemperature(double _Tinput,double _Testerna,double _pressione){
		this.pressione=_pressione;
		this.tempInterna=293.0;
		this.tempEsterna=_Testerna;
		this.tempMediata=TempMeidata();
		super.Calcolatemperature( _Tinput, tempMediata, pressione);

				// Per il calcolo del tiraggio
		aria.setTemperatura(tempEsterna);
		aria.setPressione(pressione);
	}

/**
* Con questa funzione cambio anche la temperatura interna del locale
* di solito si usa la funzione Calcolatemperature(double _Tinput,double _Testerna,double _pressione_)
*  che usa il valore di default di 20° (293 K)
* @param _TempInterna : Temperatura del locale interno
* @param _Tinput : Temperatura del fluido all'imbocco
* @param _Testerna : Temperatura esterna
* @param _Pressione : pressone del fluido interno; se calcolo tiraggio uso pressione atmosferica;
*/
	public void Calcolatemperature(double _TempInterna,double _Tinput,double _Testerna,double _pressione){
		this.tempInterna=_TempInterna;
		this.Calcolatemperature(_Tinput,_Testerna,_pressione);
	}


 	/**
	* Se Altezza è negativa; il carico statico sarà negativo (ovvero in discesa)
	* 'E il tiraggio teorico
	*/
	public double pressioneStatica(){
		return 9.81*altezza*(aria.MassaVolumica()- fluido.MassaVolumica() );
	}


}
