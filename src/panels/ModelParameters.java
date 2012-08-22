package panels;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import network.Node;


public class ModelParameters extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public JComboBox list;
	public JTextField txt;
	public JButton save;
	public JComboBox list2;
	public JTextField txt2;
	public JButton save2;

	public ModelParameters() {
		setLayout(new GridLayout(8,1));
        setBounds(1055, 285, 200, 270);
        setBackground(new Color(138, 210, 210));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
        
        txt = new JTextField();
        txt.setHorizontalAlignment(JTextField.CENTER);
        save = new JButton("Zapisz");
        list = new JComboBox();
        DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
        dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        list.setRenderer(dlcr);
        
        txt2 = new JTextField();
        txt2.setHorizontalAlignment(JTextField.CENTER);
        save2 = new JButton("Zapisz");
        list2 = new JComboBox();
        list2.setRenderer(dlcr);
        
        add(new JLabel("  Sprawnoœæ [95-99%]:"));
        add(list);
        add(txt);
        add(save);
        add(new JLabel("  Potrzebna moc [100-2000 MW]:"));
        add(list2);
        add(txt2);
        add(save2);
        
        setAllEditable(false);
	}
	
	public void createList(ArrayList<ArrayList<Node>> nodesList) {
		list.removeAllItems();
		String s = "";
		for(int i = 2; i > -1; i--) {
			for(int j = 0; j < nodesList.get(i).size(); j++) {
				if(i == 0) {
					s = "D";
				}
				if(i == 1) {
					s = "T'";
				}
				if(i == 2) {
					s = "T";
				}
				list.addItem(s+(j+1));
			}
		}
	}
	
	public void createList2(int size) {
		list2.removeAllItems();
		for(int i = 0; i < size; i++) {
			list2.addItem("D"+(i+1));
		}
	}
	
	public boolean checkTxt1() {
		int dl = txt.getText().length();
		if (dl == 0)
			return false;
			
		char[] tz = new char[dl];
		tz = txt.getText().toCharArray();
			
		for (int i = 0; i < dl; i++)
		{
			if (!(Character.isDigit(tz[i])))
				return false;
		}

		if(Integer.valueOf(txt.getText()) < 90 || Integer.valueOf(txt.getText()) > 99)
            return false;
		return true;
	}
	
	public boolean checkTxt2() {
		int dl = txt2.getText().length();
		if (dl == 0)
			return false;
			
		char[] tz = new char[dl];
		tz = txt2.getText().toCharArray();
			
		for (int i = 0; i < dl; i++)
		{
			if (!(Character.isDigit(tz[i])))
				return false;
		}

		if(Integer.valueOf(txt2.getText()) < 100 || Integer.valueOf(txt2.getText()) > 2000)
            return false;
		return true;
	}
	
	public void setAllEditable(boolean op) {
		list.setEnabled(op);
		list2.setEnabled(op);
		txt.setEditable(op);
		txt2.setEditable(op);
		save.setEnabled(op);
		save2.setEnabled(op);
	}
}
