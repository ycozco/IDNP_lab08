package com.example.lab08teo;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Sala.class, Autor.class, Pintura.class}, version = 1)
    public abstract class AppDataBase extends RoomDatabase {
        public abstract SalaDao salaDao();
        public abstract AutorDao autorDao();
        public abstract PinturaDao pinturaDao();

        private static volatile AppDataBase INSTANCE;

        public static AppDataBase getDatabase(final Context context) {
            if (INSTANCE == null) {
                synchronized (AppDataBase.class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                        AppDataBase.class, "app_database")
                                .build();
                    }
                }
            }
            return INSTANCE;
        }
    }
