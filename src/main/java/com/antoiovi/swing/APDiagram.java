package com.antoiovi.swing;

import com.antoiovi.swing.APanelDiagram.Punto;

public interface APDiagram {
	/**
	 * Imposta i valori dell'asse x; intanto inizzializza il vettore per creare
	 * le stringhe che è separato per permettere operazioni sui dati senza
	 * modificarli
	 *  20/12/2014: aggiunto setFormatNumberAxisX(..
 *          aggiunto setFormatNumberAxisY(..
 *         per differnziare il formato valori asse x  e asse y
	 * @param x_axis
	 */
	public void setX_axis(double[] x_axis);

	public double[] getX_axis();

	/**
	 * * Imposta i valori dell'asse Y; intanto inizzializza il vettore per
	 * creare le stringhe che è separato per permettere operazioni(per la
	 * visualizzazione) sui dati senza modificarli
	 * 
	 * @param y_axis
	 */
	public void setY_axis(double[] y_axis);

	public double[] getY_axis();

	/*
	 * Valori da utilizzare per la stringa: vengono modificati in base alla
	 * opzione di visualizzazione senza alterare i dati originali
	 */
	public double[] getX_axis_str();

	public double[] getY_axis_str();

	public void setX_axis_str(double[] x_axis_str);

	public void setY_axis_str(double[] y_axis_str);

	public double getMargin();

	public void setMargin(int margin);

	public int getMargin_left();

	public int getMargin_bottom();

	public int getAdaptxscale();

	public int getAdaptyscale();

	public int getAdaptxyscale();

	public int getAdaptscaleblocked();

	/**
	 * AGGIUNGE UN PUNTO DI CUI DISEGNARE LE ASISE E ORDINATE
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean addPunto(double x, double y);

	public boolean addPunto(Punto p);

	/**
	 * Elimina tutti i punti di cui vedere le coordinate
	 */
	public void clearPunti();

	/**
	 * Stringa di testo, da aggiungere alla lista di stringhe da visulizzare
	 * contiene il testo e le coordinate per eliminarle tutte chiamare
	 * clearStringhe() Usare addStringa(Tring,x,y) clearStringhe();
	 * 
	 * 18/12/2014 Aggiunto formaatAxisY e formatAxisX
	 * 			usando setFormatAxis(str) li imposta tutte e due uguali
	 * @author Anto
	 * 
	 */
	public void setStringa(int index, String str);

	public boolean addStringa(String str, double x, double y);

	/**
	 * eliminoo le stringhe visualizzate
	 */
	public void clearStringhe();

	/**
	 * per consentire l'utilizzo senza menu
	 */
	public void disabilitaMenu();

	public void abilitaMenu();

	public boolean isMenu_abilitated();

	public double getX_scale();

	public void setX_scale(double x_scale);

	public double getY_scale();

	public void setY_scale(double y_scale);

	public int getAdapt_scale();

	public void setAdapt_scale(int adapt_scale);

	/**
	 * 
	 * @param functions
	 */
	public void setFunctions(double[] functions);

	/**
	 * Imposta il formato di visualizzazione delle etichette degli assi
	 * 
	 * @param formatNumberAxis
	 */
	public void setFormatNumberAxis(String formatNumberAxis);

	public String getFormatNumberAxis();

	public String getFormatNumberAxisX();

	public void setFormatNumberAxisX(String formatNumberAxisX);

	public String getFormatNumberAxisY();

	public void setFormatNumberAxiY(String formatNumberAxiY);

}
