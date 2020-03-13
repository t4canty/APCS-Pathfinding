package algorithm;

import java.util.ArrayList;

import fileIO.MapPoint;
import fileIO.ParseMethods;

public abstract class ParentAlgortithm {
	//==========Variables==========//
	protected final int NORTH = 0;
	protected final int SOUTH = 1;
	protected final int EAST = 2;
	protected final int WEST = 3;
	protected MapPoint start;
	protected MapPoint[][] map;
	protected ParseMethods mp;
	protected boolean debug = false;
	//==========Method==========//
	/**
	 * Sets the points on the path to plus.
	 * @param isPlus
	 * Specifies whether or not the points should be set to plus or their RunNumber.
	 * @param path
	 * The path.
	 */
	protected void addPlus(boolean isPlus, ArrayList<MapPoint> path) {
		if(debug) {System.out.println("Setting points to plus:");}
		String s;												//String to set mapPoints to
		for(MapPoint mp : path) {								//Iterate over the path and set the data to s
			if(debug) {System.out.print(mp.toString());}
			if(isPlus) {
				s = "+";
			}
			else {
				if(mp.getRun() >= 10) {s = "&";}
				else {s = "" + mp.getRun();}
			}
			map[mp.getRow()-1][mp.getCol()].setData(s);
		}
		map[start.getRow()-1][start.getCol()].setData("C");	  //Make sure the the cake is visible.
		if(debug) {System.out.println();}
	}
	//==========Getters==========//
	public String getTrue() {
		String s = "";											//String to return.
		if(debug) {												//Set up row and column numbers.
			s += " ";
			for(int i = 0; i < map.length; i++) {
				s += i;
			}
			s += "\n";
		}
		int r = 1;												//Row counter.
		for(MapPoint[] ma : map) {								//Iterate over the map and get hasVisited.
			if(debug) {s += r; r++;}							//Increase row counter every row.
			for(MapPoint mp : ma) {
				if(map[mp.getRow()-1][mp.getCol()].getData().equals("@")) s += "@";	//Draw the walls.
				else if(map[mp.getRow()-1][mp.getCol()].hasVisited) s += "T";	
				else s += ".";
			}
			s += "\n";
		}
		return s;
	}

	/**
	 * Returns a string representation of the map.
	 * @return
	 * Returns the map.
	 */
	public String getMap() {
		String s = "";											//String to return.
		if(debug) {												//Set up row and column numbers.
			s += " ";
			for(int i = 0; i < map.length; i++) {
				s += i;
			}
			s += "\n";
		}
		int r = 1;												//Row counter.
		for(MapPoint[] ma : map) {
			if(debug) {s += r; r++;}
			for(MapPoint m : ma) {								//Increase row counter every row.
				s += m.getData();								//Add the point's data.
			}
			s += "\n";
		}
		return s;
	}
	protected void removeExcess(MapPoint start, ArrayList<MapPoint> path) {
		if(debug) System.out.println("Remove");
		ArrayList<MapPoint> tr = new ArrayList<MapPoint>();
		for(MapPoint m : path) {
			for(MapPoint m2 : path) {
				if(m.getRun() == m2.getRun() && !m.equals(m2)) {
					if(m.getDistance(start.toPoint()) < m2.getDistance(start.toPoint())) {
						tr.add(m2);
					}else {
						tr.add(m);
					}
				}else if (m.getRun() > start.getRun()) {
					tr.add(m);
				}
			}
		}
		for(MapPoint m : tr) {path.remove(m);}
	}
}
