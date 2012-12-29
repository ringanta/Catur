package catur.controllers;

import catur.models.PapanCaturModel;
import catur.models.Posisi;
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
		
		return valid;
	}
}
