import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
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


	private static final long serialVersionUID = 7503409692256751337L;
	Border padding;
	ChartPanel[] chartPanels;
	DefaultCategoryDataset[] datasets;
	String[] labels;
	LinkedList<String> countries;
	final StandardChartTheme chartTheme;
	
	
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
		
		chartTheme = (StandardChartTheme)org.jfree.chart.StandardChartTheme.createJFreeTheme();
		
		/*
		 * Ioannis
		 * Here is where to mess around with the fonts for the barcharts. I don't know if this is the right font for you 
		 */
        final Font extraLargeFont = new Font("sansserif", Font.BOLD, 15);
        final Font largeFont = new Font("sansserif", Font.PLAIN, 14);
        final Font regularFont = new Font("sansserif", Font.PLAIN, 12);
        final Font smallFont = new Font("sansserif", Font.PLAIN, 10);

        chartTheme.setExtraLargeFont(extraLargeFont);
        chartTheme.setLargeFont(largeFont);
        chartTheme.setRegularFont(regularFont);
        chartTheme.setSmallFont(smallFont);
	}
	
	/*
	 * Method adds a new chart with label and dataset at the selected index along the cluster panel
	 */
	public void addChart(int index, String label, DefaultCategoryDataset data){
			try {
				super.remove(index);
			}
			catch (ArrayIndexOutOfBoundsException e){
				
			}/*
			ChartPanel chartPanel = new ChartPanel(ChartFactory.createBarChart(label,
						  "Countries", label, data, PlotOrientation.VERTICAL,
						   false, true, false));*/
			JFreeChart chart = ChartFactory.createBarChart(label,
					  "Countries", label, data, PlotOrientation.VERTICAL,
					   false, true, false);
			
			chartTheme.apply(chart);
			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.addMouseListener(new MouseListener(){
				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					JFrame popUp = new JFrame();
					popUp.setSize(500, 500);
					ChartPanel panel = (ChartPanel)arg0.getComponent();
					JFreeChart chart = panel.getChart();
					popUp.add(new ChartPanel(chart));
					popUp.setVisible(true);
					
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
			datasets[index] = data;
			labels[index] = label;
			CategoryPlot plot = (CategoryPlot)chartPanel.getChart().getPlot();
			CategoryAxis xAxis = (CategoryAxis)plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
			chartPanel.setBackground(new Color(240,240,240));
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
	
	public void removeAllCharts(){
		for (int i = 0; i < 4; i++){
			removeChart(i);
		}
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
	


	
}