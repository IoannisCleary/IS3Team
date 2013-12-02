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
import java.awt.Canvas;
import java.awt.Color;
import java.awt.ScrollPane;

import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import java.awt.SystemColor;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import java.awt.ComponentOrientation;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;

import java.awt.Window.Type;
import java.awt.Rectangle;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;

public class Main {
	private static Model m ;
	private int Max_Select=4;
	private int Num_Select_Country=0;
	private int Num_Select_X=0;
	private int Num_Select_Y=0;
	private ArrayList<String> selectedC=new ArrayList<String>();
	private ArrayList<String> selectedYAxis=new ArrayList<String>();
	private ArrayList<String> selectedXAxis=new ArrayList<String>();
	private JFrame frmIsPrototype;
	private JLabel country1,country2,country3,country4,xselect,yselect;
	private String[] axisOpt=m.getVariables();
	private ArrayList<String> countryNames;
	private ScatterPlot sc;
	private JButton submit;
	private Panel DrawingArea;
	private JPanel scatterPanel;

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
            	chooser.showOpenDialog(chooser);
            	m = new Model(chooser.getSelectedFile().getAbsolutePath()); 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmIsPrototype.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void removelabel(String b){
		if(country1.getText().compareTo(b)==0){country1.setText("no selection");}
		else if(country2.getText().compareTo(b)==0){country2.setText("no selection");}
		else if(country3.getText().compareTo(b)==0){country3.setText("no selection");}
		else if(country4.getText().compareTo(b)==0){country4.setText("no selection");}
	}
	private void removelabel(String b,String axis){
		if(axis.compareTo("x")==0)xselect.setText("no selection");
		else yselect.setText("no selection");
	}
	/**
	 * Create the application.
	 */
	public Main() {
		 countryNames = m.getCountries();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIsPrototype = new JFrame();
		frmIsPrototype.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIsPrototype.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frmIsPrototype.setResizable(false);
		frmIsPrototype.setBackground(UIManager.getColor("Button.shadow"));
		frmIsPrototype.getContentPane().setBackground(new Color(169, 169, 169));
		frmIsPrototype.setTitle("IS3 Prototype");
		frmIsPrototype.setBounds(100, 100,1336, 692);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(UIManager.getColor("MenuBar.borderColor"));
		frmIsPrototype.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Serif", Font.BOLD, 12));
		mnFile.setBackground(new Color(153, 204, 255));
		menuBar.add(mnFile);
		
		final JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setBackground(new Color(240, 240, 240));
		mntmExit.setFont(new Font("Serif", Font.BOLD, 12));
		mntmExit.setHorizontalAlignment(SwingConstants.LEFT);
		mntmExit.addMouseListener(new MouseAdapter() {
		
			public void mouseReleased(MouseEvent e) {
				System.exit(0);
			}
			
		});
		
		JMenuItem start = new JMenuItem("Start");
		
		start.setFont(new Font("Serif", Font.BOLD, 12));
		mnFile.add(start);
		mnFile.add(mntmExit);
		
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setFont(new Font("Serif", Font.BOLD, 12));
		menuBar.add(mnHelp);
		
		JMenuItem Manual = new JMenuItem("Manual");
		Manual.setFont(new Font("Serif", Font.BOLD, 12));
		Manual.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				JOptionPane.showMessageDialog(frmIsPrototype," \n To run this software you need to follow these steps \n\n 1. Select a country/countries by clicking on the Countries button from the Legend: Drawing Area. \n 2. Select values for the X and Y axis by clicking the Axis button from the Legend: Drawing Area. \n\n (A Scatter plot should have been created by now on the Drawing Area and corresonding values should be displayed on the right.) \n \n 3. You can select additional information other than the default by clicking the Options button from the Legend: Additional Information.\n\n (This Legend will now display your selections and bar graphs should be generated on the left) \n\n 4. There are 2 clear buttons the one on the bottom of the screen clears only the Additinal Information, the other one clears everything. \n \n");

			}
		});
		mnHelp.add(Manual);
		
		JMenuItem About = new JMenuItem("About");
		About.setFont(new Font("Serif", Font.BOLD, 12));
		About.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				JOptionPane.showMessageDialog(frmIsPrototype," This software was made by \n Ioannis Cleary(1104205c), Matthew Bown(1002723b), \n Gavin Davidson(1102607d), Luka Prelic(1107827p), \n Georgios Moleskis(1103614m) and Mario Moro Hernandez(1106740m) \n");

			}
		});
		mnHelp.add(About);
		
		Panel LegendDA = new Panel();
		LegendDA.setBackground(new Color(192, 192, 192));
		
		JPanel selections = new JPanel();
		selections.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		selections.setBackground(new Color(211, 211, 211));
		
		JPanel countryshow = new JPanel();
		countryshow.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		countryshow.setBackground(new Color(204, 204, 204));
		
		JPanel xAxisShow = new JPanel();
		xAxisShow.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		xAxisShow.setBackground(new Color(211, 211, 211));
		
		JLabel lblX = new JLabel("X : ");
		lblX.setFont(new Font("Serif", Font.BOLD, 12));
		
		xselect = new JLabel("no selection");
		lblX.setLabelFor(xselect);
		xselect.setFont(new Font("Serif", Font.BOLD, 12));
		GroupLayout gl_xAxisShow = new GroupLayout(xAxisShow);
		gl_xAxisShow.setHorizontalGroup(
			gl_xAxisShow.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_xAxisShow.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblX, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(xselect)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		gl_xAxisShow.setVerticalGroup(
			gl_xAxisShow.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_xAxisShow.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_xAxisShow.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblX)
						.addComponent(xselect))
					.addContainerGap())
		);
		xAxisShow.setLayout(gl_xAxisShow);
		
		JPanel yAxisShow = new JPanel();
		yAxisShow.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		yAxisShow.setBackground(new Color(211, 211, 211));
		
		JLabel lblY = new JLabel("Y : ");
		lblY.setFont(new Font("Serif", Font.BOLD, 12));
		
		yselect = new JLabel("no selection");
		yselect.setVerticalAlignment(SwingConstants.TOP);
		yselect.setFont(new Font("Serif", Font.BOLD, 12));
		yselect.setBackground(SystemColor.controlHighlight);
		GroupLayout gl_yAxisShow = new GroupLayout(yAxisShow);
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
		
		JLabel lblLegendDrawng = new JLabel("Legend : Drawing Area");
		lblLegendDrawng.setFont(new Font("Serif", Font.BOLD, 13));
		lblLegendDrawng.setHorizontalAlignment(SwingConstants.CENTER);
		
		submit = new JButton("Submit");
		submit.setForeground(Color.BLACK);
		submit.setFont(new Font("Serif", Font.BOLD, 16));
		submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				 if (!selectedYAxis.isEmpty() && !selectedYAxis.isEmpty() && !selectedC.isEmpty()){
                     xLabel = selectedYAxis.get(0);
                     yLabel = selectedYAxis.get(0);
                     dataArray = m.get2VarDataArray(xLabel, yLabel, m.getTriple());
                     xVal = new ArrayList<Double>();
                     yVal = new ArrayList<Double>();
                     matchedC = new ArrayList<String>();
                     for (int j = 0; j < selectedC.size();j++ )
                       for (int i = 0; i< dataArray.size(); i++){
                           if (selectedC.get(j).equals(dataArray.get(i)[0])){
                               matchedC.add(selectedC.get(j));
                               xVal.add(Double.parseDouble(dataArray.get(i)[1]));
                               yVal.add(Double.parseDouble(dataArray.get(i)[2]));
                           }
                       }
                     ScatterPlot sc = new ScatterPlot(xLabel, yLabel,"multi data graph" , 
                     xVal, yVal,matchedC, 500, 500);
                     msgbox.append("processed");
                     sc.setVisible(true);
                     scatterPanel.add(sc);
                     frmIsPrototype.repaint();
       }
			}
		});
		GroupLayout gl_LegendDA = new GroupLayout(LegendDA);
		gl_LegendDA.setHorizontalGroup(
			gl_LegendDA.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_LegendDA.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_LegendDA.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLegendDrawng, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
						.addGroup(gl_LegendDA.createSequentialGroup()
							.addGroup(gl_LegendDA.createParallelGroup(Alignment.LEADING)
								.addComponent(selections, 0, 0, Short.MAX_VALUE)
								.addGroup(gl_LegendDA.createParallelGroup(Alignment.LEADING, false)
									.addComponent(yAxisShow, 0, 0, Short.MAX_VALUE)
									.addComponent(xAxisShow, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_LegendDA.createParallelGroup(Alignment.LEADING)
								.addComponent(countryshow, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, gl_LegendDA.createSequentialGroup()
									.addComponent(submit, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
									.addGap(32)))))
					.addContainerGap())
		);
		gl_LegendDA.setVerticalGroup(
			gl_LegendDA.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_LegendDA.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLegendDrawng)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_LegendDA.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_LegendDA.createSequentialGroup()
							.addComponent(xAxisShow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(yAxisShow, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(selections, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_LegendDA.createSequentialGroup()
							.addComponent(countryshow, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
							.addGap(37)
							.addComponent(submit, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
					.addGap(7))
		);
		
		JLabel lblCountries = new JLabel("Countries");
		lblCountries.setFont(new Font("Serif", Font.BOLD, 12));
		lblCountries.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("MenuItem.background"));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setForeground(SystemColor.text);
		country1 = new JLabel("no selection");
		country1.setFont(new Font("Serif", Font.ITALIC, 11));
		
		country2 = new JLabel("no selection");
		country2.setFont(new Font("Serif", Font.ITALIC, 11));
		
		country3 = new JLabel("no selection");
		country3.setFont(new Font("Serif", Font.ITALIC, 11));
		
		country4 = new JLabel("no selection");
		country4.setFont(new Font("Serif", Font.ITALIC, 11));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBackground(Color.RED);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setBackground(Color.BLUE);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7.setBackground(Color.YELLOW);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8.setBackground(Color.GREEN);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(country2, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(2)
							.addComponent(country4, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
							.addGap(8))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(country1, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
								.addComponent(country3, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(29)
							.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(20)
							.addComponent(country1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(country2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(23))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(country3, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(country4, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(9)
							.addComponent(panel_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(176, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		GroupLayout gl_countryshow = new GroupLayout(countryshow);
		gl_countryshow.setHorizontalGroup(
			gl_countryshow.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_countryshow.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_countryshow.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
						.addComponent(lblCountries, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_countryshow.setVerticalGroup(
			gl_countryshow.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_countryshow.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCountries)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		countryshow.setLayout(gl_countryshow);
		JButton CountryButton = new JButton("Countries");
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
						if(selectedC.contains(cBox[a].getText())){removelabel(cBox[a].getText());selectedC.remove(cBox[a].getText()); Num_Select_Country= Num_Select_Country-1;msgbox.append("\n"+"Removed : "+cBox[a].getText()+"\n");}
						else{
						if(Num_Select_Country>=Max_Select){cBox[a].setSelected(false);msgbox.append("\n");msgbox.append("Cannot select more than four countries");}
						else{selectedC.add(cBox[a].getText());
							msgbox.append("\n");
						if(country1.getText().compareTo("no selection")==0){country1.setText(cBox[a].getText());}
						else if(country2.getText().compareTo("no selection")==0){country2.setText(cBox[a].getText());}
						else if(country3.getText().compareTo("no selection")==0){country3.setText(cBox[a].getText());}
						else if(country4.getText().compareTo("no selection")==0){country4.setText(cBox[a].getText());}
						msgbox.append(cBox[a].getText()+"  ");
						Num_Select_Country++;}}
						
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
		CountryButton.setFont(new Font("Serif", Font.BOLD, 12));
		
		JButton xAxisButton = new JButton("X Axis");
		xAxisButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
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
				if(!selectedYAxis.isEmpty()&& selectedYAxis.contains(cBox[i].getText())){cBox[i].setEnabled(false);}
				final int a=i;
				cBox[i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(selectedXAxis.contains(cBox[a].getText())){removelabel(cBox[a].getText(),"x");selectedXAxis.remove(cBox[a].getText()); Num_Select_X= Num_Select_X-1;msgbox.append("\n"+"Removed : "+cBox[a].getText()+"\n");}
						else{
						if(Num_Select_X>=1){cBox[a].setSelected(false);msgbox.append("\n");msgbox.append("Cannot select more than one topic per Axis");}
						else{selectedXAxis.add(cBox[a].getText());
							msgbox.append("\n");
							msgbox.append(cBox[a].getText());
							msgbox.append("\n");
						if(xselect.getText().compareTo("no selection")==0){xselect.setText(cBox[a].getText());}
						Num_Select_X++;}}
						
					}});
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
		xAxisButton.setFont(new Font("Serif", Font.BOLD, 12));
		
		JButton MainClear = new JButton("Clear all");
		MainClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				msgbox.setText("");
				Num_Select_Country=0;
				country1.setText("no selection");
				country2.setText("no selection");
				country3.setText("no selection");
				country4.setText("no selection");
				selectedC.clear();
				selectedYAxis.clear();
				selectedXAxis.clear();
				xselect.setText("no selection");
				yselect.setText("no selection");
				Num_Select_X=0;
				Num_Select_Y=0;
			}
		});
		MainClear.setFont(new Font("Serif", Font.BOLD, 12));
		
		/*
		* Submit button updates the scatterplot with the selected data
		**/
		
		
		JButton YAxisbtn = new JButton("Y Axis");
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
				if(!selectedXAxis.isEmpty()&& selectedXAxis.contains(cBox[i].getText())){cBox[i].setEnabled(false);}
				final int a=i;
				cBox[i].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(selectedYAxis.contains(cBox[a].getText())){removelabel(cBox[a].getText(),"y");selectedYAxis.remove(cBox[a].getText()); Num_Select_Y= Num_Select_Y-1;msgbox.append("\n"+"Removed : "+cBox[a].getText()+"\n");}
						else{
						if(Num_Select_Y>=1){cBox[a].setSelected(false);msgbox.append("\n");msgbox.append("Cannot select more than one topic per Axis");}
						else{selectedYAxis.add(cBox[a].getText());
						msgbox.append("\n");
						msgbox.append(cBox[a].getText());
						msgbox.append("\n");
						if(yselect.getText().compareTo("no selection")==0){yselect.setText(cBox[a].getText());}
						Num_Select_Y++;}}
						
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
	
		YAxisbtn.setFont(new Font("Serif", Font.BOLD, 12));
		GroupLayout gl_selections = new GroupLayout(selections);
		gl_selections.setHorizontalGroup(
			gl_selections.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_selections.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_selections.createParallelGroup(Alignment.TRAILING)
						.addComponent(MainClear, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
						.addComponent(YAxisbtn, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
						.addComponent(xAxisButton, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
						.addComponent(CountryButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(38))
		);
		gl_selections.setVerticalGroup(
			gl_selections.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_selections.createSequentialGroup()
					.addGap(23)
					.addComponent(CountryButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(xAxisButton, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(YAxisbtn, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(MainClear, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(38, Short.MAX_VALUE))
		);
		selections.setLayout(gl_selections);
		LegendDA.setLayout(gl_LegendDA);
		
		Panel Addinfo = new Panel();
		Addinfo.setBackground(new Color(192, 192, 192));
		
		DrawingArea = new Panel();
		DrawingArea.setBackground(new Color(211, 211, 211));

		JPanel LegendAdd = new JPanel();
		LegendAdd.setBackground(new Color(192, 192, 192));
		GroupLayout groupLayout = new GroupLayout(frmIsPrototype.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(DrawingArea, GroupLayout.PREFERRED_SIZE, 598, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(LegendDA, GroupLayout.PREFERRED_SIZE, 390, GroupLayout.PREFERRED_SIZE))
						.addComponent(Addinfo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(LegendAdd, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(LegendAdd, GroupLayout.PREFERRED_SIZE, 632, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(LegendDA, 0, 0, Short.MAX_VALUE)
								.addComponent(DrawingArea, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Addinfo, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel lblDrawingArea = new JLabel("Drawing Area");
		lblDrawingArea.setFont(new Font("Serif", Font.BOLD, 12));
		lblDrawingArea.setLabelFor(DrawingArea);
		lblDrawingArea.setBackground(Color.GRAY);
		
		scatterPanel = new JPanel();
		GroupLayout gl_DrawingArea = new GroupLayout(DrawingArea);
		gl_DrawingArea.setHorizontalGroup(
			gl_DrawingArea.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_DrawingArea.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_DrawingArea.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_DrawingArea.createSequentialGroup()
							.addComponent(scatterPanel, GroupLayout.PREFERRED_SIZE, 568, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_DrawingArea.createSequentialGroup()
							.addComponent(lblDrawingArea, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
							.addGap(495))))
		);
		gl_DrawingArea.setVerticalGroup(
			gl_DrawingArea.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_DrawingArea.createSequentialGroup()
					.addGap(6)
					.addComponent(lblDrawingArea)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scatterPanel, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
					.addContainerGap())
		);
		DrawingArea.setLayout(gl_DrawingArea);
		
		JPanel AddOptPanel = new JPanel();
		AddOptPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		AddOptPanel.setBackground(new Color(204, 204, 204));
		
		Label AddSel = new Label("Select : ");
		AddSel.setForeground(Color.BLACK);
		AddSel.setFont(new Font("Serif", Font.BOLD, 12));
		
		JButton optionsBtn = new JButton("Options");
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
					.addGap(79))
		);
		gl_AddOptPanel.setVerticalGroup(
			gl_AddOptPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_AddOptPanel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_AddOptPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(optionsBtn)
						.addComponent(AddSel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		AddOptPanel.setLayout(gl_AddOptPanel);
		
		JPanel AddList = new JPanel();
		AddList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		AddList.setBackground(new Color(204, 204, 204));
		
		JLabel lblLegendAdditional = new JLabel("Legend : Additional Information");
		lblLegendAdditional.setFont(new Font("Serif", Font.BOLD, 13));
		lblLegendAdditional.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		
		JLabel lblMessages = new JLabel("Messages:");
		lblMessages.setFont(new Font("Serif", Font.BOLD, 14));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_LegendAdd = new GroupLayout(LegendAdd);
		gl_LegendAdd.setHorizontalGroup(
			gl_LegendAdd.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_LegendAdd.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_LegendAdd.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_LegendAdd.createSequentialGroup()
							.addComponent(lblMessages)
							.addContainerGap(248, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_LegendAdd.createSequentialGroup()
							.addGroup(gl_LegendAdd.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
								.addComponent(lblLegendAdditional, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
								.addComponent(AddOptPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(AddList, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
							.addContainerGap())))
		);
		gl_LegendAdd.setVerticalGroup(
			gl_LegendAdd.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_LegendAdd.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLegendAdditional)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(AddOptPanel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(AddList, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblMessages)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
					.addGap(129))
		);
		scrollPane.setViewportView(msgbox);
		msgbox.setBounds(0,0,msgbox.getWidth(),msgbox.getHeight());
		
		msgbox.setColumns(1);
		msgbox.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		msgbox.setRows(3);
		msgbox.setEditable(false);
		
		JLabel ListTitleAdd = new JLabel("List :");
		ListTitleAdd.setFont(new Font("Serif", Font.BOLD, 13));
		ListTitleAdd.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JPanel ListPanel = new JPanel();
		ListPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JButton btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Serif", Font.BOLD, 11));
		GroupLayout gl_AddList = new GroupLayout(AddList);
		gl_AddList.setHorizontalGroup(
			gl_AddList.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_AddList.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_AddList.createParallelGroup(Alignment.TRAILING)
						.addComponent(ListPanel, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
						.addComponent(ListTitleAdd, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnClear))
					.addContainerGap())
		);
		gl_AddList.setVerticalGroup(
			gl_AddList.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_AddList.createSequentialGroup()
					.addGap(12)
					.addComponent(ListTitleAdd)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(ListPanel, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnClear)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		
		JLabel opt_1 = new JLabel("1.");
		
		JLabel opt_2 = new JLabel("2.");
		
		JLabel opt_3 = new JLabel("3.");
		
		JLabel opt_4 = new JLabel("4.");
		GroupLayout gl_ListPanel = new GroupLayout(ListPanel);
		gl_ListPanel.setHorizontalGroup(
			gl_ListPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ListPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_ListPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(opt_4, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
						.addComponent(opt_3, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
						.addComponent(opt_2, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
						.addComponent(opt_1, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(54, Short.MAX_VALUE))
		);
		gl_ListPanel.setVerticalGroup(
			gl_ListPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_ListPanel.createSequentialGroup()
					.addContainerGap(18, Short.MAX_VALUE)
					.addComponent(opt_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(opt_2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(opt_3, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(opt_4, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(22))
		);
		ListPanel.setLayout(gl_ListPanel);
		AddList.setLayout(gl_AddList);
		LegendAdd.setLayout(gl_LegendAdd);
		
		JLabel lblAdditionalInformation = new JLabel("Additional Information");
		lblAdditionalInformation.setFont(new Font("Serif", Font.BOLD, 12));
		lblAdditionalInformation.setBackground(Color.GRAY);
		
		BarchartCluster panel_1 = new BarchartCluster();
		panel_1.test();
		GroupLayout gl_Addinfo = new GroupLayout(Addinfo);
		gl_Addinfo.setHorizontalGroup(
			gl_Addinfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Addinfo.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_Addinfo.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 971, Short.MAX_VALUE)
						.addComponent(lblAdditionalInformation))
					.addContainerGap())
		);
		gl_Addinfo.setVerticalGroup(
			gl_Addinfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Addinfo.createSequentialGroup()
					.addGap(6)
					.addComponent(lblAdditionalInformation, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(38, Short.MAX_VALUE))
		);
		
		JLabel label = new JLabel("");
		
		JLabel label_1 = new JLabel("");
		
		JLabel label_2 = new JLabel("");
		
		JLabel label_3 = new JLabel("");
		Addinfo.setLayout(gl_Addinfo);	
		frmIsPrototype.getContentPane().setLayout(groupLayout);
		start.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				msgbox.append("System started \n");
			}
		});
		mntmExit.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				msgbox.append("System exited \n");
				System.exit(0);
			}
		});
		
	}
}

