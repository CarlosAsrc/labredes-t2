package objetos;

public class PacoteDados {
    private String pd;
	private ControleErro controleErro;
    private String apelidoOrigem;
    private String apelidoDestino;
    private int CRC;
    private String mensagem;
	public PacoteDados(String pd, ControleErro controleErro, String apelidoOrigem, String apelidoDestino, int cRC,
			String mensagem) {
		super();
		this.pd = pd;
		this.controleErro = controleErro;
		this.apelidoOrigem = apelidoOrigem;
		this.apelidoDestino = apelidoDestino;
		CRC = cRC;
		this.mensagem = mensagem;
	}
	public String getPd() {
		return pd;
	}
	public void setPd(String pd) {
		this.pd = pd;
	}
	public ControleErro getControleErro() {
		return controleErro;
	}
	public void setControleErro(ControleErro controleErro) {
		this.controleErro = controleErro;
	}
	public String getApelidoOrigem() {
		return apelidoOrigem;
	}
	public void setApelidoOrigem(String apelidoOrigem) {
		this.apelidoOrigem = apelidoOrigem;
	}
	public String getApelidoDestino() {
		return apelidoDestino;
	}
	public void setApelidoDestino(String apelidoDestino) {
		this.apelidoDestino = apelidoDestino;
	}
	public int getCRC() {
		return CRC;
	}
	public void setCRC(int cRC) {
		CRC = cRC;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}



	
}
