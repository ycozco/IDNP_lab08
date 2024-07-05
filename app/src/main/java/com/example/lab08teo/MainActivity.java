package com.example.lab08teo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import androidx.room.Room;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppDataBase db;
    private TextView textViewResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar la base de datos
        db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "smart_gallery_db").allowMainThreadQueries().build();

        textViewResults = findViewById(R.id.textViewResults);

        Button buttonAddData = findViewById(R.id.buttonAddData);
        buttonAddData.setOnClickListener(v -> insertarDatosDePrueba());

        Button buttonShowData = findViewById(R.id.buttonShowData);
        buttonShowData.setOnClickListener(v -> consultarDatosDePrueba());
    }

    private void insertarDatosDePrueba() {
        // Leer y agregar datos de autores
        List<Autor> autores = leerAutores();
        for (Autor autor : autores) {
            db.autorDao().insert(autor);
        }

        // Leer y agregar datos de salas
        List<Sala> salas = leerSalas();
        for (Sala sala : salas) {
            db.salaDao().insert(sala);
        }

        // Leer y agregar datos de pinturas
        List<Pintura> pinturas = leerPinturas();
        for (Pintura pintura : pinturas) {
            db.pinturaDao().insert(pintura);
        }
    }

    private List<Autor> leerAutores() {
        List<Autor> autores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.autores)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ", 2);
                if (parts.length == 2) {
                    Autor autor = new Autor();
                    autor.nombre = parts[0].trim();
                    autor.apellido = parts[1].trim();
                    autores.add(autor);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return autores;
    }

    private List<Sala> leerSalas() {
        List<Sala> salas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.salas)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Sala sala = new Sala();
                sala.nombre = line.trim();
                sala.temaDescripcion = "Descripción de " + sala.nombre;
                salas.add(sala);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salas;
    }

    private List<Pintura> leerPinturas() {
        List<Pintura> pinturas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.pinturas)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Pintura pintura = new Pintura();
                pintura.titulo = line.trim();
                pintura.autorId = 1; // Asignar autor y sala de forma manual para pruebas
                pintura.salaId = 1;
                pintura.tecnica = "Técnica de " + pintura.titulo;
                pintura.categoria = "Categoría de " + pintura.titulo;
                pintura.descripcion = "Descripción de " + pintura.titulo;
                pintura.anio = 2023;
                pintura.imagen = "Enlace de " + pintura.titulo;
                pinturas.add(pintura);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pinturas;
    }

    private void consultarDatosDePrueba() {
        List<PinturaAutor> pinturaAutores = db.pinturaDao().getPinturaAutor();
        StringBuilder result = new StringBuilder();
        for (PinturaAutor pa : pinturaAutores) {
            result.append("Título: ").append(pa.titulo).append("\n")
                    .append("Autor: ").append(pa.autor).append("\n")
                    .append("Técnica: ").append(pa.tecnica).append("\n")
                    .append("Categoría: ").append(pa.categoria).append("\n")
                    .append("Descripción: ").append(pa.descripcion).append("\n")
                    .append("Año: ").append(pa.anio).append("\n")
                    .append("Enlace: ").append(pa.imagen).append("\n\n");
        }
        textViewResults.setText(result.toString());
    }
}
