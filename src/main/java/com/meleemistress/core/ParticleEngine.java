package com.meleemistress.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

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

import processing.core.PApplet;
import processing.core.PImage;

import com.meleemistress.core.event.EventDispatcher;
import com.meleemistress.particle.Particle;

public class ParticleEngine extends PApplet {

	/**
	 * test more commits
	 */
	private static final long serialVersionUID = 1L;
	private static final int NUM_PARTICLES = 10;
	
	public static final int DIMENSION = 800;
	
	private  KnowledgeBase kbase;	
	private StatelessKnowledgeSession ksession;
	private KnowledgeRuntimeLogger klogger;
	private ArrayList<Particle> movingParticles;
	static final int rad = 10;
	private static final int partsPerSide = DIMENSION/rad;
	
	
	private PImage img;
	
	private Background background;
	
	public void setup() {
		size(DIMENSION, DIMENSION);
		background(255);
		img = loadImage("particle_background.png");
		try {
            // load up the knowledge base
            kbase = readKnowledgeBase();
            ksession = kbase.newStatelessKnowledgeSession();
            klogger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");
            
            movingParticles = new ArrayList<Particle>(NUM_PARTICLES);
            for (int i = 0; i < NUM_PARTICLES; i++) {
            	movingParticles.add(i, new Particle.Builder().type("moving")
            			.xpos(Math.random() * DIMENSION)
            			.ypos(Math.random() * DIMENSION)
            			.xvel(Math.random() * 5)
            			.yvel(Math.random() * 5)
            			.radius(10)
            			.build());
            	
            }
            
            
            background = new Background(partsPerSide, partsPerSide, rad);
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
		
	}
	
	public void draw() {
		//need to redraw the background every time if we don't want trailing
        background(img);
        noStroke();
        //TODO change this to use Color object
        fill(255);
        Collection<Object> stuff = new LinkedList<Object>();
        stuff.addAll(movingParticles);
        stuff.add(background);
        ksession.execute(stuff);
        for (Particle[] ps : background.getParticles()) {
        	for (Particle p : ps) {
        		if (p != null) {
        			fill(255,255,255,p.getAlpha());
        			rect(p.getX(), p.getY(), p.getRadius(), p.getRadius());
        		}
        	}
        }
        
        for (int i = 0; i < NUM_PARTICLES; i++) {
        	Particle p = movingParticles.get(i);
        	//TODO change this to use Color object
        	fill(100, 0, 100);
    		ellipse(p.getX(), p.getY(), p.getRadius(), p.getRadius());
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

	//TODO HPARRY this is such a bad idea. Fix it
	public static EventDispatcher getEventDispatcher() {
		return new EventDispatcher();
	}

}
