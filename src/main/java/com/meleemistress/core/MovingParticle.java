package com.meleemistress.core;


/**
 * A Particle with velocity
 * 
 * @author Halloran Parry
 *
 */
public class MovingParticle extends Particle {

	//x,y velocities
	private double xvel;
	private double yvel;
	
	public MovingParticle(String name) {
		super(name);
	}
	
	public double getXvel() {
		return xvel;
	}

	public void setXvel(double xvel) {
		this.xvel = xvel;
	}

	public double getYvel() {
		return yvel;
	}

	public void setYvel(double yvel) {
		this.yvel = yvel;
	}
	
	public void generateStartingPositionAndVelocity() {
		
		setX(Math.random() * ParticleEngine.DIMENSION);
		setY(Math.random() * ParticleEngine.DIMENSION);
		xvel = Math.random() * 5;
		yvel = Math.random() * 5;
		setRadius(10);
	}
	
	
	public void updatePosition() {
		//bounce off the sides of the viewing window
		x = xvel > 0 ? Math.min(x + xvel, ParticleEngine.DIMENSION) : Math.max(0, x + xvel);
		y = yvel > 0 ? Math.min(y + yvel, ParticleEngine.DIMENSION) : Math.max(0, y + yvel);
	}

}
