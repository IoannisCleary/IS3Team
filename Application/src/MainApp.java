

import javax.swing.JFileChooser;

/* 
   MAIN.java
   
   Gets the file name and instantiates the viewer class

 */

public class MainApp {
  public static void main(String args[]) {

    JFileChooser chooser = new JFileChooser(); 
    chooser.showOpenDialog(chooser);
	DataViewer viewer = new DataViewer(chooser.getSelectedFile().getAbsolutePath());

  }
}





