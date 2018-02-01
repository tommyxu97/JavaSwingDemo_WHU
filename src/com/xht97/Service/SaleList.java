package com.xht97.Service;

import com.xht97.Model.Sale;

import java.util.*;

/**
 * Sale List Model
 *
 * @author TommyXu
 */
public class SaleList {

    private FilesHelper filesHelper;
    private String string;

    private ArrayList<Sale> saleList = new ArrayList<>();
    private ArrayList<Sale> newSalesRecord = new ArrayList<>();

    public SaleList()
    {
        filesHelper = new FilesHelper("Sales");
        parseSales();
    }

    public ArrayList<Sale> getSaleList()
    {
        return saleList;
    }

    public Sale getSale(int index)
    {
        for(Sale sale: this.getSaleList())
        {
            if(sale.getIndex() == index){
                return sale;
            }
        }
        return null;
    }

    public void updateSaleList(Sale sale)
    {
        this.saleList.add(sale);
        this.newSalesRecord.add(sale);
    }

    private void parseSales()
    {
        this.string = filesHelper.readFile("Sales");
        String[] strings = string.split("\n");
        for(String str: strings){
            if(str.equals("Index,Code,Name,Staff,Number,Price,TotalPrice") || str.equals("")){
                continue;
            }
            String[] properties = str.split(",");
            Sale sale = new Sale();
            sale.setIndex(Integer.parseInt(properties[0]));
            sale.setCode(Integer.parseInt(properties[1]));
            sale.setName(properties[2]);
            sale.setStaff(properties[3]);
            sale.setNumber(Integer.parseInt(properties[4]));
            sale.setPrice(Float.parseFloat(properties[5]));
            sale.setTotalPrice(Float.parseFloat(properties[6]));

            this.saleList.add(sale);
        }
    }

    public void updateFile()
    {
        StringBuilder builder = new StringBuilder();
        String sep = ",";
        for(Sale sale: newSalesRecord){
            builder.append(sale.getIndex());
            builder.append(sep);
            builder.append(sale.getCode());
            builder.append(sep);
            builder.append(sale.getName());
            builder.append(sep);
            builder.append(sale.getStaff());
            builder.append(sep);
            builder.append(sale.getNumber());
            builder.append(sep);
            builder.append(sale.getNumber());
            builder.append(sep);
            builder.append(sale.getPrice());
            builder.append(sep);
            builder.append(sale.getTotalPrice());
            builder.append("\n");
        }
        filesHelper.updateFile("Sales", builder.toString());
    }
}
