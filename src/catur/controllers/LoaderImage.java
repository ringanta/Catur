package catur.controllers;

import java.net.URL;

import javax.swing.ImageIcon;

public class LoaderImage {
	public static final String PATH = "images/";
	
	/**
	 * Mengambil gambar dari file system
	 * @param name nama file
	 * @return ImageIcon gambar
	 */
	public static ImageIcon getImage(String name){
		ImageIcon icon = new ImageIcon(getAbsolutePath(name));
		return icon;
	}
	
	/**
	 * Menentukan lokasi absolut dari sebuah file
	 * @param name nama file
	 * @return URL lokasi file
	 */
	public static URL getAbsolutePath(String name){
		URL path = LoaderImage.class.getResource(PATH + name);
		return path;
	}
}
