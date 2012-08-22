package panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import network.Line;
import network.Node;


public class ModelInfo extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextArea txtA;
	private JScrollPane scrollPane;
	private JTextArea txtA2;
	private JScrollPane scrollPane2;
	private JTextArea txtA3;
	private JScrollPane scrollPane3;

	public ModelInfo() {
		setLayout(new FlowLayout());
        setBounds(10, 10, 235, 700);
        setBackground(new Color(197, 230, 230));
        
        txtA = new JTextArea(22, 19);
        scrollPane = new JScrollPane(txtA);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        txtA2 = new JTextArea(10, 19);
        scrollPane2 = new JScrollPane(txtA2);
        scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        txtA3 = new JTextArea(5, 19);
        scrollPane3 = new JScrollPane(txtA3);
        scrollPane3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        add(new JLabel("Informacjê o liniach przesy³owych :"));
        add(scrollPane, BorderLayout.CENTER);
        add(new JLabel("Sprawnoœci transformatorów :"));
        add(scrollPane2, BorderLayout.CENTER);
        add(new JLabel("Zapotrzebowanie na moc :"));
        add(scrollPane3, BorderLayout.CENTER);
	}

	public void fillInfo(ArrayList<Line> linesList) {
		String s = "";
		for(int i = 0; i < linesList.size(); i++) {
			s += " Linia["+linesList.get(i).getID()+"] : "+linesList.get(i).getLength()+"km , "+linesList.get(i).getTension()+"kV , "+linesList.get(i).getCrossSection()+"mm2"+"\n";
		}
		txtA.setText(s);
	}
	
	public void fillInfo2(int[] nodesN, ArrayList<ArrayList<Node>> nodesList) {
		String s = "";
		String str = "";
		int k = 0;
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
				str += " Sprawnoœæ transformatora "+s+(j+1)+" : "+nodesN[k++]+" %\n";
			}
		}
		txtA2.setText(str);
	}
	
	public void fillInfo3(int[] dPower) {
		String str = "";
		for(int i = 0; i < dPower.length; i++) {
			str += " Stacja dystrybucyjna D"+(i+1)+" : "+dPower[i]+" MW\n";
		}
		txtA3.setText(str);
	}
}
