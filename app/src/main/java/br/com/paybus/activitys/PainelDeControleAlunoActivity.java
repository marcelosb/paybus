package br.com.paybus.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.paybus.R;
import br.com.paybus.dao.AlunoDAO;
import br.com.paybus.dao.PagamentoDAO;
import br.com.paybus.modelo.Aluno;
import br.com.paybus.modelo.Pagamento;
import br.com.paybus.utilitarios.AlunoRecyclerViewAdapter;
import br.com.paybus.utilitarios.ComprovanteDePagamentoAlunoRecyclerViewAdapter;
import br.com.paybus.utilitarios.Usuario;

public class PainelDeControleAlunoActivity extends AppCompatActivity {

    public List<Pagamento> listaDeComprovantesDePagamentos;
    private ComprovanteDePagamentoAlunoRecyclerViewAdapter comprovanteDePagamentoAlunoRecyclerViewAdapter;
    private RecyclerView listaComprovantesAlunoRecyclerView;
    public PagamentoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_painel_de_controle_aluno);

        TextView textoNomeAlunologado = findViewById(R.id.textoNomeAlunoLogado);
        textoNomeAlunologado.setText("Olá "+Usuario.nome+", seja bem vindo!");

        criarRecyclerViewComprovanteDePagamentoAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_painel_de_controle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_sobre:
                startActivity(new Intent(PainelDeControleAlunoActivity.this, ViewPDFAjudaActivity.class));
                PainelDeControleAlunoActivity.this.finish();
                break;
            case R.id.menu_alterar_senha:
                startActivity(new Intent(this, EditarSenhaDeUsuarioActivity.class));
                PainelDeControleAlunoActivity.this.finish();
                break;
            case R.id.menu_sair_do_sistema:
                startActivity(new Intent(this, TelaPrincipalActivity.class));
                PainelDeControleAlunoActivity.this.finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, TelaPrincipalActivity.class));
        PainelDeControleAlunoActivity.this.finish();
    }

    private void criarRecyclerViewComprovanteDePagamentoAluno() {
        listaDeComprovantesDePagamentos = new ArrayList<Pagamento>();
        dao = new PagamentoDAO(this);
        listaDeComprovantesDePagamentos = dao.listarComprovantesDePagamento(Usuario.nome);
        listaComprovantesAlunoRecyclerView = findViewById(R.id.listaComprovantesAlunoRecyclerView);
        listaComprovantesAlunoRecyclerView.setHasFixedSize(true);
        listaComprovantesAlunoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        comprovanteDePagamentoAlunoRecyclerViewAdapter = new ComprovanteDePagamentoAlunoRecyclerViewAdapter(listaDeComprovantesDePagamentos, this);
        listaComprovantesAlunoRecyclerView.setAdapter(comprovanteDePagamentoAlunoRecyclerViewAdapter);
        //listaDeAlunosrecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}
