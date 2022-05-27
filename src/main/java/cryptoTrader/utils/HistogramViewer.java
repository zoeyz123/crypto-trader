/*
 * Class Description: This class creates the histogram representation of the user's trade
 * Authors: Jessica Ou, Brielle Nguyen, Mylan Nguyen, Zoey Zheng
 */

package cryptoTrader.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import cryptoTrader.gui.MainUI;
import cryptoTrader.gui.PerformTradeProxy;

public class HistogramViewer implements Observer {

	private PerformTradeProxy successTrade = PerformTradeProxy.getInstance();
    public void update() {
        createBar();
    }
	
	//public void createCharts() {
		//createTableOutput();
		//createBar();
	//}
	
	/**
	 * This class creates the table output for the user's trading activity 
	 */
	private void createTableOutput() {
		// Dummy dates for demo purposes. These should come from selection menu
		Object[] columnNames = {"Trader","Strategy","CryptoCoin","Action","Quantity","Price","Date"};		
		Object[][] data = successTrade.getSuccessData();
		

		JTable table = new JTable(data, columnNames);
		//table.setPreferredSize(new Dimension(600, 300));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Trader Actions",
                TitledBorder.CENTER,
                TitledBorder.TOP));
		
		scrollPane.setPreferredSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);
		
		MainUI.getInstance().updateStats(scrollPane);
	}
	
	/**
	 * This class creates the time series based on the user's trading activity 
	 */
	private void createTimeSeries() {
		TimeSeries series1 = new TimeSeries("Bitcoin - Daily");
		series1.add(new Day(13, 9, 2021), 50368.67);
		series1.add(new Day(14, 9, 2021), 51552.05);
		series1.add(new Day(15, 9, 2021), 47228.30);
		series1.add(new Day(16, 9, 2021), 45263.90);
		series1.add(new Day(17, 9, 2021), 46955.41);
		
		TimeSeries series2 = new TimeSeries("Ethereum - Daily");
		series2.add(new Day(13, 9, 2021), 3912.28);
		series2.add(new Day(14, 9, 2021), 3927.27);
		series2.add(new Day(15, 9, 2021), 3460.48);
		series2.add(new Day(16, 9, 2021), 3486.09);
		series2.add(new Day(17, 9, 2021), 3550.29);

		TimeSeries series3 = new TimeSeries("Cardano - Daily");
		series3.add(new Day(13, 9, 2021), 2.87);
		series3.add(new Day(14, 9, 2021), 2.84);
		series3.add(new Day(15, 9, 2021), 2.41);
		series3.add(new Day(16, 9, 2021), 2.43);
		series3.add(new Day(17, 9, 2021), 2.59);

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		dataset.addSeries(series3);

		XYPlot plot = new XYPlot();
		XYSplineRenderer splinerenderer1 = new XYSplineRenderer();
		
		plot.setDataset(0, dataset);
		plot.setRenderer(0, splinerenderer1);
		DateAxis domainAxis = new DateAxis("");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new LogAxis("Price(USD)"));
		
		JFreeChart chart = new JFreeChart("Daily Price Line Chart", new Font("Serif", java.awt.Font.BOLD, 18), plot,
				true);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(800, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		
		MainUI.getInstance().updateStats(chartPanel);
	}

	
	/**
	 * This class creates the histogram representation of the user's trading activity 
	 */
	public void createBar() {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		//changes
		Object[][] dataList = successTrade.getSuccessData();
		
		int countA = 0;
		int countB = 0;
		int countC = 0;
		int countD = 0;
		ArrayList<String> tradersA = new ArrayList<String>();
		ArrayList<String> tradersB = new ArrayList<String>();
		ArrayList<String> tradersC = new ArrayList<String>();
		ArrayList<String> tradersD = new ArrayList<String>();
		
		for (int i=0 ; i< dataList.length ; i++) {
			if (dataList[i][1].equals("Strategy-A")) {
				countA++;
				tradersA.add((String) dataList[i][0]);
			}else if (dataList[i][1].equals("Strategy-B")) {
				countB++;
				tradersB.add((String) dataList[i][0]);
			}else if (dataList[i][1].equals("Strategy-C")) {
				countC++;
				tradersC.add((String) dataList[i][0]);
			}else if (dataList[i][1].equals("Strategy-D")) {
				countD++;
				tradersD.add((String) dataList[i][0]);
			}
		}
		
		for (int i=0 ; i< tradersA.size() ; i++) {
			dataset.setValue(countA, tradersA.get(i), "Strategy-A");
		}
		for (int i=0 ; i< tradersB.size() ; i++) {
			dataset.setValue(countB, tradersB.get(i), "Strategy-B");
		}
		for (int i=0 ; i< tradersC.size() ; i++) {
			dataset.setValue(countC, tradersC.get(i), "Strategy-C");
		}
		for (int i=0 ; i< tradersD.size() ; i++) {
			dataset.setValue(countD, tradersD.get(i), "Strategy-D");
		}
		

		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();

		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Strategy");
		plot.setDomainAxis(domainAxis);
		LogAxis rangeAxis = new LogAxis("Actions(Buys or Sells)");
		rangeAxis.setRange(new Range(1.0, 20.0));
		plot.setRangeAxis(rangeAxis);

		JFreeChart barChart = new JFreeChart("Actions Performed By Traders So Far", new Font("Serif", java.awt.Font.BOLD, 18), plot,
				true);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		MainUI.getInstance().updateStats(chartPanel);
	}
}
