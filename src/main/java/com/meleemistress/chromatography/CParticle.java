package com.meleemistress.chromatography;

import com.meleemistress.particle.Particle;

/**
 * A particle implementation for chromatography simulation. Includes custom behavior for 
 * stopping movement.
 * @author hparry
 *
 */

public class CParticle {
	
	private Particle p;
	private int maxDistance;
	
	public CParticle(Particle p, int maxDistance) {
		this.p = p;
		this.maxDistance = maxDistance;
	}

	public Particle getP() {
		return p;
	}

	public void setP(Particle p) {
		this.p = p;
	}

	public int getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(int maxDistance) {
		this.maxDistance = maxDistance;
	}
	
	public void updatePositionByVector(int time) {
		//find distance from origin
		
		//sqrt((x2 - x1)^2 + (y2 - y1)^2)
		
		double distance = Math.sqrt(Math.pow((p.getX() - Universe.ORIGIN),2) + Math.pow((p.getY() - Universe.ORIGIN),2));
		if (distance < maxDistance) {
			p.updatePositionByVector(time);
		}
	}

}
