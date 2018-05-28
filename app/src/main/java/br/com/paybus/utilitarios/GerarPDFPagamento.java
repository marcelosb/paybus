package br.com.paybus.utilitarios;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

import br.com.paybus.activitys.ViewPDFActivity;
import br.com.paybus.modelo.Pagamentos;

public class GerarPDFPagamento {
    private Context context;
    private File pastaPDF;
    private Document documentoPDF;
    private PdfWriter escreverPDF;
    private Paragraph paragrafo;

    private Font fonteTitulo= new Font(Font.FontFamily.TIMES_ROMAN,20,Font.BOLD);
    private Font fonteSubtitulo= new Font(Font.FontFamily.TIMES_ROMAN,18,Font.BOLD);
    private Font fonteTexto= new Font(Font.FontFamily.TIMES_ROMAN,16,Font.BOLD);
    //private Font fonteTextoAcima= new Font(Font.FontFamily.TIMES_ROMAN,16,Font.BOLD, BaseColor.RED);



    public GerarPDFPagamento(Context context) {
        this.context=context;
    }


    public void criarPDF(Pagamentos pagamento) {
        criarDocumentoVazio();
        addMetaDados();
        addTituloDocumento(pagamento.getDataDoPagamento());
        addTextoParagrafo("Motorista: "+pagamento.getMotorista());
        addTextoParagrafo("Cobrador: "+pagamento.getCobrador());
        addTextoParagrafo("Estudante: "+pagamento.getAluno());
        addTextoParagrafo("Data de Vencimento: "+pagamento.getDataDoVencimento());
        addTextoParagrafo("Valor do pagamento: "+pagamento.getValorDoPagamento().toString());
        addTextoParagrafo("Observação: "+pagamento.getObservacao());
        addParagrafosCentralizado("Muito Obrigado");
        fecharDocumento();
        visualizarPDF();
    }

    public void criarDocumentoVazio(){
        criarPasta();
        try{
            documentoPDF= new Document(PageSize.A4);
            escreverPDF=PdfWriter.getInstance(documentoPDF,new FileOutputStream(pastaPDF));
            documentoPDF.open();

        }catch(Exception e){
            Log.e("criarDocumentoVazio",e.toString());
        }
    }


    private void criarPasta(){
        File pasta =new File(Environment.getExternalStorageDirectory().toString(),"PDFPagamento");
        if(!pasta.exists()){
            pasta.mkdirs();
        }
        pastaPDF= new File(pasta,"ComprovantePagamento.pdf");
    }


    public void fecharDocumento(){
        documentoPDF.close();
    }


    public void addMetaDados(){
        documentoPDF.addTitle("Comprovante pagamento");
        documentoPDF.addSubject("comprovante mensal");
        documentoPDF.addAuthor("Aplicativo Pay Bus");
    }

    public void addTituloDocumento(String data){
        paragrafo=new Paragraph();
        addPequenosParagrafos(new Paragraph("Comprovante de Pagamento Mensal",fonteTitulo));
        addPequenosParagrafos(new Paragraph("Data de pagamento: "+data,fonteSubtitulo));
        paragrafo.setSpacingAfter(30);
        try{
            documentoPDF.add(paragrafo);

        }catch(Exception e){
            Log.e("addTituloDocumento",e.toString());
        }

    }

    public void addTextoParagrafo(String texto){
        try{
            paragrafo=new Paragraph(texto,fonteTexto);
            paragrafo.setSpacingAfter(10);
            paragrafo.setSpacingBefore(10);
            documentoPDF.add(paragrafo);

        }catch(Exception e){
            Log.e("addTextoParagrafo",e.toString());
        }
    }


    private void addPequenosParagrafos(Paragraph paragrafoAbaixo){
        paragrafoAbaixo.setAlignment(Element.ALIGN_CENTER);
        paragrafo.add(paragrafoAbaixo);
    }

    private void addParagrafosCentralizado(String texto){

        paragrafo=new Paragraph(texto,fonteSubtitulo);
        paragrafo.setAlignment(Element.ALIGN_CENTER);
        try{
            documentoPDF.add(paragrafo);

        }catch(Exception e){
            Log.e("addTituloDocumento",e.toString());
        }
    }


    public void visualizarPDF(){
        Intent intent = new Intent(context, ViewPDFActivity.class);
        intent.putExtra("path",pastaPDF.getAbsolutePath());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    /*
    public void visualizarPDF(Activity activity){
        if(pastaPDF.exists()){
            Uri uri = Uri.fromFile(pastaPDF);
            Intent intent = new Intent(context, ViewPDFActivity.class);
            intent.setDataAndType(uri,"aplication/pdf");
            try{
                activity.startActivity(intent);
            }catch(ActivityNotFoundException e){
                activity.startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=com.google.android.apps.pdfviewer&hl=pt_BR")));
                Toast.makeText(activity.getApplicationContext(),"Não contem aplicativo, baixar",Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(activity.getApplicationContext(),"Não arquivo",Toast.LENGTH_LONG).show();
        }

    }*/
}
