package catur.views;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Menu extends JPanel {
	/**
	 * Default serial ID
	 */
	private static final long serialVersionUID = 1L;
	private JButton menuKeluar;
	private JButton menuMain;
	private JButton menuTentang;
	
	public Menu(){
		setLayout(new GridLayout(3, 1));
		menuKeluar = new JButton("Keluar");
		menuKeluar.setPreferredSize(new Dimension(200, 200));
		menuMain = new JButton("Multi Pemain");
		menuMain.setPreferredSize(new Dimension(200, 200));
		menuTentang = new JButton("Informasi Catur");
		menuTentang.setPreferredSize(new Dimension(200, 200));
		
		add(menuMain);
		add(menuTentang);
		add(menuKeluar);
	}
	
	/**
	 * Cek apakah objek yang diberikan adalah menu keluar
	 * @param objek objek yang akan diuji
	 * @return true jika sama
	 */
	public boolean isMenuKeluar(Object objek){
		return objek == menuKeluar;
	}
	
	/**
	 * Mengecek apakah objek yang diberikan adalah menu main
	 * @param objek objek yang akan diuji
	 * @return true jika sama
	 */
	public boolean isMenuMain(Object objek){
		return objek == menuMain;
	}
	
	/**
	 * Mengecek apakah objek yang diberikan adalah menu tentang
	 * @param objek objek yang akan diuji
	 * @return true jika sama
	 */
	public boolean isMenuTentang(Object objek){
		return objek == menuTentang;
	}
	
	/**
	 * Mendaftarkan seluruh tombol ke Action Listener
	 * @param al Action listener
	 */
	public void daftar(ActionListener al){
		menuKeluar.addActionListener(al);
		menuTentang.addActionListener(al);
		menuMain.addActionListener(al);
	}
}
