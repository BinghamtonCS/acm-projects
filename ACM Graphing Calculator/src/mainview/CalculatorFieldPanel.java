package mainview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class CalculatorFieldPanel extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JLabel field = new JLabel("");
	private JPanel resultArea;
	private static String numberField = "";

	public CalculatorFieldPanel(Container c) {
		
		resultArea = new JPanel();
		c.add(resultArea,BorderLayout.NORTH);
		
		createField();
		
	}
	
	static void updateField(String more) {
		numberField += more;
	}
	
	static void setField() {
		field.setText(numberField);
	}
	
	static String getNumberField() {
		return numberField;
	}
	
	static void clearField() {
		numberField = "";
	}
	
	private void createField () {
		//Creates label for entered buttons
		field.setFont(field.getFont().deriveFont (32.0f));
		field.setHorizontalAlignment(SwingConstants.RIGHT);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        field.setBorder(border);
        field.setPreferredSize(new Dimension(400, 100));
		
		resultArea.add(field);
	}
	
}
