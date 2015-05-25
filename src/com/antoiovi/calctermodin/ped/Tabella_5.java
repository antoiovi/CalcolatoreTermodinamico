package com.antoiovi.calctermodin.ped;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.antoiovi.util.math.Geometry;
public class Tabella_5 {
private double V;
private double PS;
private final double ps05=Math.log10(0.5);
private final double ps32=Math.log10(32);
private final double ps3=Math.log10(3);
private final double v2=Math.log10(2);
private final double v1000=Math.log10(1000);



BufferedImage tabellaBuffImage;
double asse_x[];
double asse_y[];
public Tabella_5(){
	//tabella=new BufferedImage(7,6, 0);
	asse_x=Geometry.ScaleLog10(0.1,100000);
	asse_y=Geometry.ScaleLog10(0.1,10000);
	Geometry.Traslation(asse_x, 1);
	Geometry.Traslation(asse_y, 1);
	
}

public BufferedImage recreateImage(){
	int s=100;
	double margin=10;
	tabellaBuffImage=new BufferedImage(1500,1500, BufferedImage.TYPE_INT_ARGB);
	double y_max=asse_y[asse_y.length-1];
	double x_max=asse_x[asse_x.length-1];
	Graphics2D g2d=tabellaBuffImage.createGraphics();
	g2d.setColor(Color.BLACK);
	g2d.translate(0, 500);
	g2d.scale(1.0, -1.0);    
	
	   g2d.drawLine(0, 0, 500, 500); 
	for(int x=0;x<asse_x.length;x++){
					((Graphics2D) g2d).draw(new Line2D.Double(margin+asse_x[x]*s,margin,margin+ asse_x[x]*s,margin+  y_max*s));
		}
	for(int y=0;y<asse_y.length;y++){
		((Graphics2D) g2d).draw(new Line2D.Double(margin,margin+asse_y[y]*s,x_max*s,margin+asse_y[y]*s));
	}
	// Pressione 0.5
	g2d.setColor(Color.RED);
	((Graphics2D) g2d).draw(new Line2D.Double(margin,margin-ps05*s, margin+x_max*s,margin-ps05*s));
	//g2d.drawLine(0,(int)ps05*s, (int)x_max*s,(int)ps05*s);
	// Volume 2
	((Graphics2D) g2d).draw(new Line2D.Double(margin+v2*s,margin-ps05*s, margin+v2*s,margin+y_max*s));
	// p32
	((Graphics2D) g2d).draw(new Line2D.Double(margin+v2*s,margin+ps32*s, margin+Math.log10(93.75)*s,margin+ps32*s));
		//g2d.drawLine((int)v2,(int)ps05*s, (int)v2*s,(int)y_max*s);
		
	
	/*AffineTransform tx = new AffineTransform();
    tx.rotate(theta);
    AffineTransformOp op = new AffineTransformOp(tx,
        AffineTransformOp.TYPE_BILINEAR);
    tabellaBuffImage= op.filter(tabellaBuffImage, null);*/
	//g2d.drawLine(0, 0, 100, 100);	
	return tabellaBuffImage;
}



public Tabella_5(double v, double pS) {
	super();
	V = v;
	PS = pS;
}

public  int Categoria(double p,double v){
	this.PS=p;
	V=v;
	int categoria=0;
	if(v<=2){
		categoria=0;
	}else if(PS<=0.5){
		categoria=0;
	}
	else if(PS>32 || V>1000){
		categoria=4;
	}else {
		double psv=PS*V;
		if(psv<=50)
			categoria=1;
		else if(psv<=200)
			categoria=2;
		else if(psv<=3000)
			categoria=3;
	}
	return categoria;
}

}
