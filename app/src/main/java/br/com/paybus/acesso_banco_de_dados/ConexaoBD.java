package br.com.paybus.acesso_banco_de_dados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexaoBD extends SQLiteOpenHelper{

    private static final String BANCO_DE_DADOS = "bd_paybus";
    private static final int VERSAO = 6;

    public ConexaoBD(Context context) {
        super(context, BANCO_DE_DADOS,null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Tabela.admninistrador());
        db.execSQL(Tabela.cadastrarAdmninistrador());
        db.execSQL(Tabela.aluno());
        db.execSQL(Tabela.cobrador());
        db.execSQL(Tabela.motorista());
        db.execSQL(Tabela.mesDoPagamento());
        db.execSQL(Tabela.pagamento());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Tabela.ADMINISTRADOR);
        db.execSQL("DROP TABLE IF EXISTS "+Tabela.ALUNO);
        db.execSQL("DROP TABLE IF EXISTS "+Tabela.COBRADOR);
        db.execSQL("DROP TABLE IF EXISTS "+Tabela.MOTORISTA);
        db.execSQL("DROP TABLE IF EXISTS "+Tabela.MES_DO_PAGAMENTO);
        db.execSQL("DROP TABLE IF EXISTS "+Tabela.PAGAMENTO);
        this.onCreate(db);
    }

}
