package catur.models;

import catur.utils.TypeAnak;

public abstract class AnakCatur {
	protected String nama;
	protected TypeAnak type;
	protected String gambar;
	protected Posisi posisi;
	protected int pemilik;
	
	public AnakCatur(){}
	
	/**
	 * Instansiasi objek AnakCatur
	 * @param nama nama anak catur
	 * @param type tipe dari anak catur
	 * @param gambar nama file gambar anak catur
	 * @param pos posisi anak catur
	 */
	public AnakCatur(String nama, TypeAnak type, String gambar, Posisi pos){
		this.nama = nama;
		this.type = type;
		this.gambar = gambar;
		posisi = pos;
	}
	
	/**
	 * Mengambil nama dari anak catur
	 * @return String nama
	 */
	public String getNama(){
		return nama;
	}
	
	/**
	 * Mengambil tipe dari anak catur
	 * @return TypeAnak tipe
	 */
	public TypeAnak getType(){
		return type;
	}
	
	/**
	 * Mengambil nama file gambar anak catur
	 * @return String nama file
	 */
	public String getGambar(){
		return gambar;
	}
	
	/**
	 * Mengambil posisi dari anak catur
	 * @return Posisi posisi
	 */
	public Posisi getPosisi(){
		return posisi;
	}
	
	/**
	 * Mengambil pemilik anak catur
	 * @return nama pemilik
	 */
	public int getPemilik(){
		return pemilik;
	}
	
	/**
	 * Mengganti pemilik dari anak catur
	 * @param pemilik naam pemilik
	 */
	public void setPemilik(int pemilik){
		this.pemilik = pemilik;
	}
	
	/**
	 * Mengganti nama anak catur
	 * @param nama nama
	 */
	public void setNama(String nama){
		this.nama = nama;
	}
	
	/**
	 * Mengganti tipe dari anak catur
	 * @param type tipe
	 */
	public void setType(TypeAnak type){
		this.type = type;
	}
	
	/**
	 * Mengganti nama file gambar anak catur
	 * @param gambar nama file
	 */
	public void setGambar(String gambar){
		this.gambar = gambar;
	}
	
	/**
	 * Mengganti posisi anak catur
	 * @param pos posisi
	 */
	public void setPosisi(Posisi pos){
		this.posisi = pos;
	}
	
	@Override
	public String toString(){
		return String.format("{%s, %s, %s, %s, %s}", nama, type.toString(), gambar, posisi, pemilik);
	}
}
