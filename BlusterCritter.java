/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>CrabCritter</code> looks at a limited set of neighbors when it eats and moves.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class BlusterCritter extends Critter
{
	private static final double DARKENING_FACTOR = 0.1;
	private final int courage;
    public BlusterCritter(int c)
    {
        courage = c;
    }

    /**
     * Gets the actors for processing. Implemented to return the actors that
     * occupy neighboring grid locations. Override this method in subclasses to
     * look elsewhere for actors to process.<br />
     * Postcondition: The state of all actors is unchanged.
     * @return a list of actors that this critter wishes to process.
     */
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> one = getGrid().getNeighbors(getLocation());
        ArrayList<Actor> two = new ArrayList<Actor>();
        ArrayList<Actor> tmp;
        for(Actor idk : one) {
			tmp = getGrid().getNeighbors(idk.getLocation());
			for(Actor act : tmp)
				if(two.indexOf(act) == -1)
					two.add(act);
		}
		return two;
    }
	
    /**
     * Processes the elements of <code>actors</code>. New actors may be added
     * to empty locations. Implemented to "eat" (i.e. remove) selected actors
     * that are not rocks or critters. Override this method in subclasses to
     * process actors in a different way. <br />
     * Postcondition: (1) The state of all actors in the grid other than this
     * critter and the elements of <code>actors</code> is unchanged. (2) The
     * location of this critter is unchanged.
     * @param actors the actors to be processed
     */
    public void processActors(ArrayList<Actor> actors)
    {
		int cnt = 0;
		Color c = getColor();
        for (Actor a : actors)
        {
            if (a instanceof Critter)
                cnt	++;
        }
        if(cnt >= courage) {
			int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
			int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
			int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));

			setColor(new Color(red, green, blue));
		}
		else {
			int red = (int) ((255-c.getRed()) * (1 - DARKENING_FACTOR));
			int green = (int) ((255-c.getGreen()) * (1 - DARKENING_FACTOR));
			int blue = (int) ((255-c.getBlue()) * (1 - DARKENING_FACTOR));

			setColor(new Color(255-red, 255-green, 255-blue));
		}
    }
    
   
}
