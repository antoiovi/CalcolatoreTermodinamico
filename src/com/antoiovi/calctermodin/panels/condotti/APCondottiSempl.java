package com.antoiovi.calctermodin.panels.condotti;

import javax.swing.JPanel;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.awt.FlowLayout;
import com.antoiovi.calctermodin.panels.APanelTubo;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
/**
 * N condotti semplici(non concentrici )di forma uguale ;
 * la forma viene cambiata da un comando che le cambia tutte
 * @author antoiovi
 *
 */
public class APCondottiSempl extends JPanel {
List<APanelCond> panels_cond;
int maxpanels=9;

	/**
	 * Create the panel.
	 */
	public APCondottiSempl() {
		panels_cond=new ArrayList<APanelCond>();
		for(int x=0;x<maxpanels;x++){
			APanelCond p=new APanelCond();
			panels_cond.add(p);
		}
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("181px"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("308px:grow"),}));
		APanelCondLabel panel_1 = new APanelCondLabel();
		add(panel_1, "1, 1, default, fill");
		
		int p1=2;
		for(int x=0;x<maxpanels;x++){
			System.out.println(p1);
			APanelCond ap=panels_cond.get(x);
			String s=p1+1+",1, default, fill";
			p1+=2;
			add(ap, s);
		}
				

	}/***
	*	FINE COSTRUTTORE
	*
	******/
	int npanels=maxpanels;
	/**
	 * imposta il numero di piani (numero di pannelli visibili)
	 * @param n
	 */
	public void setNumberPanels(int n){
		if(n<1 || n>maxpanels)
			return;
		npanels=n;
		int x=0;
		for(JPanel p:panels_cond){
			x++;
			if(x<=npanels)
				p.setVisible(true);
			else
				p.setVisible(false);
		}
	}
	


}
