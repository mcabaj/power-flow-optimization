package application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JOptionPane;

public class Controller {
	
	private Model model;
	private View view;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		
		view.addGenerateListener(new GenerateListener());
		view.addRandomlyGenerateListener(new RandomlyGenerateListener());
		view.addListListener(new ListListener());
		view.addSaveListener(new SaveListener());
		view.addList2Listener(new List2Listener());
		view.addSave2Listener(new Save2Listener());
		view.addStartListener(new StartListener());
		view.addStopConditionListener(new StopConditionListener());
		view.addStopCondition2Listener(new StopCondition2Listener());
	}
	
	class GenerateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(view.getModelGeneration().checkParameters()) {
				int[] nodes = new int[4];
				nodes[3] = Integer.parseInt(view.getModelGeneration().txt[0].getText());
				nodes[2] = Integer.parseInt(view.getModelGeneration().txt[1].getText()); 
				nodes[1] = Integer.parseInt(view.getModelGeneration().txt[2].getText()); 
				nodes[0] = Integer.parseInt(view.getModelGeneration().txt[3].getText()); 
				
				model.generateModel(nodes);
				view.getDistributionModel().drawModel(model.getNodesList(), model.getLinesList());
				view.getModelInfo().fillInfo(model.getLinesList());
				
				model.createNodesN(nodes[0]+nodes[1]+nodes[2]+nodes[3]);
				view.getModelParameters().createList(model.getNodesList());
				view.getModelParameters().txt.setText("99");
				view.getModelInfo().fillInfo2(model.nodesN, model.getNodesList());
				
				model.createDPower(nodes[0]);
				view.getModelParameters().createList2(nodes[0]);
				view.getModelParameters().txt2.setText(Integer.toString(model.dPower[0]));
				view.getModelInfo().fillInfo3(model.dPower);
				
				view.getModelParameters().setAllEditable(true);
				view.getAlgorithmParameters().start.setEnabled(true);
			}
			else {
				JOptionPane.showMessageDialog(view, "èle wype≥nione pola !", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	class RandomlyGenerateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Random random = new Random(System.currentTimeMillis());
			int[] nodes = new int[4];
			nodes[3] = random.nextInt(2)+1;
			nodes[2] = random.nextInt(3)+2;
			nodes[1] = random.nextInt(5)+3;
			nodes[0] = random.nextInt(7)+4;
			
			model.generateModel(nodes);
			view.getDistributionModel().drawModel(model.getNodesList(), model.getLinesList());	
			view.getModelInfo().fillInfo(model.getLinesList());
			
			model.createNodesN(nodes[0]+nodes[1]+nodes[2]+nodes[3]);
			view.getModelParameters().createList(model.getNodesList());
			view.getModelParameters().txt.setText("99");
			view.getModelInfo().fillInfo2(model.nodesN, model.getNodesList());
			
			model.createDPower(nodes[0]);
			view.getModelParameters().createList2(nodes[0]);
			view.getModelParameters().txt2.setText(Integer.toString(model.dPower[0]));
			view.getModelInfo().fillInfo3(model.dPower);
			
			view.getModelParameters().setAllEditable(true);
			view.getAlgorithmParameters().start.setEnabled(true);
		}
	}
	
	class ListListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(view.getModelParameters().list.getSelectedIndex() != -1)
				view.getModelParameters().txt.setText(Integer.toString(model.nodesN[view.getModelParameters().list.getSelectedIndex()]));
		}
	}
	
	class SaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(view.getModelParameters().checkTxt1()) {
				model.nodesN[view.getModelParameters().list.getSelectedIndex()] = Integer.parseInt(view.getModelParameters().txt.getText());
				view.getModelInfo().fillInfo2(model.nodesN, model.getNodesList());
			}
			else {
				JOptionPane.showMessageDialog(view, "SprawnoúÊ transformatora musi siÍ mieúciÊ w przedziale 90-99% !", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	class List2Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(view.getModelParameters().list2.getSelectedIndex() != -1)
				view.getModelParameters().txt2.setText(Integer.toString(model.dPower[view.getModelParameters().list2.getSelectedIndex()]));
		}
	}
	
	class Save2Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(view.getModelParameters().checkTxt2()) {
				model.dPower[view.getModelParameters().list2.getSelectedIndex()] = Integer.parseInt(view.getModelParameters().txt2.getText());
				view.getModelInfo().fillInfo3(model.dPower);
			}
			else {
				JOptionPane.showMessageDialog(view, "Zapotrzebowanie na moc musi siÍ mieúciÊ w przedziale 100-2000 MW !", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	class StartListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(view.getAlgorithmParameters().checkParameters()) {
				int selected = 1;
				int stopCondition = 1;
				int stop = 0;
				double stopD = 0;
				if(view.getAlgorithmParameters().two.isSelected() == true) {
					selected = 2;
				}
				if(view.getAlgorithmParameters().three.isSelected() == true) {
					selected = 3;
				}
				if(view.getAlgorithmParameters().four.isSelected() == true) {
					selected = 4;
				}
				if(view.getAlgorithmParameters().five.isSelected() == true) {
					selected = 5;
				}
				if(view.getAlgorithmParameters().iterations.isSelected() == true) {
					stopCondition = 1;
					stop = Integer.parseInt(view.getAlgorithmParameters().nIter.getText());
				}
				if(view.getAlgorithmParameters().time.isSelected() == true) {
					stopCondition = 2;
					stop = Integer.parseInt(view.getAlgorithmParameters().nTime.getText());
				}
				model.generateAlgorithm(Integer.parseInt(view.getAlgorithmParameters().popSize.getText()), stop , stopD, Integer.parseInt(view.getAlgorithmParameters().mutation.getText()), stopCondition, selected);
			}
			else {
				JOptionPane.showMessageDialog(view, "èle wype≥nione pola !", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	class StopConditionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.getAlgorithmParameters().nIter.setEditable(true);
			view.getAlgorithmParameters().nTime.setEditable(false);
		}
	}
	
	class StopCondition2Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.getAlgorithmParameters().nIter.setEditable(false);
			view.getAlgorithmParameters().nTime.setEditable(true);
		}
	}

}
