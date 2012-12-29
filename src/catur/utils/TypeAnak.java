package catur.utils;

/**
 * Konstanta yang menyatakan tipe yang memungkinkan untuk anak catur
 * @author roy.i.ginting
 *
 */
public enum TypeAnak {
	PION(0), KUDA(1), RAJA(2), RATU(3), BENTENG(4), MENTERI(5);
	
	private int tipe;
	
	TypeAnak(int tipe){
		this.tipe = tipe;
	}
	
	public String toString(){
		String result = "";
		switch(tipe){
		case 0:
			result = "Pion";
			break;
		case 1:
			result = "Kuda";
			break;
		case 2:
			result = "Raja";
			break;
		case 3:
			result = "Ratu";
			break;
		case 4:
			result = "Benteng";
			break;
		case 5:
			result = "Menteri";
			break;
		}
		return result;
	}
}