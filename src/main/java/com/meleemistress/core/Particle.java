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
	private double x;
	private double y;
	
	//x,y velocities
	private double xvel;
	private double yvel;
	
	
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
		return (float)x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public float getY() {
		return (float)y;
	}

	public void setY(double y) {
		this.y = y;
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

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	
	public void generateStartingPositionAndVelocity() {
		
		x = Math.random() * ParticleEngine.DIMENSION;
		y = Math.random() * ParticleEngine.DIMENSION;
		xvel = Math.random() * 5;
		yvel = Math.random() * 5;
		radius = 10;
	}
	
	public void updatePosition() {
		//bounce off the sides of the viewing window
		x = xvel > 0 ? Math.min(x + xvel, ParticleEngine.DIMENSION) : Math.max(0, x + xvel);
		y = yvel > 0 ? Math.min(y + yvel, ParticleEngine.DIMENSION) : Math.max(0, y + yvel);
	}
}
