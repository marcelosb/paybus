package br.com.paybus.modelo;

public class Pagamentos {
    private Integer id;
    private String dataDoVencimento;
    private String dataDoPagamento;
    private Double valorDoPagamento;
    private String status;
    private String aluno;
    private String cobrador;
    private String motorista;
    private String observacao;

    public Pagamentos(String dataDoVencimento, String dataDoPagamento, Double valorDoPagamento, String status, String aluno, String cobrador, String motorista, String observacao) {
        this.dataDoVencimento = dataDoVencimento;
        this.dataDoPagamento = dataDoPagamento;
        this.valorDoPagamento = valorDoPagamento;
        this.status = status;
        this.aluno = aluno;
        this.cobrador = cobrador;
        this.motorista = motorista;
        this.observacao = observacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataDoVencimento() {
        return dataDoVencimento;
    }

    public void setDataDoVencimento(String dataDoVencimento) {
        this.dataDoVencimento = dataDoVencimento;
    }

    public String getDataDoPagamento() {
        return dataDoPagamento;
    }

    public void setDataDoPagamento(String dataDoPagamento) {
        this.dataDoPagamento = dataDoPagamento;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public String getCobrador() {
        return cobrador;
    }

    public void setCobrador(String cobrador) {
        this.cobrador = cobrador;
    }

    public String getMotorista() {
        return motorista;
    }

    public void setMotorista(String motorista) {
        this.motorista = motorista;
    }
}
