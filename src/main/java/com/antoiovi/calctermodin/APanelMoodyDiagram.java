package com.antoiovi.calctermodin;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.SwingConstants;

import java.awt.GridLayout;

import javax.swing.SwingConstants;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.concurrent.ExecutionException;

import javax.swing.JCheckBox;

import com.antoiovi.swing.APDiagram;
import com.antoiovi.swing.APanelDiagram;
import com.antoiovi.util.math.Geometry;

import com.antoiovi.unicig.condotti.MoodyDiagram;

import javax.swing.JScrollPane;

import java.awt.Dimension;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class APanelMoodyDiagram extends JPanel implements ActionListener,ItemListener{
	private JTextField textFieldNrey;
	private JTextField textFieldRug;
	private JTextField textFieldFattAtt;
	private JTextField textFieldDiam;
	private JTextField textFieldScabr;
	private JCheckBox ckboxScabrAMano;
/**
 * Numero di Reynold
 */
double nrey;
/**
 * Diametro idraulico interno del condotto
 */
double diam;
/**
 * Rugositia'
 */
double rug;
/**
 * Scabrezza relativa
 */
double scabr;
double fattattr;
private static double asseys[];
private static double assexs[];
private APanelDiagram diagram;
private APDiagram apdiagram;
private JTextArea textArea;

	/**
	 * Create the panel.
	 */
	public APanelMoodyDiagram() {

		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{181, 0, 0};
		gridBagLayout.rowHeights = new int[]{360, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
	//	gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		gbc_panel.weightx=0.2;
		add(panel, gbc_panel);
//		gbc_panel.weightx=0.0;
		int Y=0;

		GridBagLayout gbl_panel = new GridBagLayout();

		gbl_panel.columnWidths = new int[]{174, 0};
		gbl_panel.rowHeights = new int[]{19, 19, 19, 19, 19, 0, 0, 19, 0, 19, 0, 19, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.3,0.7};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("Numero di Reynolds [adimensionale]");
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridy = Y;
		panel.add(lblNewLabel, gbc_panel);
		Y++;
		
		textFieldNrey = new JTextField();
		textFieldNrey.setText("100000");
		gbc_panel.gridy = Y;
		panel.add(textFieldNrey, gbc_panel);
		Y++;

		JLabel lblRug =new JLabel("Rugosita'(click mouse dx per help)");
		gbc_panel.gridy = Y;
		panel.add(lblRug, gbc_panel);
		Y++;

		textFieldRug = new JTextField();
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(textFieldRug, popupMenu);
		
		JMenuItem mntmHelpRugosit = new JMenuItem("Help rugosit\u00E0");
		mntmHelpRugosit.setActionCommand("HelpRugosita");
		popupMenu.add(mntmHelpRugosit);
		mntmHelpRugosit.addActionListener(this);

		gbc_panel.gridy = Y;
		panel.add(textFieldRug, gbc_panel);
		textFieldRug.setColumns(10);
		Y++;
		
		JLabel lblNewLabel_2 = new JLabel("<html>Diametro<br/> ( metri se rugosita in metri <br/> mm se rugosita in mm)</html>", SwingConstants.CENTER);
		//"Diametro( in metri se");
		//gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_panel.gridy = Y;
		panel.add(lblNewLabel_2, gbc_panel);
		Y++;

		textFieldDiam = new JTextField();
		gbc_panel.gridy= Y;
		panel.add(textFieldDiam, gbc_panel);
		textFieldDiam.setColumns(10);
		Y++;

		JLabel lblscabrezzaRelativarugositdiamtero = new JLabel("<html>Scabrezza relativa<br>(Rugosit\u00E0/Diamtero)</html>");
		lblscabrezzaRelativarugositdiamtero.setHorizontalAlignment(SwingConstants.TRAILING);
		gbc_panel.gridy = Y;
		panel.add(lblscabrezzaRelativarugositdiamtero, gbc_panel);
		Y++;

/***
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.anchor = GridBagConstraints.WEST;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.VERTICAL;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 7;
		panel.add(panel_2, gbc_panel_2);
		
		ckboxScabrAMano = new JCheckBox(" Inserisci valore a mano");
		ckboxScabrAMano.setSelected(true);
		ckboxScabrAMano.addItemListener(this);
		panel_2.add(ckboxScabrAMano);
**/


		
		ckboxScabrAMano = new JCheckBox(" Inserisci valore a mano");
		ckboxScabrAMano.setSelected(true);
		ckboxScabrAMano.addItemListener(this);
		gbc_panel.gridy=Y;
		panel.add(ckboxScabrAMano,gbc_panel);
		Y++;

		
		textFieldScabr = new JTextField();
		textFieldScabr.setText("0.005");
		gbc_panel.gridy = Y;
		panel.add(textFieldScabr, gbc_panel);
		textFieldScabr.setColumns(10);
		Y++;
		
		
		JButton btnCalcola = new JButton("Calcola fattore attrito");
		btnCalcola.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					// Crepo il diagramma e la curva con la scabrezza
					
					// calcolo il fattore d'attrito
				if(	Calcfattaattr()){
					CreateDiagram();
				}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		gbc_panel.gridy = Y;
		panel.add(btnCalcola, gbc_panel);
		Y++;
		
		JLabel lblNewLabel_3 = new JLabel("Fattore d'attrito");
		gbc_panel.gridy = Y;
		panel.add(lblNewLabel_3, gbc_panel);
		Y++;
		
		textFieldFattAtt = new JTextField();
		textFieldFattAtt.setBorder(null);
		textFieldFattAtt.setBackground(Color.WHITE);
		textFieldFattAtt.setEditable(false);
		gbc_panel.gridy = Y;
		panel.add(textFieldFattAtt, gbc_panel);
		textFieldFattAtt.setColumns(10);
		Y++;


		// Aggiungo i verifier
		AVerifier verifier=new AVerifier(); 	
		textFieldNrey.setInputVerifier(verifier);
		textFieldRug.setInputVerifier(verifier);
		textFieldFattAtt.setInputVerifier(verifier);
		textFieldDiam.setInputVerifier(verifier);
		textFieldScabr.setInputVerifier(verifier);
		ckboxScabrAMano.setInputVerifier(verifier);
		// Aggiungo la text AREA di output	
		JPanel panel_3 = new JPanel();
		gbc_panel.gridy = Y;
		panel.add(panel_3, gbc_panel);
		panel_3.setLayout(new BorderLayout(0, 0));
			
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		panel_3.add(textArea);
		textArea.setEditable(false);
		textArea.setColumns(20);
		textArea.setRows(4);
		/**
		* Panel1 : dove viene inserito il panel diagram
		*/

		JPanel panel_1 = new JPanel();
		//double assey[]=Geometry.ScaleLog10(0.008,0.1, 0.001);
		/**
		 * Asse y 0.001 a 0.1
		 */
		double assey_1[]=Geometry.ScaleLog10(0.001, 0.1);
		int init=5;
		int lung=assey_1.length-init;
		asseys = new double[lung];
		/**
		 *  riduco asse y da 0.008 a 0.1
		 */
		System.arraycopy(assey_1, init, asseys, 0,lung);
		double assex_1[]=Geometry.ScaleLog10(100, 100000000);
		init=7;
		lung=assex_1.length-init;
		assexs = new double[lung];
		System.arraycopy(assex_1, init, assexs, 0,lung);
		panel_1.setLayout(new BorderLayout(0, 0));
		// Inserisco il panel nel panel principale
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		gbc_panel.weightx=0.8;
		gbc_panel.fill=GridBagConstraints.BOTH;
		add(panel_1, gbc_panel);

		// Creo il panel diagram
		diagram = new APanelDiagram (); 
		apdiagram=diagram;
		// Inserisco il diagram nel panel
		panel_1.add(diagram, BorderLayout.CENTER);
		// inizzializoz il diagram 
		diagram.setBackground(Color.WHITE);
		apdiagram.setX_axis(assexs);
		apdiagram.setY_axis(asseys);
		double xstr[]=apdiagram.getX_axis_str();
		Geometry.Exp10(xstr);
		apdiagram.setX_axis_str(xstr);
		double ystr[]=apdiagram.getY_axis_str();
		Geometry.Exp10(ystr);
		apdiagram.setY_axis_str(ystr);
		apdiagram.setAdapt_scale(3);
		//apdiagram.setFormatNumberAxis("%1.3f");
		apdiagram.setFormatNumberAxisX("%1.0f");
		apdiagram.setFormatNumberAxiY("%1.3f");
		apdiagram.disabilitaMenu();
		//diagram.setAdapt_scale(1);
		diagram.setLayout(null);
		

	
		init();
	}
	
	private void init(){
		ckboxScabrAMano.setSelected(false);
		textFieldRug.setText(String.valueOf(0.001));
		rug=0.001;
		diam=0.75;
		textFieldDiam.setText(String.valueOf(0.5));
		this.Calcfattaattr();
		this.CreateDiagram();





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
		String COMMAND=arg0.getActionCommand();
		if(COMMAND.equals("HelpRugosita")){
			JIF_Rugosita j = new JIF_Rugosita();
			j.setVisible(true);
			if (j.getValue().equals("ok")) {
				this.textFieldRug.setText(String.valueOf(j.getDoblevalue()));
			}
			j.dispose();
		}
		
	}
	
	@Override
		public void itemStateChanged(ItemEvent itemEvent) {
		 if(itemEvent.getSource().equals(ckboxScabrAMano)){
				int state = itemEvent.getStateChange();
				if (state == ItemEvent.SELECTED){
					textFieldDiam.setEnabled(false);
					textFieldRug.setEnabled(false);
					textFieldScabr.setEnabled(true);
				}else{
					textFieldDiam.setEnabled(true);
					textFieldRug.setEnabled(true);
					textFieldScabr.setEnabled(false);
				}
				
			}
              
		};
	
	
	
	
	
	
	
	
	class AVerifier extends InputVerifier{
		 public boolean shouldYieldFocus(JComponent input) {
		        boolean inputOK = verify(input);
		     //   makeItPretty(input);
		        if (inputOK) {
		        	Calcfattaattr();
		                return true;
		        } else {
		            Toolkit.getDefaultToolkit().beep();
		            return true;
		        }
		    }
		
		@Override
		public boolean verify(JComponent input) {
			if (input == textFieldNrey) {
	           try{
	        	   nrey=Double.parseDouble(textFieldNrey.getText());
	        	   return true;
	           }catch(Exception e){
	        	return false;   
	           }
			} else if (input == textFieldRug) {
		           try{
		        	   rug=Double.parseDouble(textFieldRug.getText());
		        	   return true;
		           }catch(Exception e){
		        	return false;   
		           }
	        } else if (input == textFieldDiam) {
		           try{
		        	   diam=Double.parseDouble(textFieldDiam.getText());
		        	   return true;
		           }catch(Exception e){
		        	return false;   
		           }

	        } else if (input == textFieldScabr) {
		           try{
		        	   scabr=Double.parseDouble(textFieldScabr.getText());
		        	   return true;
		           }catch(Exception e){
		        	return false;   
		           }
	        }else if(input ==ckboxScabrAMano){
	        	try{
	        		if (ckboxScabrAMano.isSelected()) {
						scabr=Double.parseDouble(textFieldScabr.getText());
						
					} else {
						scabr=rug/diam;
						textFieldScabr.setText(Double.toString(scabr));
					}
	        		return true;
	        	}catch(Exception e){
	        		return false;		
	        	}
	        }
			return false;
		}
		}// Fine inputVerifier

	private boolean Calcfattaattr() {
	try {

		nrey=Double.parseDouble(textFieldNrey.getText());
		scabr=Double.parseDouble(textFieldScabr.getText());
		if (ckboxScabrAMano.isSelected()) {
			scabr=Double.parseDouble(textFieldScabr.getText());
			
		} else {
		//	rug=Double.parseDouble(textFieldRug.getText());
			scabr=rug/diam;
			textFieldScabr.setText(Double.toString(scabr));
		}
		String resultString;
		if(nrey<2300){
			/**
			 * Moto laminare
			 */
			fattattr=64/nrey;
               //FattAttrRuvido=FattAttrLiscio;//verificare per nre<3000
			resultString=String.format("Moto laminare.Numero di Reynolds = %1.1f "
					+ " scabrezza relativa = %f  Fattore di attrito(64/Nreynolds)= %f", nrey,scabr,fattattr);
           }
		else if(nrey<3400){
			/**
			 * Zona di tranzizione
			 */
			double f1=64/nrey;
		    com.antoiovi.unicig.condotti.MoodyDiagram moodydiagr=new MoodyDiagram(nrey,scabr);
            double f2=moodydiagr.zbrent();
            double Xa=2300;
            double Xb=3400;
            double Ya=f1;    
            double Yb=f2;    
              //Calculate slope from p1 to p2 
            double m = (Xb-Xa)/(Yb-Ya); 
            double a=Ya*(nrey-Xb)/(Xa-Xb);
            double b=Yb*(nrey-Xa)/(Xa-Xb);
            fattattr=a-b;
            resultString=String.format("Moto in zona di tranzizione.\n Numero di Reynolds = %1.1f \n"
					+ " scabrezza relativa = %f  \n"
					+ "Fattore di attrito\n"
					+ "(interpolazione tra zona laminare e zona moto turbolento)=\n %f", nrey,scabr,fattattr);
        }else{
        	/**
        	 * Moto Turbolento
        	 */
               com.antoiovi.unicig.condotti.MoodyDiagram moodydiagr=new MoodyDiagram(nrey,scabr);
                fattattr=moodydiagr.zbrent();
               resultString=String.format("Moto turbolento. \nNumero di Reynolds = %1.1f \n"
   					+ " scabrezza relativa = %f \n Fattore di attrito\n(tramite formula di Colebrook)= %f", nrey,scabr,fattattr);
           }
		
		textFieldFattAtt.setText(Double.toString(fattattr));
		double x=Math.log10(nrey);
		double y=Math.log10(fattattr);
		apdiagram.clearPunti();
		apdiagram.clearStringhe();
		apdiagram.addPunto(Math.log10(nrey), Math.log10(fattattr));
		double topy=asseys[asseys.length-1];
		double halfx=assexs[(int)assexs.length/2];
		apdiagram.addStringa(String.format("CURVA PER SCABREZZA RELATIVA =%1.6f", scabr), halfx, topy);
		apdiagram.addStringa(String.format("Fattore d'attrito=%1.6f", fattattr), x, y);
		textArea.setText(resultString);
		return true;
	} catch (Exception e) {
		textFieldFattAtt.setText("#ERRORE");
		textArea.setText("#ERRORE#");
		return false;
	}}	

	
	public void CreateDiagram()  {
double Nrey;
 double f[]=new double[assexs.length];
		for(int x=0;x<assexs.length;x++){
		Nrey=assexs[x];
		Nrey=Math.pow(10,Nrey);
		if(Nrey<2300){
			f[x]=64/Nrey;
			f[x]=Math.log10(f[x])-asseys[0];
              }
		else{        com.antoiovi.unicig.condotti.MoodyDiagram moodydiagr=new MoodyDiagram(Nrey,scabr);
                f[x]=moodydiagr.zbrent();
                f[x]=Math.log10(f[x])-asseys[0];
            }
		}	
		diagram.setFunctions(f);
		diagram.validate();
		diagram.repaint();
	}
}
