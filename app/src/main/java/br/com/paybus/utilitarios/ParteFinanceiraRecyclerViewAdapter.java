package br.com.paybus.utilitarios;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.paybus.R;
import br.com.paybus.activitys.EditarAlunoActivity;
import br.com.paybus.activitys.ListaDeAlunosActivity;
import br.com.paybus.dao.AlunoDAO;
import br.com.paybus.dao.PagamentoDAO;
import br.com.paybus.modelo.Aluno;
import br.com.paybus.modelo.MesDoPagamento;
import br.com.paybus.modelo.Pagamento;


public class ParteFinanceiraRecyclerViewAdapter extends RecyclerView.Adapter<ParteFinanceiraRecyclerViewAdapter.ViewHolderParteFinanceira>   {

    public static Double valorTotalArrecadado;
    public PagamentoDAO pagamentoDAO;
    public List<MesDoPagamento> listaMesesPagamentosFinanceiro;
    Context context;

    public ParteFinanceiraRecyclerViewAdapter(List<MesDoPagamento> listaMesesPagamentosFinanceiro, Context context) {
        this.listaMesesPagamentosFinanceiro = listaMesesPagamentosFinanceiro;
        this.context = context;
    }

    @NonNull
    @Override
    public ParteFinanceiraRecyclerViewAdapter.ViewHolderParteFinanceira onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_lista_parte_financeira_recycler_view, parent, false);
        return new ViewHolderParteFinanceira(view);
    }


    public class ViewHolderParteFinanceira extends RecyclerView.ViewHolder{

        public TextView textoMesParteFinanceira;
        public TextView textoValorArrecadaParteFinanceira;

        public ViewHolderParteFinanceira(View itemView) {
            super(itemView);

            textoMesParteFinanceira = itemView.findViewById(R.id.textoMesParteFinanceira);
            textoValorArrecadaParteFinanceira = itemView.findViewById(R.id.textoValorArrecadaParteFinanceira);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull final ParteFinanceiraRecyclerViewAdapter.ViewHolderParteFinanceira holder, final int position) {
        holder.textoMesParteFinanceira.setText(listaMesesPagamentosFinanceiro.get(position).getMesEAnoDoPagamento());

        pagamentoDAO = new PagamentoDAO(context);
        pagamentoDAO.listarPagamentosDosAlunosPorMes(listaMesesPagamentosFinanceiro.get(position).getMesEAnoDoPagamento());
        holder.textoValorArrecadaParteFinanceira.setText("Total Arrecadado: R$ "+valorTotalArrecadado);

        valorTotalArrecadado = 0.0;
    }


    @Override
    public int getItemCount() {
        return listaMesesPagamentosFinanceiro.size();
    }

    public void setFiltro(List<MesDoPagamento> listaPag){
        listaMesesPagamentosFinanceiro = listaPag;
        notifyDataSetChanged();
    }



}
