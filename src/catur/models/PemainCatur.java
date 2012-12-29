package catur.models;

public class PemainCatur {
	private String nama;
	private int giliran;
	
	public PemainCatur(){}
	
	public PemainCatur(String nama, int giliran){
		this.nama = nama;
		this.giliran = giliran;
	}
	
	/**
	 * Mengganti nama pemain
	 * @param nama nama pemain
	 */
	public void setNama(String nama){
		this.nama = nama;
	}
	
	/**
	 * Mengganti giliran pemain
	 * @param giliran giliran pemain
	 */
	public void setGiliran(int giliran){
		this.giliran = giliran;
	}
	
	/**
	 * Mengambil nama pemain
	 * @return String nama
	 */
	public String getNama(){
		return nama;
	}
	
	/**
	 * Mengambil giliran pemain
	 * @return int giliran
	 */
	public int getGiliran(){
		return giliran;
	}
}
