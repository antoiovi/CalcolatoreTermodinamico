package com.antoiovi.calctermodin.panels;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

public class TestSpringLayout extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestSpringLayout frame = new TestSpringLayout();
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
	public TestSpringLayout() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JPanel panel_1 = new JPanel();
		sl_panel.putConstraint(SpringLayout.WEST, panel_1, 5, SpringLayout.WEST, panel);
		panel.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		panel_1.add(lblNewLabel);
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel, 73, SpringLayout.WEST, panel);
		
		JPanel panel_2 = new JPanel();
		sl_panel.putConstraint(SpringLayout.WEST, panel_2, 100, SpringLayout.EAST, panel_1);
		panel.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		panel_2.add(lblNewLabel_1);
	}

}
