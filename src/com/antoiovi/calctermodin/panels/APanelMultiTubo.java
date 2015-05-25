package com.antoiovi.calctermodin.panels;

import javax.swing.JPanel;

import java.awt.GridLayout;

import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.util.ArrayList;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.JLabel;

public class APanelMultiTubo extends JPanel {
	ArrayList<APanelTubo_2> panelslist;
	private JPanel panel;
	private SpringLayout slayout;
	/**
	 * Create the panel.
	 */
	public APanelMultiTubo() {
		setLayout(new BorderLayout(0, 0));
		panelslist=new ArrayList<APanelTubo_2>();
		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		slayout = new SpringLayout();
		panel.setLayout(slayout);
		CreaPane(4);
	
//		panel.setLayout(null);

	}

	public void CreaPane(int count) {
		panelslist.clear();
		panel.removeAll();
		panel.setLayout(slayout);
		APanelTubo_lbl panel_lbl = new APanelTubo_lbl();
		slayout.putConstraint(SpringLayout.NORTH, panel_lbl, 10,
				SpringLayout.NORTH, panel);
		slayout.putConstraint(SpringLayout.WEST, panel_lbl, 10,SpringLayout.WEST, panel);
		slayout.putConstraint(SpringLayout.SOUTH, panel_lbl, -10,
				SpringLayout.SOUTH, panel);
		slayout.putConstraint(SpringLayout.EAST, panel_lbl, 200,SpringLayout.WEST, panel);
		panel.add(panel_lbl);
		for (int x = 0; x < count; x++) {
			if (x == 0) {
				APanelTubo_2 panel_1 = new APanelTubo_2();
				slayout.putConstraint(SpringLayout.NORTH, panel_1, 10,SpringLayout.NORTH, panel);
				slayout.putConstraint(SpringLayout.WEST, panel_1,5,SpringLayout.EAST, panel_lbl);
				panelslist.add(panel_1);
				panel.add(panel_1);
			} else {
				APanelTubo_2 prev = panelslist.get(x - 1);
				APanelTubo_2 panel_1 = new APanelTubo_2();
				slayout.putConstraint(SpringLayout.NORTH, panel_1, 10,SpringLayout.NORTH, panel);
				slayout.putConstraint(SpringLayout.WEST, panel_1,5,SpringLayout.EAST, prev);
				panelslist.add(panel_1);
				panel.add(panel_1);
			}

		}
		panel.validate();
		panel.repaint();
	}
}
