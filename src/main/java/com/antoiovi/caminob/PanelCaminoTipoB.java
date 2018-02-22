package com.antoiovi.caminob;

import it.iovino.utilita.Mylogger;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JTextArea;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class PanelCaminoTipoB extends JPanel {

	private TipoB panelTipob;

	/* ******
	Mylogger
		int OFF=0;
		int SUPERFINE=1;
		int FINEST=3;
		int FINE=4;
		int INFO=5;
		int MESSAGE=6;
		int WARNING=7;
		int GRAVE=8;
	********/
	static final int LOG_LEVEL=Mylogger.INFO;
	/**
	 * Create the panel.
	 */
	public PanelCaminoTipoB() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1120, 0};
		gridBagLayout.rowHeights = new int[]{453, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		panelTipob = new TipoB();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panelTipob, gbc_panel);
		/***
		**   LOGGER PANEL
		***/
		Myloggerpanel textArea = new Myloggerpanel();
		textArea.setLineWrap(true);
		textArea.setLevel(LOG_LEVEL);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		panelTipob.setLogger(textArea);
			
		/*JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridheight = 4;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		add(panel_2, gbc_panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel_2.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
	/*	Myloggerpanel textAreaMylogger = new Myloggerpanel();
		textAreaMylogger.setEditable(false);
		textAreaMylogger.setLineWrap(true);
		//panel_2.add(textAreaMylogger);
		panelTipob.setLogger(textAreaMylogger);
		
		JScrollPane scrollPane = new JScrollPane(textAreaMylogger);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.add(scrollPane, BorderLayout.CENTER);*/

	}

	public void setLogger(Mylogger textAreaMylogger) {
		//panelTipob.setLogger(textAreaMylogger);
		
	}

}
