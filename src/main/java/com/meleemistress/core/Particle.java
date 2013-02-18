package com.meleemistress.core;

/**
 * A single Particle object
 * @author hparry
 *
 */
public class Particle {

	private static final int LUCK_LIMIT = 10;
	private String name;
	private int x;
	private int y;
	
	//see a penny
	//pick it up
	//all day long
	//you'll have good luck
	private int pennies;
	private int luck;
	
	public Particle(String name) {
		this.name = name;
		this.luck = 1;
		this.pennies = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPennies() {
		return pennies;
	}

	public void setPennies(int pennies) {
		this.pennies = pennies;
	}

	public int getLuck() {
		return luck;
	}

	public void setLuck(int luck) {
		this.luck = luck;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean lookForAPenny() {
		int m = (int) Math.floor(Math.random() * (LUCK_LIMIT + 1));
		if (m < luck) {
			pennies++;
			if (luck < LUCK_LIMIT) {
				luck++;
			}
			return true;
		}
		
		return false;
	}
	
	
}
