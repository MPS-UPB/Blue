package GUI;

/**
 * Rotate
 * 
 * @author Valentina Bold
 * @version 1.0, 19 Nov 2012
 */

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Rotate extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String NAME = "Rotate";
	public Transform operation;

	public Rotate() {
		operation = new Transform(NAME);
	}

	public void rotateParameter(String parameter, String value) {
		operation.setParameter(parameter, value);
	}

	public void createRotateWindow(String imgName) {
		JFrame frame = new JFrame("Rotate Demo");
		Box box = Box.createVerticalBox();
		Box box_button = Box.createHorizontalBox();
		JLabel operation = new JLabel(NAME);
		JLabel enter = new JLabel("\n");
		JLabel space = new JLabel("           ");
		JLabel img = new JLabel(imgName);
		JLabel angle = new JLabel("Angle");
		JTextField angle_value = new JTextField();
		JButton process_image = new JButton("Process");
		JButton next = new JButton("Continue");

		frame.add(box, BorderLayout.NORTH);
		frame.add(box_button);

		box.add(enter);
		box.add(enter);
		box.add(operation);
		box.add(img);
		box.add(enter);
		box.add(enter);
		box.add(angle);
		box.add(angle_value);

		box_button.add(space);
		box_button.add(process_image);
		box_button.add(next);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(250, 200);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void print() {
		operation.print();
	}
}
