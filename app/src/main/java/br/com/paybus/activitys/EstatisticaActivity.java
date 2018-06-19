package br.com.paybus.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import br.com.paybus.R;
import br.com.paybus.acesso_banco_de_dados.Coluna;

public class EstatisticaActivity extends AppCompatActivity {

    PieChart pieChart;
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_estatistica);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pieChart = findViewById(R.id.pieChart);
        pieChart.getDescription().setEnabled(false);
        pieChart.setUsePercentValues(true);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.99f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> valores = new ArrayList<>();
        valores.add(new PieEntry( ListaDeAlunosPagamentos.numeroDePagadores, "Pagadores"));
        valores.add(new PieEntry(ListaDeAlunosPagamentos.numeroDeDevedores, "Devedores"));

        pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic);

        PieDataSet dataSet = new PieDataSet(valores, "Alunos");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        pieChart.setData(data);


        barChart = findViewById(R.id.barChart);
        barChart.getDescription().setEnabled(false);

        setDados(10);

        barChart.setFitBars(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                startActivity(new Intent(EstatisticaActivity.this, ListaDeAlunosPagamentos.class));
                EstatisticaActivity.this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setDados(int dados){
        ArrayList<BarEntry> listaValores = new ArrayList<>();
        for(int i=0; i<dados; i++){
            float valor = (float) (Math.random()*100);
            listaValores.add(new BarEntry(i, (int) valor));
        }

        BarDataSet barDataSet = new BarDataSet(listaValores, "Data Set");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setDrawValues(true);

        BarData barData = new BarData(barDataSet);

        barChart.setData(barData);
        barChart.invalidate();
        barChart.animateY(500);

    }




}
