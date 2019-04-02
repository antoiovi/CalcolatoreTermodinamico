package com.antoiovi.unicig.fluidi.comb;


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
public class Comb_2  {



String nome;
double temperatura;
double pressione;
double CO2;
int COMB;

/**
*  Serve per calcolare l'aumento del punto di rugiada per combustibili acidi
* fattore di conversione da SO2 a SO3 in % ???
* Per i combustibili diversi da carbone ed olio combustibile
* qualunque valore viene moltiplicato per 0 (fs2 della tabella)
/* di default uso 2 (SO3% circa il 2% di SO2)Vedere 5.7.6 di norma UNI EN 13384
*/
double Kf=2;



 public Comb_2(int _combustibile) {
    this.COMB=_combustibile;
    this.nome=Comb_1.combustibile[_combustibile];
    this.pressione=101000.0;
 	}

  public Comb_2(int _combustibile,double _CO2,double _Temp) {
     this.COMB=_combustibile;
     this.nome=Comb_1.combustibile[_combustibile];
     this.temperatura=_Temp;
     this.CO2=_CO2;
     this.pressione=101000.0;
   }


   public void setPressione(double _pressione){
     this.pressione=_pressione;
   }


public void setTemperatura(double _Temp){
  this.temperatura=_Temp;
}

public void setCO2(double _CO2){
  this.CO2=_CO2;
}


  public String getNome(){
		return nome;
	}

	/**
	* Formula B.1 Prospetto B1 pag 67
  * portata massica in g/s
  * per avere i kg/s dividere per 1000.
	* @param Q portata termica apparecchio di riscaldamento in kW (utile)
	* @param rend rendimento apparecchio di riscaldamento
	* @param co2 tenore di co2% secco
	**/
	 public   double portataMassicaFumi(double Q,double rend){
		    return Comb_1.portataMassicaFumi(COMB,Q,rend,this.CO2);
	}
	/**
	*  Formula B.3 ( R )
	* @param co2 tenore di co2% secco
	* @return R in J/(kgxK)
	**/
  public  double CostElasticita_1(){
  		return  Comb_1.CostElasticita_1(COMB,this.CO2);
	}
  public  double CostElasticita_Cond(){
      return  Comb_1.CostElasticita_Cond(COMB,this.CO2);
  }



	/**
	*  Formula B.4 ( cp )
	* @param tm temperatura media fumi in Gradi Centigradi
	* @param co2 tenore di co2% secco
	* @return cp in J/(kgxK)
	**/
public   double CapTermica(){
    return Comb_1.CapTermica(COMB,temperatura,CO2);
  }

	/**
	*  Formula B.5 ( sigma(H2O) )
	* @param co2 tenore di co2% secco
	* @return sigma(H2O) tenore del vapore acqueo nei prodotti combustione in %
	**/
  public   double TenoreH2O(){
    return  Comb_1.TenoreH2O(COMB,this.CO2);
  }
	/**
	*  Formula B.6 ( PD) )
	* @param PL pressione dell'aria esterna in Pa
	* @param h20 sigma(H2O) tenore del vapore acqueo nei prodotti combustione in %
	* @return PD pressione parziale del vapore acqueo in Pa
	**/
  public  double PparzialeH2o(){
    return Comb_1.PparzialeH2o(this.TenoreH2O(),this.pressione);
	}

	/**
	*  Formula B.7 ( tp )
	* @param PD pressione parziale del vapore acqueo in Pa
	*  Vedere punto 5.7.6 della norma UNI 13384:
  *     Utilizza B.5 , B.6, B.7 ;
  *   Se combustibili acidi Bisogna aggiungere B.8
	* @return tp temperatura punto di rugiada in °C
	**/
  public  double tempPuntoRugiada(){
    return Comb_1.tempPuntoRugiada(this.PparzialeH2o());
	}


	/**
	*  Formula B.8 ( delta tsp )
	* @param Kf fattore di conversione da SO2 a SO3 in % ???
	*  Vedere punto 5.7.6 della norma UNI 13384:
  *    Per il legno vale 15;
	* @return aumento del punto di rugiada in Kelvin
	**/
	public   double deltaTsp(){
 		return Comb_1.deltaTsp(COMB,this.Kf);
	}

	/**
	*  Formula B.9 ( lambda A )
	* @param double tm temperatura media prodotti combustione °C
	*
	* @return coefficiente di conducibilità termica in W/(m x K)
	**/
  public   double lambdaA(){
    return  Comb_1.lambdaA(temperatura);
  }


	/**
	*  Formula B.10 ( eta A viscosità dinamica )
	* @param double tm temperatura media prodotti combustione °C
	*
	* @return viscosità dinamica dei prodotti della combustione  in N s/m2
	**/
  public  double viscDin(){
 			return  Comb_1.viscDin(temperatura);
	}

	/**
	*  Formula B.11 ( R )
	* @param co2 tenore di co2% secco
	* @param h2o tenore di vapore acqua
	*
	* per calcolare la massa molare considerare che
	* 	Costante universale dei gas J/(K kmol) Ru=8314.472;
    *        J/(K kmol) x 1/(kg/kmole)= J/(kg K)
			massamolare=Ru/R;
			Ru= J/(K kmol) DIVISO J/(kg K)  =kg/kmole


	 @return Costante dei gas J/(kg K)
    */
	public   double CostElasticita_2(){
		return Comb_1.CostElasticita_2(COMB, this.CO2,this.TenoreH2O());
	}

	/*
    * Costante universale dei gas J/(K kmol) Ru=8314.472;

            J/(K kmol) x 1/(kg/kmole)= J/(kg K)
	 Ru=8314.472 J/(K kmol)
	 R= 8314.472/28.013= J/(K Kg)

	 @return Costante dei gas J/(kg K)
    */

	/**
	*  Formula B.12 ( sigma(H2O) )
	* @param PD pressione parziale del vapore acqueo in Pa
	* @param PL pressione dell'aria esterna in Pa
	* @return sigma(H2O) tenore del vapore acqueo nei prodotti combustione in %
	**/
	public    double TenoreH2O(double PL){
		return 100*this.PparzialeH2o()/PL;
	}


	/**
	*  Formula B.13 ( PD )
	* @param  tp temperatura punto di rugiada in °C
	*
	* @return PD pressione parziale del vapore acqueo in Pa
	**/
	public   double PparzialeH2O_Tr(){
	return Comb_1.PparzialeH2o(this.tempPuntoRugiada());
	}


  void log(String s){
  System.out.println(s) ;
  }

  public String getStringHeader(){
    return String.format("%30s|%10s|%10s|%10s||%10s|%10s|%10s|%10s|%10s|%10s|%10s|%10s|%13s|%13s|",
    "Combustible","Temp(°C)","Patm(Pa)","CO2%","RnoCond","Rcond","Cp[J/(kgXK)]","H2O%","PD[Pa]","Tp[°C]","dTp[K]","lA[W/(mxK)]","Vd[Nxs/m2]","PparzATempRug");
  }
  public String getStringValue(){
    return String.format("%30s|%10.3f|%10.3f|%10.3f||%10.3f|%10.3f|%10.3f|%10.3f|%10.3f|%10.3f|%10.3f|%10.3f|%13.3f|%13.3f|",
      nome,temperatura,pressione,CO2,
        CostElasticita_1(),CostElasticita_Cond(),CapTermica(),TenoreH2O(),PparzialeH2o(),
        tempPuntoRugiada(),deltaTsp(),lambdaA(),viscDin(),PparzialeH2O_Tr()
    );
  }



  public void print(){
    String header1= String.format("%30s|%10s|%10s|%10s||%10s|%10s|%10s|%10s|%10s|%10s|%10s|%10s|%13s|%13s|",
    "Combustible","Temp(°C)","Patm(Pa)","CO2%","RnoCond","Rcond","Cp ","H2O%","PD[Pa]","Tp[°C]","dTp[K]","lAmbda","Vd[Nxs/m2]","PparzATempRug");

    String dati=String.format("%30s|%10.3f|%10.0f|%10.3f||%10.3f|%10.3f|%10.3f|%10.3f|%10.3f|%10.3f|%10.3f|%10.3e|%13.3e|%13.3f|",
      nome,temperatura,pressione,CO2,
        CostElasticita_1(),CostElasticita_Cond(),CapTermica(),TenoreH2O(),PparzialeH2o(),
        tempPuntoRugiada(),deltaTsp(),lambdaA(),viscDin(),PparzialeH2O_Tr()
    );
  log(header1);
  log(dati);
  }



 @Override
    public String toString() {
        return nome;
    }


}
