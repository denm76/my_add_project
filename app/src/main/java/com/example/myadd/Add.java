package com.example.myadd;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "adds")
public class Add {

    @PrimaryKey
    private int id;

    private String title;
    private String text;
    private int price;
    private int priority;

    public Add(int id, String title, String text, int price, int priority) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.price = price;
        this.priority = priority;
    }

    @Ignore
    public Add(String title, String text, int price, int priority) {
        this(
                0,
                title,
                text,
                price,
                priority
        );
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public int getPrice() {
        return price;
    }

    public int getPriority() {
        return priority;
    }
}
