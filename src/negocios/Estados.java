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
		
		if (tempoB==tempoA) {
			System.out.println("CAIU");
			System.exit(0);
		}
		if (tempoA>1) {
		tempoB=tempoA;
		}
		
	if (alteracao.equals("sim")) {
		System.out.println();
		
		alteracao="nao";
	}
	TimeUnit.SECONDS.sleep(2);
	}
}
}
