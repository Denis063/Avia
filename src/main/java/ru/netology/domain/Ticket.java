package ru.netology.domain;

public class Ticket implements Comparable<Ticket> {
    private int id;
    private int price; // Стоимость билета
    private int time; // Время полета
    private String from; // Аэропорт вылета
    private String to; // Аэропорт прилета


    public Ticket(int id, int price, int time, String from, String to) {
        this.id = id;
        this.price = price;
        this.time = time;
        this.from = from;
        this.to = to;
    }

    public int getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public int compareTo(Ticket comparePrice) {
        return this.price - comparePrice.price;
    }
}