package com.example.tubes_kelg_c;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;
@Dao
public interface PenyewaDao {
    @Query("SELECT * FROM penyewa")
    List<Penyewa> getAll();

    @Insert
    void insert(Penyewa penyewa);

    @Update
    void update(Penyewa penyewa);

    @Delete
    void delete(Penyewa penyewa);
}
