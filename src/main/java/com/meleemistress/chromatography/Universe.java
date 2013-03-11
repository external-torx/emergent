package com.meleemistress.chromatography;

public class Universe {
	
	public static final int ORIGIN = 200;
	public static final int NUM_PARTICLES = 2000;
	public static final int SCALE = 20;
	public static final int MAX_DISTANCE = 150;
	public static final int DIMENSION = 400;
	
	
	private int time;
	
	public Universe() {
		time = 0;
	}
	
	
	public int incrementTime() {
		time++;
		return time;
	}
	
	public int getTime() {
		return time;
	}
	

}
