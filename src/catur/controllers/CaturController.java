package catur.controllers;

import java.util.ArrayList;

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
		} else if (pion.getType() == TypeAnak.BENTENG){
			valid = cekBenteng(awal, akhir);
		} else if (pion.getType() == TypeAnak.RATU){
			valid = cekMenteri(awal, akhir);
			valid = !valid ? cekBenteng(awal, akhir) : valid;
		} else if (pion.getType() == TypeAnak.RAJA){
			valid = cekRaja(awal, akhir);
		}
		
		return valid;
	}
	
	/**
	 * Mengecek apakah jalur raja valid. Yang termasuk jalur valid yaitu:
	 * arah utara, timur laut, timur, tenggara, selatan, barat daya, barat, dan barat laut
	 * @param awal
	 * @param akhir
	 * @return
	 */
	public boolean cekRaja(Posisi awal, Posisi akhir){
		boolean valid = true;
		Posisi lanjut = null;
		AnakCatur pion = papanModel.getAnakCatur(awal);
		
		// cek UTARA
		lanjut = Helper.getLangkahSelanjutnya(awal, Arah.UTARA);
		valid = lanjut != null && lanjut.sama(akhir) && (isKosong(lanjut) || isMilikLawan(pion.getPemilik(), lanjut));
		
		// cek TIMURLAUT
		lanjut = Helper.getLangkahSelanjutnya(awal, Arah.TIMURLAUT);
		valid = (valid || lanjut != null && lanjut.sama(akhir) && (isKosong(lanjut) || isMilikLawan(pion.getPemilik(), lanjut)));
		
		// cek TIMUR
		lanjut = Helper.getLangkahSelanjutnya(awal, Arah.TIMUR);
		valid = (valid || lanjut != null && lanjut.sama(akhir) && (isKosong(lanjut) || isMilikLawan(pion.getPemilik(), lanjut)));
		
		// cek TENGGARA
		lanjut = Helper.getLangkahSelanjutnya(awal, Arah.TENGGARA);
		valid = (valid || lanjut != null && lanjut.sama(akhir) && (isKosong(lanjut) || isMilikLawan(pion.getPemilik(), lanjut)));
		
		// cek SELATAN
		lanjut = Helper.getLangkahSelanjutnya(awal, Arah.SELATAN);
		valid = (valid || lanjut != null && lanjut.sama(akhir) && (isKosong(lanjut) || isMilikLawan(pion.getPemilik(), lanjut)));
		
		// cek BARATDAYA
		lanjut = Helper.getLangkahSelanjutnya(awal, Arah.BARATDAYA);
		valid = (valid || lanjut != null && lanjut.sama(akhir) && (isKosong(lanjut) || isMilikLawan(pion.getPemilik(), lanjut)));
		
		// cek BARAT
		lanjut = Helper.getLangkahSelanjutnya(awal, Arah.BARAT);
		valid = (valid || lanjut != null && lanjut.sama(akhir) && (isKosong(lanjut) || isMilikLawan(pion.getPemilik(), lanjut)));
		
		// cek BARATLAUT
		lanjut = Helper.getLangkahSelanjutnya(awal, Arah.BARATLAUT);
		valid = (valid || lanjut != null && lanjut.sama(akhir) && (isKosong(lanjut) || isMilikLawan(pion.getPemilik(), lanjut)));
		
		return valid;
	}
	/**
	 * Mengecek apakah jalur benteng valid. Yang termasuk jalur valid yaitu:
	 * 1. vertikal
	 * 2. Horizontal
	 * @param awal posisi
	 * @param akhir posisi
	 * @return true jika valid
	 */
	public boolean cekBenteng(Posisi awal, Posisi akhir){
		int[] delta = Helper.getDelta(awal, akhir);
		boolean valid = Helper.isSalahSatuNol(delta[0], delta[1]);
		
		if(valid){
			valid = cekLanjutan(awal, akhir);
		}
		return valid;
	}
	
	/**
	 * Mengecek apakah jalur menteri valid. Yang termasuk jalur valid yaitu:
	 * 1. Serong kiri
	 * 2. Serong kanan
	 * @param awal posisi
	 * @param akhir posisi
	 * @return true jika valid
	 */
	public boolean cekMenteri(Posisi awal, Posisi akhir){
		int delta[] = Helper.getDelta(awal, akhir);
		boolean valid = Helper.samaTanpaSimbol(delta[0], delta[1]);
		
		if(valid){
			valid = cekLanjutan(awal, akhir);
		}
		return valid;
	}
	
	/**
	 * Mengecek apakah jalur kuda valid. Yang termasuk jalur valid yaitu jalur yg membentuk huruf L
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
	 * Mengecek apakah jalur pion valid. Yang termasuk jalur valid yaitu:
	 * 1. Maju selangkah
	 * 2. Maju 2 langkah
	 * 3. Serong kiri
	 * 4. Serong kanan
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
		valid = lanjut != null && isMilikLawan(pion.getPemilik(), lanjut) && lanjut.sama(akhir);
		
		// cek maju ke kanan
		if (!valid){
			lanjut = Helper.getLangkahSelanjutnya(awal, kanan);
			valid = lanjut != null && isMilikLawan(pion.getPemilik(), lanjut) && lanjut.sama(akhir);
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
	 * Mencari index posisi yang tidak kosong
	 * @param posisi posisi
	 * @return int index
	 */
	public int cekKosong(Posisi[] posisi){
		int i = 0;
		
		for (;i<posisi.length; i++){
			if (!isKosong(posisi[i])){
				break;
			}
		}
		return i;
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
	
	/**
	 * Mengambil seluruh anak catur yang mengancam keberadaan anak catur yg lain
	 * @param pion anak catur 
	 * @return array anak catur
	 */
	public AnakCatur[] cekBahaya(AnakCatur pion){
		Posisi posisiPion = pion.getPosisi();
		int pengancam = pion.getPemilik() == 0 ? 1 : 0;
		ArrayList<AnakCatur> listPengancam = new ArrayList<AnakCatur>();
		AnakCatur[] tabPengancam;
		AnakCatur[] kandidatPengancam = papanModel.getSemuaAnakCatur(pengancam);
		boolean flag;
		
		for (int i=0; i<kandidatPengancam.length; i++){
			flag = isJalurValid(kandidatPengancam[i].getPosisi(), posisiPion);
			if (flag){
				listPengancam.add(kandidatPengancam[i]);
			}
		}
		int jumlah = listPengancam.size();
		tabPengancam = new AnakCatur[jumlah];
		for (int i=0; i<jumlah; i++){
			tabPengancam[i] = listPengancam.get(i);
		}
		return tabPengancam;
	}
	
	private boolean cekLanjutan(Posisi awal, Posisi akhir){
		boolean valid = true;
		int delta[] = Helper.getDelta(awal, akhir);
		Arah arah = Arah.getArah(delta[0], delta[1]);
		Posisi[] listPosisi = Helper.getSemuaPosisi(awal, akhir, arah);
		
		int index = cekKosong(listPosisi);
		valid = index == listPosisi.length;
		int indexAkhir = listPosisi.length - 1;
		
		if (!valid && index == indexAkhir){
			AnakCatur pion = papanModel.getAnakCatur(awal);
			valid = isMilikLawan(pion.getPemilik(), akhir);
		}
		return valid;
	}
}
