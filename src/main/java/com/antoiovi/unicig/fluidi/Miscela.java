/*
 * Autore Antonello Iovino
 */

 package com.antoiovi.unicig.fluidi;
 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Viene conseiderata una miscela come somma di coppie (frazione PONDERALE,
 * FLUIDO) Tutte le propietà vengono calcolate cme media pesata con le frazioni
 * PONDERALI 20/08/2014 : Da verificare se alcune propietà vanno considerate con
 * la frazione MOLARE ... Da rivedere il calcolo della costante universale
 * R(costanteElasticità, in quanto dipende dalle frazioni molari)
 *
 * @author antoiovi
 */
public class Miscela extends Fluido {

    
	
	public static final int FLUIDO_MASSA=1;
	public static final int FLUIDO_MOLI=2;
	double massamolareapparente;
	
 	/**
     * Creo la miscela passando una lista di coppie <massa-fluido>
     *
     * @param pair_massa_fluido
     */
    public Miscela(List<PairFluidoDouble> pair_fluido_value, int tipo) {
		if(tipo==FLUIDO_MASSA)
			CreaDaListFluidoMassa(pair_fluido_value);
		else
			CreaDaListFluidoMoli(pair_fluido_value);
		
}

	/*************************************************************************************************
	* Creo la miscela passando una lista di coppie <Fluido_MASSA>
	*************************************************************************************************/
	private void CreaDaListFluidoMassa(List<PairFluidoDouble> pair_fluido_massa ){
		    double molitotali=0;

		double  massatotale=0;
         // Calcolo la massa totale
        for(PairFluidoDouble fluido_massa:pair_fluido_massa){
			massatotale+=fluido_massa.value;
			fluido_massa.fluido.setTemperatura(temperatura);
			fluido_massa.fluido.setPressione(pressione);
			
		}
// CREO HASMMAP CON FRAZIONI PONDERALI ELEMENTI BASE
        map_p = new HashMap<Fluido, Double>();
        for(PairFluidoDouble fluido_massa:pair_fluido_massa){
		// Per ogni fludo prendo gli elemneti base ne calcolo la massa e li aggiungo alla nuova map_ponderale		
            double massa = fluido_massa.value;
            Fluido fluido = fluido_massa.fluido;
            Map fraz_mass_elementibase=fluido.getMap_p();
			
			for (Map.Entry<Fluido, Double> entry : fluido.getMap_p().entrySet()) {
            double massabase= massa*entry.getValue();
			Fluido fluidobase=entry.getKey();
			
			if(this.map_p.containsKey(fluidobase)){
				this.map_p.put(fluidobase,this.map_p.get(fluidobase)+massabase);			
			}else{
				map_p.put(fluidobase,massabase);			
			}
			
        }
	 }
// Trasformo i valori degli elementi basi in frazioni massiche
			for (Map.Entry<Fluido, Double> entry : this.map_p.entrySet()) {
				map_p.put(entry.getKey(),entry.getValue()/ massatotale);			
			}

// Creo la mappa Molare
		this.map_m = new HashMap<Fluido, Double>();
//Per ogni elemento base calcolo il numero di moli 	moli=massa/fluido.massamolare (dove massa =massaTotale*frazionePonderale)	

			for (Map.Entry<Fluido, Double> entry : this.map_p.entrySet()) {
            double frazMassa= entry.getValue();
			double massa=frazMassa*massatotale;
			Fluido fluidobase=entry.getKey();
			double moli=massa/fluidobase.MassaMolare();
			 molitotali+=moli;
			this.map_m.put(fluidobase,moli);			
			}

// Trasformo in frazioni molari
// (Con le frazioni molari posso dire per ogni  VOLUME quante parti di volume di elemento posseggo)
			for (Map.Entry<Fluido, Double> entry : this.map_m.entrySet()) {
				this.map_m.put(entry.getKey(),entry.getValue()/molitotali);			
			}

      // CALCOLO LA MASSA MOLARE APPARENTE
        // = soommatoria di frazionemolare_i x Massamolare_i
        massamolareapparente=0;
        for (Map.Entry<Fluido, Double> entry : map_m.entrySet()) {
            massamolareapparente+=((entry.getKey().MassaMolare())*entry.getValue());
        }
		
		// Calcolo la temperatura
		Temperatura();
}
	
	
private void log(String str){
	//System.out.println(str);
}

	/*************************************************************************************************
     * Creo la miscela passando una lista di coppie <Fluido_MOLI>
	 **************************************************************************************************/

	private void CreaDaListFluidoMoli(List<PairFluidoDouble> pair_fluido_moli) {
		double molitotali=0;
		double massatotale=0;
		// Calcolo le moli totale
		for(PairFluidoDouble fluido_moli:pair_fluido_moli){
			molitotali+=fluido_moli.value;
			fluido_moli.fluido.setTemperatura(temperatura);
			fluido_moli.fluido.setPressione(pressione);
		}
// CREO HASMMAP CON FRAZIONI MOLARI ELEMENTI BASE
        map_m = new HashMap<Fluido, Double>();
		for(PairFluidoDouble fluido_moli:pair_fluido_moli){
			 double moli = fluido_moli.value;
            Fluido fluido = fluido_moli.fluido;
			log(String.format("AGGIUNGO FLUIDO %10s MOLI %3.3f",fluido,moli));
			for (Map.Entry<Fluido, Double> entry : fluido.getMap_m().entrySet()) {
				String.format("\t entry.getKey() %10s entry.getValue() %3.3f",entry.getKey(),entry.getValue());
				double molibase= moli*entry.getValue();
				Fluido fluidobase=entry.getKey();
				if(map_m.containsKey(fluidobase)){
					map_m.put(fluidobase,map_m.get(fluidobase)+molibase);
				}else{
					map_m.put(fluidobase,molibase);			
				}
        }
	 }
	 
// Trasformo i valori degli elementi basi in frazioni molari
			for (Map.Entry<Fluido, Double> entry : map_m.entrySet()) {
				log(String.format("entry.getKey() %10s entry.getValue() %3.3f",entry.getKey(),entry.getValue()));
				double fr=entry.getValue()/ molitotali;
				log("Frazione molare fluido entry.getKey():" +entry.getKey()+" uguale a "+fr);
				map_m.put(entry.getKey(),entry.getValue()/molitotali);			
				log("Frazione molare fluido entry.getKey():" +entry.getKey()+" uguale a "+fr);
			}
// Creo la mappa Ponderale
		map_p = new HashMap<Fluido, Double>();
		log("Creazione maooa ponderale ");
//Per ogni elemento base calcolo la massa  	moli*fluido.massamolare=massa (dove moli =molitotali*frazioneMolare)	
			for (Map.Entry<Fluido, Double> entry : map_m.entrySet()) {
			log(String.format("\t entry.getKey() %10s entry.getValue() %3.3f",entry.getKey(),entry.getValue()));
            double frazMolare= entry.getValue();
			double moli=frazMolare*molitotali;
			Fluido fluidobase=entry.getKey();
			log(String.format("\t Frazione molare fluido entry.getKey() %10s : %3.3f",entry.getKey(),frazMolare));
			double massa=moli*fluidobase.MassaMolare();
			 massatotale+=massa;
			 log(String.format("\t Fluido %10s  frazione molare %3.3f  numero moli %5.3f massa  %5.3f|",fluidobase,frazMolare,moli,massa));
     		map_p.put(fluidobase,massa);
				
			}
			log(String.format("Massa totale %f :",massatotale));
		log("tRASFORMO IN FRAZIONI PONDERALI");
		// Trasformo in frazioni ponderali
			for (Map.Entry<Fluido, Double> entry : map_p.entrySet()) {
				log(String.format("\t entry.getKey() %10s entry.getValue() %3.3f",entry.getKey(),entry.getValue()));
				map_p.put(entry.getKey(),entry.getValue()/massatotale);
	
			}

			// CALCOLO LA MASSA MOLARE APPARENTE
        // = soommatoria di frazionemolare_i x Massamolare_i
	massamolareapparente=0;
        for (Map.Entry<Fluido, Double> entry : map_m.entrySet()) {
            massamolareapparente+=((entry.getKey().MassaMolare())*entry.getValue());
        }
		Temperatura();
}

	private void Temperatura(){
	temperatura=0;
	int x=0;
	 for (Map.Entry<Fluido, Double> entry : map_p.entrySet()) {
			Fluido fluido=entry.getKey();
			temperatura+=fluido.CapTerm()*entry.getValue()*fluido.getTemperatura();
        if(x==3)
			return;
		}
	while(true){
		double T=temperatura/this.CapTerm();
		if(T-temperatura<0.001)
			break;
		temperatura=T;
	}
	}
	
	/**************************************************************************
	*   Set Temperatura
	**************************************************************************/
	@Override
	public void setTemperatura(double temperatura){
		this.temperatura=temperatura;
		 for (Map.Entry<Fluido, Double> entry : map_p.entrySet()) {
			Fluido fluido=entry.getKey();
			fluido.setTemperatura(temperatura);
        }
		for (Map.Entry<Fluido, Double> entry : map_m.entrySet()) {
			Fluido fluido=entry.getKey();
			fluido.setTemperatura(temperatura);
        }
	}
	
	@Override
	public void setPressione(double pressione){
		this.pressione=pressione;
		for (Map.Entry<Fluido, Double> entry : map_p.entrySet()) {
			Fluido fluido=entry.getKey();
			fluido.setPressione(pressione);
        }
		for (Map.Entry<Fluido, Double> entry : map_m.entrySet()) {
			Fluido fluido=entry.getKey();
			fluido.setPressione(pressione);
        }
	}
	
    /**
     * Calcola la pressione parziale del fluido gas; se il gas non e  presente restituisce -1
     * Questo metodo non e presente in fluido, ma solo in Miscela, quindi per usarlo devo
     * fare attensione e fare un casting;
     * @param gas
     * @return
     */
    public double PressioneParzial(Fluido gas,double pressione){
    	if(map_m.get(gas)==null)
    		return -1;
    	else
    		return map_m.get(gas)*pressione;
    }
    
    @Override
    public double CostElasticita() {
        return Ru / MassaMolare();
    }

    
    /**
     * @return
     */
    @Override
    public double MassaMolare() {
       return massamolareapparente;
    }

    
    @Override
    public double CapTerm() {
        // media pesata frazioni ponderali elementi base
        double cp2=0;
        for (Map.Entry<Fluido, Double> entry : map_p.entrySet()) {
             cp2+=entry.getKey().CapTerm()*entry.getValue();
        }
         return cp2;
    }


    @Override
    public double CondicTermica() {
        double cp2=0;
        for (Map.Entry<Fluido, Double> entry : map_p.entrySet()) {
             cp2+=entry.getKey().CondicTermica()*entry.getValue();
        }
        return cp2;
    }
 


//Media frazioni ponderali componenti
    @Override
    public double ViscositaDinamica() {
        double vd=0;
        for (Map.Entry<Fluido, Double> entry : map_p.entrySet()) {
             vd+=entry.getKey().ViscositaDinamica()*entry.getValue();
        }
        return vd;
    }

   

    
    
        
    

    @Override
    public String toString() {
String formula="(";      

 
  
 // Frazioni ponderali
        for (Map.Entry<Fluido, Double> entry : map_p.entrySet()) {
        double q=100*entry.getValue();
            String sval=String.format("%.2f", q);
            formula+=sval+"%"+
            entry.getKey().toString()+" + ";
        }
        formula+=" )peso; (";
                //  FRAZIONI MOLARI
        for (Map.Entry<Fluido, Double> entry : map_m.entrySet()) {
            double q=100*entry.getValue();
            String sval=String.format("%.2f", q);
            formula+=sval+"% "+
            entry.getKey().toString()+", ";
        }
        formula+=") Moli; ";
                return formula; 
	 
    }
    
public double getFrazioneMolare(Fluido f){
 Double mol=map_m.get(f);
 if(mol!=null)
	 return mol;
 else
	return 0;
	
}
public double getFrazionePonderale(Fluido f){
	 Double mass=map_p.get(f);
	 if(mass!=null)
		 return mass;
	 else
		return 0;
		
	}
}
