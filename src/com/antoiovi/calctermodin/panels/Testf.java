package com.antoiovi.calctermodin.panels;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;

public class Testf extends JFrame {

	private JPanel contentPane;
	private final JSpinner spinner = new JSpinner();
	private APanelMultiTubo panelmultitubo_1;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 UIManager.setLookAndFeel(
					            UIManager.getSystemLookAndFeelClassName());
					Testf frame = new Testf();
					
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
	public Testf() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 668);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		//panelmultitubo=new 	APanelMultiTubo();
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x=(Integer)spinner.getModel().getValue();
				panelmultitubo_1.CreaPane(x);
			}
		});
		contentPane.add(btnNewButton, BorderLayout.NORTH);
		contentPane.add(spinner, BorderLayout.SOUTH);
		spinner.setModel(new SpinnerNumberModel(1, 1, 7, 1));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));
		
		panelmultitubo_1 = new APanelMultiTubo();
		panel.add(panelmultitubo_1,BorderLayout.CENTER);
		//panelmultitubo_1.setLayout(null);
		//panelmultitubo_1.CreaPane(2);
		//panel.setLayout(null);
	}

}
