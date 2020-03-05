package algorithm;
import java.io.IOException;
import java.util.ArrayList;
import dataStructures.LiFo;
import fileIO.IllegalCharacterException;
import fileIO.mapParser;
import fileIO.mapPoint;

public class StackAlgorithm {
	private final int NORTH = 0;
	private final int SOUTH = 1;
	private final int EAST = 2;
	private final int WEST = 3;
	private mapPoint[][] map;
	private boolean debug = false;
	private ArrayList<mapPoint> path = new ArrayList<mapPoint>();
	private LiFo<mapPoint> stack = new LiFo<mapPoint>();
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
		if(M.getData().equals("C")) {if(debug) {System.out.println("Found cake.");}}
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
	private void addPlus() {
		if(debug) {System.out.println("Setting points to plus:");}
		for(mapPoint mp : path) {
			if(debug) {System.out.print(mp.toString());}
			map[mp.getRow()-1][mp.getCol()].setData("+");
		}
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

