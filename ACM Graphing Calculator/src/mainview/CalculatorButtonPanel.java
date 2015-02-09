package mainview;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CalculatorButtonPanel extends JFrame {

	private JButton buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven,
	buttonEight, buttonNine, buttonSin, buttonCos, buttonTan, buttonDivide, buttonMinus, buttonMultiply,
	buttonPlus, buttonZero, buttonDecimal, buttonNeg, buttonEquals, buttonParOpen, buttonParClose, buttonClear;
	private JPanel buttonArea;

	public CalculatorButtonPanel(Container c) {
		
		buttonArea = new JPanel();
		c.add(buttonArea,BorderLayout.CENTER);
		
		createButtons();
	}
	
	private void createButtons() {
		//Allows for rows of buttons to be placed on layout
		buttonArea.setLayout(new BoxLayout(buttonArea, BoxLayout.Y_AXIS));
		
		//Zero row
		JPanel row0 = new JPanel();
		
		buttonParOpen = new JButton("(");
		buttonParOpen.setPreferredSize(new Dimension(100, 50));
		row0.add(buttonParOpen);
		buttonParOpen.addActionListener(new MiscPressed());
		
		buttonParClose = new JButton(")");
		buttonParClose.setPreferredSize(new Dimension(100, 50));
		row0.add(buttonParClose);
		buttonParClose.addActionListener(new MiscPressed());
		
		buttonClear = new JButton("C");
		buttonClear.setPreferredSize(new Dimension(100,50));
		row0.add(buttonClear);
		buttonClear.addActionListener(new MiscPressed());
		
		buttonArea.add(row0);
		
		//First row of buttons
		JPanel row1 = new JPanel();
		
		buttonSin = new JButton("sin");
		buttonSin.setPreferredSize(new Dimension(100, 50));
		row1.add(buttonSin);
		buttonSin.addActionListener(new TrigPressed());
		
		buttonCos = new JButton("cos");
		buttonCos.setPreferredSize(new Dimension(100, 50));
		row1.add(buttonCos);
		buttonCos.addActionListener(new TrigPressed());
		
		buttonTan = new JButton("tan");
		buttonTan.setPreferredSize(new Dimension(100, 50));
		row1.add(buttonTan);
		buttonTan.addActionListener(new TrigPressed());
		
		buttonDivide = new JButton("/");
		buttonDivide.setPreferredSize(new Dimension(100, 50));
		row1.add(buttonDivide);
		buttonDivide.addActionListener(new OperationPressed());
		
		buttonArea.add(row1);
		
		//Second row of buttons
		JPanel row2 = new JPanel();
		
		buttonSeven = new JButton("7");
		buttonSeven.setPreferredSize(new Dimension(100, 50));
		row2.add(buttonSeven);
		buttonSeven.addActionListener(new NumberPressed());
		
		buttonEight = new JButton("8");
		buttonEight.setPreferredSize(new Dimension(100, 50));
		row2.add(buttonEight);
		buttonEight.addActionListener(new NumberPressed());
		
		buttonNine = new JButton("9");
		buttonNine.setPreferredSize(new Dimension(100, 50));
		row2.add(buttonNine);
		buttonNine.addActionListener(new NumberPressed());
		
		buttonMultiply = new JButton("*");
		buttonMultiply.setPreferredSize(new Dimension(100, 50));
		row2.add(buttonMultiply);
		buttonMultiply.addActionListener(new OperationPressed());
		
		buttonArea.add(row2);
		
		//Third row of buttons
		JPanel row3 = new JPanel();
		
		buttonFour = new JButton("4");
		buttonFour.setPreferredSize(new Dimension(100, 50));
		row3.add(buttonFour);
		buttonFour.addActionListener(new NumberPressed());
		
		buttonFive = new JButton("5");
		buttonFive.setPreferredSize(new Dimension(100, 50));
		row3.add(buttonFive);
		buttonFive.addActionListener(new NumberPressed());
		
		buttonSix = new JButton("6");
		buttonSix.setPreferredSize(new Dimension(100, 50));
		row3.add(buttonSix);
		buttonSix.addActionListener(new NumberPressed());
		
		buttonMinus = new JButton("-");
		buttonMinus.setPreferredSize(new Dimension(100, 50));
		row3.add(buttonMinus);
		buttonMinus.addActionListener(new OperationPressed());
		
		buttonArea.add(row3);
		
		//Fourth row of buttons
		JPanel row4 = new JPanel();
		
		buttonOne = new JButton("1");
		buttonOne.setPreferredSize(new Dimension(100, 50));
		row4.add(buttonOne);
		buttonOne.addActionListener(new NumberPressed());
		
		buttonTwo = new JButton("2");
		buttonTwo.setPreferredSize(new Dimension(100, 50));
		row4.add(buttonTwo);
		buttonTwo.addActionListener(new NumberPressed());
		
		buttonThree = new JButton("3");
		buttonThree.setPreferredSize(new Dimension(100, 50));
		row4.add(buttonThree);
		buttonThree.addActionListener(new NumberPressed());
		
		buttonPlus = new JButton("+");
		buttonPlus.setPreferredSize(new Dimension(100, 50));
		row4.add(buttonPlus);
		buttonPlus.addActionListener(new OperationPressed());
		
		buttonArea.add(row4);
		
		//Fifth row of buttons
		JPanel row5 = new JPanel();
		
		buttonZero = new JButton("0");
		buttonZero.setPreferredSize(new Dimension(100, 50));
		row5.add(buttonZero);
		buttonZero.addActionListener(new NumberPressed());
		
		buttonDecimal = new JButton(".");
		buttonDecimal.setPreferredSize(new Dimension(100, 50));
		row5.add(buttonDecimal);
		buttonDecimal.addActionListener(new MiscPressed());
		
		buttonNeg = new JButton("(-)");
		buttonNeg.setPreferredSize(new Dimension(100, 50));
		row5.add(buttonNeg);
		buttonNeg.addActionListener(new MiscPressed());
		
		buttonEquals = new JButton("=");
		buttonEquals.setPreferredSize(new Dimension(100, 50));
		row5.add(buttonEquals);
		buttonEquals.addActionListener(new MiscPressed());
		
		buttonArea.add(row5);
	}
	
}

