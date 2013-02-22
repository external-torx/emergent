package com.meleemistress.core.event;

import com.meleemistress.core.MovingParticle;
import com.meleemistress.core.ParticleEngine;

public class MovingParticleEventHandler {
	
	public MovingParticleEventHandler() {
		
	}
	
	public void handleEvent(HitWallEvent e) {
		System.out.println("Handling event");
		MovingParticle p = e.getParticle();
		
		if (p.getX() >= ParticleEngine.DIMENSION || p.getX() <= 0) {
			p.setXvel(-p.getXvel());
		}
		
		if (p.getY() >= ParticleEngine.DIMENSION || p.getY() <= 0) {
			p.setYvel(-p.getYvel());
		}
	}

}
