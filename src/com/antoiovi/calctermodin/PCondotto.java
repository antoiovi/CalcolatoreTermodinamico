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
import com.antoiovi.calctermodin.panels.ImgCondotti;
import com.antoiovi.calctermodin.panels.ImgCondotti.Cond;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;

public class PCondotto extends JPanel {
	private ImgCondotti imgCond;
	private JSpinner spinner_Start_Y;
	private JSpinner spinner_Start_X;
	private JSpinner spinner_Lungh;

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
		
		JLabel lblNewLabel = new JLabel("Lunghezza");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		spinner_Lungh = new JSpinner();
		spinner_Lungh.setModel(new SpinnerNumberModel(new Integer(10), new Integer(10), null, new Integer(5)));
		GridBagConstraints gbc_spinner_Lungh = new GridBagConstraints();
		gbc_spinner_Lungh.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_Lungh.insets = new Insets(0, 0, 0, 5);
		gbc_spinner_Lungh.anchor = GridBagConstraints.NORTH;
		gbc_spinner_Lungh.gridx = 1;
		gbc_spinner_Lungh.gridy = 0;
		panel.add(spinner_Lungh, gbc_spinner_Lungh);
		
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
				imgCond.resetSatrt_X_Y(x.intValue(), y.intValue());
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
		
		JButton btnNewButton_1 = new JButton("Nuovo Elem()");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 imgCond.addElem();
			}
		});
		
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Nuovo Elem( Elem e)");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			 int Lungh=(Integer)spinner_Lungh.getModel().getValue();
				imgCond.addCond(Lungh); 
			}
		});
		panel_1.add(btnNewButton);
		
		imgCond = new ImgCondotti();
		add(imgCond, BorderLayout.CENTER);
		imgCond.setLayout(new BorderLayout(0, 0));

	}

}
