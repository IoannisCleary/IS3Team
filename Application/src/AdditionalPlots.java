/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;


/**
 *
 * This class
 */

  class AdditionalPlots extends JPanel
        implements ViewController {
         
   
         private Model model;               
         private JPanel pContainer = new JPanel();
         private ArrayList<String> selected;       //Stores multiple countries

         
         public AdditionalPlots(Model m){
             
             model = m;             
             setBackground(Color.lightGray);             
             setLayout(new BorderLayout()); 
             pContainer. setLayout(new BoxLayout(pContainer, BoxLayout.PAGE_AXIS));
            
             selected = new ArrayList<String>(); 
     
  
  
     
			}	
     
     
     public void update(ArrayList<String> select) {
                selected = select;
			}
}

 

  
         
         
         
         
         
         
         
         
         
        
  
