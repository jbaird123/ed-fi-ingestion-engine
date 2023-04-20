package routines;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateHandling {

	private static final String DEFAULT_DATE_FORMAT = "EEE MMM dd HH:mm:ss z yyyy";
	
	private static String getDefaultDateFormat (){
		return DEFAULT_DATE_FORMAT;
	}
	
	public static boolean isDateValid(String date, String dateFormat) {
	    ParsePosition pos = new ParsePosition(0);
	    DateFormat df = new SimpleDateFormat(dateFormat);
	    df.setLenient(false);
	    df.parse(date, pos);

	    return pos.getIndex() == date.length();
	  }
	
	private static Date parseDate(String date, String dateFormat) {
		ParsePosition pos = new ParsePosition(0);
        DateFormat df = new SimpleDateFormat(dateFormat);
        df.setLenient(false);
        Date newDate = df.parse(date, pos);
	    if (pos.getIndex() == date.length()) {
	    	return newDate;
	    } else {
	    	return null;
	    }

	}
    public static String getDateFormat (String dateString){
    	boolean isDate = false;
    	String newDateString = getCleanDateFormat(dateString);
    	
    	String dateFormat = new String();
    	
    	
    	dateFormat = "EEE MMM dd HH:mm:ss z yyyy";
    	isDate = isDateValid(newDateString, dateFormat);
    	if (isDate) { return dateFormat; }
    	
    	dateFormat = "dd/MM/yyyy HH:mm";
    	isDate = isDateValid(newDateString, dateFormat);
    	if (isDate) { return dateFormat; }
    	
    	return "UNKNOWN";
    }
    private static String getCleanDateFormat (String dateString){
    	
    	String newDateString = new String();
    	
    	newDateString = dateString.replace("-","/").trim();
    	
    	return newDateString;
    }
    
    public static String convertStringToDateFormat(String dateString) {
    	return convertStringToDateFormat(dateString, null);
    }

    	public static String convertStringToDateFormat(String dateString, String dateFormat) {
    	
    	if (dateString==null) {return null;}
    	
    	if ((dateFormat==null)||(dateFormat.equals(""))) {
    		dateFormat=getDefaultDateFormat();
    	}
    	
    	//clean up the string
    	String tempDateString = getCleanDateFormat(dateString);
    	
    	//determine the current format 
    	String oldDateFormat = getDateFormat(tempDateString);
    	
    	//unable to determine the current format, so return the original string
    	if (oldDateFormat.equalsIgnoreCase("UNKNOWN")) {return dateString;}
    	
    	//convert to an actual date datatype
    	Date dateValue = parseDate(tempDateString ,oldDateFormat);
    	
    	//System.out.println("oldDateFormat: " + oldDateFormat);
    	//System.out.println("tempDateString: " + tempDateString);
    	//System.out.println("dateValue: " + dateValue);
    	
    	//format to the new format
    	DateFormat df = new SimpleDateFormat(dateFormat);
    	String newDateString = df.format(dateValue);    	
    	
    	//System.out.println("RETURNING: " + newDateString);
    	
    	return newDateString;
    }
   
}
