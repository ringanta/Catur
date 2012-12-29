package catur.models;

import catur.utils.TypeAnak;

public class BentengModel extends AnakCatur {
	
	/**
	 * Instansiasi objek BentengModel
	 */
	public BentengModel(){
		setType(TypeAnak.BENTENG);
		setNama("Benteng");
		setGambar("benteng.png");
	}
	
	public BentengModel(String nama, int pemilik, Posisi pos){
		this();
		setNama(nama);
		setPemilik(pemilik);
		setPosisi(pos);
	}
}
