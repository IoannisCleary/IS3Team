import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Class to create cluster of barcharts.
 * @author Gavin Davidson
 *
 */
public class BarchartCluster extends JPanel{

	Border padding;
	DefaultCategoryDataset[] datasets;
	String[] labels;
	LinkedList<String> countries;
	
	
	/*
	 * Constructor for cluster panel. For loop is used to make placeholders for graphs so that they
	 * are in the right position even if they are created in the wrong order.
	 */
	public BarchartCluster(){
		super.setLayout(new GridLayout(1,4));
		padding = BorderFactory.createEmptyBorder(0,10,0,10);
		JPanel defaultPanel;
		datasets = new DefaultCategoryDataset[4];
		labels = new String[4];
		countries = new LinkedList<String>();
		for (int i = 0; i < 4; i++){
			defaultPanel = new JPanel();
			defaultPanel.setSize(super.getWidth()/4, super.getHeight());
			super.add(defaultPanel, i);
		}
	}
	
	/*
	 * Method adds a new chart with label and dataset at the selected index along the cluster panel
	 */
	public void addChart(int index, String label, DefaultCategoryDataset data){
			try {
				super.remove(index);
			}
			catch (ArrayIndexOutOfBoundsException e){
				
			}
			ChartPanel chartPanel = new ChartPanel(ChartFactory.createBarChart(label,
						  "Countries", label, data, PlotOrientation.VERTICAL,
						   false, true, false));
			datasets[index] = data;
			labels[index] = label;
			chartPanel.setBorder(padding);
			/*
	        CategoryPlot plot = (CategoryPlot)chart.getPlot();
	        CategoryAxis xAxis = (CategoryAxis)plot.getDomainAxis();
	        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); */
			CategoryPlot plot = (CategoryPlot)chartPanel.getChart().getPlot();
			CategoryAxis xAxis = (CategoryAxis)plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
			
			super.add(chartPanel, index);
			super.updateUI();
	}
	
	/*
	 * Method to remove a particular chart according to its index along the cluster panel
	 */
	public void removeChart(int index){
		JPanel defaultPanel = new JPanel();
		defaultPanel.setSize(super.getWidth()/4, super.getHeight());
		super.remove(index);
		super.add(defaultPanel, index);
		datasets[index] = null;
		labels[index] = null;
		super.updateUI();
	}
	
	/*
	 * Method to add a country to a graph. Will be called when a new country is selected in the UI
	 */
	public void addToDataset(int index, int value, String country){
		countries.add(country);
		datasets[index].setValue(value,labels[index], country.substring(0,4));
	}
	
	/*
	 * Method to remove a country from a graph. Will be called when a country is deselected
	 */
	public void removeFromDataset(int index, String country){
		countries.remove(country);
		datasets[index].removeColumn(country.substring(0,4));
	}
	
	/*
	 * Method to test barchartCluster
	 */
	public void test(){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue(6, "Profit", "Jane");
		dataset.setValue(7, "Profit", "Tom");
		dataset.setValue(8, "Profit", "Jill");
		dataset.setValue(5, "Profit", "John");
		dataset.setValue(12, "Profit", "Fred");
		
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
		dataset2.setValue(6, "Sex appeal", "Jane");
		dataset2.setValue(56, "Sex appeal", "Tom");
		dataset2.setValue(8, "Sex appeal", "Jill");
		dataset2.setValue(2, "Sex appeal", "John");
		dataset2.setValue(16, "Sex appeal", "Fred");
		
		DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
		dataset3.setValue(90, "Height", "Jane");
		dataset3.setValue(11, "Height", "Tom");
		dataset3.setValue(100, "Height", "Jill");
		dataset3.setValue(150, "Height", "John");
		dataset3.setValue(19, "Height", "Fred");
		
		DefaultCategoryDataset dataset4 = new DefaultCategoryDataset();
		dataset4.setValue(6, "Hand Span", "Jane");
		dataset4.setValue(56, "Hand Span", "Tom");
		dataset4.setValue(23, "Hand Span", "Jill");
		dataset4.setValue(53, "Hand Span", "John");
		dataset4.setValue(12, "Hand Span", "Fred");
		
		this.addChart(0, "Profit", dataset);
		this.addChart(1, "Sex Appeal", dataset2);
		this.addChart(2, "Height", dataset3);
		this.addChart(3, "Hand Span", dataset4);
		
	}
	
	/*
	 * Test method to initialise the graphs so they can accept countries being added.
	 */
	public void countryAddTest(){
		DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
		DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
		DefaultCategoryDataset dataset4 = new DefaultCategoryDataset();
		datasets[0] = dataset1;
		datasets[1] = dataset2;
		datasets[2] = dataset3;
		datasets[3] = dataset4;
		labels[0] = "One";
		labels[1] = "Two";
		labels[2] = "Three";
		labels[3] = "Four";
		
		addChart(0, labels[0], dataset1);
		addChart(1, labels[1], dataset2);
		addChart(2, labels[2], dataset3);
		addChart(3, labels[3], dataset4);
		
	}
	
}
