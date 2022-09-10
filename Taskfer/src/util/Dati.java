package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Dati {
	static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	Calendar c = Calendar.getInstance();
	//
	public String StringDate() {
		
		String dataF = format.format(c.getTime());
		
		return dataF;
	}
	public static String convertString(Date date) {
		String dat = format.format(date);
		return dat;
		
		
	}
	//
	public Date DateDate(String arg){
		Date date = null;
		try {
			date = format.parse(arg);
		} catch (ParseException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		}
		return date;
	}
	public String statusDate(Date data, Date dataNow) {
		if(dataNow.before(data) == false) {
			return "ativo";
		}
		return "atrasado";
		
		
	}
}
