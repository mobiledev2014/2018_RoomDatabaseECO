package com.example.eco.roomdatabaseeco.Data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.eco.roomdatabaseeco.Model.PersonModel;

@Database(entities = {PersonModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    public abstract PersonDAO personDAO();
}
