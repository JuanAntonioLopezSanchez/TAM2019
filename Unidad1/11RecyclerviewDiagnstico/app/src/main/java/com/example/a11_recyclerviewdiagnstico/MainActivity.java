package com.example.a11_recyclerviewdiagnstico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    String [] nombreAlumno = {"JUAN ANTONIO LOPEZ SANCHEZ",
            "CERVANTES JIMÉNEZ JOSÉ MIGUEL",
            "CARDENAS RAMOS FRANCISCO EMANUEL",
            "CASTILLO CORRALES VICTOR EMMANUEL",
            "CERVANTES JIMÉNEZ JOSÉ MIGUEL",
            "CORDERO RIVERA SELVA YAZMIN",
            "CORDERO VILLA OSCAR ALBERTO",
            "ESPINOSA ABANDO DENISSE YANETH",
            "FIGUEROA CUETO JUAN RAMON",
            "GALLEGOS GODINEZ FRANCISCO JAVIER",
            "GIL LLANOS JUAN PEDRO",
            "GONZALEZ ARELLANO ERNESTO",};

    String [] numControl = {"15400790", "15400760","15400700","15400701","15400709","15400721",
            "15400703","15400704","15400705","15400706","15400707",};
    String [] carrera = {"ITICS", "ITICS","ITICS","ITICS","ITICS",
            "ITICS","ITICS","ITICS","ITICS","ITICS",};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);

        adapter = new RecyclerAdapter(nombreAlumno, numControl, carrera, this);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);
    }
}
