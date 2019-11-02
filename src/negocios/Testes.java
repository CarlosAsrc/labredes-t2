package negocios;

import objetos.Configuracao;

public class Testes {
	public static Configuracao indentificacao(String a) {
		if (a.equals("PC1")) {
			main.apelidoMaquinaDestino="PC2";
			String ipDestino = "192.168.4.100";
			int porta = 9876;
			String apelido = "PC1";
			int tempoToken = 1;
			boolean token = true;

			Configuracao configuracao = new Configuracao(ipDestino, porta, apelido, tempoToken, token);
			return configuracao;
		}
		if (a.equals("PC2")) {
			main.apelidoMaquinaDestino="PC3";
			String ipDestino = "192.168.4.101";
			int porta = 9876;
			String apelido = "PC2";
			int tempoToken = 1;
			boolean token = true;

			Configuracao configuracao = new Configuracao(ipDestino, porta, apelido, tempoToken, token);
			return configuracao;
		}
		if (a.equals("PC3")) {
			main.apelidoMaquinaDestino="PC1";
			//String ipDestino = "192.168.4.103";
			String ipDestino = "192.168.3.111";
			
			int porta = 9876;
			String apelido = "PC3";
			int tempoToken = 1;
			boolean token = true;

			Configuracao configuracao = new Configuracao(ipDestino, porta, apelido, tempoToken, token);
			return configuracao;
		}
		return null;

	}
	

}
