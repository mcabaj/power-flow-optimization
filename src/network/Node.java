package network;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import no.geosoft.cc.geometry.Geometry;
import no.geosoft.cc.graphics.GObject;
import no.geosoft.cc.graphics.GPosition;
import no.geosoft.cc.graphics.GSegment;
import no.geosoft.cc.graphics.GStyle;
import no.geosoft.cc.graphics.GText;
 
public class Node extends GObject{
  
	private GSegment circle;
	private int x;
	private int y;
	private int ID;
	private String c;
	private ArrayList<Line> parents;
	private ArrayList<Line> children;
  
	public Node(int x, int y, int ID){
		circle = new GSegment();
		addSegment (circle);
    
		GStyle style = new GStyle();
		style.setBackgroundColor (Color.red);
		setStyle (style);  
		
		this.x = x;
		this.y = y;
		this.ID = ID;
		parents = new ArrayList<Line>();
		children = new ArrayList<Line>();
		setC();
	}
 
	public void draw(){
		circle.setGeometry (Geometry.createCircle(x,y, 20));
		
		Integer integer = new Integer(ID);
    	
    	GText text = new GText(c+integer.toString(), GPosition.LEFT);
        GStyle textStyle = new GStyle();
        textStyle.setForegroundColor (Color.black);
        textStyle.setBackgroundColor (null);
        textStyle.setFont (new Font ("Dialog", Font.BOLD, 16));
        text.setStyle (textStyle);
        circle.setText (text);
        
 	}
	
	public void addParent(Line parent) {
		parents.add(parent);
	}
	
	public void addChildren(Line child) {
		children.add(child);
	}
	
	public void setC() {
		if(y == 50) {
			c = "E";
		}
		if(y == 200) {
			c = "T";
		}
		if(y == 350) {
			c = "T'";
		}
		if(y == 500) {
			c = "D";
		}
	}
	
	// SETTERS AND GETTERS
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ArrayList<Line> getParents() {
		return parents;
	}

	public ArrayList<Line> getChildren() {
		return children;
	}
	
	
}