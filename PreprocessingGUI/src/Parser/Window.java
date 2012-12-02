package Parser;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.*;

	
class Window extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private static JTextField inputFile = new JTextField(10);
	private static JTextField outputFile = new JTextField(10);

	private static JButton processButton = new JButton("<html><font color = 'red'>P</font>" +
			"rocess</html>");
	private static JButton continueButton = new JButton("<html><font color = 'red'>C</font>" +
			"ontinue</html>");
	private static Color bleu = new Color(94, 174, 255);;
	private static JLabel copy = new JLabel("<html><font size = '-2' color = 'red'>" +
			"<i>&copy; 2012 </i></font></html>");
	
	public static String execName = "";
	public String execDescription = "";
	public static Map<String, String> parameters;

	public Map<String, String> parametersValue = new HashMap<String, String>();
	public static ArrayList<JTextField> textFilds = new ArrayList<JTextField>();
	public static ArrayList<String> nameFilds = new ArrayList<String>();

	@SuppressWarnings("static-access")
	public Window(String execName, String execDescription,  
			Map<String, String> parameters){
		
		this.execName = execName;
		this.execDescription = execDescription;
		this.parameters = parameters;
		
		setTitle(execDescription);
		setSize(800, 500);
		setResizable(false);
		center(this);
		setVisible(true);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
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
	
	public void actionPerformed (ActionEvent e) {

		if (e.getSource() == continueButton){
			System.exit(0);
		}else if(e.getSource() == processButton){
			System.out.println("\n\nAm preluat parametrii");
			parametersValue.put("inputFile", inputFile.getText());
			parametersValue.put("outputFile", outputFile.getText());
			for (int i = 0; i < textFilds.size(); i++)
				parametersValue.put(nameFilds.get(i), textFilds.get(i).getText());
			
			printParametersValue();
			System.exit(0);
		}
	}
	
	public static void center (Window w) {
		Dimension ws = w.getSize(); 
		Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = ( ss.width - ws.width ) / 2;
		int newY = ( ss.height - ws.height ) / 2;
		w.setLocation( newX, newY );
	}
	
	static void drowWindow(Window f){
		int index = 0, coordY, contor = 0;
		
		processButton.removeActionListener(f);
		continueButton.removeActionListener(f);
		f.getContentPane().removeAll();
		TitledBorder title;
		JLabel text = new JLabel();
		title = BorderFactory.createTitledBorder(execName);
	    JPanel p = new JPanel();
	    p.setBounds(265, 195, 240, 110);
	    GridBagConstraints c = new GridBagConstraints();
	    p.setLayout(new GridBagLayout());
	    p.setBackground(bleu);
	    p.setBorder(title);
	    
	    text = new JLabel("Input File:");
		c.gridx = 0;
		c.gridy = 0;
		p.add(text, c);
		
//	    inputFile.setEditable(false);
	    c.gridx = 1;
		c.gridy = 0;
	    p.add(inputFile, c);
	    
	    
	    text = new JLabel("Output File:");
		c.gridx = 0;
		c.gridy = 1;
		p.add(text, c);
		
//	    outputFile.setEditable(false);
	    c.gridx = 1;
		c.gridy = 1;
	    p.add(outputFile, c);
	    
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
			    
			    contor++;
			    coordY++;
			}
			
			index ++;
	    
	    }
	    
	    c.gridx = 0;
		c.gridy = coordY;
	    processButton.addActionListener(f);
	    p.add(processButton, c);
	    
	    c.gridx = 1;
		c.gridy = coordY;
	    continueButton.addActionListener(f);
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
	    
	    f.add(p1, BorderLayout.NORTH);
	    f.add(p2, BorderLayout.WEST);
	    f.add(p, BorderLayout.CENTER);
	    f.add(p3, BorderLayout.EAST);
	    f.add(p4, BorderLayout.SOUTH);
	    
		f.pack ();
	}
}