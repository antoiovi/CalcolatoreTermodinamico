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
import java.util.ArrayList;
import java.util.List;
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

import java.awt.Font;

public class APanelCond extends JPanel implements ActionListener,APCondotto{
	private JComboBox comboBoxSezione;
	private JSpinner textDiametro;
	private JSpinner textLatoA;
	private JSpinner textLatoB;
	private JSpinner textSpessore;
	private JFormattedTextField textRugosita;
	private JSpinner textResterm;
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
	private JSpinner spSviluppo;
	AVerifier verifier;
	private JTextField txtTipo;
	private JTextField txtTitolo;
	private JPanel panel;
	/**
	 * Lista di APconodtti collegati a cui fare seguire alcune azioni
	 */
	List<APCondotto> apcondotti;
	private JSpinner spPerdite;
	
	/**
	 * Create the panel.
	 */
	public APanelCond() {
		DecimalFormatSymbols dot_decimal = new DecimalFormatSymbols();
		dot_decimal.setDecimalSeparator('.');
		dot_decimal.setGroupingSeparator(',');

		Locale.setDefault(Locale.US);
		setLayout(new BorderLayout(0, 0));
		verifier = new AVerifier();
		JPanel panel_1 = new JPanel();

		this.add(panel_1);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("116px"),},
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
				FormFactory.DEFAULT_ROWSPEC,}));
		forma = "Circolare";
		comboBoxSezione = new JComboBox();
		comboBoxSezione.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxSezione.setModel(new DefaultComboBoxModel(new String[] {
				"Circolare", "Rettangolare", "Quadrato" }));
		comboBoxSezione.setActionCommand("Sezione");
		comboBoxSezione.addActionListener(this);
		
		txtTitolo = new JTextField();
		txtTitolo.setEditable(false);
		txtTitolo.setText("Titolo");
		panel_1.add(txtTitolo, "1, 1, fill, default");
		txtTitolo.setColumns(10);
		
		panel = new JPanel();
		panel_1.add(panel, "1, 3, fill, fill");
		panel.setLayout(new BorderLayout(0, 0));
		
		txtTipo = new JTextField();
		panel.add(txtTipo);
		txtTipo.setEditable(false);
		txtTipo.setText("Condotto semplice");
		txtTipo.setColumns(10);
		panel_1.add(comboBoxSezione, "1, 5, fill, fill");
		
				textDiametro = new JSpinner();
				textDiametro.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						//CreaTubo();
					}
				});
				textDiametro.setModel(new SpinnerNumberModel(100.0, 100.0,600.0, 10.0));
				
				panel_1.add(textDiametro, "1, 7, fill, fill");
				textDiametro.setInputVerifier(verifier);
		
				textLatoA = new JSpinner();
				textLatoA.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						//CreaTubo();
					}
				});
				textLatoA.setModel(new SpinnerNumberModel(150.0,150.0,600.0, 10.0));
				panel_1.add(textLatoA, "1, 9, fill, fill");
		
				textLatoB = new JSpinner();
				textLatoB.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
					//	CreaTubo();
					}
				});
				textLatoB.setModel(new SpinnerNumberModel(150.0,150.0,600.0, 10.0));
				panel_1.add(textLatoB, "1, 11, fill, fill");
		
				textSpessore = new JSpinner();
				textSpessore.setModel(new SpinnerNumberModel(0.0,0.0,50.0, 1.0));
				panel_1.add(textSpessore, "1, 13, fill, fill");
		
				textRugosita = new JFormattedTextField();
				textRugosita.setToolTipText("Click dx per help\r\n");
				panel_1.add(textRugosita, "1, 15, fill, fill");
				textRugosita.setColumns(10);
				
				textRugosita.setInputVerifier(verifier);
				
						JPopupMenu popupMenu = new JPopupMenu();
						addPopup(textRugosita, popupMenu);
						
								JMenuItem mntmHelprugosita = new JMenuItem("HelpRugosita");
								mntmHelprugosita.addActionListener(this);
								popupMenu.add(mntmHelprugosita);
		
				textResterm = new JSpinner();
				textResterm.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent arg0) {
				//		CreaTubo();
					}
				});
				textResterm .setModel(new SpinnerNumberModel(0, 0, 1.0, 0.01));
				panel_1.add(textResterm, "1, 17, fill, fill");
				
				JPopupMenu popupMenu_1 = new JPopupMenu();
				addPopup(textResterm, popupMenu_1);
				
				JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
				popupMenu_1.add(mntmNewMenuItem);
		
		spSviluppo = new JSpinner();
		spSviluppo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
		//		CreaTubo();
			}
		});
		spSviluppo .setModel(new SpinnerNumberModel(1.0, 1.0, 5.0, 0.1));
		JSpinner.NumberEditor ne_spSviluppo=new JSpinner.NumberEditor(spSviluppo , "##0.#");
		DecimalFormat df=ne_spSviluppo.getFormat();
		spSviluppo .setEditor(ne_spSviluppo);
		
		panel_1.add(spSviluppo, "1, 19, fill, fill");
		
		JSpinner spAltezza = new JSpinner();
		panel_1.add(spAltezza, "1, 21, fill, default");
		
		JSpinner spCoefflimest = new JSpinner();
		panel_1.add(spCoefflimest, "1, 23, fill, default");
		
		spPerdite = new JSpinner();
		panel_1.add(spPerdite, "1, 25");

		apcondotti=new ArrayList<APCondotto>();
		circolare();
		tubo = TuboFactory.getInstace().TuboC(100,
				105, 100, 0.001, 1);
	//this.setTubo(tubo);
	
		
		
	}
	/***
	 ***	FINE COSTRUTTORE
	 ****/

	/*************************************************************
	 * AGGIUNGE IL POPUPMENU
	 * @param component
	 * @param popup
	 */
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
/************************************************
 *  	ACTION PERFORMED
 *****************************************/
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String COMMAND = arg0.getActionCommand();
		if (COMMAND.equals("HelpRugosita")) {
			JIF_Rugosita j = new JIF_Rugosita();
			j.setVisible(true);
			if (j.getValue().equals("ok")) {
				this.textRugosita.setText(String.valueOf(j.getDoblevalue()));
			}
			j.dispose();
		} else if (COMMAND.equals("Sezione")) {
			forma = (String) comboBoxSezione.getModel().getSelectedItem();
			if (forma.equals("Circolare"))
				circolare();
			else if (forma.equals("Rettangolare"))
				rettangolare();
			else if (forma.equals("Quadrato"))
				quadrato();
			//CreaTubo();
		}

	}

	

	public void circolare() {
		this.textDiametro.setEnabled(true);
		this.textLatoA.setEnabled(false);
		this.textLatoB.setEnabled(false);
		for(APCondotto apc :apcondotti){
			apc.circolare();
		}
	}

	public void rettangolare() {
		this.textDiametro.setEnabled(false);
		this.textLatoA.setEnabled(true);
		this.textLatoB.setEnabled(true);
		for(APCondotto apc :apcondotti){
			apc.rettangolare();
		}
	}

	public void quadrato() {
		this.textDiametro.setEnabled(false);
		this.textLatoA.setEnabled(true);
		this.textLatoB.setEnabled(false);
		for(APCondotto apc :apcondotti){
			apc.quadrato();
		}
	}

	private void setValues() {
		textRugosita.setText(String.format("%1.6f", rug));
		
	}

	
	/**
	 * vERIFICA EI DATI E INIZZIALIZZA LE VARIABILI
	 * @return
	 */
private boolean verifica(){
	try{
		rter =(Double)textResterm.getModel().getValue();
		rug = Double.parseDouble(textRugosita.getText());
		diametro =(Double)textDiametro.getModel().getValue()/100;
		latoa = (Double)textLatoA.getModel().getValue()/100;
		latob = (Double)textLatoB.getModel().getValue()/100;
		spessore = (Double)textSpessore.getModel().getValue();
		spessore/=1000;

return true;
}catch(Exception e){
	return false;
}
}

	class AVerifier extends InputVerifier {
		public boolean shouldYieldFocus(JComponent input) {
			boolean inputOK = verify(input);
			// makeItPretty(input);
			if (inputOK) {
				input.setBackground(Color.white);
				//CreaTubo();
				return false;
			} else {
				input.setBackground(Color.red);
				Toolkit.getDefaultToolkit().beep();
				return true;
			}
		}
		
		@Override
		public boolean verify(JComponent input) {
			// if (input == textResterm) {
			/*
			 * 
			 * try { rter = Double.parseDouble(textResterm.getText()); return
			 * true; } catch (Exception e) { // textResterm.setText("Errore");
			 * return false; } } else
			 */

			if (input == textRugosita) {
				try {
					rug = Double.parseDouble(textRugosita.getText());
					// textRugosita.setText(String.format("%1.6f", rug));
					return true;
				} catch (Exception e) {
					// textRugosita.setText("Errore");
					return false;
				}
			}/* else if (input == textDiametro) {
				try {
					diametro = Double.parseDouble(textDiametro.getText());
					// txtLungh.setText(String.format(strfloat, lungh));
					return true;
				} catch (Exception e) {
					return false;
				}
			} else if (input == textLatoA) {
				try {
					latoa = Double.parseDouble(textLatoA.getText());
					// txtLungh.setText(String.format(strfloat, lungh));
					return true;
				} catch (Exception e) {
					return false;
				}
			} else if (input == textLatoB) {
				try {
					latob = Double.parseDouble(textLatoB.getText());
					// txtLungh.setText(String.format(strfloat, lungh));
					return true;
				} catch (Exception e) {
					return false;
				}
			} else if (input == textSpessore) {
				try {
					spessore = Double.parseDouble(textSpessore.getText());
					spessore/=1000;
					// txtLungh.setText(String.format(strfloat, lungh));
					return true;
				} catch (Exception e) {
					return false;
				}
				*/
			
			return true;
		}			
		
				
			
	}// Fine classe inputVerifier
/**
 * IMPLEMENTAZIONE METODI INTERFACCIA APCondotto
 */
	/**
	 * 
	 * @param arg0
	 */
	public void setTitolo(String arg0) {
		txtTitolo.setText(arg0);
	}
	
	public void setTipo(String arg0){
		txtTipo.setText(arg0);
	}
	
	
	

	public JComboBox getComboBoxSezione() {
		return comboBoxSezione;
	}

	@Override
	public void setAPCondottiRealted(List<APCondotto> apcondotti) {
		this.apcondotti=apcondotti;		
	}

	@Override
	public void addAPCondottoRealted(APCondotto apcondotto) {
		this.apcondotti.add(apcondotto);
		
	}
}// FINE CLASSE PANEL TUBO
