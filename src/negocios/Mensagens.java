package negocios;

import java.util.ArrayList;
import java.util.Scanner;

import objetos.ControleErro;
import objetos.PacoteDados;

public class Mensagens {
	public static ArrayList<String> mensagensrecebidas = new ArrayList<String>();
	public static ArrayList<String> mensagens = new ArrayList<String>();

	public static void criaNovaMensagem() {
		System.out.println("Digite sua mensagem: ");
		Scanner s = new Scanner(System.in);
		String texto = s.nextLine();
		
		System.out.println("Digite o apelido do destinatario: ");
		Scanner s2 = new Scanner(System.in);
		String destino = s2.nextLine();
		
		String pd = "2345";
		ControleErro[] controleErro = ControleErro.values();
		String apelidoOrigem = main.configuracao.getApelido();
		String apelidoDestino = destino;
		int CRC = CRC16.converter(texto);
		PacoteDados pacoteDado = new PacoteDados(pd, controleErro[2], apelidoOrigem, apelidoDestino, CRC, texto);
		String mensagem = convertePD_String(pacoteDado);
		if (mensagens.size()>=10) {
			System.out.println("Sua mensagem nao pode ser adicionada pois a lista esta cheia");
		}
		else {
		mensagens.add(mensagem);
		System.out.println("Sua mensagem foi adicionada a lista");
		}
	}

	public static String convertePD_String(PacoteDados pad) {
		String mensagem = pad.getPd() + ";" + pad.getControleErro() + ":" + pad.getApelidoOrigem() + ":"
				+ pad.getApelidoDestino() + ":" + pad.getCRC() + ":" + pad.getMensagem();

		return mensagem;
	}

	public static PacoteDados converteString_PD(String mensagem) {
		String[] a = mensagem.split(";");
		String pd = a[0];
		String[] b = a[1].split(":");
		ControleErro[] controleErro = ControleErro.values();
		int i = 0;
		if (b[0].equals("ACK")) {
			i = 0;
		}
		if (b[0].equals("erro")) {
			i = 1;
		}
		if (b[0].equals("naocopiado")) {
			i = 2;
		}
		String apelidoOrigem = b[1];
		String apelidoDestino = b[2];
		int cRC = Integer.parseInt(b[3]);
		String mens = b[4];

		PacoteDados pacoteDados = new PacoteDados(pd, controleErro[i], apelidoOrigem, apelidoDestino, cRC, mens);

		return pacoteDados;
	}
}
