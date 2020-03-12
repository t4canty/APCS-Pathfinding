package algorithm;

import java.io.IOException;
import java.util.ArrayList;
import fileIO.CoordParser;
import fileIO.IllegalCharacterException;
import fileIO.MapParser;
import fileIO.MapPoint;

public class OptimalPath extends ParentAlgortithm{
	private ArrayList<MapPoint> mapPoints = new ArrayList<MapPoint>();
	public OptimalPath(String filename, boolean isCoord, int index, boolean debug) throws IOException, IllegalCharacterException {
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
		betterPath(mp.getEndPos(), 1, NORTH);
		addPlus(false);
	}
	public OptimalPath(MapPoint start, MapPoint[][] map) {
		this.map = map;
		betterPath(start, 1, NORTH);
	}
	public void betterPath(MapPoint M, int numRun, int d) {
		if(d > 4) d = 0;
		if(numRun <= maxRuns) {
			ArrayList<MapPoint> ms = new ArrayList<MapPoint>();
			if(M.getRow() != 1 && !map[M.getRow()-2][M.getCol()].hasVisited && d != SOUTH) {ms.add(map[M.getRow()-2][M.getCol()]);}
			if(M.getRow() != map.length && !map[M.getRow()][M.getCol()].hasVisited && d != NORTH) {ms.add(map[M.getRow()][M.getCol()]);}
			if(M.getCol() != 0 && !map[M.getRow()-1][M.getCol()-1].hasVisited && d != WEST ) {ms.add(map[M.getRow()-1][M.getCol()-1]);}
			if(M.getCol() != map[0].length-1 && !map[M.getRow()-1][M.getCol()+1].hasVisited && d != EAST) {ms.add(map[M.getRow()-1][M.getCol()+1]);}
			for(MapPoint m : ms) {
				System.out.println("Testing point " + m.toString() + "With run " + numRun);
				if(!m.hasVisited) m.setRun(numRun);
				if(m.getData().equals("K")) mapPoints.add(m);
				else if(!m.getData().equals("@") && m.hasVisited == false) {
					m.hasVisited = true; 
					if(debug) m.setData("+");
					if(debug) System.out.println(getMap());
					mapPoints.add(m);
					betterPath(m, numRun + 1, d);
				}
				else {d++; m.hasVisited = true;}
			}
		}
	}
	public ArrayList<MapPoint> getPath(){
		return mapPoints;
	}
}
