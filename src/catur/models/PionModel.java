package catur.models;

import catur.utils.TypeAnak;

public class PionModel extends AnakCatur {
	
	/**
	 * Instansiasi objek PionModel dengan nilai tipe PION dan gambar pion.png
	 */
	public PionModel(){
		setType(TypeAnak.PION);
		setGambar("pion.png");
	}
	
	public PionModel(String nama, int pemilik, Posisi pos){
		this();
		setNama(nama);
		setPemilik(pemilik);
		setPosisi(pos);
	}
}
