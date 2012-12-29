package catur.views;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class WinPapanCatur extends JFrame {
	/**
	 * Default serial ID
	 */
	private static final long serialVersionUID = 1L;
	static{
		JFrame.setDefaultLookAndFeelDecorated(true);
	}
	
	public WinPapanCatur(JComponent comp){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(comp);
		pack();
	}
}
