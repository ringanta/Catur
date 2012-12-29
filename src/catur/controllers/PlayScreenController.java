package catur.controllers;

import java.awt.event.MouseAdapter;

import javax.swing.ImageIcon;

import catur.models.AnakCatur;
import catur.models.PapanCaturModel;
import catur.models.PemainCatur;
import catur.models.Posisi;
import catur.views.InfoPermainan;
import catur.views.PapanCatur;
import catur.views.PlayScreen;

public class PlayScreenController extends MouseAdapter implements PosisiControlable {
	private PlayScreen playScreen;
	private PapanCaturModel papanModel;
	private CaturController caturCtrl;
	private PemainCatur[] pemain;
	private int giliran;
	private Posisi posisiSorot;
	
	public PlayScreenController(PlayScreen screen){
		this.playScreen = screen;
		this.giliran = 0;
		this.pemain = new PemainCatur[2];
	}
	
	public void setModel(PapanCaturModel pcm, PemainCatur pemain1, PemainCatur pemain2){
		this.papanModel = pcm;
		pemain[0] = pemain1;
		pemain[1] = pemain2;
		caturCtrl = new CaturController(papanModel);
	}
	
	public void mulai(){
		setInfoPermainan();
		loadGambar();
	}
	
	@Override
	public void proses(Posisi posisi) {
		System.out.println(posisi);
		PapanCatur papan = playScreen.getPapan();
		if (isSorot()){
			if (posisi.sama(posisiSorot)){
				posisiSorot = null;
				papan.padam(posisi);
			} else {
				// Cek apakah kotak yang dipilih valid
				if (caturCtrl.isJalurValid(posisiSorot, posisi)){
					
					AnakCatur pion = papanModel.getAnakCatur(posisiSorot);
					pion.setPosisi(posisi);
					
					// update tampilan
					loadGambar();
					posisiSorot = null;

					gantiGiliran();
				}
			}
		} else {
			AnakCatur pion = papanModel.getAnakCatur(posisi);
			if (pion != null && pion.getPemilik() == giliran){
				papan.sorot(posisi);
				posisiSorot = posisi;
			}
		}
	}
	
	private void setInfoPermainan(){
		InfoPermainan info = playScreen.getInfoPermainan();
		String warna = giliran == 0 ? "Putih" : "Hitam";
		info.setPemain(String.format("<html><center>%s<br>%s</center>", pemain[giliran].getNama(), warna));
	}
	
	private boolean isSorot(){
		return posisiSorot != null;
	}
	
	private void loadGambar(){
		AnakCatur pion = null;
		ImageIcon icon = null;
		PapanCatur papan = playScreen.getPapan();
		
		papan.hapusSeluruhGambar();
		for (int i=0; i<papanModel.getJumlahPion(); i++){
			pion = papanModel.getAnakCatur(i);
			icon = LoaderImage.getImage(String.format("%s%s", pion.getPemilik(), pion.getGambar()));
			papan.setGambarAt(icon, pion.getPosisi());
		}
	}
	
	private void gantiGiliran(){
		giliran = giliran == 0 ? 1 : 0;
		setInfoPermainan();
	}
}
