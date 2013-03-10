package com.meleemistress.core.event;

import com.meleemistress.particle.Particle;

/**
 * 
 * @author Halloran Parry
 *
 */
public class HitWallEvent {
	
	private Particle particle;
	
	public HitWallEvent(Particle particle2) {
		this.particle = particle2;
	}

	public Particle getParticle() {
		return particle;
	}


}
