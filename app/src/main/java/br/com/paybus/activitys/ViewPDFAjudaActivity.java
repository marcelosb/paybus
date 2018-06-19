package br.com.paybus.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.github.barteksc.pdfviewer.PDFView;

import br.com.paybus.R;
import br.com.paybus.utilitarios.Usuario;

public class ViewPDFAjudaActivity extends AppCompatActivity {

    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_view_pdf_ajuda_admin);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pdfView = findViewById(R.id.viewPdfAjudaAdmin);
        if(Usuario.tipo.equals("admin")){
            pdfView.fromAsset("ajuda_admin.pdf").load();
        }else if(Usuario.tipo.equals("aluno")){
            pdfView.fromAsset("ajuda_aluno.pdf").load();
        }else if(Usuario.tipo.equals("cobrador")){
            pdfView.fromAsset("ajuda_cobrador.pdf").load();
        }else if(Usuario.tipo.equals("motorista")){
            pdfView.fromAsset("ajuda_motorista.pdf").load();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tela_ajuda, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                if(Usuario.tipo.equals("admin")){
                    startActivity(new Intent(ViewPDFAjudaActivity.this, PainelDeControleAdminActivity.class));
                }else if(Usuario.tipo.equals("aluno")){
                    startActivity(new Intent(ViewPDFAjudaActivity.this, PainelDeControleAlunoActivity.class));
                }else if(Usuario.tipo.equals("cobrador") || Usuario.tipo.equals("motorista")){
                    startActivity(new Intent(ViewPDFAjudaActivity.this, PainelDeControleMotoristaCobradorActivity.class));
                }
                ViewPDFAjudaActivity.this.finish();
                return true;
            case R.id.menu_ajuda_sobre:
                startActivity(new Intent(ViewPDFAjudaActivity.this, SobreActivity.class));
                ViewPDFAjudaActivity.this.finish();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
