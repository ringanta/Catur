package catur.controllers;

import catur.models.AnakCatur;
import catur.models.PapanCaturModel;
import catur.models.Posisi;
import catur.utils.Helper;
import catur.utils.TypeAnak;

public class CaturController {
	private PapanCaturModel papanModel;
	
	public CaturController(PapanCaturModel papan){
		papanModel = papan;
	}
	
	public boolean isJalurValid(Posisi awal, Posisi akhir){
		boolean valid = true;
		int deltaX = akhir.getPosisiX() - awal.getPosisiX();
		int deltaY = akhir.getPosisiY() - awal.getPosisiY();
		boolean jalurKuda = false;
		AnakCatur pion = papanModel.getAnakCatur(awal);
		
		if (pion.getType() == TypeAnak.KUDA){
			valid = Helper.isJalurKuda(deltaX, deltaY);
		}
		valid = Helper.isLangkahValid(awal, akhir);
		return valid;
	}
	
	
	public boolean cekKuda(int deltaX, int deltaY){
		return Helper.isJalurKuda(deltaX, deltaY);
	}
	
	/**
	 * Mengecek apakah ada anak catur pada posisi tertentu
	 * @param pos posisi
	 * @return true jika kosong
	 */
	public boolean isKosong(Posisi pos){
		return papanModel.getAnakCatur(pos) == null;
	}
}
