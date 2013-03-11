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
	
	private Universe universe;
	
	ArrayList<CParticle> fastParticles; 
	ArrayList<CParticle> slowParticles;
	
	
	public void setup() {
		size(Universe.DIMENSION, Universe.DIMENSION);
		//create particles
		fastParticles = new ArrayList<CParticle>(Universe.NUM_PARTICLES);
		slowParticles = new ArrayList<CParticle>(Universe.NUM_PARTICLES);
		for (int i = 0; i < Universe.NUM_PARTICLES; i ++) {
			fastParticles.add(new CParticle(new Particle.Builder()
							.type("moving")
							.xpos(Universe.ORIGIN + Math.random())
							.ypos(Universe.ORIGIN + Math.random())
							.angle(radians((float) (Math.random() * 360)))
							.scale(Math.random() * Universe.SCALE)
							.radius(1)
							.color(new Color(0,0,0))
							.build(), Universe.MAX_DISTANCE));
			
			slowParticles.add(new CParticle(new Particle.Builder()
							.type("moving")
							.xpos(Universe.ORIGIN + Math.random())
							.ypos(Universe.ORIGIN + Math.random())
							.angle(radians((float) (Math.random() * 360)))
							.scale(Math.random() * Universe.SCALE / 2)
							.radius(1)
							.color(new Color(255, 0, 0))
							.build(), Universe.MAX_DISTANCE - 5));
						
		}
		
		try {
            // load up the knowledge base
            kbase = readKnowledgeBase();
            ksession = kbase.newStatelessKnowledgeSession();
            klogger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
		universe = new Universe();
	}
	
	public void draw() {
		universe.incrementTime();
		//need to redraw the background every time if we don't want trailing
        background(255);
        noStroke();
        //TODO change this to use Color object
        fill(255);
        Collection<Object> stuff = new LinkedList<Object>();
        stuff.addAll(fastParticles);
        stuff.addAll(slowParticles);
        stuff.add(universe.getTime());
        ksession.execute(stuff);
        
        
        for (int i = 0; i < Universe.NUM_PARTICLES; i++) {
        	CParticle p = fastParticles.get(i);
        	//TODO this is so fucking ugly. Fix the CParticle data model
        	fill(p.getP().getColor().getR(), p.getP().getColor().getB(), p.getP().getColor().getG());
    		ellipse(p.getP().getX(), p.getP().getY(), p.getP().getRadius(), p.getP().getRadius());
    		
    		p = slowParticles.get(i);
        	fill(p.getP().getColor().getR(), p.getP().getColor().getB(), p.getP().getColor().getG());
    		ellipse(p.getP().getX(), p.getP().getY(), p.getP().getRadius(), p.getP().getRadius());
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
