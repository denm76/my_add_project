package com.example.myadd;

import java.util.ArrayList;
import java.util.Random;

public class Database {

    private ArrayList<Add> adds = new ArrayList<>();

    //паттерн Синглтон для создания объекта класса(БД), доступного из разных классов
    private static Database instance = null;

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    // end pattern

    private Database() {
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            Add add = new Add(
                    "Add" + i,
                    "Add" + i,
                    String.valueOf(random.nextInt(1500) + 20), random.nextInt(3)
            );

            adds.add(add);
        }
    }

    public void add(Add add) {
        adds.add(add);
    }

    public void remove(int id) {
        for (int i = 0; i < adds.size(); i++) {
            Add add = adds.get(i);
            if (add.getId() == id) {
                adds.remove(add);
            }
        }
    }

    public ArrayList<Add> getNotes() {
        return new ArrayList<>(adds);
    }
}
