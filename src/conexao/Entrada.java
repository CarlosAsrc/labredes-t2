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
import negocios.Erro;
import negocios.Estados;
import negocios.Mensagens;
import negocios.Tempos;
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
			// Registro tempo de execucao
			tempo = tempo + 1;

			System.out.println();
			System.out.println("Tempo: " + tempo);

			byte[] receivebuffer = new byte[1024];
			byte[] sendbuffer = new byte[1024];
			DatagramPacket recvdpkt = new DatagramPacket(receivebuffer, receivebuffer.length);
			serverSocket.receive(recvdpkt);
			InetAddress IP = recvdpkt.getAddress();

			int portno = recvdpkt.getPort();
			String clientdata = new String(recvdpkt.getData());
			clientdata = ControleString.arrumaString(clientdata);
			System.out.println("Mensagem recebida: " + clientdata);
			// Tempo de chegada menor que o minimo
			if (clientdata.equals("1234")) {
				int i = (int) Tempos.calculatempo();
				if (i < main.tempoMinimoToken) {
					System.out.println("Mais de um token na rede. Token eliminado!");
					clientdata = "segundo token";
				}
			}
			if (clientdata.equals("1234")) {
				Estados.esperandoToken=false;
			}

			if (!clientdata.equals("segundo token")) {
				// Chance de ERRO!
				if (!clientdata.equals("1234")) {

					clientdata = Erro.causarErro(clientdata);

				}

				// Recebeu uma mensagem

				Estados.pacote = clientdata;
				Estados.alteracao = "sim";
				// Se nao for token
				if (!Estados.pacote.equals("1234")) {
					String saida = clientdata;

					PacoteDados pacoteDados = Mensagens.converteString_PD(saida);
					// Se for a maquina de origem ou a maquina de destino ou TODOS
					if (Mensagens.converteString_PD(Estados.pacote).getApelidoOrigem()
							.equals(main.configuracao.getApelido())
							|| Mensagens.converteString_PD(Estados.pacote).getApelidoDestino()
									.equals(main.configuracao.getApelido())
							|| Mensagens.converteString_PD(Estados.pacote).getApelidoDestino().equals("TODOS")) {

						// Confere se o CRC16 esta correto
						if (!(CRC16.converter(pacoteDados.getMensagem()) == pacoteDados.getCRC())) {
							ControleErro[] controleErro = ControleErro.values();
							pacoteDados.setControleErro(controleErro[1]);
							saida = Mensagens.convertePD_String(pacoteDados);
						}
						// Caso esteja correto
						else {
							// Confere se eh a maquina de destino
							if (pacoteDados.getApelidoDestino().equals(main.configuracao.getApelido())) {
								ControleErro[] controleErro = ControleErro.values();
								pacoteDados.setControleErro(controleErro[0]);
								saida = Mensagens.convertePD_String(pacoteDados);
							}
						}
					}
					// Se nao for a maquina de origem, envia o pacote para proxima
					if (!Mensagens.converteString_PD(Estados.pacote).getApelidoOrigem()
							.equals(main.configuracao.getApelido())) {

						Estados.saidaPacote = saida;
					}
					// Eh a maquina de origem
					else {
						// Se for ACK ou naocopiado remove a mensagem da lista e passa o token
						ControleErro[] controleErro = ControleErro.values();
						if (Mensagens.converteString_PD(Estados.pacote).getControleErro() == controleErro[0]
								|| Mensagens.converteString_PD(Estados.pacote).getControleErro() == controleErro[2]) {
							Mensagens.mensagens.remove(0);
							System.out.println("Mensagem removida da fila!");
							System.out.println("Fila atual: " + Mensagens.mensagens);
							Estados.esperandoRetorno = false;
							Estados.token = false;
							Estados.saidaPacote = "1234";
							Estados.retransmissao = false;
						}
						// Se for ERRO
						else {
							// Se a retranmissao for false , retransmite
							if (!Estados.retransmissao) {
								Estados.saidaPacote = Mensagens.mensagens.get(0);
								Estados.esperandoRetorno = false;
								Estados.retransmissao = true;
							}
							// Se for true, desisti da mensagem e passa o token
							else {
								Mensagens.mensagens.remove(0);
								System.out.println("Mensagem removida da fila!");
								System.out.println("Fila atual: " + Mensagens.mensagens);
								Estados.esperandoRetorno = false;
								Estados.retransmissao = false;
								Estados.token = false;
								Estados.saidaPacote = "1234";
							}

						}
					}
				}

				// Para nao inciar os envios antes do ciclo comecar
				if (!(main.principal) && tempo == 1) {

					main.thread2();
				}
			}
			TimeUnit.SECONDS.sleep(main.configuracao.getTempoToken());

		}

	}

}
