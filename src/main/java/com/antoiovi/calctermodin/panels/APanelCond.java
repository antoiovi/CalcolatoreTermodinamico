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

public class APanelCond extends JPanel implements ActionListener, APTubo {
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
	double altezza;
	double coeff_limin_est;
	String strfloat2d = "%1.2f";
	String strfloat4d = "%1.4f";
	
	String forma;
	String strfloat = "%1.2f";
	private JSpinner txtLungh;
	AVerifier verifier;
	private JTextField textField;
	private JTextField textField_1;
	private final JTextField textField_2 = new JTextField();
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JSpinner spinnerH;
	private JSpinner spinnerCle;
	private JPanel panel;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JLabel lblCondottoSemplice;

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
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		forma = "Circolare";
		comboBoxSezione = new JComboBox();
		comboBoxSezione.setModel(new DefaultComboBoxModel(new String[] {
				"Circolare", "Rettangolare", "Quadrato" }));
		comboBoxSezione.setActionCommand("Sezione");
		comboBoxSezione.addActionListener(this);
		
		lblCondottoSemplice = new JLabel("Condotto semplice");
		panel_1.add(lblCondottoSemplice);
		panel_1.add(comboBoxSezione);
		
		panel = new JPanel();
		panel_1.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
				textDiametro = new JSpinner();
				panel.add(textDiametro);
				textDiametro.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						CreaTubo();
					}
				});
		textDiametro.setModel(new SpinnerNumberModel(100.0, 100.0,600.0, 10.0));
		textDiametro.setInputVerifier(verifier);
		
		panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
				textLatoA = new JSpinner();
				panel_2.add(textLatoA);
				textLatoA.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						CreaTubo();
					}
				});
		textLatoA.setModel(new SpinnerNumberModel(150.0,150.0,600.0, 10.0));
		
		panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
				textLatoB = new JSpinner();
				panel_3.add(textLatoB);
				textLatoB.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						CreaTubo();
					}
				});
		textLatoB.setModel(new SpinnerNumberModel(150.0,150.0,600.0, 10.0));
		
		panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));

		textSpessore = new JSpinner();
		panel_4.add(textSpessore);
		textSpessore.setModel(new SpinnerNumberModel(0.0,0.0,50.0, 1.0));
		
		panel_5 = new JPanel();
		panel_1.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));
		
				textRugosita = new JFormattedTextField();
				panel_5.add(textRugosita);
				textRugosita.setToolTipText("Click dx per help\r\n");
				textRugosita.setColumns(10);
				
				textRugosita.setInputVerifier(verifier);
				
						JPopupMenu popupMenu = new JPopupMenu();
						addPopup(textRugosita, popupMenu);
						
								JMenuItem mntmHelprugosita = new JMenuItem("HelpRugosita");
								mntmHelprugosita.addActionListener(this);
								popupMenu.add(mntmHelprugosita);
	
		
		panel_6 = new JPanel();
		panel_1.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 2, 0, 0));
		
				textResterm = new JSpinner();
				panel_6.add(textResterm);
				textResterm.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent arg0) {
						CreaTubo();
					}
				});
				textResterm .setModel(new SpinnerNumberModel(0, 0, 1.0, 0.01));
				
				JPopupMenu popupMenu_1 = new JPopupMenu();
				addPopup(textResterm, popupMenu_1);
				
				JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
				popupMenu_1.add(mntmNewMenuItem);
		/**
		 * Altezza : deve essere minore d o uguale allo sviluppo
		 */
		
		panel_7 = new JPanel();
		panel_1.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 2, 0, 0));
		
		txtLungh = new JSpinner();
		panel_7.add(txtLungh);
		txtLungh.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				CreaTubo();
			}
		});
		txtLungh .setModel(new SpinnerNumberModel(1.0, 1.0, 5.0, 0.1));
		JSpinner.NumberEditor ne=new JSpinner.NumberEditor(txtLungh , "##0.#");
		DecimalFormat df=ne.getFormat();
		txtLungh .setEditor(ne);
		spinnerH = new JSpinner();
		spinnerH.setModel(new SpinnerNumberModel(0.0, 0.0, 5.0, 0.1));
		spinnerH.setInputVerifier(verifier);
		panel_1.add(spinnerH);
		/**
		 * Coefficiente liminare esterno
		 */
		spinnerCle = new JSpinner();
		spinnerCle.setModel(new SpinnerNumberModel(8.0, 8.0, 23.0, 0.5));
		panel_1.add(spinnerCle);

		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		panel_1.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBackground(Color.WHITE);
		textField_1.setEditable(false);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		textField_2.setBackground(Color.WHITE);
		textField_2.setEditable(false);
		panel_1.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBackground(Color.WHITE);
		textField_3.setEditable(false);
		panel_1.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBackground(Color.WHITE);
		textField_4.setEditable(false);
		panel_1.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setBackground(Color.WHITE);
		textField_5.setEditable(false);
		panel_1.add(textField_5);
		textField_5.setColumns(10);

		circolare();
		tubo = TuboFactory.getInstace().TuboC(100,
				105, 100, 0.001, 1);
	this.setTubo(tubo);
	}

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
			CreaTubo();
		}

	}

	@Override
	public void setTubo(Tubo tub) {
		// tubo=tub;
		rug = tubo.Rug();
		lungh = tubo.Lung();
		spessore = tubo.Spessore();
		rter = tubo.Resterm();
		if (tubo instanceof TuboC) {
			TuboC tc = (TuboC) tubo;
			diametro = tc.getDiam_int();
			circolare();
		} else if (tubo instanceof TuboR) {
			TuboR tr = (TuboR) tubo;
			latoa = tr.getLato_lung();
			latob = tr.getLato_cort();
			rettangolare();
		} else if (tubo instanceof TuboQ) {
			TuboQ tq = (TuboQ) tubo;
			latoa = tq.getLato();
			latob = tq.getLato();
			rettangolare();
		}
		setValues();

	}

	private void circolare() {
		this.textDiametro.setEnabled(true);
		this.textLatoA.setEnabled(false);
		this.textLatoB.setEnabled(false);
	}

	private void rettangolare() {
		this.textDiametro.setEnabled(false);
		this.textLatoA.setEnabled(true);
		this.textLatoB.setEnabled(true);
	}

	private void quadrato() {
		this.textDiametro.setEnabled(false);
		this.textLatoA.setEnabled(true);
		this.textLatoB.setEnabled(false);
	}

	private void setValues() {
		textRugosita.setText(String.format("%1.6f", rug));
		if (tubo != null) {
			this.textField.setText(String.format(strfloat4d, tubo.Per_int()));
			this.textField_1.setText(String.format(strfloat4d,tubo.Per_est()));
			this.textField_2.setText(String.format(strfloat4d,tubo.Area_int()));
			this.textField_3.setText(String.format(strfloat4d,tubo.Area_est()));
			this.textField_4.setText(String.format(strfloat4d,tubo.Dh()));
			this.textField_5.setText(String.format(strfloat4d,tubo.Dhe()));
		} else {
			setoutString("Err");
		}
	}

	private void CreaTubo() {
		if(!verifica()){
			setoutString("Err");
			return ;
		}
		try {
			
			if (forma.equals("Circolare")) {
				tubo = TuboFactory.getInstace().TuboC(diametro,
						diametro + 2 * spessore, lungh, rter, rug);
			} else if (forma.equals("Rettangolare")) {
				tubo = TuboFactory.getInstace().TuboR(latoa, latob, spessore,
						lungh, rter, rug);
			} else if (forma.equals("Quadrato")) {
				tubo = TuboFactory.getInstace().TuboQ(latoa, spessore, lungh,
						rter, rug);
			} else {
				tubo = null;
			}
			if (tubo != null) {
				this.textField.setText(String.format("%1.4f", tubo.Per_int()));
				this.textField_1.setText(String.format("%1.4f",tubo.Per_est()));
				this.textField_2.setText(String.format("%1.4f",tubo.Area_int()));
				this.textField_3.setText(String.format("%1.4f",tubo.Area_est()));
				this.textField_4.setText(String.format("%1.4f",tubo.Dh()));
				this.textField_5.setText(String.format("%1.4f",tubo.Dhe()));
			} else {
				setoutString("Err");
			}
		} catch (Exception e) {
			tubo = null;
		}
	}
	
	private void setoutString(String s){
		this.textField.setText(s);
		this.textField_1.setText(s);
		this.textField_2.setText(s);
		this.textField_3.setText(s);
		this.textField_4.setText(s);
		this.textField_5.setText(s);
	}

	@Override
	public Tubo getTubo() {
		CreaTubo();
		return tubo;
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
				CreaTubo();
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
				}else if(input ==spinnerH){
					/*
					 * Verifica che altezza non sia superiore a lunghezza
					 */
					double h=(Double)spinnerH.getModel().getValue();
					if(h>lungh){
						spinnerH.getModel().setValue(altezza);
						return false;
						}
					else
						altezza=h;
					return true;
					
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

}// FINE CLASSE PANEL TUBO
