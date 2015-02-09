package mainview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MiscPressed implements ActionListener {
	
	private static int parCount = 0;

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton src = (JButton)e.getSource();

		if (src.getText().equals(".")) {
			CalculatorFieldPanel.updateField(src.getText());
			CalculatorFieldPanel.setField();
		}
		
		if (src.getText().equals("(-)")) {
			String currField = CalculatorFieldPanel.getNumberField();
			if((currField.equals("")) || !(currField.substring(0,1).equals("-"))) {
				CalculatorFieldPanel.clearField();
				CalculatorFieldPanel.updateField("-" + currField);
				CalculatorFieldPanel.setField();
			} else {
				CalculatorFieldPanel.clearField();
				CalculatorFieldPanel.updateField(currField.substring(1));
				CalculatorFieldPanel.setField();
			}
		}
		
		if (src.getText().equals("(")) {
			CalculatorFieldPanel.updateField("(");
			CalculatorFieldPanel.setField();
			parCount++;
		}
		
		if (src.getText().equals(")")) {
			if (parCount > 0) {
				CalculatorFieldPanel.updateField(")");
				CalculatorFieldPanel.setField();
				parCount--;
			}
		}
		
		if (src.getText().equals("C")) {
			CalculatorFieldPanel.clearField();
			CalculatorFieldPanel.setField();
			parCount = 0;
			if (OperationPressed.getReady())
				OperationPressed.flipReady();
		}
		
		if (src.getText().equals("=")) {
			/* Implement control here
			 * 	Use numberField to call method in control
			 */
		}
		
	}

}
