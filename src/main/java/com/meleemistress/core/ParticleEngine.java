package com.meleemistress.core;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatelessKnowledgeSession;

import com.meleemistress.core.Particle.ParticleType;

import processing.core.*;

public class ParticleEngine extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int NUM_PARTICLES = 10;
	
	public static final int DIMENSION = 800;
	
	private  KnowledgeBase kbase;
	private StatelessKnowledgeSession ksession;
	private KnowledgeRuntimeLogger klogger;
	private Particle[][] bgParticles;
	private MovingParticle[] movingParticles;
	private static final int rad = 5;
	private static final int partsPerSide = DIMENSION/rad;
	
	private PImage img;
	
	public void setup() {
		size(DIMENSION, DIMENSION);
		background(255);
		img = loadImage("particle_background.png");
		try {
            // load up the knowledge base
            kbase = readKnowledgeBase();
            ksession = kbase.newStatelessKnowledgeSession();
            klogger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");
            
            movingParticles = new MovingParticle[NUM_PARTICLES];
            for (int i = 0; i < NUM_PARTICLES; i++) {
            	movingParticles[i] = new MovingParticle("p" + i);
            	
            }
            
            
            bgParticles = new Particle[partsPerSide][partsPerSide];
            for (int i = 0; i < partsPerSide; i++) {
            	for (int j = 0; j < partsPerSide; j++) {
            		bgParticles[i][j] = new Particle("p" + i + j);
            		bgParticles[i][j].setX(i * rad);
            		bgParticles[i][j].setY(j * rad);
            	}
            }
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
	}
	
	public void draw() {
		//need to redraw the background every time if we don't want trailing
        background(img);
        noStroke();
        fill(255);
        for (int i = 0; i < partsPerSide; i++) {
        	for (int j = 0; j < partsPerSide; j++){
        		
        		rect(bgParticles[i][j].getX(), bgParticles[i][j].getY(), rad, rad);
        		ksession.execute(bgParticles[i][j]);
        	}
        }
        for (int i = 0; i < NUM_PARTICLES; i++) {
        	ksession.execute(movingParticles[i]);
        	fill(100, 0, 100);
    		ellipse(movingParticles[i].getX(), movingParticles[i].getY(), movingParticles[i].getRadius(), movingParticles[i].getRadius());
        }

	}
	

	public void remove(Particle p) {
		System.out.println("removing...");
		if (p.getType() == ParticleType.STILL) {
			int i = (int) (p.getX() / rad);
			int j = (int) (p.getY() / rad);
			bgParticles[i][j] = null;
		}
		
	}
	
	private static KnowledgeBase readKnowledgeBase() throws Exception {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("Particle.drl"), ResourceType.DRL);
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error: errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        return kbase;
    }


}
