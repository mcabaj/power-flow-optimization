package network;

import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;



public class Chart {
	
	private ChartFrame obraz;
    private JFreeChart wykres;
    private DefaultCategoryDataset dane = new DefaultCategoryDataset();
    
    public Chart(ArrayList<Double> chartTab) {
    	for(int i = 10; i < chartTab.size(); i+=200) {
    		dane.addValue(chartTab.get(i), "Najlepszy", Integer.toString(i));
    	}
    	
        wykres = ChartFactory.createLineChart("Oceny osobnikow", "Generacja",
                "Ocena", dane, PlotOrientation.VERTICAL, true, true, false);
        
        obraz = new ChartFrame("Wykres algorytmu genetycznego", wykres);
        obraz.setLocation(5, 5);
        obraz.setVisible(true);

        obraz.pack();
        obraz.repaint();
    }

}
