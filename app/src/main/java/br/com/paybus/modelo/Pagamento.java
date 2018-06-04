package br.com.paybus.modelo;

public class Pagamento {
    private Integer id;
    private String mesEAnoDoPagamento;
    private String dataDoPagamento;
    private String dataDoVencimento;
    private Double valorDoPagamento;
    private String status;
    private String nomeDoAluno;
    private String instituicaoDeEnsinoDoAluno;
    private String nomeDoCobrador;
    private String nomeDoMotorista;
    private String observacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMesEAnoDoPagamento() {
        return mesEAnoDoPagamento;
    }

    public void setMesEAnoDoPagamento(String mesEAnoDoPagamento) {
        this.mesEAnoDoPagamento = mesEAnoDoPagamento;
    }

    public String getInstituicaoDeEnsinoDoAluno() {
        return instituicaoDeEnsinoDoAluno;
    }

    public void setInstituicaoDeEnsinoDoAluno(String instituicaoDeEnsinoDoAluno) {
        this.instituicaoDeEnsinoDoAluno = instituicaoDeEnsinoDoAluno;
    }

    public String getDataDoPagamento() {
        return dataDoPagamento;
    }

    public void setDataDoPagamento(String dataDoPagamento) {
        this.dataDoPagamento = dataDoPagamento;
    }

    public String getDataDoVencimento() {
        return dataDoVencimento;
    }

    public void setDataDoVencimento(String dataDoVencimento) {
        this.dataDoVencimento = dataDoVencimento;
    }

    public Double getValorDoPagamento() {
        return valorDoPagamento;
    }

    public void setValorDoPagamento(Double valorDoPagamento) {
        this.valorDoPagamento = valorDoPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNomeDoAluno() {
        return nomeDoAluno;
    }

    public void setNomeDoAluno(String nomeDoAluno) {
        this.nomeDoAluno = nomeDoAluno;
    }

    public String getNomeDoCobrador() {
        return nomeDoCobrador;
    }

    public void setNomeDoCobrador(String nomeDoCobrador) {
        this.nomeDoCobrador = nomeDoCobrador;
    }

    public String getNomeDoMotorista() {
        return nomeDoMotorista;
    }

    public void setNomeDoMotorista(String nomeDoMotorista) {
        this.nomeDoMotorista = nomeDoMotorista;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
