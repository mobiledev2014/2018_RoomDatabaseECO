package com.example.eco.roomdatabaseeco.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Persondb")
public class PersonModel implements Serializable{

    @PrimaryKey(autoGenerate = true)
    public int personId;

    @ColumnInfo(name = "firstname")
    public String firstName;

    @ColumnInfo(name = "middlename")
    public String middleName;

    @ColumnInfo(name = "lastname")
    public String lastName;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
