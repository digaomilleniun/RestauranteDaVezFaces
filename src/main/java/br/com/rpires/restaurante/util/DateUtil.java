/**
 * 
 */
package br.com.rpires.restaurante.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import br.com.rpires.restaurante.exception.SystemException;

/**
 * @author digao
 *
 */
public class DateUtil {
	
	static DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	public static Date format(String str) throws ParseException {
		Date date = formatter.parse(str);
		return date;
	}
	
	public static String format(Date data) throws ParseException {
		String date = formatter.format(data);
		return date;
	}

	public static Date atStartOfDay(Date date) {
	    LocalDateTime localDateTime = dateToLocalDateTime(date);
	    LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
	    return localDateTimeToDate(startOfDay);
	}

	public static Date atEndOfDay(Date date) {
	    LocalDateTime localDateTime = dateToLocalDateTime(date);
	    LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
	    return localDateTimeToDate(endOfDay);
	}
	
	private static LocalDateTime dateToLocalDateTime(Date date) {
	    return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	private static Date localDateTimeToDate(LocalDateTime localDateTime) {
	    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public static Date getPrimeiroDiaSemana(Date data) throws SystemException {
		try {
			
			final long millisPorDia = 1000 * 60 * 60 * 24;
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(data);
	        int diaDaSemana = cal.get(Calendar.DAY_OF_WEEK);
	        long time = cal.getTimeInMillis();
	        //Calculo do primeiro dia da semana
	        long primeiroDiaDaSemana = time - ((diaDaSemana - Calendar.SUNDAY) * millisPorDia);
	        Calendar primeiroDia = Calendar.getInstance();
	        primeiroDia.setTimeInMillis(primeiroDiaDaSemana);
	        eraseTime(primeiroDia);
	        
	        String dataStr = primeiroDia.get(Calendar.DAY_OF_MONTH) + "/" + (primeiroDia.get(Calendar.MONTH)+1) + "/" + (primeiroDia.get(Calendar.YEAR));
	        
	        System.out.println("Primeiro dia da semana: " + dataStr);
	       
	        Date newData = DateUtil.format(dataStr);
			
	        return newData;
		} catch (ParseException e) {
			throw new SystemException(e);
		}
	}
	
	public static Date getUltimoDiaSemana(Date data) throws SystemException {
		try {
			
			final long millisPorDia = 1000 * 60 * 60 * 24;
	        Calendar cal = Calendar.getInstance();      
	        cal.setTime(data);
	        int diaDaSemana = cal.get(Calendar.DAY_OF_WEEK);
	        long time = cal.getTimeInMillis();
	        Calendar primeiroDia = Calendar.getInstance();
	        
			 //Calculo do último dia da semana
	        long ultimoDiaDaSemana = time + ((Calendar.SATURDAY - diaDaSemana) * millisPorDia);
	        Calendar ultimoDia = Calendar.getInstance();
	        ultimoDia.setTimeInMillis(ultimoDiaDaSemana);
	        eraseTime(ultimoDia);
	        
	        String dataStr = ultimoDia.get(Calendar.DAY_OF_MONTH) + "/" + (ultimoDia.get(Calendar.MONTH)+1) + "/" + (primeiroDia.get(Calendar.YEAR));
	        
	        System.out.println("Último dia da semana: " +  dataStr);
	        
	        Date newData = DateUtil.format(dataStr);
	        
	        return newData;
        
		} catch (ParseException e) {
			throw new SystemException(e);
		}
	}
	
	private static void eraseTime(Calendar cal) {
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);        
    }
}
