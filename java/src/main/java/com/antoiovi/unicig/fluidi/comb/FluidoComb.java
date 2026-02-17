package com.antoiovi.unicig.fluidi.comb;

import com.antoiovi.unicig.fluidi.Fluido;
import com.antoiovi.unicig.Formule;

import java.util.List;
import java.util.Locale;
import java.util.Map;
 import java.math.BigDecimal;
/**
 *
 * @author Antonello iovino antoiovi@antoiovi.com
 * @version 1.0
 *
 */
public class FluidoComb extends Fluido  {

double massamolare; // kg/kmole

double R; //Costante dei gas J/(kg K)


double CO2;

 Combustibile comb;


 public FluidoComb(Combustibile _comb,double co2) {
		 this.comb=_comb;
		 this.CO2=co2;
		 //Costante dei gas J/(kg K)
		 R=comb.CostElasticita_1(co2);
		 //R=Ru/massamolare;  --> massamolare=Ru/R;
		 //Ru= J/(K kmol) DIVISO J/(kg K)  =kg/kmole
		 massamolare=Ru/R;

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
* @return cp in J/(kgxK)
********* */
@Override
public double CapTerm() {
	/**
	*  Formula B.4 ( cp )
	* @param tm temperatura media fumi
	* @param co2 tenore di co2% secco
	**/
	return comb.CapTermica(temperatura,CO2);
}



/**
24/06/2015
 **/
@Override
public double CondicTermica(){

	/**
	*  Formula B.9 ( lambda A )
	* @param double tm temperatura media prodotti combustione °C
	*
	* @return coefficiente di conducibilità termica in W/(m x K)
	**/
	return comb.lambdaA(temperatura-273.0);
	}



/**

*  @return  viscosita dinamica im Pa*s
**/
@Override
public double ViscositaDinamica(){

	 /**
	*  Formula B.10 ( eta A viscosità dinamica )
	* @param double tm temperatura media prodotti combustione °C
	*
	* @return viscosità dinamica dei prodotti della combustione  in N s/m2
	**/
	return comb.viscDin(temperatura-273.0);
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
  return comb.CostElasticita_1(this.CO2);

 }



	/**
	* Formula B.1 Prospetto B1 pag 67
	* @param Q portata termica apparecchio di riscaldamento in kW (utile)
	* @param rend rendimento apparecchio di riscaldamento
	*   co2 tenore di co2% secco E INCAPSULATO IN FLuidoComb
	**/

	 public double portataMassicaFumi(double Q,double rend)
	 {
	return comb.portataMassicaFumi(Q,rend,CO2);

	}

	/**
	*  Formula B.5 ( sigma(H2O) )
	* @param co2 tenore di co2% secco
	* @return sigma(H2O) tenore del vapore acqueo nei prodotti combustione in %
	**/

	public double TenoreH2O(){
		return comb.TenoreH2O(CO2);

	}

	/**
	*  Formula B.6 ( PD) )
	* @param PL pressione dell'aria esterna in Pa
	* @param h20 sigma(H2O) tenore del vapore acqueo nei prodotti combustione in %
	* @return PD pressione parziale del vapore acqueo in Pa
	**/
	public double PparzialeH2o_from_Paria(){
		double h2o=this.TenoreH2O();
		return h2o*this.pressione/100;
	}

	/**
	*  Formula B.7 ( tp )
	* @param PD pressione parziale del vapore acqueo in Pa
	*
	* @return tp temperatura punto di rugiada in °C
	**/
	public double tempPuntoRugiada(){
        double PD =this.PparzialeH2o_from_Paria();
        // log --> ln(PD)
        return (4077.9/(23.6448-Math.log(PD)))-236.67;
	}

	/**
	*  Formula B.8 ( delta tsp )
	* @param Kf fattore di conversione da SO2 a SO3 in % ???
	*
	* @return aumento del punto di rugiada in Kelvin
	**/
	public double deltaTsp(double Kf){
 		return 0.0;
	}



	/**
	*  Wrapper di Combustibile.CostElasticita_2(double co2,double h2o)
  *  Formula B.11 ( R )
	* con   param co2 tenore di co2% secco
	* e con param h2o tenore di vapore acqua
		* @return R in J/(kgxK)
	**/
	public double CostElasticita_2(){
		return comb.CostElasticita_2(this.CO2,this.TenoreH2O());
	}


	/**
	*  Formula B.12 ( sigma(H2O) )
  * Wrapper di double TenoreH2O(double PD,double PL)
		return 100*PD/PL;
  * con   param PD pressione parziale del vapore acqueo in Pa
	* e con param PL pressione dell'aria esterna in Pa
	* @return sigma(H2O) tenore del vapore acqueo nei prodotti combustione in %
  *       come rapporto 100*Pd/Pl
	**/
	public double TenoreH2O(double PL){
    double PD=this.PparzialeH2o_from_Trugiada();
    return comb.TenoreH2O( PD,PL);
	}


	/**
	*  Formula B.13 ( PD )
	* @param  tp temperatura punto di rugiada in °C
	*
	* @return PD pressione parziale del vapore acqueo in Pa
	**/
	public double PparzialeH2o_from_Trugiada(){
    double tp=this.tempPuntoRugiada();
		double exp=(23.6448-(4077.9/(tp-36.48)));
		return Math.exp(exp);
	}



 @Override
    public String toString() {
        return comb.getNome();
    }


}
