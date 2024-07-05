package com.example.lab08teo;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SalaDao {
    @Insert
    void insert(Sala sala);

    @Query("SELECT * FROM Sala")
    List<Sala> getAll();
}
