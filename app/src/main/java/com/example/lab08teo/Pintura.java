package com.example.lab08teo;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Autor.class, parentColumns = "id", childColumns = "autorId"),
        @ForeignKey(entity = Sala.class, parentColumns = "id", childColumns = "salaId")
})
public class Pintura {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String titulo;
    public int autorId;
    public int salaId;
    public String tecnica;
    public String categoria;
    public String descripcion;
    public int anio;
    public String enlace;
}
