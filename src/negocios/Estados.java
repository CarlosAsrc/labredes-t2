package negocios;

import java.util.concurrent.TimeUnit;

import conexao.Entrada;
import conexao.Saida;

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
		//Mensagens da situacao atual
		System.out.println("Possui token: "+token);
		System.out.println("Possui pacote: "+pacote);

		//PC principal apenas:
		if (main.principal==true) {
		System.out.println("Tokens circulando na rede: "+numeroTokens);

		System.out.println("Houve retransmissao: "+retransmissao);

		}
		
		alteracao="nao";
	}
	TimeUnit.SECONDS.sleep(2);
	}
}
public static boolean token = false;
public static boolean pacote = false;
public static int numeroTokens= 1;
public static boolean retransmissao = false;
}
