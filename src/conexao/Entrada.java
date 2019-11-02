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

import negocios.CRC16;
import negocios.ControleString;
import negocios.Estados;
import negocios.Mensagens;
import negocios.main;
import objetos.ControleErro;
import objetos.PacoteDados;

public class Entrada {
	public static int tempo = 0;

	public static void escutar() throws InterruptedException, IOException {

		String mensagem = "Recebendo";

		System.out.println("Escutando...");

		DatagramSocket serverSocket = new DatagramSocket(main.configuracao.getPorta());

		while (true) {
			//Registro tempo de execucao
			tempo = tempo + 1;
			if (tempo == 2) {
				System.out.println();
				System.out.println("Transmitindo!");
			}
			System.out.println();
			System.out.println("Tempo: " + tempo);

			Estados.tempoA = tempo;

			byte[] receivebuffer = new byte[1024];
			byte[] sendbuffer = new byte[1024];
			DatagramPacket recvdpkt = new DatagramPacket(receivebuffer, receivebuffer.length);
			serverSocket.receive(recvdpkt);
			InetAddress IP = recvdpkt.getAddress();

			int portno = recvdpkt.getPort();
			String clientdata = new String(recvdpkt.getData());
			clientdata = ControleString.arrumaString(clientdata);
			// Recebeu uma mensagem
			if (!clientdata.equals("Transmitindo")) {

				Estados.pacote = clientdata;
				Estados.alteracao = "sim";
				// Se nao for token
				if (!Estados.pacote.equals("1234")) {
					// Se nao for a maquina de origem, envia o pacote para proxima
					if (!Mensagens.converteString_PD(Estados.pacote).getApelidoOrigem()
							.equals(main.configuracao.getApelido())) {
						String saida = clientdata;
						PacoteDados pacoteDados = Mensagens.converteString_PD(saida);
						// Confere se o CRC16 esta correto
						if (!(CRC16.converter(pacoteDados.getMensagem()) == pacoteDados.getCRC())) {
							ControleErro[] controleErro = ControleErro.values();
							pacoteDados.setControleErro(controleErro[1]);
							saida = Mensagens.convertePD_String(pacoteDados);
						}
						//Caso esteja correto
						else {
							//Confere se eh a maquina de destino
							if (pacoteDados.getApelidoDestino().equals(main.configuracao.getApelido())) {
								ControleErro[] controleErro = ControleErro.values();
								pacoteDados.setControleErro(controleErro[0]);
								saida = Mensagens.convertePD_String(pacoteDados);
							}
						}
						Estados.saidaPacote = saida;
					}
					// Eh a maquina de origem
					else {
						// Se for ACK remove a mensagem da lista e passa o token
						ControleErro[] controleErro = ControleErro.values();
						if (Mensagens.converteString_PD(Estados.pacote).getControleErro() == controleErro[0]) {
							Mensagens.mensagens.remove(0);
							Estados.token = false;
							Estados.saidaPacote = "1234";
						}
						// Se for ERRO ou NAO_COPIADO
						else {
							// Se a retranmissao for false , retransmite
							if (!Estados.retransmissao) {
								Estados.saidaPacote = Mensagens.mensagens.get(0);
								Estados.retransmissao = true;
							}
							// Se for true, desisti da mensagem e passa o token
							else {
								Mensagens.mensagens.remove(0);
								Estados.retransmissao = false;
								Estados.token = false;
								Estados.saidaPacote = "1234";
							}

						}
					}
				}
			}
			String serverdata = mensagem;

			sendbuffer = serverdata.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendbuffer, sendbuffer.length, IP, portno);

			serverSocket.send(sendPacket);
			if (!(main.principal) && tempo == 1) {

				main.thread2();
			}
			TimeUnit.SECONDS.sleep(main.configuracao.getTempoToken());

		}

	}

}
