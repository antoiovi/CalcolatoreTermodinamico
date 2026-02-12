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
public class Combustibile  {

 // Proprietà combustibili tabella B1
 public static final int COL_HU = 0;          // Hu → Potere Calorifico Inferiore
 public static final int COL_VATR_MIN = 1;    // Vatr min → Volume aria teorica minima
 public static final int COL_VL_MIN = 2;      // VL min → Volume fumi secchi minimo
 public static final int COL_VH2O = 3;        // Vh2O → Volume vapore acqueo nei fumi
 public static final int COL_CO2_MAX = 4;     // CO2%max → Percentuale massima teorica di CO2
 public static final int COL_SO2_MAX = 5;     // SO2%max → Percentuale massima teorica di SO2
 

// DATI COMBUSTIONE
 static final int FM1      =0;
 static final int FM2      =1;
 static final int FR_senza =2;
 static final int FR_con =3;
 static final int FR1    =4;
 static final int FR2    =5;
 static final int FC0    =6;
 static final int FC1    =7;
 static final int FC2    =8;
 static final int FC3    =9;
 static final int FW     =10;
 static final int FS1    =11;
 static final int FS2    =12;


 public static final String combustibile[]={"Coke"       ,"Carbone minerale(antracite)"             ,"Lignite"              ,"RFO <4% S"     ,     "RFO <2% S",
						"RFO < 1% S" ,"Combustibile per riscaldamento domestico","Cherosene"            ,"Gas naturale H","Gas naturale L",
						"Gas liquido","Legno (umidità 23.1 %)"                  ,"Legno (umidità 33,3%)","Pallet di legno"};


 // TabellaB1[Riga][Colonna]
 // Riga = combustibile ;colonna=proprieta
 // Dati Combustibile
 double D_C[];
 // Dati Fumi (Combustione)
 double D_F[];

 String nome;

 public Combustibile(String nome,double[] DatiCombustibile,double[] DatiCombustione) {
		 this.nome=nome;
		 this.D_C=DatiCombustibile;
		 this.D_F=DatiCombustione;
 	}

	public String getNome(){
		return nome;
	}

	public double[] getDatiCombustibile(){
		return D_C;
	}
	public double[] getDatiCombustione(){
		return D_F;
	}

	/**
	* Formula B.1 Prospetto B1 pag 67
  * portata massica in g/s
  * per avere i kg/s dividere per 1000.
	* @param Q portata termica apparecchio di riscaldamento in kW (utile)
	* @param rend rendimento apparecchio di riscaldamento
	* @param co2 tenore di co2% secco
	**/

	 public double portataMassicaFumi(double Q,double rend,double co2){
		double Qf=100*Q/rend;
		return (D_F[FM1]/co2 +D_F[FM2])*Qf;
	}

	/**
	*  Formula B.3 ( R )
	* @param co2 tenore di co2% secco
	* @return R in J/(kgxK)
	**/

	public double CostElasticita_1(double co2){
		return 288.0*(1+D_F[FR1]*co2);
	}


	/**
	*  Formula B.4 ( cp )
	* @param tm temperatura media fumi in Gradi Centigradi
	* @param co2 tenore di co2% secco
	* @return cp in J/(kgxK)
	**/

	public double CapTermica(double tm,double co2){
		double tm2=tm*tm;
		double cp=1011.0+0.05*tm+0.0003*tm2+(D_F[FC0]+D_F[FC1]*tm+D_F[FC2]*tm2)*co2;
		return cp/(1+D_F[FC3]*co2);
	}


	/**
	*  Formula B.5 ( sigma(H2O) )
	* @param co2 tenore di co2% secco
	* @return sigma(H2O) tenore del vapore acqueo nei prodotti combustione in %
	**/

	public double TenoreH2O(double co2){
		double h2o=1+D_F[FW]/co2;
		return 100/h2o+1.1;
	}

	/**
	*  Formula B.6 ( PD) )
	* @param PL pressione dell'aria esterna in Pa
	* @param h20 sigma(H2O) tenore del vapore acqueo nei prodotti combustione in %
	* @return PD pressione parziale del vapore acqueo in Pa
	**/
	public double PparzialeH2o(double h2o,double PL){
		return h2o*PL/100;
	}

	/**
	*  Formula B.7 ( tp )
	* @param PD pressione parziale del vapore acqueo in Pa
	*
	* @return tp temperatura punto di rugiada in °C
	**/
	public double tempPuntoRugiada(double PD){

		return (4077.9/(23.6448-Math.log(PD)))-236.67;
	}

	/**
	*  Formula B.8 ( delta tsp )
	* @param Kf fattore di conversione da SO2 a SO3 in % ???
	*
	* @return aumento del punto di rugiada in Kelvin
	**/
	public double deltaTsp(double Kf){
 		return  D_F[FS1]+D_F[FS2]*Math.log(Kf);
	}

	/**
	*  Formula B.9 ( lambda A )
	* @param double tm temperatura media prodotti combustione °C
	*
	* @return coefficiente di conducibilità termica in W/(m x K)
	**/
	public double lambdaA(double tm){
 		return  0.0223+0.000065*tm;
	}

	/**
	*  Formula B.10 ( eta A viscosità dinamica )
	* @param double tm temperatura media prodotti combustione °C
	*
	* @return viscosità dinamica dei prodotti della combustione  in N s/m2
	**/
	public double viscDin(double tm){
 		double tm2=tm*tm;
		return  15e-6+47e-9*tm-20e-12*tm2;
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
	public double CostElasticita_2(double co2,double h2o){
		return 288.0*(0.096+D_F[FR1]*h2o+D_F[FR2]*(1-h2o/100)*co2);
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
	public double TenoreH2O(double PD,double PL){
		return 100*PD/PL;
	}


	/**
	*  Formula B.13 ( PD )
	* @param  tp temperatura punto di rugiada in °C
	*
	* @return PD pressione parziale del vapore acqueo in Pa
	**/
	public double PparzialeH2o(double tp){
		double exp=(23.6448-(4077.9/(tp-36.48)));
		return Math.exp(exp);
	}



 @Override
    public String toString() {
        return nome;
    }


}
