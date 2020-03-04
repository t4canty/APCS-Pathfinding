package algorithm;
import java.io.IOException;
import java.util.ArrayList;
import dataStructures.LiFo;
import fileIO.IllegalCharacterException;
import fileIO.mapParser;
import fileIO.mapPoint;

public class QueueAlgorithm {
	private final int NORTH = 0;
	private final int SOUTH = 1;
	private final int EAST = 2;
	private final int WEST = 3;
	private mapPoint[][] map;
	private boolean debug = false;
	public ArrayList<mapPoint> path = new ArrayList<mapPoint>();
	private LiFo<mapPoint> stack = new LiFo<mapPoint>();
	public QueueAlgorithm(String filename, int index, boolean debug) throws IOException, IllegalCharacterException {
		this.debug = debug;
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
	}
	public QueueAlgorithm(String filename, boolean debug) throws IOException, IllegalCharacterException {
		this(filename, 0, debug);
	}
	public QueueAlgorithm(String filename, int index) throws IOException, IllegalCharacterException {
		this(filename, index, false);
	}
	public QueueAlgorithm(String filename) throws IOException, IllegalCharacterException {
		this(filename, 0, false);
	}
	private void Pather(mapPoint M, int d) {
		if(debug) {System.out.println("Testing point" + M.toString() + "With direction of" + d);}
		if(d > 4) {d = 0;}
		if(M.getRow() != 1 && d != SOUTH) {stack.push(map[M.getRow()-2][M.getCol()]);}
		if(M.getRow() != map.length && d != NORTH) {stack.push(map[M.getRow()][M.getCol()]);}
		if(M.getCol() != 0 && d != WEST) {stack.push(map[M.getRow()-1][M.getCol()-1]);}
		if(M.getCol() != map[0].length-1&& d != EAST) {stack.push(map[M.getRow()-1][M.getCol()+1]);}
		switch (M.getData()) {
		case "C":
			path.add(M);
			break;
		case ".":
			if(!M.hasVisited) {path.add(M); Pather(stack.pop(), d);}
			else {Pather(stack.pop(), d+1);}
			break;
		case "@":
			Pather(stack.pop(), d+1);
			break;
		case "|":
			Pather(stack.pop(), d+1);
			break;
		case "K":
			Pather(stack.pop(),d+1);
		}
	}
}

