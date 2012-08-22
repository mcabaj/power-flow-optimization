package application;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import panels.AlgorithmParameters;
import panels.DistributionModel;
import panels.ModelGeneration;
import panels.ModelInfo;
import panels.ModelParameters;


public class View extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Model model;
	// PANELS
	private DistributionModel distributionModel;
	private ModelGeneration modelGeneration;
	private ModelParameters modelParameters; 
	private ModelInfo modelInfo; 
	private AlgorithmParameters algorithmParameters;
	
	public View(Model model) {
		this.model = model;
		
		// Window configuration
        setTitle("Algorytm genetyczny - rozp³yw mocy w liniach przesy³owych");
        setVisible(true);
        setSize(1270, 750);
        setLocation(5,5);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(new Color(197, 230, 230));
        
        // Creating panels
        distributionModel = new DistributionModel();
        modelGeneration = new ModelGeneration();
        modelParameters = new ModelParameters();
        modelInfo = new ModelInfo();
        algorithmParameters = new AlgorithmParameters();
        
        // Adding panels 
        getContentPane().add(distributionModel);
        getContentPane().add(modelGeneration);
        getContentPane().add(modelParameters);
        getContentPane().add(modelInfo);
        getContentPane().add(algorithmParameters);
        show();
        
	}
	
	public void addGenerateListener(ActionListener gal) {
		modelGeneration.generate.addActionListener(gal);
	}
	
	public void addRandomlyGenerateListener(ActionListener rgal) {
		modelGeneration.randomlyGenerate.addActionListener(rgal);
	}

	public void addListListener(ActionListener lal) {
		modelParameters.list.addActionListener(lal);
	}
	
	public void addSaveListener(ActionListener sal) {
		modelParameters.save.addActionListener(sal);
	}
	
	public void addList2Listener(ActionListener lal) {
		modelParameters.list2.addActionListener(lal);
	}
	
	public void addSave2Listener(ActionListener sal) {
		modelParameters.save2.addActionListener(sal);
	}
	
	public void addStartListener(ActionListener sal) {
		algorithmParameters.start.addActionListener(sal);
	}
	
	public void addStopConditionListener(ActionListener scl) {
		algorithmParameters.iterations.addActionListener(scl);
	}
	
	public void addStopCondition2Listener(ActionListener scl) {
		algorithmParameters.time.addActionListener(scl);
	}
	
	
	// SETTERS AND GETTERS
	public DistributionModel getDistributionModel() {
		return distributionModel;
	}

	public ModelGeneration getModelGeneration() {
		return modelGeneration;
	}

	public ModelParameters getModelParameters() {
		return modelParameters;
	}

	public ModelInfo getModelInfo() {
		return modelInfo;
	}

	public AlgorithmParameters getAlgorithmParameters() {
		return algorithmParameters;
	}
	
}
