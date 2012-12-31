package catur.controllers;

import java.awt.event.MouseAdapter;

import javax.swing.ImageIcon;

import catur.models.AnakCatur;
import catur.models.PapanCaturModel;
import catur.models.PemainCatur;
import catur.models.Posisi;
import catur.models.RatuModel;
import catur.utils.Helper;
import catur.utils.TypeAnak;
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
		PapanCatur papan = playScreen.getPapan();
		
		if (isSorot()){
			if (posisi.sama(posisiSorot)){
				posisiSorot = null;
				papan.padam(posisi);
			} else {
				// Cek apakah kotak yang dipilih valid
				if (caturCtrl.isJalurValid(posisiSorot, posisi)){
					
					AnakCatur pion = papanModel.getAnakCatur(posisiSorot);
					AnakCatur pionAkhir = papanModel.getAnakCatur(posisi);
					// anak catur memakan anak catur yg lain
					if (pionAkhir != null){
						papanModel.removeAnakCatur(pionAkhir);
					}
					pion.setPosisi(posisi);
					
					// pion berhasil sekolah
					if (pion.getType() == TypeAnak.PION && Helper.isPionPosisiAkhir(pion)){
						papanModel.removeAnakCatur(pion);
						papanModel.addAnakCatur(new RatuModel("Ratu sekolah", giliran, posisi));
					}
					
					InfoPermainan infoPermainan = playScreen.getInfoPermainan();
					StringBuffer info = new StringBuffer("<html>");
					// cek skak
					AnakCatur ratu = papanModel.getAnakCatur(TypeAnak.RATU, 0);
					AnakCatur pengancam[] = caturCtrl.cekBahaya(ratu);
					//System.out.print("Pengancam ratu 0: ");
					//Helper.printIsiArray(pengancam);
					if (pengancam.length > 0){
						info.append("Ratu Pemain ").append(pemain[0].getNama()).append(" mendapat ancaman");
						for (int i=0; i<pengancam.length; i++){
							info.append(String.format("<br>%s. %s", (i+1), pengancam[i]));
						}
					}
					
					AnakCatur raja = papanModel.getAnakCatur(TypeAnak.RAJA, 0);
					pengancam = caturCtrl.cekBahaya(raja);
					//System.out.print("Pengancam raja 0: ");
					//Helper.printIsiArray(pengancam);
					if (pengancam.length > 0){
						info.append("Raja Pemain ").append(pemain[0].getNama()).append(" mendapat ancaman");
						for (int i=0; i<pengancam.length; i++){
							System.out.println(pengancam[i]);
							info.append(String.format("<br>%s. %s", (i+1), pengancam[i].toString()));
						}
					}
					
					ratu = papanModel.getAnakCatur(TypeAnak.RATU, 1);
					pengancam = caturCtrl.cekBahaya(ratu);
					//System.out.print("Pengancam ratu 1: ");
					//Helper.printIsiArray(pengancam);
					if (pengancam.length > 0){
						info.append("<br>Ratu Pemain ").append(pemain[1].getNama()).append(" mendapat ancaman");
						for (int i=0; i<pengancam.length; i++){
							info.append(String.format("<br>%s. %s", (i+1), pengancam[i]));
						}
					}
					
					raja = papanModel.getAnakCatur(TypeAnak.RAJA, 1);
					pengancam = caturCtrl.cekBahaya(raja);
					//System.out.print("Pengancam raja 1: ");
					//Helper.printIsiArray(pengancam);
					if (pengancam.length > 0){
						info.append("<br>Raja Pemain ").append(pemain[1].getNama()).append(" mendapat ancaman dari");
						for (int i=0; i<pengancam.length; i++){
							info.append(String.format("<br>%s. %s", (i+1), pengancam[i].getNama()));
						}
					}
					info.append("</html>");
					infoPermainan.setInfo(info.toString());
					
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
