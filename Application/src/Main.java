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
		frmIsPrototype.setResizable(false);
		frmIsPrototype.setBackground(UIManager.getColor("Button.shadow"));
		frmIsPrototype.getContentPane().setBackground(UIManager.getColor("Button.shadow"));
		frmIsPrototype.setTitle("IS3 Prototype");
		frmIsPrototype.setBounds(100, 100,1020, 980);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(UIManager.getColor("MenuBar.borderColor"));
		frmIsPrototype.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Serif", Font.BOLD, 12));
		mnFile.setBackground(new Color(153, 204, 255));
		menuBar.add(mnFile);
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Serif", Font.BOLD, 12));
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			System.exit(0);
			}
		});
		mnFile.add(btnExit);
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setFont(new Font("Serif", Font.BOLD, 12));
		menuBar.add(mnHelp);
		
		JButton btnAbout = new JButton("About");
		btnAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				JOptionPane.showMessageDialog(frmIsPrototype," This software was made by \n Ioannis Cleary(1104205c), Matthew Bown(1002723b), \n Gavin Davidson(1102607d), Luka Prelic(1107827p), \n Georgios Moleskis(1103614m) and Mario Moro Hernandez(1106740m) \n");

			}
		});
		btnAbout.setFont(new Font("Serif", Font.BOLD, 12));
		mnHelp.add(btnAbout);
		
		JButton btnManual = new JButton("Manual");
		btnManual.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(frmIsPrototype," \n To run this software you need to follow these steps \n\n 1. Select a country/countries by clicking on the Countries button from the Legend: Drawing Area. \n 2. Select values for the X and Y axis by clicking the Axis button from the Legend: Drawing Area. \n\n (A Scatter plot should have been created by now on the Drawing Area and corresonding values should be displayed on the right.) \n \n 3. You can select additional information other than the default by clicking the Options button from the Legend: Additional Information.\n\n (This Legend will now display your selections and bar graphs should be generated on the left) \n\n 4. There are 2 clear buttons the one on the bottom of the screen clears only the Additinal Information, the other one clears everything. \n \n");

			}
		});
		btnManual.setFont(new Font("Serif", Font.BOLD, 12));
		mnHelp.add(btnManual);
		
		Panel LegendDA = new Panel();
		LegendDA.setBackground(new Color(204, 255, 255));
		
		JPanel selections = new JPanel();
		selections.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		selections.setBackground(new Color(255, 255, 255));
		
		JPanel countryshow = new JPanel();
		countryshow.setBackground(new Color(255, 255, 255));
		
		JPanel volumeshow = new JPanel();
		volumeshow.setBackground(UIManager.getColor("MenuBar.highlight"));
		
		JPanel xAxisShow = new JPanel();
		xAxisShow.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		xAxisShow.setBackground(Color.WHITE);
		
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
		yAxisShow.setBackground(Color.WHITE);
		
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
		GroupLayout gl_LegendDA = new GroupLayout(LegendDA);
		gl_LegendDA.setHorizontalGroup(
			gl_LegendDA.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_LegendDA.createSequentialGroup()
					.addGroup(gl_LegendDA.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_LegendDA.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_LegendDA.createParallelGroup(Alignment.LEADING)
								.addComponent(selections, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
								.addGroup(gl_LegendDA.createSequentialGroup()
									.addGroup(gl_LegendDA.createParallelGroup(Alignment.TRAILING)
										.addComponent(countryshow, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
										.addComponent(xAxisShow, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_LegendDA.createParallelGroup(Alignment.LEADING)
										.addComponent(volumeshow, GroupLayout.PREFERRED_SIZE, 169, Short.MAX_VALUE)
										.addComponent(yAxisShow, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)))))
						.addGroup(gl_LegendDA.createSequentialGroup()
							.addGap(102)
							.addComponent(lblLegendDrawng)))
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
						.addComponent(volumeshow, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
						.addComponent(countryshow, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE))
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
		panel_9.setBackground(SystemColor.textHighlight);
		
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
		MainSelectlbl.setBackground(new Color(255, 255, 255));
		MainSelectlbl.setFont(new Font("Serif", Font.BOLD, 12));
		
		JButton CountryButton = new JButton("Countries");
		CountryButton.setFont(new Font("Serif", Font.BOLD, 11));
		
		JButton AxisButton = new JButton("Axis");
		AxisButton.setFont(new Font("Serif", Font.BOLD, 11));
		
		JButton MainClearButton = new JButton("Clear");
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
		
		Panel AddDA = new Panel();
		AddDA.setBackground(new Color(211, 211, 211));
		
		Panel DrawingArea = new Panel();
		DrawingArea.setBackground(new Color(204, 255, 255));
		
		Canvas MainCanvas = new Canvas();
		MainCanvas.setSize(480,480);
		MainCanvas.setBackground(Color.WHITE);
		
		JPanel LegendAdd = new JPanel();
		LegendAdd.setBackground(new Color(192, 192, 192));
		GroupLayout groupLayout = new GroupLayout(frmIsPrototype.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(Addinfo, 0, 0, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(LegendAdd, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(DrawingArea, GroupLayout.PREFERRED_SIZE, 611, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
							.addComponent(LegendDA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(85))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(DrawingArea, GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
						.addComponent(LegendDA, GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(LegendAdd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(Addinfo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JLabel lblDrawingArea = new JLabel("Drawing Area");
		lblDrawingArea.setFont(new Font("Serif", Font.BOLD, 12));
		lblDrawingArea.setLabelFor(DrawingArea);
		lblDrawingArea.setBackground(Color.GRAY);
		GroupLayout gl_DrawingArea = new GroupLayout(DrawingArea);
		gl_DrawingArea.setHorizontalGroup(
			gl_DrawingArea.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_DrawingArea.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_DrawingArea.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblDrawingArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(MainCanvas, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		gl_DrawingArea.setVerticalGroup(
			gl_DrawingArea.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_DrawingArea.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDrawingArea)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(MainCanvas, GroupLayout.PREFERRED_SIZE, 494, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		DrawingArea.setLayout(gl_DrawingArea);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBackground(new Color(204, 204, 204));
		
		Label label = new Label("Select : ");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Serif", Font.BOLD, 12));
		
		JButton btnNewButton_3 = new JButton("Options");
		btnNewButton_3.setHorizontalAlignment(SwingConstants.TRAILING);
		btnNewButton_3.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton_3.setFont(new Font("Serif", Font.BOLD, 11));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_3)
					.addGap(37))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_3))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBackground(new Color(204, 204, 204));
		
		JLabel lblLegendAdditional = new JLabel("Legend : Additional Information");
		GroupLayout gl_LegendAdd = new GroupLayout(LegendAdd);
		gl_LegendAdd.setHorizontalGroup(
			gl_LegendAdd.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_LegendAdd.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_LegendAdd.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
						.addComponent(lblLegendAdditional, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_LegendAdd.setVerticalGroup(
			gl_LegendAdd.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_LegendAdd.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLegendAdditional)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel ListTitleAdd = new JLabel("List :");
		ListTitleAdd.setFont(new Font("Serif", Font.BOLD, 13));
		ListTitleAdd.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JButton btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Serif", Font.BOLD, 11));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_2.createSequentialGroup()
								.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
								.addContainerGap())
							.addGroup(gl_panel_2.createSequentialGroup()
								.addComponent(ListTitleAdd, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
								.addGap(82)))
						.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
							.addComponent(btnClear)
							.addContainerGap())))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(5)
					.addComponent(ListTitleAdd)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnClear)
					.addContainerGap())
		);
		
		JLabel opt_1 = new JLabel("1.");
		
		JLabel opt_2 = new JLabel("2.");
		
		JLabel opt_3 = new JLabel("3.");
		
		JLabel opt_4 = new JLabel("4.");
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(opt_4, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
						.addComponent(opt_3, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
						.addComponent(opt_2, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
						.addComponent(opt_1, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(54, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
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
		panel_3.setLayout(gl_panel_3);
		panel_2.setLayout(gl_panel_2);
		LegendAdd.setLayout(gl_LegendAdd);
		
		JLabel lblAdditionalInformation = new JLabel("Additional Information");
		lblAdditionalInformation.setFont(new Font("Serif", Font.BOLD, 12));
		lblAdditionalInformation.setBackground(Color.GRAY);
		GroupLayout gl_Addinfo = new GroupLayout(Addinfo);
		gl_Addinfo.setHorizontalGroup(
			gl_Addinfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Addinfo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_Addinfo.createParallelGroup(Alignment.LEADING)
						.addComponent(AddDA, GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
						.addComponent(lblAdditionalInformation, GroupLayout.PREFERRED_SIZE, 589, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_Addinfo.setVerticalGroup(
			gl_Addinfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Addinfo.createSequentialGroup()
					.addGap(10)
					.addComponent(lblAdditionalInformation)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(AddDA, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		Canvas bargraph1 = new Canvas();
		bargraph1.setSize(600, 200);
		bargraph1.setBackground(Color.WHITE);
		GroupLayout gl_AddDA = new GroupLayout(AddDA);
		gl_AddDA.setHorizontalGroup(
			gl_AddDA.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_AddDA.createSequentialGroup()
					.addContainerGap()
					.addComponent(bargraph1, GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_AddDA.setVerticalGroup(
			gl_AddDA.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_AddDA.createSequentialGroup()
					.addContainerGap()
					.addComponent(bargraph1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		AddDA.setLayout(gl_AddDA);
		Addinfo.setLayout(gl_Addinfo);	
		frmIsPrototype.getContentPane().setLayout(groupLayout);
	}
}

