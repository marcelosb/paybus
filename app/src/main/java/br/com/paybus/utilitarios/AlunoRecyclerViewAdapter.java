package br.com.paybus.utilitarios;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.paybus.R;
import br.com.paybus.activitys.CadastrarAlunoActivity;
import br.com.paybus.activitys.EditarAlunoActivity;
import br.com.paybus.activitys.ListaDeAlunosActivity;
import br.com.paybus.activitys.PainelDeControleActivity;
import br.com.paybus.activitys.RelatoriosActivity;
import br.com.paybus.dao.AlunoDAO;
import br.com.paybus.modelo.Aluno;
import br.com.paybus.modelo.RelatorioMensal;


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

        public TextView textoNome;
        public TextView textoInstituicao;

        public ViewHolderAluno(View itemView) {
            super(itemView);

            textoNome = itemView.findViewById(R.id.textoNome);
            textoInstituicao = itemView.findViewById(R.id.textoInstituicao);
        }
    }



    @Override
    public void onBindViewHolder(@NonNull final AlunoRecyclerViewAdapter.ViewHolderAluno holder, final int position) {
        holder.textoNome.setText(listaDeAlunos.get(position).getNomeCompleto());
        holder.textoInstituicao.setText(listaDeAlunos.get(position).getInstituicao());

        // aqui faz a parte do clique
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick( View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.textoNome);
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
                                intent.putExtra("senha_aluno", listaDeAlunos.get(position).getSenha());
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
