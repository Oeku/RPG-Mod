package net.zeldadungeons.util;

public class MathUtil {
	
	public static int fakultaet1(int i){
		if(i == 1)return 1;
		else return fakultaet1(i-1)+i;
	}
	
	public static long fakultaet2(long i){
		if(i == 1)return 1;
		else return fakultaet2(i-1)*i;
		
	}
}
