package com.xht97.UI.Frames;

import com.xht97.Model.Sale;
import com.xht97.Service.SaleList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.HashMap;
import java.util.Map;

/**
 * ChatFrame
 * Show sales chart with library JFreeCharts
 *
 * @author TommyXu
 */
public class ChartFrame extends JFrame{

    private JFreeChart chart;
    private SaleList saleList = new SaleList();
    private DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

    ChartFrame()
    {
        this.setTitle("Sales Chart");
        this.setSize(500,300);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.init();
        this.setVisible(true);
    }

    private void init()
    {
        updateData();
        chart = ChartFactory.createBarChart3D("Sales Chart",
                "Book",
                "Sales",
                dataSet, PlotOrientation.VERTICAL, false, false, false);
        ChartPanel panel = new ChartPanel(chart);
        this.add(panel);
    }

    private void updateData()
    {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(Sale sale: saleList.getSaleList()){
            int bookCode = sale.getCode();
            int saleNumber = sale.getNumber();

            if(map.containsKey(bookCode)){
                int current = map.get(bookCode) + saleNumber;
                map.put(bookCode, current);
            }else{
                map.put(bookCode, saleNumber);
            }
        }
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            dataSet.addValue(entry.getValue(), "Sale", entry.getKey());
        }
    }

}
