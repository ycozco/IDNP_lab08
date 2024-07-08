package com.example.lab08teo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PinturaDao {
    @Insert
    void insert(Pintura... pinturas);

    @Query("SELECT PINTURA.titulo, AUTOR.nombre || ' ' || AUTOR.apellido as autorNombre, PINTURA.tecnica, PINTURA.categoria, PINTURA.descripcion, PINTURA.anio, PINTURA.enlace " +
            "FROM PINTURA INNER JOIN AUTOR ON PINTURA.autorId = AUTOR.id")
    List<PinturaDescription> getPinturaDescriptions();

    @Query("SELECT SALA.nombre, PINTURA.titulo, AUTOR.nombre || ' ' || AUTOR.apellido as autorNombre, PINTURA.enlace " +
            "FROM PINTURA INNER JOIN SALA ON PINTURA.salaId = SALA.id INNER JOIN AUTOR ON PINTURA.autorId = AUTOR.id")
    List<PinturaMap> getPinturaMaps();
}
