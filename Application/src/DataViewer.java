
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

//This gets the Main panel's position.


class DataViewer extends JFrame {

    private ScatterPlot scatterplot;  //Main Scatterplot
    private ArrayList<ScatterPlot> addPlots; //Additional Info (below scatterplot) as an array of ScatterPlot
    private SelectPanel sp;			  //On the left the panel which helps select the data (properties countries)
    private Model model;

    public DataViewer(String filename) {
        model = new Model(filename);
        System.out.println(model.getTriple());
        addWindowListener(new WindowCloser());
        
        
        //will wrap this in for loop
        addPlots.add(new ScatterPlot("gold medals", "gold medals","Some shitty test graph" , x, y, null, 500, 500));
        //this is big one
        scatterplot = new ScatterPlot("gold medals", "gold medals","Some shitty test graph" , x, y, null, 500, 500);

        
        // addPlots = new AdditionalPlots(model);
       // scatterplot = new ScatterplotPanel(model);
       /* model.addChild(scatterplot);

        for(ScatterPlot sPlot : addPlots){
            model.addChild(sPlot);

        }
        sp = new SelectPanel(model);
        model.addChild(sp);
        */
        // prep component layout
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
		
        contentPane.add("East", sp);
        JFrame scatterplotPanel = new JFrame();
        scatterplotPanel.setLayout(new GridLayout(4,1));
        for(ScatterPlot plot : addPlots){
        contentPane.add("South", plot);
        }
        contentPane.add("Center", scatterplot);

        final int DEFAULT_FRAME_WIDTH = 900;
        final int DEFAULT_FRAME_HEIGHT = 700;

        setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
        setTitle("IS3 Data Viewer");
        setVisible(true);
       
        
        
    }

    private class WindowCloser extends WindowAdapter {

        public void windowClosing(WindowEvent event) {
            System.exit(0);
        }
    }
} /* end of DataViewer */
