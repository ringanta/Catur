package catur.views;

import java.awt.BorderLayout;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class PlayScreen extends JPanel {

	/**
	 * Default serial ID
	 */
	private static final long serialVersionUID = 1L;

	private PapanCatur papan;
	private InfoPermainan info;
	
	public PlayScreen(MouseListener ml){
		setLayout(new BorderLayout());
		papan = new PapanCatur();
		info = new InfoPermainan();
		papan.daftar(ml);
		
		add(papan, BorderLayout.CENTER);
		add(info, BorderLayout.EAST);
	}
	
	/**
	 * Mengambil papan catur
	 * @return papan
	 */
	public PapanCatur getPapan(){
		return papan;
	}
	
	/**
	 * Mengambil info permainan
	 * @return InfoPermainan
	 */
	public InfoPermainan getInfoPermainan(){
		return info;
	}
}
