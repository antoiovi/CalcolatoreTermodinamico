/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.antoiovi.unicig.fluidi;

/**
 *
 * @author antoiovi  Antonello Iovino
 */
public class PairFluidoDouble {
		public double value;
        public Fluido fluido;
      public  PairFluidoDouble(Fluido fluido,double value){
            this.value=value;
            this.fluido=fluido;
        }  
}
