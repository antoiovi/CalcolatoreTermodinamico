/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antoiovi.unicig.fluidi;

import java.util.List;
import java.util.Map;

/**
 *
 * @author antoiovi
 */
public interface FluidoI {
  public double CostElasticita();
  public double MassaMolare();
  
  public double MassaVolumica( );
  public double CapTerm( );// da tabella
  public double CondicTermica( );// da tabella
  public double DiffusivitaTermica( );// da tabella
  public double ViscositaDinamica( );
  // Dipende dalla densita quindi passo anche la pressione per il calcolo della densita
  public double ViscCin();
     // Rapporto tra diffusdivita cinematica e diffudsivita termica (adimensionale)
  public double NumeroPrandtl( );
   
  
}
