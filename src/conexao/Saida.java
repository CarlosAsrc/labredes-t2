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
import negocios.main;

public class Saida {
	public static int tempo = 0;

	public static void enviar() throws SocketException, IOException, InterruptedException {

		String mensagem = "PC2...";
		
			System.out.println("Procurando proximo....");
		
		BufferedReader clientRead = new BufferedReader(new InputStreamReader(System.in));

		InetAddress IP = InetAddress.getByName(main.configuracao.getIpDestino());

		int porta = main.configuracao.getPorta();
		DatagramSocket clientSocket = new DatagramSocket();

		while (true) {
			tempo = tempo + 1;
			System.out.println("\nTempo: " + tempo);
			byte[] sendbuffer = new byte[1024];
			byte[] receivebuffer = new byte[1024];

			String clientData = mensagem;

			sendbuffer = clientData.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendbuffer, sendbuffer.length, IP, porta);
			clientSocket.send(sendPacket);

			DatagramPacket receivePacket = new DatagramPacket(receivebuffer, receivebuffer.length);
			clientSocket.receive(receivePacket);
			String serverData = new String(receivePacket.getData());

			serverData = ControleString.arrumaString(serverData);
			System.out.print("\nServer: " + serverData);

			TimeUnit.SECONDS.sleep(1);
			
		}
		
	}
}
