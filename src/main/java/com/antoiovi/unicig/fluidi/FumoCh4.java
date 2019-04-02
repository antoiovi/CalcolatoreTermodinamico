package com.antoiovi.unicig.fluidi;

import java.util.ArrayList;
import java.util.List;

import com.antoiovi.unicig.fluidi.AzotoN2;
import com.antoiovi.unicig.fluidi.Fluido;
import com.antoiovi.unicig.fluidi.Miscela;
import com.antoiovi.unicig.fluidi.OssigenoO2;
import com.antoiovi.unicig.fluidi.PairFluidoDouble;
import com.antoiovi.unicig.fluidi.VaporeH2O;
import com.antoiovi.unicig.fluidi.AnidrideCO2;
/**
 * Dati che mi servono %CO2 %O2 Massa fumi temperatura fumi
 * se consoc Co2 e o2 come calcolo eccesso d'aria?
 * @author antoiovi
 *
 */
public class FumoCh4 {
	Fluido o2;
	Fluido co2;
	Fluido n2;
	Fluido h20;
	
	// Pressione e temperatura in "Condizioni Normali" 
	public static final double PressioneNormale=101325;
	public static final double TemperaturaNormale=273.15;
	public static final int ECC_ARIA=0;
	public static final int CO2_PERC=1;
private double co2_perc_secca;
private double co2_perc_umida;
private double o2_perc_secca;
private double o2_perc_umida;
private double n2_perc_umida;
private double n2_perc_secca;

double volumi_h20;
double volumi_co2;
double volumi_o2;
double  volumi_n2;
double  volumi_totali;


double m3_ch4;

private Miscela fumo;
private double potenza;
private double portataFumo;

private double ecc_aria;
private double ecc_aria_perc;
private double indice_aria;

public static final double pot_cal_inf=34.0;// MJ/m3	

public double getPortataFumo(){
	return portataFumo;
}
public Fluido getFumo(){
	return fumo;
}


/**
 * Crero un Miscela e restituisco una portatat:
 * Utilizzo chiamare la funzione, poi tramite getFumo ottenere il Fluido;
 * 
 * @param potenza kw
 * @param ecc_aria
 * @return
 */
public  FumoCh4(double potenza, double TFumi,double ECCARIA_CO2,int TIPO){	
	log("fumoch4");
this.potenza=potenza;
	o2=new OssigenoO2();
	co2=new AnidrideCO2();
	n2=new AzotoN2();
	h20=new VaporeH2O();
if(TIPO==ECC_ARIA){
	CalcolaEccAria_E(ECCARIA_CO2);
	log("calcola con eccaria");
}
else{
	CalcolaEccAria_CO2(ECCARIA_CO2);
	log("calcola con co2");
	}
	Volumi();
	Fumo();
	
	portataFumo=volumi_totali*fumo.MassaVolumica()*1000.0; // kg/s
	fumo.setTemperatura(TFumi);
	log(String.format("Fumo MassaVolumica()= %f  [kg/m3]",fumo.MassaVolumica()));
	
}

private void CalcolaEccAria_E(double ecc_aria){
		log("CalcolaEccAria_E");

//metri cubi metano Kw /1000=MJ -->/pci--> / MJ/m3
	/**
	 * m3 metri cubi(3) metano
	 */
	m3_ch4=potenza/(1000*pot_cal_inf);
//double m3_ch4=1;
	
	this.ecc_aria=ecc_aria;
	ecc_aria_perc=ecc_aria/100;
	indice_aria=1+ecc_aria_perc;
	log(String.format("Eccesso d aria %f    metri cubi ch4  %f [m3]",ecc_aria,m3_ch4));
	
}

private void CalcolaEccAria_CO2(double co2_secco_reale){
		log("CalcolaEccAria_CO2");
	/*if(co2_secco_reale>11.73)
	return -1;*/
	//metri cubi metano Kw /1000=MJ -->/pci--> / MJ/m3
	m3_ch4=potenza/(1000*pot_cal_inf);

	double tot=1/(co2_secco_reale/100) ;
	ecc_aria_perc=(tot-8.52)/9.52;
	this.ecc_aria=ecc_aria_perc*100;
	indice_aria=1+ecc_aria_perc;

}






private void Volumi(){
		log("VOLUMI");
	// Sono le frazioni di volume dei compnenti: 
	// infatti la legge dei gas è p*v=n RT
	// quindi a uguali volumi è dato lo stesso numero di molecole,
	// MA A AUGUALI CONDIZIONI DI PRESSIONE E TMEPRATURA;	
	// QUINDI PER CALOCLARE LA portata massica devo calcolare òa densità;
	// LA DENSITA(MAssaVolumicA) verra calcolata coin i valori riferiti al NormalMetrocubo
	// Ovvero 101
	volumi_h20=2*m3_ch4;
	volumi_co2=1*m3_ch4;
	volumi_o2=2*ecc_aria*m3_ch4;
	volumi_n2=(7.52+7.52*ecc_aria)*m3_ch4;
	// su fumi umidi   
	//7.52+7.52p+2p+1+2=tot    9.52p=tot-10.52   p=(tot-10.52)/9.52 
	// su fumi secchi   
	//7.52+7.52p+2p+1=tot    9.52p=tot-8.52   p=(tot-8.52)/9.52
	//Volumi totatli: ovvero la somma delle frazioni di volumi "specifici"
	volumi_totali=(volumi_h20+volumi_co2+volumi_o2+volumi_n2);
//	
	
}


private void Fumo(){
	log("FUMO");
	//String s=String.format("Potenza %f\t metricubi %f\t eccaria %f \n",potenza,m3_ch4,p);
	String s=String.format("v h20 %f\t vco2 %f\t vo2 %f\t vn2 %f \n",volumi_h20,volumi_co2,volumi_o2,volumi_n2);
	log(s);
	PairFluidoDouble vapore=new PairFluidoDouble(h20,volumi_h20);
	PairFluidoDouble anidrideco2=new PairFluidoDouble(co2,volumi_co2);
	PairFluidoDouble ossig=new PairFluidoDouble(o2,volumi_o2);
	PairFluidoDouble azoto=new PairFluidoDouble(n2,volumi_n2);
	List<PairFluidoDouble> listmoli=new ArrayList<PairFluidoDouble>();
	listmoli.add(anidrideco2);
	 listmoli.add(vapore);
	listmoli.add(azoto);
	listmoli.add(ossig);
	co2_perc_umida=volumi_co2/volumi_totali;
	
	co2_perc_secca=volumi_co2/(volumi_totali-volumi_h20);
	o2_perc_umida=volumi_o2/volumi_totali;
	o2_perc_secca=volumi_o2/(volumi_totali-volumi_h20);
	n2_perc_umida=volumi_n2/volumi_totali;
	n2_perc_secca=volumi_n2/(volumi_totali-volumi_h20);
	log("Creo miscela");
	fumo=new Miscela(listmoli,Miscela.FLUIDO_MOLI);
	
	
}

/**
 * Formua 49 uni10640 : calcola la temperatura fumi uscita generatore in base al rendimento ed
 * un coeffoiciente caratteristico
 * Gas natura = 0.95 Alla potenza nomina 1.80 Potenza nomina minima
 * GPL =		   1 Alla potenza nomina 1.80 Potenza nomina minima
 * Gas natura = 1.05 Alla potenza nomina 1.80 Potenza nomina minima
 * @param C
 * @param rend
 * @return
 */
public static double TemperaturaFumi(double C,double rend){
return 293.15+C*(1-rend)*1000 ;	
}

 
		 void log(String  str){
			 System.out.println("["+this.getClass().getName()+"]"+str);
		 }





}
