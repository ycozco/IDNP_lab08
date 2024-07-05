package com.example.lab08teo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

    @Entity
    public class Sala {
        @PrimaryKey(autoGenerate = true)
        public int id;
        public String nombre;
        public String temaDescripcion;
    }