package negocios;

import java.util.Random;

public class Erro {
public static String causarErro(String mensagem) {
	Random gerador = new Random();
	int resultado = gerador.nextInt(100);
	resultado=resultado+1;
	
	if (resultado<=main.chanceDeErro) {
		mensagem=mensagem+" Erro!";
		System.out.println("Deu erro");
	}
	
	return mensagem;
}
}
