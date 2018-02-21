package com.antoiovi.calctermodin.panels.condotti;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import java.awt.GridLayout;
import java.util.List;

import com.antoiovi.calctermodin.panels.APanelTubo_2;

public class APanelCondPrimSec extends JPanel implements APCondotto{
APCondotto condotto_int;
APCondotto condotto_est;
JComboBox coboxsezest;
	/**
	 * Create the panel.
	 */
	public APanelCondPrimSec() {
		setLayout(new GridLayout(0, 2, 0, 0));
		
		APanelCond panel_int = new APanelCond();
		condotto_int=panel_int;
		add(panel_int);
		
		APanelCond panel_est = new APanelCond();
		condotto_est=panel_est;
		add(panel_est);
		condotto_int.setTipo("Primario");
		condotto_est.setTipo("Secondario");
		condotto_est.setTitolo("");
/**
 * CONDOTTO ESTERNO NON EDITABILE IL TIPO DISEZIONE
 */
		coboxsezest=panel_est.getComboBoxSezione();
		coboxsezest.setEnabled(false);
		/**
		 * se cambio il tipo di sezione del condotto interno cambia anche il tipo del condotto esterno
		 */
		condotto_int.addAPCondottoRealted(condotto_est);
		//coboxsezinte.setVisible(false);
	}
	/**
	 * IMPLEMENTAZIONE METODI APCondoitti
	 */
	@Override
	public void setTitolo(String arg0) {
condotto_int.setTitolo(arg0);
	}
	@Override
	public void setTipo(String arg0) {
	}
	@Override
	public void quadrato() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void rettangolare() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void circolare() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setAPCondottiRealted(List<APCondotto> apcondotti) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addAPCondottoRealted(APCondotto apcondotto) {
		// TODO Auto-generated method stub
		
	}

}
