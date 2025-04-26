package ru.mirea.shutovks.employeedb;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "SuperheroDB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        SuperheroDao dao = App.getInstance().getDatabase().superheroDao();

        Superhero ironMan = new Superhero("Iron Man", "Величайший гений с крутым костюм", "Marvel");
        Superhero batman = new Superhero("Batman", "Пик человекого развития в физическом и умственном плане", "DC");
        dao.insert(ironMan);
        dao.insert(batman);

        List<Superhero> all = dao.getAll();
        for (Superhero h : all) {
            Log.d(TAG, h.id + ": " + h.name + " [" + h.universe + "] — " + h.superpower);
        }

        Log.d(TAG, "----------------------");

        Superhero bm = dao.getById(2);
        bm.superpower = "Величайший детектив в мире";
        dao.update(bm);
        List<Superhero> all2 = dao.getAll();
        for (Superhero h : all2) {
            Log.d(TAG, h.id + ": " + h.name + " [" + h.universe + "] — " + h.superpower);
        }

        Log.d(TAG, "----------------------");

        Superhero im = dao.getById(1);
        dao.delete(im);

        List<Superhero> remaining = dao.getAll();
        for (Superhero h : remaining) {
            Log.d(TAG, "Остался: " + h.name);
        }
    }
}
