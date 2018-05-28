package br.com.paybus.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        pdfView=(PDFView)findViewById(R.id.pdfView);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            file=new File(bundle.getString("path",""));
        }

        pdfView.fromFile(file).enableSwipe(true).swipeHorizontal(false).enableDoubletap(true).enableAntialiasing(true).load();
    }
}
