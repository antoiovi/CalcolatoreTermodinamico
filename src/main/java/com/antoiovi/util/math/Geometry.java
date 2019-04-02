package com.antoiovi.util.math;

public class Geometry {
/**
 * Genera una scala che inizia da init e finisce con end,
 * i valori sono distanziati di step; 
 * @param init
 * @param end
 * @param step passo di distanza dei punti. S è un INT allora viene chiaata l'altra funzione che 
 * 		divide la scala in n pezzi uguali
 * @return
 */
	public static double[] Scale(double init, double end, double step) {
		if (step>=(end-init))
		return null;
		int x = (int) ((end - init) / step + 1 );
		//x=x<1?10:x;
		double a[] = new double[x];
		//double s = 0;
		a[0]=init;
		for (int y = 1; y < x; y++) {
			a[y] = a[y-1] + step;
			//s = a[y];
		}
		return a;
	}
	/**
	 * GENERA UNA SCALA di number elementi che inizia da init e finisce con end
	 * @param init
	 * @param end
	 * @param number NUMERO DI ELEMENTI I CUI DIVIDERE LA SCALA. Se è double allora viene chiaat 
	 * 				l'altra funzione , in cui rappresenta il passo
	 * @return
	 */
	public static double[] Scale(double init, double end, int number) {
		//int x = (int) ((end - init) / number + 1 / number);
double step=(end-init)/number;
step=step<0?-step:step;
		double a[] = new double[number+1];
		a[0]=init;
		for (int y = 1; y < number+1; y++) {
			a[y] = a[y-1] + step;
			//s = a[y];
		}
		return a;
	}
	/**
	 * Trasla il vettore data di t, sommando a ogni valore di data t
	 * @param data
	 * @param t
	 */
	public static void Traslation(double[] data,double t){
		for(int x=0;x<data.length;x++){
			data[x]+=t;
		}
		
		return;
	}
	/**
	 * Crea un nuovo array di copia
	 * @param data
	 * @return
	 */
	public static double[] CreteNewArray(double[] data){
		double array[]=new double[data.length];
		for(int x=0;x<data.length;x++){
			array[x]=data[x];
		}
		
		return array;
	}
	
	public static double[] TraslationGetNewArray(double[] data,double t){
		double array[]=new double[data.length];
		for(int x=0;x<data.length;x++){
			array[x]=data[x]+t;
		}
		
		return array;
	}
	/**
	 * 
	 * @param data
	 * @param t
	 */
	public static void Moltiply(double[] data,double t){
		//double array[]=new double[data.length];
		for(int x=0;x<data.length;x++){
			data[x]*=t;
		}
		
		return ;
	}
	/**
	 * Restituisce l'indice del maggiore valore dell'array data
	 * @param data
	 * @return
	 */
	public static int getMax(double data[]){
		int m=0;
		for(int x=1;x<data.length;x++){
			m=data[x]>data[x-1]?x:m;
		}
		
		return m ;
	}
/**
 * Restituisce l'indice del minor valore dell'array data
 * @param data
 * @return
 */
	public static int getMin(double data[]){
		int m=0;
		for(int x=1;x<data.length;x++){
			m=data[x]<data[x-1]?x:m;
		}
		
		return m ;
	}


	public static double[] ScaleLog10(double init, double end, double step) {
		int x = (int) ((end - init) / step + 1 / step);

		double a[] = new double[x];
		double s = init;
		for (int y = 0; y < x; y++) {
			a[y] = Math.log10(s);
			s = s + step;
		}
		return a;
	}

	/**
	 * Restituisce un array di double che contiene una scala logaritmica
	 * dal primo logaritmo che contiene init, fiono al successivo che contine end
	 * per esempio init = 25 -->a[0]=10
	 * 				end = 125 -->a[last]=1000
	 * init deve essere minore di end
	 * @param init	 deve essere minore di end e diverso da zero
	 * @param end				deve essere diverso da 0
	 * @return
	 */
	public static double[] ScaleLog10(double init, double end) {
		long iStart = (long) Math.log10(init);
		long iEnd = (long) Math.log10(end);
		double fPart = Math.log10(end) - iEnd;
		// se maggiore di un multiplo della base(10) arrotonda il limite al multiplo
		// successivo
		if (fPart > 0.00001)
			iEnd++;
		// numero di logaritmi
		int z = (int) iEnd - (int) iStart;
		double a[] = new double[z * 9 + 1];

		try {
			int k = 0;
			for (int x = 0; x < z; x++) {
				double d = Math.pow(10, x + iStart);
				// Il ciclo delle y finisce con 8, 
				//  10+ 10 20 30 40 50 60 70 80 
				//y     1  2  3  4  5  6  7  8 
				//quindi il ciclo yinizia con 20 e finisce con 90
				// quindi quando x=0 k=0 e a[k]=10, dopo k+1 per fare il 100
				if (x == 0) {
					a[k] = Math.log10(d);
					} else {
					a[k + 1] = Math.log10(d);
				}
				for (int y = 1; y < 9; y++) {
					// diecine+unita, - decine precedenti, in quanto 
					//	la fine di una diecina è l'inizio della successiva
					k = 10 * x + y - x;
					double dd = d + y * d;
					a[k] = Math.log10(dd);
				}
			}
			a[k + 1] = z + iStart;
		} catch (Exception e) {
			return null;
		}
		return a;
	}
	/**
	 * Crea un nuovo array con i valori di un altro array di cui viene calcolato il Log10
	 * @param data array contenennte i valorri di cui si vuole calcolare il logaritmo; non viene odificato
	 * @return un nuovo array di double con il Log10 di data
	 */
	public static double[] LogTransformationNewArray(double data[]){
		double array[]=new double[data.length];
		for(int x=0;x<data.length;x++){
			array[x]=Math.log10(data[x]);
		}
		return array;
	}
	/**
	 * Trasforma i valori in array in logaritmic value base 10
	 * @param data	l'array di valory di cui si vuole il log. Viene modificato con il valore Log10
	 */
	public static void LogTransformation(double data[]){
	
		for(int x=0;x<data.length;x++){
			data[x]=Math.log10(data[x]);
		}
		return ;
	}
	public static void Exp10(double data[]){
		
		for(int x=0;x<data.length;x++){
			data[x]=Math.pow(10,data[x]);
			}
		return ;
	}
	
}
