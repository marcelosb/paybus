package br.com.paybus.modelo;

public class MesDoPagamento {

    private Integer id;
    private String mesEAnoDoPagamento;
    private String dataDoVencimento;

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

    public String getDataDoVencimento() {
        return dataDoVencimento;
    }

    public void setDataDoVencimento(String dataDoVencimento) {
        this.dataDoVencimento = dataDoVencimento;
    }
}
