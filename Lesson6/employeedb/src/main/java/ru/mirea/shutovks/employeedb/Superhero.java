package ru.mirea.shutovks.employeedb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Superhero {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String name;
    public String superpower;
    public String universe;

    public Superhero(String name, String superpower, String universe) {
        this.name = name;
        this.superpower = superpower;
        this.universe = universe;
    }
}
