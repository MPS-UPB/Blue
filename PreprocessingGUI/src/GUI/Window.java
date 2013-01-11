package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class Window extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton processButton = new JButton(
			"<html><font color = 'red'>P</font>" + "rocess</html>");
	private JButton continueButton = new JButton(
			"<html><font color = 'red'>C</font>" + "ontinue</html>");
	private static Color bleu = new Color(94, 174, 255);
	private static JLabel copy = new JLabel(
			"<html><font size = '-2' color = 'red'>"
					+ "<i>&copy; 2012 </i></font></html>");

	public String execName = "";
	public String execDescription = "";
	public Map<String, String> parameters;

	public Map<String, String> parametersValue = new HashMap<String, String>();
	public static ArrayList<JTextField> textFilds = new ArrayList<JTextField>();
	public static ArrayList<String> nameFilds = new ArrayList<String>();

	public int process = -1;

	public Window(String execName, String execDescription,
			Map<String, String> parameters, String inputFile, String outputFile) {

		parametersValue.put("inputFile", inputFile);
		parametersValue.put("outputFile", outputFile);

		this.execName = execName;
		this.execDescription = execDescription;
		this.parameters = parameters;

		setTitle(execDescription);
		setSize(800, 500);
		setResizable(false);
		center(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public String getValue(String parameter) {
		if (parametersValue.containsKey(parameter))
			return parametersValue.get(parameter);

		return null;
	}

	public void printParametersValue() {
		for (Map.Entry<String, String> entry : parametersValue.entrySet())
			System.out.println("Parametru = " + entry.getKey() + ", Valoare = "
					+ entry.getValue());
	}

	public String getParamValue(String key) {

		for (Map.Entry<String, String> entry : parametersValue.entrySet()) {
			if (entry.getKey().equals(key))
				return entry.getValue();
		}
		return null;
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == continueButton) {
			process = 0;
			this.dispose();
		} else if (e.getSource() == processButton) {
			for (int i = 0; i < textFilds.size(); i++)
				parametersValue.put(nameFilds.get(i), textFilds.get(i)
						.getText());
			process = 1;
			this.dispose();
		}
	}

	public Map<String, String> getParametersValue() {
		return parametersValue;
	}

	public static void center(Window w) {
		Dimension ws = w.getSize();
		Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (ss.width - ws.width) / 2;
		int newY = (ss.height - ws.height) / 2;
		w.setLocation(newX, newY);
	}

	void drawWindow() {
		int index = 0, coordY;

		TitledBorder title;
		JLabel text = new JLabel();
		title = BorderFactory.createTitledBorder(execName);
		JPanel p = new JPanel();
		p.setBounds(265, 195, 240, 110);
		GridBagConstraints c = new GridBagConstraints();
		p.setLayout(new GridBagLayout());
		p.setBackground(bleu);
		p.setBorder(title);

		coordY = 2;

		for (Map.Entry<String, String> entry : parameters.entrySet()) {

			if (index > 1) {
				JTextField parameter = new JTextField(10);
				parameter.setText(null);

				text = new JLabel(entry.getKey() + ":");
				c.gridx = 0;
				c.gridy = coordY;
				p.add(text, c);

				c.gridx = 1;
				c.gridy = coordY;
				p.add(parameter, c);

				textFilds.add(parameter);
				nameFilds.add(entry.getKey());

				coordY++;
			}

			index++;

		}

		c.gridx = 0;
		c.gridy = coordY;
		p.add(processButton, c);

		c.gridx = 1;
		c.gridy = coordY;
		p.add(continueButton, c);

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();

		p1.setPreferredSize(new Dimension(800, 195));
		p2.setPreferredSize(new Dimension(280, 110));
		p3.setPreferredSize(new Dimension(280, 110));
		p4.setPreferredSize(new Dimension(800, 195));

		p4.add(copy);

		p1.setBackground(bleu);
		p2.setBackground(bleu);
		p3.setBackground(bleu);
		p4.setBackground(bleu);

		this.add(p1, BorderLayout.NORTH);
		this.add(p2, BorderLayout.WEST);
		this.add(p, BorderLayout.CENTER);
		this.add(p3, BorderLayout.EAST);
		this.add(p4, BorderLayout.SOUTH);

		processButton.addActionListener(this);
		continueButton.addActionListener(this);

		this.pack();
	}
}