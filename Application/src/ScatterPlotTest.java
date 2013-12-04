
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;

import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.JFreeChart;

/**
 * Class to test scatter plot.
 * @author Matthew Bown
 *
 */


public class ScatterPlotTest extends ChartPanel {

	private static final long serialVersionUID = -5144781252801692212L;

	public ScatterPlotTest(String xVariable, String yVariable, String chartTitle, ArrayList<Double> x, ArrayList<Double> y, int xSize, int ySize) {
		super(null);
		// This will create the dataset 
        XYDataset dataset = createDataset(x,y);
        XYDataset dataset2 = createDataset1(y, x);
        XYDataset dataset3 = createDataset2(x,y);
        // based on the dataset we create the chart
        JFreeChart chart = createChart(dataset,dataset2,dataset3,chartTitle, xVariable, yVariable);
        // we put the chart into a panel
        this.setChart(chart);
        // default size
        this.setPreferredSize(new java.awt.Dimension(xSize, ySize));
        // add it to our application

    }

    private static XYDataset createDataset(ArrayList<Double> xVar, ArrayList<Double> yVar) {
        XYSeriesCollection result = new XYSeriesCollection();
        XYSeries series = new XYSeries("Japan");
        for (int i = 0; i < 10; i++) {
            double x = xVar.get(i)*30;
            double y = yVar.get(i)*40;
            series.add(x, y);
        }
        
        result.addSeries(series);
        return result;
        
    }
    private static XYDataset createDataset1(ArrayList<Double> xVar, ArrayList<Double> yVar) {
        XYSeriesCollection result = new XYSeriesCollection();
        XYSeries series = new XYSeries("Great Britain");
        for (int i = 0 ; i < 1; i++) {
            double x = xVar.get(i)*50;
            double y = yVar.get(i)*50;
            series.add(60.0, 50.0);
        }
        
        result.addSeries(series);
        return result;
        
    }
    private static XYDataset createDataset2(ArrayList<Double> xVar, ArrayList<Double> yVar) {
        XYSeriesCollection result = new XYSeriesCollection();
        XYSeries series = new XYSeries("Ireland");
        for (int i = 0; i < 1; i++) {
            double x = xVar.get(i)*100;
            double y = yVar.get(i)*50;
            series.add(70.0, 40.0);
        }
        
        result.addSeries(series);
        return result;
        
    }
 private JFreeChart createChart(XYDataset dataset, XYDataset dataset1, XYDataset dataset3, String title, String xVariable, String yVariable) {
	// Create a single plot containing both the scatter and line
	 XYPlot plot = new XYPlot();

	 /* SETUP SCATTER */
/*
	 // Create the scatter data, renderer, and axis
	 XYItemRenderer renderer1 = new XYLineAndShapeRenderer(false, true);   // Shapes only
	 ValueAxis domain1 = new NumberAxis("Domain1");
	 ValueAxis range1 = new NumberAxis("Range1");
	 // Set the scatter data, renderer, and axis into plot
	 plot.setDataset(0, dataset);
	 plot.setRenderer(0, renderer1);
	 plot.setDomainAxis(0, domain1);
	 plot.setRangeAxis(0, range1);

	 // Map the scatter to the first Domain and first Range
	 plot.mapDatasetToDomainAxis(0, 0);
	 plot.mapDatasetToRangeAxis(0, 0);

	 /* SETUP LINE */

	 // Create the line data, renderer, and axis
	 XYItemRenderer renderer2 = new XYLineAndShapeRenderer(false, true);   // Lines only
	 XYLineAndShapeRenderer renderer3 = new XYLineAndShapeRenderer(false, true);
	 ArrayList<XYDataset> test = new ArrayList<XYDataset>();
	 ArrayList<XYItemRenderer> rend = new  ArrayList<XYItemRenderer>();
	 rend.add(renderer2);
	 rend.add(renderer3);
	 test.add(dataset1);
	 test.add(dataset3);
	 for(int i = 1; i < test.size(); i++){
	 // Set the line data, renderer, and axis into plot
	 plot.setDataset(i, test.get(i));
	 plot.setRenderer(i, rend.get(i-1));
	// plot.setDomainAxis(1, domain2);
	// plot.setRangeAxis(1, range2);
	 
	// Map the line to the second Domain and second Range
		 plot.mapDatasetToDomainAxis(0, 0);
		 plot.mapDatasetToRangeAxis(0, 0);

	 }
	 // Map the line to the second Domain and second Range
	/* plot.mapDatasetToDomainAxis(0, 0);
	 plot.mapDatasetToRangeAxis(0, 0);

	 XYLineAndShapeRenderer renderer3 = new XYLineAndShapeRenderer(false, true);

	 plot.setDataset(2, dataset3);
	 plot.setRenderer(2, renderer3);
	
	
	 plot.mapDatasetToDomainAxis(0, 0);
	 plot.mapDatasetToRangeAxis(0, 0);*/
	 // Create the chart with the plot and a legend */
	// JFreeChart chart = new JFreeChart("Multi Dataset Chart", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
	 JFreeChart chart = ChartFactory.createScatterPlot(title, xVariable, yVariable, dataset, PlotOrientation.VERTICAL , false, true, true);
	 chart.setTextAntiAlias(true);
        //ChartFrame plot = new ChartFrame("test",chart);
        return chart;
        
    }

	
	
}
