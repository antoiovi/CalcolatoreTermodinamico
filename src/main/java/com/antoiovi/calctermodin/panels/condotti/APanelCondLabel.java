package com.antoiovi.calctermodin.panels.condotti;

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
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

public class APanelCondLabel extends JPanel {
	Tubo tubo;
	TuboFactory factoryTubo;
	double lungh;
	double diametro;
	double spessore;
	double latoa;
	double latob;
	double rug;
	double rter;
	String forma;
	String strfloat = "%1.2f";
	private JTextField txtTipo;
	private JTextField txtNumero;
	private JTextField txtPerditeLocalizzate;


	/**
	 * Create the panel.
	 */
	public APanelCondLabel() {
		DecimalFormatSymbols dot_decimal = new DecimalFormatSymbols();
		dot_decimal.setDecimalSeparator('.');
		dot_decimal.setGroupingSeparator(',');

		Locale.setDefault(Locale.US);
		setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();

		this.add(panel_1);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("left:max(50dlu;pref):grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("35px"),
				RowSpec.decode("35px"),
				RowSpec.decode("35px"),}));
		forma = "Circolare";
				
				txtNumero = new JTextField();
				txtNumero.setEditable(false);
				txtNumero.setText("Numero");
				panel_1.add(txtNumero, "1, 1, fill, default");
				txtNumero.setColumns(10);
				
				txtTipo = new JTextField();
				txtTipo.setEditable(false);
				txtTipo.setText("Tipo");
				panel_1.add(txtTipo, "1, 3, fill, default");
				txtTipo.setColumns(10);
		
				JTextField lblNewLabel = new JTextField("Sezione");
				lblNewLabel.setEditable(false);
				panel_1.add(lblNewLabel, "1, 5, fill, fill");
		
				JTextField lblNewLabel_1 = new JTextField("Diametro [ mm ]");
				lblNewLabel_1.setEditable(false);
				panel_1.add(lblNewLabel_1, "1, 7, fill, fill");
		JTextField lblNewLabel_2 = new JTextField("Lato A [ mm ]");
		lblNewLabel_2.setEditable(false);
		panel_1.add(lblNewLabel_2, "1, 9, fill, fill");
		
				JTextField lblNewLabel_3 = new JTextField("Lato B [ mm  ]");
				lblNewLabel_3.setEditable(false);
				panel_1.add(lblNewLabel_3, "1, 11, fill, fill");
		
				JTextField lblNewLabel_4 = new JTextField("Spessore [ mm ]");
				lblNewLabel_4.setEditable(false);
				panel_1.add(lblNewLabel_4, "1, 13, fill, fill");
				
				//textSpessore.setInputVerifier(verifier);
				JTextField lblNewLabel_5 = new JTextField("Rugosita [m]");
				lblNewLabel_5.setEditable(false);
				panel_1.add(lblNewLabel_5, "1, 15, fill, fill");
		
				JTextField lblNewLabel_6 = new JTextField("Resistenza termica[m2 \u00B0C/W]");
				lblNewLabel_6.setEditable(false);
				panel_1.add(lblNewLabel_6, "1, 17, fill, fill");
	//	df.setDecimalFormatSymbols(dot_decimal);
		//textResterm.setInputVerifier(verifier);

		JTextField lblNewLabel_7 = new JTextField("Lunghezza [ m ]");
		lblNewLabel_7.setEditable(false);
		panel_1.add(lblNewLabel_7, "1, 19, fill, fill");
		
		JTextField lblAltezza = new JTextField("Altezza");
		lblAltezza.setEditable(false);
		panel_1.add(lblAltezza, "1, 21, fill, default");
		
		JTextField lblCoefficenteLaminareEsterno = new JTextField("Coefficente laminare esterno");
		lblCoefficenteLaminareEsterno.setEditable(false);
		panel_1.add(lblCoefficenteLaminareEsterno, "1, 23");
		
		txtPerditeLocalizzate = new JTextField();
		txtPerditeLocalizzate.setEditable(false);
		txtPerditeLocalizzate.setText("Perdite localizzate ");
		panel_1.add(txtPerditeLocalizzate, "1, 25, fill, default");
		txtPerditeLocalizzate.setColumns(10);
	}

	
	

	
}// FINE CLASSE PANEL TUBO
