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
	
	// Creata per inizzializzare i valori 
	private String stringButton;

	
	String labels[]={"Tubi in plastica liscio(scarico caldaie condesazione)","Tubi in plastica corrigato(scarico caldaie condesazione)","Tubo in acciaio moderatamente arruginito",
					"Condotto in lamiera","Condotto in conglomerato cementizio",
					"Refrattario","Condotto in muratura"};
	double minimo[]={0.008e-3,2.85e-3,0.0005,0.0015,0.001,0.001,0.003};
	double massimo[]={0.008e-3,6.78e-3,0.001, 0.002,0.003,0.002,0.005};
	
	JLabel jlabales[]=new JLabel[7];
	JButton jbuttonsMin[]=new JButton[7];
	JButton jbuttonsMax[]=new JButton[7];
	
	
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
		setBounds(100, 100, 450, 350);
		init();
		
	}
	public JIF_Rugosita() {
	    super();
	    this.setModal(true);
	    setTitle("Rugosit\u00E0 media perete interna");
	    setResizable(false);
		setBounds(100, 100, 450, 350);
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

		
		GridBagConstraints gbl = new GridBagConstraints();
		gbl.insets = new Insets(0, 0, 5, 5);
		
		JLabel lblNewLabel = new JLabel("Modalit\u00E0 costruttive");
				
		gbl.gridx = 0;
		gbl.gridy = 0;
		getContentPane().add(lblNewLabel, gbl);

		JLabel lblNewLabel_1 = new JLabel("Rugosit\u00E0 [m]");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbl.gridwidth = 2;
		gbl.gridx = 1;
		gbl.gridy = 0;
		getContentPane().add(lblNewLabel_1, gbl);

		JLabel lblNewLabel_8 = new JLabel("da");
		gbl.gridwidth = 1;
		gbl.gridx = 1;
		gbl.gridy = 1;
		getContentPane().add(lblNewLabel_8, gbl);

		JLabel lblA = new JLabel("a");
		gbl.gridx = 2;
		gbl.gridy = 1;
		getContentPane().add(lblA, gbl);

		int gridY=1;
		gbl.fill = GridBagConstraints.HORIZONTAL;
		gbl.anchor = GridBagConstraints.SOUTH;
		for(int x=0;x<labels.length;x++){
			gridY++;
			jlabales[x]=new JLabel(labels[x]);
			gbl.gridx = 0;
			gbl.gridy = gridY;
			getContentPane().add(jlabales[x],gbl);
			stringButton=Double.toString(minimo[x]);
			jbuttonsMin[x] = new JButton(stringButton );
			jbuttonsMin[x].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				JButton btn=(JButton)arg0.getSource();
				textFieldValue.setText( btn.getText() );
					}
				});
			gbl.gridx = 1;
			getContentPane().add(jbuttonsMin[x],gbl);
			stringButton=Double.toString(massimo[x]);
			jbuttonsMax[x] = new JButton( stringButton);
			jbuttonsMax[x].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JButton btn=(JButton)arg0.getSource();
				textFieldValue.setText( btn.getText() );
					}
				});
			gbl.gridx = 2;
			getContentPane().add(jbuttonsMax[x],gbl);
		}
		gridY++;
			JLabel lblNewLabel_7 = new JLabel("<html>Valori forniti dalla norma UNI 9615.<br>Questi valori possono diventare pi\u00F9 grandi nel caso di esecuzione non corretta del canale da fumo e del camino.</html>");
				gbl.fill = GridBagConstraints.HORIZONTAL;
				gbl.gridwidth = 3;
				gbl.gridx = 0;
				gbl.gridy = gridY;
				getContentPane().add(lblNewLabel_7, gbl);
				gridY++;		
				JPanel panel = new JPanel();
				gbl.fill = GridBagConstraints.BOTH;
				gbl.gridy = gridY;
				getContentPane().add(panel, gbl);
				panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				
						JLabel lblInserireValore = new JLabel("Inserire valore");
						panel.add(lblInserireValore);
						
								textFieldValue = new JTextField();
								panel.add(textFieldValue);
								textFieldValue.setColumns(10);

		JPanel panel_1 = new JPanel();
		gbl.gridy = gridY;
		getContentPane().add(panel_1, gbl);

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
