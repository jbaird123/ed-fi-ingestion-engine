package routines;


public class DSSRejects {
	private static String DSSRejects = "\r\n";
	private static int rejectCount = 0;

	public static String getDSSRejects() {
		return DSSRejects;
	}

	public static void setDSSRejects(String rejectRecord) {
		DSSRejects += rejectRecord + "\r\n";
		rejectCount++;
	}
	
	public static void reSetDSSRejects() {
		DSSRejects = "";
		rejectCount=0;
	}

	public static int getRejectCount() {
		return rejectCount;
	}
 
}
