package catur.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import catur.models.Posisi;
import catur.utils.Helper;

public class PapanCatur extends JPanel {

	/**
	 * Defaul serial ID
	 */
	private static final long serialVersionUID = 1L;
	public static final int TOTAL_X = 8;
	public static final int TOTAL_Y = 8;
	public static final int TOTAL_KOTAK = TOTAL_X * TOTAL_Y;
	
	private ArrayList<JLabel> listKotak;
	
	/**
	 * Instansiasi PapanCatur
	 */
	public PapanCatur(){
		// Menggunakan grid layout untuk menyusun kotak-kotak yang akan menampung anak catur
		super(new GridLayout(8, 8));
		
		// Kotak penampung anak catur menggunakan JLabel
		listKotak = new ArrayList<JLabel>(TOTAL_KOTAK);
		JLabel temp = null;
		for (int i=0; i<TOTAL_KOTAK; i++){
			temp = new JLabel();
			temp.setOpaque(true);
			temp.setBackground(Helper.getBackgroundKotak(i));
			listKotak.add(temp);
			temp.setPreferredSize(new Dimension(100, 100));
			add(temp);
		}
	}
	
	/**
	 * Mengganti icon sebuah kotak
	 * @param gbr icon
	 * @param pos posisi
	 */
	public void setGambarAt(ImageIcon gbr, Posisi pos){
		JLabel temp = getLabel(pos);
		temp.setIcon(gbr);
		temp.setVerticalAlignment(JLabel.CENTER);
		temp.setHorizontalAlignment(JLabel.CENTER);
		padam(pos);
	}
	
	/**
	 * Mencari posisi kotak pada papan catur
	 * @param kotak kotak
	 * @return Posisi posisi kotak
	 */
	public Posisi getPosisiKotak(JLabel kotak){
		int index = listKotak.indexOf(kotak);
		Posisi titik = Helper.getPosisi(index);
		return titik;
	}
	
	/**
	 * Memberi tanda pada posisi tertentu
	 * @param pos posisi
	 */
	public void sorot(Posisi pos){
		JLabel temp = getLabel(pos);
		temp.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
	}
	
	/**
	 * Menghilangkan tanda pada posisi tertentu
	 * @param pos posisi
	 */
	public void padam(Posisi pos){
		JLabel temp = getLabel(pos);
		temp.setBorder(BorderFactory.createLineBorder(Color.gray));
	}
	
	/**
	 * Mendaftarkan setiap JLabel pada mouse listener
	 * @param ml mouse listerner
	 */
	public void daftar(MouseListener ml){
		JLabel temp = null;
		for (int i=0; i<listKotak.size(); i++){
			temp = listKotak.get(i);
			temp.addMouseListener(ml);
		}
	}
	
	/**
	 * Menghapus seluruh gambar yg ada pada kotak
	 */
	public void hapusSeluruhGambar(){
		for (int i=0; i<TOTAL_KOTAK; i++){
			setGambarAt(null, Helper.getPosisi(i));
		}
	}
	
	private JLabel getLabel(Posisi pos){
		int index = Helper.getIndex(pos);
		JLabel temp = listKotak.get(index);
		return temp;
	}
}
