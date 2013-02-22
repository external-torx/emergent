package com.meleemistress.core.event;

public class EventDispatcher {
	
	private MovingParticleEventHandler mpeh;
	
	public EventDispatcher(){
		mpeh = new MovingParticleEventHandler();
	}
	
	public void dispatch(HitWallEvent e) {
		mpeh.handleEvent(e);
	}

}
