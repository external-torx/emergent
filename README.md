emergent
========

An exploration into modeling emergent behavior using a stateless rules engine, a piddly event manager, and some math.

ParticleEngine.java is a basic system of foreground particles that bounce off walls and adjust the opacity of the background particles they hit.

ChromatographyEngine simulates a paper chromatography diffusion experiment. Note that it doesn't do this particularly well. I'm not a chemist. But there are particles, they have different colors, and they diffuse to different distances which is really what chromatography is all about for the rest of us.

###To build and run this, you will need:

maven 3. That'll take care of everything else for you.


###Eclipse plugins that will make your life much easier: 
Drools: http://www.jboss.org/drools/downloads  
Proclipsing: http://code.google.com/p/proclipsing/  

Import the base directory as an existing project into Eclipse and run ParticleEngine.java and ChromatographyEngine.java as java applets.
