package catur.utils;

import java.awt.Color;
import java.awt.Point;

import catur.models.Posisi;
import catur.views.PapanCatur;

public class Helper {
	
	/**
	 * Operasi pembagian bilangan bulat
	 * @param base bilangan utama
	 * @param pembagi pembagi
	 * @return int hasil div
	 */
	public static int div(int base, int pembagi){
		int hasil = (int) base / pembagi;
		return hasil;
	}
	
	/**
	 * Operasi mod pada bilangan bulat
	 * @param base bilangan utama
	 * @param pembagi pembagi
	 * @return int hasil mod
	 */
	public static int mod(int base, int pembagi){
		int hasilDiv = div(base, pembagi);
		int hasil = base - hasilDiv * pembagi;
		return hasil;
	}
	
	/**
	 * Menentukan warna background untuk kotak pada papan catur
	 * @param index index kotak
	 * @return warna hitam jika index genap dan putih selainnya
	 */
	public static Color getBackgroundKotak(int index){
		Color background = null;
		Posisi titik = getPosisi(index);
		
		if (Helper.mod(titik.getPosisiX(), 2) == 0 && Helper.mod(titik.getPosisiY(), 2) == 0){		
			background = Color.black;
		} else if(Helper.mod(titik.getPosisiX(), 2) == 1 && Helper.mod(titik.getPosisiY(), 2) == 1){
			background = Color.black;
		} else {
			background = Color.white;
		}
		return background;
	}
	
	/**
	 * Menghitung nilai x dan y dari satu nilai yang diberikan
	 * @param index index
	 * @return Point x, y
	 */
	public static Posisi getPosisi(int index){
		int x = Helper.div(index, PapanCatur.TOTAL_Y);
		int y = Helper.mod(index, PapanCatur.TOTAL_Y);
		return new Posisi(new Point(x, y));
	}
	
	/**
	 * Mengubah posisi menjadi index
	 * @param pos posisi
	 * @return int index
	 */
	public static int getIndex(Posisi pos){
		int x = pos.getPosisiX();
		int y = pos.getPosisiY();
		
		return x * PapanCatur.TOTAL_Y + y;
	}
	
	/**
	 * Menghitung langkah selanjutnya dari posisi yang diberikan
	 * @param awal posisi
	 * @param arah arah selanjutnya
	 * @return Posisi posisi
	 */
	public static Posisi getLangkahSelanjutnya(Posisi awal, Arah arah){
		int x = awal.getPosisiX() + arah.getX();
		int y = awal.getPosisiY() + arah.getY();
		if (isTitikValid(x, y)){
			return new Posisi(x, y);
		} else return null;
	}
	
	/**
	 * Mengecek apakah jalur yang diberikan valid
	 * @param awal posisi
	 * @param akhir posisi
	 * @return true jika valid
	 */
	public static boolean isLangkahValid(Posisi awal, Posisi akhir){
		int[] delta = getDelta(awal, akhir);
		return isSalahSatuNol(delta[0], delta[1]) || samaTanpaSimbol(delta[0], delta[1]) || isJalurKuda(delta[0], delta[1]);
	}
	 
	/**
	 * Mengecek apakah titik x dan y valid
	 * @param x sumbu x
	 * @param y sumbu y
	 * @return true jika x dan y dalam rentang 0 - 7
	 */
	public static boolean isTitikValid(int x, int y){
		return x >= 0 && x < PapanCatur.TOTAL_X && y >= 0 && y < PapanCatur.TOTAL_Y;
	}
	
	/**
	 * Mengecek apakah nilai mutlak kedua bilangan sama
	 * @param x bilangan pertama
	 * @param y bilangan kedua
	 * @return true jika x sama dengan y
	 */
	public static boolean samaTanpaSimbol(int x, int y){
		return nilaiMutlak(x) == nilaiMutlak(y);
	}
	
	/**
	 * Mengecek apakah salah satu nilai yang diberikan sama dengan 0
	 * @param x nilai
	 * @param y nilai
	 * @return true jika x atau y bernilai 0
	 */
	public static boolean isSalahSatuNol(int x, int y){
		return x == 0 || y == 0;
	}
	
	/**
	 * Mengecek apakah nilai yang diberikan membentuk jalur kuda
	 * @param x nilai pertama
	 * @param y nilai kedua
	 * @return true jika membentuk jalur kuda
	 */
	public static boolean isJalurKuda(int x, int y){
		int mutlakX = nilaiMutlak(x);
		int mutlakY = nilaiMutlak(y);
		
		return (mutlakX == 1 && mutlakY == 2) || (mutlakX == 2 && mutlakY == 1);
	}
	
	/**
	 * Mencari nilai mutlak dari satu bilangan
	 * @param value nilai
	 * @return int nilai mutlak
	 */
	public static int nilaiMutlak(int value){
		return value >= 0 ? value : -1 * value;
	}
	
	/**
	 * Mencari selisih nilai x dan y dari 2 posisi yang diberikan
	 * @param awal posisi awal
	 * @param akhir posisi akhir
	 * @return array int
	 */
	public static int[] getDelta(Posisi awal, Posisi akhir){
		int[] tab = new int[2];
		
		tab[0] = awal.getPosisiX() - akhir.getPosisiX();
		tab[1] = awal.getPosisiY() - akhir.getPosisiY();
		return tab;
	}
}
