package com.antoiovi.util.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AiCalendario {

/**
 * Stringhe da utilizzare per i nomi dei giorni
 */
public static final String day_name[]={"Domenica","Lunedi","Martedi","Mercoledi","Giovedi","Venerdi","Sabato"};
/**
 * Stringhe da utilizzare per i nomi dei mesi
 */
public static final String month_name[]={"Gennaio","Febbraio","Marzo","Aprile","Maggio","Giugno",
    "Luglio","Agosto","Settembre","Ottobre","Novembre","Dicembre"};



/**
 * Crea un periodo  di sette giorni, Array di AIDAte[7]	
 * @param day
 * @return
 */
public AiDate[]  createAWeek(Date day){
	AiDate settimana[]=new AiDate[7];
    Calendar cal=new GregorianCalendar();
    cal.setTime(day);
    for(int x=0;x<7;x++){
       Date d=cal.getTime();
    	AiDate giorno=new AiDate(d);
    	settimana[x]=giorno;
        cal.add(Calendar.DATE,1);
    }
    return settimana;
}

/**
 * cREA UN PERIODO: ARRAY Di AiDate di ndays
 * @param startday primo giorno
 * @param n_days   numero di giorni	
 * @return
 */
public static final AiDate[]  createAPeriod(Date startday,int n_days){
AiDate settimana[]=new AiDate[n_days];
Calendar cal=new GregorianCalendar();
cal.setTime(startday);
for(int x=0;x<n_days;x++){
   Date d=cal.getTime();
	AiDate giorno=new AiDate(d);
	settimana[x]=giorno;
    cal.add(Calendar.DATE,1);
}
return settimana;
}
/**
 * vERFICA SEL SONOI LO STESSO GIORNO :
 * @param date1
 * @param date2
 * @return
 */
public static final boolean sameDate(Date date1,Date date2){
	SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
	return fmt.format(date1).equals(fmt.format(date2));
}

}
