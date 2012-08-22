package application;

import java.util.ArrayList;
import java.util.Random;

import network.Chart;
import network.Line;
import network.Node;



public class GeneticAlgorithm {
	
	private ArrayList<ArrayList<Node>> nodesList; 
	private ArrayList<Line> linesList;
	private int[] nodesN;
	private int[] dPower;
	private int popSize;
	private int iterations;
	private int time;
	private int mutation;	
	private int stopCondition;
	
	private ArrayList<Double> chartTab;
	private double[][] population;
	private double[] fitness;
	private double[] fit;
	private double[] fit2;
	private int individualSize;
	
	private SelectionMethod selectionMethod;
	
	private Chart chart;
	
	private Random random;
	
	public GeneticAlgorithm(ArrayList<ArrayList<Node>> nodesList, ArrayList<Line> linesList, int[] nodesN, int[] dPower, int popSize, int stop, double stopD, int mutation, int stopCondition, int method) {
		this.nodesList = nodesList;
		this.linesList = linesList;
		this.nodesN = nodesN;
		this.dPower = dPower;
		this.popSize = popSize;
		this.mutation = mutation;
		
		this.iterations = stop;
		this.time = stop;
		this.stopCondition = stopCondition;
		
		chartTab = new ArrayList<Double>();
		population = new double[popSize][];
		fitness = new double[popSize];
		fit = new double[popSize];
		fit2 = new double[popSize];
		individualSize = this.linesList.size();

		random = new Random(System.currentTimeMillis());
		
		selectionMethod = new TournamentMethod(method);

	}
	
	public void start() {
		long start = System.currentTimeMillis();
		
		choosePopulation();
		
		if(stopCondition == 1) {
			for(int i = 0; i < iterations; i++) {
				etap();
				chartTab.add(fitness[findMax()]);
			}
		}
		
		if(stopCondition == 2) {
			while((System.currentTimeMillis()-start)/1000 < time) {
				etap();
				chartTab.add(fitness[findMax()]);
			}
		}
		
		chart = new Chart(chartTab);
		long end = System.currentTimeMillis();
		int best = findMax();
		System.out.println("Najlepszy osobnik : ");
		for(int i = 0; i < individualSize; i++) {
			System.out.println("Linia "+(i+1)+" : "+population[best][i]+" MW");
		}
		System.out.println("Czas trwania algorytmu : "+(end-start)/1000);
		System.out.println("Ocena najlepszego osobnika : "+fitness[best]+"     F1 : "+fit[best]+"     F2 : "+fit2[best]);
	}
	
	public void choosePopulation() {
		for(int i = 0; i < popSize; i++) {
			population[i] = new double[individualSize];
			for(int j = 0; j < individualSize; j++) {
				population[i][j] = random.nextInt(401)+100;  
			}
		}
	}
	
	public void etap() {
		fitness();
		selection();
		crossOver();
		mutation();
	}
	
	public void fitness() {
		for(int i = 0; i < popSize; i++) {
			double f1 = 0;
			double f2 = 0;
			int counter = 0;
			
			// LICZENIE F1
			for(int j = 0; j < individualSize; j++) {
				f1 += population[i][j]*population[i][j]*linesList.get(j).getFactor();
			}
			
			// LICZENIE F2
			for(int j = 2; j > -1; j--) {
				for(int k = 0; k < nodesList.get(j).size(); k++) {
					double sParents = 0;
					double sChildren = 0;
					for(int l = 0; l < nodesList.get(j).get(k).getParents().size(); l++) {
						sParents += population[i][nodesList.get(j).get(k).getParents().get(l).getID()-1];
					}
					if(j != 0) {
						for(int l = 0; l < nodesList.get(j).get(k).getChildren().size(); l++) {
							sChildren += population[i][nodesList.get(j).get(k).getChildren().get(l).getID()-1];
						}
					}
					else { 
						sChildren = dPower[k];
					}
					f2 += Math.pow((sParents*(((double)nodesN[counter++])/100)-sChildren), 2);
				}
			}
			fitness[i] = f1+f2;
			fit[i] = f1;
			fit2[i] = f2;
		}
	}
	
	
	public void selection() {
		population = selectionMethod.createNewPopulation(popSize, population, fitness);
	}

	
	public void crossOver() {
		double[][] newPopulation = new double[popSize][];
		for(int i = 0; i < popSize/2; i++) {
			newPopulation[2*i] = new double[individualSize];
			newPopulation[(2*i)+1] = new double[individualSize];
			int r = individualSize/2;
			for(int j = 0; j < r; j++) {
				newPopulation[2*i][j] = population[2*i][j];
				newPopulation[(2*i)+1][j] = population[(2*i)+1][j];
			}
			for(int j = r; j < individualSize; j++) {
				newPopulation[2*i][j] = population[(2*i)+1][j];
				newPopulation[(2*i)+1][j] = population[2*i][j];
			}
		}
		population = newPopulation;
	}
	
	
	
	public void mutation() {
		for(int i = 0; i < popSize; i++) {
			if(random.nextInt(100) < mutation ) {
				int r = random.nextInt(individualSize);
				double ran = 0.5*random.nextDouble()-0.25;
				population[i][r] += ran;
			}
		}
	}
	
	
	
	
	// FUNKCJE POMOCNICZE
	public int findMax() {
		double best = fitness[0];
		int result = 0;
		
		for(int i = 1; i < popSize; i++) {
			if(fitness[i] < best) {
				best = fitness[i];
				result = i;
			}
		}
	
		return result;
	}
}
