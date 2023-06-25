package com.example.myadd;


import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Add.class}, version = 1)
public abstract class AddDatabase extends RoomDatabase {

    private static AddDatabase instance = null;
    private static final String DB_NAME = "adds.db";

    public static  AddDatabase getInstance(Application application){
        if (instance == null){
            instance = Room.databaseBuilder(
                    application,
                    AddDatabase.class,
                    DB_NAME
            ).build();
        }
        return instance;
    }

    public abstract AddDao addsDao();

}
