package mda_utilidades;

public class I {
	static enum Verbosity {NoLogging, Low, High};
	//static Verbosity logSetting = Verbosity.NoLogging;
	static Verbosity logSetting = Verbosity.Low;
	//static Verbosity logSetting = Verbosity.High;
	
	public static void Log(int i){
		if(logSetting == Verbosity.Low) {
			System.out.println(i);	
		}
		
		if(logSetting == Verbosity.High) {

			System.out.println(context() + i);
		}
	}

	public static void Log(String s){
		if(logSetting == Verbosity.Low) {
			System.out.println(s);
		}
		
		if(logSetting == Verbosity.High) {
	
			System.out.println(context() + s);
		}
		
	}
	
	public static void Log(StringBuilder s){
		if(logSetting == Verbosity.Low) {		
			System.out.println(s);
		}
		
		if(logSetting == Verbosity.High) {
			System.out.println(context() + s);
		}
	}
	
	private static String context() {
		String currentMethod = Thread.currentThread().getStackTrace()[3].getMethodName();
		String currentClassFull = Thread.currentThread().getStackTrace()[3].getClassName();
		String currentClass = currentClassFull.substring(currentClassFull.lastIndexOf('.') + 1);
		
		return currentClass + " / " + currentMethod + "() / ";
	}
	
	I(){
		
	}
}
