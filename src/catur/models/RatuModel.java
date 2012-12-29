package catur.models;

import catur.utils.TypeAnak;

public class RatuModel extends AnakCatur {
	
	/**
	 * Instansiasi objek RatuModel dengan nilai tipe RATU dan gambar ratu.png
	 */
	public RatuModel(){
		setType(TypeAnak.RATU);
		setGambar("ratu.png");
	}
	
	public RatuModel(String nama, int pemilik, Posisi pos){
		this();
		setNama(nama);
		setPemilik(pemilik);
		setPosisi(pos);
	}
}
