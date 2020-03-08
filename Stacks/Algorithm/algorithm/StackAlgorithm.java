package algorithm;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import com.sun.javafx.css.CalculatedValue;

import dataStructures.LiFo;
import fileIO.IllegalCharacterException;
import fileIO.mapParser;
import fileIO.mapPoint;

public class StackAlgorithm {
	private final int NORTH = 0;
	private final int SOUTH = 1;
	private final int EAST = 2;
	private final int WEST = 3;
	private mapPoint start;
	private mapPoint[][] map;
	private boolean debug = false;
	private ArrayList<mapPoint> path = new ArrayList<mapPoint>();
	private LiFo<mapPoint> stack = new LiFo<mapPoint>();
	private ArrayList<mapPoint> bestpath = new ArrayList<mapPoint>();
	public StackAlgorithm(String filename, boolean debug, int index) throws IOException, IllegalCharacterException {
		this.debug = debug;
		if(debug) {System.out.println("Begin constructor");}
		mapParser mp = new mapParser(filename, debug, index);
		map = new mapPoint[mp.getHeight()][mp.getWidth()];
		for(mapPoint m : mp.toArray()) {
			if(debug) {System.out.println("Adding " + m.getData() + " at: " + m.getRow() + "," + m.getCol());}
			map[m.getRow()-1][m.getCol()] = m;
		}
		if(debug) {
			System.out.println("Map array:");
			for(mapPoint[] sa : map) {for(mapPoint s : sa) {System.out.print(s.toString());}}
		}
		Pather(mp.getStartPos(), NORTH);
		//addPlus();
		if(debug) {System.out.println("StackAlgorithm.calculateIdeal()");}
		//path.add(start);
		System.out.println("Unsorted array:");
		for(mapPoint m : path) {
			System.out.print(m.toString());
		}
		System.out.println();
		Collections.sort(path);
		System.out.println("Sorted array:");
		for(mapPoint m : path) {
			System.out.print(m.toString());
		}
		System.out.println();
		for(int i = path.size()-2; i >= 0; i--) {
			calculateIdeal(path.get(i), path.get(i + 1), mp.getStartPos().toPoint());
		}
		path = bestpath;
		addPlus();
	}
	public StackAlgorithm(String filename, boolean debug) throws IOException, IllegalCharacterException {
		this(filename, debug, 0);
	}
	public StackAlgorithm(String filename, int index) throws IOException, IllegalCharacterException {
		this(filename, false, index);
	}
	public StackAlgorithm(String filename) throws IOException, IllegalCharacterException {
		this(filename, false, 0);
	}
	private void Pather(mapPoint M, int d) {
		if(d > 4) {d = 0;}
		if(debug) {System.out.println("Testing point" + M.toString() + "With direction of" + d);}
		mapPoint tmp;
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
	private void calculateIdeal(mapPoint currentPoint, mapPoint PreviousPoint, Point End) {	
		if(debug) {System.out.println("Point 1:" + currentPoint.toString() + "Point 2:" + PreviousPoint.toString());
		System.out.println("Distance 1:" + currentPoint.getDistance(start.toPoint()) + "Distance 2:" + PreviousPoint.getDistance(start.toPoint()));}	
		if(currentPoint.getDistance(start.toPoint()) < PreviousPoint.getDistance(start.toPoint())) {
			if(debug) {System.out.println(currentPoint.getDistance(start.toPoint()) <= PreviousPoint.getDistance(start.toPoint()));}
			bestpath.add(currentPoint);
		}

	}
	private void addPlus() {
		if(debug) {System.out.println("Setting points to plus:");}
		for(mapPoint mp : path) {
			if(debug) {System.out.print(mp.toString());}
			map[mp.getRow()-1][mp.getCol()].setData("+");
		}
		map[start.getRow()-1][start.getCol()].setData("C");
		if(debug) {System.out.println();}
	}
	public String getMap() {
		String s = "";
		for(mapPoint[] ma : map) {
			for(mapPoint m : ma) {
				s += m.getData();
			}
			s += "\n";
		}
		return s;
	}
}

