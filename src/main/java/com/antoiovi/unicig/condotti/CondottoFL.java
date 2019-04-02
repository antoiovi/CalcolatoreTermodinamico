/*
 * CondottoFL  FLuido
 *
 * 
 * Non tiene conto dei parametri termici :
 * vengono calcolate i fattori di attrito lisci e ruvido,
 * gli unici dati del fluido necessari sono : 
 * 		-massavolumica (densita)
 * 		viscosita cinematica (per calcolo numero Raynold)
 *		
 * 
 */

package com.antoiovi.unicig.condotti;
import com.antoiovi.unicig.tubi.*;
import com.antoiovi.unicig.fluidi.*;
import com.antoiovi.unicig.Formule;
/**
 * @author antoiovi
 */
public class CondottoFL {
	
	// PROPRIETA
	
	
	
	double csi=0.0;
	
	// Proprietà dal costruttore 
	//Misure in metri
	double area;
	double diamIdr;
 	double scabrRel;
	double lunghezza;
	double altezza;
	
 
	
	
	//Errore di macchina
	double epsilon=0.0001;
	
	//Dati calcolati
	double velMedia=0.0;
	double numeroReynolds=0.0;
	double fattattrLiscio=0.0;
	double fattattrRuvido=0.0;
    double numeroNusselt=0.0;
	
 	public CondottoFL(double _diamIdr ,double _area, 	double _scabrRel,double _lunghezza)
	{
		this.diamIdr=_diamIdr;
		this.area=_area;
		this.scabrRel=_scabrRel;
		this.lunghezza=_lunghezza;
		this.altezza=_lunghezza;
	}
	
	public CondottoFL(double _diam ,double _scabrRel,double _lunghezza)
	{
		this.diamIdr=_diam;
		double r=_diam/2;
		this.area=3.14*r*r;
		this.scabrRel=_scabrRel;
		this.lunghezza=_lunghezza;
		this.altezza=_lunghezza;
	}
/*
*
*            VENGONO CALCOLATI
*					velocita media
*                  numeroNusselt
*                  numeroReynolds
*             	   fattattrLiscio
*		  		   fattattrRuvido
* 			@param  _portata_masica : Portata in Kg/s 
*
*/	
	public  void calcolaParametri(double _portata_massica,double massa_volumica,double _visc_cin){
					
			velMedia= _portata_massica /(massa_volumica*area);
			numeroReynolds=diamIdr* velMedia /_visc_cin;
			fattattrLiscio=Formule.FattAttrLiscio(numeroReynolds, scabrRel);
			fattattrRuvido=Formule.FattAttrRuvido(numeroReynolds, scabrRel);
			numeroNusselt=Formule.NumeroNusselt(fattattrLiscio,fattattrRuvido,numeroReynolds);
		}
		
	
/**
*  Formula di Darcy
**/
public double perditediCaricoLin(double _massa_volumica){
	 return Formule.perditediCaricoLin(_massa_volumica, velMedia,  fattattrRuvido,lunghezza,diamIdr);
	}
	
public double perditediCaricoConc(double _massa_volumica){
	return 0.5 * _massa_volumica* Math.pow(velMedia, 2) * csi;
}
	
public double pressDin(double _massa_volumica){
	return  0.5 * _massa_volumica* Math.pow(velMedia, 2);
}

	/*********
	* Se Altezza è negativa; il carico statico sarà negativo (ovvero in discesa)
	************/
	public double caricoStatico(double _massa_volumica){
		return 9.81*altezza*_massa_volumica;
	}	  
	
/*****
*	SETTERS
******/


	// Proprietà dal costruttore 
	//Misure in metri
	
	public void setDiamIdrArea(double _diamInt,double _area){
		this.diamIdr=_diamInt;
		this.area=_area;
	}
		
	/**
	*	Per sezoni circolari
	****/
	public void setDiam(double _diam){
		this.diamIdr=_diam;
		double r=_diam/2;
		this.area=3.14*r*r;
	}
	
 	public void setScabrRel(double _scabRel){
		this.scabrRel=_scabRel;
	}
	
	public void setLunghezza(double _lunghezza ){
	this.lunghezza=_lunghezza;	
	double x=altezza<0?-1*altezza:altezza;
	int verso=altezza<0?-1:1;
		if(x<=lunghezza)
			return;
		this.altezza=lunghezza*verso;	
	}
	 /**
	* Altezza può essere negativa; il carico statico sarà negativo (ovvero in discesa)
	**/
	public void setAltezza(double _altezza){
		double x=_altezza<0?-1*_altezza:_altezza;
		if(x>lunghezza)
			return;
		this.altezza=_altezza;	
	}
	
	// proprieta varie
	public void setCsi(double _csi){
		if(_csi<0)
			return;
		this.csi=_csi;
	}
	
	
	
	
	
/***
*	GETTERS
****/

public double getCsi(){
		return csi;
	}
public double getArea(){	return area;	}
public double getDiamIdr(){	return diamIdr;	}
public double getScabrRel(){	return scabrRel;	}
public double getLunghezza(){	return lunghezza;	}
public double getAltezza(){	return altezza;	}
	
	 
 	 
	 
	 
	
	
	
	/***
	*	Valori calcolati
	*
	 getNumeroReynolds()
	 getFattattrLiscio()
	  getFattattrRuvido()
	 getNumeroNusselt()
	 getVelMedia()

	****/
	
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
		
   
}
    

