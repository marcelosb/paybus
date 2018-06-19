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
import br.com.paybus.modelo.Cobrador;
import br.com.paybus.utilitarios.CobradorRecyclerViewAdapter;

public class ListaDeCobradoresActivity extends AppCompatActivity {

    public List<Cobrador> listaCobradores;
    private CobradorRecyclerViewAdapter cobradorRecyclerViewAdapter;
    private RecyclerView listaDeCobradoresRecyclerView;
    public CobradorDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_lista_de_cobradores);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        criarRecyclerViewCobrador();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                startActivity(new Intent(ListaDeCobradoresActivity.this, UsuariosActivity.class));
                ListaDeCobradoresActivity.this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ListaDeCobradoresActivity.this, UsuariosActivity.class));
        ListaDeCobradoresActivity.this.finish();
    }

    private void criarRecyclerViewCobrador() {
        listaCobradores = new ArrayList<Cobrador>();
        dao = new CobradorDAO(this);
        listaCobradores = dao.listarCobradores();
        listaDeCobradoresRecyclerView = findViewById(R.id.cobradorRecyclerView);
        listaDeCobradoresRecyclerView.setHasFixedSize(true);
        listaDeCobradoresRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cobradorRecyclerViewAdapter = new CobradorRecyclerViewAdapter(listaCobradores, this);
        listaDeCobradoresRecyclerView.setAdapter(cobradorRecyclerViewAdapter);
    }

    public void irParaCadastrarCobrador(View view){
        startActivity(new Intent(ListaDeCobradoresActivity.this, CadastrarCobradorActivity.class));
        ListaDeCobradoresActivity.this.finish();
    }


}
