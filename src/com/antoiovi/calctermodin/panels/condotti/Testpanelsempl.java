package com.antoiovi.calctermodin.panels.condotti;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Testpanelsempl extends JFrame {

	private JPanel contentPane;
	private APCondottiSempl panel_cond;
	private JSpinner spinner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Testpanelsempl frame = new Testpanelsempl();
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
	public Testpanelsempl() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel_cond = new APCondottiSempl();

		JScrollPane scrollPane = new JScrollPane(panel_cond);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		spinner = new JSpinner();
		panel_1.add(spinner);
		
		JButton btnNpiani = new JButton("npiani ");
		btnNpiani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer i=(Integer)spinner.getModel().getValue();
				int n=i.intValue();
				panel_cond.setNumberPanels(n);
			}
		});
		panel_1.add(btnNpiani);
	}

}
