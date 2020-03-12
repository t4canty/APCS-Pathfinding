package algorithm;

import java.util.ArrayList;

import fileIO.MapPoint;

public class OptimalPath {
	private ArrayList<MapPoint> mapPoints = new ArrayList<MapPoint>();
	public OptimalPath(MapPoint start, MapPoint finish, MapPoint[][] map) {
		
	}
	private void pather(MapPoint M, MapPoint start, MapPoint finish, MapPoint[][] map) {
		if(M.getData().equals("K")) {mapPoints.add(M);}
		else {
			if(M.getRow() != 1 && !map[M.getRow()-2][M.getCol()].hasVisited && !map[M.getRow()-2][M.getCol()].getData().equals("@") ) {mapPoints.add(map[M.getRow()-2][M.getCol()]);}
			if(M.getRow() != map.length && !map[M.getRow()][M.getCol()].hasVisited && !map[M.getRow()][M.getCol()].getData().equals("@")) {mapPoints.add(map[M.getRow()][M.getCol()]);}
			if(M.getCol() != 0 && !map[M.getRow()-1][M.getCol()-1].hasVisited && !map[M.getRow()-1][M.getCol()-1].getData().equals("@")) {mapPoints.add(map[M.getRow()-1][M.getCol()-1]);}
			if(M.getCol() != map[0].length-1 && !map[M.getRow()-1][M.getCol()+1].hasVisited && !map[M.getRow()-1][M.getCol()+1].getData().equals("@")) {mapPoints.add(map[M.getRow()-1][M.getCol()+1]);}
			
		}
		
	}
}
