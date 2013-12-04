import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

/**
 * Class to test scatter plot.
 * @author Matthew Bown
 *
 */
public class ScatterPlotDriver {

	 public static void main(String[] args) {
		 	ArrayList<Double> x = new ArrayList<Double>();
		 	ArrayList<Double> y = new ArrayList<Double>();
		 	for(int i = 0; i < 100; i ++){
		 		double n = i;
		 		x.add(n+(Math.random()*50));
		 		y.add(n+(Math.random()*50));
		 		
		 	}
		 	ArrayList<String> countries = new ArrayList<String>();
		 	countries.add("Wales");
		 	countries.add("Scotland");
		 	countries.add("England");
		 	countries.add("Ireland");
		 	countries.add("Japan");
		 	countries.add("Albania");
		 	countries.add("France");
		 	countries.add("Romaina");
		 	countries.add("Bulgaria");
		 	countries.add("Sweden");
		 	countries.add("Cyprus");
		 	countries.add("Turkey");
		 	countries.add("China");
		 	countries.add("Greece");
		 	countries.add("Australia");
		 	
		 	JFrame test = new JFrame();
	        ScatterPlot demo = new ScatterPlot("silver medals", "gold medals","Some shitty test graph" , x, y,countries, 500, 500);

	       // ScatterPlotTest demo = new ScatterPlotTest("gold medals", "gold medals","Some shitty test graph" , x, y, 500, 500);
		    test.setLayout(new BorderLayout());    
	        test.add(demo, BorderLayout.CENTER);
	        test.repaint();
	        test.pack();
	        demo.setVisible(true);
	        
	        test.setVisible(true);
	    }
}
