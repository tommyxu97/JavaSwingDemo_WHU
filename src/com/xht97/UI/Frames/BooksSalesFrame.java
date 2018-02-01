package com.xht97.UI.Frames;

import com.xht97.Model.Book;
import com.xht97.Model.Sale;
import com.xht97.Service.BookList;
import com.xht97.Service.SaleList;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Inet4Address;

/**
 * Books Sales Frame
 * Main Frame of this program
 *
 * @author TommyXu
 */
public class BooksSalesFrame extends JFrame {

    // All private modules
    private JLabel titleLable = new JLabel("Books Manager");

    private JPanel northPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JPanel southPanel = new JPanel();

    private JButton showStock = new JButton("ShowStock");
    private JButton showGraph = new JButton("ShowGraph");
    private JButton saveAndExit = new JButton("SaveAndExit");

    private JButton submit = new JButton("Submit");
    private JButton clear = new JButton("Clear");

    private JTextField numberTextInput = new JTextField("", 10);
    private JTextField bookIndexIdInput = new JTextField("", 10);
    private JTextField salesmanNameInput = new JTextField("", 10);

    private JLabel number = new JLabel("Number:");
    private JLabel bookIndex = new JLabel("Book Index:");
    private JLabel salesmanName = new JLabel("Salesman Name:");

    private JTextArea textArea = new JTextArea("");

    // Maintains a sale list which includes all sale records
    private BookList bookList = new BookList();
    private SaleList saleList = new SaleList();

    public BooksSalesFrame() {
        this.setTitle("Books Manager");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 540);
        this.init();

//      this.test();
    }

    // This function can be accessed only with private method
    private void init() {
        // North panel
        northPanel.setLayout(new FlowLayout());
        northPanel.add(titleLable);
        northPanel.add(showStock);
        northPanel.add(showGraph);
        northPanel.add(saveAndExit);

        // Center panel
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(textArea, BorderLayout.CENTER);

        // South panel: divide into two separate panels
        southPanel.setPreferredSize(new Dimension(0, 120));
        southPanel.setLayout(new FlowLayout());

        JPanel leftSubPanel = new JPanel();
        leftSubPanel.setLayout(new BoxLayout(leftSubPanel, BoxLayout.Y_AXIS));
        leftSubPanel.add(submit);
        leftSubPanel.add(Box.createRigidArea(new Dimension(15, 10)));
        leftSubPanel.add(clear);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.3;
        c.gridx = 0;
        c.gridy = 0;
        rightPanel.add(number, c);
        c.gridy = 1;
        rightPanel.add(bookIndex, c);
        c.gridy = 2;
        rightPanel.add(salesmanName, c);
        c.weightx = 0.7;
        c.gridx = 1;
        c.gridy = 0;
        rightPanel.add(numberTextInput, c);
        c.gridy = 1;
        rightPanel.add(bookIndexIdInput, c);
        c.gridy = 2;
        rightPanel.add(salesmanNameInput, c);

        southPanel.add(leftSubPanel);
        southPanel.add(rightPanel);

        // Add panels to the frame
        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);

        updateData();
        setButtonListener();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
        });
    }

    private void setButtonListener() {
        showStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookFrame frame = new BookFrame();
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            }
        });
        showGraph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChartFrame frame = new ChartFrame();
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            }
        });
        saveAndExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane optionPane = new JOptionPane();
                int n = optionPane.showConfirmDialog(null, "Are you sure to exit?",
                        "Message", JOptionPane.YES_NO_CANCEL_OPTION);
                if (n == 0)
                    System.exit(0);
            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pNumber;
                int pBookIndex;
                String pSalesmanName;
                try {
                    pNumber = Integer.parseInt(numberTextInput.getText());
                    pBookIndex = Integer.parseInt(bookIndexIdInput.getText());
                } catch (Exception exception) {
                    pNumber = -1;
                    pBookIndex = -1;
                    JOptionPane optionPane = new JOptionPane();
                    optionPane.showMessageDialog(null, "Input Error!", "Error",
                            JOptionPane.WARNING_MESSAGE);
                    exception.printStackTrace();
                }
                pSalesmanName = salesmanNameInput.getText();
                if (pSalesmanName.equals("")) {
                    pSalesmanName = "message";
                }
                try {
                    update(pNumber, pBookIndex, pSalesmanName);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    JOptionPane optionPane = new JOptionPane();
                    optionPane.showMessageDialog(null, "Update Failed", "Error",
                            JOptionPane.WARNING_MESSAGE);
                }
                bookList.updateFile();
                saleList.updateFile();

            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberTextInput.setText("");
                bookIndexIdInput.setText("");
                salesmanNameInput.setText("");
            }
        });
    }

    private void updateData() {
        StringBuilder builder = new StringBuilder();
        builder.append("Index     Code      Name                Staff     Number    Price     TotalPrice\n");
        try {
            for (Sale sale : saleList.getSaleList()) {
                int index;
                String blankString = " ";
                builder.append(sale.getIndex());
                index = 10 - String.valueOf(sale.getIndex()).length();
                for (int i = index; i > 0; i--) {
                    builder.append(blankString);
                }
                builder.append(sale.getCode());
                index = 10 - String.valueOf(sale.getCode()).length();
                for (int i = index; i > 0; i--) {
                    builder.append(blankString);
                }
                builder.append(sale.getName());
                index = 20 - sale.getName().length();
                for (int i = index; i > 0; i--) {
                    builder.append(blankString);
                }
                builder.append(sale.getStaff());
                index = 10 - sale.getStaff().length();
                for (int i = index; i > 0; i--) {
                    builder.append(blankString);
                }
                builder.append(sale.getNumber());
                index = 10 - String.valueOf(sale.getNumber()).length();
                for (int i = index; i > 0; i--) {
                    builder.append(blankString);
                }
                builder.append(sale.getPrice());
                index = 10 - String.valueOf(sale.getPrice()).length();
                for (int i = index; i > 0; i--) {
                    builder.append(blankString);
                }
                builder.append(sale.getTotalPrice());
                index = 10 - String.valueOf(sale.getTotalPrice()).length();
                for (int i = index; i > 0; i--) {
                    builder.append(blankString);
                }
                builder.append("\n");
            }
            textArea.setText(builder.toString());
        } catch (NullPointerException e) {
            e.printStackTrace();
            JOptionPane optionPane = new JOptionPane();
            optionPane.showMessageDialog(this, "No Data", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    // Submit changes to BookList and SaleList
    private void update(int bookNumber, int bookCode, String salesmanname) {
        Sale sale = new Sale();

        sale.setCode(bookCode);
        sale.setNumber(bookNumber);
        sale.setStaff(salesmanNameInput.getText());

        Book book = bookList.getBook(Integer.parseInt(bookIndexIdInput.getText()));
        if(bookNumber > book.getStock()){
            JOptionPane optionPane = new JOptionPane();
            optionPane.showMessageDialog(this,"Stock Not Enough!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        sale.setName(book.getName());
        sale.setPrice(book.getPrice());
        sale.setTotalPrice(book.getPrice() * bookNumber);

        int listLength = saleList.getSaleList().size();
        sale.setIndex(listLength + 1);
        // Updates sale list
        saleList.updateSaleList(sale);
        // Updates book list
        bookList.updateBookList(sale);
        // Update Showing data
        updateData();
    }

//    private void test()
//    {
//        System.out.println("-------TEST--------");
//        for(Book book: this.bookList.getBookList()){
//            System.out.println(book.getName());
//        }
//    }


}
