package com.meleemistress.shapefill;

import processing.core.PApplet;

import com.meleemistress.particle.Color;
import com.meleemistress.particle.Particle;

public class ShapeFillerEngine extends PApplet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int NUM_PARTICLES = 100;
	
//	square 100x100
//	particle 10x10 = 100 particles
	
	Particle[] particles;
	public void setup() {
		size(400, 400);
		background(255);
		particles = new Particle[NUM_PARTICLES];
		for (int i = 0; i < NUM_PARTICLES; i++) {
			Particle p = new Particle.Builder()
										.xpos(random(400))
										.ypos(random(400))
										.color(new Color(255, 0, 0))
										.radius(10)
										.xvel(random(5) * coinFlip())
										.yvel(random(5) * coinFlip())
										.yaccel(.05)
										.build();
			particles[i] = p;
		}
		
	}
	
	public void draw() {
		background(255);
		noStroke();
		fill(100);
		rect(200,200,100,100);
		
		for (Particle p : particles) {
			p.updatePosition();
			fill(p.getColor().getR(), p.getColor().getB(), p.getColor().getG());
			rect(p.getX(), p.getY(), p.getRadius(), p.getRadius());
		}
	}
	
	private int coinFlip() {
		return (int) (Math.random() * 2) == 1 ? 1 : -1;
	}

}
