package application;

import java.util.ArrayList;
import java.util.Random;

import network.Line;
import network.Node;



public class Model {  

	private GeneticAlgorithm geneticAlgorithm;
	private ArrayList<ArrayList<Node>> nodesList; 
	private ArrayList<Line> linesList;
	private int[] nID;
	private int lID;
	private int[] diff;
	public int[] nodesN;
	public int[] dPower;
	
	public Model(){
		clearVariables();
	}
	
	public void generateAlgorithm(int popSize, int stop,double stopD, int mutation, int stopCondition, int method) {
		geneticAlgorithm = new GeneticAlgorithm(nodesList, linesList, nodesN, dPower, popSize, stop, stopD, mutation, stopCondition, method);
		geneticAlgorithm.start();
	}
	
	public void generateModel(int[] nodes) {
		// CLEARING VARIABLES
		clearVariables();
		
		// HIGHER RANGE FOR SMALL GAPS
		for(int i = 0; i < 3; i++) {
			if(nodes[i] - nodes[i+1] == 1 || nodes[i] - nodes[i+1] == 2) {
				diff[i] = 200;
			}
		}
	    
	    // ADDING NODES
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < nodes[i]; j++) {
				nodesList.get(i).add(new Node(800/(nodes[i]+1)*(j+1), (3-i)*150+50, nID[i]++));
			}
		}
	    
	    // CORRECT CENTER
		int [] center = new int[4];
		int size;
		for(int i = 0; i < 4; i++) {
			size = nodesList.get(i).size();
			if(size % 2 == 0) {
				center[i] = nodesList.get(i).get((size/2)-1).getX() + (nodesList.get(i).get(size/2).getX() - nodesList.get(i).get((size/2)-1).getX())/2;
			}
			else {
		    	center[i] = nodesList.get(i).get(((size+1)/2)-1).getX();
		    }
		}		
	    
	    for(int i = 0; i < 3; i++) {
	    	for(int j = 0; j < nodesList.get(i).size(); j++) {
	    		nodesList.get(i).get(j).setX(nodesList.get(i).get(j).getX() - (center[i] - center[i+1]));
	    	}
	    }
	    
	    // ADDING LINES  
	    for(int i = 3; i > 0; i--) {
	    	for(int j = 0; j < nodesList.get(i).size(); j++) {
	    		int min = 800/(nodes[i]+1)*j - diff[i-1]/(nodes[i]+1);
	    		int max = 800/(nodes[i]+1)*(j+2) + diff[i-1]/(nodes[i]+1);
	    		for(int k = 0; k < nodesList.get(i-1).size(); k++) {
	    			if(nodesList.get(i-1).get(k).getX() > min-5 && nodesList.get(i-1).get(k).getX() < max+5) {
	    				Line tmp = new Line(nodesList.get(i).get(j), nodesList.get(i-1).get(k), lID++, i);
	    				linesList.add(tmp);
	    				nodesList.get(i).get(j).addChildren(tmp);
	    				nodesList.get(i-1).get(k).addParent(tmp);
	    			}
	    		}
	    	}
	    }
	    
	}
	
	public void clearVariables() {
		nodesList = new ArrayList<ArrayList<Node>>();
		for(int i = 0; i < 4; i++) {
			nodesList.add(new ArrayList<Node>());
		}
	    
	    linesList = new ArrayList<Line>();
	    diff = new int[3];
	    for(int i = 0; i < 3; i++) {
	    	diff[i] = 0;
	    }
	    
	    nID = new int[4];
	    for(int i = 0; i < 4; i++) {
	    	nID[i] = 1;
	    }
	    lID = 1;
	}
	
	public void createNodesN(int size) {
		nodesN = new int[size];
		for(int i = 0; i < size; i++) {
			nodesN[i] = 99;
		}
	}
	
	public void createDPower(int size) {
		Random random = new Random(System.currentTimeMillis());
		dPower = new int[size];
		for(int i = 0; i < size; i++) {
			dPower[i] = random.nextInt(901)+100;
		}
	}
	
	// SETTERS AND GETTERS
	public ArrayList<ArrayList<Node>> getNodesList() {
		return nodesList;
	}

	public ArrayList<Line> getLinesList() {
		return linesList;
	}	
}
