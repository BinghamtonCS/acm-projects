package mainview;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class ControlPanel extends JPanel {
	
	public ControlPanel() {
		setLayout(new GridLayout(5,4));
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				add(new JPanel());
			}
		}
	}
}
