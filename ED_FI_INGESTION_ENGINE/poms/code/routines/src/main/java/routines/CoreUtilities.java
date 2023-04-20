package routines;

import java.io.FileInputStream;
import java.security.MessageDigest;
import java.util.Date;

/* 
 * Core class with usefule functions
 * 
 */

public class CoreUtilities {

    /**
     * appendDelimiter: checks whether the input string is null or empty and if not, appends the delimiter.
     */ 
    public static String appendDelimiter(String istrInputString, String istrDelimiter) {
    	String ostrReturnString;
    	
        if ((istrInputString == null) || (istrInputString.equals(""))) {
        	ostrReturnString = istrInputString;
        } else {
        	ostrReturnString = istrInputString+istrDelimiter;
        }
        return ostrReturnString;
    }
    
    /**
     * isNull: checks whether the input string is null or empty and if so, returns the alternate value.
     */     
    public static String isNull(String istrInputString, String istrAlternate) {
    	String ostrReturnString;
    	
        if ((istrInputString == null) || (istrInputString.equals(""))) {
        	ostrReturnString = istrAlternate;
        } else {
        	ostrReturnString = istrInputString;
        }
        return ostrReturnString;
    }
    
    /**
     * isNull: checks whether the input string is null or empty and if so, returns the alternate value.
     */     
    public static Integer isNull(Integer iintInput, Integer iintAlternate) {
    	Integer ointReturnInteger;
    	
        if (iintInput == null)  {
        	ointReturnInteger = iintAlternate;
        } else {
        	ointReturnInteger = iintInput;
        }
        return ointReturnInteger;
    }
    public static String getSHA1Checksum (String fileName) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        FileInputStream fis = new FileInputStream(fileName);
        byte[] dataBytes = new byte[1024];
        int nread = 0; 
     
        while ((nread = fis.read(dataBytes)) != -1) {
          md.update(dataBytes, 0, nread);
        };
        
        fis.close();
        
        byte[] mdbytes = md.digest();
     
        //convert the byte to hex format
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < mdbytes.length; i++) {
        	sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
     
        return sb.toString();
    }
    
    public static void setTimeLimitInSeconds(int timeLimitInSeconds){
    	
    	final int timeLimit = timeLimitInSeconds;
    	
    	final java.lang.Thread currentThread = Thread.currentThread();
    	java.util.TimerTask monitorTask = new java.util.TimerTask() {
    		
    	        public void run() {
    	        	
    	        	CoreLogs.errorMsg("Timeout occurred!!!  Your process was killed due to the fact that it ran longer than the maximum allowed time of " + timeLimit + " seconds.  In order to correct this, you must either reconfigure the job to allow a longer run time, or fix the jobs so it completes within the amount of time allowed.");
    	            System.exit(0);
    	        	currentThread.interrupt();
    	        }
    	};
    	java.util.Timer monitorTimer = new java.util.Timer();
    	monitorTimer.schedule(monitorTask, timeLimitInSeconds*1000);
    }



}