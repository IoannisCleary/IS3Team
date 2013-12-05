import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JMenu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Label;
import java.awt.Font;
import java.awt.Color;
import java.awt.ScrollPane;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import java.awt.SystemColor;

import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import java.awt.ComponentOrientation;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;

import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;
/**
 * Class for creating the main window and GUI.
 * @author Ioannis Cleary and Luka and Georgios and all of us
 *
 */
public class Main {
	private static Model m ;
	private JMenuItem Open;
	private int Num_Select_Country=0;
	private int Num_Select_Opts=0;
	private ArrayList<String> selectedC=new ArrayList<String>();
	private ArrayList<String> selectedYAxis=new ArrayList<String>();
	private ArrayList<String> selectedXAxis=new ArrayList<String>();
	private ArrayList<String> selectedOpts=new ArrayList<String>();
	private JFrame frmIsPrototype;
	private JLabel op1,op2,op3,op4;
	private String[] axisOpt;
	private ArrayList<String> countryNames;
	private ScatterPlot sc;
	private JButton submit;
	private JPanel DrawingArea;
	private JPanel scatterPanel;
	private BarchartCluster barchartPanel;
	/*
         *  Variables used for updating the scatterplot 
         */
        String xLabel;
        String yLabel;
        ArrayList<String[]> dataArray;
        ArrayList<Double> xVal;
        ArrayList<Double> yVal;
        ArrayList<String> matchedC;

	private final JTextArea msgbox = new JTextArea();
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		
		/*
		* Code to choose the csv file
		**/
		String filepath = "mergedFile.csv";
		File def = new File(filepath);
		if(!def.exists()){
			
		
		JFileChooser chooser = new JFileChooser(); 
       int ret=chooser.showOpenDialog(chooser);
       if (ret==1){
    	   JOptionPane.showMessageDialog(null,"No file has been selected, system will exit");
       System.exit(0);}
       
       filepath=chooser.getSelectedFile().getAbsolutePath();
       String ending=filepath.substring(filepath.length()-4,filepath.length());
//       System.out.println(ending);
       while(ending.compareTo(".csv")!=0){
    	   JOptionPane.showMessageDialog(null,"File is not valid, make sure it's a .csv file");
    	   ret=chooser.showOpenDialog(chooser); 
    	   if(ret==1){  JOptionPane.showMessageDialog(null,"No file has been selected, system will exit"); System.exit(0);}
    	   filepath=chooser.getSelectedFile().getAbsolutePath();
    	   ending=filepath.substring(filepath.length()-4,filepath.length());
       }
		}
      
         m = new Model(filepath); 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main(); // Main Window
					window.frmIsPrototype.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void removelabel(String text) {
		if(op1.getText().compareTo(text)==0)op1.setText("no selection");
		else if(op2.getText().compareTo(text)==0)op2.setText("no selection");
		else if(op3.getText().compareTo(text)==0)op3.setText("no selection");
		else if(op4.getText().compareTo(text)==0)op4.setText("no selection");
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Create the application.
	 */
	
	public Main() {
		 countryNames = m.getCountries(); // Get Countries from file
			initialize();
	}
		
              
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String[] tempOpt=m.getLabels();
		axisOpt=new String[tempOpt.length-58];
		for(int i=0;i<axisOpt.length;i++){
			axisOpt[i]=tempOpt[i+4];
		}
		frmIsPrototype = new JFrame(); // Main frame
		frmIsPrototype.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIsPrototype.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frmIsPrototype.setBackground(UIManager.getColor("Button.shadow"));
		frmIsPrototype.getContentPane().setBackground(new Color(247, 247, 247));
		frmIsPrototype.setTitle("IS3 Prototype");
		frmIsPrototype.setBounds(100, 100,1337, 740);
		
		final JMenuBar menuBar = new JMenuBar(); // menu bar
		menuBar.setBackground(UIManager.getColor("MenuBar.borderColor"));
		frmIsPrototype.setJMenuBar(menuBar);
		
		final JMenu mnFile = new JMenu("File");  // File option on the menu bar
		mnFile.setFont(new Font("SansSerif", Font.BOLD, 12));
		mnFile.setBackground(new Color(153, 204, 255));
		menuBar.add(mnFile); // adding to menu bar
		
		final JMenuItem mntmExit = new JMenuItem("Exit"); // creating exit button and adding it to the File menu option
		mntmExit.setBackground(new Color(240, 240, 240));
		mntmExit.setFont(new Font("SansSerif", Font.BOLD, 13));
		mntmExit.setHorizontalAlignment(SwingConstants.LEFT);
		mntmExit.addMouseListener(new MouseAdapter() {
		
			public void mouseReleased(MouseEvent e) {
				System.exit(0);
			}
			
		});
		
		Open = new JMenuItem("Open File...");
		
		Open.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			//	 System.out.println("sadasd");
		         String filepath;
		         JFileChooser chooser = new JFileChooser(); 
		              int ret=chooser.showOpenDialog(chooser);
		              boolean selected = true;
		              if (ret==1){
		                //JOptionPane.showMessageDialog(null,"No file has been selected");
		               selected =false;
		              }
		              if(selected){
		              filepath=chooser.getSelectedFile().getAbsolutePath();
		              String ending=filepath.substring(filepath.length()-4,filepath.length());
		 //             System.out.println(ending);
		              while(ending.compareTo(".csv")!=0){
		                JOptionPane.showMessageDialog(null,"File is not valid, make sure it's a .csv file");
		                ret=chooser.showOpenDialog(chooser); 
		                if(ret==1){  JOptionPane.showMessageDialog(null,"No file has been selected, system will exit"); System.exit(0);}
		                filepath=chooser.getSelectedFile().getAbsolutePath();
		                ending=filepath.substring(filepath.length()-4,filepath.length());
		              }
		              m = new Model(filepath); 
		              }
			}});
		Open.setHorizontalAlignment(SwingConstants.LEFT);
		Open.setFont(new Font("SansSerif", Font.BOLD, 13));
		Open.setBackground(SystemColor.menu);
		mnFile.add(Open);
		mnFile.add(mntmExit); // actual addition to the menu
		
		JMenu mnHelp = new JMenu("Help"); // Help option on the menu bar
		mnHelp.setFont(new Font("SansSerif", Font.BOLD, 12));
		menuBar.add(mnHelp);
		
		JMenuItem Manual = new JMenuItem("Manual"); // creating Manual button and adding it to the Help menu option
		Manual.setFont(new Font("SansSerif", Font.BOLD, 13));
		Manual.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				JOptionPane.showMessageDialog(frmIsPrototype," \n To run this software you need to follow these steps \n\n 1. Select a country/countries by clicking on the Countries button from the Legend: Drawing Area. \n 2. Select values for the X and Y axis by clicking the Axis button from the Legend: Drawing Area. \n\n (A Scatter plot should have been created by now on the Drawing Area and corresonding values should be displayed on the right.) \n \n 3. You can select additional information other than the default by clicking the Options button from the Legend: Additional Information.\n\n (This Legend will now display your selections and bar graphs should be generated on the left) \n\n 4. There are 2 clear buttons the one on the bottom of the screen clears only the Additinal Information, the other one clears everything. \n \n");

			}
		});
		mnHelp.add(Manual);// actual addition to the menu
		
		JMenuItem About = new JMenuItem("About");  // creating About button and adding it to the Help menu option
		About.setFont(new Font("SansSerif", Font.BOLD, 13));
		About.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				JOptionPane.showMessageDialog(frmIsPrototype," This software was made by \n Ioannis Cleary(1104205c), Matthew Bown(1002723b), \n Gavin Davidson(1102607d), Luka Prelic(1107827p), \n Georgios Moleskis(1103614m) and Mario Moro Hernandez(1106740m) \n");

			}
		});
		mnHelp.add(About); // actual addition to the menu
		
                //bar chart creation
		barchartPanel = new BarchartCluster(); // panel 
		barchartPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		barchartPanel.setBackground(new Color(245, 245, 245));
		
		/*
		* Submit button updates the scatterplot with the selected data
		**/
		
		JPanel Addinfo = new JPanel(); // Additional Information Panel
		Addinfo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		Addinfo.setBackground(new Color(240, 240, 240));
		
		DrawingArea = new JPanel(); // Drawing Area Panel
		DrawingArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		DrawingArea.setBackground(new Color(245, 245, 245));

		JPanel LegendAdd = new JPanel(); // Legend: Additional Information Panel creation
		LegendAdd.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		LegendAdd.setBackground(new Color(240, 240, 240));
		GroupLayout groupLayout = new GroupLayout(frmIsPrototype.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(Addinfo, 0, 0, Short.MAX_VALUE)
						.addComponent(DrawingArea, GroupLayout.DEFAULT_SIZE, 1010, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(LegendAdd, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(LegendAdd, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(DrawingArea, GroupLayout.PREFERRED_SIZE, 410, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Addinfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
		JLabel lblDrawingArea = new JLabel("Scatter Plot "); // Drawing area title
		lblDrawingArea.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblDrawingArea.setLabelFor(DrawingArea);
		lblDrawingArea.setBackground(Color.GRAY);
		
		scatterPanel = new JPanel(); // Panel for scatter plot
		scatterPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scatterPanel.setSize(500,320);
		
		JPanel LegendDA = new JPanel(); // Creating Legend : Drawing Area
		LegendDA.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		LegendDA.setBackground(new Color(240, 240, 240));
		
			JPanel selections = new JPanel(); // Creating panel in Legend : Drawing Area where all the buttons are
			selections.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			selections.setBackground(new Color(245, 245, 245));
		
		JLabel lblLegendDrawng = new JLabel("Panel : Scatter Plot"); // Legend title
		lblLegendDrawng.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblLegendDrawng.setHorizontalAlignment(SwingConstants.CENTER);
		
		submit = new JButton("Generate");  // Submit button
		submit.setForeground(Color.BLACK);
		submit.setIcon((new ImageIcon(Main.class.getResource("/smallgen.png"))));

		submit.setFont(new Font("SansSerif", Font.BOLD, 14));
		submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
		//		System.out.println(selectedXAxis);
		//		System.out.println(selectedYAxis);
				if (!(selectedXAxis.isEmpty() && selectedYAxis.isEmpty()) && !selectedC.isEmpty()){
				
					scatterPanel.removeAll();
                   
                     
                     xVal = new ArrayList<Double>();
                     yVal = new ArrayList<Double>();
                     matchedC = new ArrayList<String>();
                     if(!selectedXAxis.isEmpty() && !selectedYAxis.isEmpty()){
                    	  xLabel = selectedXAxis.get(0);
                          yLabel = selectedYAxis.get(0);
                          dataArray = m.get2VarDataArray(m.getVarLab().get(xLabel), m.getVarLab().get(yLabel), m.getTriple());
                     for (int j = 0; j < selectedC.size();j++ )
                       for (int i = 0; i< dataArray.size(); i++){
                    	   if (dataArray.get(i)[1].equals(" ") || dataArray.get(i)[2].equals(" "))
                        	   continue;
                           if (selectedC.get(j).equals(dataArray.get(i)[0])){
                        	   matchedC.add(selectedC.get(j));
                               xVal.add(Double.parseDouble(dataArray.get(i)[1]));                          
                               yVal.add(Double.parseDouble(dataArray.get(i)[2]));
                           }
                       }
                     }
                     else  if(selectedYAxis.isEmpty()){
                    	 Double cnt=1.0;
                         xLabel = "Countries";
                    	 yLabel = selectedXAxis.get(0);
                    	 dataArray = m.get2VarDataArray(m.getVarLab().get(yLabel), "Factor1", m.getTriple());
                    	 for (int j = 0; j < selectedC.size();j++ )
                             for (int i = 0; i< dataArray.size(); i++){
                            	 if (dataArray.get(i)[1].equals(" ") || dataArray.get(i)[2].equals(" "))
                              	   continue;
                                 if (selectedC.get(j).equals(dataArray.get(i)[0])){
                                	 matchedC.add(selectedC.get(j));
                                	 xVal.add(cnt);
                                	 yVal.add(Double.parseDouble(dataArray.get(i)[1]));                          
                                     
                                     cnt++;

                                 }
                             }
                           }
                     else  if(selectedXAxis.isEmpty()){
                    	 Double cnt=1.0;

                          yLabel = selectedYAxis.get(0);
                          xLabel = "Countries";
                    	 dataArray = m.get2VarDataArray("Factor1", m.getVarLab().get(yLabel), m.getTriple());
                    	 for (int j = 0; j < selectedC.size();j++ )
                             for (int i = 0; i< dataArray.size(); i++){
                            	 if (dataArray.get(i)[1].equals(" ") || dataArray.get(i)[2].equals(" "))
                                	   continue;
                                 if (selectedC.get(j).equals(dataArray.get(i)[0])){
                                     matchedC.add(selectedC.get(j));
                                     xVal.add(cnt);
                                     yVal.add(Double.parseDouble(dataArray.get(i)[2]));                          
                                     cnt++;

                                 }
                             }
                           }
                                      	 
                                 	 
                     
				
                     
           //          System.out.println(matchedC.toString());
     
                     sc = new ScatterPlot(xLabel,yLabel,"" ,xVal, yVal,matchedC,scatterPanel.getWidth()-20,scatterPanel.getHeight()-25);
                     
                     msgbox.append("\n processed \n");
                     sc.setVisible(true);           
                     sc.setSize(scatterPanel.getWidth(),scatterPanel.getHeight());
                     sc.repaint();
                     scatterPanel.add(sc);
                     scatterPanel.repaint();
                     scatterPanel.doLayout();
                    
            //         frmIsPrototype.repaint();
       }}
			
		});
		
		JButton MainClear = new JButton("Start Over"); // Clear all selection button, clears all selections and resets labels
		MainClear.setForeground(Color.BLACK);
		MainClear.setIcon((new ImageIcon(Main.class.getResource("/refresh.png"))));
		
		MainClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				msgbox.setText("");
				Num_Select_Country=0;
				selectedC.clear();
				selectedYAxis.clear();
				selectedXAxis.clear();
				selectedOpts.clear();
				op1.setText("no selection");
				op2.setText("no selection");
				op3.setText("no selection");
				op4.setText("no selection");
				Num_Select_Opts=0;
				scatterPanel.removeAll();
				scatterPanel.repaint();
				if (barchartPanel.getComponentCount()>0){
				barchartPanel.removeAllCharts();}
				barchartPanel.repaint();
			}
		});
		MainClear.setFont(new Font("SansSerif", Font.BOLD, 14));
		GroupLayout gl_LegendDA = new GroupLayout(LegendDA);
		gl_LegendDA.setHorizontalGroup(
			gl_LegendDA.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_LegendDA.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_LegendDA.createParallelGroup(Alignment.TRAILING)
						.addComponent(selections, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLegendDrawng, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
						.addGroup(gl_LegendDA.createSequentialGroup()
							.addComponent(submit, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(MainClear, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_LegendDA.setVerticalGroup(
			gl_LegendDA.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_LegendDA.createSequentialGroup()
					.addComponent(lblLegendDrawng)
					.addGap(5)
					.addComponent(selections, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_LegendDA.createParallelGroup(Alignment.TRAILING)
						.addComponent(MainClear, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(submit, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		JButton CountryButton = new JButton("Countries"); // Countries selection button, window with check boxes is created and shown
		CountryButton.setIcon(new ImageIcon(Main.class.getResource("/countryIcon.png")));
		CountryButton.setForeground(Color.BLACK);

		CountryButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				msgbox.append("Countries Selected : ");
				msgbox.append("\n");
			//	msgbox.append(countryNames.toString());
				ScrollPane popupCountry=new ScrollPane();
				popupCountry.setWheelScrollingEnabled(true);
				JOptionPane dialCountry= new JOptionPane();
				dialCountry.setLayout(new BoxLayout(dialCountry, BoxLayout.Y_AXIS));
				JPanel tick=new JPanel();
				tick.setBounds(61, 11, 81, 140);
			    tick.setLayout(new BoxLayout(tick, BoxLayout.Y_AXIS));
				final JCheckBox cBox[] = new JCheckBox[countryNames.size()];
				for(int i=0;i<cBox.length;i++){
				cBox[i]=new JCheckBox(countryNames.get(i));
				if(selectedC.contains(countryNames.get(i))){cBox[i].setSelected(true);}
				final int a=i;
				cBox[i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(selectedC.contains(cBox[a].getText())){selectedC.remove(cBox[a].getText()); Num_Select_Country= Num_Select_Country-1;msgbox.append("\n"+"Removed : "+cBox[a].getText()+"\n");}
						else{
						selectedC.add(cBox[a].getText());
							msgbox.append("\n");
						msgbox.append(cBox[a].getText()+"  ");
						Num_Select_Country++;}
						if(selectedC.contains(cBox[a].getText()))cBox[a].setSelected(true);
						else{cBox[a].setSelected(false);}
					}});
				tick.add(cBox[i]);}
				popupCountry.add(tick);
				popupCountry.setBounds(100,100,400,400);
				popupCountry.setVisible(true);
			//	dialCountry.add(popupCountry);
				dialCountry.setBounds(100,100,400,400);
				Object[] options = {"Ok","Select All","Clear","Cancel"};
				int n = JOptionPane.showOptionDialog(null, popupCountry,"Countries", JOptionPane.YES_NO_CANCEL_OPTION,
                        	JOptionPane.PLAIN_MESSAGE,null,options,options[3]);
                                if (n == JOptionPane.NO_OPTION){
                                    selectedC = (ArrayList<String>) countryNames.clone();
                                    msgbox.append("All countries Selected");
                                }
                                else if (n == JOptionPane.CANCEL_OPTION){
                                    selectedC.clear();
                                }
				
			}
			
		});
		CountryButton.setFont(new Font("SansSerif", Font.BOLD, 13));
		
		JButton xAxisButton = new JButton("X Axis"); // X Axis selection button, window with check boxes is created and shown
		xAxisButton.setIcon((new ImageIcon(Main.class.getResource("/x axis.png"))));
		xAxisButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				msgbox.append("\n");
				msgbox.append("X Selected : ");
				msgbox.append("\n");
				ScrollPane popupAxis=new ScrollPane();
				JOptionPane dialCountry= new JOptionPane();
				dialCountry.setLayout(new BoxLayout(dialCountry, BoxLayout.Y_AXIS));
				JPanel tick=new JPanel();
				tick.setBounds(61, 11, 81, 140);
			    tick.setLayout(new BoxLayout(tick, BoxLayout.Y_AXIS));
			    final JRadioButton rBut[] = new JRadioButton[axisOpt.length];
			    final ButtonGroup xRadio = new ButtonGroup();
			    
				for(int i=0;i<rBut.length;i++){
				rBut[i]=new JRadioButton(axisOpt[i]);
				xRadio.add(rBut[i]);
				if(selectedXAxis.contains(axisOpt[i])){rBut[i].setSelected(true);}
				if((!selectedYAxis.isEmpty()&& selectedYAxis.contains(rBut[i].getText())) || (!selectedOpts.isEmpty()&& selectedOpts.contains(rBut[i].getText()))){rBut[i].setEnabled(false);}
				final int a=i;
				rBut[i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(rBut[a].isEnabled()){
							
							selectedXAxis.clear();
							selectedXAxis.add(rBut[a].getText());
							msgbox.append("\n");
							msgbox.append(rBut[a].getText());
							msgbox.append("\n");
							}
						
				}});
				tick.add(rBut[i]);
				}
				popupAxis.add(tick);
				popupAxis.setBounds(100,100,400,400);
				popupAxis.setVisible(true);
			//	dialCountry.add(popupCountry);
				dialCountry.setBounds(100,100,400,400);
				

				Object[] options = {"Ok","Clear","Cancel"};
				int n =JOptionPane.showOptionDialog(null, popupAxis,"X Axis", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE, null, options, options[2]);
				if(n==JOptionPane.NO_OPTION){
					selectedXAxis.clear();
					for(int i = 0; i < rBut.length;i++){
						rBut[i].setSelected(false);
					}
				}	
				
			}
		});
		xAxisButton.setFont(new Font("SansSerif", Font.BOLD, 13));
		
		
		JButton YAxisbtn = new JButton("Y Axis"); // Y Axis selection button, window with check boxes is created and shown
		YAxisbtn.setIcon((new ImageIcon(Main.class.getResource("/y axis.png"))));
		YAxisbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				msgbox.append("Y Selected : ");
				msgbox.append("\n");
				ScrollPane popupAxis=new ScrollPane();
				JOptionPane dialCountry= new JOptionPane();
				dialCountry.setLayout(new BoxLayout(dialCountry, BoxLayout.Y_AXIS));
				JPanel tick=new JPanel();
				tick.setBounds(61, 11, 81, 140);
			    tick.setLayout(new BoxLayout(tick, BoxLayout.Y_AXIS));
			    
				final JRadioButton rBut[] = new JRadioButton[axisOpt.length];
				final ButtonGroup Yradio = new ButtonGroup();
				for(int i=0;i<rBut.length;i++){
				rBut[i]=new JRadioButton(axisOpt[i]);
				Yradio.add(rBut[i]);
				if(selectedYAxis.contains(axisOpt[i])){rBut[i].setSelected(true);}
				if((!selectedOpts.isEmpty()&& selectedOpts.contains(rBut[i].getText())) || (!selectedXAxis.isEmpty()&& selectedXAxis.contains(rBut[i].getText()))){rBut[i].setEnabled(false);}
				final int a=i;
				rBut[i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(rBut[a].isEnabled()){
				
						selectedYAxis.clear();
						selectedYAxis.add(rBut[a].getText());
						msgbox.append("\n");
						msgbox.append(rBut[a].getText());
						msgbox.append("\n");
			
						}
						
					}});
				tick.add(rBut[i]);}
				popupAxis.add(tick);
				popupAxis.setBounds(100,100,400,400);
				popupAxis.setVisible(true);
				dialCountry.setBounds(100,100,400,400);
			
				Object[] options = {"Ok","Clear","Cancel"};
				int n =JOptionPane.showOptionDialog(null, popupAxis,"Y Axis", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE, null, options, options[2]);
				if(n==JOptionPane.NO_OPTION){
					selectedYAxis.clear();
					for(int i = 0; i < rBut.length;i++){
						rBut[i].setSelected(false);
					}
				}	
				
			}
		});;
				
	
		
			YAxisbtn.setFont(new Font("SansSerif", Font.BOLD, 13));
			
			JButton swapaxis = new JButton("Swap Axis");
			swapaxis.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					ArrayList<String> x= new ArrayList<String>(selectedXAxis);
					selectedXAxis = new ArrayList<String>(selectedYAxis);
					selectedYAxis = x;
					if (!(selectedXAxis.isEmpty() && selectedYAxis.isEmpty()) && !selectedC.isEmpty()){					
						scatterPanel.removeAll();
	                     xVal = new ArrayList<Double>();
	                     yVal = new ArrayList<Double>();
	                     matchedC = new ArrayList<String>();
	                     if(!selectedXAxis.isEmpty() && !selectedYAxis.isEmpty()){
	                    	  xLabel = selectedXAxis.get(0);
	                          yLabel = selectedYAxis.get(0);
	                          dataArray = m.get2VarDataArray(m.getVarLab().get(xLabel), m.getVarLab().get(yLabel), m.getTriple());
	                     for (int j = 0; j < selectedC.size();j++ )
	                       for (int i = 0; i< dataArray.size(); i++){
	                    	   if (dataArray.get(i)[1].equals(" ") || dataArray.get(i)[2].equals(" "))
	                        	   continue;
	                           if (selectedC.get(j).equals(dataArray.get(i)[0])){
	                        	   matchedC.add(selectedC.get(j));
	                               xVal.add(Double.parseDouble(dataArray.get(i)[1]));                          
	                               yVal.add(Double.parseDouble(dataArray.get(i)[2]));
	                           }
	                       }
	                     }
	                     else  if(selectedYAxis.isEmpty()){
	                    	 Double cnt=1.0;
	                         xLabel = "Countries";
	                    	 yLabel = selectedXAxis.get(0);
	                    	 dataArray = m.get2VarDataArray(m.getVarLab().get(yLabel), "Factor1", m.getTriple());
	                    	 for (int j = 0; j < selectedC.size();j++ )
	                             for (int i = 0; i< dataArray.size(); i++){
	                            	 if (dataArray.get(i)[1].equals(" ") || dataArray.get(i)[2].equals(" "))
	                              	   continue;
	                                 if (selectedC.get(j).equals(dataArray.get(i)[0])){
	                                	 matchedC.add(selectedC.get(j));
	                                	 xVal.add(cnt);
	                                	 yVal.add(Double.parseDouble(dataArray.get(i)[1]));                                                            
	                                     cnt++;
	                                 }
	                             }
	                           }
	                     else  if(selectedXAxis.isEmpty()){
	                    	 Double cnt=1.0;

	                          yLabel = selectedYAxis.get(0);
	                          xLabel = "Countries";
	                    	 dataArray = m.get2VarDataArray("Factor1", m.getVarLab().get(yLabel), m.getTriple());
	                    	 for (int j = 0; j < selectedC.size();j++ )
	                             for (int i = 0; i< dataArray.size(); i++){
	                            	 if (dataArray.get(i)[1].equals(" ") || dataArray.get(i)[2].equals(" "))
	                                	   continue;
	                                 if (selectedC.get(j).equals(dataArray.get(i)[0])){
	                                     matchedC.add(selectedC.get(j));
	                                     xVal.add(cnt);
	                                     yVal.add(Double.parseDouble(dataArray.get(i)[2]));                          
	                                     cnt++;
	                                 }
	                             }
	                           }
	                     sc = new ScatterPlot(xLabel,yLabel,"" ,xVal, yVal,matchedC,scatterPanel.getWidth()-20,scatterPanel.getHeight()-25);
	                     
	                     msgbox.append("\n Axis Swapped \n");
	                     sc.setVisible(true);           
	                     sc.setSize(scatterPanel.getWidth(),scatterPanel.getHeight());
	                     sc.repaint();
	                     scatterPanel.add(sc);
	                     scatterPanel.repaint();
	                     scatterPanel.doLayout();
	       }
				}
			});
			swapaxis.setFont(new Font("SansSerif", Font.BOLD, 13));
			GroupLayout gl_selections = new GroupLayout(selections);
			gl_selections.setHorizontalGroup(
				gl_selections.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_selections.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_selections.createParallelGroup(Alignment.TRAILING)
							.addComponent(swapaxis, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
							.addComponent(CountryButton, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
							.addComponent(xAxisButton, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
							.addComponent(YAxisbtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
						.addContainerGap())
			);
			gl_selections.setVerticalGroup(
				gl_selections.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_selections.createSequentialGroup()
						.addContainerGap(6, Short.MAX_VALUE)
						.addComponent(CountryButton, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(xAxisButton, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(YAxisbtn, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(swapaxis, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
			);
			selections.setLayout(gl_selections);
			LegendDA.setLayout(gl_LegendDA);
		GroupLayout gl_DrawingArea = new GroupLayout(DrawingArea);
		gl_DrawingArea.setHorizontalGroup(
			gl_DrawingArea.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_DrawingArea.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_DrawingArea.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_DrawingArea.createSequentialGroup()
							.addComponent(scatterPanel, GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(LegendDA, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_DrawingArea.createSequentialGroup()
							.addComponent(lblDrawingArea, GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
							.addGap(495))))
		);
		gl_DrawingArea.setVerticalGroup(
			gl_DrawingArea.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_DrawingArea.createSequentialGroup()
					.addGap(6)
					.addComponent(lblDrawingArea)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_DrawingArea.createParallelGroup(Alignment.LEADING)
						.addComponent(scatterPanel, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
						.addComponent(LegendDA, GroupLayout.PREFERRED_SIZE, 370, Short.MAX_VALUE))
					.addContainerGap())
		);
		DrawingArea.setLayout(gl_DrawingArea);
		
		JPanel AddOptPanel = new JPanel(); // Panel in Legend : Additional Information where option button is
		AddOptPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		AddOptPanel.setBackground(new Color(245, 245, 245));
		
		Label AddSel = new Label("Select : ");  // Select label
		AddSel.setAlignment(Label.CENTER);
		AddSel.setForeground(Color.BLACK);
		AddSel.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		JButton optionsBtn = new JButton("Options"); // option button in Legend : Additional Information 
		optionsBtn.setIcon((new ImageIcon(Main.class.getResource("/option.png"))));
		optionsBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				msgbox.append("\n");
				msgbox.append("Additional Information Options Selected : ");
				msgbox.append("\n");
				ScrollPane popupAxis=new ScrollPane();
				JOptionPane dialCountry= new JOptionPane();
				dialCountry.setLayout(new BoxLayout(dialCountry, BoxLayout.Y_AXIS));
				JPanel tick=new JPanel();
				tick.setBounds(61, 11, 81, 140);
			    tick.setLayout(new BoxLayout(tick, BoxLayout.Y_AXIS));
				final JCheckBox cBox[] = new JCheckBox[axisOpt.length];
				for(int i=0;i<cBox.length;i++){
				cBox[i]=new JCheckBox(axisOpt[i]);
				if(selectedOpts.contains(axisOpt[i])){cBox[i].setSelected(true);}
				if((!selectedYAxis.isEmpty()&& selectedYAxis.contains(cBox[i].getText())) || (!selectedXAxis.isEmpty()&& selectedXAxis.contains(cBox[i].getText()))){cBox[i].setEnabled(false);}
				final int a=i;
				cBox[i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(cBox[a].isEnabled()){
						if(selectedOpts.contains(cBox[a].getText())){removelabel(cBox[a].getText());selectedOpts.remove(cBox[a].getText()); Num_Select_Opts= Num_Select_Opts-1;msgbox.append("\n"+"Removed : "+cBox[a].getText()+"\n");}
						else{
						if(Num_Select_Opts>=4){cBox[a].setSelected(false);msgbox.append("\n");msgbox.append("Cannot select more than one topic per Axis");}
						else{selectedOpts.add(cBox[a].getText());
							msgbox.append("\n");
							msgbox.append(cBox[a].getText());
							msgbox.append("\n");
							if(op1.getText().compareTo("no selection")==0){op1.setText(cBox[a].getText());}
							else if(op2.getText().compareTo("no selection")==0){op2.setText(cBox[a].getText());}
							else if(op3.getText().compareTo("no selection")==0){op3.setText(cBox[a].getText());}
							else if(op4.getText().compareTo("no selection")==0){op4.setText(cBox[a].getText());}
							Num_Select_Opts++;}}
						
					}
						if(selectedOpts.contains(cBox[a].getText()))cBox[a].setSelected(true);
						else{cBox[a].setSelected(false);}	
					}
					});
				tick.add(cBox[i]);}
				popupAxis.add(tick);
				popupAxis.setBounds(100,100,400,400);
				popupAxis.setVisible(true);
			//	dialCountry.add(popupCountry);
				dialCountry.setBounds(100,100,400,400);
				JOptionPane.showConfirmDialog(null, popupAxis,"X Axis", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
				
			
			}
		});
		optionsBtn.setHorizontalAlignment(SwingConstants.TRAILING);
		optionsBtn.setVerticalAlignment(SwingConstants.TOP);
		optionsBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		JButton AdditionalSubmit = new JButton("Generate");
		AdditionalSubmit.setIcon((new ImageIcon(Main.class.getResource("/tinygen.png"))));
		AdditionalSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
							if(barchartPanel.getComponentCount()>0){
							barchartPanel.removeAllCharts();}
							
                            if (!selectedC.isEmpty() && !selectedOpts.isEmpty()){
                            
                            
                            List<String> LimitedSelectedC = new ArrayList<String>();
                            if (selectedC.size() >30){
                            	LimitedSelectedC = selectedC.subList(0, 29);
                            	msgbox.append("\n More than 30 countries \n");
                                msgbox.append("\n First 30 countries selected\n");}
                            else
                            	LimitedSelectedC = selectedC.subList(0, selectedC.size()-1);
                            for (int i = 0; i<selectedOpts.size();i++){
                                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                                for (int j = 0; j< LimitedSelectedC.size();j++ ){
                                    if (m.getTriple().getValue(m.getVarLab().get(selectedOpts.get(i)), LimitedSelectedC.get(j))!=null){
                                        double d = Double.parseDouble(m.getTriple().getValue(m.getVarLab().get(selectedOpts.get(i)), LimitedSelectedC.get(j)));
                                        dataset.setValue(d, selectedOpts.get(i), LimitedSelectedC.get(j));
                                        
                                        }
                                    barchartPanel.addChart(i, selectedOpts.get(i), dataset);
                                    }
                                    barchartPanel.repaint();  
                                       
                               }
                            }
                            else{
                            	 msgbox.append("\n");
                                msgbox.append("No selected Countries or Options");
                                msgbox.append("\n");}
                            
                           
                            
                            
			}
		});
		AdditionalSubmit.setForeground(Color.BLACK);
		AdditionalSubmit.setFont(new Font("SansSerif", Font.BOLD, 13));
		GroupLayout gl_AddOptPanel = new GroupLayout(AddOptPanel);
		gl_AddOptPanel.setHorizontalGroup(
			gl_AddOptPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_AddOptPanel.createSequentialGroup()
					.addGap(20)
					.addComponent(AddSel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(19))
				.addGroup(gl_AddOptPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(optionsBtn, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(AdditionalSubmit)
					.addContainerGap())
		);
		gl_AddOptPanel.setVerticalGroup(
			gl_AddOptPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_AddOptPanel.createSequentialGroup()
					.addGap(10)
					.addComponent(AddSel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_AddOptPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(optionsBtn, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(AdditionalSubmit, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(30))
		);
		AddOptPanel.setLayout(gl_AddOptPanel);
		
		JPanel AddList = new JPanel();  // Panel in Legend : Additional Information where labels/selections are displayed
		AddList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		AddList.setBackground(new Color(245, 245, 245));
		
		JLabel lblLegendAdditional = new JLabel("Panel : Additional Information"); // Legend title
		lblLegendAdditional.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblLegendAdditional.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		
		JLabel lblMessages = new JLabel("Messages:"); // Message box title
		lblMessages.setFont(new Font("SansSerif", Font.BOLD, 13));
		
		JScrollPane scrollPane = new JScrollPane(); // scroll pane to make it scrollable
		GroupLayout gl_LegendAdd = new GroupLayout(LegendAdd);
		gl_LegendAdd.setHorizontalGroup(
			gl_LegendAdd.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_LegendAdd.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_LegendAdd.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
						.addComponent(AddList, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
						.addComponent(lblLegendAdditional, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
						.addComponent(lblMessages, Alignment.LEADING)
						.addComponent(AddOptPanel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 268, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_LegendAdd.setVerticalGroup(
			gl_LegendAdd.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_LegendAdd.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLegendAdditional)
					.addGap(5)
					.addComponent(AddOptPanel, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(AddList, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblMessages)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
					.addContainerGap())
		);
		scrollPane.setViewportView(msgbox);
// creating message box
		msgbox.setBounds(0,0,msgbox.getWidth(),msgbox.getHeight());
		msgbox.setSize(msgbox.getWidth(),msgbox.getHeight()-50);
		msgbox.setColumns(1);
		msgbox.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		msgbox.setRows(3);
		msgbox.setEditable(false);
		
		JLabel ListTitleAdd = new JLabel("List of Selected Options :"); // List label
		ListTitleAdd.setHorizontalAlignment(SwingConstants.CENTER);
		ListTitleAdd.setFont(new Font("SansSerif", Font.BOLD, 12));
		ListTitleAdd.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JPanel ListPanel = new JPanel(); // panel were selections are shown
		ListPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JButton btnClear = new JButton("Clear"); // Clear button that clears only info of that panel
		btnClear.setIcon((new ImageIcon(Main.class.getResource("/refrsmall.png"))));
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
                Num_Select_Opts=0; 
				selectedOpts.clear();
				op1.setText("no selection");
				op2.setText("no selection");
				op3.setText("no selection");
				op4.setText("no selection");
				if(barchartPanel.getComponentCount()>0){
				barchartPanel.removeAllCharts();}
			//	barchartPanel.removeAll();
			//	barchartPanel.repaint();
			}
		});
		btnClear.setFont(new Font("SansSerif", Font.BOLD, 12));
		GroupLayout gl_AddList = new GroupLayout(AddList);
		gl_AddList.setHorizontalGroup(
			gl_AddList.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_AddList.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_AddList.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_AddList.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_AddList.createSequentialGroup()
								.addComponent(btnClear, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
								.addGap(9))
							.addGroup(gl_AddList.createSequentialGroup()
								.addComponent(ListTitleAdd, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
								.addContainerGap()))
						.addGroup(Alignment.TRAILING, gl_AddList.createSequentialGroup()
							.addGap(4)
							.addComponent(ListPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_AddList.setVerticalGroup(
			gl_AddList.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_AddList.createSequentialGroup()
					.addContainerGap()
					.addComponent(ListTitleAdd)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ListPanel, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JLabel opt_1 = new JLabel("1."); // first selection
		
		JLabel opt_2 = new JLabel("2."); // second selection
		
		JLabel opt_3 = new JLabel("3."); // third selection
		
		JLabel opt_4 = new JLabel("4."); // fourth selection
		
		op1 = new JLabel("no selection");
		op1.setFont(new Font("SansSerif", Font.PLAIN, 11));
		
		op2 = new JLabel("no selection");
		op2.setFont(new Font("SansSerif", Font.PLAIN, 11));
		
		op3 = new JLabel("no selection");
		op3.setFont(new Font("SansSerif", Font.PLAIN, 11));
		
		op4 = new JLabel("no selection");
		op4.setFont(new Font("SansSerif", Font.PLAIN, 11));
		
		GroupLayout gl_ListPanel = new GroupLayout(ListPanel);
		gl_ListPanel.setHorizontalGroup(
			gl_ListPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ListPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ListPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(opt_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(opt_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(opt_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(opt_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_ListPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(op1, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_ListPanel.createSequentialGroup()
							.addComponent(op3, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
							.addGap(2))
						.addComponent(op2, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
						.addComponent(op4, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_ListPanel.setVerticalGroup(
			gl_ListPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_ListPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ListPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(opt_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(op1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
					.addGroup(gl_ListPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(opt_2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(op2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_ListPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(opt_3, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(op3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_ListPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(opt_4, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(op4, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(22))
		);
		ListPanel.setLayout(gl_ListPanel);
		AddList.setLayout(gl_AddList);
		LegendAdd.setLayout(gl_LegendAdd);
		
		JLabel lblAdditionalInformation = new JLabel("Additional Information"); // label/title
		lblAdditionalInformation.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblAdditionalInformation.setBackground(Color.GRAY);
		
		
		
		
		GroupLayout gl_Addinfo = new GroupLayout(Addinfo);
		gl_Addinfo.setHorizontalGroup(
			gl_Addinfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Addinfo.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_Addinfo.createParallelGroup(Alignment.LEADING)
						.addComponent(barchartPanel, GroupLayout.DEFAULT_SIZE, 947, Short.MAX_VALUE)
						.addComponent(lblAdditionalInformation))
					.addContainerGap())
		);
		gl_Addinfo.setVerticalGroup(
			gl_Addinfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Addinfo.createSequentialGroup()
					.addGap(6)
					.addComponent(lblAdditionalInformation, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(barchartPanel, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		
		Addinfo.setLayout(gl_Addinfo);	
		frmIsPrototype.getContentPane().setLayout(groupLayout);
		mntmExit.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				msgbox.append("System exited \n");
				System.exit(0);
			}
		});
			}
}
	
