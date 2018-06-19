package br.com.paybus.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

import br.com.paybus.R;

public class ViewPDFActivity extends AppCompatActivity {

    private PDFView pdfView;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_view_pdf);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Comprovante de Pagamento");

        pdfView=(PDFView)findViewById(R.id.pdfView);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            file=new File(bundle.getString("path",""));
        }

        pdfView.fromFile(file).enableSwipe(true).swipeHorizontal(false).enableDoubletap(true).enableAntialiasing(true).load();
    }

    public void botaoVoltarDaTelaViewPdfParaListaDeAlunosPagamentos(View view){
        startActivity(new Intent(ViewPDFActivity.this, ListaDeAlunosPagamentos.class));
        ViewPDFActivity.this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                startActivity(new Intent(ViewPDFActivity.this, ListaDeAlunosPagamentos.class));
                ViewPDFActivity.this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ViewPDFActivity.this, ListaDeAlunosPagamentos.class));
        ViewPDFActivity.this.finish();
    }


}
