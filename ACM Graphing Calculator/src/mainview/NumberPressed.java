package mainview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class NumberPressed implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton src = (JButton)e.getSource();
		CalculatorFieldPanel.updateField(src.getText());
		CalculatorFieldPanel.setField();
		
		if (!OperationPressed.getReady())
			OperationPressed.flipReady();
	}

}
