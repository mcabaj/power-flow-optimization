package panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import network.Line;
import network.Node;
import no.geosoft.cc.graphics.GScene;
import no.geosoft.cc.graphics.GWindow;


public class DistributionModel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GWindow window;
	private GScene scene;
	
	public DistributionModel() {
		setLayout(new BorderLayout());
        setBounds(250, 10, 800, 545);
        setBackground(new Color(138, 210, 210));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
        
        window = new GWindow ();
        add (window.getCanvas());
        scene = new GScene (window);
	}

	public void drawModel(ArrayList<ArrayList<Node>> nodesList, ArrayList<Line> linesList) {
		scene.removeAll();
	    // ADDING LINES TO SCENE
	    for(int i = 0; i < linesList.size(); i++){
	    	scene.add(linesList.get(i));
	    }
	    
	    // ADDING CIRCLES TO SCENE
	    for(int i = 0; i < nodesList.size(); i++){
	    	for(int j = 0; j < nodesList.get(i).size(); j++){
	    		scene.add(nodesList.get(i).get(j));
	    	}
	    }
	    scene.redraw();
	    scene.refresh();
	}
	
}
