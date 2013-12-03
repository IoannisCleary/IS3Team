import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JMenu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Panel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Label;
import java.awt.Font;
import java.awt.Color;
import java.awt.ScrollPane;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import java.awt.SystemColor;

import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import java.awt.ComponentOrientation;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;

import javax.swing.JFileChooser;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.border.EtchedBorder;

public class Main {
	private static Model m ;
	private int Max_Select=15;
	private int Num_Select_Country=0;
	private int Num_Select_X=0;
	private int Num_Select_Y=0;
	private int Num_Select_Opts=0;
	private ArrayList<String> selectedC=new ArrayList<String>();
	private ArrayList<String> selectedYAxis=new ArrayList<String>();
	private ArrayList<String> selectedXAxis=new ArrayList<String>();
	private ArrayList<String> selectedOpts=new ArrayList<String>();
	private JFrame frmIsPrototype;
	private JLabel yselect,op1,op2,op3,op4;
	private String[] axisOpt=m.getVariables();
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
		
		JFileChooser chooser = new JFileChooser(); 
       int ret=chooser.showOpenDialog(chooser);
       if (ret==1){
    	   JOptionPane.showMessageDialog(null,"No file has been selected, system will exit");
       System.exit(0);}
       
       String filepath=chooser.getSelectedFile().getAbsolutePath();
       String ending=filepath.substring(filepath.length()-4,filepath.length());
//       System.out.println(ending);
       while(ending.compareTo(".csv")!=0){
    	   JOptionPane.showMessageDialog(null,"File is not valid, make sure it's a .csv file");
    	   ret=chooser.showOpenDialog(chooser); 
    	   if(ret==1){  JOptionPane.showMessageDialog(null,"No file has been selected, system will exit"); System.exit(0);}
    	   filepath=chooser.getSelectedFile().getAbsolutePath();
    	   ending=filepath.substring(filepath.length()-4,filepath.length());
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
		frmIsPrototype = new JFrame(); // Main frame
		frmIsPrototype.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIsPrototype.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frmIsPrototype.setBackground(UIManager.getColor("Button.shadow"));
		frmIsPrototype.getContentPane().setBackground(new Color(247, 247, 247));
		frmIsPrototype.setTitle("IS3 Prototype");
		frmIsPrototype.setBounds(100, 100,1280, 740);
		
		JMenuBar menuBar = new JMenuBar(); // menu bar
		menuBar.setBackground(UIManager.getColor("MenuBar.borderColor"));
		frmIsPrototype.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");  // File option on the menu bar
		mnFile.setFont(new Font("Serif", Font.BOLD, 12));
		mnFile.setBackground(new Color(153, 204, 255));
		menuBar.add(mnFile); // adding to menu bar
		
		final JMenuItem mntmExit = new JMenuItem("Exit"); // creating exit button and adding it to the File menu option
		mntmExit.setBackground(new Color(240, 240, 240));
		mntmExit.setFont(new Font("Serif", Font.BOLD, 12));
		mntmExit.setHorizontalAlignment(SwingConstants.LEFT);
		mntmExit.addMouseListener(new MouseAdapter() {
		
			public void mouseReleased(MouseEvent e) {
				System.exit(0);
			}
			
		});
		mnFile.add(mntmExit); // actual addition to the menu
		
		
		JMenu mnHelp = new JMenu("Help"); // Help option on the menu bar
		mnHelp.setFont(new Font("Serif", Font.BOLD, 12));
		menuBar.add(mnHelp);
		
		JMenuItem Manual = new JMenuItem("Manual"); // creating Manual button and adding it to the Help menu option
		Manual.setFont(new Font("Serif", Font.BOLD, 12));
		Manual.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				JOptionPane.showMessageDialog(frmIsPrototype," \n To run this software you need to follow these steps \n\n 1. Select a country/countries by clicking on the Countries button from the Legend: Drawing Area. \n 2. Select values for the X and Y axis by clicking the Axis button from the Legend: Drawing Area. \n\n (A Scatter plot should have been created by now on the Drawing Area and corresonding values should be displayed on the right.) \n \n 3. You can select additional information other than the default by clicking the Options button from the Legend: Additional Information.\n\n (This Legend will now display your selections and bar graphs should be generated on the left) \n\n 4. There are 2 clear buttons the one on the bottom of the screen clears only the Additinal Information, the other one clears everything. \n \n");

			}
		});
		mnHelp.add(Manual);// actual addition to the menu
		
		JMenuItem About = new JMenuItem("About");  // creating About button and adding it to the Help menu option
		About.setFont(new Font("Serif", Font.BOLD, 12));
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
						.addComponent(DrawingArea, GroupLayout.DEFAULT_SIZE, 951, Short.MAX_VALUE))
					.addGap(10)
					.addComponent(LegendAdd, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(LegendAdd, GroupLayout.PREFERRED_SIZE, 659, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(DrawingArea, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Addinfo, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)))
					.addContainerGap())
		);
		
		JLabel lblDrawingArea = new JLabel("Scatter Plot "); // Drawing area title
		lblDrawingArea.setFont(new Font("Serif", Font.BOLD, 12));
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
			
			JPanel yAxisShow = new JPanel(); // creating panel with labels that show the selected x Axis value 
			yAxisShow.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			yAxisShow.setBackground(new Color(211, 211, 211));
			
			JLabel lblY = new JLabel("Y : ");  // Label that shows Y :
			lblY.setFont(new Font("Serif", Font.BOLD, 12));
			
			yselect = new JLabel("no selection"); // Label that will be regularly updated with the Y axis value
			yselect.setVerticalAlignment(SwingConstants.TOP);
			yselect.setFont(new Font("Serif", Font.BOLD, 12));
			yselect.setBackground(SystemColor.controlHighlight);
			GroupLayout gl_yAxisShow = new GroupLayout(yAxisShow);   // adding the components and creating layout
			gl_yAxisShow.setHorizontalGroup(
				gl_yAxisShow.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_yAxisShow.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblY, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(yselect, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(29, Short.MAX_VALUE))
			);
			gl_yAxisShow.setVerticalGroup(
				gl_yAxisShow.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_yAxisShow.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_yAxisShow.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblY)
							.addComponent(yselect))
						.addContainerGap())
			);
			yAxisShow.setLayout(gl_yAxisShow);
			
			JLabel lblLegendDrawng = new JLabel("Legend : Scatter Plot"); // Legend title
			lblLegendDrawng.setFont(new Font("Serif", Font.BOLD, 13));
			lblLegendDrawng.setHorizontalAlignment(SwingConstants.CENTER);
			
		submit = new JButton("Generate");  // Submit button
		submit.setForeground(Color.BLACK);
		submit.setFont(new Font("Serif", Font.BOLD, 16));
		submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			//	System.out.println(selectedXAxis.isEmpty());
			//	System.out.println(selectedYAxis.isEmpty());
				if (!(selectedXAxis.isEmpty() && selectedYAxis.isEmpty()) && !selectedC.isEmpty()){
				//	if(scatterPanel.getComponentCount()>0)
				//	System.out.println("here");
					scatterPanel.removeAll();
                   
                     
                     xVal = new ArrayList<Double>();
                     yVal = new ArrayList<Double>();
                     matchedC = new ArrayList<String>();
                     if(!selectedXAxis.isEmpty() && !selectedYAxis.isEmpty()){
                    	  xLabel = selectedXAxis.get(0);
                          yLabel = selectedYAxis.get(0);
                          dataArray = m.get2VarDataArray(xLabel, yLabel, m.getTriple());
                     for (int j = 0; j < selectedC.size();j++ )
                       for (int i = 0; i< dataArray.size(); i++){
                    	   
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
                    	 dataArray = m.get2VarDataArray(yLabel, "Factor1", m.getTriple());
                    	 for (int j = 0; j < selectedC.size();j++ )
                             for (int i = 0; i< dataArray.size(); i++){
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
                    	 dataArray = m.get2VarDataArray("Factor1", yLabel, m.getTriple());
                    	 for (int j = 0; j < selectedC.size();j++ )
                             for (int i = 0; i< dataArray.size(); i++){
                                 if (selectedC.get(j).equals(dataArray.get(i)[0])){
                                     matchedC.add(selectedC.get(j));
                                     xVal.add(cnt);
                                     yVal.add(Double.parseDouble(dataArray.get(i)[2]));                          
                                     cnt++;

                                 }
                             }
                           }
                                      	 
                                 	 
                     
				
                     
                     System.out.println(matchedC.toString());
     
                     sc = new ScatterPlot(xLabel,yLabel,"multi data graph" ,xVal, yVal,matchedC, 500, 320);
                     
                     msgbox.append("processed");
                     sc.setVisible(true);           
                     sc.setSize(scatterPanel.getWidth()-20,scatterPanel.getHeight()-25);
                     sc.repaint();
                     scatterPanel.add(sc);
                     scatterPanel.repaint();
                   
                    
            //         frmIsPrototype.repaint();
       }}
			
		});
		
		JButton MainClear = new JButton("Start Over"); // Clear all selection button, clears all selections and resets labels
		MainClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				msgbox.setText("");
				Num_Select_Country=0;
				selectedC.clear();
				selectedYAxis.clear();
				selectedXAxis.clear();
				Num_Select_X=0;
				Num_Select_Y=0;
				scatterPanel.removeAll();
				scatterPanel.repaint();
				barchartPanel.removeAll();
				barchartPanel.repaint();
			}
		});
		MainClear.setFont(new Font("Serif", Font.BOLD, 16));
		GroupLayout gl_LegendDA = new GroupLayout(LegendDA);
		gl_LegendDA.setHorizontalGroup(
			gl_LegendDA.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_LegendDA.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_LegendDA.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLegendDrawng, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
						.addGroup(gl_LegendDA.createSequentialGroup()
							.addComponent(yAxisShow, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addComponent(selections, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_LegendDA.createSequentialGroup()
							.addComponent(submit, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
							.addComponent(MainClear, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_LegendDA.setVerticalGroup(
			gl_LegendDA.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_LegendDA.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLegendDrawng)
					.addGroup(gl_LegendDA.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_LegendDA.createSequentialGroup()
							.addGap(49)
							.addComponent(yAxisShow, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_LegendDA.createSequentialGroup()
							.addGap(12)
							.addComponent(selections, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_LegendDA.createParallelGroup(Alignment.BASELINE)
						.addComponent(submit, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(MainClear, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(179))
		);
		
		JButton CountryButton = new JButton("Countries"); // Countries selection button, window with check boxes is created and shown
		CountryButton.setForeground(Color.BLACK);

		CountryButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {

				msgbox.append("Countries Selected : ");
				msgbox.append("\n");
			//	msgbox.append(countryNames.toString());
				ScrollPane popupCountry=new ScrollPane();
				JOptionPane dialCounty= new JOptionPane();
				dialCounty.setLayout(new BoxLayout(dialCounty, BoxLayout.Y_AXIS));
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
						if(Num_Select_Country>=Max_Select){cBox[a].setSelected(false);msgbox.append("\n");msgbox.append("Cannot select more than four countries");}
						else{selectedC.add(cBox[a].getText());
							msgbox.append("\n");
						msgbox.append(cBox[a].getText()+"  ");
						Num_Select_Country++;}}
						if(selectedC.contains(cBox[a].getText()))cBox[a].setSelected(true);
					}});
				tick.add(cBox[i]);}
				popupCountry.add(tick);
				popupCountry.setBounds(100,100,400,400);
				popupCountry.setVisible(true);
			//	dialCounty.add(popupCountry);
				dialCounty.setBounds(100,100,400,400);
				JOptionPane.showConfirmDialog(null, popupCountry,"Countries", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
				
			}
		});
		CountryButton.setFont(new Font("Serif", Font.BOLD, 14));
		
		JButton xAxisButton = new JButton("X Axis"); // X Axis selection button, window with check boxes is created and shown
		xAxisButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				msgbox.append("\n");
				msgbox.append("X Selected : ");
				msgbox.append("\n");
				ScrollPane popupAxis=new ScrollPane();
				JOptionPane dialCounty= new JOptionPane();
				dialCounty.setLayout(new BoxLayout(dialCounty, BoxLayout.Y_AXIS));
				JPanel tick=new JPanel();
				tick.setBounds(61, 11, 81, 140);
			    tick.setLayout(new BoxLayout(tick, BoxLayout.Y_AXIS));
				final JCheckBox cBox[] = new JCheckBox[axisOpt.length];
				for(int i=0;i<cBox.length;i++){
				cBox[i]=new JCheckBox(axisOpt[i]);
				if(selectedXAxis.contains(axisOpt[i])){cBox[i].setSelected(true);}
				if((!selectedYAxis.isEmpty()&& selectedYAxis.contains(cBox[i].getText())) || (!selectedOpts.isEmpty()&& selectedOpts.contains(cBox[i].getText()))){cBox[i].setEnabled(false);}
				final int a=i;
				cBox[i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(cBox[a].isEnabled()){
						if(selectedXAxis.contains(cBox[a].getText())){selectedXAxis.remove(cBox[a].getText()); Num_Select_X= Num_Select_X-1;msgbox.append("\n"+"Removed : "+cBox[a].getText()+"\n");}
						else{
						if(Num_Select_X>=1){cBox[a].setSelected(false);msgbox.append("\n");msgbox.append("Cannot select more than one topic per Axis");}
						else{selectedXAxis.add(cBox[a].getText());
							msgbox.append("\n");
							msgbox.append(cBox[a].getText());
							msgbox.append("\n");
							Num_Select_X++;}}
						
					}}});
				tick.add(cBox[i]);}
				popupAxis.add(tick);
				popupAxis.setBounds(100,100,400,400);
				popupAxis.setVisible(true);
			//	dialCounty.add(popupCountry);
				dialCounty.setBounds(100,100,400,400);
				JOptionPane.showConfirmDialog(null, popupAxis,"X Axis", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
				
			}
		});
		xAxisButton.setFont(new Font("Serif", Font.BOLD, 14));
		
		
		JButton YAxisbtn = new JButton("Y Axis"); // Y Axis selection button, window with check boxes is created and shown
		YAxisbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				msgbox.append("Y Selected : ");
				msgbox.append("\n");
				ScrollPane popupAxis=new ScrollPane();
				JOptionPane dialCounty= new JOptionPane();
				dialCounty.setLayout(new BoxLayout(dialCounty, BoxLayout.Y_AXIS));
				JPanel tick=new JPanel();
				tick.setBounds(61, 11, 81, 140);
			    tick.setLayout(new BoxLayout(tick, BoxLayout.Y_AXIS));
				final JCheckBox cBox[] = new JCheckBox[axisOpt.length];
				for(int i=0;i<cBox.length;i++){
				cBox[i]=new JCheckBox(axisOpt[i]);
				if(selectedYAxis.contains(axisOpt[i])){cBox[i].setSelected(true);}
				if((!selectedOpts.isEmpty()&& selectedOpts.contains(cBox[i].getText())) || (!selectedXAxis.isEmpty()&& selectedXAxis.contains(cBox[i].getText()))){cBox[i].setEnabled(false);}
				final int a=i;
				cBox[i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(cBox[a].isEnabled()){
						if(selectedYAxis.contains(cBox[a].getText())){selectedYAxis.remove(cBox[a].getText()); Num_Select_Y= Num_Select_Y-1;msgbox.append("\n"+"Removed : "+cBox[a].getText()+"\n");}
						else{
						if(Num_Select_Y>=1){cBox[a].setSelected(false);msgbox.append("\n");msgbox.append("Cannot select more than one topic per Axis");}
						else{selectedYAxis.add(cBox[a].getText());
						msgbox.append("\n");
						msgbox.append(cBox[a].getText());
						msgbox.append("\n");
			
						Num_Select_Y++;}}}
						
					}});
				tick.add(cBox[i]);}
				popupAxis.add(tick);
				popupAxis.setBounds(100,100,400,400);
				popupAxis.setVisible(true);
				dialCounty.setBounds(100,100,400,400);
				JOptionPane.showConfirmDialog(null, popupAxis,"Y Axis", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
				
			}
		});
		
			YAxisbtn.setFont(new Font("Serif", Font.BOLD, 14));
			GroupLayout gl_selections = new GroupLayout(selections);
			gl_selections.setHorizontalGroup(
				gl_selections.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_selections.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_selections.createParallelGroup(Alignment.LEADING)
							.addComponent(CountryButton, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
							.addComponent(xAxisButton, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
							.addComponent(YAxisbtn, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
						.addContainerGap())
			);
			gl_selections.setVerticalGroup(
				gl_selections.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_selections.createSequentialGroup()
						.addContainerGap()
						.addComponent(CountryButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(xAxisButton, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(YAxisbtn, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(130, Short.MAX_VALUE))
			);
			selections.setLayout(gl_selections);
			LegendDA.setLayout(gl_LegendDA);
		GroupLayout gl_DrawingArea = new GroupLayout(DrawingArea);
		gl_DrawingArea.setHorizontalGroup(
			gl_DrawingArea.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_DrawingArea.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_DrawingArea.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_DrawingArea.createSequentialGroup()
							.addComponent(scatterPanel, GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(LegendDA, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_DrawingArea.createSequentialGroup()
							.addComponent(lblDrawingArea, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
							.addGap(495))))
		);
		gl_DrawingArea.setVerticalGroup(
			gl_DrawingArea.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_DrawingArea.createSequentialGroup()
					.addGroup(gl_DrawingArea.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_DrawingArea.createSequentialGroup()
							.addGap(6)
							.addComponent(lblDrawingArea)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scatterPanel, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE))
						.addGroup(gl_DrawingArea.createSequentialGroup()
							.addGap(44)
							.addComponent(LegendDA, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		DrawingArea.setLayout(gl_DrawingArea);
		
		JPanel AddOptPanel = new JPanel(); // Panel in Legend : Additional Information where option button is
		AddOptPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		AddOptPanel.setBackground(new Color(245, 245, 245));
		
		Label AddSel = new Label("Select : ");  // Select label
		AddSel.setForeground(Color.BLACK);
		AddSel.setFont(new Font("Serif", Font.BOLD, 12));
		
		JButton optionsBtn = new JButton("Options"); // option button in Legend : Additional Information 
		optionsBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				msgbox.append("\n");
				msgbox.append("Additional Information Options Selected : ");
				msgbox.append("\n");
				ScrollPane popupAxis=new ScrollPane();
				JOptionPane dialCounty= new JOptionPane();
				dialCounty.setLayout(new BoxLayout(dialCounty, BoxLayout.Y_AXIS));
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
					}
					});
				tick.add(cBox[i]);}
				popupAxis.add(tick);
				popupAxis.setBounds(100,100,400,400);
				popupAxis.setVisible(true);
			//	dialCounty.add(popupCountry);
				dialCounty.setBounds(100,100,400,400);
				JOptionPane.showConfirmDialog(null, popupAxis,"X Axis", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
				
			
			}
		});
		optionsBtn.setHorizontalAlignment(SwingConstants.TRAILING);
		optionsBtn.setVerticalAlignment(SwingConstants.TOP);
		optionsBtn.setFont(new Font("Serif", Font.BOLD, 11));
		GroupLayout gl_AddOptPanel = new GroupLayout(AddOptPanel);
		gl_AddOptPanel.setHorizontalGroup(
			gl_AddOptPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_AddOptPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(AddSel, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(optionsBtn)
					.addContainerGap(116, Short.MAX_VALUE))
		);
		gl_AddOptPanel.setVerticalGroup(
			gl_AddOptPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_AddOptPanel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_AddOptPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(optionsBtn)
						.addComponent(AddSel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		AddOptPanel.setLayout(gl_AddOptPanel);
		
		JPanel AddList = new JPanel();  // Panel in Legend : Additional Information where labels/selections are displayed
		AddList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		AddList.setBackground(new Color(245, 245, 245));
		
		JLabel lblLegendAdditional = new JLabel("Legend : Additional Information"); // Legend title
		lblLegendAdditional.setFont(new Font("Serif", Font.BOLD, 13));
		lblLegendAdditional.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		
		JLabel lblMessages = new JLabel("Messages:"); // Message box title
		lblMessages.setFont(new Font("Serif", Font.BOLD, 14));
		
		JScrollPane scrollPane = new JScrollPane(); // scroll pane to make it scrollable
		
		JButton AdditionalSubmit = new JButton("Submit");
		AdditionalSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
                            if (!selectedC.isEmpty() && !selectedOpts.isEmpty()){
                            for (int i = 0; i<selectedOpts.size();i++){
                                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                                for (int j = 0; j<selectedC.size();j++ ){
                                    if (m.getTriple().getValue(selectedOpts.get(i), selectedC.get(j))!=null){
                                        double d = Double.parseDouble(m.getTriple().getValue(selectedOpts.get(i), selectedC.get(j)));
                                        dataset.setValue(d, selectedOpts.get(i), selectedC.get(j));
                                        
                                        }
                                    barchartPanel.addChart(i, selectedOpts.get(i), dataset);
                                    }
                                       
                                        
                               }
                            }
                            else
                                msgbox.append("No selected Countries or Options");
                            
                           
                            
                            
			}
		});
		AdditionalSubmit.setForeground(Color.BLACK);
		AdditionalSubmit.setFont(new Font("Serif", Font.BOLD, 12));
		GroupLayout gl_LegendAdd = new GroupLayout(LegendAdd);
		gl_LegendAdd.setHorizontalGroup(
			gl_LegendAdd.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_LegendAdd.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_LegendAdd.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
						.addComponent(AddList, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 259, Short.MAX_VALUE)
						.addComponent(AddOptPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
						.addComponent(lblLegendAdditional, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
						.addComponent(AdditionalSubmit, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMessages, Alignment.LEADING))
					.addContainerGap())
		);
		gl_LegendAdd.setVerticalGroup(
			gl_LegendAdd.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_LegendAdd.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLegendAdditional)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(AddOptPanel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(AdditionalSubmit, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(AddList, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblMessages)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
					.addGap(22))
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
		ListTitleAdd.setFont(new Font("Serif", Font.BOLD, 13));
		ListTitleAdd.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JPanel ListPanel = new JPanel(); // panel were selections are shown
		ListPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JButton btnClear = new JButton("Clear"); // Clear button that clears only info of that panel
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
                Num_Select_Opts=0; 
				selectedOpts.clear();
				op1.setText("no selection");
				op2.setText("no selection");
				op3.setText("no selection");
				op4.setText("no selection");
				barchartPanel.removeAllCharts();
			//	barchartPanel.removeAll();
			//	barchartPanel.repaint();
			}
		});
		btnClear.setFont(new Font("Serif", Font.BOLD, 11));
		GroupLayout gl_AddList = new GroupLayout(AddList);
		gl_AddList.setHorizontalGroup(
			gl_AddList.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_AddList.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_AddList.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnClear)
						.addComponent(ListPanel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 239, Short.MAX_VALUE)
						.addComponent(ListTitleAdd, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_AddList.setVerticalGroup(
			gl_AddList.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_AddList.createSequentialGroup()
					.addGap(17)
					.addComponent(ListTitleAdd)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ListPanel, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnClear)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		
		JLabel opt_1 = new JLabel("1."); // first selection
		
		JLabel opt_2 = new JLabel("2."); // second selection
		
		JLabel opt_3 = new JLabel("3."); // third selection
		
		JLabel opt_4 = new JLabel("4."); // fourth selection
		
		op1 = new JLabel("no selection");
		
		op2 = new JLabel("no selection");
		
		op3 = new JLabel("no selection");
		
		op4 = new JLabel("no selection");
		
		GroupLayout gl_ListPanel = new GroupLayout(ListPanel);
		gl_ListPanel.setHorizontalGroup(
			gl_ListPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ListPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ListPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(opt_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(opt_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(opt_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(opt_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_ListPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(op1, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
						.addComponent(op2, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
						.addComponent(op3, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
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
					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
					.addGroup(gl_ListPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(opt_2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(op2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_ListPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(opt_3, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(op3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_ListPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(opt_4, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(op4, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(22))
		);
		ListPanel.setLayout(gl_ListPanel);
		AddList.setLayout(gl_AddList);
		LegendAdd.setLayout(gl_LegendAdd);
		
		JLabel lblAdditionalInformation = new JLabel("Additional Information"); // label/title
		lblAdditionalInformation.setFont(new Font("Serif", Font.BOLD, 12));
		lblAdditionalInformation.setBackground(Color.GRAY);
		
		
		
		
		GroupLayout gl_Addinfo = new GroupLayout(Addinfo);
		gl_Addinfo.setHorizontalGroup(
			gl_Addinfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Addinfo.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_Addinfo.createParallelGroup(Alignment.LEADING)
						.addComponent(barchartPanel, GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)
						.addComponent(lblAdditionalInformation))
					.addContainerGap())
		);
		gl_Addinfo.setVerticalGroup(
			gl_Addinfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Addinfo.createSequentialGroup()
					.addGap(6)
					.addComponent(lblAdditionalInformation, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(barchartPanel, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
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

