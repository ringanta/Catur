package catur.models;

import java.awt.Point;

public class Posisi {
	private static char[] COLOM = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
	private Point titik;
	
	public Posisi(){ }
	
	/**
	 * Instansiasi objek posisi
	 * @param row nilai sumbu x
	 * @param col nilai sumbu y
	 */
	public Posisi(int row, int col){
		titik = new Point(row, col);
	}
	
	/**
	 * Instansiasi objek posisi
	 * @param pos posisi
	 */
	public Posisi(Point pos){
		titik = pos;
	}
	
	public Posisi(Posisi pos){
		this(pos.getPosisiX(), pos.getPosisiY());
	}
	
	/**
	 * Mengambil nilai posisi saat ini
	 * @return Point posisi saat ini
	 */
	public Point getTitik(){
		return titik;
	}
	
	/**
	 * Mengganti posisi lama dengan nilai yang baru
	 * @param titik posisi lama
	 */
	public void setTitik(Point titik){
		this.titik = titik;
	}
	
	/**
	 * Mengambil nilai sumbu x dari titik
	 * @return int sumbu x
	 */
	public int getPosisiX(){
		return titik.x;
	}
	
	/**
	 * Mengembalikan nilai sumbu y dari titik
	 * @return int sumbu y
	 */
	public int getPosisiY(){
		return titik.y;
	}
	
	/**
	 * Mengecek apakah posisi yang diberikan sama
	 * @param pos posisi
	 * @return true jika sama
	 */
	public boolean sama(Posisi pos){
		return titik.x == pos.getPosisiX() && titik.y == pos.getPosisiY();
	}
	
	@Override
	public String toString(){
		return String.format("%s%s", COLOM[titik.x],titik.y + 1);
	}
}
