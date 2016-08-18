import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;
import java.awt.SystemColor;

public class Gui {

	private JFrame frmUncGpaCalculator;
	private static int x_coords;
	private static int y_coords;
	private int field_count;
	private ArrayList<JTextField> letterGrades;
	private ArrayList<JComboBox<Double>> credits;
	private double[] grades;
	private JTextField gpaDisplay;
	private double gpa;
	private double finalTotal;
	private double[] total;
	private double totalCredits;
	private JTextField tutorial;
	Border emptyBorder = BorderFactory.createEmptyBorder();
	private JLabel courseShow;
	private Color carolinaBlue;
	private String past_grade;
	PrintWriter pw;
	BufferedReader br;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frmUncGpaCalculator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		
		try {
			FileWriter fw = new FileWriter("pastlogs.txt");
			pw = new PrintWriter(fw);
			FileReader fr = new FileReader("pastlogs.txt");
			br = new BufferedReader(fr);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		
		
		MetalLookAndFeel.setCurrentTheme(new OceanTheme());
		try {
			UIManager.setLookAndFeel(new MetalLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		x_coords = 5;
		y_coords = 5;
		field_count = 0;
		letterGrades = new ArrayList<JTextField>();
		credits = new ArrayList<JComboBox<Double>>();
		initialize();
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		field_count = 0;
		carolinaBlue = new Color(123, 175, 212);

		
		frmUncGpaCalculator = new JFrame();
		frmUncGpaCalculator.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\marclane\\Pictures\\unc.png"));
		frmUncGpaCalculator.setTitle("UNC GPA Calculator V2");
		frmUncGpaCalculator.getContentPane().setBackground(SystemColor.text);
		frmUncGpaCalculator.setBounds(100, 100, 450, 300);
		frmUncGpaCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUncGpaCalculator.getContentPane().setLayout(null);
		Image img = new ImageIcon(this.getClass().getResource("/plus.png")).getImage();
		frmUncGpaCalculator.setResizable(false);
		
		

		
		
		JButton btnCalculate = new JButton("Calculate GPA");
		btnCalculate.setFont(new Font("Stencil", Font.PLAIN, 10));
		btnCalculate.setBackground((carolinaBlue));
		btnCalculate.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));;
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean valid = true;
				finalTotal = 0.0;
				grades = new double[letterGrades.size()];
				for(int i = 0; i <letterGrades.size(); i++) {
					grades[i] = gradeConverter(letterGrades.get(i));
				}
				for(int j = 0; j < grades.length; j++) {
					total = new double[grades.length];
					 total[j] = (grades[j] * (double)credits.get(j).getSelectedItem());
					 finalTotal += total[j];
				totalCredits += (double)credits.get(j).getSelectedItem();
				
						
				}
				
				String[] isValid = new String[grades.length];
				String invalidNums = " ";
				
				for(int k = 0; k<grades.length; k++) {
					if(grades[k] == -.1) {
						isValid[k] = "false";
						
						invalidNums = invalidNums  + String.valueOf(k+1)+ " , ";
						
					}
						
					}
				if(Arrays.asList(isValid).contains("false")) {
					valid = false;
					JOptionPane.showMessageDialog(null, "Invalid grade(s) on line(s)" + invalidNums);
					
				}
				
				gpa =  (finalTotal/totalCredits);
				
				if (valid == true) {
				gpaDisplay.setText("                " + String.format("%.2f", gpa)+ "!");
				}
				
				pw.println(String.format("%.2f", gpa));
				
				try {
					System.out.println(br.readLine());
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				try {
					textField.setText(br.readLine());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				finalTotal = 0.0;
				totalCredits = 0.0;
				
			
			}
		});
		
		btnCalculate.setBounds(354, 5, 90, 73);
		frmUncGpaCalculator.getContentPane().add(btnCalculate);
		Image img3 = new ImageIcon(this.getClass().getResource("/minus.png")).getImage();
		
		gpaDisplay = new JTextField();
		gpaDisplay.setEditable(false);
		gpaDisplay.setFont(new Font("Stencil", Font.PLAIN,13));
		gpaDisplay.setText("Hope for that 4.0!");
		gpaDisplay.setBackground(carolinaBlue);
		gpaDisplay.setBounds(293, 160, 126, 26);
		frmUncGpaCalculator.getContentPane().add(gpaDisplay);
		gpaDisplay.setColumns(10);
		
		tutorial = new JTextField("Click + to Add");
		tutorial.setFont(new Font("Arial", Font.BOLD, 9));
		tutorial.setEditable(false);
		tutorial.setBounds(5, 5, 75, 20);
		frmUncGpaCalculator.getContentPane().add(tutorial);
		tutorial.setColumns(10);
		
		JLabel label = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/well.jpg")).getImage();
		label.setIcon(new ImageIcon(img1));
		label.setBounds(263, 83, 205, 198);
		frmUncGpaCalculator.getContentPane().add(label);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder((carolinaBlue), 5, true));
		panel.setBackground(new Color(240,240,240));
		panel.setBounds(138, 0, 126, 271);
		frmUncGpaCalculator.getContentPane().add(panel);
				panel.setLayout(null);
				
				courseShow = new JLabel();
				courseShow.setForeground(new Color(51, 153, 204));
				courseShow.setFont(new Font("Stencil",Font.BOLD,35));
				courseShow.setBounds(27, 25, 72, 53);
				panel.add(courseShow);
				
				
		
				JButton addCourse = new JButton("+");
				addCourse.setFont(new Font("Stencil", Font.PLAIN, 35));
				addCourse.setBounds(37, 125, 49, 41);
				addCourse.setForeground(carolinaBlue);
				addCourse.setBorder((new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
				panel.add(addCourse);
				addCourse.setBorder(emptyBorder);
				addCourse.setBackground(Color.WHITE);
				//addCourse.setIcon(new ImageIcon(img));
				
				JLabel lblAddClass = new JLabel("Add Course");
				lblAddClass.setForeground(new Color(102, 153, 204));
				lblAddClass.setBounds(27, 106, 66, 14);
				panel.add(lblAddClass);
				lblAddClass.setBackground(Color.CYAN);
				lblAddClass.setFont(new Font("Stencil", Font.PLAIN, 11));
				
						JButton deleteCourse = new JButton("-");
						deleteCourse.setForeground(carolinaBlue);
						deleteCourse.setFont(new Font("Stencil", Font.PLAIN, 44));
						deleteCourse.setBounds(37, 175, 49, 41);
						panel.add(deleteCourse);
						deleteCourse.setBorder((new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
						deleteCourse.setBorder(emptyBorder);
						deleteCourse.setBackground(Color.WHITE);
						//deleteCourse.setIcon(new ImageIcon(img3));
						
						JLabel lblRemoveClass = new JLabel("Remove Course");
						lblRemoveClass.setForeground(new Color(51, 153, 204));
						lblRemoveClass.setBounds(20, 224, 99, 14);
						panel.add(lblRemoveClass);
						lblRemoveClass.setFont(new Font("Stencil", Font.PLAIN, 11));
						
						JPanel panel_1 = new JPanel();
						panel_1.setBorder(new LineBorder(new Color(123, 175, 212), 5, true));
						panel_1.setBounds(0, 0, 126, 78);
						panel.add(panel_1);
						panel_1.setLayout(null);
						
						JLabel lblCourses = new JLabel("Course(s)");
						lblCourses.setBounds(30, 11, 56, 11);
						panel_1.add(lblCourses);
						lblCourses.setForeground(new Color(51, 153, 204));
						lblCourses.setFont(new Font("Stencil", Font.PLAIN, 11));
						
						JPanel panel_2 = new JPanel();
						panel_2.setBounds(263, -12, 181, 101);
						frmUncGpaCalculator.getContentPane().add(panel_2);
						panel_2.setLayout(null);
						
						JLabel pastLog = new JLabel("New label");
						pastLog.setBounds(10, 44, 75, 14);
						panel_2.add(pastLog);
						pastLog.setText(past_grade);
						
						textField = new JTextField();
						textField.setBounds(4, 44, 86, 14);
						panel_2.add(textField);
						textField.setColumns(10);
						textField.setEditable(false);
						
						
						JLabel lblPastGpa = new JLabel("Past GPA");
						lblPastGpa.setFont(new Font("Stencil", Font.PLAIN, 11));
						lblPastGpa.setBounds(20, 26, 59, 14);
						panel_2.add(lblPastGpa);
						
						
						
						deleteCourse.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								
								if (!letterGrades.isEmpty()) {
									JTextField last_added = letterGrades.remove(letterGrades.size()-1);
									y_coords -= (last_added.getHeight()+18);
									tutorial.setBounds(x_coords, y_coords,75 , 20);
									frmUncGpaCalculator.remove(last_added);
									frmUncGpaCalculator.repaint();
									field_count--;
									courseShow.setText("  " + String.valueOf(field_count));
									
								}
								
								if (!credits.isEmpty()) {
									JComboBox<Double> last_add = credits.remove(credits.size()-1);
//					y_coords -= last
									frmUncGpaCalculator.remove(last_add);
									frmUncGpaCalculator.repaint();
								}
							}
						});
						
						
				addCourse.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						
						if (field_count >= 7) {
							JOptionPane.showMessageDialog(null, "Max Course Number Reached");
							return;
						}
						
						
						
						JTextField newTextField = new JTextField();
						newTextField.setBounds(x_coords, y_coords, 75, 20);	
						y_coords += newTextField.getHeight() + 18;
						tutorial.setBounds(x_coords, y_coords,75 , 20);
						// frame.getContentPane().add(newTextField);
						frmUncGpaCalculator.getContentPane().add(newTextField);
						frmUncGpaCalculator.getContentPane().validate();
						frmUncGpaCalculator.getContentPane().repaint();
						field_count++;
						courseShow.setText("  " + String.valueOf(field_count));
						
						letterGrades.add(newTextField);
						
						Double[] creds = {1.0, 2.0, 3.0, 4.0};
						JComboBox<Double> comboBox = new JComboBox<Double>(creds);
						comboBox.setBounds(x_coords+80, y_coords-38, 45, 20);
						frmUncGpaCalculator.getContentPane().add(comboBox);
						frmUncGpaCalculator.validate();
						frmUncGpaCalculator.repaint();
						credits.add(comboBox);
						
						
			
						

					}
				});
		
			
	}
	public double gradeConverter(JTextField text) {
		
		if( text.getText().equalsIgnoreCase("A")) {
			return 4.0;
		}else if(text.getText().equalsIgnoreCase("A-")) {
			return 3.7;
		} else if (text.getText().equalsIgnoreCase("B+")) {
			return 3.3;
		}else if (text.getText().equalsIgnoreCase("B")) {
			return 3.0;
		}else if(text.getText().equalsIgnoreCase("B-")) {
			return 2.7;
		} else if (text.getText().equalsIgnoreCase("C+")) {
			return 2.3;
		} else if (text.getText().equalsIgnoreCase("C")) {
			return 2.0;
		} else if(text.getText().equalsIgnoreCase("C-")) {
			return 1.7;
		}else if (text.getText().equalsIgnoreCase("D+")) {
			return 1.3;
		} else if (text.getText().equalsIgnoreCase("D")) {
			return 1.0;
		} else if (text.getText().equalsIgnoreCase("F")) {
			return 0.0;
		} else { 
			return -0.1;
				
			}
		}
}
