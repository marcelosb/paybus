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
import br.com.paybus.activitys.EditarMotoristaActivity;
import br.com.paybus.activitys.ListaDeMotoristasActivity;
import br.com.paybus.dao.MotoristaDAO;
import br.com.paybus.modelo.Motorista;


public class MotoristaRecyclerViewAdapter extends RecyclerView.Adapter<MotoristaRecyclerViewAdapter.ViewHolderMotorista>   {

    List<Motorista> listaDeMotoristas;
    Context context;

    public MotoristaRecyclerViewAdapter(List<Motorista> listaDeMotoristas, Context context) {
        this.listaDeMotoristas = listaDeMotoristas;
        this.context = context;
    }

    @NonNull
    @Override
    public MotoristaRecyclerViewAdapter.ViewHolderMotorista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_lista_motoristas_recycler_view, parent, false);
        return new ViewHolderMotorista(view);
    }


    public class ViewHolderMotorista extends RecyclerView.ViewHolder{

        public TextView textoNomeMotorista;
        public TextView textoContatoMotorista;
        public TextView textoMenuNeverMotorista;

        public ViewHolderMotorista(View itemView) {
            super(itemView);

            textoNomeMotorista = itemView.findViewById(R.id.textoNomeMotorista);
            textoContatoMotorista = itemView.findViewById(R.id.textoTelefoneMotorista);
            textoMenuNeverMotorista = itemView.findViewById(R.id.textoMenuNeverMotorista);
        }
    }



    @Override
    public void onBindViewHolder(@NonNull final MotoristaRecyclerViewAdapter.ViewHolderMotorista holder, final int position) {
        holder.textoNomeMotorista.setText(listaDeMotoristas.get(position).getNomeCompleto());
        holder.textoContatoMotorista.setText("Contato: "+listaDeMotoristas.get(position).getTelefone());

        holder.textoMenuNeverMotorista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenuNever = new PopupMenu(context, holder.textoMenuNeverMotorista);
                popupMenuNever.inflate(R.menu.menu_popup_aluno);
                popupMenuNever.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.popup_editar:
                                Intent intent = new Intent(context, EditarMotoristaActivity.class);
                                intent.putExtra("id_motorista", listaDeMotoristas.get(position).getId());
                                intent.putExtra("nome_completo_motorista", listaDeMotoristas.get(position).getNomeCompleto());
                                intent.putExtra("cpf_motorista", listaDeMotoristas.get(position).getCpf());
                                intent.putExtra("endereco_motorista", listaDeMotoristas.get(position).getEndereco());
                                intent.putExtra("cnh", listaDeMotoristas.get(position).getCnh());
                                intent.putExtra("telefone_motorista", listaDeMotoristas.get(position).getTelefone());
                                intent.putExtra("email_motorista", listaDeMotoristas.get(position).getEmail());

                                ((ListaDeMotoristasActivity) context).finish();
                                context.startActivity(intent);
                                break;

                            case R.id.popup_deletar:
                                AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(context);
                                caixaDeDialogo.setCancelable(false);
                                caixaDeDialogo.setTitle("Confirmar");
                                caixaDeDialogo.setMessage("Deseja realmente excluir o(a) motorista "+listaDeMotoristas.get(position).getNomeCompleto()+
                                        "?");

                                caixaDeDialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });

                                caixaDeDialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                                        MotoristaDAO dao = new MotoristaDAO(context);
                                        dao.deletarMotorista(listaDeMotoristas.get(position));
                                        ((ListaDeMotoristasActivity) context).finish();
                                        context.startActivity(new Intent(context, ListaDeMotoristasActivity.class));
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
                PopupMenu popupMenu = new PopupMenu(context, holder.textoNomeMotorista);
                popupMenu.inflate(R.menu.menu_popup_aluno);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.popup_editar:

                                Intent intent = new Intent(context, EditarMotoristaActivity.class);
                                intent.putExtra("id_motorista", listaDeMotoristas.get(position).getId());
                                intent.putExtra("nome_completo_motorista", listaDeMotoristas.get(position).getNomeCompleto());
                                intent.putExtra("cpf_motorista", listaDeMotoristas.get(position).getCpf());
                                intent.putExtra("endereco_motorista", listaDeMotoristas.get(position).getEndereco());
                                intent.putExtra("cnh", listaDeMotoristas.get(position).getCnh());
                                intent.putExtra("telefone_motorista", listaDeMotoristas.get(position).getTelefone());
                                intent.putExtra("email_motorista", listaDeMotoristas.get(position).getEmail());
                                context.startActivity(intent);

                                break;
                            case R.id.popup_deletar:

                                AlertDialog.Builder caixaDeDialogo = new AlertDialog.Builder(context);
                                caixaDeDialogo.setCancelable(false);
                                caixaDeDialogo.setTitle("Confirmar");
                                caixaDeDialogo.setMessage("Deseja realmente excluir o(a) motorista "+listaDeMotoristas.get(position).getNomeCompleto()+
                                        "?");

                                caixaDeDialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });

                                caixaDeDialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                                        MotoristaDAO dao = new MotoristaDAO(context);
                                        dao.deletarMotorista(listaDeMotoristas.get(position));
                                        context.startActivity(new Intent(context, ListaDeMotoristasActivity.class));
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
        return listaDeMotoristas.size();
    }

    public void setFiltro(List<Motorista> listaMotorista){
        listaDeMotoristas = listaMotorista;
        notifyDataSetChanged();
    }



}
