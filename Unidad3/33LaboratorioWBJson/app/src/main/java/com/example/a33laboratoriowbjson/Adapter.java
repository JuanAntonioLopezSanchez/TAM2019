package com.example.a33laboratoriowbjson_sinahiruiz;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolderAlumnos>
{
    ArrayList<NumerosA> listaAlumnos;

    public Adapter(ArrayList<NumerosA> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    @NonNull
    @Override
    public Adapter.ViewHolderAlumnos onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null, false);
        return new ViewHolderAlumnos(view);
    }

    @Override
    public void onBindViewHolder( ViewHolderAlumnos holder, int i) {

        holder.nombre.setText("Alumno: " + listaAlumnos.get(i).getNombre());
        holder.control.setText("No. Control: " + listaAlumnos.get(i).getControl());
        holder. u1.setText("Cal. U1: " + listaAlumnos.get(i).getCalificacion1());
        holder.u2.setText("Cal. U2: " + listaAlumnos.get(i).getCalificacion2());
        holder. u3.setText("Cal U3: " + listaAlumnos.get(i).getCalificacion3());

        int c1,c2,c3;
        int contador=0;
        c1=Integer.parseInt(listaAlumnos.get(i).calificacion1);
        c2=Integer.parseInt(listaAlumnos.get(i).calificacion2);
        c3=Integer.parseInt(listaAlumnos.get(i).calificacion3);

        if(c1==0)
        {
            contador++;
        }
        if(c2==0)
        {
            contador++;
        }
        if(c3==0)
        {
            contador++;
        }

        if (contador==0)
        {
            holder.constrain.setBackgroundColor(Color.parseColor("#3ADF00"));
        }
        if (contador==1)
        {
            holder.constrain.setBackgroundColor(Color.parseColor("#F4FA58"));
        }

        if(contador==2)
        {
            holder.constrain.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        if(contador==3)
        {
            holder.constrain.setBackgroundColor(Color.parseColor("#FF0000"));
        }


    }

    @Override
    public int getItemCount() {
        return listaAlumnos.size();
    }

    public class ViewHolderAlumnos extends RecyclerView.ViewHolder {

        TextView control, nombre, u1, u2, u3;
        ConstraintLayout constrain;

        public ViewHolderAlumnos(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.nombre);
            control=itemView.findViewById(R.id.control);
            u1=itemView.findViewById(R.id.u1);
            u2=itemView.findViewById(R.id.u2);
            u3=itemView.findViewById(R.id.u3);
            constrain=itemView.findViewById(R.id.constrainF);

        }
    }
}


