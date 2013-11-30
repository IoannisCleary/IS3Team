import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;


public class ScatterPlotDriver {
	 public static void main(String[] args) {
		 	ArrayList<Double> x = new ArrayList<Double>();
		 	ArrayList<Double> y = new ArrayList<Double>();
		 	for(int i = 0; i < 100; i ++){
		 		double n = i;
		 		x.add(n+(Math.random()*5));
		 		y.add(n);
		 	}
		 	JFrame test = new JFrame();
		 	
	        ScatterPlot demo = new ScatterPlot("gold medals", "gold medals","Some shitty test graph" , x, y, 500, 500);
		    test.setLayout(new BorderLayout());    
	        test.add(demo, BorderLayout.CENTER);
	        test.repaint();
	        demo.setVisible(true);
	        
	        test.setVisible(true);
	    }
}
