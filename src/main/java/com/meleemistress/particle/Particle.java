package com.meleemistress.particle;

import com.meleemistress.core.ParticleEngine;
import com.meleemistress.core.event.HitWallEvent;

/**
 * A single Particle object
 * @author hparry
 *
 */
public class Particle {

	//user defined particle type. Useful for classifying different 
	//particle incarnations
	private String type;
	
	
	
	//x,y positions of object. Can be between 0 and ParticleEngine.DIMENSION. Any value
	//outside that range will put the object off screen
	protected double x;
	protected double y;
	
	//x and y velocity constants. Can be positive or negative
	protected double xvel;
	protected double yvel;
	
	//x and y acceleration constants. Default to 0
	protected double xaccel;
	protected double yaccel;
	
	//Processing-specific properties
	//TODO move this into a ProcessingParticle class
	protected int alpha;
	//TODO this should be x and y radius
	private int radius;
	protected Color color;
	//TODO add shape
	//private string shape;
	
	/***** vector stuff ****/
	
	//angle is in radians
	private float angle;	
	private double scale;

	
	private Particle(String type, 
					double x, 
					double y, 
					double xvel, 
					double yvel, 
					double xaccel, 
					double yaccel,
					int alpha, 
					int radius, 
					Color color,
					float angle,
					double scale) {
		this.type = type;
		this.x = x;
		this.y = y;
		this.xvel = xvel;
		this.yvel = yvel;
		this.xaccel = xaccel;
		this.yaccel = yaccel;
		this.alpha = alpha;
		this.radius = radius;
		this.color = color;
		this.angle = angle;
		this.scale = scale;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}


	public String getType() {
		return type;
	}

	public void setType(String name) {
		this.type = name;
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
	

	public int getAlpha() {
		return alpha;
	}

	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}
	
	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	
	public void updatePosition() {
		//bounce off the sides of the viewing window
		x = xvel > 0 ? Math.min(x + xvel, ParticleEngine.DIMENSION) : Math.max(0, x + xvel);
		y = yvel > 0 ? Math.min(y + yvel, ParticleEngine.DIMENSION) : Math.max(0, y + yvel);
	}
	
	public void updatePositionByVector(int time) {
		x = Math.sin(angle) * scale * time + 200;
		y = Math.cos(angle) * scale * time + 200;
	}
	
	public void fireWallEvent() {
		HitWallEvent e = new HitWallEvent(this);
		
		ParticleEngine.getEventDispatcher().dispatch(e);
	}

	public static class Builder {
		private String type;
		
		private int radius;
		
		//x,y positions of object. Can be between 0 and ParticleEngine.DIMENSION. Any value
		//outside that range will put the object off screen
		protected double xpos;
		protected double ypos;
		
		//x and y velocity constants. Can be positive or negative
		protected double xvel;
		protected double yvel;
		
		//x and y acceleration constants. Default to 0
		protected double xaccel = 0;
		protected double yaccel = 0;
		
		//opacity
		protected int alpha = 255;
		
		//vector stuff
		private float angle;
		private double scale;
		
		protected Color color;
		
		public Builder xpos(double x) {this.xpos = x; return this;}
		public Builder ypos(double y) {this.ypos = y; return this;}
		public Builder type(String type) {this.type = type; return this;}
		public Builder xvel(double xvel) {this.xvel = xvel; return this;}
		public Builder yvel(double yvel) {this.yvel = yvel; return this;}
		public Builder xaccel(double xaccel) {this.xaccel = xaccel; return this;}
		public Builder yaccel(double yaccel) {this.yaccel = yaccel; return this;}
		public Builder alpha(int alpha) {this.alpha = alpha; return this;}
		public Builder radius(int rad) {this.radius = rad; return this;}
		public Builder color(Color color) {this.color = color; return this;}
		public Builder angle(float angle) {this.angle = angle; return this;}
		public Builder scale(double scale) {this.scale = scale; return this;}
		
		public Particle build() {
			return new Particle(type, xpos, ypos, xvel, yvel, xaccel, yaccel, alpha, radius, color, angle, scale);
		}
		
	}
	
}
