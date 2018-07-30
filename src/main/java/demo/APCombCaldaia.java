package demo; 
 import com.antoiovi.unicig.fluidi.comb.Combustibile;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.util.Calendar;
import java.util.Date;

import java.awt.Color;
 import java.awt.Font;


import javax.swing.JFrame;
import javax.swing.JPanel;

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

import javax.swing.SwingUtilities;


public class APCombCaldaia extends JPanel 
{
    JSpinner s;
    SpinnerNumberModel model ;
    JSpinner.NumberEditor editor;
    String[] sinput={"Combustibile","Potenza","Temperatura fumi [°C]"};
	
	String[] schek={"Input rendiemento a mano","Input CO2% a mano","Bruciatore aria soffiata","Bruciatore aria naturale"};
	
	String[] soutput={"Portata massica fumi [g/s]"     		  ,"CostElasticita",
					"CapTermica"							  ,"TenoreH2O [%]"			  ,
					"Pressione parziale del vapore acqueo[Pa]","temperatura punto di rugiada[°C]",
					"Aumento del punto di rugiada in Kelvin"  ,"coefficiente di conducibilità termica in W/(m x K)",
					"viscosità dinamica dei prodotti [N s/m2]"," R in J/(kgxK)",
					"Tenore vapore H2O  in %"				  ,"pressione parziale del vapore acqueo in Pa"};
	//"<html><b><u>T</u>wo</b><br>lines</html>";
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
	static final int REND_MAN=0;
	static final int CO2_MAN=1;

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
	JComboBox combList;
	JPanel panel;	
    public APCombCaldaia()
    {
    panel=this;
	
	
		super.setLayout(new GridBagLayout());
		combustibili=Combustibile.combustibile;
		combList= new JComboBox(combustibili);
		combList.setSelectedIndex(4);
		jinput=new JComponent[sinput.length];
		
	
		
	    lblInput=new JLabel[sinput.length];
		txtInput=new JTextField[sinput.length-1];
		
		lblOutput=new JLabel[soutput.length];
		txtOutput=new JTextField[soutput.length];
		
		for(int x=0;x<sinput.length;x++){
		if(x==0){
			jinput[x]=combList;
			
		}else{
			txtInput[x-1]=new JTextField();
			txtInput[x-1].setColumns(10);
			jinput[x]=txtInput[x-1];
		}
		
		
	
	}
		
	int X=0;
	int Y=0;
	GridBagConstraints c = new GridBagConstraints();
	c.anchor=GridBagConstraints.EAST;
	//c.fill = GridBagConstraints.HORIZONTAL;
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
	// Button OK
	Y+=2;
	X=0;
	bCalcola=new JButton("Calcola")	;
	//c.weighty = 1.0;   //request any extra vertical space
	c.gridx = 0;
	c.gridy = Y;
	c.gridwidth = 2;
	panel.add(bCalcola,c);
	
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
	
	// Output
	Y+=2;
	c.gridy = Y;
	c.ipady = 0;       //reset to default
	c.weighty =0;		//reset to default
	X=0;
int col=0;
int row=Y;

	for(int x=0;x<soutput.length;x++){
		c.gridx = X;
		c.gridy = Y;
		
		lblOutput[x]=new JLabel(soutput[x]);
		panel.add(lblOutput[x], c);
		X++;
		c.gridx = X;
		txtOutput[x]=new JTextField();
		txtOutput[x].setColumns(10);
		panel.add(txtOutput[x],c);
		X++;
		col++;
		if(col>1){
			col=0;
			X=0;
			Y+=2;
		}
		
	}	
Y++;
	
	JLabel descr=new JLabel(sDescrizione);
	c.gridx = 0;
	c.gridy = Y;
	c.gridwidth = 6;
	c.weightx=1.0;
	c.weighty=1.0;
	c. gridheight=2;
	c.anchor=GridBagConstraints.LAST_LINE_START;
	panel.add(descr,c);
    }
	
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
	int chb=2;
	int rb1=2;
	
	String[] schek={"Input rendiemento a mano","Input CO2% a mano","Bruciatore aria soffiata","Bruciatore aria naturale"};
	panelInput2=new JPanel(new FlowLayout());
	group= new ButtonGroup();
	

	jcheck=new JCheckBox[chb];
	manual=new JTextField[chb];
	rdb_ariabr=new JRadioButton[rb1];
	
	jinput2=new JComponent[chb+rb1+chb];
	int W=0;
	for(int x=0;x<chb;x++){
			jcheck[x]=new JCheckBox(schek[x]);
			jinput2[W]=jcheck[x];
			W++;
			manual[x]=new JTextField();
			manual[x].setColumns(10);
			jinput2[W]=manual[x];
			W++;
		}
	for(int x=0;x<rb1;x++){
			rdb_ariabr[x]=new JRadioButton(schek[x+chb]);
			group.add(rdb_ariabr[x]);
			jinput2[W]=rdb_ariabr[x];
			W++;
		}
	for(int x=0;x<jinput2.length;x++){
		panelInput2.add(jinput2[x]);
	}
	
 	
	}

    
}