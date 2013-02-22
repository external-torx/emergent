package com.meleemistress.core.event;

import com.meleemistress.core.MovingParticle;

/**
 * 
 * @author Halloran Parry
 *
 */
public class HitWallEvent {
	
	private MovingParticle particle;
	
	public HitWallEvent(MovingParticle p) {
		this.particle = p;
	}

	public MovingParticle getParticle() {
		return particle;
	}


}
