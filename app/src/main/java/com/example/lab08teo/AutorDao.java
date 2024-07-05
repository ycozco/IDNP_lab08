package com.example.lab08teo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AutorDao {
    @Insert
    void insert(Autor autor);

    @Query("SELECT * FROM Autor")
    List<Autor> getAll();
}
