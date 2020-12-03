package com.example.tubes_kelg_c.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.tubes_kelg_c.Penyewa;
import com.example.tubes_kelg_c.PenyewaDao;


@Database(entities = {Penyewa.class}, version =1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract PenyewaDao penyewaDao();
}

