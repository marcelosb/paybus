package br.com.paybus.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.github.barteksc.pdfviewer.PDFView;

import br.com.paybus.R;

public class ViewPDFAjudaAdminActivity extends AppCompatActivity {

    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_view_pdf_ajuda_admin);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pdfView = findViewById(R.id.viewPdfAjudaAdmin);
        pdfView.fromAsset("ajuda_admin.pdf").load();
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
                ViewPDFAjudaAdminActivity.this.finish();
                return true;
            case R.id.menu_ajuda_sobre:
                startActivity(new Intent(ViewPDFAjudaAdminActivity.this, SobreActivity.class));
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
