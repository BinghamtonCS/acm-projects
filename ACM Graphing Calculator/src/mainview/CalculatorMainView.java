package mainview;

import javax.swing.JFrame;

public class CalculatorMainView extends JFrame {

	public CalculatorMainView() {
		super("Calculator");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		CalculatorMainView windows = new CalculatorMainView();
		windows.setSize(640,480);
		windows.show();		
	}
	
}
