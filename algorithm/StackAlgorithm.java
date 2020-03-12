package algorithm;

import java.io.IOException;
import dataStructures.LiFo;
import fileIO.CoordParser;
import fileIO.IllegalCharacterException;
import fileIO.MapParser;
import fileIO.MapPoint;

public class StackAlgorithm extends ParentAlgortithm{
	private LiFo<MapPoint> stack = new LiFo<MapPoint>();
	private OptimalPath o;
	public StackAlgorithm(String filename, boolean debug, int index, boolean isCoord) throws IOException, IllegalCharacterException {
		this.debug = debug;
		if(debug) {System.out.println("Begin constructor");}
		if(isCoord) {mp = new CoordParser(filename, debug, index);}
		else{mp = new MapParser(filename, debug, index);}
		map = new MapPoint[mp.getHeight()][mp.getWidth()];
		maxRuns = mp.getHeight() * mp.getWidth();
		for(MapPoint m : mp.toArray()) {
			if(debug) {System.out.println("Adding " + m.getData() + " at: " + m.getRow() + "," + m.getCol());}
			map[m.getRow()-1][m.getCol()] = m;
		}
		if(debug) {
			System.out.println("Map array:");
			for(MapPoint[] sa : map) {for(MapPoint s : sa) {System.out.print(s.toString());}}
		}
		Pather(mp.getStartPos(), NORTH, 0);
		for(MapPoint[] ma : map) {for(MapPoint m : ma) {m.hasVisited = false;}}
		o = new OptimalPath(start, map);
		path = o.getPath();
		addPlus(false);
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
	private void Pather(MapPoint M, int d, int numRun) {
		if(d > 4) {d = 0;}
		if(debug) {System.out.println("Testing point" + M.toString() + "With direction of" + d);}
		M.setRun(numRun);
		MapPoint tmp;
		if(!M.hasVisited && !M.getData().equals("@")) {
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
		if(M.getData().equals("C")) {start = M; if(debug) {System.out.println("Found cake.");}}
		else if(M.getData().equals(".") && M.hasVisited == false) {
			if(debug) {System.out.println("Found empty unvisited square.");}
			path.add(M);
			M.hasVisited = true;
			Pather(stack.pop(), d, numRun + 1);
		}else{
			if(debug) {System.out.println("Found impass.");}
			if(debug && M.hasVisited == true) {System.out.println("Visited Square");}
			M.hasVisited = true;
			Pather(stack.pop(), d+1, numRun + 1);
		}

	}
	
	
}

