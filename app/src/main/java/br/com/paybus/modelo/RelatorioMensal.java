package br.com.paybus.modelo;

public class RelatorioMensal {
    private Integer id;
    private String dataDeEmissaoDoRelatorio;
    private Integer numeroDeAlunosPagantes;
    private Integer numeroDeAlunosInadimplentes;
    private Double totalPago;
    private Double debitoMensal;
    private Double ValorTotalmensal;
    private String observacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataDeEmissaoDoRelatorio() {
        return dataDeEmissaoDoRelatorio;
    }

    public void setDataDeEmissaoDoRelatorio(String dataDeEmissaoDoRelatorio) {
        this.dataDeEmissaoDoRelatorio = dataDeEmissaoDoRelatorio;
    }

    public Integer getNumeroDeAlunosPagantes() {
        return numeroDeAlunosPagantes;
    }

    public void setNumeroDeAlunosPagantes(Integer numeroDeAlunosPagantes) {
        this.numeroDeAlunosPagantes = numeroDeAlunosPagantes;
    }

    public Integer getNumeroDeAlunosInadimplentes() {
        return numeroDeAlunosInadimplentes;
    }

    public void setNumeroDeAlunosInadimplentes(Integer numeroDeAlunosInadimplentes) {
        this.numeroDeAlunosInadimplentes = numeroDeAlunosInadimplentes;
    }

    public Double getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(Double totalPago) {
        this.totalPago = totalPago;
    }

    public Double getDebitoMensal() {
        return debitoMensal;
    }

    public void setDebitoMensal(Double debitoMensal) {
        this.debitoMensal = debitoMensal;
    }

    public Double getValorTotalmensal() {
        return ValorTotalmensal;
    }

    public void setValorTotalmensal(Double valorTotalmensal) {
        ValorTotalmensal = valorTotalmensal;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
