import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import conexao.Entrada;
import conexao.Saida;
import javafx.application.Platform;
import javafx.scene.paint.Color;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
System.out.println("comecou");

thread1();
System.out.println("1");
thread2();
System.out.println("2");


	}
public static void thread1() {
	Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        
        @Override
        public void run() {
        	try {
				Entrada.escutar();
			} catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	        	

        }
    };
    long delay = 0; //
    long period = 1000;
    timer.schedule(task, delay, period);
	}
public static void thread2() {
	Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        
        @Override
        public void run() {
        	
     		try {
				Saida.enviar();
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    	
	
        }
    };
    long delay = 0; //
    long period = 1000;
    timer.schedule(task, delay, period);
	}

}
