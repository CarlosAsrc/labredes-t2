

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.TimeUnit;


public class Saida {

	public static void enviar() throws SocketException, IOException, InterruptedException {

		
		String mensagem = "pc2";
	
		System.out.println("Procurando proximo....");

		BufferedReader clientRead = new BufferedReader(new InputStreamReader(System.in));

		//InetAddress IP = InetAddress.getByName("192.168.0.18");
	//	InetAddress IP = InetAddress.getByName("10.32.163.3");
	//	InetAddress IP = InetAddress.getByName("10.32.143.59");
		InetAddress IP = InetAddress.getByName("192.168.4.100");
int porta = 9876;
		DatagramSocket clientSocket = new DatagramSocket();
		int contlinha = 0;
		int tempo = 0;
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
			System.out.print("\nServer: " + serverData);

		
			TimeUnit.SECONDS.sleep(1);

		}

	}


}
