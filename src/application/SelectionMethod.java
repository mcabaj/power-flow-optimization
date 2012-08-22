package application;

public interface SelectionMethod {
	double[][] createNewPopulation(int popSize, double[][] population, double[] fitness);
}
