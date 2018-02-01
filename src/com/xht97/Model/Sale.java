package com.xht97.Model;

/**
 * Sale Record Model
 * Each sale model shows data of one sale record
 *
 * @author TommyXu
 */
public class Sale {

    private int Index;
    private int Code;
    private String Name;
    private String Staff;
    private int Number;
    private float Price;
    private float TotalPrice;

    public void setIndex(int index) {
        Index = index;
    }

    public void setCode(int code) {
        Code = code;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setStaff(String staff) {
        Staff = staff;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public void setTotalPrice(float totalPrice) {
        TotalPrice = totalPrice;
    }

    public int getIndex() {
        return Index;
    }

    public int getCode() {
        return Code;
    }

    public String getName() {
        return Name;
    }

    public String getStaff() {
        return Staff;
    }

    public int getNumber() {
        return Number;
    }

    public float getPrice() {
        return Price;
    }

    public float getTotalPrice() {
        return TotalPrice;
    }

}
