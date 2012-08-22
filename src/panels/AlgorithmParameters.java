package panels;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class AlgorithmParameters extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public JButton start;
	public JTextField popSize;
	public JTextField mutation;
	public JTextField nIter;
	public JTextField nTime;
	public JLabel pS;
	public JLabel stop;
	public JLabel mut;
	public JLabel method;
	public JRadioButton two;
    public JRadioButton three;
    public JRadioButton four;
    public JRadioButton five;
    public ButtonGroup group;
    public JRadioButton iterations;
    public JRadioButton time;
    public ButtonGroup group2;
	
	public AlgorithmParameters() {
		setLayout(new GridLayout(3,5));
        setBounds(250, 560 , 1005, 150);
        setBackground(new Color(138, 210, 210));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
        
        start = new JButton("Start");
        start.setEnabled(false);
        
        popSize = new JTextField();
        popSize.setText("100");
        popSize.setHorizontalAlignment(JTextField.CENTER);
        
        nIter = new JTextField();
        nIter.setHorizontalAlignment(JTextField.CENTER);
        nIter.setText("100000");
        
        nTime = new JTextField();
        nTime.setHorizontalAlignment(JTextField.CENTER);
        nTime.setText("10");
        nTime.setEditable(false);
        
        mutation = new JTextField();
        mutation.setHorizontalAlignment(JTextField.CENTER);
        mutation.setText("10");
        
        pS = new JLabel("Populacja :");
        pS.setHorizontalAlignment(JLabel.CENTER);
        stop= new JLabel("Warunek stopu :");
        stop.setHorizontalAlignment(JLabel.CENTER);
        mut = new JLabel("Szansa mutacji [%]:");
        mut.setHorizontalAlignment(JLabel.CENTER);
        method = new JLabel("Rozmiar turnieju : ");
        method.setHorizontalAlignment(JLabel.CENTER);
        
        two = new JRadioButton("2");
        two.setSelected(true);
        two.setBackground(new Color(138, 210, 210));
        two.setHorizontalAlignment(JRadioButton.CENTER);
        three = new JRadioButton("3");
        three.setSelected(false);
        three.setBackground(new Color(138, 210, 210));
        three.setHorizontalAlignment(JRadioButton.CENTER);
        four = new JRadioButton("4");
        four.setSelected(false);
        four.setBackground(new Color(138, 210, 210));
        four.setHorizontalAlignment(JRadioButton.CENTER);
        five = new JRadioButton("5");
        five.setSelected(false);
        five.setBackground(new Color(138, 210, 210));
        five.setHorizontalAlignment(JRadioButton.CENTER);


        iterations = new JRadioButton("Iloœæ iteracji");
        iterations.setSelected(true);
        iterations.setBackground(new Color(138, 210, 210));
        iterations.setHorizontalAlignment(JRadioButton.CENTER);
        time = new JRadioButton("Czas [s]");
        time.setSelected(false);
        time.setBackground(new Color(138, 210, 210));
        time.setHorizontalAlignment(JRadioButton.CENTER);
        
        group = new ButtonGroup();
        group.add(two);
        group.add(three);
        group.add(four);
        group.add(five);
        
        group2 = new ButtonGroup();
        group2.add(iterations);
        group2.add(time);
        
        add(method);
        add(two);
        add(three);
        add(four);
        add(five);
        
        add(stop);
        add(iterations);
        add(nIter);
        add(time);
        add(nTime);
        
        add(pS);
        add(popSize);
        add(mut);
        add(mutation);
        add(start);
        
	}
	
	public boolean checkParameters() {
		int dl = nIter.getText().length();
		if (dl == 0)
			return false;
		char[] tz = new char[dl];
		tz = nIter.getText().toCharArray();
		for (int i = 0; i < dl; i++)
		{
			if (!(Character.isDigit(tz[i])))
				return false;
		}
		
		dl = nTime.getText().length();
		if (dl == 0)
			return false;
		tz = new char[dl];
		tz = nTime.getText().toCharArray();
		for (int i = 0; i < dl; i++)
		{
			if (!(Character.isDigit(tz[i])))
				return false;
		}
		
		dl = popSize.getText().length();
		if (dl == 0)
			return false;
		tz = new char[dl];
		tz = popSize.getText().toCharArray();
		for (int i = 0; i < dl; i++)
		{
			if (!(Character.isDigit(tz[i])))
				return false;
		}
		
		dl = mutation.getText().length();
		if (dl == 0)
			return false;
		tz = new char[dl];
		tz = mutation.getText().toCharArray();
		for (int i = 0; i < dl; i++)
		{
			if (!(Character.isDigit(tz[i])))
				return false;
		}
		
		if(Integer.valueOf(nIter.getText()) < 1)
    		return false;
		if(Integer.valueOf(nTime.getText()) < 1)
    		return false;
		if(Integer.valueOf(popSize.getText()) < 2)
    		return false;
		if(Integer.valueOf(mutation.getText()) < 0 || Integer.valueOf(mutation.getText()) > 100)
    		return false;


		return true;
	}

}
