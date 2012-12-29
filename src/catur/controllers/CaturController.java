package catur.controllers;

import catur.models.AnakCatur;
import catur.models.PapanCaturModel;
import catur.models.Posisi;
import catur.utils.Arah;
import catur.utils.Helper;
import catur.utils.TypeAnak;

public class CaturController {
	private PapanCaturModel papanModel;
	
	public CaturController(PapanCaturModel papan){
		papanModel = papan;
	}
	
	/**
	 * Mengecek apakah perpindahan anak catur dari posisi awal menuju posisi akhir valid
	 * @param awal posisi
	 * @param akhir posisi
	 * @return true jika valid
	 */
	public boolean isJalurValid(Posisi awal, Posisi akhir){
		boolean valid = true;
		AnakCatur pion = papanModel.getAnakCatur(awal);
		
		if (pion.getType() == TypeAnak.KUDA){
			valid = cekKuda(awal, akhir);
		} else if (pion.getType() == TypeAnak.PION){
			valid = cekPion(awal, akhir);
		} else if (pion.getType() == TypeAnak.MENTERI){
			valid = cekMenteri(awal, akhir);
		}
		
		return valid;
	}
	
	public boolean cekMenteri(Posisi awal, Posisi akhir){
		int delta[] = Helper.getDelta(awal, akhir);
		boolean valid = Helper.samaTanpaSimbol(delta[0], delta[1]);
		
		if(valid){
			Arah arah = Arah.getArah(delta[0], delta[1]);
			
		}
		return valid;
	}
	
	/**
	 * Mengecek apakah jalur kuda valid
	 * @param awal posisi
	 * @param akhir posisi
	 * @return true jika valid
	 */
	public boolean cekKuda(Posisi awal, Posisi akhir){
		int delta[] = Helper.getDelta(awal, akhir);
		boolean valid = Helper.isJalurKuda(delta[0], delta[1]);
		
		if (valid){
			if (!isKosong(akhir)){
				AnakCatur pion = papanModel.getAnakCatur(awal);
				valid = isMilikLawan(pion.getPemilik(), akhir);
			}
		}
		return valid;
	}
	
	/**
	 * Mengecek apakah jalur pion valid
	 * @param awal posisi
	 * @param akhir posisi
	 * @return true jika valid
	 */
	public boolean cekPion(Posisi awal, Posisi akhir){
		boolean valid = true;
		AnakCatur pion = papanModel.getAnakCatur(awal);
		Posisi lanjut = null;
		Arah arah = pion.getPemilik() == 0 ? Arah.SELATAN : Arah.UTARA;
		Arah kiri = pion.getPemilik() == 0 ? Arah.TENGGARA : Arah.BARATLAUT;
		Arah kanan = pion.getPemilik() == 0 ? Arah.BARATDAYA : Arah.TIMURLAUT;
		
		// cek maju ke kiri
		lanjut = Helper.getLangkahSelanjutnya(awal, kiri);
		valid = isMilikLawan(pion.getPemilik(), lanjut);
		
		// cek maju ke kanan
		if (!valid){
			lanjut = Helper.getLangkahSelanjutnya(awal, kanan);
			valid = isMilikLawan(pion.getPemilik(), lanjut);
		}
		
		// cek maju satu langkah
		if (!valid){
			lanjut = Helper.getLangkahSelanjutnya(awal, arah);
			valid = isKosong(lanjut) && lanjut.sama(akhir);
		}
		
		// cek maju 2 langkah
		if (!valid && Helper.isPionPosisiAwal(pion)){
			lanjut = Helper.getLangkahSelanjutnya(lanjut, arah);
			valid = isKosong(lanjut) && lanjut.sama(akhir);
		}
		return valid;
	}
	
	/**
	 * Mengecek apakah ada anak catur pada posisi tertentu
	 * @param pos posisi
	 * @return true jika kosong
	 */
	public boolean isKosong(Posisi pos){
		return papanModel.getAnakCatur(pos) == null;
	}
	
	/**
	 * Mengecek apakah seluruh posisi yang diberikan kosong
	 * @param posisi sekumpulan posisi
	 * @return true jika sekumpulan posisi kosong
	 */
	public boolean isKosong(Posisi[] posisi){
		boolean valid = true;
		
		for (int i=0; i<posisi.length; i++){
			if (!isKosong(posisi[i])){
				valid = false;
				break;
			}
		}
		return valid;
	}
	
	/**
	 * Mengecek apakah anak catur pada posisi tertentu milik lawan
	 * @param pemilik pemilik
	 * @param pos posisi
	 * @return true jika anak catur milik lawan
	 */
	public boolean isMilikLawan(int pemilik, Posisi pos){
		boolean hasil = false;
		
		if (!isKosong(pos)){
			AnakCatur pion = papanModel.getAnakCatur(pos);
			hasil = pion.getPemilik() != pemilik;
		}
		return hasil;
	}
}
