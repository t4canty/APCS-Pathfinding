package algorithm;

import java.util.ArrayList;

import fileIO.MapPoint;
import fileIO.ParseMethods;

public abstract class ParentAlgortithm {
	protected final int NORTH = 0;
	protected final int SOUTH = 1;
	protected final int EAST = 2;
	protected final int WEST = 3;
	protected MapPoint start;
	protected static int maxRuns;
	protected MapPoint[][] map;
	protected ParseMethods mp;
	protected boolean debug = false;
	protected ArrayList<MapPoint> path = new ArrayList<MapPoint>();
	public void addPlus(boolean isPlus) {
		if(debug) {System.out.println("Setting points to plus:");}
		String s;
		for(MapPoint mp : path) {
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
		map[start.getRow()-1][start.getCol()].setData("C");
		if(debug) {System.out.println();}
	}

	public String getTrue() {
		if(debug) {System.out.println("Setting points to plus:");}
		String s = "";
		if(debug) {
			s += " ";
			for(int i = 0; i < map.length; i++) {
				s += i;
			}
			s += "\n";
		}
		int r = 1;
		for(MapPoint[] ma : map) {
			if(debug) {s += r; r++;}
			for(MapPoint mp : ma) {
				if(map[mp.getRow()-1][mp.getCol()].getData().equals("@")) s += "@";
				else if(map[mp.getRow()-1][mp.getCol()].hasVisited) s += "T";
				else s += "F";
			}
			s += "\n";
		}
		return s;
	}


	public String getMap() {
		String s = "";
		if(debug) {
			s += " ";
			for(int i = 0; i < map.length; i++) {
				s += i;
			}
			s += "\n";
		}
		int r = 1;
		for(MapPoint[] ma : map) {
			if(debug) {s += r; r++;}
			for(MapPoint m : ma) {
				s += m.getData();
			}
			s += "\n";
		}
		return s;
	}
}
