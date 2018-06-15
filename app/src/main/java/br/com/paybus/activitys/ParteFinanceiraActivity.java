package br.com.paybus.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import br.com.paybus.R;
import br.com.paybus.dao.AlunoDAO;
import br.com.paybus.dao.MesDoPagamentoDAO;
import br.com.paybus.dao.PagamentoDAO;
import br.com.paybus.modelo.Aluno;
import br.com.paybus.modelo.MesDoPagamento;
import br.com.paybus.modelo.Pagamento;
import br.com.paybus.utilitarios.AlunoRecyclerViewAdapter;
import br.com.paybus.utilitarios.ParteFinanceiraRecyclerViewAdapter;

public class ParteFinanceiraActivity extends AppCompatActivity {

    public List<MesDoPagamento> listaMesesPagamentosFinanceiro;
    public ParteFinanceiraRecyclerViewAdapter parteFinanceiraRecyclerViewAdapter;
    public RecyclerView listaDeParteFinanceiraRecyclerView;
    public MesDoPagamentoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_parte_financeira);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        criarRecyclerViewParteFinanceira();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                ParteFinanceiraActivity.this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void criarRecyclerViewParteFinanceira() {
        listaMesesPagamentosFinanceiro = new ArrayList<>();
        dao = new MesDoPagamentoDAO(this);
        listaMesesPagamentosFinanceiro = dao.listarMesesDoPagamento();
        listaDeParteFinanceiraRecyclerView = findViewById(R.id.parteFinanceiraRecyclerView);
        listaDeParteFinanceiraRecyclerView.setHasFixedSize(true);
        listaDeParteFinanceiraRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        parteFinanceiraRecyclerViewAdapter = new ParteFinanceiraRecyclerViewAdapter(listaMesesPagamentosFinanceiro, this);
        listaDeParteFinanceiraRecyclerView.setAdapter(parteFinanceiraRecyclerViewAdapter);
    }
}
