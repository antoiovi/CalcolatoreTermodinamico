package com.antoiovi.calctermodin.panels;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.GridLayout;

public class PanelTest extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelTest() {
		
		APanelTubo_lbl panel_1 = new APanelTubo_lbl();
		add(panel_1);
		setLayout(new GridLayout(0, 4, 0, 0));
		
		APanelTubo_in panel = new APanelTubo_in();
		add(panel);
		APanelTubo_in panel_3 = new APanelTubo_in();
		add(panel_3);
		
		JPanel panel_2 = new APanelTubo_lbl();
		add(panel_2);

	}

}
