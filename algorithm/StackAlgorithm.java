package algorithm;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import dataStructures.FiFo;
import dataStructures.LiFo;
import fileIO.CoordParser;
import fileIO.IllegalCharacterException;
import fileIO.MapParser;
import fileIO.MapPoint;
import fileIO.ParseMethods;

public class StackAlgorithm {
	private final int NORTH = 0;
	private final int SOUTH = 1;
	private final int EAST = 2;
	private final int WEST = 3;
	private MapPoint start;
	private MapPoint[][] map;
	private ParseMethods mp;
	private boolean debug = false;
	private ArrayList<MapPoint> path = new ArrayList<MapPoint>();
	private LiFo<MapPoint> stack = new LiFo<MapPoint>();
	private ArrayList<MapPoint> bestpath = new ArrayList<MapPoint>();
	public StackAlgorithm(String filename, boolean debug, int index, boolean isCoord) throws IOException, IllegalCharacterException {
		this.debug = debug;
		if(debug) {System.out.println("Begin constructor");}
		if(isCoord) {mp = new CoordParser(filename, debug, index);}
		else{mp = new MapParser(filename, debug, index);}
		map = new MapPoint[mp.getHeight()][mp.getWidth()];
		for(MapPoint m : mp.toArray()) {
			if(debug) {System.out.println("Adding " + m.getData() + " at: " + m.getRow() + "," + m.getCol());}
			map[m.getRow()-1][m.getCol()] = m;
		}
		if(debug) {
			System.out.println("Map array:");
			for(MapPoint[] sa : map) {for(MapPoint s : sa) {System.out.print(s.toString());}}
		}
		Pather(mp.getStartPos(), NORTH);
		//addPlus();
		if(debug) {System.out.println("StackAlgorithm.calculateIdeal()");}
		//path.add(start);
		if(false) {
			System.out.println("Unsorted array:");
			for(MapPoint m : path) {
				System.out.print(m.toString());
			}
			System.out.println();
			Collections.sort(path);
			System.out.println("Sorted array:");
			for(MapPoint m : path) {
				System.out.print(m.toString());
			}
			System.out.println();
		}
//		for(int i = path.size()-2; i >= 0; i--) {
//			calculateIdeal(path.get(i), path.get(i + 1), mp.getStartPos().toPoint());
//		}
		//path = bestpath;
		for(MapPoint[] ma : map) {for(MapPoint m : ma) {m.hasVisited = false;}}
		stack = new LiFo<MapPoint>();
		System.out.println(didWork(start, 1, mp.getStartPos().toPoint(), 0));
		System.out.println(stack.toString());
		addPlus();
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
		if(d > 4) {d = 0;}
		if(debug) {System.out.println("Testing point" + M.toString() + "With direction of" + d);}
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
			Pather(stack.pop(), d);
		}else{
			if(debug) {System.out.println("Found impass.");}
			if(debug && M.hasVisited == true) {System.out.println("Visited Square");}
			M.hasVisited = true;
			Pather(stack.pop(), d+1);
		}

	}
	private boolean didWork(MapPoint M, int d, Point end, int runNum) {
		if(d > 4) {d = 0;}
		runNum++;
		ArrayList<MapPoint> ma = new ArrayList<MapPoint>();
		boolean isNotEnd = false;
		if(M.getRow() != 1 && d != SOUTH) {ma.add(map[M.getRow()-2][M.getCol()]);}
		if(M.getRow() != map.length && d != NORTH) {ma.add(map[M.getRow()][M.getCol()]);}
		if(M.getCol() != 0 && d != WEST) {ma.add(map[M.getRow()-1][M.getCol()-1]);}
		if(M.getCol() != map[0].length-1&& d != EAST) {ma.add(map[M.getRow()-1][M.getCol()+1]);}
		MapPoint nextPoint = ma.get(0);
		for(MapPoint m : ma) {
			if(debug) {System.out.println("Testing point " + m.toString() +" Returned " + (!m.getData().equals("@") && m.hasVisited == false ));}
			if(!m.getData().equals("@") && m.hasVisited == false) {
				isNotEnd = true;
				if(nextPoint.getDistance(end) > m.getDistance(end)) {nextPoint = m; }
			}
		}
		if(debug) {System.out.println("End boolean:" + isNotEnd + "For run " + runNum);}
		if(isNotEnd) {
			stack.push(M);
			M.hasVisited = true;
			return didWork(nextPoint, d, end, runNum);
		}else {
			return isNotEnd;
		}
		
		
	}
	private void calculateIdeal(MapPoint currentPoint, MapPoint PreviousPoint, Point End) {	
		if(debug) {System.out.println("Point 1:" + currentPoint.toString() + "Point 2:" + PreviousPoint.toString());
		System.out.println("Distance 1:" + currentPoint.getDistance(start.toPoint()) + "Distance 2:" + PreviousPoint.getDistance(start.toPoint()));}	
		if(currentPoint.getDistance(start.toPoint()) < PreviousPoint.getDistance(start.toPoint())) {
			if(debug) {System.out.println(currentPoint.getDistance(start.toPoint()) <= PreviousPoint.getDistance(start.toPoint()));}
			bestpath.add(currentPoint);
		}

	}
	private void addPlus() {
		if(debug) {System.out.println("Setting points to plus:");}
		for(MapPoint mp : path) {
			if(debug) {System.out.print(mp.toString());}
			map[mp.getRow()-1][mp.getCol()].setData("+");
		}
		map[start.getRow()-1][start.getCol()].setData("C");
		if(debug) {System.out.println();}
	}
	public String getMap() {
		String s = "";
		for(MapPoint[] ma : map) {
			for(MapPoint m : ma) {
				s += m.getData();
			}
			s += "\n";
		}
		return s;
	}
}

