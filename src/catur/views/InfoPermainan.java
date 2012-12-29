package catur.views;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPermainan extends JPanel {
	/**
	 * Default serial ID
	 */
	private static final long serialVersionUID = 1L;
	private JLabel pemain;
	private JLabel info;
	
	public InfoPermainan(){
		super(new GridLayout(2,1));
		
		Dimension ukuran = new Dimension(200, 200);
		pemain = new JLabel("Pemain X", JLabel.CENTER);
		pemain.setPreferredSize(ukuran);
		
		info = new JLabel("Sekilas info", JLabel.CENTER);
		info.setPreferredSize(ukuran);
		
		add(pemain);
		add(info);
	}
	
	public void setPemain(String pemain){
		this.pemain.setText(pemain);
	}
	
	public void setInfo(String info){
		this.info.setText(info);
	}
}
