package catur.models;

import catur.utils.TypeAnak;

public class RajaModel extends AnakCatur {
	
	/**
	 * Instansiasi objek RajaModel dengan nilai tipe RAJA dan gambar raja.png
	 */
	public RajaModel(){
		setType(TypeAnak.RAJA);
		setGambar("raja.png");
	}
	
	public RajaModel(String nama, int pemilik, Posisi pos){
		this();
		setNama(nama);
		setPemilik(pemilik);
		setPosisi(pos);
	}
}
