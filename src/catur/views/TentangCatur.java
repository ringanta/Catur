package catur.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TentangCatur extends JPanel {
	/**
	 * Default serial ID
	 */
	private static final long serialVersionUID = 1L;
	private JLabel text;
	private JButton keluar;
	
	public TentangCatur(ActionListener al){
		setLayout(new BorderLayout(10, 25));
		text = new JLabel("<html><h1>Permainan Catur</h1><br>" +
				"Permainan catur manusia melawan manusia<br>" +
				"Permainan ini tidak mempunyai AI/Komputer<br>" +
				"Anda harus mengajak seseorang untuk bermain</html>");		
		add(text, BorderLayout.CENTER);
		
		keluar = new JButton("Kembali");
		keluar.setPreferredSize(new Dimension(200, 50));
		keluar.addActionListener(al);
		add(keluar, BorderLayout.SOUTH);
	}
	
	/**
	 * Mengecek apakah objek yang diberikan adalah JButton keluar
	 * @param objek objek yang akan diuji
	 * @return true jika sama
	 */
	public boolean isTombolKeluar(Object objek){
		return objek == keluar;
	}
}
