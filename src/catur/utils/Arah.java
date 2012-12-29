package catur.utils;

public enum Arah {
	UTARA(-1, 0), TIMURLAUT(-1, 1), TIMUR(0, 1), TENGGARA(1,1),
	SELATAN(1, 0), BARATDAYA(1, -1), BARAT(0, -1), BARATLAUT(-1, -1);
	
	private int x;
	private int y;
	
	Arah(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public static Arah getArah(int x, int y){
		int normalX = normalisasi(x);
		int normalY = normalisasi(y);
		Arah arah = null;
		
		if (normalX == -1 ){
			if (normalY == -1){
				arah = Arah.BARATLAUT;
			} else if (normalY == 0){
				arah = UTARA;
			} else {
				arah = TIMURLAUT;
			}
		} else if (normalX == 0){
			if (normalY == -1){
				arah = BARAT;
			} else {
				arah = TIMUR;
			}
		} else {
			if (normalY == -1){
				arah = BARATDAYA;
			} else if (normalY == 0){
				arah = SELATAN;
			} else {
				arah = TENGGARA;
			}
		}
		return arah;
	}
	
	private static int normalisasi(int value){
		int result = 0;
		if (value <= -1){
			result = -1;
		} else if (value >= 1){
			result = 1;
		}
		return result;
	}
}
