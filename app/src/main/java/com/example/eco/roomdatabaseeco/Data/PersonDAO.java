package com.example.eco.roomdatabaseeco.Data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.eco.roomdatabaseeco.Model.PersonModel;

@Dao
public interface PersonDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertPerson(PersonModel personModel);

    @Update
    int updatePerson(PersonModel personModel);

    @Delete
    int deletePerson(PersonModel personModel);

    @Query("SELECT * FROM Persondb")
    PersonModel[] selectAllPersons();

    @Query("SELECT * FROM Persondb WHERE personId = :id LIMIT 1")
    PersonModel selectSinglePerson(int id);
}
