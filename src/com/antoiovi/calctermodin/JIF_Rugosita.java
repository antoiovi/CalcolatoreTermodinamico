package com.antoiovi.calctermodin;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.SwingConstants;
import javax.swing.JPanel;

import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.awt.Dimension;

import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JIF_Rugosita extends JDialog implements ActionListener {
	SpinnerNumberModel model;
	JSpinner.NumberEditor editor;
	JSpinner s;
	private JTextField textFieldValue;
	private String value = "esc";
	private double doblevalue;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public JIF_Rugosita(JFrame parent, boolean modal) {
	    super(parent,  true);
	    setTitle("Rugosit\u00E0 media perete interna");
	    setResizable(false);
		setBounds(100, 100, 416, 291);
		init();
		
	}
	public JIF_Rugosita() {
	    super();
	    this.setModal(true);
	    setTitle("Rugosit\u00E0 media perete interna");
	    setResizable(false);
		setBounds(100, 100, 416, 291);
		init();
		
	}

	private void init(){
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 273, 52, 53, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 33, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Modalit\u00E0 costruttive");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Rugosit\u00E0 [m]");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

		JLabel lblNewLabel_8 = new JLabel("da");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 1;
		gbc_lblNewLabel_8.gridy = 1;
		getContentPane().add(lblNewLabel_8, gbc_lblNewLabel_8);

		JLabel lblA = new JLabel("a");
		GridBagConstraints gbc_lblA = new GridBagConstraints();
		gbc_lblA.insets = new Insets(0, 0, 5, 0);
		gbc_lblA.gridx = 2;
		gbc_lblA.gridy = 1;
		getContentPane().add(lblA, gbc_lblA);

		JLabel lblNewLabel_2 = new JLabel(
				"Tubo in acciaio moderatamente arruginito");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2.anchor = GridBagConstraints.SOUTH;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JButton button = new JButton("0.000 5");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldValue.setText(Double.toString(0.0005));
				
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 1;
		gbc_button.gridy = 2;
		getContentPane().add(button, gbc_button);
		
		JButton button_1 = new JButton("0.001");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldValue.setText(Double.toString(0.001));
			}
		});
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(0, 0, 5, 0);
		gbc_button_1.gridx = 2;
		gbc_button_1.gridy = 2;
		getContentPane().add(button_1, gbc_button_1);

		JLabel lblNewLabel_3 = new JLabel("Condotto in lamieral");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);

		JLabel lblNewLabel_11 = new JLabel("0.001 5");
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_11.gridx = 1;
		gbc_lblNewLabel_11.gridy = 3;
		getContentPane().add(lblNewLabel_11, gbc_lblNewLabel_11);

		JLabel lblNewLabel_12 = new JLabel("0.002");
		GridBagConstraints gbc_lblNewLabel_12 = new GridBagConstraints();
		gbc_lblNewLabel_12.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_12.gridx = 2;
		gbc_lblNewLabel_12.gridy = 3;
		getContentPane().add(lblNewLabel_12, gbc_lblNewLabel_12);

		JLabel lblNewLabel_4 = new JLabel("Condotto in conglomerato cementizio");
		lblNewLabel_4.setHorizontalTextPosition(SwingConstants.LEFT);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 4;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);

		JLabel lblNewLabel_13 = new JLabel("0.001");
		GridBagConstraints gbc_lblNewLabel_13 = new GridBagConstraints();
		gbc_lblNewLabel_13.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_13.gridx = 1;
		gbc_lblNewLabel_13.gridy = 4;
		getContentPane().add(lblNewLabel_13, gbc_lblNewLabel_13);

		JLabel lblNewLabel_14 = new JLabel("0.003");
		GridBagConstraints gbc_lblNewLabel_14 = new GridBagConstraints();
		gbc_lblNewLabel_14.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_14.gridx = 2;
		gbc_lblNewLabel_14.gridy = 4;
		getContentPane().add(lblNewLabel_14, gbc_lblNewLabel_14);

		JLabel lblNewLabel_5 = new JLabel("Refrattario");
		lblNewLabel_5.setHorizontalTextPosition(SwingConstants.LEFT);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 5;
		getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);

		JLabel lblNewLabel_15 = new JLabel("0.001");
		GridBagConstraints gbc_lblNewLabel_15 = new GridBagConstraints();
		gbc_lblNewLabel_15.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_15.gridx = 1;
		gbc_lblNewLabel_15.gridy = 5;
		getContentPane().add(lblNewLabel_15, gbc_lblNewLabel_15);

		JLabel lblNewLabel_16 = new JLabel("0.002");
		GridBagConstraints gbc_lblNewLabel_16 = new GridBagConstraints();
		gbc_lblNewLabel_16.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_16.gridx = 2;
		gbc_lblNewLabel_16.gridy = 5;
		getContentPane().add(lblNewLabel_16, gbc_lblNewLabel_16);

		JLabel lblNewLabel_6 = new JLabel("Condotto in muratura");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 6;
		getContentPane().add(lblNewLabel_6, gbc_lblNewLabel_6);

		JLabel lblNewLabel_17 = new JLabel("0.003");
		GridBagConstraints gbc_lblNewLabel_17 = new GridBagConstraints();
		gbc_lblNewLabel_17.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_17.gridx = 1;
		gbc_lblNewLabel_17.gridy = 6;
		getContentPane().add(lblNewLabel_17, gbc_lblNewLabel_17);

		JLabel lblNewLabel_18 = new JLabel("0.005");
		GridBagConstraints gbc_lblNewLabel_18 = new GridBagConstraints();
		gbc_lblNewLabel_18.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_18.gridx = 2;
		gbc_lblNewLabel_18.gridy = 6;
		getContentPane().add(lblNewLabel_18, gbc_lblNewLabel_18);
				
				JLabel lblNewLabel_7 = new JLabel("<html>Valori forniti dalla norma UNI 9615.<br>Questi valori possono diventare pi\u00F9 grandi nel caso di esecuzione non corretta del canale da fumo e del camino.</html>");
				GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
				gbc_lblNewLabel_7.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblNewLabel_7.gridwidth = 3;
				gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 0);
				gbc_lblNewLabel_7.gridx = 0;
				gbc_lblNewLabel_7.gridy = 7;
				getContentPane().add(lblNewLabel_7, gbc_lblNewLabel_7);
		
				JPanel panel = new JPanel();
				GridBagConstraints gbc_panel = new GridBagConstraints();
				gbc_panel.gridwidth = 3;
				gbc_panel.insets = new Insets(0, 0, 5, 0);
				gbc_panel.fill = GridBagConstraints.BOTH;
				gbc_panel.gridx = 0;
				gbc_panel.gridy = 8;
				getContentPane().add(panel, gbc_panel);
				panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				
						JLabel lblInserireValore = new JLabel("Inserire valore");
						panel.add(lblInserireValore);
						
								textFieldValue = new JTextField();
								panel.add(textFieldValue);
								textFieldValue.setColumns(10);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 3;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 9;
		getContentPane().add(panel_1, gbc_panel_1);

		JButton okButton = new JButton("Ok");
		okButton.setActionCommand("ok");
		okButton.addActionListener(this);
		panel_1.add(okButton);

		JButton escButton = new JButton("Annulla");
		escButton.setActionCommand("esc");
		escButton.addActionListener(this);
		panel_1.add(escButton);

	}
	public double getDoblevalue() {
		return doblevalue;
	}

	public void setDoblevalue(double doblevalue) {
		this.doblevalue = doblevalue;
	}

	public String getValue() {
		return value;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("ok")) {
			try {
				String s = textFieldValue.getText();
				doblevalue = Double.valueOf(s).doubleValue();
				value = "ok";
			} catch (Exception ex) {
				value = "esc";
			}
			this.setVisible(false);
		} else if (e.getActionCommand().equals("esc")) {
			value = "esc";
			this.setVisible(false);
		}
	}

}
