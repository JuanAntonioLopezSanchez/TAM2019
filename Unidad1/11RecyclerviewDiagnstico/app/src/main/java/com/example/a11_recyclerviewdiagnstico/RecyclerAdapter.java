package com.example.a11_recyclerviewdiagnstico;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private String[] nombresAlumnos;
    private String[] numControl;
    private String[] carrera;
    private Context con;

    public RecyclerAdapter(String[] nombresAlumnos, String[] numControl, String[] carrera, Context context) {
        this.nombresAlumnos = nombresAlumnos;
        this.numControl = numControl;
        this.carrera = carrera;
        this.con = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_item_layout, viewGroup, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder recyclerViewHolder, final int i) {
        recyclerViewHolder.campo_nombre.setText(nombresAlumnos[i]);
        recyclerViewHolder.num_control.setText(numControl[i]);
        recyclerViewHolder.carrera_escuela.setText(carrera[i]);

        recyclerViewHolder.imgAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(con, "Seleccionaste la imagen de: "+nombresAlumnos[i]+ ". " + numControl[i] + ". " + carrera[i], Toast.LENGTH_LONG).show();
            }
        });

        recyclerViewHolder.imgEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(con, "Eliminar: "+nombresAlumnos[i]+ ". " + numControl[i] + ". " + carrera[i] + "?", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return nombresAlumnos.length;
    }



    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView campo_nombre;
        TextView num_control;
        TextView carrera_escuela;

        ImageView imgAlumno, imgEliminar;

        public RecyclerViewHolder(final View itemView) {
            super(itemView);

            campo_nombre = itemView.findViewById(R.id.alumno);
            num_control = itemView.findViewById(R.id.numControl);
            carrera_escuela = itemView.findViewById(R.id.carrera);

            imgAlumno = itemView.findViewById(R.id.imgAlumno);
            imgEliminar = itemView.findViewById(R.id.imgEliminar);

        }
    }
}
