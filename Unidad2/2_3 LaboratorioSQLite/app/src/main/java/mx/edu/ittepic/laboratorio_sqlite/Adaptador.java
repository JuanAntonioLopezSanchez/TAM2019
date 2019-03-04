package mx.edu.ittepic.laboratorio_sqlite;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolderClientes> {

    ArrayList<Paciente> listaClientes;

    Context context;

    public Adaptador(ArrayList<Paciente> listaClientes, Context context){
        this.listaClientes = listaClientes;
        this.context = context;
    }

    @Override
    public ViewHolderClientes onCreateViewHolder(ViewGroup viewGroup, int i) {
        int layout = R.layout.vista;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layout, null, false);
        return new ViewHolderClientes(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderClientes viewHolderClientes, int i) {

        viewHolderClientes.id.setText("ID: "+listaClientes.get(i).getIdPaciente());
        viewHolderClientes.nombre.setText("Nombre: "+listaClientes.get(i).getNombre());

    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public class ViewHolderClientes extends RecyclerView.ViewHolder{

        TextView id, nombre;

        public ViewHolderClientes(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            nombre = itemView.findViewById(R.id.nombre);
        }
    }
}
