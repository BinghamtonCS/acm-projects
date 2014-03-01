package mainview;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class CalculatorMainView extends JFrame implements ActionListener {
	
	private String numberField = "0.0";
	private JLabel field = new JLabel("0.0");
	private JButton buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven,
		buttonEight, buttonNine, buttonSin, buttonCos, buttonTan, buttonDivide, buttonMinus, buttonMultiply,
		buttonPlus, buttonZero, buttonDecimal, buttonNeg, buttonEquals, buttonParOpen, buttonParClose, buttonClear;
	private JPanel resultArea, buttonArea;
	
	public CalculatorMainView() {
		super("Calculator");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		Container c = getContentPane();

		//JPanel for result area
		resultArea = new JPanel();
		c.add(resultArea,BorderLayout.NORTH);
		
		//JPanel for button area
		buttonArea = new JPanel();
		c.add(buttonArea,BorderLayout.CENTER);
		
		createField();
		createButtons();
	}
	
	private void updateField() {
		field.setText(numberField);
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		CalculatorMainView windows = new CalculatorMainView();
		windows.setSize(480,500);
		windows.show();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (numberField == "0.0") {
			numberField = "";
		}
		
		JButton src = (JButton)e.getSource();
		
		if (src == buttonOne) {
			numberField += "1";
		}
		
		if (src == buttonTwo) {
			numberField += "2";
		}
		
		if (src == buttonThree) {
			numberField += "3";
		}
		
		if (src == buttonFour) {
			numberField += "4";
		}
		
		if (src == buttonFive) {
			numberField += "5";
		}
		
		if (src == buttonSix) {
			numberField += "6";
		}
		
		if (src == buttonSeven) {
			numberField += "7";
		}
		
		if (src == buttonEight) {
			numberField += "8";
		}
		
		if (src == buttonNine) {
			numberField += "9";
		}
		
		if (src == buttonZero) {
			numberField += "0";
		}
		
		if (src == buttonDecimal) {
			numberField += ".";
		}
		
		if (src == buttonDivide) {
			numberField += "/";
		}
		
		if (src == buttonMultiply) {
			numberField += "*";
		}
		
		if (src == buttonMinus) {
			numberField += "-";
		}
		
		if (src == buttonPlus) {
			numberField += "+";
		}
		
		if (src == buttonNeg) {
			if((numberField == "") || !(numberField.substring(0,1).equals("-")))
				numberField = "-" + numberField;
			else
				numberField = numberField.substring(1);
		}
		
		if (src == buttonParOpen) {
			numberField += "(";
		}
		
		if (src == buttonParClose) {
			numberField += ")";
		}
		
		if (src == buttonClear) {
			numberField = "0.0";
		}
		
		if (src == buttonSin) {
			numberField += "sin(";
		}
		
		if (src == buttonCos) {
			numberField += "cos(";
		}
		
		if (src == buttonTan) {
			numberField += "tan(";
		}
		
		if (src == buttonEquals) {
			/* Implement control here
			 * 	Use numberField to call method in control
			 */
		}
		
		updateField();
		
	}
	
	private void createField () {
		//Creates label for entered buttons
		field.setHorizontalAlignment(SwingConstants.RIGHT);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        field.setBorder(border);
        field.setPreferredSize(new Dimension(400, 100));
		
		resultArea.add(field);
	}
	
	private void createButtons() {
		//Allows for rows of buttons to be placed on layout
		buttonArea.setLayout(new BoxLayout(buttonArea, BoxLayout.Y_AXIS));
		
		//Zero row
		JPanel row0 = new JPanel();
		
		buttonParOpen = new JButton("(");
		buttonParOpen.setPreferredSize(new Dimension(100, 50));
		row0.add(buttonParOpen);
		buttonParOpen.addActionListener(this);
		
		buttonParClose = new JButton(")");
		buttonParClose.setPreferredSize(new Dimension(100, 50));
		row0.add(buttonParClose);
		buttonParClose.addActionListener(this);
		
		buttonClear = new JButton("C");
		buttonClear.setPreferredSize(new Dimension(100,50));
		row0.add(buttonClear);
		buttonClear.addActionListener(this);
		
		buttonArea.add(row0);
		
		//First row of buttons
		JPanel row1 = new JPanel();
		
		buttonSin = new JButton("sin");
		buttonSin.setPreferredSize(new Dimension(100, 50));
		row1.add(buttonSin);
		buttonSin.addActionListener(this);
		
		buttonCos = new JButton("cos");
		buttonCos.setPreferredSize(new Dimension(100, 50));
		row1.add(buttonCos);
		buttonCos.addActionListener(this);
		
		buttonTan = new JButton("tan");
		buttonTan.setPreferredSize(new Dimension(100, 50));
		row1.add(buttonTan);
		buttonTan.addActionListener(this);
		
		buttonDivide = new JButton("/");
		buttonDivide.setPreferredSize(new Dimension(100, 50));
		row1.add(buttonDivide);
		buttonDivide.addActionListener(this);
		
		buttonArea.add(row1);
		
		//Second row of buttons
		JPanel row2 = new JPanel();
		
		buttonSeven = new JButton("7");
		buttonSeven.setPreferredSize(new Dimension(100, 50));
		row2.add(buttonSeven);
		buttonSeven.addActionListener(this);
		
		buttonEight = new JButton("8");
		buttonEight.setPreferredSize(new Dimension(100, 50));
		row2.add(buttonEight);
		buttonEight.addActionListener(this);
		
		buttonNine = new JButton("9");
		buttonNine.setPreferredSize(new Dimension(100, 50));
		row2.add(buttonNine);
		buttonNine.addActionListener(this);
		
		buttonMultiply = new JButton("x");
		buttonMultiply.setPreferredSize(new Dimension(100, 50));
		row2.add(buttonMultiply);
		buttonMultiply.addActionListener(this);
		
		buttonArea.add(row2);
		
		//Third row of buttons
		JPanel row3 = new JPanel();
		
		buttonFour = new JButton("4");
		buttonFour.setPreferredSize(new Dimension(100, 50));
		row3.add(buttonFour);
		buttonFour.addActionListener(this);
		
		buttonFive = new JButton("5");
		buttonFive.setPreferredSize(new Dimension(100, 50));
		row3.add(buttonFive);
		buttonFive.addActionListener(this);
		
		buttonSix = new JButton("6");
		buttonSix.setPreferredSize(new Dimension(100, 50));
		row3.add(buttonSix);
		buttonSix.addActionListener(this);
		
		buttonMinus = new JButton("-");
		buttonMinus.setPreferredSize(new Dimension(100, 50));
		row3.add(buttonMinus);
		buttonMinus.addActionListener(this);
		
		buttonArea.add(row3);
		
		//Fourth row of buttons
		JPanel row4 = new JPanel();
		
		buttonOne = new JButton("1");
		buttonOne.setPreferredSize(new Dimension(100, 50));
		row4.add(buttonOne);
		buttonOne.addActionListener(this);
		
		buttonTwo = new JButton("2");
		buttonTwo.setPreferredSize(new Dimension(100, 50));
		row4.add(buttonTwo);
		buttonTwo.addActionListener(this);
		
		buttonThree = new JButton("3");
		buttonThree.setPreferredSize(new Dimension(100, 50));
		row4.add(buttonThree);
		buttonThree.addActionListener(this);
		
		buttonPlus = new JButton("+");
		buttonPlus.setPreferredSize(new Dimension(100, 50));
		row4.add(buttonPlus);
		buttonPlus.addActionListener(this);
		
		buttonArea.add(row4);
		
		//Fifth row of buttons
		JPanel row5 = new JPanel();
		
		buttonZero = new JButton("0");
		buttonZero.setPreferredSize(new Dimension(100, 50));
		row5.add(buttonZero);
		buttonZero.addActionListener(this);
		
		buttonDecimal = new JButton(".");
		buttonDecimal.setPreferredSize(new Dimension(100, 50));
		row5.add(buttonDecimal);
		buttonDecimal.addActionListener(this);
		
		buttonNeg = new JButton("(-)");
		buttonNeg.setPreferredSize(new Dimension(100, 50));
		row5.add(buttonNeg);
		buttonNeg.addActionListener(this);
		
		buttonEquals = new JButton("=");
		buttonEquals.setPreferredSize(new Dimension(100, 50));
		row5.add(buttonEquals);
		buttonEquals.addActionListener(this);
		
		buttonArea.add(row5);
	}
	
}