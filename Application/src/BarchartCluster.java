import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Class to create cluster of barcharts.
 * @author Gavin Davidson
 *
 */
public class BarchartCluster extends JPanel{

	Border padding;
	
	/*
	 * Constructor for cluster panel. For loop is used to make placeholders for graphs so that they
	 * are in the right position even if they are created in the wrong order.
	 */
	public BarchartCluster(){
		super.setLayout(new GridLayout(1,4));
		padding = BorderFactory.createEmptyBorder(0,10,0,10);
		JPanel defaultPanel;
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
			chartPanel.setBorder(padding);
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
		super.updateUI();
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
	
}
