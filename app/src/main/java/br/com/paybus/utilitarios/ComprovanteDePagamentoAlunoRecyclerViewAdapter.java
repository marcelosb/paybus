package br.com.paybus.utilitarios;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.List;

import br.com.paybus.R;
import br.com.paybus.activitys.EditarAlunoActivity;
import br.com.paybus.activitys.ListaDeAlunosActivity;
import br.com.paybus.activitys.ListaDeAlunosPagamentos;
import br.com.paybus.activitys.ReceberPagamentoActivity;
import br.com.paybus.activitys.VisualizarComprovanteDePagamentoAlunoActivity;
import br.com.paybus.dao.AlunoDAO;
import br.com.paybus.modelo.Aluno;
import br.com.paybus.modelo.Pagamento;
import br.com.paybus.modelo.Pagamentos;


public class ComprovanteDePagamentoAlunoRecyclerViewAdapter extends RecyclerView.Adapter<ComprovanteDePagamentoAlunoRecyclerViewAdapter.ViewHolderComprovanteDePagamentoAluno>   {

    List<Pagamento> listaDeComprovantesDePagamentos;
    Context context;

    public ComprovanteDePagamentoAlunoRecyclerViewAdapter(List<Pagamento> listaDeComprovantesDePagamentos, Context context) {
        this.listaDeComprovantesDePagamentos = listaDeComprovantesDePagamentos;
        this.context = context;
    }

    @NonNull
    @Override
    public ComprovanteDePagamentoAlunoRecyclerViewAdapter.ViewHolderComprovanteDePagamentoAluno onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_lista_comprovantes_de_pagamentos_alunos_recycler_view, parent, false);
        return new ViewHolderComprovanteDePagamentoAluno(view);
    }

    public class ViewHolderComprovanteDePagamentoAluno extends RecyclerView.ViewHolder{

        public TextView textoMesComprovanteDePagamento;

        public ViewHolderComprovanteDePagamentoAluno(View itemView) {
            super(itemView);

            textoMesComprovanteDePagamento = itemView.findViewById(R.id.textoMesComprovanteDePagamento);
        }
    }

    public static String mesEAnoCP;
    public static String motoristaCP;

    @Override
    public void onBindViewHolder(@NonNull final ComprovanteDePagamentoAlunoRecyclerViewAdapter.ViewHolderComprovanteDePagamentoAluno holder, final int position) {
        holder.textoMesComprovanteDePagamento.setText(listaDeComprovantesDePagamentos.get(position).getMesEAnoDoPagamento());

        mesEAnoCP = holder.textoMesComprovanteDePagamento.getText().toString();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //motoristaCP = listaDeComprovantesDePagamentos.get(position).getNomeDoMotorista().toString();

                Intent intent = new Intent(context, VisualizarComprovanteDePagamentoAlunoActivity.class);
                intent.putExtra("mes_e_ano_do_comprovante_de_pagamento", listaDeComprovantesDePagamentos.get(position).getMesEAnoDoPagamento());
                intent.putExtra("motorista_comprovante_de_pagamento", listaDeComprovantesDePagamentos.get(position).getNomeDoMotorista());
                intent.putExtra("cobrador_comprovante_de_pagamento", listaDeComprovantesDePagamentos.get(position).getNomeDoCobrador());
                intent.putExtra("estudante_comprovante_de_pagamento", listaDeComprovantesDePagamentos.get(position).getNomeDoAluno());
                intent.putExtra("instituicao_estudante_comprovante_de_pagamento", listaDeComprovantesDePagamentos.get(position).getInstituicaoDeEnsinoDoAluno());
                intent.putExtra("data_do_pagamento_comprovante_de_pagamento", listaDeComprovantesDePagamentos.get(position).getDataDoPagamento());
                intent.putExtra("data_do_vencimento_comprovante_de_pagamento", listaDeComprovantesDePagamentos.get(position).getDataDoVencimento());
                intent.putExtra("valor_comprovante_de_pagamento", listaDeComprovantesDePagamentos.get(position).getValorDoPagamento());
                intent.putExtra("observacao_comprovante_de_pagamento", listaDeComprovantesDePagamentos.get(position).getObservacao());
                //((ListaDeAlunosPagamentos) context).finish();
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return listaDeComprovantesDePagamentos.size();
    }

    public void setFiltro(List<Pagamento> listaComprovantes){
        listaDeComprovantesDePagamentos = listaComprovantes;
        notifyDataSetChanged();
    }



}
