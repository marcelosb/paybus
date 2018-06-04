package br.com.paybus.utilitarios;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.List;

import br.com.paybus.R;
import br.com.paybus.activitys.EditarNovoMesDePagamentoActivity;
import br.com.paybus.activitys.ListaDeAlunosPagamentos;
import br.com.paybus.activitys.ListarPagamentos;
import br.com.paybus.dao.MesDoPagamentoDAO;
import br.com.paybus.dao.PagamentoDAO;
import br.com.paybus.modelo.MesDoPagamento;


public class PagamentoRecyclerViewAdapter extends RecyclerView.Adapter<PagamentoRecyclerViewAdapter.ViewHolderPagamento>   {

    public static String mesPagamento;
    public static String dataVencimento;

    List<MesDoPagamento> listaDeMesesDePagamentos;
    Context context;

    public PagamentoRecyclerViewAdapter(List<MesDoPagamento> listaDeMesesDePagamentos, Context context) {
        this.listaDeMesesDePagamentos = listaDeMesesDePagamentos;
        this.context = context;
    }

    @NonNull
    @Override
    public PagamentoRecyclerViewAdapter.ViewHolderPagamento onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_lista_meses_de_pagamentos_recycler_view, parent, false);
        return new ViewHolderPagamento(view);
    }


    public class ViewHolderPagamento extends RecyclerView.ViewHolder{

        public TextView textoMesDePagamento;
        public TextView textoMenuNeverPagamento;

        public ViewHolderPagamento(View itemView) {
            super(itemView);

            textoMesDePagamento = itemView.findViewById(R.id.textoMesDePagamento);
            textoMenuNeverPagamento = itemView.findViewById(R.id.textoMenuNeverPagamento);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull final PagamentoRecyclerViewAdapter.ViewHolderPagamento holder, final int position) {

        holder.textoMesDePagamento.setText(listaDeMesesDePagamentos.get(position).getMesEAnoDoPagamento());

        holder.textoMesDePagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mesPagamento = holder.textoMesDePagamento.getText().toString();
                dataVencimento = listaDeMesesDePagamentos.get(position).getDataDoVencimento();
                ((ListarPagamentos) context).finish();
                context.startActivity(new Intent(context, ListaDeAlunosPagamentos.class));
            }

        });

        holder.textoMenuNeverPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenuNever = new PopupMenu(context, holder.textoMenuNeverPagamento);
                popupMenuNever.inflate(R.menu.menu_popup_aluno);
                popupMenuNever.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.popup_editar:
                                Intent intent = new Intent(context, EditarNovoMesDePagamentoActivity.class);
                                intent.putExtra("id_mes_pagamento", listaDeMesesDePagamentos.get(position).getId());
                                mesPagamento = holder.textoMesDePagamento.getText().toString();
                                ((ListarPagamentos) context).finish();
                                context.startActivity(intent);

                                break;
                            case R.id.popup_deletar:
                                AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(context);
                                caixaDeDialogo.setCancelable(false);
                                caixaDeDialogo.setTitle("Confirmar");
                                caixaDeDialogo.setMessage("Deseja realmente excluir o pagamento do mês de "+listaDeMesesDePagamentos.get(position).getMesEAnoDoPagamento()+
                                         "?");
                                caixaDeDialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                                caixaDeDialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                                        MesDoPagamentoDAO dao = new MesDoPagamentoDAO(context);
                                        dao.deletarMesDoPagamento(listaDeMesesDePagamentos.get(position));

                                        PagamentoDAO pagamentoDAO = new PagamentoDAO(context);
                                        pagamentoDAO.deletarMesDePagamento(listaDeMesesDePagamentos.get(position).getMesEAnoDoPagamento());

                                        ((ListarPagamentos) context).finish();
                                        context.startActivity(new Intent(context, ListarPagamentos.class));
                                    }
                                });
                                caixaDeDialogo.create();
                                caixaDeDialogo.show();

                                break;

                            default:
                                break;
                        }

                        return true;
                    }
                });
                popupMenuNever.show();

            }

        });


        // aqui faz a parte do clique longo
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick( View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.textoMenuNeverPagamento);
                popupMenu.inflate(R.menu.menu_popup_aluno);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.popup_editar:

                                //Intent intent = new Intent(context, EditarAlunoActivity.class);


                                //context.startActivity(intent);

                                break;
                            case R.id.popup_deletar:

                                // AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(context);
                                // caixaDeDialogo.setCancelable(false);
                                //caixaDeDialogo.setTitle("Confirmar");
                                //caixaDeDialogo.setMessage("Deseja realmente excluir "+listaDeAlunos.get(position).getNomeCompleto()+
                                //         "? Que Estuda na "+listaDeAlunos.get(position).getInstituicao()+"?");

                                //caixaDeDialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                //  @Override public void onClick(DialogInterface dialogInterface, int i) {
                                //       dialogInterface.cancel();
                                //    }
                                //});

                                // caixaDeDialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                //   @Override public void onClick(DialogInterface dialogInterface, int i) {
                                //    AlunoDAO dao = new AlunoDAO(context);
                                //    dao.deletarAluno(listaDeAlunos.get(position));
                                //   context.startActivity(new Intent(context, ListaDeAlunosActivity.class));

                                //   }
                            //  });

                        // caixaDeDialogo.create();
                        //caixaDeDialogo.show();


                                break;
                             default:
                                 break;
                        }

                        return true;
                    }
                });
                popupMenu.show();

                return true;
            }

        });
    }


    @Override
    public int getItemCount() {
        return listaDeMesesDePagamentos.size();
    }

    public void setFiltro(List<MesDoPagamento> listaPagamentos){
        listaDeMesesDePagamentos = listaPagamentos;
        notifyDataSetChanged();
    }



}
