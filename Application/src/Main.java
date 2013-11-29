import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.BoxLayout;
import com.jgoodies.forms.factories.FormFactory;
import java.awt.Panel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Label;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Canvas;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


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
		frmIsPrototype.setAlwaysOnTop(true);
		frmIsPrototype.setTitle("IS3 Prototype");
		frmIsPrototype.setBounds(100, 100,640, 480);
		
		JMenuBar menuBar = new JMenuBar();
		frmIsPrototype.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		JButton startExit = new JButton("Start");
		mnFile.add(startExit);
		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			System.exit(0);
			}
		});
		mnFile.add(btnExit);
		frmIsPrototype.getContentPane().setLayout(new BorderLayout(0, 0));
		
		Panel panel = new Panel();
		frmIsPrototype.getContentPane().add(panel, BorderLayout.EAST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{66, 0, 60, 36, 39, 0};
		gbl_panel.rowHeights = new int[]{24, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		Label select = new Label("Select : ");
		select.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_select = new GridBagConstraints();
		gbc_select.anchor = GridBagConstraints.NORTHWEST;
		gbc_select.insets = new Insets(0, 0, 5, 5);
		gbc_select.gridx = 1;
		gbc_select.gridy = 7;
		panel.add(select, gbc_select);
		
		Button countries = new Button("Countries");
		GridBagConstraints gbc_countries = new GridBagConstraints();
		gbc_countries.anchor = GridBagConstraints.WEST;
		gbc_countries.insets = new Insets(0, 0, 5, 5);
		gbc_countries.gridx = 2;
		gbc_countries.gridy = 7;
		panel.add(countries, gbc_countries);
		
		Button axis = new Button("Axis");
		GridBagConstraints gbc_axis = new GridBagConstraints();
		gbc_axis.anchor = GridBagConstraints.WEST;
		gbc_axis.insets = new Insets(0, 0, 5, 5);
		gbc_axis.gridx = 3;
		gbc_axis.gridy = 7;
		panel.add(axis, gbc_axis);
		
		Button clear = new Button("Clear");
		GridBagConstraints gbc_clear = new GridBagConstraints();
		gbc_clear.insets = new Insets(0, 0, 5, 0);
		gbc_clear.anchor = GridBagConstraints.WEST;
		gbc_clear.gridx = 4;
		gbc_clear.gridy = 7;
		panel.add(clear, gbc_clear);
		
		Panel panel_1 = new Panel();
		frmIsPrototype.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		Panel panel_3 = new Panel();
		panel_1.add(panel_3);
		
		Label label = new Label("Select : ");
		panel_1.add(label);
		label.setFont(new Font("Dialog", Font.BOLD, 13));
		
		Button option = new Button("Options");
		panel_1.add(option);
		
		JLabel lblNewLabel = new JLabel("List :");
		panel_1.add(lblNewLabel);
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		
		Panel panel_2 = new Panel();
		frmIsPrototype.getContentPane().add(panel_2, BorderLayout.CENTER);
		
		Canvas canvas = new Canvas();
		canvas.setSize(400,320);
		canvas.setBackground(Color.WHITE);
		panel_2.add(canvas);
		
		Panel panel_5 = new Panel();
		frmIsPrototype.getContentPane().add(panel_5, BorderLayout.NORTH);
		
	}

}
