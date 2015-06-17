package com.antoiovi.calctermodin;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.border.LineBorder;

public class APanelScambiatore extends JPanel {
	ImageScambiatore imagescabiatore;
	/**
	 * Create the panel.
	 */
	public APanelScambiatore() {
		imagescabiatore=new ImageScambiatore();
	}	
		@Override
		protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			Graphics2D g2d=(Graphics2D)g;
			g2d.setBackground(Color.white);
			g2d.drawImage(imagescabiatore.recreateImage(),0,0, null);
		}
		
	}
	

