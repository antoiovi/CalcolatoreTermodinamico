package com.antoiovi.calctermodin;

import it.iovino.fluidi.Fluido;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.antoiovi.unicig.condotti.VelocitaFluido;
import com.antoiovi.unicig.tubi.Tubo;

/**
 * Vengono calcolati:
 *    Il numero di Nusselt
 *     Coefficente liminare interno
 *      Coefficente globale di scambio termico
 *     Fattore di raffreddamento
 *      Temperatura uscita
 *      Temperatura media
 * @param tubo
 * @param Portmassica
 * @param fluidoi
 * @param coeflime
 * @param velflu
 * @param Tamb
 * @param Tinput 
 */
    /*public void Calcola(Tubo tubo,double Portmassica,Fluido fluidoi,
            double coeflime, VelocitaFluido velflu ,double Tamb,double Tinput)
*/


public class ImageScambiatore {
BufferedImage bufimage;
public BufferedImage getBufimage() {
	return bufimage;
}
Line2D linetest;
Line2D line1;
Line2D line2;
Line2D line3;
Line2D line4;
List<Stringa> etichette;
int hfont=10;

public ImageScambiatore(){
	etichette=new ArrayList<Stringa>();
	Stringa Ti=new Stringa();
	
	Stringa Te=new Stringa();
	Ti.testo="Ti";
	etichette.add(Ti);
	Te.testo="Te";
	etichette.add(Te);
	double x1,x2,y1,y2,y3,y4;
	x1=50;
	x2=250;
	y1=50;
	y2=75;
	y3=100;
	y4=125;
Ti.x=(float)x1;
Ti.y=(float)y1+hfont;
Te.x=(float)x1;
Te.y=(float)y2+hfont;
line1=new Line2D.Double(x1,y1,x2,y1);
line2=new Line2D.Double(x1,y2,x2,y2);
line3=new Line2D.Double(x1,y3,x2,y3);
line4=new Line2D.Double(x1,y4,x2,y4);
// linetest=new Line2D.Double(0,0,500,300);



}



public BufferedImage recreateImage(){
	int s=100;
	double margin=10;
	bufimage=new BufferedImage(250,200, BufferedImage.TYPE_INT_ARGB);
	Graphics2D g2d=bufimage.createGraphics();
	g2d.setColor(Color.BLACK);
	g2d.translate(0, 200);
	g2d.scale(1.0, -1.0);  
	Font font = new Font(null, Font.PLAIN, hfont);
	AffineTransform affineTransform = new AffineTransform();
	// Rendo il testo asse x orientato correttmente creando una
	// trasformazione che annulla la rototraslazione del piano
	affineTransform.scale(1, -1);
	Font rotatedFont = font.deriveFont(affineTransform);
	g2d.setFont(rotatedFont);
	// get metrics from the graphics
	FontMetrics metrics = g2d.getFontMetrics(font);
	
	g2d.draw(line1);
	g2d.draw(line2);
	g2d.draw(line3);
	g2d.draw(line4);
	//g2d.draw(linetest);
	//g2d.drawString(str, x, y);
	Iterator<Stringa> i=etichette.iterator();
	while(i.hasNext()){
		Stringa str=(Stringa)i.next();
		g2d.drawString(str.testo,str.x,str.y);
			}
	return bufimage;
}

private class Stringa {
	public String testo;
	public  float x;
	public float y;
};

}
