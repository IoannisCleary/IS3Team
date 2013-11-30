import java.util.Random;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.Rotation;
public class JFreeChartTest extends JFrame {
	private static final Random r = new Random();

	public JFreeChartTest(String applicationTitle, String chartTitle) {
        super(applicationTitle);
        // This will create the dataset 
        XYDataset dataset = createDataset1();
        // based on the dataset we create the chart
        JFreeChart chart = createChart(dataset, chartTitle);
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        // add it to our application
        setContentPane(chartPanel);

    }
    private  PieDataset createDataset() {
        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("Linux", 29);
        result.setValue("Mac", 20);
        result.setValue("Windows", 51);
        return result;
        
    }
    private static XYDataset createDataset1() {
        XYSeriesCollection result = new XYSeriesCollection();
        XYSeries series = new XYSeries("Random");
        for (int i = 0; i <= 100; i++) {
            double x = r.nextDouble();
            double y = r.nextDouble();
            series.add(x, y);
        }
        result.addSeries(series);
        return result;
    }
 private JFreeChart createChart(XYDataset dataset, String title) {
        JFreeChart chart = ChartFactory.createScatterPlot("test", "x variable", "y variable", dataset, PlotOrientation.HORIZONTAL , false, true, false);
        
           
        ChartFrame plot = new ChartFrame("test",chart);
       
        return chart;
        
    }

	
	
}
