public class PacoteDados {
    private ControleErro controleErro;
    private String apelidoOrigem;
    private String apelidoDestino;
    private int CRC;
    private String mensagem;

    public PacoteDados(ControleErro controleErro, String apelidoOrigem, String apelidoDestino, int CRC, String mensagem) {
        this.controleErro = controleErro;
        this.apelidoOrigem = apelidoOrigem;
        this.apelidoDestino = apelidoDestino;
        this.CRC = CRC;
        this.mensagem = mensagem;
    }
}
