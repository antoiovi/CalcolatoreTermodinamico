package com.antoiovi.calctermodin;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.LayoutManager;

public final class APCondotto {
	/**
	 * @wbp.factory
	 * @wbp.factory.parameter.source arg0 lblNewLabel
	 * @wbp.factory.parameter.source arg0_1 comboBoxSezione
	 * @wbp.factory.parameter.source arg0_2 lblNewLabel_1
	 * @wbp.factory.parameter.source arg0_3 textDiametro
	 * @wbp.factory.parameter.source arg0_4 lblNewLabel_2
	 * @wbp.factory.parameter.source arg0_5 textLatoA
	 * @wbp.factory.parameter.source arg0_6 lblNewLabel_3
	 * @wbp.factory.parameter.source arg0_7 textLatoB
	 * @wbp.factory.parameter.source arg0_8 lblNewLabel_4
	 * @wbp.factory.parameter.source arg0_9 textSpessore
	 * @wbp.factory.parameter.source arg0_10 lblNewLabel_5
	 * @wbp.factory.parameter.source arg0_11 textRugosita
	 * @wbp.factory.parameter.source arg0_12 lblNewLabel_6
	 * @wbp.factory.parameter.source arg0_13 textResterm
	 */
	public static JPanel createJPanel(Component arg0, Component arg0_1, Component arg0_2, Component arg0_3, Component arg0_4, Component arg0_5, Component arg0_6, Component arg0_7, Component arg0_8, Component arg0_9, Component arg0_10, Component arg0_11, Component arg0_12, Component arg0_13) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		panel.add(arg0);
		panel.add(arg0_1);
		panel.add(arg0_2);
		panel.add(arg0_3);
		panel.add(arg0_4);
		panel.add(arg0_5);
		panel.add(arg0_6);
		panel.add(arg0_7);
		panel.add(arg0_8);
		panel.add(arg0_9);
		panel.add(arg0_10);
		panel.add(arg0_11);
		panel.add(arg0_12);
		panel.add(arg0_13);
		return panel;
	}
	/**
	 * @wbp.factory
	 * @wbp.factory.parameter.source layout new java.awt.GridLayout(0, 2, 0, 0)
	 * @wbp.factory.parameter.source arg0 lblNewLabel
	 * @wbp.factory.parameter.source arg0_1 comboBoxSezione
	 * @wbp.factory.parameter.source arg0_2 lblNewLabel_1
	 * @wbp.factory.parameter.source arg0_3 textDiametro
	 * @wbp.factory.parameter.source arg0_4 lblNewLabel_2
	 * @wbp.factory.parameter.source arg0_5 textLatoA
	 * @wbp.factory.parameter.source arg0_6 lblNewLabel_3
	 * @wbp.factory.parameter.source arg0_7 textLatoB
	 * @wbp.factory.parameter.source arg0_8 lblNewLabel_4
	 * @wbp.factory.parameter.source arg0_9 textSpessore
	 * @wbp.factory.parameter.source arg0_10 lblNewLabel_5
	 * @wbp.factory.parameter.source arg0_11 textRugosita
	 * @wbp.factory.parameter.source arg0_12 lblNewLabel_6
	 * @wbp.factory.parameter.source arg0_13 textResterm
	 */
	public static JPanel createJPanel(LayoutManager layout, Component arg0, Component arg0_1, Component arg0_2, Component arg0_3, Component arg0_4, Component arg0_5, Component arg0_6, Component arg0_7, Component arg0_8, Component arg0_9, Component arg0_10, Component arg0_11, Component arg0_12, Component arg0_13) {
		JPanel panel = new JPanel();
		panel.setLayout(layout);
		panel.add(arg0);
		panel.add(arg0_1);
		panel.add(arg0_2);
		panel.add(arg0_3);
		panel.add(arg0_4);
		panel.add(arg0_5);
		panel.add(arg0_6);
		panel.add(arg0_7);
		panel.add(arg0_8);
		panel.add(arg0_9);
		panel.add(arg0_10);
		panel.add(arg0_11);
		panel.add(arg0_12);
		panel.add(arg0_13);
		return panel;
	}
}