package com.xht97.UI.Frames;

import com.xht97.Model.Book;
import com.xht97.Service.BookList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * BookFrame
 * Book frame shows the stock of the books
 *
 * @author TommyXu
 */
public class BookFrame extends JFrame{

    private BookList bookList = new BookList();

    private JPanel centerPanel = new JPanel(new BorderLayout());
    private JPanel southPanel = new JPanel();

    private JTextArea textArea = new JTextArea("");
    private JButton button = new JButton("OK");

    BookFrame()
    {
        this.setTitle("Book Stock");
        this.setSize(500,300);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.init();
        this.updateData();
        this.setVisible(true);
    }

    private void init()
    {
        centerPanel.add(textArea, BorderLayout.CENTER);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));

        southPanel.add(button);

        this.setLayout(new BorderLayout());
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);

        setButtonListener();
    }

    private void setButtonListener()
    {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void updateData()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Code      Name                Price     Stock\n");
        try{
            for(Book book: bookList.getBookList()){
                int index;
                String blankString = " ";
                builder.append(book.getCode());
                index = 10 - String.valueOf(book.getCode()).length();
                for(int i = index; i>0; i--){
                    builder.append(blankString);
                }
                builder.append(book.getName());
                index = 20 - book.getName().length();
                for(int i = index; i>0; i--){
                    builder.append(blankString);
                }
                builder.append(book.getPrice());
                index = 10 - String.valueOf(book.getPrice()).length();
                for(int i = index; i>0; i--){
                    builder.append(blankString);
                }
                builder.append(book.getStock());
                index = 10 - String.valueOf(book.getStock()).length();
                for(int i = index; i>0; i--){
                    builder.append(blankString);
                }
                builder.append("\n");
            }
            textArea.setText(builder.toString());
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}
