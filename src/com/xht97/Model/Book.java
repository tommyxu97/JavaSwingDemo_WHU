package com.xht97.Model;

/**
 * Book Model
 * Each book object shows data of a kind of book
 *
 * @author TommyXu
 */
public class Book {

    private int Code;
    private String Name;
    private float Price;
    private int Stock;

    public void setCode(int code) {
        Code = code;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public void setStock(int stock)
    {
        Stock = stock;
    }

    public int getCode() {
        return Code;
    }

    public String getName() {
        return Name;
    }

    public float getPrice() {
        return Price;
    }

    public int getStock()
    {
        return Stock;
    }

}
