package com.antoiovi.calctermodin;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;

import com.antoiovi.calctermodin.panels.APanelTubo;
import com.antoiovi.calctermodin.ped.Tabella_5;

import java.awt.FlowLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

public class ATabPaneScambiatore extends JPanel {
	APanelScambiatore apanel;
	private APanelTubo panelcond;
	public ATabPaneScambiatore() {
		
		Panel panel = new Panel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:max(145dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:default"),}));
		
		apanel= new APanelScambiatore();
		apanel.setBackground(Color.WHITE);
		apanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		add(apanel, "4, 2, fill, fill");
		
		panelcond = new APanelTubo();
		add(panelcond, "4, 4, left, fill");
				
		
	}
	
	class Panel extends JPanel{
		ImageScambiatore images=new ImageScambiatore();
				@Override
				protected void paintComponent(Graphics g) {
					// TODO Auto-generated method stub
					Graphics2D g2d=(Graphics2D)g;
					//g2d.scale(4,4);
//					g2d.translate(1, -0.5);
					g2d.drawImage(images.recreateImage(),0,0, null);
				}
				
			}

}
