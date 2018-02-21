package com.antoiovi.caminob;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class CalcCaminoB extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalcCaminoB frame = new CalcCaminoB();
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
	public CalcCaminoB() {
		Locale.setDefault(Locale.US);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1318, 836);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		//contentPane.add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("New button");
		panel_1.add(btnNewButton);
		
		
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.EAST);
		
		JLabel lblNewLabel = new JLabel("New label");
		panel_2.add(lblNewLabel);
		
		PanelCaminoTipoB panelProject = new PanelCaminoTipoB();
		GridBagLayout gridBagLayout = (GridBagLayout) panelProject.getLayout();
		gridBagLayout.columnWeights = new double[]{0.0};
		gridBagLayout.columnWidths = new int[]{1054};
		JScrollPane scrollPane = new JScrollPane(panelProject);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		/*Myloggerpanel textAreaMylogger = new Myloggerpanel();
		textAreaMylogger.setEditable(false);
		textAreaMylogger.setLineWrap(true);
		//panel_2.add(textAreaMylogger);
		panelProject.setLogger(textAreaMylogger);
		
		JScrollPane scrollPaneLog = new JScrollPane(textAreaMylogger);
		scrollPaneLog.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneLog.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPaneLog, BorderLayout.SOUTH);
		*/
		
		
		
	}

}
