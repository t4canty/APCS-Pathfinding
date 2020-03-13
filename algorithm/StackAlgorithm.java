package algorithm;

import java.io.IOException;
import java.util.ArrayList;
import dataStructures.LiFo;
import fileIO.CoordParser;
import fileIO.IllegalCharacterException;
import fileIO.MapParser;
import fileIO.MapPoint;

public class StackAlgorithm extends ParentAlgortithm{
	//==========Variables==========//
	private LiFo<MapPoint> stack = new LiFo<MapPoint>();				//private stack to use
	private ArrayList<MapPoint> path = new ArrayList<MapPoint>();		//Path to store points
	private OptimalPath o;												//Optimal path object when the stack finds cake
	//==========Constructors==========//
	public StackAlgorithm(String filename, boolean debug, int index, boolean isCoord) throws IOException, IllegalCharacterException {
		this.debug = debug;												//Set debug flag.
		if(debug) {System.out.println("Begin constructor");}			//Setting up a mapParser or a CoordParser
		if(isCoord) {mp = new CoordParser(filename, debug, index);}
		else{mp = new MapParser(filename, debug, index);}
		map = new MapPoint[mp.getHeight()][mp.getWidth()];				//Setting up the map.
		for(MapPoint m : mp.toArray()) {
			if(debug) {System.out.println("Adding " + m.getData() + " at: " + m.getRow() + "," + m.getCol());}
			map[m.getRow()-1][m.getCol()] = m;
		}
		if(debug) {
			System.out.println("Map array:");
			for(MapPoint[] sa : map) {for(MapPoint s : sa) {System.out.print(s.toString());}}
		}
		Pather(mp.getStartPos(), NORTH);								//Find the cake.
		for(MapPoint[] ma : map) {for(MapPoint m : ma) {m.hasVisited = false;}}	//Reset the map.
		if(debug) System.out.println("Begin optimize");				
		o = new OptimalPath(start, map, debug);							//Find the optimal path.
		path = o.getPath();
		removeExcess(mp.getStartPos(), path);
		addPlus(!debug, path);											//Set to the points to plus. (Change to optimal path to plus later)
		
	}
	public StackAlgorithm(String filename, boolean debug, boolean isCoord) throws IOException, IllegalCharacterException {
		this(filename, debug, 0, isCoord);
	}
	public StackAlgorithm(String filename, int index, boolean isCoord) throws IOException, IllegalCharacterException {
		this(filename, false, index, isCoord);
	}
	public StackAlgorithm(String filename, boolean isCoord) throws IOException, IllegalCharacterException {
		this(filename, false, 0, isCoord);
	}
	private void Pather(MapPoint M, int d) {
		if(d > 4) {d = 0;}																			//set the direction to loop back around when greater than 4
		if(debug) {System.out.println("Testing point" + M.toString() + "With direction of" + d);}	
		MapPoint tmp;																				//temporary point to increase readability.
		if(!M.hasVisited && !M.getData().equals("@")) {												//Add all valid points around M
			if(M.getRow() != 1 && d != SOUTH) {
				tmp = map[M.getRow()-2][M.getCol()];
				if(!tmp.hasVisited) {stack.push(tmp);}
			}
			if(M.getRow() != map.length && d != NORTH) {
				tmp = map[M.getRow()][M.getCol()];
				if(!tmp.hasVisited) {stack.push(tmp);}
			}
			if(M.getCol() != 0 && d != WEST) {
				tmp = map[M.getRow()-1][M.getCol()-1];
				if(!tmp.hasVisited) {stack.push(tmp);}
			}
			if(M.getCol() != map[0].length-1&& d != EAST) {
				tmp = map[M.getRow()-1][M.getCol()+1];
				if(!tmp.hasVisited) {stack.push(tmp);}
			}
		}
		if(M.getData().equals("C")) {start = M; if(debug) {System.out.println("Found cake.");}}		//if cake is found, end recursion
		else if(M.getData().equals(".") && M.hasVisited == false) {									//if empty, unvisited square, continue in the same direction.
			if(debug) {System.out.println("Found empty unvisited square.");}						
			path.add(M);
			M.hasVisited = true;
			Pather(stack.pop(), d);
		}else{
			if(debug) {System.out.println("Found impass.");}										
			if(debug && M.hasVisited == true) {System.out.println("Visited Square");}			
			M.hasVisited = true;																	//if square is not valid, continue with a new direction.
			Pather(stack.pop(), d+1);
		}

	}
}