package negocios;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import conexao.Entrada;
import conexao.Saida;
import javafx.application.Platform;
import javafx.scene.paint.Color;

public class main {
	public static Configuracao configuracao =Testes.indentificacao("PC1");
	public static void main(String[] args) throws InterruptedException {
		/*
		String ipDestino="";
		int porta=0;
		String apelido="";
		int tempoToken=0;
		boolean token=true;

		configuracao = new Configuracao(ipDestino, porta, apelido, tempoToken, token);
		*/
		
//	configuracao = LeitorArquivo.ler("teste.txt");
	
System.out.println("comecou");

//Mensagens da situacao atual
System.out.println("Possui token: ");

System.out.println("Possui pacote: ");

//PC principal apenas:
System.out.println("Tokens circulando na rede: ");

System.out.println("Ouve retransmissao");

thread1();

thread2();

thread3();

TimeUnit.SECONDS.sleep(1);
while (true) {
	Mensagens.criaNovaMensagem();
	System.out.println(Mensagens.mensagens);
}


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
public static void thread3() {
	Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        
        @Override
        public void run() {
        	
     		try {
				Estados.atualizacao();
			} catch (InterruptedException e) {
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
