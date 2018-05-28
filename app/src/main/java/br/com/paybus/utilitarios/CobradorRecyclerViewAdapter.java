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
import br.com.paybus.activitys.EditarCobradorActivity;
import br.com.paybus.activitys.ListaDeAlunosActivity;
import br.com.paybus.activitys.ListaDeCobradoresActivity;
import br.com.paybus.dao.AlunoDAO;
import br.com.paybus.dao.CobradorDAO;
import br.com.paybus.modelo.Aluno;
import br.com.paybus.modelo.Cobrador;


public class CobradorRecyclerViewAdapter extends RecyclerView.Adapter<CobradorRecyclerViewAdapter.ViewHolderCobrador>   {

    List<Cobrador> listaDeCobradores;
    Context context;

    public CobradorRecyclerViewAdapter(List<Cobrador> listaDeCobradores, Context context) {
        this.listaDeCobradores = listaDeCobradores;
        this.context = context;
    }

    @NonNull
    @Override
    public CobradorRecyclerViewAdapter.ViewHolderCobrador onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_lista_cobradores_recycler_view, parent, false);
        return new ViewHolderCobrador(view);
    }


    public class ViewHolderCobrador extends RecyclerView.ViewHolder{

        public TextView textoNomeCobrador;
        public TextView textoInstituicaoCobrador;
        public TextView textoMenuNeverCobrador;

        public ViewHolderCobrador(View itemView) {
            super(itemView);

            textoNomeCobrador = itemView.findViewById(R.id.textoNomeCobrador);
            textoInstituicaoCobrador = itemView.findViewById(R.id.textoInstituicaoCobrador);
            textoMenuNeverCobrador = itemView.findViewById(R.id.textoMenuNeverCobrador);
        }
    }



    @Override
    public void onBindViewHolder(@NonNull final CobradorRecyclerViewAdapter.ViewHolderCobrador holder, final int position) {
        holder.textoNomeCobrador.setText(listaDeCobradores.get(position).getNomeCompleto());
        holder.textoInstituicaoCobrador.setText(listaDeCobradores.get(position).getInstituicao());

        holder.textoMenuNeverCobrador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenuNever = new PopupMenu(context, holder.textoMenuNeverCobrador);
                popupMenuNever.inflate(R.menu.menu_popup_aluno);
                popupMenuNever.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.popup_editar:
                                Intent intent = new Intent(context, EditarCobradorActivity.class);
                                intent.putExtra("id_cobrador", listaDeCobradores.get(position).getId());
                                intent.putExtra("nome_completo_cobrador", listaDeCobradores.get(position).getNomeCompleto());
                                intent.putExtra("instituicao_cobrador", listaDeCobradores.get(position).getInstituicao());
                                intent.putExtra("cpf_cobrador", listaDeCobradores.get(position).getCpf());
                                intent.putExtra("endereco_cobrador", listaDeCobradores.get(position).getEndereco());
                                intent.putExtra("telefone_cobrador", listaDeCobradores.get(position).getTelefone());
                                intent.putExtra("email_cobrador", listaDeCobradores.get(position).getEmail());
                                context.startActivity(intent);
                                break;

                            case R.id.popup_deletar:
                                AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(context);
                                caixaDeDialogo.setCancelable(false);
                                caixaDeDialogo.setTitle("Confirmar");
                                caixaDeDialogo.setMessage("Deseja realmente excluir o(a) cobrador(a) "+listaDeCobradores.get(position).getNomeCompleto()+
                                        "? Que Estuda no(a) "+listaDeCobradores.get(position).getInstituicao()+"?");

                                caixaDeDialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });

                                caixaDeDialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                                        CobradorDAO dao = new CobradorDAO(context);
                                        dao.deletarCobrador(listaDeCobradores.get(position));
                                        context.startActivity(new Intent(context, ListaDeCobradoresActivity.class));
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
                PopupMenu popupMenu = new PopupMenu(context, holder.textoNomeCobrador);
                popupMenu.inflate(R.menu.menu_popup_aluno);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.popup_editar:

                                Intent intent = new Intent(context, EditarCobradorActivity.class);
                                intent.putExtra("id_cobrador", listaDeCobradores.get(position).getId());
                                intent.putExtra("nome_completo_cobrador", listaDeCobradores.get(position).getNomeCompleto());
                                intent.putExtra("instituicao_cobrador", listaDeCobradores.get(position).getInstituicao());
                                intent.putExtra("cpf_cobrador", listaDeCobradores.get(position).getCpf());
                                intent.putExtra("endereco_cobrador", listaDeCobradores.get(position).getEndereco());
                                intent.putExtra("telefone_cobrador", listaDeCobradores.get(position).getTelefone());
                                intent.putExtra("email_cobrador", listaDeCobradores.get(position).getEmail());

                                ((ListaDeCobradoresActivity) context).finish();
                                context.startActivity(intent);

                                break;
                            case R.id.popup_deletar:

                                AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(context);
                                caixaDeDialogo.setCancelable(false);
                                caixaDeDialogo.setTitle("Confirmar");
                                caixaDeDialogo.setMessage("Deseja realmente excluir o(a) cobrador(a) "+listaDeCobradores.get(position).getNomeCompleto()+
                                        "? Que Estuda no(a) "+listaDeCobradores.get(position).getInstituicao()+"?");

                                caixaDeDialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });

                                caixaDeDialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                                        CobradorDAO dao = new CobradorDAO(context);
                                        dao.deletarCobrador(listaDeCobradores.get(position));
                                        ((ListaDeCobradoresActivity) context).finish();
                                        context.startActivity(new Intent(context, ListaDeCobradoresActivity.class));
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
        return listaDeCobradores.size();
    }

    public void setFiltro(List<Cobrador> listaCobradores){
        listaDeCobradores = listaCobradores;
        notifyDataSetChanged();
    }



}
