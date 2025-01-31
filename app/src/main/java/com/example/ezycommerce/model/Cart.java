package com.example.ezycommerce.model;

public class Cart {

    private int id, quantity;
    private double BookPrice;
    private String BookName, BookAuthor, BookImage;

    public Cart(int id, int quantity, String bookName, double bookPrice, String bookAuthor, String bookImage) {
        this.id = id;
        this.quantity = quantity;
        this.BookName = bookName;
        this.BookPrice = bookPrice;
        this.BookAuthor = bookAuthor;
        this.BookImage = bookImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public double getBookPrice() {
        return BookPrice;
    }

    public void setBookPrice(double bookPrice) {
        BookPrice = bookPrice;
    }

    public String getBookAuthor() {
        return BookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        BookAuthor = bookAuthor;
    }

    public String getBookImage() {
        return BookImage;
    }

    public void setBookImage(String bookImage) {
        BookImage = bookImage;
    }
}
