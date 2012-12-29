package catur.models;

import catur.utils.TypeAnak;

public class KudaModel extends AnakCatur {
	
	/**
	 * Instansiasi objek KudaModel
	 */
	public KudaModel(){
		setType(TypeAnak.KUDA);
		setGambar("kuda.png");
		setNama("Kuda");
	}
	
	public KudaModel(String nama, int pemilik, Posisi pos){
		this();
		setNama(nama);
		setPemilik(pemilik);
		setPosisi(pos);
	}
}
