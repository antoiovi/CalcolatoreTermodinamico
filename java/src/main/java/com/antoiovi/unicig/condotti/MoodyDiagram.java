/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antoiovi.unicig.condotti;

/**
 * Calcola il fattore di attrito  in base al diagramma di Moody;
 * <br>Utilizzo
 * if(Nre<3000){<br>
               FattAttrLiscio=64/Nre;<br>
               FattAttrRuvido=FattAttrLiscio;//verificare per nre<3000<br>
           }else{<br>
                double scabrRel=rugosita/Dh;<br>
                <br>
                it.iovino.impter.MoodyDiagram moodydiagr=new MoodyDiagram(Nre,scabrRel);<br>
                FattAttrRuvido=moodydiagr.zbrent();<br>
                moodydiagr.setEsd(0);// fattore attrito liscio con scabrezza=0<br>
                FattAttrLiscio=moodydiagr.zbrent();<br>
           }
 * @author antoiovi
 */
public class MoodyDiagram {
    protected double Re;
    protected double scabr_rel;

/**
 * Costruttore 
 * @param Re Numero di Raynold
 * @param esd Scabreza relativa
 */
    public MoodyDiagram(double Re, double esd) {
        this.Re = Re;
        this.scabr_rel = esd;
    }
/**
 * f  la funzione omogenea da calcolare:
 * 		.....nel caso la funzione
 * @param xi
 * @return
 */
    protected double f(double xi){
    double s=Math.sqrt(xi);
    if(s!=0.0 && Re!=0.0)return (1.0/s+2*Math.log10(2.51/(Re*s)+scabr_rel/3.71));
        else
         return this.machEps();
}
    /**
     * Brents' method per il calcolo della funzione omogenea f(xi)
     * @return Il fattore di attrito 
     */
    public double zbrent(){
        final int ITERMAX=100;
        double EPS=(double)this.machEps();
        double a=0;
        double b=1;
        double c=1;
        double fa=f(a);
        double fb=f(b);
        double fc=f(c);
        double toll=EPS;
      /**
       * input a, b, and (a pointer to) a function for f
		calculate f(a)
		calculate f(b)
		
		c := a
		set mflag
       */
      //if f(a) f(b) >= 0 then exit function because the root is not bracketed.
        if((fa>0.0 && fb>0.0) || (fa<0.0 && fb<0.0))
        {
            return 0.0;// errore
        }
        for(int iter=1;iter<ITERMAX;iter++)
        {
            double d=1;
            double e=1;
            if((fb>0.0 && fc>0.0)|| (fb<0.0 && fc<0.0))
           {
                c=a;
                fc=fa;
                e=d=b-a;
            }
            if(Math.abs(fc)<Math.abs(fc))
            {
                a=b;
                b=c;
                c=a;
                fa=fb;
                fb=fc;
                fc=fa;
            }
            double tol1=(2*EPS*Math.abs(b)+0.5*toll);
            double xm=(0.5*(c-b));
            // CONDIZIONE DI FINE CICLO
            if(Math.abs(xm)<=tol1 || fb==0) return b;
            if(Math.abs(e)>=tol1 && Math.abs(fa)>Math.abs(fb))
            {
                double s=fb/fa;
                double p;
                double q;
                if(a==c){
                    p=2*xm*s;
                    q=1d-s;
                    
                }else {
                    q=fa/fc;
                    double r=fb/fc;
                    double r_m=r-1;
                    p=s*(2.0*xm*q*(q-r)-(b-a)*r_m);
                    q=(q-1.0)*r_m*(s-1.0);
                }
                if(p>0.0) q=-q;
                p=Math.abs(p);
                double min1=3.0*xm*q-f(Math.abs(tol1*q));
                double min2=f(Math.abs(e*q));
                if(2*p<(min1<min2 ? min1:min2)){
                    e=d;
                    d=p/q;
                }else{
                    d=xm;
                    e=d;
                }                
            }else {
                d=xm;
                e=d;
            }
        a=b;
        fa=fb;
        if( Math.abs(d) >tol1)
                b+=d;
        else b+=sign(toll,xm);
        
        fb=f(b);
         }
        return 0.0;
        
    }
        
    
    
private double sign(double a, double b){
    return (b>=0 ? Math.abs(a): -Math.abs(a));
}
    /**
     * Calcola l'errore di macchina 
     * @return errore di macchina (minimo double valido)
     */
    private float machEps(){    
     float machEps = 1.0f;
do {
           machEps /= 2.0f;
        }
        while ((float)(1.0 + (machEps/2.0)) != 1.0);    
return machEps;
}
    public double getRe() {
        return Re;
    }

    public void setRe(double Re) {
        this.Re = Re;
    }
    public double getEsd() {
        return scabr_rel;
    }

    public void setEsd(double esd) {
        this.scabr_rel = esd;
    }

}
