import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.JFreeChart;
public class ScatterPlot extends ChartPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5144781252801692212L;

	public ScatterPlot(String xVariable, String yVariable, String chartTitle, ArrayList<Double> x, ArrayList<Double> y, int xSize, int ySize) {
		super(null);
		// This will create the dataset 
        XYDataset dataset = createDataset(x,y);
        // based on the dataset we create the chart
        JFreeChart chart = createChart(dataset,chartTitle, xVariable, yVariable);
        // we put the chart into a panel
        this.setChart(chart);
        // default size
        this.setPreferredSize(new java.awt.Dimension(xSize, ySize));
        // add it to our application

    }

    private static XYDataset createDataset(ArrayList<Double> xVar, ArrayList<Double> yVar) {
        XYSeriesCollection result = new XYSeriesCollection();
        XYSeries series = new XYSeries("Country");
        for (int i = 0; i < Math.min(xVar.size(), yVar.size())-1; i++) {
            double x = xVar.get(i);
            double y = yVar.get(i);
            series.add(x, y);
        }

        result.addSeries(series);
        return result;
        
    }
 private JFreeChart createChart(XYDataset dataset, String title, String xVariable, String yVariable) {
       JFreeChart chart = ChartFactory.createScatterPlot(title, xVariable, yVariable, dataset, PlotOrientation.VERTICAL , false, false, false);
        
           chart.setTextAntiAlias(true);
           
        //ChartFrame plot = new ChartFrame("test",chart);
           
        return chart;
        
    }

	
	
}
