package br.com.paybus.activitys;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.paybus.R;

public class EditarCobradorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_editar_cobrador);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#31a9b8")));

    }
}
