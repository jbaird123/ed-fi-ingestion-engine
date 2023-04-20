package routines;

public class Load_DSS_302_To_Staging_helper {

   public static String decimalCleanup(String inString) {
	   String newString ="0.00";
	   if ((inString!=null) && (inString.trim().length()>0)) {
		   newString=CoreUtilities.isNull(StringHandling.EREPLACE(inString.trim(),",","") ,"0.00") ;
		   if (newString.equals("-")) {newString = "0.00"; }
	   }
	   return newString;
   }
}
