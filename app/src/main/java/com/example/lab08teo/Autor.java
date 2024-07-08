package com.example.lab08teo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Autor {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nombre;
    public String apellido;
}
