package leitores;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import objetos.Configuracao;

public class LeitorArquivo {

    private static String caminhoArquivos = "./arquivos/";

    public LeitorArquivo() {

    }

    public static Configuracao ler(String nomeArquivo){

        try{
            Scanner scanner = new Scanner(new File(caminhoArquivos + nomeArquivo));
            String [] linha = scanner.nextLine().split(":");
            String ipDestino = linha[0];
            int porta = Integer.parseInt(linha[1]);
            String apelido = scanner.nextLine();
            int tempoToken = Integer.parseInt(scanner.nextLine());
            boolean token = Boolean.parseBoolean(scanner.nextLine());

            return new Configuracao(ipDestino, porta, apelido, tempoToken, true);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e){
            System.out.println("Arquivo de configuracao com formato invalido.");
        }
        return null;
    }


}
