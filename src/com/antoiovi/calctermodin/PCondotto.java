package com.antoiovi.calctermodin;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.antoiovi.calctermodin.panels.IPaintCond;
import com.antoiovi.calctermodin.panels.ImgCondotti;
import com.antoiovi.calctermodin.panels.ImgCondotti.Cond;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;

public class PCondotto extends JPanel {
	private ImgCondotti imgCond;
	private IPaintCond paintCond;
	private JSpinner spinner_Start_Y;
	private JSpinner spinner_Start_X;
	private JSpinner spinner_Lungh;
	private JSpinner spinnerAngoloGomito;

	/**
	 * Create the panel.
	 */
	public PCondotto() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{108, 74, 29, 0, 19, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{20, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel_1 = new JLabel("Start X");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 0;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		spinner_Start_X = new JSpinner();
		spinner_Start_X.setModel(new SpinnerNumberModel(new Integer(50), null, null, new Integer(50)));
		GridBagConstraints gbc_spinner_Start_X = new GridBagConstraints();
		gbc_spinner_Start_X.insets = new Insets(0, 0, 0, 5);
		gbc_spinner_Start_X.gridx = 3;
		gbc_spinner_Start_X.gridy = 0;
		panel.add(spinner_Start_X, gbc_spinner_Start_X);
		
		JLabel lblNewLabel_2 = new JLabel("Start Y");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.gridx = 5;
		gbc_lblNewLabel_2.gridy = 0;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		spinner_Start_Y = new JSpinner();
		spinner_Start_Y.setModel(new SpinnerNumberModel(new Integer(50), null, null, new Integer(50)));
		GridBagConstraints gbc_spinner_Start_Y = new GridBagConstraints();
		gbc_spinner_Start_Y.insets = new Insets(0, 0, 0, 5);
		gbc_spinner_Start_Y.gridx = 6;
		gbc_spinner_Start_Y.gridy = 0;
		panel.add(spinner_Start_Y, gbc_spinner_Start_Y);
		
		JButton btnResetSatrxy = new JButton("Reset satrX_Y");
		btnResetSatrxy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				Integer x=(Integer)spinner_Start_X.getModel().getValue();
				Integer y=(Integer)spinner_Start_Y.getModel().getValue();
				paintCond.resetStart_X_Y(x.intValue(), y.intValue());
				}catch(Exception e){
					
				}
				
			}
		});
		GridBagConstraints gbc_btnResetSatrxy = new GridBagConstraints();
		gbc_btnResetSatrxy.gridx = 7;
		gbc_btnResetSatrxy.gridy = 0;
		panel.add(btnResetSatrxy, gbc_btnResetSatrxy);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{81, 49, 24, 68, 0};
		gbl_panel_2.rowHeights = new int[]{23, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JButton btnNewGomito = new JButton("Add gomito ");
		btnNewGomito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Double ang=(Double)spinnerAngoloGomito.getModel().getValue();
				
				paintCond.addGomito(ang.doubleValue());
			}
		});
		GridBagConstraints gbc_btnNewGomito = new GridBagConstraints();
		gbc_btnNewGomito.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewGomito.anchor = GridBagConstraints.NORTH;
		gbc_btnNewGomito.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewGomito.gridx = 0;
		gbc_btnNewGomito.gridy = 0;
		panel_2.add(btnNewGomito, gbc_btnNewGomito);
		
		JLabel lblNewLabel_3 = new JLabel(" ");
		lblNewLabel_3.setIcon(new ImageIcon(PCondotto.class.getResource("/com/antoiovi/calctermodin/icon/FreccieRotaz_01.gif")));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 0;
		panel_2.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JLabel label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 2;
		gbc_label.gridy = 0;
		panel_2.add(label, gbc_label);
		
		spinnerAngoloGomito = new JSpinner();
		spinnerAngoloGomito.setModel(new SpinnerNumberModel(0.0, -90.0, 90.0, 0.0));
		GridBagConstraints gbc_spinnerAngoloGomito = new GridBagConstraints();
		gbc_spinnerAngoloGomito.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerAngoloGomito.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerAngoloGomito.gridx = 3;
		gbc_spinnerAngoloGomito.gridy = 0;
		panel_2.add(spinnerAngoloGomito, gbc_spinnerAngoloGomito);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paintCond.clearCond();
			}
		});
		
		JButton btnNewButton = new JButton("Add condotto");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 1;
		panel_2.add(btnNewButton, gbc_btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			 int Lungh=(Integer)spinner_Lungh.getModel().getValue();
				paintCond.addCond(Lungh); 
			}
		});
		
		JLabel lblNewLabel = new JLabel("Lunghezza");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panel_2.add(lblNewLabel, gbc_lblNewLabel);
		
		spinner_Lungh = new JSpinner();
		GridBagConstraints gbc_spinner_Lungh = new GridBagConstraints();
		gbc_spinner_Lungh.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_Lungh.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_Lungh.gridx = 3;
		gbc_spinner_Lungh.gridy = 1;
		panel_2.add(spinner_Lungh, gbc_spinner_Lungh);
		spinner_Lungh.setModel(new SpinnerNumberModel(new Integer(10), new Integer(10), null, new Integer(5)));
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnClear.insets = new Insets(0, 0, 0, 5);
		gbc_btnClear.gridx = 0;
		gbc_btnClear.gridy = 2;
		panel_2.add(btnClear, gbc_btnClear);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		
		imgCond = new ImgCondotti();
		paintCond=imgCond;
		add(imgCond, BorderLayout.CENTER);
		imgCond.setLayout(new BorderLayout(0, 0));

	}

}
