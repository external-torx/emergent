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
	private Particle[] particles;
	
	public void setup() {
		size(DIMENSION, DIMENSION);
		background(255);
		//img = loadImage("Candlesticks.jpg");
		try {
            // load up the knowledge base
            kbase = readKnowledgeBase();
            ksession = kbase.newStatelessKnowledgeSession();
            klogger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");
            
            particles = new Particle[NUM_PARTICLES];
            for (int i = 0; i < NUM_PARTICLES; i++) {
            	particles[i] = new Particle("p" + i);
            	
            }
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
	}
	
	public void draw() {
		//need to redraw the background every time if we don't want trailing
        background(255);
        for (int i = 0; i < NUM_PARTICLES; i++) {
        	ksession.execute(particles[i]);
        	fill(100, 0, 100);
    		ellipse(particles[i].getX(), particles[i].getY(), particles[i].getRadius(), particles[i].getRadius());
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
