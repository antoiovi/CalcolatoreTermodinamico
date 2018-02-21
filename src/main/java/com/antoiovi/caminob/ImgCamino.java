package com.antoiovi.caminob;

import it.iovino.fluidi.Fluido;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.antoiovi.caldaie.Caldaia;
import com.antoiovi.unicig.camini.CaminoB_1;
import com.antoiovi.unicig.condotti.Condotto;
import com.antoiovi.unicig.condotti.VelocitaFluido;

import java.awt.Color;

public class ImgCamino extends JPanel implements ActionListener {
int piano=1;
int npiani=1;
int sp=5;// spessore parete
int inter=20;//interspazio tar condotto esteerno e inerno verticale
private int h_1=300;
private int h_0=10;
private int h_u=50;
private int L1=100;// larghezza canale esterno
private int L2=30;// larghezza canaale interno
private int d1=30;//diam int canale d a fumo 
private int c=150;// distanza orizzontale da tubo esterno a canale da fumo
private int f=100;// h da base canne a innesto tubo esterno f+d2 deve essere minore di h
private int g=30;// discesa canale da fumo
public  Generatore generatore;
public boolean pmax=true;
Condotto condotto;
Condotto canale;
Pressioni pressioni;
public void setCanale(Condotto canale) {
	this.canale = canale;
}
VelocitaFluido vf_co;
public Condotto getCondotto() {
	return condotto;
}
public void setCondotto(Condotto condotto) {
	this.condotto = condotto;
}
Color alarmcolor=Color.black;
static int countanimation=0;
/**
 * 
 * |		|	|
 * |		|	|______________
 * |		|________________  |
 * |		    |          |  |
 * |            |________  |  |   
 * |            |   c   g| |  | 
 * |        |   |
 * |        |   |f
 * |    |   |   |
 * x        x1  x2         x3 x4
 * @return
 */

public int getPiano() {
return piano;
}
public void setPiano(int piano) {
this.piano = piano;
String.valueOf(piano);
String.valueOf(piano-1);
}
/**
 * DATI DA VISUALIZZARE
 */

static double Potenza=10.0;
private static double Rendimento=95.0;
private Caldaia caldaia;
private Timer timer;

private int speed=300;
private Color alarmcolor_pca=Color.black;
private boolean pari_animation;
private CaminoB_1 camino;
private Color alarmcolor_aria;
private Color alarmcolor_speed_can=Color.black;
private Color alarmcolor_speed_cond=Color.black;

private Font font;
private Font rotatedFont;
private FontMetrics fontmetrics;
	/**
	 *  
	 * CAMINO SEMLICE
	 */
	public ImgCamino() {
		setBackground(Color.WHITE);
		String.valueOf(piano);
		String.valueOf(piano-1);
		generatore=new Generatore();
		timer = new Timer(speed, this);
		int pause=100;
		timer.setInitialDelay(pause);
		timer.start(); 
		
	}
	/**
	 * 		paintComponent
	 */
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
				int h=h_0;// altezza condotto
				int x_base=20;
				int y_base=30;
			 // Condotto 0
				paintCond0(g2d,x_base,y_base,h);
				
				y_base+=h;
				h=h_1;
				for(int x=0;x<npiani;x++){
					paintCond(g2d,x_base,y_base,h,x+1);
					y_base+=h;
				}
				
			   	h=h_u;
				paintCondU(g2d,x_base,y_base,h);
				paintTabelPressioni(g2d,x_base+L1+L2+50,y_base+120);
				paintPresaaria(g2d, 10, 10,0);
			//	paintTabelPressioni(g2d,x_base,y_base+h_u);
	}
	/**
	 * CONDOTTO ZERO
	 * @param g2d
	 * @param x_base
	 * @param y_base
	 * @param h
	 */
	void paintCond0(Graphics2D g2d,int x_base,int y_base,int h){
		int x=x_base;
		int x1=x_base+L1+L2;
		 
		int y=y_base+f;
		 
		int y4=y+h;
		 
	//	g2d.drawLine(x, y, x,y4);
		 
	//	g2d.drawLine(x1,y, x1,y4);
		g2d.drawLine(x,y, x1,y);
			
	}
	/**
	 * CONDOTTO ULTIMO
	 * @param g2d
	 * @param x_base
	 * @param y_base
	 * @param h
	 */
	void paintCondU(Graphics2D g2d,int x_base,int y_base,int h){
		int x=x_base;
		int x1=x_base+L1+L2;
		 
		int y=y_base;
		 
		int y4=y+h;
		 
		g2d.drawLine(x, y, x,y4);
		 
		g2d.drawLine(x1,y, x1,y4);
		/**
		 * comignolo
		 */
		int f=3;
		 for(int k=0;k<4;k++){
			 g2d.drawLine(x ,y4+f, x1,y4+f);
			 f+=3;
		 }
		 if(camino!=null){
			 String s=String.format("Perdi comignolo %1.3f pa", camino.dp_com);
		// g2d.drawString(s, x1, y4+f+2);
		 }
		
		
		
		
		}
	/**
	 * CONDOTTO
	 * @param g2d
	 * @param x_base
	 * @param y_base
	 * @param h
	 * @param piano
	 */
	void paintCond(Graphics2D g2d,int x_base,int y_base,int h,int piano){
		//int d=(int)(d2-d1)/2;
		 
		int x=x_base;
		int x1=x_base+L1;
		int x2=x1+L2;
		int x3=x2+c;
		int x4=x3+d1;
		//int x5=x4+d;
		 
		int y=y_base;
		int x_right=x2;
		int y1=y+f-d1-d1;
		int y2=y+f;
		int y3=y2+d1;
		int y4=y+h;
		int y_top=y4;
		int y0=y2-g;
		g2d.drawLine(x, y+f-h_0, x,y4);
		//g2d.drawLine(x1,y+f, x1,y1);
		//g2d.drawLine(x1,y1, x1,y4);
		
		g2d.drawLine(x2,y+f-h_0, x2,y2);
 	g2d.drawLine(x2,y3, x2,y4);
		//g2d.drawString("Condotti primario e secondario "+piano, x2, y3+10);
		
		//canale da fumo
		g2d.drawLine(x2, y2, x3,y2);//orizzontale
		g2d.drawLine(x2, y3, x4,y3);
		/***
		 * DATI CANALE
		 */
		if(canale!=null){
			int yt=y3;
			String s=String.format("Tu= %1.1f  Tm= %1.1f  Ti= %1.1f", canale.Tu(),canale.Tm(),canale.Ti());
			g2d.drawString(s, x2+5, yt-10);
		// s=String.format("Ps(1)= %1.1f  d_P= %1.1f  ", canale.Ps(1),canale.d_P());
	//	g2d.drawString(s, x2+5, yt-25);
			int y_y=y0;
			g2d.drawString( String.format("d_P() %1.2f Ps()= %1.3f ",camino.dp_can, camino.ps_ca), x2, y_y);
			y_y-=15;
			g2d.setColor(alarmcolor_speed_can);
			g2d.drawString( String.format("Velocita= %1.3f",canale.Wm()), x2, y_y);
			
			g2d.setColor(alarmcolor_pca);
			y_y-=15;
			g2d.drawString( String.format("Peff base= %1.3f",camino.peff_ca_base), x2, y_y);
			
			
			g2d.setColor(Color.black);
		}
		
		g2d.drawLine(x3,y2, x3,y0);//verticale
		g2d.drawLine(x4,y3, x4,y0);
		
		
		//g2d.drawLine(x1,y1, x2,y1-d1);//diagonale
		int yt=y_top;
		/***
		 * DATI CONDOTTO
		 */
		if(condotto!=null && camino!=null){
			 
			g2d.drawString("Tu "+String.format("%1.1f K", condotto.Tu()), x+5, yt);
			yt-=20;
			g2d.drawString("Tm "+String.format("%1.1f K",condotto.Tm()), x+5, yt);
			yt-=20;
			g2d.drawString("Portata "+String.format("%1.4f k/s",condotto.M1()), x+5, yt);
			yt-=15;
			g2d.setColor(alarmcolor_speed_cond);
			g2d.drawString("Velocità "+String.format("%1.2f m/s",condotto.Wm()), x+5, yt);
			g2d.setColor(Color.black);
			yt-=15;
			g2d.drawString("N Raynold "+String.format("%1.3f",vf_co.getNre()), x+5, yt);
			yt-=15;
			g2d.drawString("Densità "+String.format("%1.3f kg/m3",vf_co.getMassaVolumicaMedia()), x+5, yt);
			yt-=20;
			g2d.drawString("Ti "+String.format("%1.1f k",condotto.Ti()), x+5, yt);
			
			yt=y_top-40;
			int xx=x_right;
			
			g2d.drawString(String.format("Area %1.3f [m2] Diam idraulico %1.2f [m]",condotto.Area(),condotto.Dh()), xx+5, yt);
			yt-=20;
			g2d.setColor(alarmcolor);
			g2d.drawString("Peff(0) "+String.format("%1.3f",camino.peff_cond), xx+5, yt);
			g2d.setColor(Color.black);
			yt-=20;
			g2d.drawString("d_P() "+String.format("%1.2f",camino.dp_con), xx+5, yt);
			yt-=20;
			g2d.drawString("Ps(1) "+String.format("%1.3f",camino.ps_cond), xx+5, yt);
			yt-=20;
			g2d.drawString("Densita aria) "+String.format("%1.3f",condotto.getMassvolumaria()), xx+5, yt);
			yt-=20;
			
		}
		
		 
		paintCaldaia(g2d,x3,y0,d1,piano);
		
		
	}
	void paintPresaaria(Graphics2D g2d,int x_base,int y_base,int piano){
		Color color=g2d.getBackground();
		g2d.setColor(Color.blue);
		g2d.drawRect(x_base, y_base, 20, 20);
		if(camino!=null){
			g2d.setColor(alarmcolor_aria);
		String s=String.format("Portata d'aria %1.4f kg/s",camino.maria);
		
		g2d.drawString(s, x_base+25, y_base);
		}
		
		
		g2d.setColor(color);
	}
	public void setVf_co(VelocitaFluido vf_co) {
		this.vf_co = vf_co;
	}
	/**
	 * 
	 * @param g2d
	 * @param x_base punto a sinistra tratto verticale canale da fumo
	 * @param y_base base caldaia
	 * @param l2 larghezza canale fumo
	 */
	public   void paintCaldaia( Graphics2D g2d,int x_base,int y_base,int l2,int piano){
		Font font=g2d.getFont();
		FontMetrics metrics = g2d.getFontMetrics(font);
		// get the height of a line of text in this
		// font and render context
		int h_char = metrics.getHeight();
		int h_c=40;
		int a=20;
		int l_c=a+l2+a;// larghezza caldaia
		 int x_l=x_base-a;//x left
		// int x_r=x_base+l2+a;
		 int x_r=x_l+l_c;
		 int y_t=y_base;//y top
		 int y_b=y_base-h_c;//y bottom
		 g2d.drawLine(x_base , y_t ,x_base-10 ,y_t-5);
		 g2d.drawLine(x_base+l2 , y_t ,x_base+l2+10 ,y_t-5);
		 g2d.drawLine(x_base-10 , y_t-5 ,x_base+l2+10 ,y_t-5);
		 
		 
		 y_t-=10;
		 y_b-=10;
		g2d.drawLine(x_l , y_t ,x_r ,y_t);//tratto sup orizzontale left right
		g2d.drawLine( x_l, y_t ,x_l ,y_b );// verticale sx top bottom
		g2d.drawLine( x_l, y_b ,x_r ,y_b );// orizzontale inf  left right
		g2d.drawLine(  x_r,y_b ,x_r ,y_t);// verticale dx bottom top
		//g2d.drawString("Caldaia "+piano, x_r+5, y_b+5);
		int y_tt=y_t;
		if(caldaia!=null) {
			if(pmax){
		String s=String.format("Potenza max %1.1f  Rendimento %3.0f ",caldaia.getPmax(),caldaia.getRend_Pmax()*100);
		g2d.drawString(s, x_r+5, y_tt);
		y_tt-=10;
		  s=String.format("CO2 %1.1f  Eccesso d'aria %3.1f ",caldaia.getCO2percentPmax() ,caldaia.getEccariaPmax());
		g2d.drawString(s ,x_r+5, y_tt );
		y_tt-=10;
		s=String.format("Portata fumi %1.3f [kg/s]  ",caldaia.getPortatfumiPmax() );
		g2d.drawString(s ,x_r+5, y_tt );
		 y_tt-=10;
		s=String.format("Temperatura fumi %1.0f [K] ", caldaia.getTempFumiPmax());
		g2d.drawString(s ,x_r+5, y_tt );
		 y_tt-=10;
	 /*	Fluido fumo=caldaia.getFumoPmax();
	 	s= "Composizione "+fumo.toString();
	 	g2d.drawString(s ,0, y_tt -20);*/
		
			}else{// Potenza minima
				String s=String.format("Potenza min %1.1f  Rendimento %3.0f ",caldaia.getPmin(),caldaia.getRend_Pmin()*100);
				g2d.drawString(s, x_r+5, y_tt);
				y_tt-=10;
				  s=String.format("CO2 %1.1f  Eccesso d'aria %3.1f ",caldaia.getCO2percentPmin() ,caldaia.getEccariaPmin());
				g2d.drawString(s ,x_r+5, y_tt );
				y_tt-=10;
				s=String.format("Portata fumi %1.3f [kg/s]  ",caldaia.getPortatfumiPmin() );
				g2d.drawString(s ,x_r+5, y_tt );
				 y_tt-=10;
				s=String.format("Temperatura fumi %1.0f [K] ", caldaia.getTempFumiPmin());
				g2d.drawString(s ,x_r+5, y_tt );
				 y_tt-=10;
			}
		
		}
		
		/**
		 * disegna fiammella
		 */
		int k=15;
		Color c=pari_animation?Color.red:Color.yellow;
		g2d.setColor(c);
		for(int x=0;x<10;x++){
				g2d.drawLine(  x_l+k,y_b+4 ,x_l+k ,y_b+10);
			k+=2;
		}
		k=15+1;
		Color c2=pari_animation?Color.YELLOW:Color.red;
		g2d.setColor(c2);
		for(int x=0;x<10;x++){
				g2d.drawLine(  x_l+k,y_b+4 ,x_l+k ,y_b+10);
			k+=2;
		}
		g2d.setColor(Color.black);
	}
	/**
	 * STAMPA TABELLA PRESSIONI
	 * @param g2d
	 * @param x_base
	 * @param y_base
	 */
	
	public   void paintTabelPressioni( Graphics2D g2d,int x_base,int y_base){
		  String title=String.format("TABELLA RIEPIOGO CALCOLO PRESSIONI     .");
	int width=fontmetrics.stringWidth(title);
		// line space
	//	int ls=fontmetrics.getDescent()+2*fontmetrics.getAscent();
	//	ls*=-1;
		int ls=20;
		int height=0;
		
		int yt=y_base;
		g2d.drawString(title, x_base, yt);//titolo
		yt-=ls;
		height+=ls;
		
		g2d.drawString(String.format("Perdite comignolo               %f [pa] ", camino.dp_com), x_base, yt);
		yt-=ls;
		height+=ls;
		/**è
		 * Condotto
		 */
		g2d.drawString(String.format("d_P() perdite carico condotto    %1.3f",camino.dp_con), x_base, yt);
		yt-=ls;
		height+=ls;
		g2d.drawString(String.format("Ps(1) pressione statica condotto %1.3f",camino.ps_cond), x_base, yt);
		yt-=ls;
		height+=ls;
		/**
		 * Canale
		 */
		g2d.drawString( String.format("d_P() perdite carico canale    %1.3f ",camino.dp_can ), x_base, yt);
		yt-=ls;
		height+=ls;
		g2d.drawString( String.format(" Ps() pressione statica canale %1.3f " , camino.ps_ca), x_base, yt);
		yt-=ls;
		height+=ls;
		g2d.drawString( String.format("Peff base canale               %1.3f",camino.peff_ca_base),  x_base, yt);
		height+=ls;
		yt-=ls;
		g2d.drawRect(x_base, yt, width,height);
		
	}
	 
	public void setPmax(boolean pmax) {
		this.pmax = pmax;
	}
	public boolean isPmax() {
		return pmax;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		pari_animation = (countanimation%2 ==0)?true:false;
	if(camino!=null){
		
	
		if(camino.peff_cond<0.5){
			alarmcolor=pari_animation?Color.red:Color.black;
		}else{
			alarmcolor=Color.black;}
			if(canale!=null){
				if(camino.peff_ca_base<0.5){
					alarmcolor_pca=pari_animation?Color.red:Color.black;
				}else{
					alarmcolor_pca=Color.black;}
			}
			if(camino.maria<=0){
				alarmcolor_aria=pari_animation?Color.white:Color.blue;
			}else{
				alarmcolor_aria=Color.blue;
			}
			
			 
				alarmcolor_speed_can=camino.isVel_can_ok()?Color.black:(pari_animation?Color.red:Color.black);
				alarmcolor_speed_cond=camino.isVel_con_ok()?Color.black:(pari_animation?Color.red:Color.black);
			 
	}
		countanimation++;
		countanimation=countanimation >100 ? 1 : countanimation;
	 repaint();
	}
	
	public void setCamino(CaminoB_1 camino) {
		condotto=camino.condotto;
		canale=camino.canale;
		caldaia=camino.caldaia;
		this.camino=camino;
		// TODO Auto-generated method stub
		
	}
	
}
