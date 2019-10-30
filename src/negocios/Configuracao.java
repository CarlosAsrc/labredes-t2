package negocios;
public class Configuracao {

    private String ipDestino;
    private int porta;
    private String apelido;
    private int  tempoToken;
    private boolean token;

    public Configuracao(String ipDestino, int porta, String apelido, int tempoToken, boolean token) {
        this.ipDestino = ipDestino;
        this.porta = porta;
        this.apelido = apelido;
        this.tempoToken = tempoToken;
        this.token = token;
    }
}
