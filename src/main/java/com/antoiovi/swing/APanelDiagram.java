package com.antoiovi.swing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.antoiovi.util.math.Geometry;

import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;





import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JViewport;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;



import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;

/**
 * Override paintComponent invece diComponent, per l'inserimento in JscrollPanel
 * 
 * @version 1.0
 * @author Antoiovi date 21/11/2014 8/12/2014 : prima versione funzionante
 *         bbastanza bene: ancora da verificare con valori negativi ed
 *         implementare menù labels UTILIZZO: 
 *         		double x[]=GenerateScale.Scale(-500, 500,1.0); 
 *       		 panel.setX_axis(x);
 *        	 panel.setY_axis(y);
 *        	  double[] f=getPow(2,x); [indice di f deve essere minore o uguale a x]
 *         	 int  min=GenerateScale.getMin(f);
 *        	  int max=GenerateScale.getMax(f);
 *        	 double  y[]=GenerateScale.Scale(f[min], f[max],100); 
 *         	 panel.setFunctions(f);
 *         ItemListeer : per intercettare i cjechboxmenuItems
 *         16/12/2014 : revisione 
 *         Utilizzo: Implementazione dell'interfaccia APDiagram dove sono riportati
 *         iprototopi delle  funzioni usate
 *         
 *         addStringa(String,x,y) Stmpa una stringa nella posizione xy
 *         clearStringhe(); pulisce le stringhe
 *         20/12/2014: aggiunto setFormatNumberAxisX(..
 *          aggiunto setFormatNumberAxisY(..
 *         per differnziare il formato valori asse x  e asse y
 */

public class APanelDiagram extends JPanel implements ItemListener, ActionListener,APDiagram {
	/**
	 * Deve assumere uno dei valori ADAPT... 2/12/2014 attualmente usate solo
	 * AADAPTXYSCALEBLOCKED e ADAPTXYSCALE
	 */
	private int adapt_scale;
	public  final int ADAPTXSCALE = 1;
	public  final int ADAPTYSCALE = 2;
	public  final int ADAPTXYSCALE = 3;
	public  final int ADAPTSCALEBLOCKED = 4;
	// PROPIETA' AFFERENTI AI DATI
	private double x_axis[];
	private double y_axis[];
	/**
	 * Valori da utilizzare per la stringa: vengono modificati in base alla opzione di visualizzazione
	 * senza alterare i dati originali
	 */
	private  double x_axis_str[];
	private  double y_axis_str[];
	public void setX_axis_str(double[] x_axis_str) {
		this.x_axis_str = x_axis_str;
	}
	public void setY_axis_str(double[] y_axis_str) {
		this.y_axis_str = y_axis_str;
	}
	/**
	 * PROPIETA ATTINENTI GLI ASSI
	 */
	/**
	 * Margine sinistro: margine + larghezza massima testo: COORDINATA X ASSE
	 * ORDINATE SE I CLICK MOSE SONO A DESTRA APRO MENU LABL
	 */
	  int margin_left;// = margin + size_label_y.width;
	/**
	 * Margine inferiore margine + altezza testo; corrisponde alla ordinata asse
	 * x SE I CLICK MOSE SONO A SOTT APRO MENU LABEL
	 */
	  int margin_bottom;
	 
	 public  double[] getX_axis_str() {
		return x_axis_str;
	}
	public  double[] getY_axis_str() {
		return y_axis_str;
	}
	public  int getMargin_left() {
		return margin_left;
	}
	public  int getMargin_bottom() {
		return margin_bottom;
	}
	public  int getAdaptxscale() {
		return ADAPTXSCALE;
	}
	public  int getAdaptyscale() {
		return ADAPTYSCALE;
	}
	public  int getAdaptxyscale() {
		return ADAPTXYSCALE;
	}
	public  int getAdaptscaleblocked() {
		return ADAPTSCALEBLOCKED;
	}
	
	
	
	
	
	// = margin + size_label_y.height;
	// PROPIETA' AFFERENTI LA SCALA
	/**
	 * sacala impostabile dll'utente; CAMBIABILE TRAMITE MENUPOPUP, PER 2 E
	 * DIVISO 2; se uso adapt scale, e poi torno a scala fissa, appena
	 * semodifica la l'ampieza torna a questa scala originaria
	 */
	private double x_scale = 50;
	/**
	 * y_scale : sacala impostabile dll'utente
	 * 
	 */
	private double y_scale = 50;
	/**
	 * 2/12/2014 x_scale_adapted: scala calcolata in base alle opzioni impostate
	 * di adapt_scale; è quella usata nei calcoli, e di default vale x_scale
	 */
	private double x_scale_adapted;
	/**
	 * Y_scale_adapted: scala calcolata in base alle opzioni impostate di
	 * adapt_scale; è quella usata nei calcoli per evetuamente adattare allo
	 * schermo, e di default vale Y_scale
	 */
	private double y_scale_adapted;
	

	// PROPIETA' AFFERENTI LE ETICHETTE

	/**
	 * Stabilisce come deve essere disposto il testo dei valori dell'asse x
	 */
	/*private int label_x;
	public  final int LABEL_X_NORMAL = 1;
	public static final int LABEL_X_ADAPT = 2;
	public static final int LABEL_X_ALTERNATE = 3;
	// Visualizza solo i valori 10, 100,1000, ....
	public static final int LABEL_X_LOGARITM = 4;*/

	private int margin = 20;
	
	/**
	 * Rectangle delle etichette valory x
	 */
	private List<Rectangle> rect_x_label;
	/**
	 * Rectangle dei valori delle etichette asse Y
	 */
	private List<Rectangle> rect_y_label;
	/**
	 * Array delle coordinate dei punti u'llasse x
	 */
	private double x_coordinate[];
	/**
	 * Array delle coorinate dei punti sull'asse y
	 */
	private double y_coordinate[];
	/**
	 * Funzione da disegnare
	 */
	private double functions[];
	/**
	 *  Lista di punti di cui disegnare ascissa e ordinata
	 */
	private List<Punto> punti;
	
	
	/**
	 * AGGIUNGE UN PUNTO DI CUI DISEGNARE LE ASISE E ORDINATE
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean addPunto(double x,double y) {
		Punto e=new Punto();
		e.x=x;
		e.y=y;
		return punti.add(e);
	}
	public boolean addPunto(Punto p) {
			return punti.add(p);
	}
	/**
	 * Colore con cui si disegano le coordinate del punto
	 */
	Color color_punto=Color.green;
	
	/**
	 * Elimina tutti i punti di cui vedere le coordinate
	 */
	public void clearPunti() {
		punti.clear();
	}
	/**
	 *  Stringhe con coordinate in cui scriverle
	 */
	private List<Stringa> stringhe;
	
	public void setStringa(int index, String str) {
		Stringa stri=stringhe.get(index);
		stri.testo=str;
	}
	public boolean addStringa(String str,double x,double y) {
		Stringa stri=new Stringa();
		stri.testo=str;
		stri.x=x;
		stri.y=y;
		return stringhe.add(stri);
	}
	/**
	 * eliminoo le stringhe visualizzate
	 */
	public void clearStringhe() {
		stringhe.clear();
	}
	
	
	/**
	 * DATI ATTENENTI LA GRIGLIA
	 */
	private Color colorGridHoriz = Color.gray;
	private Color colorGridVert = Color.gray;
	private boolean showGridHorizontal = true;
	private boolean showGridVertical = true;

	private String formatNumberAxis = "%2.2f";
	private String formatNumberAxisX = "%2.2f";
	private String formatNumberAxisY = "%2.2f";
	
	private  Rectangle rect_view;
	private  Rectangle rect_panel;
	private  Container parent;

	/***
 * 	ELEMENTI DEI MENU DI CONTESTO
 */
	private JCheckBoxMenuItem chckbxmnAdattascala;
	private  JPopupMenu popupX;
	private  JPopupMenu popupMenu;
	private  JMenuItem mntmScala_x_2;
	private  JMenuItem mntmScala_div_2;
	private JMenuItem mntmAsseY_2;
	private JMenuItem mntmAsseX1_2;
	private JMenuItem mntmAsseY2x;
	private JMenuItem mntmAsseX2x;
	private JCheckBoxMenuItem chckbxmntmGrigliaOrizz;
	private JCheckBoxMenuItem chckbxmntmGridVert;
	private JMenuItem mntmLog;
	private JMenuItem mntm10E;
	private  JPopupMenu popupY;
	private JMenuItem menuItem;
	private JMenuItem menuItem_1;
	private JMenuItem mntmResety;
	private JMenuItem mntmResetx;
	private   boolean menu_abilitated=true;
	public  boolean isMenu_abilitated() {
		return menu_abilitated;
		}
	
	/************************************************
	 * Create the panel.	COSTRUTTORE				*
	 ************************************************/
	public APanelDiagram() {
		super();
		this.setBackground(Color.white);
		// formatNumberAxis = formatAxisExp;
		popupMenu = new JPopupMenu();
		addPopup(this, popupMenu);

		chckbxmnAdattascala = new JCheckBoxMenuItem("Adatta A Frame");
		mntmScala_x_2 = new JMenuItem("Scala per 2");
		mntmScala_div_2 = new JMenuItem("Scala  per 1/2");

		chckbxmnAdattascala.addItemListener(this);
		chckbxmnAdattascala.setActionCommand("AdattaAFrame");
		mntmScala_x_2.addActionListener(this);
		popupMenu.add(chckbxmnAdattascala);
		popupMenu.add(mntmScala_x_2);
		popupMenu.add(mntmScala_div_2);

		mntmAsseX2x = new JMenuItem("Asse X 2x");
		mntmAsseX2x.addActionListener(this);
		popupMenu.add(mntmAsseX2x);

		mntmAsseX1_2 = new JMenuItem("Asse X 1/2");
		mntmAsseX1_2.addActionListener(this);
		popupMenu.add(mntmAsseX1_2);

		mntmAsseY_2 = new JMenuItem("Asse y 1/2");
		mntmAsseY_2.addActionListener(this);
		popupMenu.add(mntmAsseY_2);

		mntmAsseY2x = new JMenuItem("Asse Y 2x");
		mntmAsseY2x.addActionListener(this);
		popupMenu.add(mntmAsseY2x);
		
		chckbxmntmGrigliaOrizz = new JCheckBoxMenuItem("Griglia orizz.");
		chckbxmntmGrigliaOrizz.setSelected(true);
		popupMenu.add(chckbxmntmGrigliaOrizz);
		/**
		 * Per i checkbox menu item devo inserire itemListener
		 */
		chckbxmntmGrigliaOrizz.addItemListener(this);
		chckbxmntmGridVert = new JCheckBoxMenuItem("Griglia vert.");
		chckbxmntmGridVert.setSelected(true);
		popupMenu.add(chckbxmntmGridVert);
		chckbxmntmGridVert.addItemListener(this);
		mntmScala_div_2.addActionListener(this);

		popupX = new JPopupMenu();
		addPopup(this, popupX);

		mntm10E = new JMenuItem("exp10");
		popupX.add(mntm10E);
		mntm10E.addActionListener(this);
		
		mntmLog = new JMenuItem("log10");
		mntmLog.addActionListener(this);
		popupX.add(mntmLog);
		
		mntmResetx = new JMenuItem("resetX");
		mntmResetx.addActionListener(this);
		popupX.add(mntmResetx);
		
		
		popupY = new JPopupMenu();
		add(popupY);
		
		menuItem = new JMenuItem("exp10Y");
		menuItem.setActionCommand("exp10Y");
		menuItem.addActionListener(this);
		popupY.add(menuItem);
		
		menuItem_1 = new JMenuItem("log10Y");
		menuItem_1.addActionListener(this);
		menuItem_1.setActionCommand("log10Y");
		popupY.add(menuItem_1);
		
		mntmResety = new JMenuItem("resetY");
		mntmResety.addActionListener(this);
		popupY.add(mntmResety);
		
		/**
		 * Creo l'array dei punti da visualizzare con coordinate
		 */
		punti =new ArrayList<Punto>();
		
		
		/**
		 * Creo lalista d stringhe da visualizzare con coordinate
		 */
		stringhe=new ArrayList<Stringa>();
		
		// default data
		x_axis = new double[10];
		y_axis = new double[10];
		for (int x = 0; x < 10; x++) {
			x_axis[x] = x;
			y_axis[x] = x;
		}
		x_axis_str =new double[x_axis.length];
		System.arraycopy(x_axis, 0, x_axis_str,0, x_axis.length);
		y_axis_str =new double[y_axis.length];
		System.arraycopy(y_axis, 0, y_axis_str,0, y_axis.length);

	}
	/*************************************************************
	 * 		METODO PAINTCOMPONENT
	************************************************************* */

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		/**
		 * Creo gli array con le aree delle etichette
		 */
		rect_x_label = new ArrayList<Rectangle>();
		rect_y_label = new ArrayList<Rectangle>();
		
		
		Graphics2D g2d = (Graphics2D) g;
		// Sposta l'origine in basso e rende positivo lasse y i su
		g2d.translate(0, getHeight());
		g2d.scale(1.0, -1.0);
		Font font = new Font(null, Font.PLAIN, 10);
		AffineTransform affineTransform = new AffineTransform();
		// Rendo il testo asse x orientato correttmente creando una
		// trasformazione che annulla la rototraslazione del piano
		affineTransform.scale(1, -1);
		Font rotatedFont = font.deriveFont(affineTransform);
		g2d.setFont(rotatedFont);
		// get metrics from the graphics
		FontMetrics metrics = g2d.getFontMetrics(font);
		/**
		 * Calcola la larghezza massima del testo delle assi y per potere
		 * calcolare il margine sinistro
		 */
		int adv0 = 0;
		int hgt;
		int adv;
		Dimension size_label_y = new Dimension(margin, margin);
		for (int z = 0; z < y_axis_str.length; z++) {
			String s = String.format(formatNumberAxis, y_axis_str[z]);
			// get the height of a line of text in this
			// font and render context
			hgt = metrics.getHeight();
			// get the advance of my text in this font
			// and render context
			adv = metrics.stringWidth(s);
			// calculate the size of a box to hold the
			// text with some padding.
			if (adv > adv0) {
				size_label_y = new Dimension(adv + 2, hgt + 2);
			}

		}
		/**
		 * Margine sinistro: margine + larghezza massima testo
		 */
		margin_left = margin + size_label_y.width;
		/**
		 * Margine inferiore margine + altezza testo
		 */
		margin_bottom = margin + size_label_y.height;

		/**
		 * calcolo la scala
		 */

		parent = this.getParent();
		/**
		 * rect_view sono i confini della vista che non necessariamente
		 * orrispondono a quelli del disegn(pannello) devo usare questi per
		 * calcolare le scale di adattaamento
		 */
		if (parent instanceof JViewport)
			rect_view = this.getParent().getBounds();
		else
			rect_view = this.getBounds();
		/**
		 * I confni del pannello vengoo tuttavia utilizzati, in quanto quand uso
		 * uno jscroll, devo fare riferimento ai confini del disegno(pannello),
		 * per esempio quando clicco sopra per ottenere le coordinte relative al
		 * disegno
		 */

		rect_panel = this.getBounds();
		/**
		 * adatta la scala all'ampiezza della finestra
		 */
		switch (adapt_scale) {
		case ADAPTXYSCALE:
			double h2 = y_axis[y_axis.length - 1];
			double h1 = y_axis[0];
			double w2 = x_axis[x_axis.length - 1];
			double w1 = x_axis[0];
			double h = h2 - h1;
			double w = w2 - w1;
			// String
			// s=String.format("h2=%f \t h1=%f \t  \t h2-h1=%f",h2,h1,(h2-h1));
			h = h < 1 ? 1 / h : h;
			w = w < 1 ? 1 / w : w;
			//String s = String.format("h2=%f \t h1=%f \t  \t h=%f", h2, h1, h);
			x_scale_adapted = (rect_view.width - margin_left * 2) / w; // x_axis[x_axis.length
																		// - 1];
			y_scale_adapted = (rect_view.height - margin_bottom * 2) / h;
			y_scale_adapted = y_scale_adapted < 0 ? -y_scale_adapted
					: y_scale_adapted;
			x_scale_adapted = x_scale_adapted < 0 ? -x_scale_adapted
					: x_scale_adapted;
			// String
			// s=String.format("wy=%d  h=%f (rect_view.height-margin_bottom*2)/ h=%f\ty_axis[y_axis.length - 1]=%f",wy,h,((rect_view.height-margin_bottom*2)/
			// h),y_axis[y_axis.length - 1]);
			// System.out.println(s);
			break;
		case ADAPTSCALEBLOCKED:
			// break;
		default:
			x_scale_adapted = x_scale;
			y_scale_adapted = y_scale;
		}

		/**
		 * DISEGNO ASSI
		 */
		
		/**
		 * Creo l'array delle coordinate, VERRà INIZIALIZZATO IN BASE AALLA
		 * SCAALA
		 */
		// test traslo i dtai di margin left
		 x_coordinate= Geometry.CreteNewArray(x_axis);

		Geometry.Moltiply(x_coordinate, x_scale_adapted);
		Geometry.Traslation(x_coordinate, margin_left - x_coordinate[0]);
		/**
		 * Asse delle coordinate Y
		 */
		y_coordinate = Geometry.CreteNewArray(y_axis);
		/*
		 * Estensione in ase alla scala
		 */
		Geometry.Moltiply(y_coordinate, y_scale_adapted);
		//System.out.println("y[0]=" + y_coordinate[0]);
		/**
		 * Traslazione in base al margine sinistro
		 */
		int f = Geometry.getMin(y_coordinate);
		Geometry.Traslation(y_coordinate, margin_bottom - y_coordinate[f]);// fy_coordinate[0]);
	//	System.out.println("y 0 traslato" + y_coordinate[0] + "   Margine "
	//			+ margin_bottom);
		/**
		 * Creo l'elenco delle coordinate dei punti ed i rectangle delle
		 * etichette dell'asse x
		 */
		for (int x = 0; x < x_axis_str.length; x++) {
			String s = String.format(formatNumberAxis, x_axis_str[x]);
			Rectangle rect = new Rectangle((int) x_coordinate[x], margin,
					metrics.stringWidth(s), metrics.getHeight());
			rect_x_label.add(rect);
		}

		/**
		 * Disegno asse x
		 */
		g2d.drawLine((int) x_coordinate[0], (int) y_coordinate[0],
				(int) x_coordinate[x_coordinate.length - 1],
				(int) y_coordinate[0]);
		/**
		 * Disegno asse Y
		 */
		
		
		g2d.drawLine((int) x_coordinate[0], (int) y_coordinate[0],
				(int) x_coordinate[0],
				(int) y_coordinate[y_coordinate.length - 1]);
		/**
		 * Nuova larghezza DISEGNO: Larghezza dell'asse x disegnato: sarà la
		 * nuova larghezza finestra
		 */
		double new_width = (int) x_coordinate[x_coordinate.length - 1];
		/**
		 * Altezza disegno uguale altezza ultimo punto asse Y
		 */
		long new_height = (long) y_coordinate[y_coordinate.length - 1];
		/**
		 * Disegno etichette asse X
		 */
		this.paintLabelX(g2d);

		/**
		 * Creo l'elenco dei rectangle delle etichette dell'asse y
		 */
		for (int x = 0; x < y_axis_str.length; x++) {
			String s = String.format(formatNumberAxis, y_axis_str[x]);
			Rectangle rect = new Rectangle(margin, (int) y_coordinate[x],
					metrics.stringWidth(s), metrics.getHeight());
			rect_y_label.add(rect);
		}
		this.paintLabelY(g2d);

		/**
		 * DISEGNA LA FUNCTION
		 */

		if (functions != null) {
			GeneralPath pl_f = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
			pl_f.moveTo(x_coordinate[0], y_coordinate[0]);
			//double max = new_height;
			int k=(x_coordinate.length<=functions.length)?x_coordinate.length:functions.length;
			for (int j = 0; j < k; j++) {
				double yf = margin_bottom + functions[j] * y_scale_adapted;
				// new_height=new_height > yf ? new_height: (long)yf;
				//System.out.println("x=  " + x_coordinate[j] + "  yf=" + yf);
				pl_f.lineTo(x_coordinate[j], yf);
			}
			g2d.draw(pl_f);
		}
		
		/**
		 * Paints le griglie
		 */
		paintGridHorizzontal(g2d);
		paintGridVertical(g2d);
/**
 * DISEGA I PUNTI DI CUI SI VUOLE EVIDDENZIARE ASCISSA E COORDINATA
 */
	 paintPoints(g2d);
	 paintStringhe(g2d);	
		/**
		 * Riporta le coordinate al top left
		 */
		g2d.scale(1.0, -1.0);
		g2d.translate(0, getHeight());

		/**
		 * Per permettere lo scroll nel scrollpane
		 */
		Dimension dimension = new Dimension((int) (new_width +margin_left),
				(int) (new_height +margin_bottom));
		this.setPreferredSize(dimension);
	}

	/**********************************************************************************
	 * 		FINE FUNZIONE PAINTCOMPONENT
	 ***********************************************************************************/
	
	
	/***************
	 * Disegno le etichette asse X , conoscendo già i rect delle etichette ;
	 * verifihe se vengono sovrapposte ed in base alle opzioni decido come
	 * stamparle a video ( per ora scrivo solo le etichette che non si
	 * sovrappongono).
	 * 
	 * @param e
	 ************/
	private void paintLabelX(Graphics2D g) {

		Rectangle r_prev = null;
		for (int x = 0; x < rect_x_label.size(); x++) {
			//double resto=x_axis[x]%10;
			//if(resto==0){
			Rectangle r = (Rectangle) rect_x_label.get(x);
			String str = String.format(formatNumberAxisX, x_axis_str[x]);
			// g.drawString(str, r.x, r.y);
			if (r_prev != null) {
				if (r.intersects(r_prev))
					continue;
				r_prev = r;
				g.drawString(str, r.x, r.y-r.height);
				g.drawLine(r.x,r.y,r.x,(int)y_coordinate[0]);
			} else {
				r_prev = r;
				g.drawString(str, r.x, r.y-r.height);
				g.drawLine(r.x,r.y,r.x,(int)y_coordinate[0]);
			}
			//}
		}

	}

	public void paintGridHorizzontal(Graphics2D g) {

		if (showGridHorizontal) {
			Color savecolor = g.getColor();
			g.setColor(colorGridHoriz);
			int k = y_coordinate.length ;
			int x1 = (int) x_coordinate[0];
			int x2 = (int) x_coordinate[x_coordinate.length-1];
			for (int x = 1; x < k; x++) {
				int y = (int) y_coordinate[x];

				g.drawLine(x1, y, x2, y);
			}
			g.setColor(savecolor);
		}

	}

	public void paintGridVertical(Graphics2D g) {
		if (showGridVertical) {
			Color savecolor = g.getColor();
			g.setColor(colorGridVert);
			int k = x_coordinate.length ;
			int y1 = (int) y_coordinate[0];
			int y2 = (int) y_coordinate[y_coordinate.length-1];
			for (int j = 1; j < k; j++) {
				int x = (int) x_coordinate[j];
				g.drawLine(x, y1, x, y2);
			}
			g.setColor(savecolor);
		}

	}
	/**
	 * DISEGNO DEI PUNTI CHE VOGLIO EVIDENZIARE
	 */
	private void paintPoints(Graphics2D g2){
		
		Stroke savestroke= g2.getStroke();
    		Color savecolor = g2.getColor();
		
		for (int x=0;x <punti.size();x++){
			
		Punto punto=punti.get(x);
		g2.setColor(punto.color);
		g2.setStroke(punto.stroke);
		
		double x1=ConvertXValue(punto.x);//=punto.x*x_scale_adapted;
		
		double y1=ConvertYValue(punto.y);//*y_scale_adapted;
		g2.drawLine((int)x_coordinate[0],(int)y1,(int) x1,(int)y1);
		g2.drawLine((int)x1,(int)y_coordinate[0],(int) x1,(int) y1);
		}
		g2.setColor(savecolor);
		g2.setStroke(savestroke);
		//gets rid of the copy
    //    g2.dispose();
	}
	
	private void paintStringhe(Graphics2D g2){
		for (int x=0;x <stringhe.size();x++){
		Stringa str=stringhe.get(x);
		//System.out.println("punt= "+punto.x+"  "+punto.y);
		double x1=ConvertXValue(str.x);//=punto.x*x_scale_adapted;
		
		double y1=ConvertYValue(str.y);//*y_scale_adapted;
		g2.drawString(str.testo, (float)x1, (float)y1);
		}
		
	}
	
	
	double  ConvertXValue(double x1){
		x1-=x_axis[0];
		x1*=x_scale_adapted;
		x1+=margin_left;
		return x1;
	}
	double  ConvertYValue(double y1){
		y1-=y_axis[0];
		y1*=y_scale_adapted;
		y1+=margin_bottom;
		return y1;
	}
	/***************
	 * Disegno le etichette asse Y , conoscendo già i rect delle etichette ;
	 * verifihe se vengono sovrapposte ed in base alle opzioni decido come
	 * stamparle a video ( per ora scrivo solo le etichette che non si
	 * sovrappongono).
	 * 
	 * @param e
	 ************/
	private void paintLabelY(Graphics2D g) {

		Rectangle r_prev = null;
		for (int x = 0; x < rect_y_label.size(); x++) {
			Rectangle r = (Rectangle) rect_y_label.get(x);
			String str = String.format(formatNumberAxisY, y_axis_str[x]);
			// g.drawString(str, r.x, r.y);
			if (r_prev != null) {
				if (r.intersects(r_prev))
					continue;
				r_prev = r;
				g.drawString(str, r.x, r.y);
			} else {
				r_prev = r;
				g.drawString(str, r.x, r.y);
			}
		}

	}

	/**
	 * utilizzato per aggiungere i popupmenu: menu del grafico menu delle
	 * etichette
	 */
	/**
	 * 
	 * @param component
	 *            - questo pannello Diagram (vedere costruttore )
	 * @param popup
	 *            - il menu, che in base alla posizione verrà visualizzzato
	 */
	private  void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			/**
			 * se il clic èa sinistra dell'asse Y o sotto l'asse X visualizzo il
			 * menu label altrimenti il menu diagram
			 * 
			 * @param e
			 */
			private void showMenu(MouseEvent e) {
				if(!menu_abilitated)
					return;
				/**
				 * Per verificare se il clic è fuori dall'area diagram e
				 * nell'are label utilizzo le coordinate del pannello Poichè le
				 * coordinate Y sono trasposte per usare origine in basso a
				 * destra devo per le coordinate inferiori riporatre la
				 * coordinata Y del click in basso(...)
				 */
				if ((rect_panel.height - e.getY()) <margin_bottom)
					popupX.show(e.getComponent(), e.getX(), e.getY());
				else if(e.getX() < margin_left)
					popupY.show(e.getComponent(), e.getX(), e.getY());
				else 
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				// System.out.println("e.gety="+e.getY()+"  e.getX= "+e.getX()+" marginleft  ="+margin_left+"   marginnottom ="+margin_bottom);
			}
		});
	}

	@Override
	/**
	 * Intercetto il cambiamento di stato di checkBox dei menuItem, dei popupmenu (eventuali)
	 */
	public void itemStateChanged(ItemEvent e) {
		// Checkbox adatta scalaa Frame
	if (e.getItem().equals(chckbxmnAdattascala)) {
			if (e.getStateChange() == 1) {
				mntmScala_x_2.setEnabled(false);
				mntmScala_div_2.setEnabled(false);
				mntmAsseY_2.setEnabled(false);
				mntmAsseX1_2.setEnabled(false);
				mntmAsseY2x.setEnabled(false);
				mntmAsseX2x.setEnabled(false);

				adapt_scale =ADAPTXYSCALE;
				repaint();
				validate();
				// Mi posiziona la finestra scollable con l'origine visibile
				if (parent instanceof JViewport) {
					((JViewport) parent).setViewPosition(new Point(0,
							rect_panel.height));
				}
			} else {
				mntmScala_x_2.setEnabled(true);
				mntmScala_div_2.setEnabled(true);

				mntmAsseY_2.setEnabled(true);
				mntmAsseX1_2.setEnabled(true);
				mntmAsseY2x.setEnabled(true);
				mntmAsseX2x.setEnabled(true);

				adapt_scale = ADAPTSCALEBLOCKED;
				repaint();
				validate();
				// Mi posiziona la finestra scollable con l'origine visibile
				if (parent instanceof JViewport) {
					((JViewport) parent).setViewPosition(new Point(0,
							rect_view.height - margin_bottom));
				}
			}
		}else if(e.getItem().equals(chckbxmntmGrigliaOrizz)){
			if(e.getStateChange()==ItemEvent.SELECTED)
				this.showGridHorizontal=true;
			else
				this.showGridHorizontal=false;
			repaint();
		}else if(e.getItem().equals(chckbxmntmGridVert)){
			if(e.getStateChange()==ItemEvent.SELECTED)
			showGridVertical=true;
			else
			showGridVertical=false;
				repaint();	
		}

	}
	/**
	 * 
	 */
	public void abilitaMenu(){
		menu_abilitated=true;
		
	}
	/**
	 * per consentire l'utilizzo senza menu
	 */
public void disabilitaMenu(){
	menu_abilitated=false;
		
		
	}

	/**
	 *   Intercetto gli evnti (non check box..) ACTION PERFORMED
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();

		if (command.equals("Scala x 2")) {
			x_scale *= 2;
			y_scale *= 2;
			this.repaint();
			this.validate();
		} else if (command.equals("Scala x 1/2")) {
			x_scale /= 2;
			y_scale /= 2;
			this.repaint();
		} else if (command.equals("Asse X 2x")) {
			x_scale *= 2;
			this.repaint();
		} else if (command.equals("Asse X 1/2")) {
			x_scale /= 2;
			this.repaint();
		} else if (command.equals("Asse y 1/2")) {
			y_scale /= 2;
			this.repaint();
		} else if (command.equals("Asse Y 2x")) {
			y_scale *= 2;
			this.repaint();
		} else if (command.equals("exp10")) {
			Geometry.Exp10(x_axis_str);
			//this.mntmLog.setEnabled(true);
			//this.mntm10E.setEnabled(false);
			this.repaint();
		} else if (command.equals("log10")) {
			Geometry.LogTransformation(x_axis_str);
			//this.mntmLog.setEnabled(false);
			//this.mntm10E.setEnabled(true);
			this.repaint();
		} else if (command.equals("exp10Y")) {
			Geometry.Exp10(y_axis_str);
			this.repaint();
		} else if (command.equals("log10Y")) {
			Geometry.LogTransformation(y_axis_str);
			this.repaint();
		}else if (command.equals("resetY")) {
			System.arraycopy(y_axis, 0, y_axis_str,0, y_axis.length);
			this.repaint();
		} else if (command.equals("resetX")) {
			System.arraycopy(x_axis, 0, x_axis_str,0, x_axis.length);
			this.repaint();
		}

	}

	

	public double[] getX_axis() {
		return x_axis;
	}
/**
 * Imposta i valori dell'asse x; intanto inizzializza il vettore per creare le stringhe
 * che è separato per permettere operazioni sui dati senza modificarli
 * @param x_axis
 */
	public void setX_axis(double[] x_axis) {
		this.x_axis = x_axis;
		x_axis_str =new double[x_axis.length];
		System.arraycopy(x_axis, 0, x_axis_str,0, x_axis.length);
		
		
		this.repaint();
	}

	public double[] getY_axis() {
		return y_axis;
	}
/**
 *  * Imposta i valori dell'asse Y; intanto inizzializza il vettore per creare le stringhe
 * che è separato per permettere operazioni(per la visualizzazione) sui dati senza modificarli
 * 
 * @param y_axis
 */
	public void setY_axis(double[] y_axis) {
		this.y_axis = y_axis;
		y_axis_str =new double[y_axis.length];
		System.arraycopy(y_axis, 0, y_axis_str,0, y_axis.length);
		this.repaint();
	}

	public double getX_scale() {
		return x_scale_adapted;
	}

	public void setX_scale(double x_scale) {
		this.x_scale_adapted = x_scale;
		this.repaint();
	}

	public double getY_scale() {
		return y_scale;
	}

	public void setY_scale(double y_scale) {
		this.y_scale = y_scale;
		this.repaint();
	}

	public double getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = margin;
	}

	/**
	 * Imposta il  formato di visualizzazione delle etichette degli assi
	 * @param formatNumberAxis
	 */
		public void setFormatNumberAxis(String formatNumberAxis) {
			String oldf = this.formatNumberAxis;
			try {
				String.format(formatNumberAxis, 0.15);// Verifica se nuovo formato è
														// corretto
				this.formatNumberAxis = formatNumberAxis;
				this.formatNumberAxisX = formatNumberAxis;
				this.formatNumberAxisY = formatNumberAxis;
			} catch (Exception e) {
				this.formatNumberAxis = oldf;
			}

		}
	
	public void setFormatNumberAxisX(String formatNumberAxisX) {
		String oldf = this.formatNumberAxisX;
		try {
			String.format(formatNumberAxisX, 0.15);// Verifica se nuovo formato è
										// corretto
			this.formatNumberAxisX = formatNumberAxisX;
		} catch (Exception e) {
			this.formatNumberAxisX = oldf;
		}
			
	}
	
	public void setFormatNumberAxiY(String formatNumberAxiY) {
		String oldf = this.formatNumberAxisY;
		try {
			String.format(formatNumberAxiY, 0.15);// Verifica se nuovo formato è
										// corretto
			this.formatNumberAxisY = formatNumberAxiY;
		} catch (Exception e) {
			this.formatNumberAxisY = oldf;
		}
	}
	
	
	public String getFormatNumberAxisY() {
		return formatNumberAxisY;
	}
	public String getFormatNumberAxisX() {
		return formatNumberAxisX;
	}
	public String getFormatNumberAxis() {
		return formatNumberAxis;
	}

	public int getAdapt_scale() {
		return adapt_scale;
	}

	public void setAdapt_scale(int adapt_scale) {
		this.adapt_scale = adapt_scale;
	}

	/**
	 * 
	 * @param functions
	 */
	public void setFunctions(double[] functions) {
		this.functions = functions;
	}


	/**
	 * Classe per disegnare una coordinata con segmenti sugli assi ortogonali
	 * usare 
	 * addPunto(x,y)
	 * addPunto(new Punto(...))
	 * clearPunti();
	 * @author Anto
	 *
	 */
	public class Punto{
		public double x;
		public double y;
		public Color color;
		public Stroke stroke;
		
		public Punto(double x, double y, Color color, Stroke stroke) {
			super();
			this.x = x;
			this.y = y;
			this.color = color;
			this.stroke = stroke;
		}

		public Punto(double x, double y, Color color) {
			super();
			this.x = x;
			this.y = y;
			this.color = color;
		}

		public  Punto(){
		color=Color.green;
		 stroke = new BasicStroke(0.5f);
		 stroke=new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
		
		};
		
		
	}
	/**
	 * Stringa di testo, da aggiungere alla lista di stringhe da visulizzare
	 * 	contiene il testo e le coordinate
	 * per eliminarle tutte chiamare clearStringhe()
	 * Usare addStringa(Tring,x,y)
	 * clearStringhe();
	 * @author Anto
	 *
	 */
	public class Stringa{
		String testo;
		public double x;
		public double y;
		
	}
	
}
