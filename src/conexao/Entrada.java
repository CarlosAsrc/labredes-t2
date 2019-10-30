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
import negocios.main;



public class Entrada {
	public static int tempo = 0;
	public static void escutar() throws InterruptedException, IOException {
		
		String mensagem = "PC1...";
	
		System.out.println("Escutando....");
		
		DatagramSocket serverSocket = new DatagramSocket(main.configuracao.getPorta());
		
		
		while (true) {
			tempo = tempo + 1;
			System.out.println("Tempo: " + tempo);
			Estados.tempoA=tempo;
			byte[] receivebuffer = new byte[1024];
			byte[] sendbuffer = new byte[1024];
			DatagramPacket recvdpkt = new DatagramPacket(receivebuffer, receivebuffer.length);
			serverSocket.receive(recvdpkt);
			InetAddress IP = recvdpkt.getAddress();
		
			int portno = recvdpkt.getPort();
			String clientdata = new String(recvdpkt.getData());
			clientdata=ControleString.arrumaString(clientdata);
		
				System.out.println("\nClient : " + clientdata);
	

			

			String serverdata = mensagem;

			sendbuffer = serverdata.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendbuffer, sendbuffer.length, IP, portno);

			serverSocket.send(sendPacket);

			TimeUnit.SECONDS.sleep(main.configuracao.getTempoToken());
			
		}
		
	}


}
