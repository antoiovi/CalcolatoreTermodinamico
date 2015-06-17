package com.antoiovi.caminob;

import it.iovino.utilita.Mylogger;
import it.iovino.utilita.Emptylog;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import com.antoiovi.caldaie.*;
import com.antoiovi.unicig.camini.CaminoB_1;
import com.antoiovi.unicig.camini.PresaariaB;
import com.antoiovi.unicig.condotti.CondottoBase;
import com.antoiovi.unicig.condotti.VelocitaFluido;
import com.antoiovi.unicig.tubi.Tubo;
import com.antoiovi.unicig.tubi.TuboC;
import com.antoiovi.unicig.tubi.TuboR;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
 

public class TipoB extends JPanel {
static final String CIRCOLARE="Circolare";
static final String RETTANGOLARE="Rettangolare";

	private JSpinner sp_pmax;
	private ImgCamino panel_camino;


private JSpinner sp_pmin;
private JSpinner sp_rend_pmax;
private JSpinner sp_rend_pmin;
private JComboBox comboBox;
String sezione;
private JSpinner sp_lungh;
private JSpinner sp_latoB;
private JSpinner sp_latoA;
private JSpinner sp_diam;
private JTextField t_rug;
private JSpinner sp_rug;
private JSpinner sp_resterm;
private JSpinner sp_diam_ca;
private JSpinner sp_spess;
private JSpinner sp_perc_cond_est;
private JSpinner sp_lung_ca;
private JSpinner sp_alt_ca;
private JSpinner sp_spes_ca;
private JSpinner sp_rug_ca_1;
private JSpinner sp_rter_ca;
private JSpinner sp_contest_ca;
private final ButtonGroup buttonGroup = new ButtonGroup();
private JSpinner sp_co2;
private JRadioButton rdbtnCo2;
private JRadioButton rdbtnEccaria;
private JSpinner sp_eccaria;
private JSpinner sp_Temperest;
private JSpinner sp_hslm;
private JSpinner sp_perd_ap;
private JSpinner sp_co2min;
private JSpinner sp_eccar_min;
private JSpinner sp_perd;
private JTextField textRug_ca;
private JSpinner sp_perdloc_ca;
private JTextField textInariaPmax;
private JTextField textIndariaPmin;
private JSpinner sp_perd_loc_rt;
private JSpinner sp_ar_rt;
private JSpinner sp_csi_com;
private JSpinner sp_superf;

Generatore generatore;
Mylogger mylogger= Emptylog.getInstance();
public void setLogger(Mylogger logger) {
	this.mylogger = logger;
	camino.setLogger(logger);
}
{
	generatore=new Generatore();
}
PresaariaB presaaria;

Pressioni pressioni=new Pressioni();
FaCondotto facondotto;
FaCondotto facanale;

Caldaiach4 caldaiach4;


{
	caldaiach4=new Caldaiach4();
	facondotto=new FaCondotto();
	facanale=new FaCondotto();
}
TuboC tuboCco=new TuboC();
TuboC tuboc_ca=new TuboC();
TuboR tuboRco=new TuboR();
CondottoBase condotto=new CondottoBase();
CondottoBase canale=new CondottoBase();
CaminoB_1 camino=new CaminoB_1();

Ambiente ambiente=new Ambiente();

	/**
	 * Create the panel.
	 */
	public TipoB() {
		 
	presaaria=new PresaariaB( ambiente.csi_int_tir, ambiente.csi_presa_ar , ambiente.area_int_tir, ambiente.area_presa );
		setMinimumSize(new Dimension(1200, 10));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{466, 337, 0, 0};
		gridBagLayout.rowHeights = new int[] {519, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 4;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{96, 84, 80, 0};
		gbl_panel.rowHeights = new int[]{0, 31, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel_11 = new JLabel("Codotto");
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.gridwidth = 3;
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_11.gridx = 0;
		gbc_lblNewLabel_11.gridy = 0;
		panel.add(lblNewLabel_11, gbc_lblNewLabel_11);
		/**
		 * forma sezione condotto
		 */
		JLabel lblNewLabel = new JLabel("Sezione");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sezione=(String)comboBox.getModel().getSelectedItem();
		if(sezione.equals(CIRCOLARE)){
			sp_latoA.setEnabled(false);
			sp_latoB.setEnabled(false);
			sp_diam.setEnabled(true);
			facondotto.sezione=CIRCOLARE;
		}else {
			sp_latoA.setEnabled(true);
			sp_latoB.setEnabled(true);
			sp_diam.setEnabled(false);
			facondotto.sezione=RETTANGOLARE;
		}
		validazione();
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {CIRCOLARE, RETTANGOLARE}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 1;
		panel.add(comboBox, gbc_comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		/**
		 * diametro condotto
		 */
		JLabel lblNewLabel_2 = new JLabel("Diametro [mm]");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		sp_diam = new JSpinner();
		sp_diam.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				double d=(Double)sp_diam.getModel().getValue();
				facondotto.diametro=d;
				validazione();
			}
		});
		sp_diam.setModel(new SpinnerNumberModel(120.0, 80.0, 500.0, 10.0));
		GridBagConstraints gbc_sp_diam = new GridBagConstraints();
		gbc_sp_diam.fill = GridBagConstraints.BOTH;
		gbc_sp_diam.insets = new Insets(0, 0, 5, 5);
		gbc_sp_diam.gridx = 1;
		gbc_sp_diam.gridy = 2;
		panel.add(sp_diam, gbc_sp_diam);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_3.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 2;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		/**
		 * lato a condotto
		 */
		JLabel lblNewLabel_4 = new JLabel("Lato A [mm]");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 3;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		sp_latoA = new JSpinner();
		sp_latoA.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				double d=(Double)sp_latoA.getModel().getValue();
				facondotto.lato_a=d;
				validazione();
			}
		});
		sp_latoA.setEnabled(false);
		sp_latoA.setModel(new SpinnerNumberModel(100.0, 80.0, 500.0, 10.0));
		GridBagConstraints gbc_sp_latoA = new GridBagConstraints();
		gbc_sp_latoA.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_latoA.insets = new Insets(0, 0, 5, 5);
		gbc_sp_latoA.gridx = 1;
		gbc_sp_latoA.gridy = 3;
		panel.add(sp_latoA, gbc_sp_latoA);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_5.gridx = 2;
		gbc_lblNewLabel_5.gridy = 3;
		panel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		/**
		 * LATO B CONDOTTO
		 */
		JLabel lblNewLabel_6 = new JLabel("Lato B [mm]");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 4;
		panel.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		sp_latoB = new JSpinner();
		sp_latoB.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				double d=(double)sp_latoB.getModel().getValue();
				facondotto.lato_b=d;
				validazione();
			}
		});
		sp_latoB.setEnabled(false);
		sp_latoB.setModel(new SpinnerNumberModel(100.0, 80.0, 500.0, 10.0));
		GridBagConstraints gbc_sp_latoB = new GridBagConstraints();
		gbc_sp_latoB.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_latoB.insets = new Insets(0, 0, 5, 5);
		gbc_sp_latoB.gridx = 1;
		gbc_sp_latoB.gridy = 4;
		panel.add(sp_latoB, gbc_sp_latoB);
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_7.gridx = 2;
		gbc_lblNewLabel_7.gridy = 4;
		panel.add(lblNewLabel_7, gbc_lblNewLabel_7);
		/**
		 * ALTEZZA-LUNGHEZZA CONDOTTO
		 */
		JLabel lblNewLabel_8 = new JLabel("Altezza [m]");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 5;
		panel.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		sp_lungh = new JSpinner();
		sp_lungh.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				double d=(Double)sp_lungh.getModel().getValue();
				facondotto.lunghezza=d;
				validazione();
			}
		});
		sp_lungh.setModel(new SpinnerNumberModel(5.0, 1.0, 12.0, 1.0));
		GridBagConstraints gbc_sp_lungh = new GridBagConstraints();
		gbc_sp_lungh.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_lungh.insets = new Insets(0, 0, 5, 5);
		gbc_sp_lungh.gridx = 1;
		gbc_sp_lungh.gridy = 5;
		panel.add(sp_lungh, gbc_sp_lungh);
		
		JLabel lblNewLabel_9 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_9.gridx = 2;
		gbc_lblNewLabel_9.gridy = 5;
		panel.add(lblNewLabel_9, gbc_lblNewLabel_9);
		/**
		 * SPESSORE CONDOTTO CM
		 */
		JLabel lblNewLabel_10 = new JLabel("Spesore [cm]");
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_10.gridx = 0;
		gbc_lblNewLabel_10.gridy = 6;
		panel.add(lblNewLabel_10, gbc_lblNewLabel_10);
		
		sp_spess = new JSpinner();
		sp_spess.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				double d=(Double)sp_spess.getModel().getValue();
				facondotto.spessore=d;
				validazione();
			}
		});
		sp_spess.setModel(new SpinnerNumberModel(5.0, 2.0, 10.0, 1.0));
		GridBagConstraints gbc_sp_eccaria = new GridBagConstraints();
		gbc_sp_eccaria.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_eccaria.insets = new Insets(0, 0, 5, 5);
		gbc_sp_eccaria.gridx = 1;
		gbc_sp_eccaria.gridy = 6;
		panel.add(sp_spess, gbc_sp_eccaria);
		/**
		 * RUGPSITA' CONDOTTO in mm*10 000
		 */
		JLabel lblNewLabel_12 = new JLabel("Rugosita [mm x 10^4]");
		GridBagConstraints gbc_lblNewLabel_12 = new GridBagConstraints();
		gbc_lblNewLabel_12.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_12.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_12.gridx = 0;
		gbc_lblNewLabel_12.gridy = 7;
		panel.add(lblNewLabel_12, gbc_lblNewLabel_12);
		
		sp_rug = new JSpinner();
		sp_rug.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int r=(Integer)sp_rug.getModel().getValue();
				double d_r=(double)r/10000.0;
				String s_r=String.format("%.4f [mm]",d_r);
				t_rug.setText(s_r);
			
				facondotto.rugosita=d_r;
				validazione();
			}
		});
		sp_rug.setModel(new SpinnerNumberModel(10, 5, 10000, 5));
		GridBagConstraints gbc_sp_rug_ca = new GridBagConstraints();
		gbc_sp_rug_ca.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_rug_ca.insets = new Insets(0, 0, 5, 5);
		gbc_sp_rug_ca.gridx = 1;
		gbc_sp_rug_ca.gridy = 7;
		panel.add(sp_rug, gbc_sp_rug_ca);
		/**
		 * rgosità condotto in mm
		 */
		t_rug = new JTextField();
		t_rug.setBackground(Color.WHITE);
		t_rug.setEnabled(false);
		GridBagConstraints gbc_t_rug = new GridBagConstraints();
		gbc_t_rug.insets = new Insets(0, 0, 5, 0);
		gbc_t_rug.fill = GridBagConstraints.HORIZONTAL;
		gbc_t_rug.gridx = 2;
		gbc_t_rug.gridy = 7;
		panel.add(t_rug, gbc_t_rug);
		t_rug.setColumns(10);
		/**
		 * Resistenza termica parete Condotto
		 */
		JLabel lblNewLabel_19 = new JLabel("Resistenza termica parete");
		GridBagConstraints gbc_lblNewLabel_19 = new GridBagConstraints();
		gbc_lblNewLabel_19.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_19.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_19.gridx = 0;
		gbc_lblNewLabel_19.gridy = 8;
		panel.add(lblNewLabel_19, gbc_lblNewLabel_19);
		
		sp_resterm = new JSpinner();
		sp_resterm.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				double d=(Double)sp_resterm.getModel().getValue();
				facondotto.resterm=d;
				validazione();
			}
		});
		sp_resterm.setModel(new SpinnerNumberModel(0, 0, 1.0, 0.01));
		GridBagConstraints gbc_sp_Test = new GridBagConstraints();
		gbc_sp_Test.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_Test.insets = new Insets(0, 0, 5, 5);
		gbc_sp_Test.gridx = 1;
		gbc_sp_Test.gridy = 8;
		panel.add(sp_resterm, gbc_sp_Test);
		/**
		 * Percentuale condotto in contatto con l' esterno
		 */
		JLabel lblNewLabel_20 = new JLabel("Percentuale contatto esterno");
		GridBagConstraints gbc_lblNewLabel_20 = new GridBagConstraints();
		gbc_lblNewLabel_20.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_20.gridx = 0;
		gbc_lblNewLabel_20.gridy = 9;
		panel.add(lblNewLabel_20, gbc_lblNewLabel_20);
		
		sp_perc_cond_est = new JSpinner();
		sp_perc_cond_est.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				double d=(Double)sp_perc_cond_est.getModel().getValue();
				facondotto.perccontest=d;
				validazione();
			}
		});
		sp_perc_cond_est.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 10.0));
		GridBagConstraints gbc_spinner_perc_cond_est = new GridBagConstraints();
		gbc_spinner_perc_cond_est.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_perc_cond_est.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_perc_cond_est.gridx = 1;
		gbc_spinner_perc_cond_est.gridy = 9;
		panel.add(sp_perc_cond_est, gbc_spinner_perc_cond_est);
		JLabel lblPerditeLocalizzate = new JLabel("Perdite localizzate");
		GridBagConstraints gbc_lblPerditeLocalizzate = new GridBagConstraints();
		gbc_lblPerditeLocalizzate.anchor = GridBagConstraints.WEST;
		gbc_lblPerditeLocalizzate.insets = new Insets(0, 0, 5, 5);
		gbc_lblPerditeLocalizzate.gridx = 0;
		gbc_lblPerditeLocalizzate.gridy = 10;
		panel.add(lblPerditeLocalizzate, gbc_lblPerditeLocalizzate);
		sp_perd = new JSpinner();
		sp_perd.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				double d=(Double)sp_perd.getModel().getValue();
				facondotto.perd=d;
				validazione();
			}
		});
		sp_perd.setModel(new SpinnerNumberModel(0.3, 0.3, 3.0, 0.1));
		GridBagConstraints gbc_sp_perd = new GridBagConstraints();
		gbc_sp_perd.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_perd.insets = new Insets(0, 0, 5, 5);
		gbc_sp_perd.gridx = 1;
		gbc_sp_perd.gridy = 10;
		panel.add(sp_perd, gbc_sp_perd);
		
		/************************************************
		 * canale da fumo
		 ********************************************/
			
		JLabel lblCanaleDaFumo = new JLabel("Canale da fumo");
		GridBagConstraints gbc_lblCanaleDaFumo = new GridBagConstraints();
		gbc_lblCanaleDaFumo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCanaleDaFumo.gridx = 1;
		gbc_lblCanaleDaFumo.gridy = 12;
		panel.add(lblCanaleDaFumo, gbc_lblCanaleDaFumo);
		
		JLabel lblDiametro = new JLabel("Diametro [mm]");
		GridBagConstraints gbc_lblDiametro = new GridBagConstraints();
		gbc_lblDiametro.anchor = GridBagConstraints.WEST;
		gbc_lblDiametro.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiametro.gridx = 0;
		gbc_lblDiametro.gridy = 13;
		panel.add(lblDiametro, gbc_lblDiametro);
		/**
		 * diametro canale da fumo
		 */
		sp_diam_ca = new JSpinner();
		sp_diam_ca.setModel(new SpinnerNumberModel(100.0, 80.0, 300.0, 10.0));
		sp_diam_ca.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				double d=(Double)sp_diam_ca.getModel().getValue();
				facanale.diametro=d;
				validazione();
			}
		});
		GridBagConstraints gbc_sp_diam_ca = new GridBagConstraints();
		gbc_sp_diam_ca.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_diam_ca.insets = new Insets(0, 0, 5, 5);
		gbc_sp_diam_ca.gridx = 1;
		gbc_sp_diam_ca.gridy = 13;
		panel.add(sp_diam_ca, gbc_sp_diam_ca);
		
		JLabel label_4 = new JLabel("New label");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.insets = new Insets(0, 0, 5, 0);
		gbc_label_4.gridx = 2;
		gbc_label_4.gridy = 13;
		panel.add(label_4, gbc_label_4);
		
		JLabel lblLunghezzaTotale = new JLabel("Lunghezza totale[m]");
		GridBagConstraints gbc_lblLunghezzaTotale = new GridBagConstraints();
		gbc_lblLunghezzaTotale.anchor = GridBagConstraints.WEST;
		gbc_lblLunghezzaTotale.insets = new Insets(0, 0, 5, 5);
		gbc_lblLunghezzaTotale.gridx = 0;
		gbc_lblLunghezzaTotale.gridy = 14;
		panel.add(lblLunghezzaTotale, gbc_lblLunghezzaTotale);
		/**
		 * LNGHEZZA CANALE
		 */
		sp_lung_ca = new JSpinner();
		sp_lung_ca.setModel(new SpinnerNumberModel(1.0, 1.0, 3.0, 0.1));
		sp_lung_ca.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				double d=(Double)sp_lung_ca.getModel().getValue();
				facanale.lunghezza=d;
				validazione();
			}
		});
		GridBagConstraints gbc_sp_lung_ca = new GridBagConstraints();
		gbc_sp_lung_ca.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_lung_ca.insets = new Insets(0, 0, 5, 5);
		gbc_sp_lung_ca.gridx = 1;
		gbc_sp_lung_ca.gridy = 14;
		panel.add(sp_lung_ca, gbc_sp_lung_ca);
		
		JLabel label_6 = new JLabel("New label");
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.insets = new Insets(0, 0, 5, 0);
		gbc_label_6.gridx = 2;
		gbc_label_6.gridy = 14;
		panel.add(label_6, gbc_label_6);
		/**
		 * ALTEZZA CANALE
		 */
		JLabel lblAltezza = new JLabel("Altezza[cm]");
		GridBagConstraints gbc_lblAltezza = new GridBagConstraints();
		gbc_lblAltezza.anchor = GridBagConstraints.WEST;
		gbc_lblAltezza.insets = new Insets(0, 0, 5, 5);
		gbc_lblAltezza.gridx = 0;
		gbc_lblAltezza.gridy = 15;
		panel.add(lblAltezza, gbc_lblAltezza);
		
		sp_alt_ca = new JSpinner();
		sp_alt_ca.setModel(new SpinnerNumberModel(10.0, 10.0, 90.0, 10.0));
		sp_alt_ca.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				double d=(Double)sp_alt_ca.getModel().getValue();
				facanale.altezza=d;
				validazione();
			}
		});
		GridBagConstraints gbc_sp_alt_ca = new GridBagConstraints();
		gbc_sp_alt_ca.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_alt_ca.insets = new Insets(0, 0, 5, 5);
		gbc_sp_alt_ca.gridx = 1;
		gbc_sp_alt_ca.gridy = 15;
		panel.add(sp_alt_ca, gbc_sp_alt_ca);
		
		JLabel label_8 = new JLabel("New label");
		GridBagConstraints gbc_label_8 = new GridBagConstraints();
		gbc_label_8.insets = new Insets(0, 0, 5, 0);
		gbc_label_8.gridx = 2;
		gbc_label_8.gridy = 15;
		panel.add(label_8, gbc_label_8);
		/**
		 * spessore canale mm
		 */
		JLabel lblSpessore = new JLabel("Spessore [mm]");
		GridBagConstraints gbc_lblSpessore = new GridBagConstraints();
		gbc_lblSpessore.anchor = GridBagConstraints.WEST;
		gbc_lblSpessore.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpessore.gridx = 0;
		gbc_lblSpessore.gridy = 16;
		panel.add(lblSpessore, gbc_lblSpessore);
		
		sp_spes_ca = new JSpinner();
		sp_spes_ca.setModel(new SpinnerNumberModel(0.0, 0.0, 10.0, 1.0));
		sp_spes_ca.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				validazione();
			}
		});
		GridBagConstraints gbc_sp_spes_ca = new GridBagConstraints();
		gbc_sp_spes_ca.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_spes_ca.insets = new Insets(0, 0, 5, 5);
		gbc_sp_spes_ca.gridx = 1;
		gbc_sp_spes_ca.gridy = 16;
		panel.add(sp_spes_ca, gbc_sp_spes_ca);
		
		JLabel label_10 = new JLabel("New label");
		GridBagConstraints gbc_label_10 = new GridBagConstraints();
		gbc_label_10.insets = new Insets(0, 0, 5, 0);
		gbc_label_10.gridx = 2;
		gbc_label_10.gridy = 16;
		panel.add(label_10, gbc_label_10);
		/**
		 * Rugosita canale
		 */
		JLabel lblNewLabel_13 = new JLabel("Rugosit\u00E0 [mm x 10^4]");
		GridBagConstraints gbc_lblNewLabel_13 = new GridBagConstraints();
		gbc_lblNewLabel_13.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_13.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_13.gridx = 0;
		gbc_lblNewLabel_13.gridy = 17;
		panel.add(lblNewLabel_13, gbc_lblNewLabel_13);
		
		sp_rug_ca_1 = new JSpinner();
		sp_rug_ca_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				double d=(Double)sp_rug_ca_1.getModel().getValue();
				textRug_ca.setText(String.format("%1.5f [mm]", d/10000));
				facanale.rugosita=d/10000;
				validazione();
			}
		});
		sp_rug_ca_1.setModel(new SpinnerNumberModel(10.0, 5.0, 10000.0, 5.0));
		GridBagConstraints gbc_sp_rug_ca1 = new GridBagConstraints();
		gbc_sp_rug_ca1.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_rug_ca1.insets = new Insets(0, 0, 5, 5);
		gbc_sp_rug_ca1.gridx = 1;
		gbc_sp_rug_ca1.gridy = 17;
		panel.add(sp_rug_ca_1, gbc_sp_rug_ca1);
		
		textRug_ca = new JTextField();
		textRug_ca.setEditable(false);
		textRug_ca.setEnabled(false);
		GridBagConstraints gbc_textRug_ca = new GridBagConstraints();
		gbc_textRug_ca.insets = new Insets(0, 0, 5, 0);
		gbc_textRug_ca.fill = GridBagConstraints.HORIZONTAL;
		gbc_textRug_ca.gridx = 2;
		gbc_textRug_ca.gridy = 17;
		panel.add(textRug_ca, gbc_textRug_ca);
		textRug_ca.setColumns(10);
		/**
		 * resistenz termica canale
		 */
		JLabel label = new JLabel("Resistenza termica parete");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 18;
		panel.add(label, gbc_label);
		
		sp_rter_ca = new JSpinner();
		sp_rter_ca.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				double d=(Double)sp_rter_ca.getModel().getValue();
				facanale.resterm=d;
				validazione();
			}
		});
		sp_rter_ca.setModel(new SpinnerNumberModel(0.0, 0.0, 10.0, 1.0));
		GridBagConstraints gbc_sp_rter_ca = new GridBagConstraints();
		gbc_sp_rter_ca.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_rter_ca.insets = new Insets(0, 0, 5, 5);
		gbc_sp_rter_ca.gridx = 1;
		gbc_sp_rter_ca.gridy = 18;
		panel.add(sp_rter_ca, gbc_sp_rter_ca);
		
		JLabel label_1 = new JLabel("Percentuale contatto esterno");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 19;
		panel.add(label_1, gbc_label_1);
		/**
		 * percnt ContEst contatto esterno Canale
		 */
		sp_contest_ca = new JSpinner();
		sp_contest_ca.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				facanale.perccontest=(Double)sp_contest_ca.getModel().getValue();
				validazione();
			}
		});
		sp_contest_ca.setModel(new SpinnerNumberModel(0.0, 0.0, 100.0, 10.0));
		GridBagConstraints gbc_sp_contest_ca = new GridBagConstraints();
		gbc_sp_contest_ca.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_contest_ca.insets = new Insets(0, 0, 5, 5);
		gbc_sp_contest_ca.gridx = 1;
		gbc_sp_contest_ca.gridy = 19;
		panel.add(sp_contest_ca, gbc_sp_contest_ca);
		/**
		 * perdite localizzate canale
		 */
		JLabel lblNewLabel_27 = new JLabel("Perdite localizzate");
		GridBagConstraints gbc_lblNewLabel_27 = new GridBagConstraints();
		gbc_lblNewLabel_27.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_27.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_27.gridx = 0;
		gbc_lblNewLabel_27.gridy = 20;
		panel.add(lblNewLabel_27, gbc_lblNewLabel_27);
		
		sp_perdloc_ca = new JSpinner();
		sp_perdloc_ca.setModel(new SpinnerNumberModel(0, 0, 3, 0.1));
		sp_perdloc_ca.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				double d=(Double)sp_perdloc_ca.getModel().getValue();
				facanale.perd=d;
				validazione();
			}
		});
		GridBagConstraints gbc_sp_perdloc_ca = new GridBagConstraints();
		gbc_sp_perdloc_ca.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_perdloc_ca.insets = new Insets(0, 0, 5, 5);
		gbc_sp_perdloc_ca.gridx = 1;
		gbc_sp_perdloc_ca.gridy = 20;
		panel.add(sp_perdloc_ca, gbc_sp_perdloc_ca);
		/********************
		 * GENERATORE
		 **********************/
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.VERTICAL;
		gbc_panel_2.anchor = GridBagConstraints.WEST;
		gbc_panel_2.gridx = 2;
		gbc_panel_2.gridy = 0;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{202, 0};
		gbl_panel_2.rowHeights = new int[]{0, 271, 158, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblNewLabel_14 = new JLabel("Generatore");
		GridBagConstraints gbc_lblNewLabel_14 = new GridBagConstraints();
		gbc_lblNewLabel_14.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_14.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_14.gridx = 0;
		gbc_lblNewLabel_14.gridy = 0;
		panel_2.add(lblNewLabel_14, gbc_lblNewLabel_14);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridheight = 2;
		gbc_panel_3.anchor = GridBagConstraints.WEST;
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.VERTICAL;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 1;
		panel_2.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{81, 20, 50, 0};
		gbl_panel_3.rowHeights = new int[]{25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblPotenzaMassima = new JLabel("Potenza ");
		GridBagConstraints gbc_lblPotenzaMassima = new GridBagConstraints();
		gbc_lblPotenzaMassima.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPotenzaMassima.insets = new Insets(0, 0, 5, 5);
		gbc_lblPotenzaMassima.gridx = 1;
		gbc_lblPotenzaMassima.gridy = 0;
		panel_3.add(lblPotenzaMassima, gbc_lblPotenzaMassima);
		
		JLabel lblPotenza = new JLabel("Potenza");
		GridBagConstraints gbc_lblPotenza = new GridBagConstraints();
		gbc_lblPotenza.insets = new Insets(0, 0, 5, 0);
		gbc_lblPotenza.gridx = 2;
		gbc_lblPotenza.gridy = 0;
		panel_3.add(lblPotenza, gbc_lblPotenza);
		
		JLabel lblNewLabel_15 = new JLabel("massima");
		GridBagConstraints gbc_lblNewLabel_15 = new GridBagConstraints();
		gbc_lblNewLabel_15.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_15.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_15.gridx = 1;
		gbc_lblNewLabel_15.gridy = 1;
		panel_3.add(lblNewLabel_15, gbc_lblNewLabel_15);
		
		JLabel lblNewLabel_26 = new JLabel("minima");
		GridBagConstraints gbc_lblNewLabel_26 = new GridBagConstraints();
		gbc_lblNewLabel_26.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_26.gridx = 2;
		gbc_lblNewLabel_26.gridy = 1;
		panel_3.add(lblNewLabel_26, gbc_lblNewLabel_26);
		
		JLabel lblNewLabel_16 = new JLabel("Potenza  [kW]");
		GridBagConstraints gbc_lblNewLabel_16 = new GridBagConstraints();
		gbc_lblNewLabel_16.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_16.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_16.gridx = 0;
		gbc_lblNewLabel_16.gridy = 2;
		panel_3.add(lblNewLabel_16, gbc_lblNewLabel_16);
		/**
		 * POTENZA MASSIMA
		 */
		
		sp_pmax = new JSpinner();
		sp_pmax.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				double d=	(Double)sp_pmax.getModel().getValue();
				generatore.P_max=d;
				validazione();
			}
		});
		sp_pmax.setModel(new SpinnerNumberModel(35.0, 10.0, 35.0, 1.0));
		
		GridBagConstraints gbc_sp_pmax = new GridBagConstraints();
		gbc_sp_pmax.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_pmax.insets = new Insets(0, 0, 5, 5);
		gbc_sp_pmax.gridx = 1;
		gbc_sp_pmax.gridy = 2;
		panel_3.add(sp_pmax, gbc_sp_pmax);
		/**
		 * POTENZZA MINIMA
		 */
		sp_pmin = new JSpinner();
		sp_pmin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				double d=	(Double)sp_pmin.getModel().getValue();
				 generatore.P_min=d;
					validazione();
			}
		});
		sp_pmin.setModel(new SpinnerNumberModel(35.0, 10.0, 35.0, 1.0));
		GridBagConstraints gbc_sp_pmin = new GridBagConstraints();
		gbc_sp_pmin.insets = new Insets(0, 0, 5, 0);
		gbc_sp_pmin.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_pmin.gridx = 2;
		gbc_sp_pmin.gridy = 2;
		panel_3.add(sp_pmin, gbc_sp_pmin);
		JLabel lblNewLabel_17 = new JLabel("Rendimento ");
		GridBagConstraints gbc_lblNewLabel_17 = new GridBagConstraints();
		gbc_lblNewLabel_17.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_17.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_17.gridx = 0;
		gbc_lblNewLabel_17.gridy = 3;
		panel_3.add(lblNewLabel_17, gbc_lblNewLabel_17);
		/**
		 * REndimento pmax
		 */
		sp_rend_pmax = new JSpinner();
		sp_rend_pmax.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				double d=(Double)sp_rend_pmax.getModel().getValue();
				generatore.Rend_Pmax=d;
				validazione();
			}
		});
		sp_rend_pmax.setModel(new SpinnerNumberModel(90.0, 75.0, 100.0, 1.0));
		GridBagConstraints gbc_sp_rend_pmax = new GridBagConstraints();
		gbc_sp_rend_pmax.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_rend_pmax.insets = new Insets(0, 0, 5, 5);
		gbc_sp_rend_pmax.gridx = 1;
		gbc_sp_rend_pmax.gridy = 3;
		panel_3.add(sp_rend_pmax, gbc_sp_rend_pmax);
		/**
		 * RENDIMENTO P MINIMa
		 */
		
		sp_rend_pmin = new JSpinner();
		sp_rend_pmin.setModel(new SpinnerNumberModel(90.0, 75.0, 100.0, 1.0));
		sp_rend_pmin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				double d=(Double)sp_rend_pmin.getModel().getValue();
				generatore.Rend_Pmin=d;
				validazione();
			}
		});
		GridBagConstraints gbc_sp_rend_pmin = new GridBagConstraints();
		gbc_sp_rend_pmin.insets = new Insets(0, 0, 5, 0);
		gbc_sp_rend_pmin.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_rend_pmin.gridx = 2;
		gbc_sp_rend_pmin.gridy = 3;
		panel_3.add(sp_rend_pmin, gbc_sp_rend_pmin);
		JLabel lblNewLabel_18 = new JLabel("Rendimento ");
		GridBagConstraints gbc_lblNewLabel_18 = new GridBagConstraints();
		gbc_lblNewLabel_18.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_18.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_18.gridx = 0;
		gbc_lblNewLabel_18.gridy = 4;
		panel_3.add(lblNewLabel_18, gbc_lblNewLabel_18);
		
		rdbtnCo2 = new JRadioButton("CO2% fumi secchi");
		
		rdbtnCo2.setSelected(true);
		/**
		 * CO2
		 */
		buttonGroup.add(rdbtnCo2);
		GridBagConstraints gbc_rdbtnCo2 = new GridBagConstraints();
		gbc_rdbtnCo2.anchor = GridBagConstraints.WEST;
		gbc_rdbtnCo2.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnCo2.gridx = 0;
		gbc_rdbtnCo2.gridy = 5;
		panel_3.add(rdbtnCo2, gbc_rdbtnCo2);
		
		sp_co2 = new JSpinner();
		sp_co2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				double d=(Double)sp_co2.getModel().getValue();
				generatore.co2_max=d;
				validazione();
			}
		});
		
		sp_co2.setModel(new SpinnerNumberModel(5.0, 4.0, 11.73, 0.01));
		GridBagConstraints gbc_sp_co2 = new GridBagConstraints();
		gbc_sp_co2.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_co2.insets = new Insets(0, 0, 5, 5);
		gbc_sp_co2.gridx = 1;
		gbc_sp_co2.gridy = 5;
		panel_3.add(sp_co2, gbc_sp_co2);
		/**
		 * ECCESSO D'ARIA
		 */
		
		sp_co2min = new JSpinner();
		sp_co2min.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				double d=(Double)sp_co2min.getModel().getValue();
				generatore.co2_min=d;
				validazione();
			}
		});
		sp_co2min.setModel(new SpinnerNumberModel(5.0, 4.0, 11.73, 0.01));
		GridBagConstraints gbc_sp_co2min = new GridBagConstraints();
		gbc_sp_co2min.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_co2min.insets = new Insets(0, 0, 5, 0);
		gbc_sp_co2min.gridx = 2;
		gbc_sp_co2min.gridy = 5;
		panel_3.add(sp_co2min, gbc_sp_co2min);
		rdbtnEccaria = new JRadioButton("Eccesso d'aria");
		
		buttonGroup.add(rdbtnEccaria);
		GridBagConstraints gbc_rdbtnEccaria = new GridBagConstraints();
		gbc_rdbtnEccaria.anchor = GridBagConstraints.WEST;
		gbc_rdbtnEccaria.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnEccaria.gridx = 0;
		gbc_rdbtnEccaria.gridy = 6;
		panel_3.add(rdbtnEccaria, gbc_rdbtnEccaria);
		
		sp_eccaria = new JSpinner();
		sp_eccaria.setEnabled(false);
		sp_eccaria.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				double d=(Double)sp_eccaria.getModel().getValue();
				generatore.eccaria_max=d;
				validazione();
			}
		});
		sp_eccaria.setModel(new SpinnerNumberModel(0.0, 0.0, 200.0, 5.0));
		GridBagConstraints gbc_sp_eccaria1 = new GridBagConstraints();
		gbc_sp_eccaria1.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_eccaria1.insets = new Insets(0, 0, 5, 5);
		gbc_sp_eccaria1.gridx = 1;
		gbc_sp_eccaria1.gridy = 6;
		panel_3.add(sp_eccaria, gbc_sp_eccaria1);
		
		
		rdbtnCo2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(rdbtnCo2.isSelected()){
					sp_co2.setEnabled(true);
					sp_eccaria.setEnabled(false);
				}else {
					sp_co2.setEnabled(false);
					sp_eccaria.setEnabled(true);
				}
				validazione();

			}
		});
		
		rdbtnEccaria.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(rdbtnEccaria.isSelected()){
					sp_co2.setEnabled(false);
					sp_co2min.setEnabled(false);
					sp_eccaria.setEnabled(true);
					 sp_eccar_min.setEnabled(true);
				}else{
					sp_co2.setEnabled(true);
					sp_co2min.setEnabled(true);
					sp_eccaria.setEnabled(false);
					sp_eccar_min.setEnabled(false);
				}
				validazione();
			}
		});
		/**
		 * presa d'aria
		 */
		
		sp_eccar_min = new JSpinner();
		sp_eccar_min.setEnabled(false);
		sp_eccar_min.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				double d=(Double)sp_eccar_min.getModel().getValue();
				generatore.eccaria_min=d;
				validazione();
			}
		});
		sp_eccar_min.setModel(new SpinnerNumberModel(0.0, 0.0, 200.0, 5.0));
		GridBagConstraints gbc_sp_eccar_min = new GridBagConstraints();
		gbc_sp_eccar_min.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_eccar_min.insets = new Insets(0, 0, 5, 0);
		gbc_sp_eccar_min.gridx = 2;
		gbc_sp_eccar_min.gridy = 6;
		panel_3.add(sp_eccar_min, gbc_sp_eccar_min);
		
		JLabel lblIndiceDaria = new JLabel("Indice d'aria");
		GridBagConstraints gbc_lblIndiceDaria = new GridBagConstraints();
		gbc_lblIndiceDaria.insets = new Insets(0, 0, 5, 5);
		gbc_lblIndiceDaria.anchor = GridBagConstraints.WEST;
		gbc_lblIndiceDaria.gridx = 0;
		gbc_lblIndiceDaria.gridy = 7;
		panel_3.add(lblIndiceDaria, gbc_lblIndiceDaria);
		
		textInariaPmax = new JTextField();
		textInariaPmax.setDisabledTextColor(Color.BLACK);
		textInariaPmax.setEditable(false);
		textInariaPmax.setEnabled(false);
		GridBagConstraints gbc_textInariaPmax = new GridBagConstraints();
		gbc_textInariaPmax.insets = new Insets(0, 0, 5, 5);
		gbc_textInariaPmax.fill = GridBagConstraints.HORIZONTAL;
		gbc_textInariaPmax.gridx = 1;
		gbc_textInariaPmax.gridy = 7;
		panel_3.add(textInariaPmax, gbc_textInariaPmax);
		textInariaPmax.setColumns(10);
		
		textIndariaPmin = new JTextField();
		textIndariaPmin.setDisabledTextColor(Color.BLACK);
		textIndariaPmin.setForeground(Color.BLACK);
		textIndariaPmin.setBackground(Color.WHITE);
		textIndariaPmin.setEditable(false);
		textIndariaPmin.setEnabled(false);
		GridBagConstraints gbc_textIndariaPmin = new GridBagConstraints();
		gbc_textIndariaPmin.anchor = GridBagConstraints.NORTH;
		gbc_textIndariaPmin.insets = new Insets(0, 0, 5, 0);
		gbc_textIndariaPmin.fill = GridBagConstraints.HORIZONTAL;
		gbc_textIndariaPmin.gridx = 2;
		gbc_textIndariaPmin.gridy = 7;
		panel_3.add(textIndariaPmin, gbc_textIndariaPmin);
		textIndariaPmin.setColumns(10);
		JLabel lblPresaDaria = new JLabel("Presa d'aria");
		GridBagConstraints gbc_lblPresaDaria = new GridBagConstraints();
		gbc_lblPresaDaria.gridwidth = 2;
		gbc_lblPresaDaria.insets = new Insets(0, 0, 5, 5);
		gbc_lblPresaDaria.gridx = 0;
		gbc_lblPresaDaria.gridy = 8;
		panel_3.add(lblPresaDaria, gbc_lblPresaDaria);
		
		JLabel lblNewLabel_24 = new JLabel("Superficie netta [m2]");
		GridBagConstraints gbc_lblNewLabel_24 = new GridBagConstraints();
		gbc_lblNewLabel_24.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_24.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_24.gridx = 0;
		gbc_lblNewLabel_24.gridy = 9;
		panel_3.add(lblNewLabel_24, gbc_lblNewLabel_24);
		/**
		 * suerficie apertura aria m2
		 */
		sp_superf = new JSpinner();
		sp_superf.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				ambiente.area_presa=doublefromspinner(sp_superf);
				validazione();
			}
		});
		sp_superf.setModel(new SpinnerNumberModel(0.03, 0.001, 0.3, 0.001));
		GridBagConstraints gbc_sp_superf1 = new GridBagConstraints();
		gbc_sp_superf1.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_superf1.insets = new Insets(0, 0, 5, 5);
		gbc_sp_superf1.gridx = 1;
		gbc_sp_superf1.gridy = 9;
		panel_3.add(sp_superf, gbc_sp_superf1);
		
		JLabel lblNewLabel_25 = new JLabel("Perdite localizzate ");
		GridBagConstraints gbc_lblNewLabel_25 = new GridBagConstraints();
		gbc_lblNewLabel_25.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_25.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_25.gridx = 0;
		gbc_lblNewLabel_25.gridy = 10;
		panel_3.add(lblNewLabel_25, gbc_lblNewLabel_25);
		/**
		 * perdilocalizzate apertura aria
		 */
		sp_perd_ap = new JSpinner();
		sp_perd_ap.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				ambiente.csi_presa_ar=doublefromspinner(sp_perd_ap);
				validazione();
			}
		});
		sp_perd_ap.setModel(new SpinnerNumberModel(0.0, 0.0, 10.0, 0.25));
		GridBagConstraints gbc_sp_perd_ap = new GridBagConstraints();
		gbc_sp_perd_ap.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_perd_ap.insets = new Insets(0, 0, 5, 5);
		gbc_sp_perd_ap.gridx = 1;
		gbc_sp_perd_ap.gridy = 10;
		panel_3.add(sp_perd_ap, gbc_sp_perd_ap);
		
		JLabel lblRompiTiraggio = new JLabel("Rompi tiraggio");
		GridBagConstraints gbc_lblRompiTiraggio = new GridBagConstraints();
		gbc_lblRompiTiraggio.gridwidth = 2;
		gbc_lblRompiTiraggio.insets = new Insets(0, 0, 5, 5);
		gbc_lblRompiTiraggio.gridx = 0;
		gbc_lblRompiTiraggio.gridy = 11;
		panel_3.add(lblRompiTiraggio, gbc_lblRompiTiraggio);
		
		JLabel lblNewLabel_28 = new JLabel("Area netta [m2]");
		GridBagConstraints gbc_lblNewLabel_28 = new GridBagConstraints();
		gbc_lblNewLabel_28.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_28.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_28.gridx = 0;
		gbc_lblNewLabel_28.gridy = 12;
		panel_3.add(lblNewLabel_28, gbc_lblNewLabel_28);
		/**
		 * area interruttore tiraggio m2
		 */
		sp_ar_rt = new JSpinner();
		sp_ar_rt.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				ambiente.area_int_tir=doublefromspinner(sp_ar_rt);
				validazione();
			}
		});
		sp_ar_rt.setModel(new SpinnerNumberModel(0.01, 0.01, 0.1, 0.01));
		GridBagConstraints gbc_sp_ar_rt = new GridBagConstraints();
		gbc_sp_ar_rt.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_ar_rt.insets = new Insets(0, 0, 5, 5);
		gbc_sp_ar_rt.gridx = 1;
		gbc_sp_ar_rt.gridy = 12;
		panel_3.add(sp_ar_rt, gbc_sp_ar_rt);
		
		JLabel lblNewLabel_29 = new JLabel("coeff perdite locali");
		GridBagConstraints gbc_lblNewLabel_29 = new GridBagConstraints();
		gbc_lblNewLabel_29.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel_29.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_29.gridx = 0;
		gbc_lblNewLabel_29.gridy = 13;
		panel_3.add(lblNewLabel_29, gbc_lblNewLabel_29);
		/**
		 * csi perdite localizzate inetrrutore tiraggio
		 */
		sp_perd_loc_rt = new JSpinner();
		sp_perd_loc_rt.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				ambiente.csi_int_tir=doublefromspinner(sp_perd_loc_rt);
				validazione();
						
			}
		});
		sp_perd_loc_rt.setModel(new SpinnerNumberModel(50.0, 2.0, 100.0, 2.0));
		GridBagConstraints gbc_sp_perd_loc_rt = new GridBagConstraints();
		gbc_sp_perd_loc_rt.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_perd_loc_rt.insets = new Insets(0, 0, 5, 5);
		gbc_sp_perd_loc_rt.gridx = 1;
		gbc_sp_perd_loc_rt.gridy = 13;
		panel_3.add(sp_perd_loc_rt, gbc_sp_perd_loc_rt);
		
		JLabel lblNewLabel_21 = new JLabel("Ambiente ");
		GridBagConstraints gbc_lblNewLabel_21 = new GridBagConstraints();
		gbc_lblNewLabel_21.gridwidth = 2;
		gbc_lblNewLabel_21.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_21.gridx = 0;
		gbc_lblNewLabel_21.gridy = 14;
		panel_3.add(lblNewLabel_21, gbc_lblNewLabel_21);
		
		JLabel lblNewLabel_22 = new JLabel("Altezza slm [m]");
		GridBagConstraints gbc_lblNewLabel_22 = new GridBagConstraints();
		gbc_lblNewLabel_22.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_22.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_22.gridx = 0;
		gbc_lblNewLabel_22.gridy = 15;
		panel_3.add(lblNewLabel_22, gbc_lblNewLabel_22);
		/**
		 * altezza sul livello del mare
		 */
		sp_hslm = new JSpinner();
		sp_hslm.setModel(new SpinnerNumberModel(0, 0, 800, 50));
		sp_hslm.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				ambiente.quotageod=doublefromspinner(sp_hslm);
				validazione();
						
			}
		});
		GridBagConstraints gbc_sp_hslm = new GridBagConstraints();
		gbc_sp_hslm.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_hslm.insets = new Insets(0, 0, 5, 5);
		gbc_sp_hslm.gridx = 1;
		gbc_sp_hslm.gridy = 15;
		panel_3.add(sp_hslm, gbc_sp_hslm);
		
		JLabel lblNewLabel_23 = new JLabel("Temperatura esterna [\u00B0C]");
		GridBagConstraints gbc_lblNewLabel_23 = new GridBagConstraints();
		gbc_lblNewLabel_23.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_23.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_23.gridx = 0;
		gbc_lblNewLabel_23.gridy = 16;
		panel_3.add(lblNewLabel_23, gbc_lblNewLabel_23);
		/**
		 * Temperatura minima esterna
		 */
		sp_Temperest = new JSpinner();
		sp_Temperest.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				double d=(Double)sp_Temperest.getModel().getValue();
				d+=273;
				ambiente.Tmin=d;
				validazione();
			}
		});
		sp_Temperest.setModel(new SpinnerNumberModel(0.0, -10.0, 20.0, 0.5));
		GridBagConstraints gbc_sp_Test1 = new GridBagConstraints();
		gbc_sp_Test1.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_Test1.insets = new Insets(0, 0, 5, 5);
		gbc_sp_Test1.gridx = 1;
		gbc_sp_Test1.gridy = 16;
		panel_3.add(sp_Temperest, gbc_sp_Test1);
		
		JLabel lblNewLabel_30 = new JLabel("Comignolo");
		GridBagConstraints gbc_lblNewLabel_30 = new GridBagConstraints();
		gbc_lblNewLabel_30.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_30.gridx = 0;
		gbc_lblNewLabel_30.gridy = 17;
		panel_3.add(lblNewLabel_30, gbc_lblNewLabel_30);
		
		JLabel lblNewLabel_31 = new JLabel("Perdite localizzate");
		GridBagConstraints gbc_lblNewLabel_31 = new GridBagConstraints();
		gbc_lblNewLabel_31.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_31.gridx = 0;
		gbc_lblNewLabel_31.gridy = 18;
		panel_3.add(lblNewLabel_31, gbc_lblNewLabel_31);
		/**
		 * csi Comignolo
		 */
		sp_csi_com = new JSpinner();
		sp_csi_com.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				ambiente.csi_comign=doublefromspinner(sp_csi_com);
				validazione();
			}
		});
		sp_csi_com.setModel(new SpinnerNumberModel(0.0, 0.0, 3.0, 0.1));
		GridBagConstraints gbc_sp_csi_com = new GridBagConstraints();
		gbc_sp_csi_com.fill = GridBagConstraints.HORIZONTAL;
		gbc_sp_csi_com.insets = new Insets(0, 0, 5, 5);
		gbc_sp_csi_com.gridx = 1;
		gbc_sp_csi_com.gridy = 18;
		panel_3.add(sp_csi_com, gbc_sp_csi_com);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 5);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 0;
		add(panel_4, gbc_panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		panel_camino = new ImgCamino();
		panel_4.add(panel_camino);
		mylogger.setLevel(Mylogger.FINEST);
		camino.setLogger(mylogger);
		init();
validazione();
	}
	/**
	 * INIZZIALIZZAZIONE OMPONENTI
	 */
	void init(){

		/**
		 * Generatore
		 */
	 sp_pmax.getModel().setValue((Double)generatore.P_max);
	 sp_pmin.getModel().setValue((Double)generatore.P_min);
	  sp_rend_pmax.getModel().setValue((Double)generatore.Rend_Pmax);
	 
	  sp_rend_pmin.getModel().setValue((Double)generatore.Rend_Pmin);
	// textInariaPmax;
	 // textIndariaPmin;
	  doubletospinner(sp_co2, generatore.co2_max);
	  doubletospinner(sp_co2min, generatore.co2_min);
	 // sp_eccar_min;
	//  rdbtnEccaria;
	 //   sp_eccaria; 
		
		   
	  
	  /**
	   * Tubo
	   */
	//private JComboBox comboBox;
	//String sezione;
	  sp_lungh.getModel().setValue((Double)facondotto.lunghezza);
	  sp_latoB.getModel().setValue((Double)facondotto.lato_b);
	  sp_latoA.getModel().setValue((Double)facondotto.lato_a);
	  sp_diam.getModel().setValue((Double)facondotto.diametro);
	  t_rug.setText(String.valueOf(facondotto.rugosita));
	 
	  double dr=facondotto.rugosita;
	  int ir=(int)dr;
	  sp_rug.getModel().setValue((Integer)ir);
	  sp_resterm.getModel().setValue((Double)facondotto.resterm);
	  doubletospinner(sp_spess,0);
	 
	  doubletospinner(sp_perc_cond_est,facondotto.perccontest);
	  doubletospinner(sp_perd,facondotto.perd);
	 
	/**
	 * Canale
	 */
	  doubletospinner(sp_diam_ca,facanale.diametro);
	
	  doubletospinner(sp_lung_ca,facanale.lunghezza);
	  doubletospinner(sp_alt_ca,facanale.altezza);
	  doubletospinner(sp_spes_ca,facanale.spessore);

	    dr=facanale.rugosita;
	    ir=(int)dr;
	  doubletospinner(sp_rug_ca_1,(Integer)ir);
	  textRug_ca.setText(String.valueOf(facanale.rugosita));
	  doubletospinner(sp_rter_ca,facanale.resterm);
	  doubletospinner(sp_contest_ca,facanale.perccontest);
	  
	  doubletospinner(sp_perdloc_ca,facanale.perd);
	
	 /**
	  * ambiente
	  */
	  doubletospinner(sp_Temperest,ambiente.Tmin-273);
	  doubletospinner(sp_hslm,ambiente.quotageod);
	 
	  doubletospinner(sp_perd_ap,ambiente.csi_presa_ar);
	  doubletospinner(sp_superf,ambiente.area_presa);
	
	 
	 
	  doubletospinner(sp_perd_loc_rt,ambiente.csi_int_tir);
	  doubletospinner(sp_ar_rt,ambiente.area_int_tir);
	 
	  doubletospinner(sp_csi_com,ambiente.csi_comign);
	
	}
	
	double doublefromspinner(JSpinner sp){
		return (Double)sp.getModel().getValue();
	}
	
	void doubletospinner(JSpinner sp,double val){
		 sp.getModel().setValue((Double)val);
	}
	/**
	 * VALIDAZIONE
	 */
void validazione(){
	/**
	 * caldaia e fumo
	 */
	if(rdbtnCo2.isSelected()){
	caldaiach4.setData(generatore.Rend_Pmax/100,generatore.Rend_Pmin/100, 
	generatore.co2_max,generatore.co2_min,generatore.P_max, generatore.P_min);
	}else{
		caldaiach4.setData(generatore.Rend_Pmax/100,generatore.Rend_Pmin/100, 
				generatore.eccaria_max,generatore.eccaria_min,generatore.P_max, generatore.P_min,0);
	}
	double indaria_max=caldaiach4.getIndiceAriaPmax();
	double indaria_min=caldaiach4.getIndiceAriaPmin();
	textInariaPmax.setText(String.format("%1.3f", indaria_max));
	textIndariaPmin.setText(String.format("%1.3f",indaria_min));
	/**;
	 * Canale
	 */
	tuboc_ca.setDiam_int(facanale.diametro/1000);
	tuboc_ca.setResterm(facanale.resterm);
	tuboc_ca.setSpessore(facanale.spessore/100);
	tuboc_ca.setRug(facanale.rugosita/1000);
	tuboc_ca.setLung(facanale.lunghezza);
	
	canale.setTubo(tuboc_ca);
	canale.setHaltezza(facanale.altezza/100);
	canale.getCsi().clear();
	canale.addCsi(facanale.perd);
	canale.setCoefLimE(8.0);
	//canale.setData(ambiente.Patm, caldaiach4.getPortatfumiPmax(), ambiente.Tmin, caldaiach4.getTempFumiPmax(), caldaiach4.getFumoPmax());
 
	
	/**
	 * condotto
	 */
	Tubo tubo;
	tuboCco.setDiam_int(facondotto.diametro/1000);
	tuboRco.setLato_cort(facondotto.lato_a/1000);
	tuboRco.setLato_lung(facondotto.lato_b/1000);
	
	if(facondotto.sezione.equals(CIRCOLARE)){
		tubo=tuboCco;
		 
	}else{
		tubo=tuboRco;
		 
		}
	tubo.setResterm(facondotto.resterm);
	tubo.setSpessore(facondotto.spessore/100);
	tubo.setRug(facondotto.rugosita/1000);
	tubo.setLung(facondotto.lunghezza);
	condotto.setTubo(tubo);
	
	//String sdh=String.format("%1.3f",tubo.Dh());
	//out_panel.print("perimetro "+tubo.Per_int()+"  Area= "+tubo.Area_int());
	condotto.getCsi().clear();
	condotto.addCsi(facondotto.perd);
	condotto.setCoefLimE(8.0);
	condotto.setHaltezza(tubo.Lung());
	/**
	 * imposto dati presa aria e interruttore di tiraggio
	 */
	presaaria.setAd(ambiente.area_presa);
	presaaria.setAi(ambiente.area_int_tir);
	presaaria.setCsi_d(ambiente.csi_presa_ar);
	presaaria.setCsi_i(ambiente.csi_int_tir);
	camino.setPresaaria(presaaria);
	camino.setCsi_comign(ambiente.csi_comign);
	
	camino.Patm=ambiente.Patm;
	camino.Tamb= ambiente.Tmin;
	
	camino.condotto=condotto;
	 camino.canale=canale;
	 camino.caldaia=caldaiach4;
	 camino.calcola();

	//condotto.setData(ambiente.Patm,  canale.M1(), ambiente.Tmin, canale.Tu(), canale.fl_int());
 	 
	/*condotto.Ps(1);
	pressioni.ps_ca= canale.Ps(1);
	 
	pressioni.peff_ca= canale.Peff(condotto.Peff());
 	
 	pressioni.dp_ca=canale.d_P();
 	pressioni.dp_co=canale.d_P();
 	*/
 	
 	//out_panel.addMessage(caldaiach4.toString());
	
	String msg=String.format("Temperatura media %1.3f  Temp uscita %1.3f", condotto.Tm(),condotto.Tu());
	VelocitaFluido vf=condotto.getVelocitaFluido();
	
	panel_camino.setCamino(camino);
	panel_camino.setVf_co(vf);
	
	panel_camino.validate();
	panel_camino.repaint();
	
}

}
