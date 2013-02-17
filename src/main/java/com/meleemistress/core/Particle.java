package com.meleemistress.core;

/**
 * A single Particle object
 * @author hparry
 *
 */
public class Particle {

	private String name;
	private boolean foundSomething;
	
	public Particle(String name) {
		this.name = name;
		foundSomething = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isFoundSomething() {
		return foundSomething;
	}

	public void setFoundSomething(boolean foundSomething) {
		this.foundSomething = foundSomething;
	}
	
	
}
