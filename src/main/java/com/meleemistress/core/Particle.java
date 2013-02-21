package com.meleemistress.core;

/**
 * A single Particle object
 * @author hparry
 *
 */
public abstract class Particle {

	private String name;
	
	private int radius;
	
	//x,y positions of object. Can be between 0 and ParticleEngine.DIMENSION. Any value
	//outside that range will put the object off screen
	protected double x;
	protected double y;
	

	
	Particle(double x, double y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
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



	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}


}
