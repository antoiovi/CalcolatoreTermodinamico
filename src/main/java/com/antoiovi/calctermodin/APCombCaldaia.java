package com.antoiovi.calctermodin;

import com.antoiovi.unicig.fluidi.comb.CombustibiliFactory;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.antoiovi.unicig.fluidi.comb.Combustibile;
import com.antoiovi.unicig.fluidi.comb.Comb_1;
import com.antoiovi.unicig.fluidi.comb.Comb_2;
import com.antoiovi.unicig.impianti.Gener;
import java.awt.Toolkit;


import java.util.List;
import java.util.ArrayList;


import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.text.DefaultCaret;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class APCombCaldaia extends JPanel implements ItemListener {
	String[] sinput = { "Combustibile", "Potenza", "Temperatura fumi [°C]" };

	Dati daticalcolo = new Dati();

	// INDICI OUTPUT
	final int OUT_P_MASS = 0;
	final int OUT_R = 1;

	final int OUT_CAP_T = 2;
	final int OUT_TEN_H2O = 3;

	final int OUT_PRESS_PARZ_VAP_H2O = 4;
	final int OUT_TEMP_RUG = 5;

	final int OUT_AUM_TEMP_RUG = 6;
	final int OUT_COND_TERM = 7;

	final int OUT_VISC_DIN = 8;
	final int OUT_R_CONDENS = 9;

	final int OUT_TIR_MIN = 10;
	final int OUT_PRESS_PARZ_VAP_H20_A_T_RUG = 11;

	// Indici InputTextField
	final int INP_POT = 0;
	final int INP_TEMPFUM = 1;
	final int INP_P_MASS = 2;
	final int INP_REND = 3;
	final int INP_CO2 = 4;

	String[] strCheckBoxes = { "Portata fumi nota [g/s]", "Rendiemento noto", "CO2% noto" };
	final int CHB_P_MASS = 0;
	final int CHB_REND = 1;
	final int CHB_CO = 2;

	String[] strRadioButtons = { "Bruciatore aria soffiata", "Bruciatore aria naturale" };
	final int RAD_BTN_AS = 0;
	final int RAD_BTN_AN = 1;

	private List<String> listaErroriInput = new ArrayList<String>();

	String[] soutput = {
			"Portata massica fumi [g/s]", "CostElasticita",
			"CapTermica", "TenoreH2O [%]",
			"Pressione parziale del vapore acqueo[Pa]", "temperatura punto di rugiada[°C]",
			"Aumento del punto di rugiada [K]", "coefficiente di conducibilità termica in W/(m x K)",
			"viscosità dinamica dei prodotti [N s/m2]", " R in J/(kgxK)",
			"Tiraggio minimo [Pa] ", "P parz vapore acqueo a temp rugiada [Pa]" };
	String sDescrizione = "<html><b>Calcolo parametri combustione</b><br>" +
			"<p>Il programma calcola i parametri dei prodotti " +
			"della combustione dati i dati del generatore utilizando il metodo della norma UNI EN 13384-1</p><br>" +
			"" +
			"</html>";
	JLabel[] lblInput;
	JTextField[] txtInput;

	// Panel input 2
	JPanel panelInput1;
	JPanel panelInput2;
	JTextField textFieldInput[];

	JTextField[] manual;
	JCheckBox[] jcheck;

	static final int BR_SOFF = 0;
	static final int BR_NAT = 1;

	JRadioButton[] rdb_ariabr;
	// Group the radio buttons.
	ButtonGroup group;

	JComponent[] jinput2;


	// Dati output
	JLabel[] lblOutput;
	JTextField[] txtOutput;

	JComponent[] jinput;


	JButton bCalcola;

	String[] combustibili;
	JComboBox jcombListCombust;
	JPanel panel;
	JTextArea outArea;

	public APCombCaldaia() {
		panel = this;// new JPanel();
		panel.setLayout(new GridBagLayout());
		// JComboBox con lista combustibili
		combustibili = Combustibile.combustibile;
		jcombListCombust = new JComboBox(combustibili);
		jcombListCombust.setSelectedIndex(4);

		// Right-click help on fuel combo box
		jcombListCombust.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					showFuelHelpDialog();
				}
			}
		});

		// sinput stringhe input : combiustiblie, potenza; temperatura
		// jiunput : componenti che possono essere JTextField o JComboBox
		jinput = new JComponent[sinput.length];
		// etichette inpute : come campi input
		lblInput = new JLabel[sinput.length];
		// textfield solo due (uno è jcombobox )
		txtInput = new JTextField[sinput.length - 1 + strCheckBoxes.length];

		lblOutput = new JLabel[soutput.length];
		txtOutput = new JTextField[soutput.length];

		// Numero 5 textfield Input : Potenza, Temperatura Pmassica Rendimento CO2
		textFieldInput = new JTextField[5];
		textFieldInput[INP_POT] = new JTextField(10);
		textFieldInput[INP_TEMPFUM] = new JTextField(10);
		textFieldInput[INP_P_MASS] = new JTextField(10);
		textFieldInput[INP_REND] = new JTextField(10);
		textFieldInput[INP_CO2] = new JTextField(10);

		for (int x = 0; x < sinput.length; x++) {
			if (x == 0) {
				jinput[x] = jcombListCombust;
			} else {
				jinput[x] = textFieldInput[x - 1];
			}
		}

		int X = 0;
		int Y = 0;
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0, 0, 5, 5);
		// Titolo input
		X = 0;
		c.gridy = Y;
		// c.weighty = 1.0; //request any extra vertical space
		c.ipady = 40; // make this component tall
		c.gridwidth = 2;

		JLabel titlinput = new JLabel("Dati caldaia :");
		titlinput.setFont(new Font("Courier New", Font.BOLD, 24));
		titlinput.setForeground(Color.GRAY);
		panel.add(titlinput, c);

		c.weighty = 0; // reset
		c.ipady = 0; // reset
		Y += 2;

		X = 0;

		/**
		 * INPUT PANEL 1
		 **/
		this.initPanelInput1();
		// Input
		c.gridx = X;
		c.gridy = Y;

		c.gridwidth = 5;

		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(panelInput1, c);
		/**
		 * INPUT PANEL 2
		 **/
		this.initPanelInput2();

		Y += 2;
		c.gridx = 0;
		c.gridy = Y;

		c.gridwidth = 5;

		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(panelInput2, c);
		c.fill = GridBagConstraints.NONE;

		// -------- Button OK
		Y += 2;
		X = 0;
		bCalcola = new JButton("Calcola");
		bCalcola.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calcola();
			}
		});

		// c.weighty = 1.0; //request any extra vertical space
		c.gridx = 0;
		c.gridy = Y;
		c.gridwidth = 2;
		panel.add(bCalcola, c);
		// -------- RISULTATI DEL CALCOLO -------
		X = 0;
		Y += 2;
		c.gridx = X;
		c.gridy = Y;
		c.gridwidth = 2;
		// c.weighty = 1.0; //request any extra vertical space
		c.ipady = 30; // make this component tall

		JLabel titleout = new JLabel("Risultati del calcolo :");
		titleout.setFont(new Font("Courier New", Font.BOLD, 24));
		titleout.setForeground(Color.GRAY);
		panel.add(titleout, c);
		c.gridwidth = 1;

		// ------------- Output
		Y += 2;
		c.gridy = Y;
		c.ipady = 0; // reset to default
		c.weighty = 0; // reset to default
		c.gridwidth = 1;
		X = 0;
		int col = 0;
		int row = Y;

		for (int x = 0; x < soutput.length; x++) {
			c.gridx = X;
			c.gridy = Y;
			c.weightx = 0.1;

			lblOutput[x] = new JLabel(soutput[x]);
			panel.add(lblOutput[x], c);
			log(String.format("Label %20s  Y=%d", soutput[x], Y));
			X++;
			c.gridx = X;
			c.weightx = 0.4;
			txtOutput[x] = new JTextField();
			c.fill = GridBagConstraints.HORIZONTAL;
			txtOutput[x].setColumns(10);
			txtOutput[x].setBackground(Color.BLUE);

			panel.add(txtOutput[x], c);
			X++;
			col++;
			if (col > 1) {
				col = 0;
				X = 0;
				Y += 2;
			}

		}

		log(String.format("Y=%d", Y));
		/********
		 * Output logArea
		 ************/
		outArea = new JTextArea(10, 10);
		// Queste due righe sono state messe per permettere l'autoscrll
		// dell'area nello jscrollpane
		DefaultCaret caret = (DefaultCaret) outArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		Y++;
		c.gridx = 0;
		c.gridy = Y;
		c.gridwidth = 6;
		c.gridheight = 2;
		c.weighty = 1.0;
		c.weightx = 1.0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		log(String.format("out Area     Y=%d", Y));
		JScrollPane spane = new JScrollPane(outArea);
		panel.add(spane, c);
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.NONE;
		// ---------------------DESCIZIONE (Ultima Linea)-----
		JLabel descr = new JLabel(sDescrizione);
		Y++;
		c.gridx = 0;
		c.gridy = Y + 1;
		c.gridwidth = 6;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LAST_LINE_START;

		panel.add(descr, c);

		/****
		 * Inizzializzo checkBox e radio box
		 ****/
		boolean is_def = false;
		jcheck[CHB_CO].setSelected(is_def);
		jcheck[CHB_REND].setSelected(is_def);
		jcheck[CHB_P_MASS].setSelected(is_def);

		rdb_ariabr[RAD_BTN_AN].setSelected(true);
		rdb_ariabr[RAD_BTN_AS].setSelected(false);

	}

	/**
	 * Implementazione metodo di ItemListener
	 * (intercettazione change checkbox
	 **/
	@Override
	public void itemStateChanged(ItemEvent itemEvent) {
		if (itemEvent.getSource().equals(jcheck[CHB_REND])) {
			textFieldInput[INP_REND].setEnabled(jcheck[CHB_REND].isSelected());
		} else if (itemEvent.getSource().equals(jcheck[CHB_CO])) {
			textFieldInput[INP_CO2].setEnabled(jcheck[CHB_CO].isSelected());
		} else if (itemEvent.getSource().equals(jcheck[CHB_P_MASS])) {
			textFieldInput[INP_P_MASS].setEnabled(jcheck[CHB_P_MASS].isSelected());
		}

	};

	/*********************
	 * panel input 1
	 *********************/
	void initPanelInput1() {
		panelInput1 = new JPanel(new FlowLayout());
		// Input
		lblInput[0] = new JLabel(sinput[0]);
		panelInput1.add(lblInput[0]);
		panelInput1.add(jinput[0]);
		for (int x = 1; x < sinput.length; x++) {
			lblInput[x] = new JLabel(sinput[x]);
			panelInput1.add(lblInput[x]);
			panelInput1.add(jinput[x]);
		}

	}

	/*********************
	 * panel input 2
	 *********************/
	void initPanelInput2() {
		int chb = 3; // Check Box : portatamassica,Rendimento, CO2
		int rb1 = 2;

		panelInput2 = new JPanel(new FlowLayout());
		group = new ButtonGroup();
		jcheck = new JCheckBox[chb];
		jcheck[CHB_P_MASS] = new JCheckBox(strCheckBoxes[CHB_P_MASS]);
		jcheck[CHB_P_MASS].addItemListener(this);
		panelInput2.add(jcheck[CHB_P_MASS]);
		panelInput2.add(textFieldInput[INP_P_MASS]);

		jcheck[CHB_REND] = new JCheckBox(strCheckBoxes[CHB_REND]);
		jcheck[CHB_REND].addItemListener(this);
		panelInput2.add(jcheck[CHB_REND]);
		panelInput2.add(textFieldInput[INP_REND]);

		jcheck[CHB_CO] = new JCheckBox(strCheckBoxes[CHB_CO]);
		jcheck[CHB_CO].addItemListener(this);
		panelInput2.add(jcheck[CHB_CO]);
		panelInput2.add(textFieldInput[INP_CO2]);

		rdb_ariabr = new JRadioButton[rb1];
		rdb_ariabr[RAD_BTN_AN] = new JRadioButton(strRadioButtons[RAD_BTN_AN]);
		group.add(rdb_ariabr[RAD_BTN_AN]);
		panelInput2.add(rdb_ariabr[RAD_BTN_AN]);
		rdb_ariabr[RAD_BTN_AS] = new JRadioButton(strRadioButtons[RAD_BTN_AS]);
		group.add(rdb_ariabr[RAD_BTN_AS]);
		panelInput2.add(rdb_ariabr[RAD_BTN_AS]);

	}

	/*****************
	 * CALCOLA
	 *****************/
	private void Calcola() {
		Dati dati = new Dati();

		// logArea("");
		if (validateInput(dati)) {
			logArea("INPUT OK");
			try {
				daticalcolo.calcola();

				logArea(daticalcolo.gen_stringHeader);
				logArea(daticalcolo.gen_stringValue);
				logArea(daticalcolo.gen_comb_stringHeader);
				logArea(daticalcolo.gen_comb_stringValue);

				setTextValue(daticalcolo.gen_pmass, txtOutput[OUT_P_MASS]);
				setTextValue(daticalcolo.gen_cost_el, txtOutput[OUT_R]);

				setTextValue(daticalcolo.gen_cap_term, txtOutput[OUT_CAP_T]);
				setTextValue(daticalcolo.gen_tenore_h20, txtOutput[OUT_TEN_H2O]);

				setTextValue(daticalcolo.gen_press_parz_vap_h20, txtOutput[OUT_PRESS_PARZ_VAP_H2O]);
				setTextValue(daticalcolo.gen_temp_rug, txtOutput[OUT_TEMP_RUG]);

				setTextValue(daticalcolo.gen_aum_temp_rug, txtOutput[OUT_AUM_TEMP_RUG]);
				setTextValue(daticalcolo.gen_cond_term, txtOutput[OUT_COND_TERM]);

				setTextValue(daticalcolo.gen_visc_din, txtOutput[OUT_VISC_DIN]);
				setTextValue(daticalcolo.gen_cost_el_cond, txtOutput[OUT_R_CONDENS]);

				setTextValue(daticalcolo.gen_tiragg_min, txtOutput[OUT_TIR_MIN]);
				setTextValue(daticalcolo.gen_press_parz_h20_a_Trug, txtOutput[OUT_PRESS_PARZ_VAP_H20_A_T_RUG]);

			} catch (Exception e) {
				log(e.toString());
				logArea("ERRORE DURANTE I CALCOLI");
			}

		} else {
			logArea("ERRORE INPUT");
			for (String _str_ : listaErroriInput) {
				logArea(_str_);
			}
		}

	}

	/****************************************************************
	 * Validate Input
	 ************************************************************/
	boolean validateInput(Dati dati) {

		listaErroriInput.clear();
		boolean test = true;

		String[] strErrori = new String[textFieldInput.length];

		strErrori[INP_POT] = "Valore potenza non valido...";
		strErrori[INP_TEMPFUM] = "Valore temperatura fumi non valido";
		strErrori[INP_P_MASS] = "Valore portata massica non valido ";
		strErrori[INP_REND] = "Valore rendimento non valido ";
		strErrori[INP_CO2] = "Valore CO2 non valido ";

		daticalcolo.is_bruc_aria_nat = rdb_ariabr[RAD_BTN_AN].isSelected();
		daticalcolo.is_bruc_aria_soff = rdb_ariabr[RAD_BTN_AS].isSelected();
		daticalcolo.gen_COMBUSTIBILE = jcombListCombust.getSelectedIndex();

		for (int x = 0; x < textFieldInput.length; x++) {
			// log(String.format("x=%d",x));
			// Verifcio quelli condizionati da check box
			if (x == INP_P_MASS) {
				if (jcheck[CHB_P_MASS].isSelected()) {
					Double _VAL = convertField(textFieldInput[x]);
					test = _VAL == null ? false : test;
					if (_VAL != null) {
						daticalcolo.gen_pmass = _VAL;
						daticalcolo.is_pmass_noto = true;
					} else {
						listaErroriInput.add(strErrori[INP_P_MASS]);
					}

				} else {
					daticalcolo.is_pmass_noto = false;
				}

			} else if (x == INP_REND) {
				if (jcheck[CHB_REND].isSelected()) {
					Double _VAL = convertField(textFieldInput[x]);
					test = _VAL == null ? false : test;
					if (_VAL != null) {
						daticalcolo.gen_rend = _VAL;
						daticalcolo.is_rend_noto = true;
					} else {
						listaErroriInput.add(strErrori[INP_REND]);
					}

				} else {
					daticalcolo.is_rend_noto = false;
				}

			} else if (x == INP_CO2) {
				if (jcheck[CHB_CO].isSelected()) {
					Double _VAL = convertField(textFieldInput[x]);

					test = _VAL == null ? false : test;
					if (_VAL != null) {
						daticalcolo.gen_co2 = _VAL;
						daticalcolo.is_co2_noto = true;
					} else {
						listaErroriInput.add(strErrori[INP_CO2]);
					}

				} else {
					daticalcolo.is_co2_noto = false;
				}

			} else {
				Double _VAL = null;
				switch (x) {
					case INP_POT:
						_VAL = convertField(textFieldInput[x]);
						test = _VAL == null ? false : test;
						if (_VAL != null) {
							daticalcolo.gen_potenza = _VAL;
						} else {
							listaErroriInput.add(strErrori[INP_POT]);
						}
					case INP_TEMPFUM:
						_VAL = convertField(textFieldInput[x]);
						test = _VAL == null ? false : test;
						if (_VAL != null) {
							daticalcolo.gen_temp_fumi = _VAL;
						} else {
							listaErroriInput.add(strErrori[INP_TEMPFUM]);
						}
				}// switch
					// log(String.format("x=%d",x));
			}
		} // ciclo for

		return test;
	}

	/*************
	 * CONVERT FIELD
	 **************/
	private Double convertField(JTextField field) {
		String strVal = field.getText();
		Double val = convertToDouble(strVal);
		if (val == null) {
			field.setOpaque(true);
			field.setBackground(Color.GREEN);
			field.setForeground(Color.RED);
			return null;
		} else {
			field.setBackground(Color.WHITE);
			field.setForeground(Color.BLACK);
			return val;

		}
	}

	/************************
	 * ConvertToDouble
	 ***************/
	private Double convertToDouble(String string) {
		try {
			Double val = Double.parseDouble(string);
			log(String.format("Conversione OK val(%s) = %f", string, val));
			return val;
		} catch (Exception e) {
			log(String.format("errore val(%s) = nullo...", string));
			return null;
		}

	}

	private void setTextValue(double _value, JTextField _jtextfield) {
		try {
			String string = String.format("%f", _value);
			_jtextfield.setText(string);

		} catch (Exception e) {
			_jtextfield.setText("#errore");
		}
	}

	private void log(String s) {
		System.out.println(s);
	}

	private void logArea(String s) {
		outArea.append(s);
		outArea.append("\n");
	}

	private void logAreaClean() {
		outArea.setText("");
	}

	/**
	 * Opens a dialog showing help information for all fuels.
	 * One row per fuel with its parameters.
	 */
	/**
	 * Opens a dialog showing all fuels and their properties.
	 * Data are loaded directly from CombustibiliFactory.*/

private void showFuelHelpDialog() {

    JDialog dialog = new JDialog();
    dialog.setTitle("Fuel Technical Data");
    dialog.setModal(true);
    dialog.setSize(800, 450);
    dialog.setLocationRelativeTo(this);

    // === Load data from factory ===
    String[] fuelNames = CombustibiliFactory.getCombustibileName();
    double[][] fuelData = CombustibiliFactory.getDatiCombustibile();

    // === Create column headers ===
    int propertyCount = fuelData[0].length;
    String[] columns = new String[propertyCount + 1];
    columns[0] = "Combustibile";
    for (int i = 0; i < propertyCount; i++) {
        columns[i + 1] = CombustibiliFactory.COL_NAMES[i];
    }

    // === Table model (read-only) ===
    DefaultTableModel model = new DefaultTableModel(columns, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    // === Populate table ===
    for (int i = 0; i < fuelNames.length; i++) {
        Object[] row = new Object[propertyCount + 1];
        row[0] = fuelNames[i];
        for (int j = 0; j < propertyCount; j++) {
            row[j + 1] = fuelData[i][j];
        }
        model.addRow(row);
    }

    // === JTable ===
    JTable table = new JTable(model);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    JScrollPane scrollPane = new JScrollPane(table);

    // === Pannello descrizione proprietà ===
    JPanel descriptionPanel = new JPanel();
    descriptionPanel.setLayout(new GridLayout(CombustibiliFactory.COL_NAMES.length, 1, 5, 5));

    String[] descrizioni = {
        "Potere calorifico inferiore del combustibile [MJ/kg]",        // HU
        "Valore minimo di VATR [m3/kg]",                               // Vatr min
        "Valore minimo di VL [m3/kg]",                                 // VL min
        "Contenuto massimo di acqua [%]",                              // VH2O
        "CO2 massimo [%]",                                             // CO2%max
        "SO2 massimo [%]"                                              // SO2%max
    };

    for (int i = 0; i < CombustibiliFactory.COL_NAMES.length; i++) {
        JLabel lbl = new JLabel(CombustibiliFactory.COL_NAMES[i] + " : " + descrizioni[i]);
        lbl.setFont(new Font("Courier New", Font.PLAIN, 14));
        descriptionPanel.add(lbl);
    }

    // === Pannello principale ===
    JPanel contentPanel = new JPanel();
    contentPanel.setLayout(new BorderLayout(5, 5));
    contentPanel.add(scrollPane, BorderLayout.CENTER);
    contentPanel.add(descriptionPanel, BorderLayout.SOUTH);

    dialog.add(contentPanel);
    dialog.pack(); // ridimensiona in base ai contenuti
    dialog.setLocationRelativeTo(this); // centra
    dialog.setVisible(true);
}










	private class Dati {
		int gen_COMBUSTIBILE;
		double gen_potenza;
		double gen_temp_fumi;

		boolean is_pmass_noto;
		double gen_pmass;
		boolean is_rend_noto;
		double gen_rend;
		boolean is_co2_noto;
		double gen_co2;
		boolean is_bruc_aria_soff;
		boolean is_bruc_aria_nat;

		boolean inp_rend_mano;
		double rend_a_mano;
		boolean inp_co2_mano;
		double co2_mano;
		boolean bruc_aria_soff;
		boolean bruc_aria_nat;

		String gen_stringHeader;
		String gen_stringValue;

		String gen_comb_stringHeader;
		String gen_comb_stringValue;

		double gen_cost_el;
		double gen_cap_term;
		double gen_tenore_h20;
		double gen_press_parz_h20;
		double gen_press_parz_h20_a_Trug;
		double gen_temp_rug;
		double gen_aum_temp_rug;
		double gen_cond_term;
		double gen_visc_din;
		double gen_cost_el_cond;
		double gen_press_parz_vap_h20;

		double gen_tiragg_min;

		public void calcola() {

			Gener gener = new Gener(gen_potenza, gen_COMBUSTIBILE, gen_temp_fumi, is_bruc_aria_nat);

			if (is_co2_noto)
				gener.setCo2(gen_co2);
			if (is_rend_noto)
				gener.setRend(gen_rend);
			if (is_pmass_noto)
				gener.setPortatamassicaFumi(gen_pmass);

			gen_stringHeader = gener.getStringHeader();
			gen_stringValue = gener.getStringValue();
			gener.print();

			Comb_2 combx = new Comb_2(gen_COMBUSTIBILE, gener.getCo2(), gener.getTm());
			combx.setPressione(101000.0);
			combx.print();

			gen_comb_stringHeader = combx.getStringHeader();
			gen_comb_stringValue = combx.getStringValue();

			gen_pmass = gener.portataMassicaFumi();
			gen_cost_el = combx.CostElasticita_1();

			gen_cap_term = combx.CapTermica();
			gen_tenore_h20 = combx.TenoreH2O();// tenore vapore acqueo

			System.out.println(String.format("Tenore h20= %f  Parziale=%f ", gen_tenore_h20,
					Comb_1.PparzialeH2o(gen_tenore_h20, 101000.0)));
			System.out.println(String.format("PARZIALE VAPORE= %f   ", combx.PparzialeH2o()));

			gen_press_parz_vap_h20 = combx.PparzialeH2o();
			gen_temp_rug = combx.tempPuntoRugiada();

			gen_aum_temp_rug = combx.deltaTsp();
			gen_cond_term = combx.lambdaA();

			gen_visc_din = combx.viscDin();
			gen_cost_el_cond = combx.CostElasticita_Cond();

			gen_press_parz_h20_a_Trug = combx.PparzialeH2O_Tr();

			gen_tiragg_min = gener.getPW_tiraggiominimo();
		}

	}// PRIVATE CLASS Date

	/****************** */

}// CLASSE PRINCIPALE
