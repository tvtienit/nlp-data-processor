package hp.eng.utils;

public class Log {
	
	public static void info(String logName, String logContent) {
		if (logName == null) logName = "[INFO] ";
		System.out.print(logName);
		System.out.println(logContent);
	}
	
	public static void error(String err) {
		System.out.print("[ERROR] ");
		System.out.println(err);
	}
	
	public static void write(String logContent) {
		Log.write(logContent, true);
	}
	
	public static void write(String logContent, boolean lineBreak) {
		if (lineBreak) {
			System.out.println(logContent);	
		} else {
			System.out.print(logContent);
		}
	}
}
