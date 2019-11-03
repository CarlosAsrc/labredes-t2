package negocios;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import conexao.Entrada;
import conexao.Saida;

import objetos.Configuracao;
import objetos.ControleErro;

public class main {

	public static String nome = "PC1";
	//Chance em % de erro em cada recebimento de mensagem
	public static int chanceDeErro =50;
	public static int tempoMaximoToken =80;
	public static int tempoMinimoToken =40;
	public static String apelidoMaquinaDestino = "";
	public static boolean principal = false;
	public static Configuracao configuracao = Testes.indentificacao(nome);

	public static void main(String[] args) throws InterruptedException {

		/*
		 * String ipDestino=""; int porta=0; String apelido=""; int tempoToken=0;
		 * boolean token=true;
		 * 
		 * configuracao = new Configuracao(ipDestino, porta, apelido, tempoToken,
		 * token);
		 */

		// configuracao = LeitorArquivo.ler("teste.txt");

		if (nome.equals("PC1")) {
			principal = true;
			Mensagens.mensagens.add("2345;naocopiado:PC1:PC2:61965:arvore");
			Mensagens.mensagens.add("2345;naocopiado:PC1:PC3:16176:bola");
			Mensagens.mensagens.add("2345;naocopiado:PC1:TODOS:16840:casa");
		}

		System.out.println("Comecou");

		// Mensagens da situacao atual
		if (principal == true) {
			System.out.println("Possui token: true ");
			Estados.token = true;
		} else {
			System.out.println("Possui token: false ");
		}
		

		// PC principal apenas:
		if (principal == true) {
			System.out.println("Tokens circulando na rede: 1 ");

			System.out.println("Houve retransmissao: false");

		}
		thread1();

		if (principal) {
			thread2();
		}

		thread3();

		TimeUnit.SECONDS.sleep(1);
		while (true) {
			Mensagens.criaNovaMensagem();
			System.out.println(Mensagens.mensagens);
		}

	}

	// Responsavel pela Entrada
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

	// Responsavel pela Saida
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

	// Responsavel pela Situacao Atual
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
