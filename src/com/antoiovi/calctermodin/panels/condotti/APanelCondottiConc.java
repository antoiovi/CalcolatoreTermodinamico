package com.antoiovi.calctermodin.panels.condotti;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import com.jgoodies.forms.factories.FormFactory;

public class APanelCondottiConc extends JPanel {
	List<APanelCondConc> panels_cond;
	int maxpanels=9;
	/**
	 * Create the panel.
	 */
	public APanelCondottiConc() {
		setLayout(new FormLayout(new ColumnSpec[] {
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
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("10px"),},
			new RowSpec[] {
				RowSpec.decode("207px:grow"),
				RowSpec.decode("10px"),}));

		panels_cond=new ArrayList<APanelCondConc>();
		for(int x=0;x<maxpanels;x++){
			APanelCondConc p=new APanelCondConc();
			panels_cond.add(p);
		}
		APanelCondLabel panel_1 = new APanelCondLabel();
		add(panel_1, "1, 1, default, fill");
		
		int p1=2;
		for(int x=0;x<maxpanels;x++){
			System.out.println(p1);
			APanelCondConc ap=panels_cond.get(x);
			String s=p1+1+",1, default, fill";
			p1+=2;
			add(ap, s);
		}
		
	}
	/**
	 * FINE COSTRUTTORE
	 */
	
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
