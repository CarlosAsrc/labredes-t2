package negocios;

import java.util.ArrayList;
import java.util.Scanner;

public class Mensagens {
public static ArrayList<String> mensagens = new ArrayList<String>();

public static void criaNovaMensagem () {
	System.out.println("Digite sua mensagem: ");
	
	 Scanner s = new Scanner(System.in);	
	String mensagem = s.nextLine();
	mensagens.add(mensagem);
	System.out.println("Sua mensagem foi adicionada a lista");
	
}
}
