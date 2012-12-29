package catur.models;

import catur.utils.TypeAnak;

public class MenteriModel extends AnakCatur {
	
	/**
	 * Instansiasi objek MenteriModel
	 */
	public MenteriModel(){
		setType(TypeAnak.MENTERI);
		setGambar("menteri.png");
		setNama("Menteri");
	}
	
	public MenteriModel(String nama, int pemilik, Posisi pos){
		this();
		setNama(nama);
		setPemilik(pemilik);
		setPosisi(pos);
	}
}
