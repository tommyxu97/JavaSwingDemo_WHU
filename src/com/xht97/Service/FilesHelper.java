package com.xht97.Service;

import java.io.*;
import java.util.List;

/**
 * FilesHelper
 * File handler: provides method to read and write the txt file which includes program data
 *
 * @author TommyXu
 */
public class FilesHelper {

    // Current path in system
    private final static String CURRENT_DIR = System.getProperty("user.dir");

    private BufferedReader booksReader;
    private BufferedReader salesReader;
    private BufferedWriter booksWriter;
    private BufferedWriter salesWriter;

    // Initialize Reader and Writer
    public FilesHelper(String name)
    {
        System.out.println("Class:"+ name +" FileHelper Initialized!");
    }


    public String readFile(String type)
    {
        try{
            booksReader = new BufferedReader(new FileReader(CURRENT_DIR+"/res/Books.txt"));
            salesReader = new BufferedReader(new FileReader(CURRENT_DIR+"/res/Sales.txt"));
        }catch (IOException e){
            e.printStackTrace();
        }
        String flag;
        String str = "";
        if(type.equals("Books")) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                while((flag = booksReader.readLine())!=null) {
                    stringBuilder.append(flag);
                    stringBuilder.append("\n");
                }
                str = stringBuilder.toString();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                while((flag =salesReader.readLine())!=null) {
                    stringBuilder.append(flag);
                    stringBuilder.append("\n");
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            str = stringBuilder.toString();
        }
        try{
            booksReader.close();
            salesReader.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        return str;
    }

    public void updateFile(String type, String updatetext) {
        if (type.equals("Books")) {
            try {
                booksWriter = new BufferedWriter(new FileWriter(CURRENT_DIR + "/res/Books.txt"));
                booksWriter.write(updatetext);
                booksWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                salesWriter = new BufferedWriter(new FileWriter(CURRENT_DIR + "/res/Sales.txt", true));
                salesWriter.write(updatetext);
                salesWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
