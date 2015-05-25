package com.antoiovi.calctermodin.panels;

import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.InputVerifier;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.awt.BorderLayout;

import javax.swing.JPopupMenu;

import com.antoiovi.calctermodin.JIF_Rugosita;
import com.antoiovi.unicig.tubi.Tubo;
import com.antoiovi.unicig.tubi.TuboC;
import com.antoiovi.unicig.tubi.TuboFactory;
import com.antoiovi.unicig.tubi.TuboQ;
import com.antoiovi.unicig.tubi.TuboR;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;

import java.awt.Color;

import javax.swing.JSpinner;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.swing.JFormattedTextField;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SpringLayout;

public class APanelTubo_lbl extends JPanel {

	/**
	 * Create the panel.
	 */
	public APanelTubo_lbl() {
		DecimalFormatSymbols dot_decimal = new DecimalFormatSymbols();
		dot_decimal.setDecimalSeparator('.');
		dot_decimal.setGroupingSeparator(',');

		Locale.setDefault(Locale.US);
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 0,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 0,
				SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, 359,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, panel_1, 236,
				SpringLayout.WEST, this);

		this.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel = new JLabel("Sezione");
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Diametro [ mm ]");
		panel_1.add(lblNewLabel_1);
		JLabel lblNewLabel_2 = new JLabel("Lato A [ mm ]");
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Lato B [ mm  ]");
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Spessore [ mm ]");
		panel_1.add(lblNewLabel_4);

		// textSpessore.setInputVerifier(verifier);
		JLabel lblNewLabel_5 = new JLabel("Rugosita [m]");
		panel_1.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Resistenza termica[m2 \u00B0C/W]");
		panel_1.add(lblNewLabel_6);
		// textResterm.setInputVerifier(verifier);

		JLabel lblNewLabel_7 = new JLabel("Lunghezza [ m ]");
		panel_1.add(lblNewLabel_7);
		// txtLungh.setInputVerifier(verifier);

		JLabel lblNewLabel_8 = new JLabel("Perimetro interno [m]");
		panel_1.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Perimetro esterno [m]");
		panel_1.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Area interna [m2]l");
		panel_1.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("Area esterna [m2]");
		panel_1.add(lblNewLabel_11);

		JLabel lblNewLabel_12 = new JLabel("Diametro idraulico int [m]");
		panel_1.add(lblNewLabel_12);

		JLabel lblNewLabel_13 = new JLabel("Diametro idraulico esterno [m]");
		panel_1.add(lblNewLabel_13);

	}
}// FINE CLASSE PANEL TUBO
