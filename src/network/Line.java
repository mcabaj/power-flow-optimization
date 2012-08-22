package network;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import no.geosoft.cc.graphics.GObject;
import no.geosoft.cc.graphics.GPosition;
import no.geosoft.cc.graphics.GSegment;
import no.geosoft.cc.graphics.GStyle;
import no.geosoft.cc.graphics.GText;
 
public class Line extends GObject{
	
	private static final double cosinus = 0.95;
	private static final double ro = 28.2;
	
    private GSegment arrow;
    private Node topEnd;
    private Node bottomEnd;
    private int ID;
    private double tension;				// w kV
    private double length;				// w KM
    private double crossSection;		// w mm^2
    private double factor;
  
    public Line(Node tE, Node bE, int ID, int ten){
    	arrow = new GSegment();
    	addSegment (arrow);
    	
    	GStyle style = new GStyle();
    	style.setForegroundColor(Color.black);
    	style.setLineWidth(1);
    	setStyle (style);
    
    	topEnd = tE;
    	bottomEnd = bE;
    	this.ID = ID;
    	calculateTension(ten);
    	calculateLengthAndSection();
    	calculateFactor();
    } 
 
    public void draw(){
    	int[] arrowCoord = new int[] {topEnd.getX(), topEnd.getY(), bottomEnd.getX(), bottomEnd.getY()};
    	arrow.setGeometry(arrowCoord);
    	
    	Integer integer = new Integer(ID);
    	
    	GText text = new GText(integer.toString(), GPosition.DYNAMIC);
        GStyle textStyle = new GStyle();
        textStyle.setForegroundColor (Color.black);
        textStyle.setBackgroundColor (Color.white);
        textStyle.setFont (new Font ("Dialog", Font.BOLD, 16));
        text.setStyle (textStyle);
        arrow.setText (text);
    }
    
    public void calculateLengthAndSection() {
    	Random random = new Random();
    	if(tension == 750) {
    		length = random.nextInt(1001)+1000;
    		crossSection = random.nextInt(701)+900;
    	}
    	if(tension == 400) {
    		length = random.nextInt(701)+300;
    		crossSection = random.nextInt(401)+700;
    	}
    	if(tension == 220) {
    		length = random.nextInt(301)+50;
    		crossSection = random.nextInt(301)+500;
    	}
    }
    
    public void calculateTension(int ten) {
    	if(ten == 3) {
    		this.tension = 750;
    	}
    	if(ten == 2) {
    		this.tension = 400;
    	}
    	if(ten == 1) {
    		this.tension = 220;
    	}
    }
    
    public void calculateFactor() {
    	factor = (ro*length)/(crossSection*Math.pow(tension*cosinus, 2));
    }

	public int getID() {
		return ID;
	}

	public double getLength() {
		return length;
	}

	public double getCrossSection() {
		return crossSection;
	}
    
    public double getTension() {
    	return tension;
    }
    
    public double getFactor() {
    	return factor;
    }
}