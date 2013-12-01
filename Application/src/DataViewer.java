


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

//This gets the Main panel's position.


class DataViewer extends JFrame {

    private ScatterPlot scatterplot;  //Main Scatterplot
	
    
    private AdditionalPlots addPlots; //Additional Info (below scatterplot)
    private SelectPanel sp;			  //On the left the panel which helps select the data (properties countries)
    private Model model;

    public DataViewer(String filename) {
        model = new Model(filename);
        System.out.println(model.getTriple());
        addWindowListener(new WindowCloser());
        
       
        addPlots = new addPlots(model);
        model.addChild(addPlots);
        scatterplot = new ScatterPlot(model);
        model.addChild(scatterplot);
        sp = new SelectPanel(model);
        model.addChild(sp);
        
        // prep component layout
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
		
        contentPane.add("East", sp);
        contentPane.add("South", addplots);
        contentPane.add("Center", scatterplotPanel);

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
