package panels;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ModelGeneration extends JPanel {

	private static final long serialVersionUID = 1L;
	public JLabel label1;
	public JLabel label2;
	public JLabel label3;
	public JLabel label4;
	public JTextField[] txt;
	public JButton generate;
	public JButton randomlyGenerate; 
	
	public ModelGeneration() {
		setLayout(new GridLayout(11, 1));
        setBounds(1055, 10, 200, 270);
        setBackground(new Color(138, 210, 210));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
        
        label1 = new JLabel("  [E]  Elektrownie [1-2] :");
        label2 = new JLabel("  [T]  400/220kV [2-4] :");
        label3 = new JLabel("  [T'] 220/110kV [3-7] :");
        label4 = new JLabel("  [D]  Stacje dystrybucyjne [4-10] :");
        txt = new JTextField[4];
        for(int i = 0; i < 4; i++) {
        	txt[i] = new JTextField();
        	txt[i].setHorizontalAlignment(JTextField.CENTER);
        }
        generate = new JButton("Generuj");
        randomlyGenerate = new JButton("Generuj losowo");
        
        add(label1);
        add(txt[0]);
        add(label2);
        add(txt[1]);
        add(label3);
        add(txt[2]);
        add(label4);
        add(txt[3]);
        add(new JLabel(""));
        add(generate);
        add(randomlyGenerate);
	}
	
	public boolean checkParameters() {
		for(int j = 0; j < 4; j++) {
			int dl = txt[j].getText().length();
			if (dl == 0)
				return false;
			
			char[] tz = new char[dl];
			tz = txt[j].getText().toCharArray();
			
			for (int i = 0; i < dl; i++)
			{
				if (!(Character.isDigit(tz[i])))
					return false;
			}

			if(j == 0) {
				if(Integer.valueOf(txt[j].getText()) < 1 || Integer.valueOf(txt[j].getText()) > 2)
            		return false;
			}
			if(j == 1) {
				if(Integer.valueOf(txt[j].getText()) < 2 || Integer.valueOf(txt[j].getText()) > 4)
            		return false;
			}
			if(j == 2) {
				if(Integer.valueOf(txt[j].getText()) < 3 || Integer.valueOf(txt[j].getText()) > 7)
            		return false;
			}
			if(j == 3) {
				if(Integer.valueOf(txt[j].getText()) < 4 || Integer.valueOf(txt[j].getText()) > 10)
            		return false;
			}

		}
		return true;
	}
}
