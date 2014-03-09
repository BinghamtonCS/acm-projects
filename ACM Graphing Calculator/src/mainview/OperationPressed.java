package mainview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class OperationPressed implements ActionListener{
	private static boolean readyForOp = false;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (readyForOp) {
			JButton src = (JButton)e.getSource();
			CalculatorFieldPanel.updateField(src.getText());
			CalculatorFieldPanel.setField();
			readyForOp = false;
		}
	}
	
	public static void flipReady() {
		if (readyForOp)
			readyForOp = false;
		else
			readyForOp = true;
	}
	
	public static boolean getReady() {
		return readyForOp;
	}

}
