package negocios;

import java.util.concurrent.TimeUnit;

import conexao.Entrada;
import conexao.Saida;
import objetos.ControleErro;
import objetos.PacoteDados;

public class Estados {
public static String alteracao = "nao";

public static void atualizacao() throws InterruptedException {
	while (true) {
		
	//Atualizacoes	
	if (alteracao.equals("sim")) {
		if (pacote.equals("1234")) {
			token=true;
			pacote="";
		}
		
		//Mensagens da situacao atual
		System.out.println("Possui token: "+token);
	
		
	//	System.out.println("Pacote: "+pacote);
		
		if (!pacote.equals("")) {
			
			
		PacoteDados pacoteDados = Mensagens.converteString_PD(pacote);
		//Se o CRC16 estiver errado coloca erro na mensagem
		if (pacoteDados.getCRC()!=CRC16.converter(pacoteDados.getMensagem())) {
			ControleErro[] controleErro = ControleErro.values();
			pacoteDados.setControleErro(controleErro[1]);
		}
		//Se o pacote for para o Destino ou para TODOS, adiciona na lista de pacotes recebidos
		if (pacoteDados.getApelidoDestino().equals(main.configuracao.getApelido())||pacoteDados.getApelidoDestino().equals("TODOS")) {
			if (!pacoteDados.getApelidoOrigem().equals(main.configuracao.getApelido())) {
				ControleErro[] controleErro = ControleErro.values();
			//Verifica sem a mensagem nao contem erros
				if (pacoteDados.getControleErro()!=controleErro[1]) {
			Mensagens.mensagensrecebidas.add(pacoteDados.getMensagem());
			System.out.println("Meus pacotes recebidos: "+Mensagens.mensagensrecebidas);
				}
			}
		}
		
		}
		

		//PC principal apenas:
		if (main.principal) {
		System.out.println("Tokens circulando na rede: "+numeroTokens);

		}
		System.out.println("Houve retransmissao: "+retransmissao);
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
