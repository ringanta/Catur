package catur.controllers;

import catur.models.Posisi;


public interface PosisiControlable {
	/**
	 * Proses data posisi
	 * @param posisi data
	 */
	public abstract void proses(Posisi posisi);
}
