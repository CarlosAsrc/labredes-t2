package negocios;

import objetos.Configuracao;

public class Testes {
	public static Configuracao indentificacao(String a) {
		if (a.equals("PC1")) {
			main.apelidoMaquinaDestino="PC2";
			String ipDestino = "192.168.0.18";
			int porta = 9876;
			String apelido = "PC1";
			int tempoToken = 1;
			boolean token = true;

			Configuracao configuracao = new Configuracao(ipDestino, porta, apelido, tempoToken, token);
			return configuracao;
		}
		if (a.equals("PC2")) {
			main.apelidoMaquinaDestino="PC1";
			String ipDestino = "192.168.0.15";
			int porta = 9876;
			String apelido = "PC2";
			int tempoToken = 1;
			boolean token = true;

			Configuracao configuracao = new Configuracao(ipDestino, porta, apelido, tempoToken, token);
			return configuracao;
		}
		if (a.equals("PC3")) {
			main.apelidoMaquinaDestino="PC1";
			String ipDestino = "";
			int porta = 9876;
			String apelido = "";
			int tempoToken = 0;
			boolean token = true;

			Configuracao configuracao = new Configuracao(ipDestino, porta, apelido, tempoToken, token);
			return configuracao;
		}
		return null;

	}
	

}
