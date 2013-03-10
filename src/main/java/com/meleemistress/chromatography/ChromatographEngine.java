package com.meleemistress.chromatography;

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

import com.meleemistress.core.event.EventDispatcher;
import com.meleemistress.particle.Color;
import com.meleemistress.particle.Particle;

/**
 * An engine to model chromatography and diffusion
 * @author hparry
 *
 */
public class ChromatographEngine extends PApplet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  KnowledgeBase kbase;
	private StatelessKnowledgeSession ksession;
	private KnowledgeRuntimeLogger klogger;
	
	public static final int DIMENSION = 400;
	
	private static final int NUM_PARTICLES = 800;
	
	private static final int ORIGIN = 20;
	
	ArrayList<Particle> fastParticles; 
	ArrayList<Particle> slowParticles;
	
	
	public void setup() {
		size(DIMENSION, DIMENSION);
		//create particles
		fastParticles = new ArrayList<Particle>(NUM_PARTICLES);
		slowParticles = new ArrayList<Particle>(NUM_PARTICLES);
		for (int i = 0; i < NUM_PARTICLES; i ++) {
			fastParticles.add(new Particle.Builder()
							.type("moving")
							.xpos(ORIGIN + Math.random())
							.ypos(ORIGIN + Math.random())
							.xvel(Math.random() * ((int) (Math.random() * 2) == 1 ? 1 : -1))
							.yvel(Math.random() * ((int) (Math.random() * 2) == 1 ? 1 : -1))
							.radius(1)
							.color(new Color(0,0,0))
							.build());
			
			slowParticles.add(new Particle.Builder()
							.type("moving")
							.xpos(ORIGIN + Math.random())
							.ypos(ORIGIN + Math.random())
							.xvel(Math.random() / 2 * ((int) (Math.random() * 2) == 1 ? 1 : -1))
							.yvel(Math.random() / 2 * ((int) (Math.random() * 2) == 1 ? 1 : -1))
							.radius(1)
							.color(new Color(255, 0, 0))
							.build());
						
		}
		
		try {
            // load up the knowledge base
            kbase = readKnowledgeBase();
            ksession = kbase.newStatelessKnowledgeSession();
            klogger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
	}
	
	public void draw() {
		//need to redraw the background every time if we don't want trailing
        background(255);
        noStroke();
        //TODO change this to use Color object
        fill(255);
        Collection<Object> stuff = new LinkedList<Object>();
        stuff.addAll(fastParticles);
        stuff.addAll(slowParticles);
        ksession.execute(stuff);
        
        
        for (int i = 0; i < NUM_PARTICLES; i++) {
        	Particle p = fastParticles.get(i);
        	fill(p.getColor().getR(), p.getColor().getB(), p.getColor().getG());
    		ellipse(p.getX(), p.getY(), p.getRadius(), p.getRadius());
    		
    		p = slowParticles.get(i);
        	fill(p.getColor().getR(), p.getColor().getB(), p.getColor().getG());
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
