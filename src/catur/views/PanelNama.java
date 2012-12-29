package catur.views;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelNama extends JPanel {

	/**
	 * Default serial ID
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lblPemain1;
	private JLabel lblPemain2;
	private JTextField txtPemain1;
	private JTextField txtPemain2;
	private JButton play;
	
	public PanelNama(ActionListener al){
		super(new GridLayout(5, 1));
		lblPemain1 = new JLabel("Nama pemain pertama:");
		lblPemain2 = new JLabel("Nama pemain kedua:");
		
		txtPemain1 = new JTextField("Pemain pertama", 25);
		txtPemain2 = new JTextField("Pemain kedua", 25);
		
		play = new JButton("Main catur");
		play.addActionListener(al);
		play.setSize(new Dimension(200, 50));
		
		add(lblPemain1);
		add(txtPemain1);
		add(lblPemain2);
		add(txtPemain2);
		add(play);
	}
	
	/**
	 * Mengambil nama pemain pertama
	 * @return String nama pemain
	 */
	public String getNamaPertama(){
		return txtPemain1.getText();
	}
	
	/**
	 * Mengambil nama pemain kedua
	 * @return String nama pemain
	 */
	public String getNamaKedua(){
		return txtPemain2.getText();
	}
	
	/**
	 * Mengecek apakah objek yang diberikan adalah tombol play
	 * @param objek objek yang akan dicek
	 * @return true jika sama
	 */
	public boolean isTombolMain(Object objek){
		return objek == play;
	}

}
