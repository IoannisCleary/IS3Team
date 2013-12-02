import java.awt.Shape;
import java.util.ArrayList;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.ShapeUtilities;
import org.jfree.chart.JFreeChart;


public class ScatterPlot extends ChartPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public ScatterPlot(String xVariable, String yVariable, String chartTitle, ArrayList<Double> xVal, ArrayList<Double> yVal, ArrayList<String> countries, int xSize, int ySize) {
		super(null);
		
		ArrayList<XYDataset> dataset = new ArrayList<XYDataset>();
		// This will create the dataset 
		for(int i = 0; i < countries.size(); i++){
			String country = countries.get(i);
			double x =  xVal.get(i);
			double y = yVal.get(i);
			int j = 20;
			j = j-country.length();
			String space = "";
			for(int k =0; k< j;k++){
				space+= " ";
			}
			int xSpaceCount = 10;
			String xSpace = "";
			int xTemp = (int)x;
			while(xTemp >0){
				xTemp /= 10;
				xSpaceCount--;
			}
			for(int k = 0; k < xSpaceCount; k++){
				xSpace+=" ";
			}

			System.out.printf("%s%s%s: %2.2f%s%s: %2.2f\n",country,space,xVariable,x,xSpace,yVariable,y);
			dataset.add(createDataset(country, x, y ));
		}
	      // based on the dataset we create the chart
        JFreeChart chart = createChart(dataset,chartTitle, xVariable, yVariable);
        // we put the chart into a panel
        this.setChart(chart);
        // default size
        this.setPreferredSize(new java.awt.Dimension(xSize, ySize));
    }

    private  XYDataset createDataset(String country, double xVar, double yVar) {
        XYSeriesCollection result = new XYSeriesCollection();
        XYSeries series = new XYSeries(country);
        series.setDescription(country);
        series.add(xVar,yVar);
        result.addSeries(series);
        return result;
        
    }
   

 private JFreeChart createChart(ArrayList<XYDataset> dataset, String title, String xVariable, String yVariable) {
	// Create a single plot containing both the scatter and line
	 XYPlot plot = new XYPlot();
	// ArrayList<XYItemRenderer> rend = new ArrayList<XYItemRenderer>();
	 ValueAxis domain = new NumberAxis(xVariable);
	 ValueAxis range = new NumberAxis(yVariable);

	 /* SETUP SCATTER */
/*	 XYItemRenderer render = new XYLineAndShapeRenderer(false, true);
	 plot.setDataset(0, dataset.get(0));
	 plot.setRenderer(0,render); */
	 plot.setDomainAxis(0,domain);
	 plot.setRangeAxis(0,range);
	 
	 plot.mapDatasetToDomainAxis(0, 0);
	 plot.mapDatasetToRangeAxis(0, 0);
	 

	 for(int i = 0; i < dataset.size(); i++){
		 XYItemRenderer renderer = new XYLineAndShapeRenderer(false, true);
		 Shape circle = ShapeUtilities.createDiamond((float) 5.5);
		 
		 XYToolTipGenerator tt1 = new XYToolTipGenerator() {
	         public String generateToolTip(XYDataset dataset, int series, int item) {
	            StringBuffer sb = new StringBuffer();
	            Number x = dataset.getX(0, 0);
	            Number y = dataset.getY(0, 0);
	            XYSeriesCollection temp = (XYSeriesCollection) dataset;
	            String s = temp.getSeries(0).getDescription();
	            
	            sb.append("<html><p style='color:blue;'>"+s+"</p><br />");
	            sb.append(String.format("X: %.2f <br />", x.doubleValue()));
	            sb.append(String.format("Y: %.2f</html>", y.doubleValue()));
	            return sb.toString();
	         }
	      };
		 
		 
		 
		 renderer.setBaseToolTipGenerator(tt1);;
		 renderer.setSeriesShape(0,circle);
		 plot.setDataset(i, dataset.get(i));
		 plot.setRenderer(i, renderer);

		 plot.mapDatasetToDomainAxis(0, 0);
		 plot.mapDatasetToRangeAxis(0, 0);
		
	 }
	 //plot.
	 
	 

	 // Create the scatter data, renderer, and axis
	
	 // Create the chart with the plot and a legend
	 JFreeChart chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
	// JFreeChart chart = ChartFactory.createScatterPlot(title, xVariable, yVariable, dataset, PlotOrientation.VERTICAL , false, true, true);
        
           chart.setTextAntiAlias(true);
        //ChartFrame plot = new ChartFrame("test",chart);
           
        return chart;
        
    }

	
	
}
