package catur.models;

import java.util.ArrayList;

import catur.utils.TypeAnak;

public class PapanCaturModel {
	private ArrayList<AnakCatur> pions;
	
	/**
	 * Instansiasi PapanCaturModel
	 */
	public PapanCaturModel(){
		pions = new ArrayList<AnakCatur>();
	}
	
	public void setup(){
		AnakCatur temp;
		
		addAnakCatur(new BentengModel("Benteng", 0, new Posisi(0,0)));
		addAnakCatur(new KudaModel("Kuda", 0, new Posisi(0,1)));
		addAnakCatur(new MenteriModel("Menteri", 0, new Posisi(0,2)));
		addAnakCatur(new RatuModel("Ratu", 0, new Posisi(0,3)));
		addAnakCatur(new RajaModel("Raja", 0, new Posisi(0,4)));
		addAnakCatur(new MenteriModel("Menteri", 0, new Posisi(0,5)));
		addAnakCatur(new KudaModel("Kuda", 0, new Posisi(0,6)));
		addAnakCatur(new BentengModel("Benteng", 0, new Posisi(0,7)));
		for (int i=0; i<8; i++){
			temp = new PionModel("Pion", 0, new Posisi(1, i));
			addAnakCatur(temp);
		}
		
		addAnakCatur(new BentengModel("Benteng", 1, new Posisi(7,0)));
		addAnakCatur(new KudaModel("Kuda", 1, new Posisi(7,1)));
		addAnakCatur(new MenteriModel("Menteri", 1, new Posisi(7,2)));
		addAnakCatur(new RatuModel("Ratu", 1, new Posisi(7,3)));
		addAnakCatur(new RajaModel("Raja", 1, new Posisi(7,4)));
		addAnakCatur(new MenteriModel("Menteri", 1, new Posisi(7,5)));
		addAnakCatur(new KudaModel("Kuda", 1, new Posisi(7,6)));
		addAnakCatur(new BentengModel("Benteng", 1, new Posisi(7,7)));
		for (int i=0; i<8; i++){
			temp = new PionModel("Pion", 1, new Posisi(6, i));
			addAnakCatur(temp);
		}
	}
	
	/**
	 * Mengembalikan jumlah anak catur yang ada pada papan catur
	 * @return
	 */
	public int getJumlahPion(){
		return pions.size();
	}
	
	/**
	 * Menambahkan anak catur baru pada papan catur
	 * @param pion anak catur baru
	 */
	public void addAnakCatur(AnakCatur pion){
		pions.add(pion);
	}
	
	/**
	 * Menghapus anak catur dari papan catur
	 * @param pion anak catur
	 */
	public void removeAnakCatur(AnakCatur pion){
		pions.remove(pion);
	}
	
	/**
	 * Mengambil anak catur pada posisi tertentu
	 * @param pos posisi
	 * @return AnakCatur
	 */
	public AnakCatur getAnakCatur(Posisi pos){
		AnakCatur temp, result = null;
		
		for (int i=0; i<pions.size(); i++){
			temp = pions.get(i);
			if(pos.sama(temp.getPosisi())){
				result = temp;
				break;
			}
		}
		return result;
	}
	
	/**
	 * Mengambil anak catur pada index tertentu
	 * @param index index
	 * @return AnakCatur
	 */
	public AnakCatur getAnakCatur(int index){
		return pions.get(index);
	}
	
	/**
	 * Mengambil anak catur dengan tipe dan pemilik tertentu
	 * @param tipe tipe anak catur
	 * @param pemilik pemilik anak catur
	 * @return Anak Catur
	 */
	public AnakCatur getAnakCatur(TypeAnak tipe, int pemilik){
		AnakCatur pion = null;
		AnakCatur temp;
		
		for (int i=0; i<getJumlahPion(); i++){
			temp = getAnakCatur(i);
			if (temp.getType() == tipe && temp.getPemilik() == pemilik){
				pion = temp;
				break;
			}
		}
		return pion;
	}
	
	/**
	 * Mengambil seluruh anak catur milik pemain
	 * @param pemain pemain
	 * @return array anak catur
	 */
	public AnakCatur[] getSemuaAnakCatur(int pemain){
		ArrayList<AnakCatur> list = new ArrayList<AnakCatur>();
		AnakCatur temp;
		
		for (int i=0; i<getJumlahPion(); i++){
			temp = pions.get(i);
			if (temp.getPemilik() == pemain){
				list.add(temp);
			}
		}
		
		AnakCatur[] tabs = new AnakCatur[list.size()];
		for (int i=0; i<list.size(); i++){
			tabs[i] = list.get(i);
		}
		return tabs;
	}
	
}
