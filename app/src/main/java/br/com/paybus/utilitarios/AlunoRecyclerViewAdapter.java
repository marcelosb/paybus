package br.com.paybus.utilitarios;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.paybus.R;

public class AlunoRecyclerViewAdapter extends RecyclerView.Adapter<AlunoRecyclerViewAdapter.ViewHolderAluno> {

    private String[] arrayNomes = {"João da Silva", "Rafael Costa",
            "Junior Dutra", "Valquiria Dornelas", "Maria Antonia"};

    private String[] arrayInsituicoes = {"FIP - Faculdades Integradas de Patos",
            "UEPB - Universidade Estadual da Paraíba", "Cultura Inglesa",
            "UFCG - Universidade Federal de Campina Grande", "IFPB - Instituto Federal de Ciência e Tecnologia da Paraíba"};

    /*
    //Contrutor
    public AlunoRecyclerViewAdapter(String[] arrayNomes){
        this.arrayNomes = arrayNomes;
    }
    */



    @NonNull
    @Override
    public AlunoRecyclerViewAdapter.ViewHolderAluno onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_lista_alunos_recycler_view, parent, false);
        ViewHolderAluno viewHolderAluno = new ViewHolderAluno(view);

        return viewHolderAluno;
    }

    @Override
    public void onBindViewHolder(@NonNull AlunoRecyclerViewAdapter.ViewHolderAluno holder, int position) {

        holder.textoNome.setText(arrayNomes[position]);
        holder.textoInstituicao.setText(arrayInsituicoes[position]);

    }

    @Override
    public int getItemCount() {
        return arrayNomes.length;
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

}
