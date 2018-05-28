package br.com.paybus.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.paybus.R;
import br.com.paybus.dao.CobradorDAO;
import br.com.paybus.dao.MotoristaDAO;
import br.com.paybus.modelo.Aluno;
import br.com.paybus.modelo.Cobrador;
import br.com.paybus.modelo.Motorista;
import br.com.paybus.utilitarios.CobradorRecyclerViewAdapter;
import br.com.paybus.utilitarios.MotoristaRecyclerViewAdapter;

public class ListaDeMotoristasActivity extends AppCompatActivity {

    public List<Motorista> listaMotoristas;
    private RecyclerView listaDeMotoristasRecyclerView;
    private MotoristaRecyclerViewAdapter motoristaRecyclerViewAdapter;
    public MotoristaDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_lista_de_motoristas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        criarRecyclerViewMotorista();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void filtro(String text){
        ArrayList<Motorista> novaLista = new ArrayList<Motorista>();
        for(Motorista motorista: listaMotoristas){
            if(motorista.getNomeCompleto().toLowerCase().contains(text.toLowerCase())){
                novaLista.add(motorista);
            }
        }
        motoristaRecyclerViewAdapter.setFiltro(novaLista);
    }

    private void criarRecyclerViewMotorista() {

        listaMotoristas = new ArrayList<Motorista>();
        dao = new MotoristaDAO(this);
        listaMotoristas = dao.listarMotoristas();
        listaDeMotoristasRecyclerView = findViewById(R.id.motoristaRecyclerView);
        listaDeMotoristasRecyclerView.setHasFixedSize(true);
        listaDeMotoristasRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        motoristaRecyclerViewAdapter = new MotoristaRecyclerViewAdapter(listaMotoristas, this);
        listaDeMotoristasRecyclerView.setAdapter(motoristaRecyclerViewAdapter);

    }

    public void irParaCadastrarMotorista(View view){
        finish();
        startActivity(new Intent(ListaDeMotoristasActivity.this, CadastrarMotoristaActivity.class));
    }

}
