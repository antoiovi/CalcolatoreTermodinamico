package demo;
import com.antoiovi.unicig.fluidi.comb.Combustibile;
import java.awt.Toolkit;


import java.awt.Color;
 import java.awt.Font;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;


import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


import java.awt.BorderLayout;
import java.awt.FlowLayout;



public class APCombCaldaia extends JPanel  implements ItemListener
{
    String[] sinput={"Combustibile","Potenza","Temperatura fumi [°C]"};



	//INDICI OUTPUT
	final int OUT_P_MASS=0;
	final int OUT_R=1;
	final int OUT_CAP_T=2;
	final int OUT_H2O_P=3;

	final int OUT_PRESS_PARZ_H2O=4;
	final int OUT_TEMP_RUG=5;
	final int OUT_COND_TERM=6;
	final int OUT_VISC_DIN=7;
	final int OUT_TEN_VAP_H2O=8;

	// Indici InputTextField
	final int INP_POT=0;
	final int INP_TEMPFUM=1;
	final int INP_P_MASS=2;
  final int INP_REND=3;
	final int INP_CO2=4;


	String[] strCheckBoxes={"Portata fumi nota [g/s]","Rendiemento noto","CO2% noto"};
  final int CHB_P_MASS=0;
  final int CHB_REND=1;
	final int CHB_CO=2;

String[] strRadioButtons={"Bruciatore aria soffiata","Bruciatore aria naturale"};
final int RAD_BTN_AS=0;
final int RAD_BTN_AN=1;



	String[] soutput={"Portata massica fumi [g/s]"     		  ,"CostElasticita",
					"CapTermica"							  ,"TenoreH2O [%]"			  ,
					"Pressione parziale del vapore acqueo[Pa]","temperatura punto di rugiada[°C]",
					"Aumento del punto di rugiada in Kelvin"  ,"coefficiente di conducibilità termica in W/(m x K)",
					"viscosità dinamica dei prodotti [N s/m2]"," R in J/(kgxK)",
					"Tenore vapore H2O  in %"				  ,"pressione parziale del vapore acqueo in Pa"};
	String sDescrizione= "<html><b>Calcolo parametri combustione</b><br>"+
					"<p>Il programma calcola i parametri dei prodotti "+
				"della combustione dati i dati del generatore utilizando il metodo della norma UNI EN 13384-1</p><br>"+
				""+
				"</html>";
	JLabel[]     lblInput;
	JTextField[] txtInput;

	// Panel input 2
	JPanel panelInput1;
	JPanel panelInput2;
	JTextField textFieldInput[];

	JTextField[] manual;
	JCheckBox[] jcheck;

	static final int BR_SOFF=0;
	static final int BR_NAT=1;

	JRadioButton[] rdb_ariabr;
	//Group the radio buttons.
	 ButtonGroup group ;

	JComponent[] jinput2;


	// Dati output
	JLabel[]     lblOutput;
	JTextField[] txtOutput;

	JComponent[] jinput;


	JButton bCalcola;

	String[] combustibili;
	JComboBox jcombListCombust;
	JPanel panel;
	JTextArea outArea;

    public APCombCaldaia()
    {
       panel=this;//new JPanel();
	panel.setLayout(new GridBagLayout());
  // JComboBox con lista combustibili
	combustibili=Combustibile.combustibile;
	jcombListCombust= new JComboBox(combustibili);
	jcombListCombust.setSelectedIndex(4);
// sinput stringhe input : combiustiblie, potenza; temperatura
// jiunput : componenti che possono essere JTextField o JComboBox
  jinput=new JComponent[sinput.length];
// etichette inpute : come campi input
	lblInput=new JLabel[sinput.length];
  // textfield solo due (uno è jcombobox )
	txtInput=new JTextField[sinput.length-1+strCheckBoxes.length];

	lblOutput=new JLabel[soutput.length];
	txtOutput=new JTextField[soutput.length];

// Numero 5 textfield Input :  Potenza, Temperatura Pmassica Rendimento CO2
	textFieldInput=new JTextField[5];
  textFieldInput[INP_POT]=new JTextField(10);
  textFieldInput[INP_TEMPFUM]=new JTextField(10);
  textFieldInput[INP_P_MASS]=new JTextField(10);
  textFieldInput[INP_REND]=new JTextField(10);
  textFieldInput[INP_CO2]=new JTextField(10);

	for(int x=0;x<sinput.length;x++){
		if(x==0){
			jinput[x]=jcombListCombust;
		}else{
			jinput[x]=textFieldInput[x-1];
		}
	}


	int X=0;
	int Y=0;
	GridBagConstraints c = new GridBagConstraints();
	c.anchor=GridBagConstraints.EAST;
	c.insets = new Insets(0, 0, 5, 5);
	//Titolo input
	X=0;
	c.gridy = Y;
	//c.weighty = 1.0;   //request any extra vertical space
	c.ipady = 40;      //make this component tall
	c.gridwidth = 2;

	JLabel titlinput=new JLabel("Dati caldaia :")	;
	titlinput.setFont(new Font("Courier New", Font.BOLD, 24));
	titlinput.setForeground(Color.GRAY);
	panel.add(titlinput,c);

	c.weighty =0;   //reset
	c.ipady = 0;    //reset
	Y+=2;

	X=0;

	/**
	* INPUT PANEL 1
	**/
	this.initPanelInput1();
	// Input
	c.gridx = X;
	c.gridy = Y;

	c.gridwidth = 5;

	c.fill = GridBagConstraints.HORIZONTAL;
	panel.add(panelInput1,c);
	/**
	* INPUT PANEL 2
	**/
	this.initPanelInput2();

	Y+=2;
	c.gridx = 0;
	c.gridy = Y;

	c.gridwidth = 5;

	c.fill = GridBagConstraints.HORIZONTAL;
	panel.add(panelInput2,c);
	c.fill = GridBagConstraints.NONE;

	// -------- Button OK
	Y+=2;
	X=0;
	bCalcola=new JButton("Calcola")	;
	bCalcola.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
               		Calcola();
			}
		});

	//c.weighty = 1.0;   //request any extra vertical space
	c.gridx = 0;
	c.gridy = Y;
	c.gridwidth = 2;
	panel.add(bCalcola,c);
	// -------- RISULTATI DEL CALCOLO -------
	X=0;
	Y+=2;
	c.gridx = X;
	c.gridy = Y;
	c.gridwidth = 2;
	//c.weighty = 1.0;   //request any extra vertical space
	c.ipady = 30;      //make this component tall

	JLabel titleout=new JLabel("Risultati del calcolo :")	;
	titleout.setFont(new Font("Courier New", Font.BOLD, 24));
	titleout.setForeground(Color.GRAY);
	panel.add(titleout,c);
	c.gridwidth = 1;

	//------------- Output
	Y+=2;
	c.gridy = Y;
	c.ipady = 0;       //reset to default
	c.weighty =0;		//reset to default
	c.gridwidth=1;
	X=0;
	int col=0;
	int row=Y;

	for(int x=0;x<soutput.length;x++){
		c.gridx = X;
		c.gridy = Y;
		c.weightx=0.1;

		lblOutput[x]=new JLabel(soutput[x]);
		panel.add(lblOutput[x], c);
		X++;
		c.gridx = X;
		c.weightx=0.4;
		txtOutput[x]=new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		txtOutput[x].setColumns(10);
		txtOutput[x].setBackground(Color.BLUE);

		panel.add(txtOutput[x],c);
		X++;
		col++;
		if(col>1){
			col=0;
			X=0;
			Y+=2;
		}

	}

	//---------------------DESCIZIONE (Ultima Linea)-----
	outArea=new JTextArea(10,10);
	JLabel descr=new JLabel(sDescrizione);


	Y++;
	c.gridx = 0;
	c.gridy = Y;
	c.gridwidth = 6;
	c.weightx=1.0;
	c.weighty=1.0;
	c. gridheight=2;
	c.fill = GridBagConstraints.HORIZONTAL;
	c.anchor=GridBagConstraints.LAST_LINE_START;

	panel.add(descr,c);


	Y++;
	c.gridy=Y;
	c.gridheight=3;
	c.weighty=1.0;
	panel.add(outArea,c);

			jcheck[CHB_CO].setSelected(true);
			jcheck[CHB_REND].setSelected(true);
      jcheck[CHB_P_MASS].setSelected(true);



    }
		/**
		* Implementazione metodo di ItemListener
		*  (intercettazione change checkbox
		**/
		@Override
		public void itemStateChanged(ItemEvent itemEvent) {
		 if(itemEvent.getSource().equals(jcheck[CHB_REND])){
			    textFieldInput[INP_REND].setEnabled(jcheck[CHB_REND].isSelected());
			}else if(itemEvent.getSource().equals(jcheck[CHB_CO])){
			     textFieldInput[INP_CO2].setEnabled(jcheck[CHB_CO].isSelected());
			}else if(itemEvent.getSource().equals(jcheck[CHB_P_MASS])){
			     textFieldInput[INP_P_MASS].setEnabled(jcheck[CHB_P_MASS].isSelected());
			}

		};

	/*********************
	* panel input 1
	*********************/
	void initPanelInput1(){
		panelInput1=new JPanel(new FlowLayout());
		// Input
		lblInput[0]=new JLabel(sinput[0]);
		panelInput1.add(lblInput[0]);
		panelInput1.add(jinput[0]);
		for(int x=1;x<sinput.length;x++){
			lblInput[x]=new JLabel(sinput[x]);
			panelInput1.add(lblInput[x]);
			panelInput1.add(jinput[x]);
			}

	}


	/*********************
	* panel input 2
	*********************/
	void initPanelInput2(){
	int chb=3; // Check Box : portatamassica,Rendimento, CO2
	int rb1=2;

	panelInput2=new JPanel(new FlowLayout());
	group= new ButtonGroup();
	jcheck=new JCheckBox[chb];
  jcheck[CHB_P_MASS]=new JCheckBox(strCheckBoxes[CHB_P_MASS]);
  jcheck[CHB_P_MASS].addItemListener(this);
  panelInput2.add(jcheck[CHB_P_MASS]);
  panelInput2.add(textFieldInput[INP_P_MASS]);

  jcheck[CHB_REND]=new JCheckBox(strCheckBoxes[CHB_REND]);
  jcheck[CHB_REND].addItemListener(this);
  panelInput2.add(jcheck[CHB_REND]);
  panelInput2.add(textFieldInput[INP_REND]);

  jcheck[CHB_CO]=new JCheckBox(strCheckBoxes[CHB_CO]);
  jcheck[CHB_CO].addItemListener(this);
  panelInput2.add(jcheck[CHB_CO]);
  panelInput2.add(textFieldInput[INP_CO2]);

	rdb_ariabr=new JRadioButton[rb1];
  rdb_ariabr[RAD_BTN_AN]=new JRadioButton(strRadioButtons[RAD_BTN_AN]);
  group.add(rdb_ariabr[RAD_BTN_AN]);
  panelInput2.add(rdb_ariabr[RAD_BTN_AN]);
  rdb_ariabr[RAD_BTN_AS]=new JRadioButton(strRadioButtons[RAD_BTN_AS]);
  group.add(rdb_ariabr[RAD_BTN_AS]);
  panelInput2.add(rdb_ariabr[RAD_BTN_AS]);


	}

	/*****************
	*	CALCOLA
	*****************/
	private void Calcola(){
		Dati dati=new Dati();

		logArea("");
		if(validateInput(dati)){
			logArea("INPUT OK");

		}else{
			logArea("ERRORE INPUT");
		}


		}
		/****************************************************************
		*	Validate Input
		************************************************************/
	boolean validateInput(Dati dati){

String[] strErrori=new String[textFieldInput.length];
    boolean test=false;



for(int x=0;x<textFieldInput.length;x++){
log(String.format("x=%d",x));
  // Verifcio quelli condizionati da check box
  if(x==INP_P_MASS){
    if(jcheck[CHB_P_MASS].isSelected()){
			 Double _TEST=convertField(textFieldInput[x]);
      test=_TEST==null?false:test;
			}

  }else if(x==INP_REND){
    if(jcheck[CHB_REND].isSelected()){
      Double _TEST=convertField(textFieldInput[x]);
     test=_TEST==null?false:test;

			}

  }
  else if(x==INP_CO2){
    if(jcheck[CHB_CO].isSelected()){
      Double _TEST=convertField(textFieldInput[x]);
     test=_TEST==null?false:test;

			}

  }else {
    Double _TEST=convertField(textFieldInput[x]);
   test=_TEST==null?false:test;
   log(String.format("x=%d",x));
  }


}

		return test;
	}
	/*************
	*	CONVERT FIELD
	**************/
	private	Double convertField(JTextField field){
		String strVal=field.getText();
		Double val=convertToDouble(strVal);
		if(val== null){
			 field.setOpaque(true);
			field.setBackground(Color.GREEN);
			field.setForeground(Color.RED);
			return null;
		}else{
			field.setBackground(Color.WHITE);
			field.setForeground(Color.BLACK);
			return val;

		}
	}

	/************************
	*	ConvertToDouble
	***************/
	private Double convertToDouble(String string){
			try{
				Double val=Double.parseDouble(string);
				log(String.format("Conversione OK val(%s) = %f",string,val));
				return val;
			}catch(Exception e){
				log(String.format("errore val(%s) = nullo...",string));
				return null;
			}

    		}

	private void log(String s){
		System.out.println(s);
		}

	private void logArea(String s){
		outArea.setText(s);
		}



	private class Dati {
			int COMBUSTIBILE;
			double potenza;
			double temp_fumi;
			boolean inp_rend_mano;
			double rend_a_mano;
			boolean inp_co2_mano;
			double co2_mano;
			boolean bruc_aria_soff;
			boolean bruc_aria_nat;


			}

}
