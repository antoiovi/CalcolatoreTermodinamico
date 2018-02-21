package com.antoiovi.calctermodin;

import javax.swing.JPanel;

import it.iovino.fluidi.Fluido;
import it.iovino.fluidi.combustione.*;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.InputVerifier;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerListModel;

import java.awt.Rectangle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;



public class APanelCombustione extends JPanel implements ItemListener{

SpinnerNumberModel model ;
JSpinner.NumberEditor editor;
private final ButtonGroup buttonGroup = new ButtonGroup();
private JSpinner spinnerKw;
private JSpinner spinnerCO2Secc;
private JCheckBox chckbxPotenzaNota;
AVerifier averifier;
private JSpinner spinnerEccAria;
private JRadioButton rdbtnEccAria;
private JRadioButton rdbtnCO2;
private JComboBox comboBox;
private JTextField txtEccAria;
private JTextField txtCo2Umidi;
private JTextField textO2Secc;
private JTextField txtN2secchi;
private JTextField txtH2O;
private JSpinner spinnerRend;
private JCheckBox chckbxRendimentoNoto;

/**
 * Dati per il calcolo
 */
String combustibile;
double ecc_aria;
double co2_perc_sec;
double potenza;
double rend;
double temp_max;
double temp_min;
//CombMetano combmetano;
Combustione combustione;
Fluido fumo;
private JPanel panel_1;
private JLabel lblStatus;
private JTextField txtPortatamass;
private JTextField textTmax;
private JTextField textTmin;
private JLabel lblTempMinima;
private JPanel panel_3;
private JPanel panel_4;
private JTextField txtO2Vol;
private JTextField txtN2Vol;
private JTextField txtH20Vol;
private JTextField txtCo2Vol;
private JLabel lblVolumiTotatli;
private JTextField txtCO2secco;
private JLabel lblInFumi;
private JTextField txtO2Umidi;
private JTextField txtN2Umidi;
private JTextField txtVoltot;
private JLabel lblPotenza;
private JTextField txtPotenza;
private JLabel lblPortataGas;
private JTextField txtPortatagas;
private JPanel panel_5;
private JLabel lblNewLabel_4;

	/**
	 * Create the panel.
	 */
	public APanelCombustione() {
		averifier=new AVerifier();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{135, 0, 198, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 95, 0, 28, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		/**
		 * COMBUSTIBILE
		 */
		JLabel lblCombustubile = new JLabel("Combustubile");
		GridBagConstraints gbc_lblCombustubile = new GridBagConstraints();
		gbc_lblCombustubile.insets = new Insets(0, 0, 5, 5);
		gbc_lblCombustubile.gridx = 0;
		gbc_lblCombustubile.gridy = 0;
		panel.add(lblCombustubile, gbc_lblCombustubile);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Metano"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.anchor = GridBagConstraints.NORTH;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		panel.add(comboBox, gbc_comboBox);
		/**
		 * SELEZIONE tipo di dati input (ecc aria o co2%)
		 */
		rdbtnEccAria = new JRadioButton("Eccesso d'aria\r\n");
		rdbtnEccAria.setSelected(true);
		buttonGroup.add(rdbtnEccAria);
		rdbtnEccAria.addItemListener(this);
		GridBagConstraints gbc_rdbtnEccAria = new GridBagConstraints();
		gbc_rdbtnEccAria.anchor = GridBagConstraints.WEST;
		gbc_rdbtnEccAria.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnEccAria.gridx = 0;
		gbc_rdbtnEccAria.gridy = 1;
		panel.add(rdbtnEccAria, gbc_rdbtnEccAria);
		
		rdbtnCO2 = new JRadioButton("CO2 % fumi secchi");
		buttonGroup.add(rdbtnCO2);
		rdbtnCO2.addItemListener(this);
		GridBagConstraints gbc_rdbtnCO2 = new GridBagConstraints();
		gbc_rdbtnCO2.anchor = GridBagConstraints.WEST;
		gbc_rdbtnCO2.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnCO2.gridx = 3;
		gbc_rdbtnCO2.gridy = 1;
		panel.add(rdbtnCO2, gbc_rdbtnCO2);
		
		/**
		 * Eccesso d'aria
		 */
		JLabel lblEccessoDaria = new JLabel("Eccesso d'aria");
		GridBagConstraints gbc_lblEccessoDaria = new GridBagConstraints();
		gbc_lblEccessoDaria.insets = new Insets(0, 10, 5, 5);
		gbc_lblEccessoDaria.anchor = GridBagConstraints.WEST;
		gbc_lblEccessoDaria.gridx = 0;
		gbc_lblEccessoDaria.gridy = 2;
		panel.add(lblEccessoDaria, gbc_lblEccessoDaria);
		
		spinnerEccAria = new JSpinner();
		spinnerEccAria.setModel(new SpinnerNumberModel(0.0, 0.0, 200.0, 5.0));
		spinnerEccAria.setInputVerifier(averifier);
		GridBagConstraints gbc_spinner_1 = new GridBagConstraints();
		gbc_spinner_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_1.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_1.gridx = 1;
		gbc_spinner_1.gridy = 2;
		panel.add(spinnerEccAria, gbc_spinner_1);
		/**
		 * CO" percentuale secca
		 */
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.anchor = GridBagConstraints.WEST;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.VERTICAL;
		gbc_panel_2.gridx = 3;
		gbc_panel_2.gridy = 2;
		panel.add(panel_2, gbc_panel_2);
		JLabel lblNewLabel = new JLabel("CO2%");
		panel_2.add(lblNewLabel);
		
		spinnerCO2Secc = new JSpinner();
		spinnerCO2Secc.setPreferredSize(new Dimension(50, 20));
		panel_2.add(spinnerCO2Secc);
		spinnerCO2Secc.setEnabled(false);
		spinnerCO2Secc.setModel(new SpinnerNumberModel(7.0, 4.0, 11.8, 0.1));
		spinnerCO2Secc.setInputVerifier(averifier);
		/**
		 * Potenza
		 */
		chckbxPotenzaNota = new JCheckBox("Potenza nota");
		chckbxPotenzaNota.setSelected(true);
		chckbxPotenzaNota .addItemListener(this);
		GridBagConstraints gbc_chckbxPotenzaNota = new GridBagConstraints();
		gbc_chckbxPotenzaNota.anchor = GridBagConstraints.NORTHWEST;
		gbc_chckbxPotenzaNota.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxPotenzaNota.gridx = 0;
		gbc_chckbxPotenzaNota.gridy = 3;
		panel.add(chckbxPotenzaNota, gbc_chckbxPotenzaNota);

		spinnerKw = new JSpinner();
		spinnerKw.setModel(new SpinnerNumberModel(4.0, 4.0, 35.0, 0.5));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.insets = new Insets(0, 0, 5, 5);
		gbc_spinner.gridx = 1;
		gbc_spinner.gridy = 3;
		panel.add(spinnerKw, gbc_spinner);
		
		JLabel lblkw = new JLabel("[kw]");
		GridBagConstraints gbc_lblkw = new GridBagConstraints();
		gbc_lblkw.insets = new Insets(0, 0, 5, 5);
		gbc_lblkw.gridx = 2;
		gbc_lblkw.gridy = 3;
		panel.add(lblkw, gbc_lblkw);
		
		JLabel lblSePotenzaNota = new JLabel("Se potenza nota calcolo portata massica fumi.");
		GridBagConstraints gbc_lblSePotenzaNota = new GridBagConstraints();
		gbc_lblSePotenzaNota.anchor = GridBagConstraints.WEST;
		gbc_lblSePotenzaNota.insets = new Insets(0, 0, 5, 5);
		gbc_lblSePotenzaNota.gridx = 3;
		gbc_lblSePotenzaNota.gridy = 3;
		panel.add(lblSePotenzaNota, gbc_lblSePotenzaNota);
		/**
		 * Rendimento
		 */
		chckbxRendimentoNoto = new JCheckBox("Rendimento noto");
		chckbxRendimentoNoto.setSelected(true);
		chckbxRendimentoNoto.addItemListener(this);
		GridBagConstraints gbc_chckbxRendimentoNoto = new GridBagConstraints();
		gbc_chckbxRendimentoNoto.anchor = GridBagConstraints.WEST;
		gbc_chckbxRendimentoNoto.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxRendimentoNoto.gridx = 0;
		gbc_chckbxRendimentoNoto.gridy = 4;
		panel.add(chckbxRendimentoNoto, gbc_chckbxRendimentoNoto);
		spinnerRend = new JSpinner();
		spinnerRend.setModel(new SpinnerNumberModel(90.0, 75.0, 100.0, 5.0));
		GridBagConstraints gbc_spinnerRend = new GridBagConstraints();
		gbc_spinnerRend.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerRend.insets = new Insets(0, 0, 0, 5);
		gbc_spinnerRend.gridx = 1;
		gbc_spinnerRend.gridy = 4;
		panel.add(spinnerRend, gbc_spinnerRend);
		
		JLabel lblSeRendimentoNoto = new JLabel("Se rendimento noto calcol temperatura fumi");
		GridBagConstraints gbc_lblSeRendimentoNoto = new GridBagConstraints();
		gbc_lblSeRendimentoNoto.anchor = GridBagConstraints.WEST;
		gbc_lblSeRendimentoNoto.insets = new Insets(0, 0, 0, 5);
		gbc_lblSeRendimentoNoto.gridx = 3;
		gbc_lblSeRendimentoNoto.gridy = 4;
		panel.add(lblSeRendimentoNoto, gbc_lblSeRendimentoNoto);
		/**
		 * Comando Calcola
		 */
		JButton btnCalcola = new JButton("Calcola");
		btnCalcola.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Calcola();
			}
		});
		GridBagConstraints gbc_btnCalcola = new GridBagConstraints();
		gbc_btnCalcola.insets = new Insets(0, 0, 5, 5);
		gbc_btnCalcola.gridx = 0;
		gbc_btnCalcola.gridy = 1;
		add(btnCalcola, gbc_btnCalcola);
		/**
		 * PANNELLO DI OUTPUT
		 */
		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{128, 46, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{14, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblComposizioneFumi = new JLabel("Composizione fumi:");
		GridBagConstraints gbc_lblComposizioneFumi = new GridBagConstraints();
		gbc_lblComposizioneFumi.gridwidth = 2;
		gbc_lblComposizioneFumi.insets = new Insets(0, 0, 5, 5);
		gbc_lblComposizioneFumi.gridx = 1;
		gbc_lblComposizioneFumi.gridy = 0;
		panel_1.add(lblComposizioneFumi, gbc_lblComposizioneFumi);
		
		panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.gridwidth = 4;
		gbc_panel_4.gridheight = 3;
		gbc_panel_4.insets = new Insets(0, 0, 5, 5);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 1;
		panel_1.add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{86, 53, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_4.rowHeights = new int[]{14, 0, 20, 0, 14, 0, 0, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JLabel lblNewLabel_1 = new JLabel("Eccesso d'aria");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel_4.add(lblNewLabel_1, gbc_lblNewLabel_1);
		lblNewLabel_1.setBounds(new Rectangle(10, 0, 0, 0));
		
		txtEccAria = new JTextField();
		GridBagConstraints gbc_txtEccAria = new GridBagConstraints();
		gbc_txtEccAria.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEccAria.gridwidth = 2;
		gbc_txtEccAria.anchor = GridBagConstraints.NORTHWEST;
		gbc_txtEccAria.insets = new Insets(0, 0, 5, 5);
		gbc_txtEccAria.gridx = 1;
		gbc_txtEccAria.gridy = 0;
		panel_4.add(txtEccAria, gbc_txtEccAria);
		txtEccAria.setColumns(10);
		
		lblInFumi = new JLabel("% in fumi secchi");
		GridBagConstraints gbc_lblInFumi = new GridBagConstraints();
		gbc_lblInFumi.insets = new Insets(0, 0, 5, 5);
		gbc_lblInFumi.gridx = 1;
		gbc_lblInFumi.gridy = 1;
		panel_4.add(lblInFumi, gbc_lblInFumi);
		
		JLabel lblFumiUmidi = new JLabel("% fumi umidi");
		GridBagConstraints gbc_lblFumiUmidi = new GridBagConstraints();
		gbc_lblFumiUmidi.gridwidth = 2;
		gbc_lblFumiUmidi.insets = new Insets(0, 0, 5, 5);
		gbc_lblFumiUmidi.gridx = 3;
		gbc_lblFumiUmidi.gridy = 1;
		panel_4.add(lblFumiUmidi, gbc_lblFumiUmidi);
		
		JLabel lblVolumi = new JLabel("volumi");
		GridBagConstraints gbc_lblVolumi = new GridBagConstraints();
		gbc_lblVolumi.insets = new Insets(0, 0, 5, 5);
		gbc_lblVolumi.gridx = 6;
		gbc_lblVolumi.gridy = 1;
		panel_4.add(lblVolumi, gbc_lblVolumi);
		
		JLabel lblNewLabel_2 = new JLabel("CO2%");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		panel_4.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		txtCO2secco = new JTextField();
		GridBagConstraints gbc_txtCO2secco = new GridBagConstraints();
		gbc_txtCO2secco.insets = new Insets(0, 0, 5, 5);
		gbc_txtCO2secco.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCO2secco.gridx = 1;
		gbc_txtCO2secco.gridy = 2;
		panel_4.add(txtCO2secco, gbc_txtCO2secco);
		txtCO2secco.setColumns(10);
		
		txtCo2Umidi = new JTextField();
		GridBagConstraints gbc_txtCo2Umidi = new GridBagConstraints();
		gbc_txtCo2Umidi.gridwidth = 2;
		gbc_txtCo2Umidi.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCo2Umidi.anchor = GridBagConstraints.NORTH;
		gbc_txtCo2Umidi.insets = new Insets(0, 0, 5, 5);
		gbc_txtCo2Umidi.gridx = 3;
		gbc_txtCo2Umidi.gridy = 2;
		panel_4.add(txtCo2Umidi, gbc_txtCo2Umidi);
		txtCo2Umidi.setColumns(10);
		
		txtCo2Vol = new JTextField();
		GridBagConstraints gbc_txtCo2Vol = new GridBagConstraints();
		gbc_txtCo2Vol.insets = new Insets(0, 0, 5, 5);
		gbc_txtCo2Vol.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCo2Vol.gridx = 6;
		gbc_txtCo2Vol.gridy = 2;
		panel_4.add(txtCo2Vol, gbc_txtCo2Vol);
		txtCo2Vol.setColumns(10);
		
		
		
		JLabel lblNewLabel_3 = new JLabel("O2%");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		panel_4.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		textO2Secc = new JTextField();
		GridBagConstraints gbc_textO2Secc = new GridBagConstraints();
		gbc_textO2Secc.fill = GridBagConstraints.HORIZONTAL;
		gbc_textO2Secc.anchor = GridBagConstraints.NORTH;
		gbc_textO2Secc.insets = new Insets(0, 0, 5, 5);
		gbc_textO2Secc.gridx = 1;
		gbc_textO2Secc.gridy = 3;
		panel_4.add(textO2Secc, gbc_textO2Secc);
		textO2Secc.setColumns(10);
		
		txtO2Umidi = new JTextField();
		GridBagConstraints gbc_txtO2Umidi = new GridBagConstraints();
		gbc_txtO2Umidi.gridwidth = 2;
		gbc_txtO2Umidi.insets = new Insets(0, 0, 5, 5);
		gbc_txtO2Umidi.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtO2Umidi.gridx = 3;
		gbc_txtO2Umidi.gridy = 3;
		panel_4.add(txtO2Umidi, gbc_txtO2Umidi);
		txtO2Umidi.setColumns(10);
		
		txtO2Vol = new JTextField();
		GridBagConstraints gbc_txtO2Vol = new GridBagConstraints();
		gbc_txtO2Vol.anchor = GridBagConstraints.NORTH;
		gbc_txtO2Vol.insets = new Insets(0, 0, 5, 5);
		gbc_txtO2Vol.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtO2Vol.gridx = 6;
		gbc_txtO2Vol.gridy = 3;
		panel_4.add(txtO2Vol, gbc_txtO2Vol);
		txtO2Vol.setColumns(10);
		
		JLabel lblN = new JLabel("N2%");
		GridBagConstraints gbc_lblN = new GridBagConstraints();
		gbc_lblN.anchor = GridBagConstraints.NORTH;
		gbc_lblN.insets = new Insets(0, 0, 5, 5);
		gbc_lblN.gridx = 0;
		gbc_lblN.gridy = 4;
		panel_4.add(lblN, gbc_lblN);
		
		txtN2secchi = new JTextField();
		GridBagConstraints gbc_txtN2secchi = new GridBagConstraints();
		gbc_txtN2secchi.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtN2secchi.anchor = GridBagConstraints.NORTH;
		gbc_txtN2secchi.insets = new Insets(0, 0, 5, 5);
		gbc_txtN2secchi.gridx = 1;
		gbc_txtN2secchi.gridy = 4;
		panel_4.add(txtN2secchi, gbc_txtN2secchi);
		txtN2secchi.setColumns(10);
		
		txtN2Umidi = new JTextField();
		GridBagConstraints gbc_txtN2Umidi = new GridBagConstraints();
		gbc_txtN2Umidi.gridwidth = 2;
		gbc_txtN2Umidi.insets = new Insets(0, 0, 5, 5);
		gbc_txtN2Umidi.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtN2Umidi.gridx = 3;
		gbc_txtN2Umidi.gridy = 4;
		panel_4.add(txtN2Umidi, gbc_txtN2Umidi);
		txtN2Umidi.setColumns(10);
		
		txtN2Vol = new JTextField();
		GridBagConstraints gbc_txtN2Vol = new GridBagConstraints();
		gbc_txtN2Vol.insets = new Insets(0, 0, 5, 5);
		gbc_txtN2Vol.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtN2Vol.gridx = 6;
		gbc_txtN2Vol.gridy = 4;
		panel_4.add(txtN2Vol, gbc_txtN2Vol);
		txtN2Vol.setColumns(10);
		
		JLabel lblH = new JLabel("H20%");
		GridBagConstraints gbc_lblH = new GridBagConstraints();
		gbc_lblH.anchor = GridBagConstraints.NORTH;
		gbc_lblH.insets = new Insets(0, 0, 5, 5);
		gbc_lblH.gridx = 0;
		gbc_lblH.gridy = 5;
		panel_4.add(lblH, gbc_lblH);
		
		txtH2O = new JTextField();
		GridBagConstraints gbc_txtH2O = new GridBagConstraints();
		gbc_txtH2O.gridwidth = 2;
		gbc_txtH2O.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtH2O.insets = new Insets(0, 0, 5, 5);
		gbc_txtH2O.anchor = GridBagConstraints.NORTH;
		gbc_txtH2O.gridx = 3;
		gbc_txtH2O.gridy = 5;
		panel_4.add(txtH2O, gbc_txtH2O);
		txtH2O.setColumns(10);
		
		txtH20Vol = new JTextField();
		GridBagConstraints gbc_txtH20Vol = new GridBagConstraints();
		gbc_txtH20Vol.insets = new Insets(0, 0, 5, 5);
		gbc_txtH20Vol.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtH20Vol.gridx = 6;
		gbc_txtH20Vol.gridy = 5;
		panel_4.add(txtH20Vol, gbc_txtH20Vol);
		txtH20Vol.setColumns(10);
		
		lblVolumiTotatli = new JLabel("Volumi totatli");
		GridBagConstraints gbc_lblVolumiTotatli = new GridBagConstraints();
		gbc_lblVolumiTotatli.insets = new Insets(0, 0, 0, 5);
		gbc_lblVolumiTotatli.gridx = 0;
		gbc_lblVolumiTotatli.gridy = 6;
		panel_4.add(lblVolumiTotatli, gbc_lblVolumiTotatli);
		
		txtVoltot = new JTextField();
		GridBagConstraints gbc_txtVoltot = new GridBagConstraints();
		gbc_txtVoltot.insets = new Insets(0, 0, 0, 5);
		gbc_txtVoltot.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtVoltot.gridx = 6;
		gbc_txtVoltot.gridy = 6;
		panel_4.add(txtVoltot, gbc_txtVoltot);
		txtVoltot.setColumns(10);
		
		panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.anchor = GridBagConstraints.EAST;
		gbc_panel_3.gridheight = 2;
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.VERTICAL;
		gbc_panel_3.gridx = 4;
		gbc_panel_3.gridy = 1;
		panel_1.add(panel_3, gbc_panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblPortataGas = new JLabel("Portata gas");
		panel_3.add(lblPortataGas);
		
		txtPortatagas = new JTextField();
		panel_3.add(txtPortatagas);
		txtPortatagas.setColumns(10);
		
		lblPotenza = new JLabel("Potenza");
		panel_3.add(lblPotenza);
		
		txtPotenza = new JTextField();
		panel_3.add(txtPotenza);
		txtPotenza.setColumns(10);
		
		JLabel lblPortataMassica = new JLabel("Portata massica");
		panel_3.add(lblPortataMassica);
		
		txtPortatamass = new JTextField();
		panel_3.add(txtPortatamass);
		txtPortatamass.setColumns(10);
		
		JLabel lblTemperaatura = new JLabel("Temperatura alla potenza nominale");
		panel_3.add(lblTemperaatura);
		
		textTmax = new JTextField();
		panel_3.add(textTmax);
		textTmax.setColumns(10);
		
		lblTempMinima = new JLabel("Temperatura alla potenza minima");
		panel_3.add(lblTempMinima);
		
		textTmin = new JTextField();
		panel_3.add(textTmin);
		textTmin.setColumns(10);
		
		lblStatus = new JLabel(".....:");
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.anchor = GridBagConstraints.WEST;
		gbc_lblStatus.gridwidth = 5;
		gbc_lblStatus.gridx = 0;
		gbc_lblStatus.gridy = 4;
		panel_1.add(lblStatus, gbc_lblStatus);
		
		panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.gridwidth = 2;
		gbc_panel_5.insets = new Insets(0, 0, 0, 5);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 3;
		add(panel_5, gbc_panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel_4 = new JLabel("<html> La temperatura dei fumi viene calcolata i base alla Norma UNI 10640 formula 49 per caldaie tipo B.<br>saxas</html>");
		lblNewLabel_4.setVerticalAlignment(SwingConstants.TOP);
		panel_5.add(lblNewLabel_4);

	}
	/***
	 * 	CALCOLO
	 */
private void Calcola(){
try{
	combustibile=(String)comboBox.getModel().getSelectedItem();
	if(combustibile.equals("Metano")){
		this.combustione=CombFactory.getInstance().getCombustione("METANO");
	}else{
		this.lblStatus.setText("Selezionare combustibile");
		Toolkit.getDefaultToolkit().beep();
		return ;
	}
	
	/**
	 * cALCOLO IN BASE A ECCESSO D'ARIA O CO2% SECCA
	 */
	if(rdbtnCO2.isSelected()){
		Double co2=(Double)this.spinnerCO2Secc.getModel().getValue();
		co2_perc_sec=co2.doubleValue();
		/**
		 * VIENE PRODOTTO IL FLUIDO FUMO
		 */
			/**
			 * 	Viene verificato se è nota la potenza per il calcolo della portata massica fumi
			 */
		if(this.chckbxPotenzaNota.isSelected()){
			potenza=((Double)this.spinnerKw.getModel().getValue()).doubleValue();
			fumo=combustione.CombustioneCO2Secc(co2_perc_sec,potenza);
		}else{
		fumo=combustione.CombustioneCO2Secc(co2_perc_sec);
		}
	}else if(rdbtnEccAria.isSelected()){
		Double ea=(Double)this.spinnerEccAria.getModel().getValue();
		ecc_aria=ea.doubleValue();
		/**
		 * VIENE PRODOTTO IL FLUIDO FUMO
		 */
			/**
			 * 	Viene verificato se è nota la potenza per il calcolo della portata massica fumi
			 */
		if(this.chckbxPotenzaNota.isSelected()){
			potenza=((Double)this.spinnerKw.getModel().getValue()).doubleValue();
			fumo=combustione.CombustioneEccAria(potenza,ecc_aria);
		}else{
		fumo=combustione.CombustioneEccAria(ecc_aria);
		}
	}
	/**
	 * VERIFICA SE 'E NOTO IL RENDIMENTO : quindi calcola la temperatura fumi
	 * alla massima potenza e alla minima potenza
	 */
	if(this.chckbxRendimentoNoto.isSelected()){
		double rend=((Double)this.spinnerRend.getModel().getValue()).doubleValue();
		temp_max=combustione.TemperaturaFumiPmax(rend);
		temp_min=combustione.TemperaturaFumiPmin(rend);
		textTmax.setText(String.format("%1.2f K %1.2f °C ",temp_max,temp_max-273.15));
		textTmin.setText(String.format("%1.2f K  %1.2f °C",temp_min,temp_min-273.15));
	}else{
		textTmax.setText(" ");
		textTmin.setText(" ");
	}
	/**
	 * CREO UN RIFERIMENTO ALLA CLASSE ASTRATTA CombustioneBase PER ACCEDERE
	 * A TUTTE LE PROPIETA' 
	 */
	CombustioneBase cb=(CombustioneBase )combustione;
	
	/*****************
	 * 		STAMPO OUTPUT
	 */
	this.txtEccAria.setText(String.format("%1.2f",cb.getEcc_aria()));
	this.txtCo2Umidi.setText(String.format("%1.2f",cb.getCo2_perc_umida()*100));
	this.txtCO2secco.setText(String.format("%1.2f",cb.getCo2_perc_secca()*100));
	this.textO2Secc.setText(String.format("%1.2f",cb.getO2_perc_secca()*100));
	this.txtO2Umidi.setText((String.format("%1.2f",cb.getO2_perc_umida()*100)));
	this.txtN2Umidi.setText(String.format("%1.2f",cb.getN2_perc_umida()*100));
	this.txtN2secchi.setText(String.format("%1.2f",cb.getN2_perc_secca()*100));
	
	this.txtH2O.setText((String.format("%1.2f",cb.getH2o_perc()*100)));
	this.txtCo2Vol.setText(String.format("%1.5f",cb.getVolumi_co2()));
	this.txtN2Vol.setText(String.format("%1.5f",cb.getVolumi_n2()));
	this.txtO2Vol.setText(String.format("%1.5f",cb.getVolumi_o2()));
	this.txtH20Vol.setText(String.format("%1.5f",cb.getVolumi_h20()));
	this.txtVoltot.setText(String.format("%1.5f",cb.getVolumi_totali()));
	
	this.txtPortatagas.setText(String.format("%1.4f",cb.getVolume_gas()));
	this.txtPotenza.setText(String.format("%1.4f",cb.getPotenza()));
	this.txtPortatamass.setText(String.format("%1.4f",cb.getPortata_mass_fumi()));
	
	lblStatus.setText("Fumo= "+fumo.toString());
}catch(Exception e){
	e.printStackTrace();
Toolkit.getDefaultToolkit().beep();
this.lblStatus.setText("Errore nel calcolo...");
}
	
	
}
class AVerifier extends InputVerifier{
	 public boolean shouldYieldFocus(JComponent input) {
	        boolean inputOK = verify(input);
	     //   makeItPretty(input);
	        if (inputOK) {
	        	Calcola();
	                return true;
	        } else {
	            Toolkit.getDefaultToolkit().beep();
	            return true;
	        }
	    }
	
	@Override
	public boolean verify(JComponent input) {
		if (input == spinnerKw) {
          try{
       	   Double kw=(Double)spinnerKw.getModel().getValue();
       	   return true;
          }catch(Exception e){
       	return false;   
          }
   
		} else if (input == spinnerCO2Secc) {
	           try{
	        	   //rug=Double.parseDouble(textFieldRug.getText());
	        	   return true;
	           }catch(Exception e){
	        	return false;   
	           }

       } else if(input ==chckbxPotenzaNota){
       	try{
       		if (chckbxPotenzaNota.isSelected()) {
					//scabr=Double.parseDouble(textFieldScabr.getText());
					
				} else {
					//scabr=rug/diam;
					//textFieldScabr.setText(Double.toString(scabr));
				}
       		return true;
       	}catch(Exception e){
       		return false;		
       	}
       						
       }
		return false;
	}

}

	/**
	 * Per intercettare i cambiamenti di stato dei checkBox
	 */
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		Object obj = arg0.getItem();
		if (obj.equals(chckbxPotenzaNota)) {
			if (chckbxPotenzaNota.isSelected())
				this.spinnerKw.setEnabled(true);
			else
				this.spinnerKw.setEnabled(false);
			
		} else if (obj.equals(chckbxRendimentoNoto)) {
			if(chckbxRendimentoNoto.isSelected())
				this.spinnerRend.setEnabled(true);
			else
				this.spinnerRend.setEnabled(false);
		}else if(obj.equals(this.rdbtnCO2) || obj.equals(this.rdbtnEccAria)){
			if(	rdbtnCO2.isSelected()){
				this.spinnerCO2Secc.setEnabled(true);
				this.spinnerEccAria.setEnabled(false);
			}else{
				this.spinnerCO2Secc.setEnabled(false);
				this.spinnerEccAria.setEnabled(true);
			}
		}

	}
}
