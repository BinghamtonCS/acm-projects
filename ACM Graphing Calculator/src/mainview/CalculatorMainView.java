package mainview;

import java.awt.Container;

import javax.swing.JFrame;

public class CalculatorMainView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CalculatorMainView() {
		super("Binghamton ACM Calculator");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		Container c = getContentPane();

		//JPanel for result area
		@SuppressWarnings("unused")
		CalculatorFieldPanel cfp = new CalculatorFieldPanel(c);
		
		//JPanel for button area
		@SuppressWarnings("unused")
		CalculatorButtonPanel cbp = new CalculatorButtonPanel(c);
		
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		CalculatorMainView windows = new CalculatorMainView();
		windows.setSize(480,500);
		windows.show();
		
	}
	
}