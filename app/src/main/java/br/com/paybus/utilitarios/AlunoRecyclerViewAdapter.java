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

import java.util.List;

import br.com.paybus.R;
import br.com.paybus.activitys.EditarAlunoActivity;
import br.com.paybus.activitys.ListaDeAlunosActivity;
import br.com.paybus.dao.AlunoDAO;
import br.com.paybus.modelo.Aluno;


public class AlunoRecyclerViewAdapter extends RecyclerView.Adapter<AlunoRecyclerViewAdapter.ViewHolderAluno>   {

    List<Aluno> listaDeAlunos;
    Context context;

    public AlunoRecyclerViewAdapter(List<Aluno> listaDeAlunos, Context context) {
        this.listaDeAlunos = listaDeAlunos;
        this.context = context;
    }

    @NonNull
    @Override
    public AlunoRecyclerViewAdapter.ViewHolderAluno onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_lista_alunos_recycler_view, parent, false);
        return new ViewHolderAluno(view);
    }


    public class ViewHolderAluno extends RecyclerView.ViewHolder{

        public TextView textoNomeAluno;
        public TextView textoInstituicaoAluno;
        public TextView textoMenuNever;

        public ViewHolderAluno(View itemView) {
            super(itemView);

            textoNomeAluno = itemView.findViewById(R.id.textoNomeAluno);
            textoInstituicaoAluno = itemView.findViewById(R.id.textoInstituicaoAluno);
            textoMenuNever = itemView.findViewById(R.id.textoMenuNever);
        }
    }



    @Override
    public void onBindViewHolder(@NonNull final AlunoRecyclerViewAdapter.ViewHolderAluno holder, final int position) {
        holder.textoNomeAluno.setText(listaDeAlunos.get(position).getNomeCompleto());
        holder.textoInstituicaoAluno.setText(listaDeAlunos.get(position).getInstituicao());

        holder.textoMenuNever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenuNever = new PopupMenu(context, holder.textoMenuNever);
                popupMenuNever.inflate(R.menu.menu_popup_aluno);
                popupMenuNever.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.popup_editar:
                                Intent intent = new Intent(context, EditarAlunoActivity.class);
                                intent.putExtra("id_aluno", listaDeAlunos.get(position).getId());
                                intent.putExtra("nome_completo_aluno", listaDeAlunos.get(position).getNomeCompleto());
                                intent.putExtra("instituicao_aluno", listaDeAlunos.get(position).getInstituicao());
                                intent.putExtra("cpf_aluno", listaDeAlunos.get(position).getCpf());
                                intent.putExtra("endereco_aluno", listaDeAlunos.get(position).getEndereco());
                                intent.putExtra("telefone_aluno", listaDeAlunos.get(position).getTelefone());
                                intent.putExtra("email_aluno", listaDeAlunos.get(position).getEmail());

                                ((ListaDeAlunosActivity) context).finish();
                                context.startActivity(intent);

                                break;

                            case R.id.popup_deletar:
                                AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(context);
                                caixaDeDialogo.setCancelable(false);
                                caixaDeDialogo.setTitle("Confirmar");
                                caixaDeDialogo.setMessage("Deseja realmente excluir "+listaDeAlunos.get(position).getNomeCompleto()+
                                        "? Que Estuda no(a) "+listaDeAlunos.get(position).getInstituicao()+"?");
                                caixaDeDialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                                caixaDeDialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                                        AlunoDAO dao = new AlunoDAO(context);
                                        dao.deletarAluno(listaDeAlunos.get(position));

                                        ((ListaDeAlunosActivity) context).finish();
                                        context.startActivity(new Intent(context, ListaDeAlunosActivity.class));

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


        // aqui faz a parte do clique
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick( View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.textoNomeAluno);
                popupMenu.inflate(R.menu.menu_popup_aluno);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.popup_editar:
                                Intent intent = new Intent(context, EditarAlunoActivity.class);
                                intent.putExtra("id_aluno", listaDeAlunos.get(position).getId());
                                intent.putExtra("nome_completo_aluno", listaDeAlunos.get(position).getNomeCompleto());
                                intent.putExtra("instituicao_aluno", listaDeAlunos.get(position).getInstituicao());
                                intent.putExtra("cpf_aluno", listaDeAlunos.get(position).getCpf());
                                intent.putExtra("endereco_aluno", listaDeAlunos.get(position).getEndereco());
                                intent.putExtra("telefone_aluno", listaDeAlunos.get(position).getTelefone());
                                intent.putExtra("email_aluno", listaDeAlunos.get(position).getEmail());

                                ((ListaDeAlunosActivity) context).finish();
                                context.startActivity(intent);

                            break;

                            case R.id.popup_deletar:
                                AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(context);
                                caixaDeDialogo.setCancelable(false);
                                caixaDeDialogo.setTitle("Confirmar");
                                caixaDeDialogo.setMessage("Deseja realmente excluir "+listaDeAlunos.get(position).getNomeCompleto()+
                                        "? Que Estuda na "+listaDeAlunos.get(position).getInstituicao()+"?");
                                caixaDeDialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                                caixaDeDialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                                        AlunoDAO dao = new AlunoDAO(context);
                                        dao.deletarAluno(listaDeAlunos.get(position));

                                        ((ListaDeAlunosActivity) context).finish();
                                        context.startActivity(new Intent(context, ListaDeAlunosActivity.class));

                                        /**
                                        notifyItemRemoved(position);
                                        List<Aluno> listaAlunos = dao.listarAlunos();
                                        listaDeAlunos = listaAlunos;
                                        notifyDataSetChanged();*/
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
                popupMenu.show();
                return true;
            }

        });
    }


    @Override
    public int getItemCount() {
        return listaDeAlunos.size();
    }

    public void setFiltro(List<Aluno> listaAlunos){
        listaDeAlunos = listaAlunos;
        notifyDataSetChanged();
    }



}
