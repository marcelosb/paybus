package br.com.paybus.modelo;

public class Pagamentos {
    private Integer id;
    private String dataDoVencimento;
    private String dataDoPagamento;
    private Double valorDoPagamento;
    private String status;
    private Aluno aluno;
    private Cobrador cobrador;
    private Motorista motorista;
    private String observacao;

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

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Cobrador getCobrador() {
        return cobrador;
    }

    public void setCobrador(Cobrador cobrador) {
        this.cobrador = cobrador;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
