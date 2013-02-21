package com.meleemistress.core;

public class StillParticle extends Particle {

	//alpha == opacity
	private int alpha = 255;
	public static final int ALPHA_DECAY = 50;
	public StillParticle(double x, double y, int radius) {
		super(x, y, radius);
		
	}
	public int getAlpha() {
		return alpha;
	}
	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}


}
