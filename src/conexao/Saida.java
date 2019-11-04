package conexao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.TimeUnit;

import negocios.ControleString;
import negocios.Erro;
import negocios.Estados;
import negocios.Mensagens;

import negocios.Tempos;
import negocios.main;

public class Saida {
	public static int tempo = 0;

	public static void enviar() throws SocketException, IOException, InterruptedException {

		String mensagem = "Transmitindo";

		System.out.println("Enviando...");

		

		InetAddress IP = InetAddress.getByName(main.configuracao.getIpDestino());

		int porta = main.configuracao.getPorta();
		DatagramSocket clientSocket = new DatagramSocket();
		DatagramSocket clientSocket2 = new DatagramSocket();
		
		while (true) {
			tempo = tempo + 1;

			// System.out.println("\nTempo: " + tempo);

			byte[] sendbuffer = new byte[1024];
		
			// Prepara mensagem padrao
			String clientData = mensagem;
			// Se recebeu um pacote, envia ele para o proximo
			if (!Estados.saidaPacote.equals("")) {
				clientData = Estados.saidaPacote;
				Estados.saidaPacote = "";
			}
			// Esta com o token e nao enviou sua propria mensagem
			// ainda, entao envia uma mensagem

			if (Estados.token) {
				
				if (!Estados.esperandoRetorno) {
					if (Mensagens.mensagens.size() > 0) {
						clientData = Mensagens.mensagens.get(0);
						Estados.esperandoRetorno = true;
					} else {
						Estados.token = false;
						clientData = "1234";
					}
				}

			}



			sendbuffer = clientData.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendbuffer, sendbuffer.length, IP, porta);

			
			// Tempo de espera maior que o tempo maximo cria um novo token
						if (Estados.esperandoToken && main.tempoMaximoToken<(int) Tempos.calculatempo()) {
							System.out.println("Time out, mais um token foi criado!");
							String a ="1234";
							byte[] sendbuffer2 = new byte[1024];
							sendbuffer2 = a.getBytes();
							DatagramPacket sendPacket2 = new DatagramPacket(sendbuffer2, sendbuffer2.length, IP, porta);
							clientSocket2.send(sendPacket2);
							System.out.println("Mensagem enviada: " + a);
							Tempos.iniciaTempo();
						}
			
			if (!clientData.equals("Transmitindo")) {
				if (clientData.equals("1234")&&!(Estados.esperandoToken)) {
					Tempos.iniciaTempo();
					
					Estados.esperandoToken=true;
				}
				
				
				clientSocket.send(sendPacket);
				System.out.println("Mensagem enviada: " + clientData);
			}
			
			TimeUnit.SECONDS.sleep(main.configuracao.getTempoToken());

		}

	}
}
