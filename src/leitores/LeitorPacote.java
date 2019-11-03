package leitores;
import java.util.Scanner;

import objetos.ControleErro;
import objetos.PacoteDados;

public class LeitorPacote {

    public PacoteDados ler(String dados) {
        Scanner scanner = new Scanner(dados);
        scanner.useDelimiter(";");
        scanner.next();
        scanner.useDelimiter(":");
        String controleErro = scanner.next().replace(";", "");
        String apelidoOrigem = scanner.next();
        String apelidoDestino = scanner.next();
        int CRC = Integer.parseInt(scanner.next());
        String mensagem = scanner.next();

        return new PacoteDados("2345",ControleErro.valueOf(controleErro), apelidoOrigem, apelidoDestino, CRC, mensagem);
    }
}
