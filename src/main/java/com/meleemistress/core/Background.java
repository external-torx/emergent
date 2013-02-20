

package com.meleemistress.core;


/**
 * @author hparry
 *
 */
public class Background {

	
	private Particle[][] particles;
	private int radius;
	private int rfill, gfill, bfill;
	
	Background(int numx, int numy, int radius) {
		particles = new Particle[numx][numy];
		this.radius = radius;
		for (int i = 0; i < numx; i++) {
			for (int j = 0; j < numy; j++) {
				particles[i][j] = new StillParticle(radius * i, radius * j, radius);
			}
		}
	}
	
	public Particle[][] getParticles() {
		return particles;
	}
	
	public void removeCollidingParticles(double x, double y) {

		int xIndex = x < 1 ? 0 : (int) Math.floor(x / radius);
		int yIndex = y < 1 ? 0 : (int) Math.floor(y / radius);
		try {
			particles[xIndex][yIndex] = null;
		} catch (ArrayIndexOutOfBoundsException e) {
			//TODO HPARRY this happens when the x or why coordinate is 800. The index works out to the length of the array
			//rather than length -1. Fix it.
			//e.printStackTrace();
		}
	}
	
}
