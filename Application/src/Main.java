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

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import java.awt.SystemColor;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.ComponentOrientation;
import javax.swing.border.MatteBorder;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

public class Main {

	private JFrame frmIsPrototype;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the application.
	 */
	public Main() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIsPrototype = new JFrame();
		frmIsPrototype.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frmIsPrototype.setResizable(false);
		frmIsPrototype.setBackground(UIManager.getColor("Button.shadow"));
		frmIsPrototype.getContentPane().setBackground(new Color(169, 169, 169));
		frmIsPrototype.setTitle("IS3 Prototype");
		frmIsPrototype.setBounds(100, 100,1336, 720);
		
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
		
		JPanel volumeshow = new JPanel();
		volumeshow.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		volumeshow.setBackground(new Color(204, 204, 204));
		
		JPanel xAxisShow = new JPanel();
		xAxisShow.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		xAxisShow.setBackground(new Color(211, 211, 211));
		
		JLabel lblX = new JLabel("X : ");
		lblX.setFont(new Font("Serif", Font.BOLD, 12));
		
		JLabel xselect = new JLabel("no selection");
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
		
		JLabel yselect = new JLabel("no selection");
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
					.addContainerGap(22, Short.MAX_VALUE))
		);
		gl_yAxisShow.setVerticalGroup(
			gl_yAxisShow.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 39, Short.MAX_VALUE)
				.addGroup(gl_yAxisShow.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_yAxisShow.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblY)
						.addComponent(yselect))
					.addContainerGap())
		);
		yAxisShow.setLayout(gl_yAxisShow);
		
		JLabel lblLegendDrawng = new JLabel("Legend : Drawng Area");
		lblLegendDrawng.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_LegendDA = new GroupLayout(LegendDA);
		gl_LegendDA.setHorizontalGroup(
			gl_LegendDA.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_LegendDA.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_LegendDA.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLegendDrawng, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
						.addComponent(selections, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
						.addGroup(gl_LegendDA.createSequentialGroup()
							.addGroup(gl_LegendDA.createParallelGroup(Alignment.TRAILING)
								.addComponent(countryshow, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
								.addComponent(xAxisShow, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_LegendDA.createParallelGroup(Alignment.LEADING)
								.addComponent(volumeshow, GroupLayout.PREFERRED_SIZE, 169, Short.MAX_VALUE)
								.addComponent(yAxisShow, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_LegendDA.setVerticalGroup(
			gl_LegendDA.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_LegendDA.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLegendDrawng)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_LegendDA.createParallelGroup(Alignment.LEADING)
						.addComponent(yAxisShow, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(xAxisShow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_LegendDA.createParallelGroup(Alignment.LEADING)
						.addComponent(volumeshow, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
						.addComponent(countryshow, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(selections, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(60))
		);
		
		JLabel lblVolume = new JLabel("Volume");
		lblVolume.setFont(new Font("Serif", Font.BOLD, 12));
		lblVolume.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_4.setForeground(Color.WHITE);
		
		JLabel volsmall = new JLabel("small");
		volsmall.setFont(new Font("Serif", Font.ITALIC, 12));
		
		JLabel volmed = new JLabel("medium");
		volmed.setFont(new Font("Serif", Font.ITALIC, 12));
		
		JLabel vollarg = new JLabel("large");
		vollarg.setFont(new Font("Serif", Font.ITALIC, 12));
		
		JLabel volxlarge = new JLabel("xlarge");
		volxlarge.setFont(new Font("Serif", Font.ITALIC, 12));
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_9.setBackground(Color.BLACK);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_10.setBackground(Color.BLACK);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_11.setBackground(Color.BLACK);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_12.setBackground(Color.BLACK);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(panel_12, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(volxlarge, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(panel_11, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(vollarg, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_4.createSequentialGroup()
									.addComponent(panel_9, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(volsmall, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_4.createSequentialGroup()
									.addComponent(panel_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(volmed, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addGap(67)))
					.addContainerGap())
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGap(26)
							.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING)
								.addComponent(volmed, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_4.createSequentialGroup()
									.addComponent(panel_9, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(panel_10, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)))
							.addGap(24)
							.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_11, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(vollarg, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_12, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(volxlarge, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addContainerGap()
							.addComponent(volsmall, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(168, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
		GroupLayout gl_volumeshow = new GroupLayout(volumeshow);
		gl_volumeshow.setHorizontalGroup(
			gl_volumeshow.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_volumeshow.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_volumeshow.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_4, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(lblVolume, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(8, Short.MAX_VALUE))
		);
		gl_volumeshow.setVerticalGroup(
			gl_volumeshow.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_volumeshow.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblVolume, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
					.addContainerGap())
		);
		volumeshow.setLayout(gl_volumeshow);
		
		JLabel lblCountries = new JLabel("Countries");
		lblCountries.setFont(new Font("Serif", Font.BOLD, 12));
		lblCountries.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("MenuItem.background"));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setForeground(SystemColor.text);
		
		JLabel country1 = new JLabel("no selection");
		country1.setFont(new Font("Serif", Font.ITALIC, 11));
		
		JLabel country2 = new JLabel("no selection");
		country2.setFont(new Font("Serif", Font.ITALIC, 11));
		
		JLabel country3 = new JLabel("no selection");
		country3.setFont(new Font("Serif", Font.ITALIC, 11));
		
		JLabel country4 = new JLabel("no selection");
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
						.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 152, Short.MAX_VALUE)
						.addComponent(lblCountries, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_countryshow.setVerticalGroup(
			gl_countryshow.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_countryshow.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCountries)
					.addGap(18)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
					.addContainerGap())
		);
		countryshow.setLayout(gl_countryshow);
		
		Label MainSelectlbl = new Label("Select : ");
		MainSelectlbl.setFont(new Font("Serif", Font.BOLD, 12));
		
		JButton CountryButton = new JButton("Countries");
		CountryButton.setFont(new Font("Serif", Font.BOLD, 11));
		
		JButton AxisButton = new JButton("Axis");
		AxisButton.setFont(new Font("Serif", Font.BOLD, 11));
		
		JButton MainClearButton = new JButton("Clear all");
		MainClearButton.setFont(new Font("Serif", Font.BOLD, 11));
		GroupLayout gl_selections = new GroupLayout(selections);
		gl_selections.setHorizontalGroup(
			gl_selections.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_selections.createSequentialGroup()
					.addGap(15)
					.addComponent(MainSelectlbl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(CountryButton)
					.addGap(5)
					.addComponent(AxisButton)
					.addGap(5)
					.addComponent(MainClearButton))
		);
		gl_selections.setVerticalGroup(
			gl_selections.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_selections.createSequentialGroup()
					.addGap(5)
					.addComponent(CountryButton))
				.addGroup(gl_selections.createSequentialGroup()
					.addGap(6)
					.addComponent(AxisButton))
				.addGroup(gl_selections.createSequentialGroup()
					.addGap(6)
					.addComponent(MainClearButton))
				.addGroup(gl_selections.createSequentialGroup()
					.addGap(5)
					.addComponent(MainSelectlbl, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
					.addContainerGap())
		);
		selections.setLayout(gl_selections);
		LegendDA.setLayout(gl_LegendDA);
		
		Panel Addinfo = new Panel();
		Addinfo.setBackground(new Color(192, 192, 192));
		
		Panel DrawingArea = new Panel();
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
							.addComponent(DrawingArea, GroupLayout.PREFERRED_SIZE, 632, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(LegendDA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(Addinfo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(3)
					.addComponent(LegendAdd, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(LegendAdd, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(LegendDA, 0, 0, Short.MAX_VALUE)
								.addComponent(DrawingArea, GroupLayout.PREFERRED_SIZE, 399, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(Addinfo, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)))
					.addGap(3))
		);
		
		JLabel lblDrawingArea = new JLabel("Drawing Area");
		lblDrawingArea.setFont(new Font("Serif", Font.BOLD, 13));
		lblDrawingArea.setLabelFor(DrawingArea);
		lblDrawingArea.setBackground(Color.GRAY);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		GroupLayout gl_DrawingArea = new GroupLayout(DrawingArea);
		gl_DrawingArea.setHorizontalGroup(
			gl_DrawingArea.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_DrawingArea.createSequentialGroup()
					.addGroup(gl_DrawingArea.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_DrawingArea.createSequentialGroup()
							.addGap(29)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 577, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_DrawingArea.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblDrawingArea, GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_DrawingArea.setVerticalGroup(
			gl_DrawingArea.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_DrawingArea.createSequentialGroup()
					.addComponent(lblDrawingArea, GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(8))
		);
		final JTextArea msgbox = new JTextArea();
		msgbox.setColumns(1);
		msgbox.setRows(10);
		msgbox.setEditable(false);
		Canvas MainCanvas = new Canvas();
		panel_1.add(MainCanvas);
		MainCanvas.setSize(400,360);
		MainCanvas.setBackground(Color.WHITE);
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
		lblLegendAdditional.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		
		JLabel lblMessages = new JLabel("Messages:");
		lblMessages.setFont(new Font("Serif", Font.BOLD, 14));
		GroupLayout gl_LegendAdd = new GroupLayout(LegendAdd);
		gl_LegendAdd.setHorizontalGroup(
			gl_LegendAdd.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_LegendAdd.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_LegendAdd.createParallelGroup(Alignment.LEADING)
						.addComponent(AddList, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
						.addComponent(lblLegendAdditional, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
						.addComponent(AddOptPanel, GroupLayout.PREFERRED_SIZE, 267, Short.MAX_VALUE)
						.addComponent(msgbox, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
						.addComponent(lblMessages))
					.addContainerGap())
		);
		gl_LegendAdd.setVerticalGroup(
			gl_LegendAdd.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_LegendAdd.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLegendAdditional)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(AddOptPanel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
					.addComponent(AddList, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
					.addGap(61)
					.addComponent(lblMessages)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(msgbox, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addGap(44))
		);
		
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
					.addGroup(gl_AddList.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_AddList.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_AddList.createSequentialGroup()
								.addComponent(ListPanel, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
								.addContainerGap())
							.addGroup(gl_AddList.createSequentialGroup()
								.addComponent(ListTitleAdd, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(98, Short.MAX_VALUE)))
						.addGroup(Alignment.TRAILING, gl_AddList.createSequentialGroup()
							.addComponent(btnClear)
							.addContainerGap())))
		);
		gl_AddList.setVerticalGroup(
			gl_AddList.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_AddList.createSequentialGroup()
					.addGap(12)
					.addComponent(ListTitleAdd)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(ListPanel, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(btnClear)
					.addContainerGap(288, Short.MAX_VALUE))
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
		
		JPanel AddPanel = new JPanel();
		AddPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		AddPanel.setBackground(new Color(211,211,211));
		GroupLayout gl_Addinfo = new GroupLayout(Addinfo);
		gl_Addinfo.setHorizontalGroup(
			gl_Addinfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Addinfo.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_Addinfo.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAdditionalInformation)
						.addComponent(AddPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 986, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_Addinfo.setVerticalGroup(
			gl_Addinfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Addinfo.createSequentialGroup()
					.addGap(6)
					.addComponent(lblAdditionalInformation)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(AddPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		Canvas BarGraphCanvas = new Canvas();
		BarGraphCanvas.setBackground(Color.WHITE);
		BarGraphCanvas.setSize(800,200);
		AddPanel.add(BarGraphCanvas);
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
			}
		});
	}
}

