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
import negocios.Estados;
import negocios.Mensagens;
import negocios.main;

public class Saida {
	public static int tempo = 0;

	public static void enviar() throws SocketException, IOException, InterruptedException {

		String mensagem = "Transmitindo";

		System.out.println("Enviando...");

		BufferedReader clientRead = new BufferedReader(new InputStreamReader(System.in));

		InetAddress IP = InetAddress.getByName(main.configuracao.getIpDestino());

		int porta = main.configuracao.getPorta();
		DatagramSocket clientSocket = new DatagramSocket();

		while (true) {
			tempo = tempo + 1;
		
			// System.out.println("\nTempo: " + tempo);
			
			byte[] sendbuffer = new byte[1024];
			byte[] receivebuffer = new byte[1024];

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
			
			if (!clientData.equals("Transmitindo")) {
			clientSocket.send(sendPacket);
			System.out.println("Mensagem enviada: " + clientData);
			}
			/*DatagramPacket receivePacket = new DatagramPacket(receivebuffer, receivebuffer.length);
			clientSocket.receive(receivePacket);
			String serverData = new String(receivePacket.getData());

			
*/
			TimeUnit.SECONDS.sleep(main.configuracao.getTempoToken());

		}

	}
}
