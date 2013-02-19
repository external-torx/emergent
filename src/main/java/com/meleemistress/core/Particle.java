package com.meleemistress.core;

/**
 * A single Particle object
 * @author hparry
 *
 */
public class Particle {

	private String name;
	
	private int radius;
	
	//x,y positions of object. Can be between 0 and ParticleEngine.DIMENSION. Any value
	//outside that range will put the object off screen
	private float x;
	private float y;
	
	//x,y velocities
	private float xvel;
	private float yvel;
	
	
	public Particle(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getXvel() {
		return xvel;
	}

	public void setXvel(float xvel) {
		this.xvel = xvel;
	}

	public float getYvel() {
		return yvel;
	}

	public void setYvel(float yvel) {
		this.yvel = yvel;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	
	public void generateStartingPositionAndVelocity() {
		x = 0;
		y = 0;
		xvel = 1;
		yvel = 1;
		radius = 10;
	}
	
	public void updatePosition() {
		x = Math.min(x + xvel, ParticleEngine.DIMENSION);
		y = Math.min(y + yvel, ParticleEngine.DIMENSION);
	}
}
