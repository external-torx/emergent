package com.meleemistress.chromatography;

import com.meleemistress.particle.Particle;

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
	
	//if we're too far from the origin, set velocity
	//and acceleration to zero
	//TODO I think this should technically be part of the rule system
	public void checkForRangeBoundaries(Particle p) {
		double distance = Math.sqrt(Math.pow((p.getX() - Universe.ORIGIN),2) + Math.pow((p.getY() - Universe.ORIGIN),2));
		if (distance >= MAX_DISTANCE) {
			p.setScale(0);
		}
	}
	

}
