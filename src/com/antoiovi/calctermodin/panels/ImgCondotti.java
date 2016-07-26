package com.antoiovi.calctermodin.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

public class ImgCondotti extends JPanel{

private Font font;
private Font rotatedFont;
private FontMetrics fontmetrics;
int startX=50;
int startY=50;


private ArrayList<Elem> conds;
	public ImgCondotti(){
		setBackground(Color.WHITE);
		conds=new ArrayList<Elem>();
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		//Sposta l'origine in basso e rende positivo lasse y i su
			g2d.translate(0, getHeight());
				g2d.scale(1.0, -1.0);
				font = new Font(null, Font.PLAIN, 10);
				AffineTransform affineTransform = new AffineTransform();
				// Rendo il testo asse x orientato correttmente creando una
				// trasformazione che annulla la rototraslazione del piano
				affineTransform.scale(1, -1);
				rotatedFont = font.deriveFont(affineTransform);
				fontmetrics=g2d.getFontMetrics( rotatedFont);
				g2d.setFont(rotatedFont);
				Iterator<Elem> i=conds.iterator();
				int x_base=startX;
				int y_base=startY;
		while(   i.hasNext())
			{
			Elem e=i.next();
			e.paint(g2d, x_base, y_base);
			
			x_base=e.endPoint().x;
			y_base=e.endPoint().y;
		}
		
	}
	public void resetSatrt_X_Y(int x,int y){
		startX=x;
		startY=y;
		repaint();
		
	}
	
	public void addElem(){
		Elem e=new Cond();
		conds.add(e);
		 repaint();
	}
	public void addElem(Elem e){
		conds.add(e);
		 repaint();
	}
	 
	private interface Elem{
	public void paint(Graphics2D g2d ,int x_base,int y_base);
	public Point endPoint(   );
		
		}
	
	public void addCond(int Lungh){
		Cond c=new Cond();
		c.lungh=Lungh;
		addElem(c);
	}
	
	public  class Cond implements Elem{
		int lungh=50;
		Point startPoint;
		public Cond(){
			
		}
		@Override
		public void paint(Graphics2D g2d, int x_base, int y_base) {
		 startPoint=new Point(x_base,y_base);
			g2d.drawLine(x_base, y_base, endPoint().x,endPoint().y);
			
		}

		@Override
		public Point endPoint(   ) {
			Point p=new Point(startPoint.x+lungh,startPoint.y);
			return p;
		}
		
		}
	
	
}
