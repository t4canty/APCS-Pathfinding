package algorithm;

import java.io.IOException;
import java.util.ArrayList;
import fileIO.CoordParser;
import fileIO.IllegalCharacterException;
import fileIO.MapParser;
import fileIO.MapPoint;

public class OptimalPath extends ParentAlgortithm{
	//==========Variables==========//
	private ArrayList<MapPoint> path = new ArrayList<MapPoint>();
	private int maxRuns;
	//==========Constructors==========//
	/**
	 * Runs through the map with the optimal path algorithm.
	 * @param filename
	 * Name of the file to parse.
	 * @param debug
	 * Debug flag.
	 * @param index
	 * Index at which to start parsing at.
	 * @param isCoord
	 * Specifies whether the map is a coordinate-based map or an ASCII map.
	 * @throws IOException
	 * Throws IOException when FileReader has an issue.
	 * @throws IllegalCharacterException
	 * Throws IllegalChar when parsing the map and there is an error with the char.
	 */
	public OptimalPath(String filename, boolean debug, int index, boolean isCoord) throws IOException, IllegalCharacterException {
		this.debug = debug;														//Setting the debug flag.
		if(debug) {System.out.println("Begin constructor");}					
		if(isCoord) {mp = new CoordParser(filename, debug, index);}				//Deciding between using a coordinateParser or a MapParser
		else{mp = new MapParser(filename, debug, index);}
		map = new MapPoint[mp.getHeight()][mp.getWidth()];						//Setting up the map.
		maxRuns = mp.getHeight() * mp.getWidth();								//Setting maximum possible runs.
		for(MapPoint m : mp.toArray()) {										//Adding points into the array.
			if(debug) {System.out.println("Adding " + m.getData() + " at: " + m.getRow() + "," + m.getCol());}
			map[m.getRow()-1][m.getCol()] = m;
		}
		if(debug) {
			System.out.println("Map array:");
			for(MapPoint[] sa : map) {for(MapPoint s : sa) {System.out.print(s.toString());}}
		}
		start = mp.getEndPos();													//getting startPos.
		betterPath(start, 1, NORTH);											//Pathing
		removeExcess(mp.getStartPos(), path);
		addPlus(!debug, path);													//Set points to their runNumber (Change to optimal points later)
	}
	/**
	 * Sets up an OptimalPath using a pre-existing map, and then stores it's path in an ArrayList.
	 * @param start
	 * Starting MapPoint.
	 * @param map
	 * 2D array of points to use.
	 * @param debug
	 * Debug flag.
	 */
	public OptimalPath(MapPoint start, MapPoint[][] map, boolean debug) {
		this.debug = debug;														//Set up local vars
		this.map = map;
		maxRuns = map[0].length * map.length;									//Setting maxRuns
		if(debug) System.out.println("Optimize constructor");
		betterPath(start, 1, NORTH);											//begin Pathing
	}
	//==========Private Methods==========//
	private void betterPath(MapPoint M, int numRun, int d) {
		if(d > 4) d = 0;														//If the direction is greater than 4, loop back to 0.
		if(numRun <= maxRuns) {													//StackOverflow protection
			ArrayList<MapPoint> ms = new ArrayList<MapPoint>();					//Temporary arrayList to store the 3 coordinates around M
			//Store the points around M
			if(M.getRow() != 1 && !map[M.getRow()-2][M.getCol()].hasVisited && d != SOUTH) {ms.add(map[M.getRow()-2][M.getCol()]);}
			if(M.getRow() != map.length && !map[M.getRow()][M.getCol()].hasVisited && d != NORTH) {ms.add(map[M.getRow()][M.getCol()]);}
			if(M.getCol() != 0 && !map[M.getRow()-1][M.getCol()-1].hasVisited && d != WEST ) {ms.add(map[M.getRow()-1][M.getCol()-1]);}
			if(M.getCol() != map[0].length-1 && !map[M.getRow()-1][M.getCol()+1].hasVisited && d != EAST) {ms.add(map[M.getRow()-1][M.getCol()+1]);}
			//Iterate over the points and test them.
			for(MapPoint m : ms) {
				if(debug) System.out.println("Testing point " + m.toString() + "With run " + numRun + "and Direction " + d);
				if(!m.hasVisited) m.setRun(numRun);								//Set run to NumRun if not visited.
				if(m.getData().equals("K")) path.add(m);						//End branch if K is found.
				else if(!m.getData().equals("@") && m.hasVisited == false) {	//If an open, unvisited spot is found, add it to the path and start a new branch.
					m.hasVisited = true; 										
					if(debug) m.setData("+");									//Debug to print the map every step.
					if(debug) System.out.println(getMap());
					path.add(m);
					betterPath(m, numRun + 1, d);
				}
				else {d++; m.hasVisited = true;}								//Try a different direction if the current one doesn't work.
			}
		}
	}
	//==========Getter==========//
	/**
	 * Getter for the path.
	 * @return
	 * Returns the path created by the optimal algorithm.
	 */
	public ArrayList<MapPoint> getPath(){return path;}
}