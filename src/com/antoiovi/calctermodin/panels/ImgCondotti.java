package com.antoiovi.calctermodin.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import com.antoiovi.logging.IOutput;
import com.antoiovi.logging.IOutput.Level;
import com.antoiovi.logging.OutputStandard;

public class ImgCondotti extends JPanel implements IPaintCond{

private Font font;
private Font rotatedFont;
private FontMetrics fontmetrics;
int startX=50;
int startY=50;

double dstartX;
double dstartY;
IOutput output;
{
	output=new OutputStandard();
	output.setLevel(Level.DEBUG);
}

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
				/*font = new Font(null, Font.PLAIN, 10);
				AffineTransform affineTransform = new AffineTransform();
				// Rendo il testo asse x orientato correttmente creando una
				// trasformazione che annulla la rototraslazione del piano
				affineTransform.scale(1, -1);
				rotatedFont = font.deriveFont(affineTransform);
				fontmetrics=g2d.getFontMetrics( rotatedFont);
				g2d.setFont(rotatedFont);*/
				Iterator<Elem> i=conds.iterator();
				int x_base=startX;
				int y_base=startY;
				dstartX=(double)startX;
				dstartY=(double)startY;
				Point2D startP=new Point2D.Double(dstartX,dstartY);
				 
				paintElementi(g2d,conds,startP);
	
	}
	
	// END PAINT COMPONENT******************************************
	static final int defaultWidth=600;
	static final int deafultHeight=300;
	static final int margin=50;
	int maxWidth=600;
	int maxHeight=300;
	int bottom=400;
	
	@Override
	public Dimension getPreferredSize() {
	    return new Dimension(maxWidth, bottom);
	}
	
	public void resetSatrt_X_Y(int x,int y){
		startX=x;
		startY=y;
		repaint();
		
	}
	@Override
	public void resetStart_X_Y(int x,int y){
		startX=x;
		startY=y;
		repaint();
	}
	
	double angoloTotale=0;
	
	@Override
	public void addGomito(double angolo){
		double ang=angoloTotale;
		String msg="angoloTotale ="+angoloTotale;
		output.logTrace(msg);
		angoloTotale+=angolo;
		if(angoloTotale>90.0 || angoloTotale<-90){
			output.logDebug(String.format("AngoloTotale minore o maggiore di 90�, resetted angoloTotale= %f ", ang));
			angoloTotale=ang;
			return;
		}
		
		Elem e=new Gomito(angolo);
		conds.add(e);
		 repaint();
	}
	
	@Override
	public void clearCond(){
		conds.clear();
		angoloTotale=0;
		repaint();
	}
	
	@Override
	public void addCond(int Lungh){
		Cond c=new Cond();
		c.lungh=Lungh;
		conds.add(c);
		 repaint();
	}
	
  
	
	
	private void paintElementi(Graphics2D g2d,List<Elem> e,Point2D point){
		double angolo=0.0;
		Point2D startPoint=point;
		Iterator i=e.iterator();
		double limitX=0;
		double limitY=0;
		while(i.hasNext()){
			Elem el=(Elem) i.next();
			el.paint2D(g2d, startPoint, angolo);
			startPoint=el.endPoint();
			angolo+=el.getAngle();
			limitX=startPoint.getX();
			limitY=startPoint.getY();
		}
		if(limitX>maxWidth){
			maxWidth=(int)limitX+margin;
			repaint();
		}
		output.logDebug(String.format("limitY = %f",limitY));
		
	}
	

	
	/***
	 *  inner class e interfaces
	 * @author Anto
	 *
	 */
	private interface Elem{
	public void paint2D(Graphics2D g2d ,double x_base,double y_base,double angolo);
	public void paint2D(Graphics2D g2d ,Point2D punto,double angolo);
	public Point2D endPoint(   );
	public double getAngle(   );
		}
	
	
	public  class Cond implements Elem{
		static final double angolo=0.0; //zero perch� � un elemento che non ruota;
		double lungh=50;
		Point2D endPoint;
		Point2D startPoint;
		
		public Cond(){
			 
		}
		
		double relativeAngle=0;
		@Override
		public Point2D endPoint(  ) {
			
			return endPoint;
		}
		
		private void calculateEndPoint(){
			double angleRad = relativeAngle * Math.PI / 180;//Converti in radianti
			output.logTrace(String.format("Cond relativeangle Radianti = %f ", angleRad));
			double	endX   = startPoint.getX()+ lungh* Math.cos(angleRad);
			double	endY   = startPoint.getY() + lungh * Math.sin(angleRad);
			endPoint=new Point2D.Double(endX,endY);
		 
		}
		

		@Override
		public void paint2D(Graphics2D g2d, double x_base, double y_base,double angle) {
			relativeAngle=angolo+angle;
		 	startPoint=new Point2D.Double(x_base,y_base);
		 	calculateEndPoint();
			g2d.draw(new Line2D.Double(startPoint.getX(),startPoint.getY(), endPoint.getX(),endPoint.getY()));
			
		}

		@Override
		public void paint2D(Graphics2D g2d, Point2D punto, double angle) {
			relativeAngle=angolo+angle;
			output.logTrace(String.format("Paint Cond relativeangle  = %f ", relativeAngle));
		 	startPoint=punto;
		 	calculateEndPoint();
		 	g2d.draw(new Line2D.Double(startPoint.getX(),startPoint.getY(), endPoint.getX(),endPoint.getY()));
		}

		@Override
		public double getAngle() {
			return angolo;
		}

		public double getLungh() {
			return lungh;
		}

		public void setLungh(double lungh) {
			this.lungh = lungh;
		}

		public Point2D getStartPoint() {
			return startPoint;
		}

		public void setStartPoint(Point2D startPoint) {
			this.startPoint = startPoint;
		}
		
		 
		
		}
	public  class Gomito implements Elem{
		Color color;
	// Agolo in gradi
		double angolo;
		Point2D endPoint;
		Point2D startPoint;
		 
		 
		double lungh=10;
		 
		public Gomito(){
			angolo=0;
		}
		public Gomito(double angol){
			angolo=angol; 
			color=Color.red;
		}
		
	
		
		double relativeAngle=0;
		@Override
		public Point2D endPoint(  ) {
			
			double angleRad = relativeAngle * Math.PI / 180;//Converti in radianti
			double	endX   = startPoint.getX()+ lungh* Math.cos(angleRad);
			double	endY   = startPoint.getY() + lungh * Math.sin(angleRad);
			Point2D p=new Point2D.Double(endX,endY);
			return p;
		}

		@Override
		public void paint2D(Graphics2D g2d, double x_base, double y_base,double angle) {
			paint2D( g2d, new Point2D.Double(x_base,y_base),   angle);
		}

		@Override
		public void paint2D(Graphics2D g2d, Point2D punto, double angle) {
			String msg=	String.format("Paint gomito; angolo = %f", angolo);
			output.logTrace(msg);
			Color oldcolor=g2d.getColor();
			g2d.setColor(color);
			relativeAngle=angolo+angle;
		 	startPoint=punto;
		 	calculateEndPoint();
		 	g2d.draw(new Line2D.Double(startPoint.getX(),startPoint.getY(), endPoint.getX(),endPoint.getY()));
		 	g2d.setColor(oldcolor);
		}

		@Override
		public double getAngle() {
			return angolo;
		}
		
		private void calculateEndPoint(){
			double angleRad = relativeAngle * Math.PI / 180;//Converti in radianti
			double	endX   = startPoint.getX()+ lungh* Math.cos(angleRad);
			double	endY   = startPoint.getY() + lungh * Math.sin(angleRad);
			endPoint=new Point2D.Double(endX,endY);
			 
		 
		}
		
		}
	
	
	
}
