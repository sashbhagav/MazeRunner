/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arduinogame;

/**
 *
 * @author sashreek
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuTest {
	//this class is for setting up my JFrame 
	public MyPanel mainMenu;
	public MyPanel2 levelMenu;//initializing my variables
		
	private void menuDisplay() {
		JFrame frame = new JFrame("JAVA MAZE");//Name of Frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		
		JPanel panel = new JPanel();
		panel.setLayout(new CardLayout());
		
		mainMenu = new MyPanel(panel);

		mainMenu.setBackground(Color.BLACK);
		levelMenu = new MyPanel2();
		levelMenu.setBackground(Color.BLACK);//setting color of the Frame
			
		panel.add(mainMenu);
		panel.add(levelMenu);
		frame.setContentPane(panel);
		frame.setVisible(true);
				
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
					new MenuTest().menuDisplay();
				
			}
		});
	}
}

class MyPanel extends JPanel {

	private JButton buttonPlay;//play button
	public JPanel contentPane;
	private JLabel name;//displays name of game
	private JLabel subName;//author of game
	public MyPanel panel2;

	public MyPanel(JPanel panel) {

		contentPane = panel;

		buttonPlay = new JButton("Play");//setting up the play button
		buttonPlay.setFont(new Font("Algerian", Font.ITALIC,40 ));
		buttonPlay.setFocusPainted(false);
		buttonPlay.setBackground(Color.RED);//color of button
		buttonPlay.setForeground(Color.WHITE);//color of words
		buttonPlay.setLocation(200, 100);
		buttonPlay.setBounds(200, 300, 200, 100);
		
		name = new JLabel("MAZE RUN");//displays name of game
		subName = new JLabel ("By: Sashreek Bhagavatula");
		name.setFont(new Font("Algerian", Font.BOLD,70));
		name.setForeground(Color.BLUE);
		subName.setFont(new Font("Algerian", Font.BOLD,20));
		subName.setForeground(Color.BLUE);
		name.setBounds(110, 10, 590, 300);
		subName.setBounds (130, 50, 590, 300);
	
		buttonPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) contentPane.getLayout();
				cardLayout.next(contentPane);
			}
		});
		add(name);
		add(subName);
		this.setLayout(null);
		add(buttonPlay);
		buttonPlay.setVisible(true);
		}

	public static void createFrame() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {//running the frame to open up the next frame
				JFrame frame = new JFrame("Levels");//next frame is called Levels
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//disposes of previous frame
				
				/*try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}*/

				frame.pack();
				frame.setLocationByPlatform(true);
				frame.setVisible(true);
				frame.setResizable(false);

			}
		});
	}
}
class MyPanel2 extends JPanel {
	//various buttons for level panel
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	private JButton button7;
	private JButton button8;
	private JLabel label;
	private JLabel label2;
	
	public MyPanel2() {
		final boolean solutionFlg = false;
		// buttons for levels and solutions for each level
		//setting background of button to LightGray for each button
		button1 = new JButton("Level 1"); 
		button1.setBackground(Color.RED);
		button2 = new JButton("Level 2"); 
		button2.setBackground(Color.RED);
		button3 = new JButton("Level 3"); 
		button3.setBackground(Color.RED);
		button4 = new JButton("Level 4");	
		button4.setBackground(Color.RED);
		button5 = new JButton("Solution 1");
		button5.setBackground(Color.RED);
		button6 = new JButton("Solution 2");
		button6.setBackground(Color.RED);
		button7 = new JButton("Solution 3");
		button7.setBackground(Color.RED);
		button8 = new JButton("Solution 4");
		button8.setBackground(Color.RED);
		label = new JLabel(" Rules: You are the red dot. Find a path to the blue box");//rules
		label2 = new JLabel("Select Level");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Algerian", Font.BOLD,15));
		label2.setForeground(Color.BLUE);
		label2.setFont(new Font("Algerian", Font.BOLD, 25));
			// adjust size and set layout
		setPreferredSize(new Dimension(395, 156));
		setLayout(null);

		//setting the location of each button and JLabel
		button1.setBounds(55, 150, 100, 25);
		button2.setBounds(170, 150, 100, 25);
		button3.setBounds(285, 150, 100, 25);
		button4.setBounds(400, 150, 100, 25);
		button5.setBounds(55, 190, 100, 25);
		button6.setBounds(170, 190, 100, 25);
		button7.setBounds(285, 190, 100, 25);
		button8.setBounds(400, 190, 100, 25);
		label.setBounds (10, 300, 590, 25);
		label2.setBounds(175, 70, 590, 25);

		//method used to launch each level of game
		//calls the methods in the Header class 
		//each level is instantiated
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Header.launchGame(1, solutionFlg);
			}
		});

		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Header.launchGame(2, solutionFlg);

			}
		});
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Header.launchGame(3, solutionFlg);

			}
		});
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Header.launchGame(4, solutionFlg);

			}
		});
		//buttons used to launch solutions
		//calls the recSolve method in the Header class
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Header.launchGame(1, true);
			}
		});
		button6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Header.launchGame(2, true);
			}
		});
		button7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Header.launchGame(3, true);
			}
		});
		button8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Header.launchGame(4, true);
			}
		});
		// adding all buttons in Panel
		add(button1);
		add(button2);
		add(button3);
		add(button4);
		add(button5);
		add(button6);
		add(button7);
		add(button8);
		add(label);
		add(label2);
	}

}