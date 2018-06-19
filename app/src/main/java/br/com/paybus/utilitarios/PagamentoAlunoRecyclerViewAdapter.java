package br.com.paybus.utilitarios;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
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
import br.com.paybus.activitys.ListaDeAlunosPagamentos;
import br.com.paybus.activitys.ReceberPagamentoActivity;
import br.com.paybus.dao.PagamentoDAO;
import br.com.paybus.modelo.Pagamento;


public class PagamentoAlunoRecyclerViewAdapter extends RecyclerView.Adapter<PagamentoAlunoRecyclerViewAdapter.ViewHolderPagamentoAluno>   {

    public static boolean editarPagamento = false;

    List<Pagamento> listaDePagamentosAlunos;
    Context context;

    public PagamentoAlunoRecyclerViewAdapter(List<Pagamento> listaDePagamentosAlunos, Context context) {
        this.listaDePagamentosAlunos = listaDePagamentosAlunos;
        this.context = context;
    }

    @NonNull
    @Override
    public PagamentoAlunoRecyclerViewAdapter.ViewHolderPagamentoAluno onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_lista_pagamentos_alunos_recycler_view, parent, false);
        return new ViewHolderPagamentoAluno(view);
    }

    public class ViewHolderPagamentoAluno extends RecyclerView.ViewHolder{

        public TextView textoNomeAlunoPagamento;
        public TextView textoInstituicaoAlunoPagamento;
        public TextView textoMenuNeverAlunoPagamento;
        public ImageView imagemAlunoPagamento;

        public ViewHolderPagamentoAluno(View itemView) {
            super(itemView);
            textoNomeAlunoPagamento = itemView.findViewById(R.id.textoNomeAlunoPagamento);
            textoInstituicaoAlunoPagamento = itemView.findViewById(R.id.textoInstituicaoAlunoPagamento);
            textoMenuNeverAlunoPagamento = itemView.findViewById(R.id.textoMenuNeverAlunoPagamento);
            imagemAlunoPagamento = itemView.findViewById(R.id.imagemAlunoPagamento);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final PagamentoAlunoRecyclerViewAdapter.ViewHolderPagamentoAluno holder,final int position) {

        if(listaDePagamentosAlunos.get(position).getStatus().equals("Pago")){
            holder.textoNomeAlunoPagamento.setText(listaDePagamentosAlunos.get(position).getNomeDoAluno());
            holder.textoInstituicaoAlunoPagamento.setText(listaDePagamentosAlunos.get(position).getInstituicaoDeEnsinoDoAluno());
            holder.textoNomeAlunoPagamento.setTextColor(Color.parseColor("#016601"));
            holder.textoInstituicaoAlunoPagamento.setTextColor(Color.parseColor("#016601"));
            holder.textoMenuNeverAlunoPagamento.setTextColor(Color.parseColor("#016601"));
            holder.imagemAlunoPagamento.setImageResource(R.drawable.aluno_pagamento_pago);

            holder.textoMenuNeverAlunoPagamento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenuNever = new PopupMenu(context, holder.textoMenuNeverAlunoPagamento);
                    popupMenuNever.inflate(R.menu.menu_popup_pagamento_aluno);
                    popupMenuNever.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.popup_editar_pagamento_aluno:
                                    editarPagamento = true;
                                    Intent intent = new Intent(context, ReceberPagamentoActivity.class);
                                    intent.putExtra("id_pagamento", listaDePagamentosAlunos.get(position).getId());
                                    intent.putExtra("mes_e_ano_do_pagamento", listaDePagamentosAlunos.get(position).getMesEAnoDoPagamento());
                                    intent.putExtra("data_do_vencimento", listaDePagamentosAlunos.get(position).getDataDoVencimento());
                                    intent.putExtra("nome_aluno_pagamento", listaDePagamentosAlunos.get(position).getNomeDoAluno());
                                    intent.putExtra("instituicao_aluno_pagamento", listaDePagamentosAlunos.get(position).getInstituicaoDeEnsinoDoAluno());
                                    intent.putExtra("cobrador_selecao", listaDePagamentosAlunos.get(position).getNomeDoCobrador());
                                    intent.putExtra("motorista_selecao", listaDePagamentosAlunos.get(position).getNomeDoMotorista());
                                    intent.putExtra("valor_pagamento", listaDePagamentosAlunos.get(position).getValorDoPagamento());
                                    intent.putExtra("observacao_pagamento", listaDePagamentosAlunos.get(position).getObservacao());
                                    context.startActivity(intent);
                                    ((ListaDeAlunosPagamentos) context).finish();
                                    break;
                                case R.id.popup_deletar_pagamento_aluno:
                                    AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(context);
                                    caixaDeDialogo.setCancelable(false);
                                    caixaDeDialogo.setTitle("Confirmar");
                                    caixaDeDialogo.setMessage("Deseja realmente excluir o(a) aluno(a): "+listaDePagamentosAlunos.get(position).getNomeDoAluno()+
                                            "?\n\nQue já realizou o pagamento do mês, e estuda no(a) "+listaDePagamentosAlunos.get(position).getInstituicaoDeEnsinoDoAluno()+"?");
                                    caixaDeDialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                        @Override public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });
                                    caixaDeDialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                        @Override public void onClick(DialogInterface dialogInterface, int i) {
                                            PagamentoDAO dao = new PagamentoDAO(context);
                                            dao.deletarPagamento(listaDePagamentosAlunos.get(position));
                                            context.startActivity(new Intent(context, ListaDeAlunosPagamentos.class));
                                            ((ListaDeAlunosPagamentos) context).finish();
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


        }else if(listaDePagamentosAlunos.get(position).getStatus().equals("Não Pago")){
            holder.textoNomeAlunoPagamento.setText(listaDePagamentosAlunos.get(position).getNomeDoAluno());
            holder.textoInstituicaoAlunoPagamento.setText(listaDePagamentosAlunos.get(position).getInstituicaoDeEnsinoDoAluno());
            holder.textoNomeAlunoPagamento.setTextColor(Color.parseColor("#B20000"));
            holder.textoInstituicaoAlunoPagamento.setTextColor(Color.parseColor("#B20000"));
            holder.textoMenuNeverAlunoPagamento.setTextColor(Color.parseColor("#B20000"));
            holder.imagemAlunoPagamento.setImageResource(R.drawable.aluno_pagamento_nao_pago);

            holder.textoNomeAlunoPagamento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editarPagamento = false;
                    Intent intent = new Intent(context, ReceberPagamentoActivity.class);
                    intent.putExtra("id_pagamento", listaDePagamentosAlunos.get(position).getId());
                    intent.putExtra("mes_e_ano_do_pagamento", listaDePagamentosAlunos.get(position).getMesEAnoDoPagamento());
                    intent.putExtra("data_do_vencimento", listaDePagamentosAlunos.get(position).getDataDoVencimento());
                    intent.putExtra("nome_aluno_pagamento", listaDePagamentosAlunos.get(position).getNomeDoAluno());
                    intent.putExtra("instituicao_aluno_pagamento", listaDePagamentosAlunos.get(position).getInstituicaoDeEnsinoDoAluno());
                    intent.putExtra("valor_pagamento", listaDePagamentosAlunos.get(position).getValorDoPagamento());
                    intent.putExtra("observacao_pagamento", listaDePagamentosAlunos.get(position).getObservacao());
                    context.startActivity(intent);
                    ((ListaDeAlunosPagamentos) context).finish();
                }

            });

            holder.textoInstituicaoAlunoPagamento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editarPagamento = false;
                    Intent intent = new Intent(context, ReceberPagamentoActivity.class);
                    intent.putExtra("id_pagamento", listaDePagamentosAlunos.get(position).getId());
                    intent.putExtra("mes_e_ano_do_pagamento", listaDePagamentosAlunos.get(position).getMesEAnoDoPagamento());
                    intent.putExtra("data_do_vencimento", listaDePagamentosAlunos.get(position).getDataDoVencimento());
                    intent.putExtra("nome_aluno_pagamento", listaDePagamentosAlunos.get(position).getNomeDoAluno());
                    intent.putExtra("instituicao_aluno_pagamento", listaDePagamentosAlunos.get(position).getInstituicaoDeEnsinoDoAluno());
                    intent.putExtra("valor_pagamento", listaDePagamentosAlunos.get(position).getValorDoPagamento());
                    intent.putExtra("observacao_pagamento", listaDePagamentosAlunos.get(position).getObservacao());
                    context.startActivity(intent);
                    ((ListaDeAlunosPagamentos) context).finish();
                }
            });

            holder.imagemAlunoPagamento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editarPagamento = false;
                    Intent intent = new Intent(context, ReceberPagamentoActivity.class);
                    intent.putExtra("id_pagamento", listaDePagamentosAlunos.get(position).getId());
                    intent.putExtra("mes_e_ano_do_pagamento", listaDePagamentosAlunos.get(position).getMesEAnoDoPagamento());
                    intent.putExtra("data_do_vencimento", listaDePagamentosAlunos.get(position).getDataDoVencimento());
                    intent.putExtra("nome_aluno_pagamento", listaDePagamentosAlunos.get(position).getNomeDoAluno());
                    intent.putExtra("instituicao_aluno_pagamento", listaDePagamentosAlunos.get(position).getInstituicaoDeEnsinoDoAluno());
                    intent.putExtra("valor_pagamento", listaDePagamentosAlunos.get(position).getValorDoPagamento());
                    intent.putExtra("observacao_pagamento", listaDePagamentosAlunos.get(position).getObservacao());
                    context.startActivity(intent);
                    ((ListaDeAlunosPagamentos) context).finish();
                }
            });

            holder.textoMenuNeverAlunoPagamento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenuNever = new PopupMenu(context, holder.textoMenuNeverAlunoPagamento);
                    popupMenuNever.inflate(R.menu.menu_popup_pagamento_aluno);
                    popupMenuNever.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.popup_editar_pagamento_aluno:
                                    editarPagamento = false;
                                    Intent intent = new Intent(context, ReceberPagamentoActivity.class);
                                    intent.putExtra("id_pagamento", listaDePagamentosAlunos.get(position).getId());
                                    intent.putExtra("mes_e_ano_do_pagamento", listaDePagamentosAlunos.get(position).getMesEAnoDoPagamento());
                                    intent.putExtra("data_do_vencimento", listaDePagamentosAlunos.get(position).getDataDoVencimento());
                                    intent.putExtra("nome_aluno_pagamento", listaDePagamentosAlunos.get(position).getNomeDoAluno());
                                    intent.putExtra("instituicao_aluno_pagamento", listaDePagamentosAlunos.get(position).getInstituicaoDeEnsinoDoAluno());
                                    intent.putExtra("valor_pagamento", listaDePagamentosAlunos.get(position).getValorDoPagamento());
                                    intent.putExtra("observacao_pagamento", listaDePagamentosAlunos.get(position).getObservacao());
                                    context.startActivity(intent);
                                    ((ListaDeAlunosPagamentos) context).finish();
                                    break;
                                case R.id.popup_deletar_pagamento_aluno:
                                    AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(context);
                                    caixaDeDialogo.setCancelable(false);
                                    caixaDeDialogo.setTitle("Confirmar");
                                    caixaDeDialogo.setMessage("Deseja realmente excluir o pagamento do(a) aluno(a): "+listaDePagamentosAlunos.get(position).getNomeDoAluno()+
                                            "?\n\nQue estuda no(a) "+listaDePagamentosAlunos.get(position).getInstituicaoDeEnsinoDoAluno()+"?");
                                    caixaDeDialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                        @Override public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });
                                    caixaDeDialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                        @Override public void onClick(DialogInterface dialogInterface, int i) {
                                            PagamentoDAO dao = new PagamentoDAO(context);
                                            dao.deletarPagamento(listaDePagamentosAlunos.get(position));
                                            context.startActivity(new Intent(context, ListaDeAlunosPagamentos.class));
                                            ((ListaDeAlunosPagamentos) context).finish();
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

        }


    }


    @Override
    public int getItemCount() {
        return listaDePagamentosAlunos.size();
    }

    public void setFiltro(List<Pagamento> listaPagamentos){
        listaDePagamentosAlunos = listaPagamentos;
        notifyDataSetChanged();
    }


}
