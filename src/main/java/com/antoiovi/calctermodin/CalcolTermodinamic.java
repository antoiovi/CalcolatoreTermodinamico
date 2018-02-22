package com.antoiovi.calctermodin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JSeparator;
import javax.swing.SpringLayout;

import com.antoiovi.caminob.PanelCaminoTipoB;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.SwingConstants;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.JFormattedTextField;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JMenuBar;
import com.antoiovi.calctermodin.panels.ImgCondotti;
import javax.swing.JButton;

public class CalcolTermodinamic extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JMenuItem mntmInfo;
	private ImgCondotti imgCondotti;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 UIManager.setLookAndFeel(
					            UIManager.getSystemLookAndFeelClassName());
					CalcolTermodinamic frame = new CalcolTermodinamic();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CalcolTermodinamic() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 584);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mntmInfo = new JMenuItem("Info");
		mntmInfo.addActionListener(this);
		menuBar.add(mntmInfo);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// tabbedPane.setSelectedIndex(0);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setToolTipText(" ");
		panel.add(tabbedPane);

		APanelMoodyDiagram panel_moody= new APanelMoodyDiagram();
		tabbedPane.addTab("Diagramma di Moody", null, panel_moody, null);
		
		APanelCombustione panelcombustione = new APanelCombustione ();
		tabbedPane.addTab("Combustione", null, panelcombustione, null);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Miscele di gas", null, panel_3, null);
		//APanelScambiatore panel_4 = new APanelScambiatore();
		ATabPaneScambiatore panel_4 = new ATabPaneScambiatore();
		tabbedPane.addTab("Scambiatore", null, panel_4, null);

		PanelCaminoTipoB panelCaminoTipoB = new PanelCaminoTipoB();
		JScrollPane scrollpane1=new JScrollPane(panelCaminoTipoB);
		tabbedPane.addTab("Camino caldia tipo B < 35 kW", null, scrollpane1, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		imgCondotti = new ImgCondotti();
		panel_1.add(imgCondotti, BorderLayout.CENTER);
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 
			}
		});
		panel_5.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		PCondotto panel_6 = new PCondotto();
		panel_2.add(panel_6);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String COMMAND = e.getActionCommand();
		if(COMMAND.equals("Info")){
			JOptionPane.showMessageDialog(this,"Autore : Antonello Iovino");
		}
	
	}

}