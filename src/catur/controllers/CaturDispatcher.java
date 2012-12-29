package catur.controllers;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import catur.models.PapanCaturModel;
import catur.models.PemainCatur;
import catur.models.Posisi;
import catur.views.Menu;
import catur.views.PanelNama;
import catur.views.PapanCatur;
import catur.views.PlayScreen;
import catur.views.TentangCatur;
import catur.views.WinPapanCatur;

public class CaturDispatcher extends MouseAdapter implements ActionListener {
	// models
	private PapanCaturModel modelCatur;
	
	// views
	private PlayScreen playScreen;
	private Menu menu;
	private TentangCatur tentangCatur;
	private PanelNama panelNama;
	private JFrame[] app;
	
	// controllers
	private PlayScreenController playScreenCtrl;
	
	public CaturDispatcher(){
		modelCatur = new PapanCaturModel();
		
		menu = new Menu();
		menu.daftar(this);
		playScreen = new PlayScreen(this);
		tentangCatur = new TentangCatur(this);
		panelNama = new PanelNama(this);
		
		playScreenCtrl = new PlayScreenController(playScreen);
	}
	
	private void init(){
		app = new JFrame[4];
		app[0] = new WinPapanCatur(menu);
		app[0].pack();
		app[1] = new WinPapanCatur(playScreen);
		app[1].setSize(new Dimension(800, 600));
		app[2] = new WinPapanCatur(tentangCatur);
		app[2].pack();
		app[3] = new WinPapanCatur(panelNama);
		app[3].setSize(new Dimension(350, 300));
	}
	
	public void dispatch(){
		init();
		start();
	}
	
	/**
	 * Mulai bermain catur
	 */
	public void start(){
		app[0].setVisible(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent e){
		PapanCatur papanCatur = playScreen.getPapan();
		JLabel source = (JLabel) e.getSource();
		Posisi posisi = papanCatur.getPosisiKotak(source);
		playScreenCtrl.proses(posisi);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		if(menu.isMenuKeluar(source)){
			tutup();
		} else if (menu.isMenuMain(source)){
			// Mulai main catur
			tampil(3);
		} else if (menu.isMenuTentang(source)){
			// tampilkan halaman tentang catur
			tampil(2);
		} else if (tentangCatur.isTombolKeluar(source)){
			// tampilkan halaman utama
			tampil(0);
		} else if (panelNama.isTombolMain(source)){
			// Mulai main catur
			// inisialisasi model
			PemainCatur p1 = new PemainCatur(panelNama.getNamaPertama(), 0);
			PemainCatur p2 = new PemainCatur(panelNama.getNamaKedua(), 1);
			modelCatur.setup();
			playScreenCtrl.setModel(modelCatur, p1, p2);
			playScreenCtrl.mulai();
			
			tampil(1);
		}
	}
	
	public void tampil(int index){
		for (int i=0; i<app.length; i++){
			app[i].setVisible(false);
		}
		app[index].setVisible(true);
	}
	
	public void tutup(){
		for (int i=0; i<app.length; i++){
			app[i].dispose();
		}
	}
}
