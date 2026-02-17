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
public class CombustibiliFactory  {
	
	 static CombustibiliFactory instance=null;
 
 // Proprietà combustibili tabella B1
 
 static final int HU       =0; //Tenore di energia del combustibile
 static final int V_ATR_MIN=1; // relazione tra volumegas minimom 
 static final int VL_MIN   =2;
 static final int V_H2O    =3;
 static final int CO2_MAX  =4;
 static final int SO2_MAX  =5;
 
 // Combustibili
 static final int COKE       =0;
 static final int ANTRACITE  =1;
 static final int LIGNITE    =2;
 static final int RFO_4		 =3;
 static final int RFO_2		 =4;
  static final int RFO_1     =5;
 static final int RISC_DOM   =6;
 static final int CHEROSENE  =7;
 static final int GAS_NAT_H  =8;
 static final int GAS_NAT_L  =9;
 static final int GAS_LIQUIDO=10;
 static final int LEGNO_23   =11;
 static final int LEGNO_33   =12;
 static final int PALLET     =13;
 
 static final int NUM_COMB=14; // numero di combustibili
 
 static final int DEFAULT=GAS_NAT_L;

 public static final String combustibileName[]={"Coke"       ,"Carbone minerale(antracite)"             ,"Lignite"              ,"RFO <4% S"     ,     "RFO <2% S",
						"RFO < 1% S" ,"Combustibile per riscaldamento domestico","Cherosene"            ,"Gas naturale H","Gas naturale L",
						"Gas liquido","Legno (umidità 23.1 %)"                  ,"Legno (umidità 33,3%)","Pallet di legno"};
 

public static final int COL_HU = 0;          // Hu → Potere Calorifico Inferiore
public static final int COL_VATR_MIN = 1;    // Vatr min → Volume aria teorica minima
public static final int COL_VL_MIN = 2;      // VL min → Volume fumi secchi minimo
public static final int COL_VH2O = 3;        // Vh2O → Volume vapore acqueo nei fumi
public static final int COL_CO2_MAX = 4;     // CO2%max → Percentuale massima teorica di CO2
public static final int COL_SO2_MAX = 5;     // SO2%max → Percentuale massima teorica di SO2

public static final String[] COL_NAMES = {
    "Hu [MJ/kg o MJ/Nm3]",
    "Vatr min [Nm3/Nm3 o Nm3/kg]",
    "VL min [Nm3/Nm3 o Nm3/kg]",
    "Vh2O [Nm3/Nm3 o Nm3/kg]",
    "CO2 max [%]",
    "SO2 max [%]"
};

 // TabellaB1[Riga][Colonna]
 // Riga = combustibile ;colonna=proprieta
 public static final double DatiCombustibile[ ][]={
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
 
 double DatiCombustione[ ][]={
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
 
 Combustibile[] combustibili;
 
    private CombustibiliFactory() {
		combustibili=new Combustibile[NUM_COMB];
     	 for(int x=0;x<NUM_COMB;x++){
				combustibili[x]=   new Combustibile(combustibileName[x],
									DatiCombustibile[x],
									DatiCombustione[x]);
			}
     
    }
     
	  static public CombustibiliFactory getInstance(){
		  if(instance ==null){
			  instance=new CombustibiliFactory();
		  }
		  return instance;
	  }  
	
public Combustibile getNewCombustibile(int COMBUSTIBILE){
		if(COMBUSTIBILE<0 || COMBUSTIBILE> (NUM_COMB-1))
				COMBUSTIBILE=DEFAULT;
			return combustibili[COMBUSTIBILE];			}


			public static String[] getCombustibileName() {
				return combustibileName;
			}
			
			public static double[][] getDatiCombustibile() {
				return DatiCombustibile;
			}
			
		 
}
