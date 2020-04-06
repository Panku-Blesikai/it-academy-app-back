package it.academy.app.models;

public class Comment {
    public String author;
    public String input;
    public String date;

    public Comment(String author, String input, String date) {
        this.author = author;
        this.input = input;
        this.date = date;
    }
}
