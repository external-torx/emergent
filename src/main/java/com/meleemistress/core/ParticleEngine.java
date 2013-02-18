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
	private static final int NUM_ITERATIONS = 15;
	
	public void setup() {
		size(200,200);
		background(0);
		try {
            // load up the knowledge base
            KnowledgeBase kbase = readKnowledgeBase();
            StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
            KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");
            
            Particle[] particles = new Particle[NUM_PARTICLES];
            for (int i = 0; i < NUM_PARTICLES; i++) {
            	particles[i] = new Particle("p" + i);
            	particles[i].setLuck(Math.floor((Math.max(i - (Math.random() * 5), 0))));
            	
            }
            
            for (int i = 0; i < NUM_ITERATIONS; i++) {
            	for (Particle p : particles) {
            		ksession.execute(p);
            	}
            	
            }
            logger.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
	}
	
	public void draw() {
		stroke(255);
		if (mousePressed) {
			line(mouseX, mouseY, pmouseX, pmouseY);
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
