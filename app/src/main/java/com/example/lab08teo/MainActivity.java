package com.example.lab08teo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button buttonAddData, buttonShowData;
    private TextView textViewResults;
    private AppDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        buttonAddData = findViewById(R.id.buttonAddData);
        buttonShowData = findViewById(R.id.buttonShowData);
        textViewResults = findViewById(R.id.textViewResults);

        // Inicializar la base de datos
        db = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, "database-name").build();

        // Configurar listeners para los botones
        buttonAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });

        buttonShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData();
            }
        });
    }

    private void addData() {
        // Insertar datos en la base de datos
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Crear ejemplo de datos
                Autor autor = new Autor();
                autor.nombre = "Pablo";
                autor.apellido = "Picasso";
                long autorId = db.autorDao().insert(autor);

                Sala sala = new Sala();
                sala.nombre = "Galería de Arte";
                sala.descripcion = "Una colección de arte moderna.";
                long salaId = db.salaDao().insert(sala);

                Pintura pintura = new Pintura();
                pintura.titulo = "La Guernica";
                pintura.autorId = (int) autorId;
                pintura.salaId = (int) salaId;
                pintura.tecnica = "Óleo sobre lienzo";
                pintura.categoria = "Expresionismo";
                pintura.descripcion = "Una obra que representa los horrores de la guerra.";
                pintura.anio = 1937;
                pintura.enlace = "http://ejemplo.com/guernica.jpg";
                db.pinturaDao().insert(pintura);
            }
        }).start();
    }

    private void showData() {
        // Mostrar datos de la base de datos
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<PinturaDescription> pinturas = db.pinturaDao().getPinturaDescriptions();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        StringBuilder sb = new StringBuilder();
                        for (PinturaDescription pintura : pinturas) {
                            sb.append(pintura.titulo).append(" - ").append(pintura.autorNombre).append("\n");
                        }
                        textViewResults.setText(sb.toString());
                    }
                });
            }
        }).start();
    }
}
