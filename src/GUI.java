import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.SpringLayout.Constraints;

public class GUI {

	static public String dealer, vul;
	static private JPanel middle;
	static JFrame frame;
	private static final int NUM_OF_BUTTONS = 10;
	static JButton[] buttons = new JButton[NUM_OF_BUTTONS];

	public static void addComponentsToPane(Container pane) {

		pane.setLayout(new GridLayout(4, 1));
		
		// numbers pane
		JPanel numbers = new JPanel(new GridLayout(1, NUM_OF_BUTTONS, 4, 2));
		addButtons(numbers);
		pane.add(numbers);
		
		// space
		pane.add(new JPanel(new GridLayout(1, NUM_OF_BUTTONS)));

		// game pane
		JPanel game = new JPanel();
		game.setLayout(new GridLayout(2, 2));
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		game.add(new JLabel("Write the tranlation"), c);
		
		c.gridx = 1;
		c.gridy = 1;
		game.add(new JLabel(""), c);

		c.gridx = 2;
		c.gridy = 0;
		game.add(new JLabel("<word>"), c);

		c.gridx = 2;
		c.gridy = 1;
		game.add(new JTextPane(), c);
		
		pane.add(game);
		
		// done
		JPanel done = new JPanel(new GridLayout(1, 2));
		done.add(new JLabel(""));
		done.add(new JButton("Done"));
		pane.add(done);
		


	}

	private static void addButtons(JPanel numbers) {
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 1;
		c.gridy = 1;
		//
		for (int i = 1; i <= NUM_OF_BUTTONS; i++) {
			JButton jb = new JButton(String.valueOf(i));
			jb.setEnabled(true);
			buttons[i - 1] = jb;
			numbers.add(jb, c);
			c.gridx++;
		}

	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		frame = new JFrame("Java Quiz");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(50, 50);

		// Set up the content pane.
		addComponentsToPane(frame.getContentPane());

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public GUI() {
		// render();
	}

	public static void render() {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

}
