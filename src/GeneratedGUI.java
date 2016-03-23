import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

public class GeneratedGUI extends JFrame {

	private JPanel contentPane;
	private JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;
	private JButton check;
	private int index = 1;
	private JLabel word;
	private JTextField ans;
	private String[] words = { "word1", "word2", "word3", "word4", "word5",
			"word6", "word7", "word8", "word9", "word10" };

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GeneratedGUI frame = new GeneratedGUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public static void render(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GeneratedGUI frame = new GeneratedGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GeneratedGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		b1 = new JButton("1");
		b1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		b1.setBounds(10, 10, 40, 40);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				index = 1;
				word.setText(words[index - 1]);
			}
		});
		contentPane.add(b1);

		b2 = new JButton("2");
		b2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		b2.setBounds(60, 10, 40, 40);
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				index = 2;
				word.setText(words[index - 1]);
			}
		});
		contentPane.add(b2);

		b3 = new JButton("3");
		b3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		b3.setBounds(110, 10, 40, 40);
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				index = 3;
				word.setText(words[index - 1]);
			}
		});
		contentPane.add(b3);

		b4 = new JButton("4");
		b4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		b4.setBounds(160, 10, 40, 40);
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				index = 4;
				word.setText(words[index - 1]);
			}
		});
		contentPane.add(b4);

		b5 = new JButton("5");
		b5.setFont(new Font("Tahoma", Font.PLAIN, 10));
		b5.setBounds(210, 10, 40, 40);
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				index = 5;
				word.setText(words[index - 1]);
			}
		});
		contentPane.add(b5);

		b6 = new JButton("6");
		b6.setFont(new Font("Tahoma", Font.PLAIN, 10));
		b6.setBounds(260, 10, 40, 40);
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				index = 6;
				word.setText(words[index - 1]);
			}
		});
		contentPane.add(b6);

		b7 = new JButton("7");
		b7.setFont(new Font("Tahoma", Font.PLAIN, 10));
		b7.setBounds(310, 10, 40, 40);
		b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				index = 7;
				word.setText(words[index - 1]);
			}
		});
		contentPane.add(b7);

		b8 = new JButton("8");
		b8.setFont(new Font("Tahoma", Font.PLAIN, 10));
		b8.setBounds(360, 10, 40, 40);
		b8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				index = 8;
				word.setText(words[index - 1]);
			}
		});
		contentPane.add(b8);

		b9 = new JButton("9");
		b9.setFont(new Font("Tahoma", Font.PLAIN, 10));
		b9.setBounds(410, 10, 40, 40);
		b9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				index = 9;
				word.setText(words[index - 1]);
			}
		});
		contentPane.add(b9);

		b10 = new JButton("10");
		b10.setFont(new Font("Tahoma", Font.PLAIN, 10));
		b10.setBounds(460, 10, 45, 40);
		b10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				index = 10;
				word.setText(words[index - 1]);
			}
		});
		contentPane.add(b10);

		final JLabel prompt = new JLabel("Write the translation");
		prompt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		prompt.setBounds(15, 60, 150, 40);
		contentPane.add(prompt);

		word = new JLabel(words[index - 1]);
		word.setFont(new Font("Tahoma", Font.PLAIN, 16));
		word.setBounds(10, 110, 150, 20);
		contentPane.add(word);

		ans = new JTextField();
		ans.setBounds(110, 110, 150, 20);
		ans.setColumns(20);
		contentPane.add(ans);

		check = new JButton("Check");
		check.setBounds(425, 210, 80, 40);
		contentPane.add(check);

		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String strAns = ans.getText();

				switch (index) {
				case 1:
					if (strAns.equals("word1")) {
						b1.setBackground(Color.green);
					} else {
						b1.setBackground(Color.RED);
					}
					b1.setEnabled(false);
					break;
				case 2:
					b2.setBackground(Color.RED);
					b2.setEnabled(false);
					break;
				case 3:
					b3.setBackground(Color.red);
					b3.setEnabled(false);
				}

			}
		});
	}
}
