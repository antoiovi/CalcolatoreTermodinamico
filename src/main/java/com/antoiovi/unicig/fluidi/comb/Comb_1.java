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
public class Comb_1  {
  // Combustibili
  public static final int COKE       =0;
  public static final int ANTRACITE  =1;
  public static final int LIGNITE    =2;
  public static final int RFO_4		 =3;
  public static final int RFO_2		 =4;
   public static final int RFO_1     =5;
  public static final int RISC_DOM   =6;
  public static final int CHEROSENE  =7;
  public static final int GAS_NAT_H  =8;
  public static final int GAS_NAT_L  =9;
  public static final int GAS_LIQUIDO=10;
  public static final int LEGNO_23   =11;
  public static final int LEGNO_33   =12;
  public static final int PALLET     =13;

 // Proprietà combustibili tabella B1

 static final int HU       =0; //Tenore di energia del combustibile
 static final int V_ATR_MIN=1; // relazione tra volumegas minimom
 static final int VL_MIN   =2;
 static final int V_H2O    =3;
 static final int CO2_MAX  =4;
 static final int SO2_MAX  =5;


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


 // TabellaB1[Riga][Colonna]
 // Riga = combustibile ;colonna=proprieta
static  final double D_C[ ][]={
          { 8.06, 7.64, 7.66, 0.13,20.60, 0.09},//Riga 0
          { 9.24, 8.37, 8.55, 0.44,19.05, 0.10},
          { 5.42, 5.09, 5.17, 0.68,19.48, 0.04},
          { 9.43, 9.91,10.48, 1.15,16.17, 0.28},// 3
          { 9.61,10.06,10.67, 1.21,16.15, 0.14},
          { 9.74,10.17,10.79, 1.25,16.09, 0.07},
          {11.86,10.52,11.26, 1.49,15.40, 0.00}, // 6
          {12.09,11.36,12.14, 1.57,15.00, 0.00},
          {10.03, 8.67, 9.57, 1.86,12.00, 0.00},
          { 9.03, 7.87, 8.63, 1.70,11.80, 0.00}, //9
          {26.67,22.46,24.51, 4.10,13.80, 0.00},
          { 3.70, 3.44, 3.45, 0.80,20.50, 0.00},
          { 3.12, 2.98, 2.99, 0.86,20.50, 0.00}, //12
          { 5.27, 4.78, 4.81, 0.78,20.31, 0.00}
    };


static  final double D_F[ ][]={
    { 7.06, 0.033, -0.0036, -0.0038, 0.0036,-0.0040, 3.4,0.014,-0.000014,0.0046,  1.235,99.0,7.0 }, //0
    { 6.23, 0.036, -0.0028, -0.0033, 0.0036,-0.0039, 5.6,0.014,-0.000013,0.0057,370.0  ,93.0,7.0},
    { 6.61, 0.055, -0.0014, -0.0026, 0.0037,-0.0040,10.3,0.015,-0.000012,0.0083,149.0  ,80.0,7.0},

    { 6.14, 0.052, -0.0012, -0.0024, 0.0037,-0.0039,10.7,0.014,-0.000012,0.0082,142.0,94.0,7.0}, // 3
    { 6.11, 0.052, -0.001 , -0.0023, 0.0037,-0.0038,11.0,0.014,-0.000011,0.00083,137.0,89.0,7.0},
    { 6.07, 0.052, -0.0009, -0.0022, 0.0037,-0.0038,11.2,0.014,-0.000011,0.00084,134.0,85.0,7.0},

    { 4.94, 0.046, -0.0002, -0.0018, 0.0038,-0.0037,13.0,0.014,-0.000011,0.0093,111.0, 0.0, 0.0}, //6
    { 5.09, 0.047, -0.0002, -0.0018, 0.0038,-0.0036,13.0,0.014,-0.000011,0.0093,111.0, 0.0, 0.0},
    { 3.75, 0.053, -0.0032,  0.0002, 0.0039,-0.0032,23.0,0.015,-0.000007,0.0142, 57.0, 0.0, 0.0},

    { 3.72, 0.054, -0.0033,  0.0003, 0.0039,-0.0032,23.5,0.015,-0.000007,0.0144, 56.0, 0.0, 0.0}, //9
    { 4.20, 0.049, -0.0013, -0.0009, 0.0038,-0.0035,17.6,0.015,-0.000009,0.0116, 77.0, 0.0, 0.0},
    { 6.89, 0.076, -0.0001, -0.0018, 0.0038,-0.0041,15.4,0.016,-0.000011,0.0111, 90.0,15.0, 0.0},

    { 7.08, 0.090,  0.001, -0.0013, 0.0038,-0.0042,18.5,0.016,-0.000010,0.0128, 72.0,15.0, 0.0}, //12
    { 6.66, 0.060, -0.001, -0.0024, 0.0037,-0.0041,11.6,0.015,-0.000012,0.0091,127.0,15.0, 0.0}
  };



 public static final String combustibile[]={"Coke"       ,"Carbone minerale(antracite)"             ,"Lignite"              ,"RFO <4% S"     ,     "RFO <2% S",
						"RFO < 1% S" ,"Combustibile per riscaldamento domestico","Cherosene"            ,"Gas naturale H","Gas naturale L",
						"Gas liquido","Legno (umidità 23.1 %)"                  ,"Legno (umidità 33,3%)","Pallet di legno"};



 public Comb_1() {

 	}

	/**
	* Formula B.1 Prospetto B1 pag 67
  * portata massica in g/s
  * per avere i kg/s dividere per 1000.
	* @param Q portata termica apparecchio di riscaldamento in kW (utile)
	* @param rend rendimento apparecchio di riscaldamento
	* @param co2 tenore di co2% secco
	**/

	 public  static double portataMassicaFumi(int COMB,double Q,double rend,double co2){
		double Qf=100*Q/rend;
    return (D_F[COMB][FM1]/co2 +D_F[COMB][FM2])*Qf;
	}

	/**
	*  Formula B.3 ( R )
	* @param co2 tenore di co2% secco
	* @return R in J/(kgxK)
	**/

	public  static double CostElasticita_1(int COMB,double co2){
    //log(String.format("Comb_1 Costante elasticita; D_F[COMB][FM1]=%f ",D_F[COMB][FM1]));
		return 288.0*(1+D_F[COMB][FR_senza]*co2);
	}
  public  static double CostElasticita_Cond(int COMB,double co2){
      return 288.0*(1+D_F[COMB][FR_con]*co2);
  }

	/**
	*  Formula B.4 ( cp )
	* @param tm temperatura media fumi in Gradi Centigradi
	* @param co2 tenore di co2% secco
	* @return cp in J/(kgxK)
	**/

  public  static  double CapTermica(int COMB,double tm,double co2){
		double tm2=tm*tm;
		double cp=1011.0+0.05*tm+0.0003*tm2+(D_F[COMB][FC0]+D_F[COMB][FC1]*tm+D_F[COMB][FC2]*tm2)*co2;
		return cp/(1+D_F[COMB][FC3]*co2);
	}


	/**
	*  Formula B.5 ( sigma(H2O) )
	* @param co2 tenore di co2% secco
	* @return sigma(H2O) tenore del vapore acqueo nei prodotti combustione in %
	**/
	public static  double TenoreH2O(int COMB,double co2){
		double h2o=1+D_F[COMB][FW]/co2;
		return 100/h2o+1.1;
	}

	/**
	*  Formula B.6 ( PD) )
	* @param PL pressione dell'aria esterna in Pa
	* @param h20 sigma(H2O) tenore del vapore acqueo nei prodotti combustione in %
	* @return PD pressione parziale del vapore acqueo in Pa
	**/
  	public  static double PparzialeH2o(double h2o,double PL){
		return h2o*PL/100;
	}

	/**
	*  Formula B.7 ( tp )
	* @param PD pressione parziale del vapore acqueo in Pa
	*
	* @return tp temperatura punto di rugiada in °C
	**/

	public static double tempPuntoRugiada(double PD){
	   return (4077.9/(23.6448-Math.log(PD)))-236.67;
	}

	/**
	*  Formula B.8 ( delta tsp )
	* @param Kf fattore di conversione da SO2 a SO3 in % ???
	*
	* @return aumento del punto di rugiada in Kelvin
	**/
	public   static double deltaTsp(int COMB,double Kf){
 		return  D_F[COMB][FS1]+D_F[COMB][FS2]*Math.log(Kf);
	}

	/**
	*  Formula B.9 ( lambda A )
	* @param double tm temperatura media prodotti combustione °C
	*
	* @return coefficiente di conducibilità termica in W/(m x K)
	**/

  public static  double lambdaA(double tm){
 		return  0.0223+0.000065*tm;
	}

	/**
	*  Formula B.10 ( eta A viscosità dinamica )
	* @param double tm temperatura media prodotti combustione °C
	*
	* @return viscosità dinamica dei prodotti della combustione  in N s/m2
	**/
  public static double viscDin(double tm){
 			return  15e-6+47e-9*tm-20e-12*tm*tm;
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
	public  static double CostElasticita_2(int COMB,double co2,double h2o){
		return 288.0*(0.096+D_F[COMB][FR1]*h2o+D_F[COMB][FR2]*(1-h2o/100)*co2);
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
	public static   double TenoreH2O(double PD,double PL){
		return 100*PD/PL;
	}


	/**
	*  Formula B.13 ( PD )
	* @param  tp temperatura punto di rugiada in °C
	*
	* @return PD pressione parziale del vapore acqueo in Pa
	**/
	public static  double PparzialeH2o(double tp){
		double exp=(23.6448-(4077.9/(tp-36.48)));
		return Math.exp(exp);
	}

  public static void print(int COMB){
    String header1= String.format("%10s|%10s|%10s|%10s|%10s|%10s|",
    "Hu","Vatr min","VL min","Vh20","CO2%max","SO2%max");
    String header2= String.format("%10s|%10s|%10s|%10s|%10s|%10s|%10s|%10s|%10s|%10s|%10s|%10s|%10s|",
    "fm1","fm2","fr senza","fr con","fr1","fr2","fc0","fc1","fc2","fc3","fw","fs1","fs2");

    String daticomb=String.format("%10.3f|%10.3f|%10.3f|%10.3f|%10.3f|%10.3f|",
    D_C[COMB][0],D_C[COMB][1],D_C[COMB][2],D_C[COMB][3],D_C[COMB][4],D_C[COMB][5]
    );

    String datifumo=String.format("%10.3f|%10.3f|%10.4f|%10.4f|%10.4f|%10.4f|%10.1f|%10.3f|%10.7f|%10.4f|%10.3f|%10.3f|%10.3f|",
    D_F[COMB][0],D_F[COMB][1],D_F[COMB][2],D_F[COMB][3],D_F[COMB][4],D_F[COMB][5],
    D_F[COMB][6],D_F[COMB][7],D_F[COMB][8],D_F[COMB][9],D_F[COMB][10],D_F[COMB][11],
    D_F[COMB][12]
    );
System.out.println(combustibile[COMB]);
System.out.println(header1);
System.out.println(daticomb);
System.out.println(header2);
System.out.println(datifumo);

  }

  /*******************************************
  *		LOG
  ********************************************/
static   void log(String s){
    System.out.println(s);
  }
}
