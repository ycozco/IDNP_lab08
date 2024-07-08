package com.example.lab08teo;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Autor.class, Pintura.class, Sala.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract AutorDao autorDao();
    public abstract PinturaDao pinturaDao();
    public abstract SalaDao salaDao();
}