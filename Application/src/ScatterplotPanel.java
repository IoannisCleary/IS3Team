
import java.util.ArrayList;
import javax.swing.JPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Specter
 */
public class ScatterplotPanel extends JPanel
        implements ViewController   {
     private Model model;
     private ScatterPlot scatterplot;
     String xVariable; 
     String yVariable; 
     String chartTitle;
     ArrayList<Double> x; 
     ArrayList<Double> y; 
     int xSize; int ySize;
    
    public ScatterplotPanel(Model m) {
        this.model = m;
        scatterplot = new ScatterPlot(xVariable, yVariable, chartTitle, x, y, xSize, ySize);
    }

   
    public void update(ArrayList<String> SelectedC) {
        
        
        
    }
    
}
