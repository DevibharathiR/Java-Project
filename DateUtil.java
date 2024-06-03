package Project;

import java.text.ParseException;
/**
 * This class contains static method to handle dates
 */
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static final String[] MONTHS= {"January","February","March","April","May","June","July","August","September","October","November","December"};
	/**
	 * This method converts string-date into Date object
	 * @param dateAsString string formatted date (ex.11/03/2024 : DD/MM/YYYY).
	 * @return returns a Date object for input date-string
	 */
	public static Date  stringToDate(String dateAsString) {
		
		
			try {
				SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
				
				return df.parse(dateAsString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return null;
		
		
	}
	/**
	 * This method converts Date into String .
	 * @param date Date  object to be converted to String
	 * @returnString date in DD/MM/YYYY format.
	 */
	public static String  dateToString(Date date) {
	
		SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
		
		
		return df.format(date);
	
	}
	/**
	 * The method returns Year and Month from given Date in Year,Month -No format:
	 * ex.2023,01:2023,03 so on.....
	 * @param date year and month will be extracted for this date
	 * @return return year and month for input.
	 */
	public static String getYearAndMonth(Date date ) {
		
        SimpleDateFormat df=new SimpleDateFormat("yyyy,MM");
		
		
		return df.format(date);
	
	}
	
/**
 * Returns Year for input date
 * @param date
 * @return
 */
    public static Integer getYear(Date date ) {
		
        SimpleDateFormat df=new SimpleDateFormat("yyyy");
		
		
		return new Integer( df.format(date));
	
	}
    /**
     * This method returns Month Name for given month number.
     * 01:Jan,02:Feb.....,12:Dec
     * @param monthNo month number between 1 to 12
     * @return returns month name for input number
     */
	public static String getMonthName(Integer monthNo) {
		return MONTHS[monthNo-1];
	}
	
		
		
	


}
