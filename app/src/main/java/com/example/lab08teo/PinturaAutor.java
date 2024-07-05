package com.example.lab08teo;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class PinturaAutor {
    public String titulo;
    public String nombre;
    public String apellido;
    public String tecnica;
    public String categoria;
    public String descripcion;
    public int anio;
    public String imagen;
    @Embedded
    public Autor autor2;
    @Relation(
            parentColumn = "id", entityColumn = "autorId"
    )
    public List<Pintura> pinturas;

}
