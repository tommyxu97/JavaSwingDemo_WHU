package com.xht97.Service;

import com.xht97.Model.Book;
import com.xht97.Model.Sale;

import java.util.*;

/**
 * Book List Model
 *
 * @author TommyXu
 */
public class BookList {

    private FilesHelper filesHelper;
    private String string;

    private ArrayList<Book> bookList = new ArrayList<>();

    public BookList()
    {
        filesHelper = new FilesHelper("BookList");
        try{
            parseBooks();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Book> getBookList()
    {
        return bookList;
    }

    public Book getBook(int index)
    {
        for(Book book: this.getBookList())
        {
            if(book.getCode() == index) {
                return book;
            }
        }
        return null;
    }

    public void updateBookList(Sale sale) {
        int stockChange = sale.getNumber();
        int code = sale.getCode();

        for (Book book : bookList) {
            if (book.getCode() == code) {
                int currentStock = book.getStock();
                book.setStock((currentStock - stockChange));
            } else {
                continue;
            }
        }

    }

    private void parseBooks()
    {
        this.string = filesHelper.readFile("Books");
        String[] strings = this.string.split("\n");
//        System.out.println("-------StringParseTest-------");
//        for(String str:strings){
//            System.out.println(str);
//        }

        for (String str: strings)
        {
            if(str.equals("Code,Name,Price,Stock") || str.equals("")){
                continue;
            }
            String[] properties = str.split(",");
            Book book = new Book();
            book.setCode(Integer.parseInt(properties[0]));
            book.setName(properties[1]);
            book.setPrice(Float.parseFloat(properties[2]));
            book.setStock(Integer.parseInt(properties[3]));

//            System.out.println("------ParseBooksTest------");
//            System.out.println(book.getName());

            this.bookList.add(book);
        }
    }

    public void updateFile()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Code,Name,Price,Stock\n");
        String sep = ",";
        for(Book book: bookList){
            builder.append(book.getCode());
            builder.append(sep);
            builder.append(book.getName());
            builder.append(sep);
            builder.append(book.getPrice());
            builder.append(sep);
            builder.append(book.getStock());
            builder.append("\n");
        }
        filesHelper.updateFile("Books", builder.toString());
    }
}
