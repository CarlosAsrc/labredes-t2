package negocios;

import java.util.concurrent.TimeUnit;

import conexao.Entrada;
import conexao.Saida;
import objetos.PacoteDados;

public class Estados {
public static String alteracao = "nao";
public static int tempoB=-1;
public static int tempoA=0;
public static void atualizacao() throws InterruptedException {
	while (true) {
		//Controle de conexao
		if (tempoB==tempoA) {
			System.out.println("CAIU");
			System.exit(0);
		}
		if (tempoA>1) {
		tempoB=tempoA;
		}
		
	//Atualizacoes	
	if (alteracao.equals("sim")) {
		if (pacote.equals("1234")) {
			token=true;
			pacote="";
		}
		
		//Mensagens da situacao atual
		System.out.println("Possui token: "+token);
	
		
		System.out.println("Pacote: "+pacote);
		
		if (!pacote.equals("")) {
			
			
		PacoteDados pacoteDados = Mensagens.converteString_PD(pacote);
		if (pacoteDados.getApelidoDestino().equals(main.configuracao.getApelido())) {
			Mensagens.mensagensrecebidas.add(pacoteDados.getMensagem());
			System.out.println("Meus pacotes recebidos: "+Mensagens.mensagensrecebidas);
		}
		
		}
		

		//PC principal apenas:
		if (main.principal) {
		System.out.println("Tokens circulando na rede: "+numeroTokens);

		System.out.println("Houve retransmissao: "+retransmissao);

		}
		
		alteracao="nao";
	}
	TimeUnit.SECONDS.sleep(main.configuracao.getTempoToken());
	}
}
public static boolean token = false;
public static String pacote = "";
public static String saidaPacote = "";
public static int numeroTokens= 1;
public static boolean retransmissao = false;
public static boolean esperandoRetorno= false;

}
