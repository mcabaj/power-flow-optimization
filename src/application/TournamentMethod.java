package application;

import java.util.Random;

public class TournamentMethod implements SelectionMethod {
	
	private double[] fitness; 
	private int size;
	
	public TournamentMethod(int size) {
		this.size = size;
	}
	
	public double[][] createNewPopulation(int popSize, double[][] population, double[] fitness) {
		Random random = new Random(System.currentTimeMillis());
		this.fitness = fitness;
		
		double[][] newPopulation = new double[popSize][];
		int[] r = new int[size];
		
		for(int i = 0; i < popSize; i++) {
			for(int j = 0; j < size; j++) {
				r[j] = random.nextInt(popSize);
			}
			newPopulation[i] = population[bestFrom(r)];
		}
		return newPopulation;
	}
	
	public int bestFrom(int[] tab) {
		double min = fitness[tab[0]];
		int minN = tab[0];
		for(int i = 1; i < tab.length; i++) {
			if(fitness[tab[i]] < min) {
				min = fitness[tab[i]];
				minN = tab[i];
			}
		}
		return minN;
	}
}
