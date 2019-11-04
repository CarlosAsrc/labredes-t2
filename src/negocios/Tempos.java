package negocios;

import java.util.concurrent.TimeUnit;

public class Tempos {
	public static long startTime;
	

	public static void iniciaTempo() {
		startTime=System.nanoTime();
	}
	
	public static long calculatempo(){
	
	
	long endTime   = System.nanoTime();
	long totalTime = endTime - startTime;
	totalTime=(long) (totalTime/Math.pow(10, 9));
	return totalTime;
	
}
}
