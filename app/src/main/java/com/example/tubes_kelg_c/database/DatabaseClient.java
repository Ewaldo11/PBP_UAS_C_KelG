package com.example.tubes_kelg_c.database;

import android.content.Context;
import androidx.room.Room;

public class DatabaseClient {

    private Context context;
    private static DatabaseClient databaseClient;

    private AppDataBase database;

    private DatabaseClient(Context context){
        this.context = context;
        database = Room.databaseBuilder(context, AppDataBase.class, "penyewa").build();
    }

    public static synchronized DatabaseClient getInstance(Context context){
        if (databaseClient==null){
            databaseClient = new DatabaseClient(context);
        }
        return databaseClient;
    }

    public AppDataBase getDatabase(){ return database; }
}
