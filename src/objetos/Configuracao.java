package objetos;
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

	public String getIpDestino() {
		return ipDestino;
	}

	public void setIpDestino(String ipDestino) {
		this.ipDestino = ipDestino;
	}

	public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public int getTempoToken() {
		return tempoToken;
	}

	public void setTempoToken(int tempoToken) {
		this.tempoToken = tempoToken;
	}

	public boolean isToken() {
		return token;
	}

	public void setToken(boolean token) {
		this.token = token;
	}
    
}
